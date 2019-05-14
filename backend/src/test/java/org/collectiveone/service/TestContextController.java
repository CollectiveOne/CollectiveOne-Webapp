package org.collectiveone.service;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.collectiveone.AbstractTest;
import org.collectiveone.common.dto.GetResult;
import org.collectiveone.common.dto.PostResult;
import org.collectiveone.modules.c1.data.DataType;
import org.collectiveone.modules.c1.data.dtos.DataDto;
import org.collectiveone.modules.c1.data.dtos.LinkDto;
import org.collectiveone.modules.c1.data.dtos.TextDataDto;
import org.collectiveone.modules.uprcl.dtos.CommitDto;
import org.collectiveone.modules.uprcl.dtos.ContextDto;
import org.collectiveone.modules.uprcl.dtos.PerspectiveDto;
import org.collectiveone.modules.uprcl.entities.PerspectiveType;
import org.collectiveone.modules.users.AppUserDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.net.AuthRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("serial")
public class TestContextController extends AbstractTest {
	
	private static final Logger logger = LogManager.getLogger(TestContextController.class);
	
	@Autowired
    private MockMvc mockMvc;
	
	private Gson gson = new Gson();
	
	@Value("${AUTH0_ISSUER}")
	private String auth0Domain;
	
	@Value("${AUTH0_AUDIENCE}")
	private String clientId;
	
	@Value("${AUTH0_SECRET}")
	private String clientSecret;
	
	@Value("${TEST_USER_EMAIL_1}")
	private String testEmail1;
	
	@Value("${TEST_USER_PWD_1}")
	private String testPwd1;
	
	private String authorizationTokenUser1;
	
	@Value("${TEST_USER_EMAIL_2}")
	private String testEmail2;
	
	@Value("${TEST_USER_PWD_2}")
	private String testPwd2;
	
	private String authorizationTokenUser2;
	
	private AppUserDto user1;
	
	private AppUserDto user2;
	
	@Before
    public void setUp() throws Exception {
		
		AuthAPI auth = new AuthAPI(auth0Domain, clientId, clientSecret);
		
		AuthRequest request = auth.login(testEmail1, testPwd1)
		    .setScope("openid contacts");
		try {
		    TokenHolder holder = request.execute();
		    authorizationTokenUser1 = holder.getIdToken();
		} catch (APIException exception) {
			System.out.println(exception);
		} catch (Auth0Exception exception) {
			System.out.println(exception);
		}
		
		authorizationTokenUser1 = authorizationTokenUser1.substring(0, authorizationTokenUser1.length() - 5) + "AAAAA";
		
		MvcResult result = this.mockMvc
	    	.perform(get("/1/u/me")
	        .header("Authorization", "Bearer " + authorizationTokenUser1))	    	
	    	.andReturn();
		
		assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
        		200, result.getResponse().getStatus());
		
		
		GetResult<AppUserDto> getResult = 
        		gson.fromJson(result.getResponse().getContentAsString(), 
        				new TypeToken<GetResult<AppUserDto>>(){}.getType());
        
        user1 = getResult.getData();
		
        logger.debug("Test user created:" + result.getResponse().getContentAsString());
        
        request = auth.login(testEmail2, testPwd2)
    		    .setScope("openid contacts");
		try {
		    TokenHolder holder = request.execute();
		    authorizationTokenUser2 = holder.getIdToken();
		} catch (APIException exception) {
			System.out.println(exception);
		} catch (Auth0Exception exception) {
			System.out.println(exception);
		}
		
		result = this.mockMvc
	    	.perform(get("/1/u/me")
	        .header("Authorization", "Bearer " + authorizationTokenUser2))	    	
	    	.andReturn();
		
		assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
        		200, result.getResponse().getStatus());
		
		
		getResult = 
        		gson.fromJson(result.getResponse().getContentAsString(), 
        				new TypeToken<GetResult<AppUserDto>>(){}.getType());
        
        user2 = getResult.getData();
		
        logger.debug("Test user created:" + result.getResponse().getContentAsString());

    }

    @After
    public void tearDown() {
        // clean up after each test method
    }
    
    private LinkDto buildPerspectiveObjects(String creator, String text, Long timestamp, PerspectiveDto parent) throws JsonProcessingException {
    	ContextDto context = new ContextDto();
    	
    	context.setCreator(creator);
    	context.setNonce(0L);
    	context.setTimestamp(timestamp);
    	
    	PerspectiveDto perspective = new PerspectiveDto();
    	
    	perspective.setContext(context);
    	perspective.setCreator(creator);
    	perspective.setTimestamp(timestamp);
    	perspective.setName("DEFAULT");
    	perspective.setType(PerspectiveType.DYNAMIC);
    	
    	CommitDto head = new CommitDto();
    	
    	head.setCreator(user1.getDid());
    	head.setMessage("");
    	head.setNonce(0L);
    	
    	DataDto data = new DataDto();
    	
    	TextDataDto textContent = new TextDataDto();
    	textContent.setText(text);
		
    	data.setType(DataType.TEXT);
    	data.setJsonData(textContent.getDataJson());
    	
    	head.setData(data);
    	perspective.setHead(head);
    	
    	LinkDto linkDto = new LinkDto();
    	linkDto.setPerspective(perspective);
    	linkDto.setParent(parent);
    	
    	return linkDto;
    }
    
    @Test
    @Rollback(false)
    public void createContext() throws Exception {
    	Long start;
    	ObjectMapper objectMapper = new ObjectMapper();
    	
    	String text = "My First Context";
    	Long timestamp = System.currentTimeMillis();
    	
    	LinkDto link = buildPerspectiveObjects(user1.getDid(), text, timestamp, user1.getRootPerspective());
    	String json = gson.toJson(link);
        MvcResult result = null;
        
        /** -------------------------
         *  create perspective 
         * -------------------------- */
        start = System.currentTimeMillis();
        result = this.mockMvc
	    	.perform(post("/1/l")
	    	.header("Authorization", "Bearer " + authorizationTokenUser1)
	    	.contentType(MediaType.APPLICATION_JSON)
	    	.content(json))
	    	.andReturn();
        
        logger.debug("perspective created in: " + (System.currentTimeMillis() - start)  + " ms") ;
        
        assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
        		200, result.getResponse().getStatus());
        
        PostResult postResult = gson.fromJson(result.getResponse().getContentAsString(), PostResult.class); 
        
        assertEquals("error creating context: " + postResult.getMessage(),
        		"success", postResult.getResult());
        
        String perspectiveId1 = postResult.getElementId();        
        
        /** -------------------------
         *  read perspective 
         * -------------------------- */
        start = System.currentTimeMillis();
        result = this.mockMvc
    	    	.perform(get("/1/p/" + perspectiveId1)
    	    	.header("Authorization", "Bearer " + authorizationTokenUser1))
    	    	.andReturn();
        
        logger.debug("perspective retrieved in: " + (System.currentTimeMillis() - start)  + " ms") ;
        
        assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
        		200, result.getResponse().getStatus());
        
        
		GetResult<PerspectiveDto> getResult = 
        		gson.fromJson(result.getResponse().getContentAsString(), 
        				new TypeToken<GetResult<PerspectiveDto>>(){}.getType());
        
        assertEquals("error getting context: " + getResult.getMessage(),
        		"success", getResult.getResult());
        
        PerspectiveDto perspectiveDto = getResult.getData();
        
        TextDataDto textData = objectMapper.readValue(
        		perspectiveDto.getHead().getData().getJsonData(), TextDataDto.class);
        
        assertEquals("unexpected content",
        		text, textData.getText());
        
        /** -------------------------
         *  read root perspective without subperspectives 
         * -------------------------- */
        start = System.currentTimeMillis();
        result = this.mockMvc
    	    	.perform(get("/1/p/" + user1.getRootPerspective().getId())
    	    	.param("levels", "0")
    	    	.header("Authorization", "Bearer " + authorizationTokenUser1))
    	    	.andReturn();
        
        logger.debug("perspective retrieved in: " + (System.currentTimeMillis() - start)  + " ms") ;
        
        assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
        		200, result.getResponse().getStatus());
        
        
		getResult = 
        		gson.fromJson(result.getResponse().getContentAsString(), 
        				new TypeToken<GetResult<PerspectiveDto>>(){}.getType());
        
        assertEquals("error getting context: " + getResult.getMessage(),
        		"success", getResult.getResult());
        
        perspectiveDto = getResult.getData();

    }
    
}

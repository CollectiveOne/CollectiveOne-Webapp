package org.collectiveone.service;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.collectiveone.AbstractTest;
import org.collectiveone.common.dto.GetResult;
import org.collectiveone.common.dto.PostResult;
import org.collectiveone.modules.c1.data.dtos.DataDto;
import org.collectiveone.modules.c1.data.dtos.TextDataDto;
import org.collectiveone.modules.c1.data.enums.DataType;
import org.collectiveone.modules.uprcl.dtos.CommitDto;
import org.collectiveone.modules.uprcl.dtos.ContextDto;
import org.collectiveone.modules.uprcl.dtos.PerspectiveDto;
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
    
    private List<String> createData(DataDto dataDto, String auth) throws Exception {
    	String json = gson.toJson(Arrays.asList(dataDto));
    	MvcResult result = 
    		this.mockMvc
	    	.perform(post("/1/data")
	    	.header("Authorization", "Bearer " + auth)
	    	.contentType(MediaType.APPLICATION_JSON)
	    	.content(json))
	    	.andReturn();
    	
    	assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
        		200, result.getResponse().getStatus());
        
        PostResult postResult = gson.fromJson(result.getResponse().getContentAsString(), PostResult.class); 
        
        assertEquals("error creating context: " + postResult.getMessage(),
        		"success", postResult.getResult());
        
        return postResult.getElementIds();        
    }
    
    private List<String> createCommit(CommitDto commitDto, String auth) throws Exception {
    	String json = gson.toJson(Arrays.asList(commitDto));
    	MvcResult result = 
    		this.mockMvc
	    	.perform(post("/1/commit")
	    	.header("Authorization", "Bearer " + auth)
	    	.contentType(MediaType.APPLICATION_JSON)
	    	.content(json))
	    	.andReturn();
    	
    	assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
        		200, result.getResponse().getStatus());
        
        PostResult postResult = gson.fromJson(result.getResponse().getContentAsString(), PostResult.class); 
        
        assertEquals("error creating context: " + postResult.getMessage(),
        		"success", postResult.getResult());
        
        return postResult.getElementIds();        
    }
    
    private List<String> createPerspective(PerspectiveDto perspectiveDto, String auth) throws Exception {
    	String json = gson.toJson(Arrays.asList(perspectiveDto));
    	MvcResult result = 
    		this.mockMvc
	    	.perform(post("/1/persp")
	    	.header("Authorization", "Bearer " + auth)
	    	.contentType(MediaType.APPLICATION_JSON)
	    	.content(json))
	    	.andReturn();
    	
    	assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
        		200, result.getResponse().getStatus());
        
        PostResult postResult = gson.fromJson(result.getResponse().getContentAsString(), PostResult.class); 
        
        assertEquals("error creating context: " + postResult.getMessage(),
        		"success", postResult.getResult());
        
        return postResult.getElementIds();        
    }
    
    private List<String> createContext(ContextDto contextDto, String auth) throws Exception {
    	String json = gson.toJson(Arrays.asList(contextDto));
    	MvcResult result = 
    		this.mockMvc
	    	.perform(post("/1/ctx")
	    	.header("Authorization", "Bearer " + auth)
	    	.contentType(MediaType.APPLICATION_JSON)
	    	.content(json))
	    	.andReturn();
    	
    	assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
        		200, result.getResponse().getStatus());
        
        PostResult postResult = gson.fromJson(result.getResponse().getContentAsString(), PostResult.class); 
        
        assertEquals("error creating context: " + postResult.getMessage(),
        		"success", postResult.getResult());
        
        return postResult.getElementIds();        
    }
    
    private DataDto newTextData(String text) throws JsonProcessingException {
    	DataDto data = new DataDto();
    	
    	TextDataDto textContent = new TextDataDto();
    	textContent.setText(text);
		
    	data.setType(DataType.TEXT);
    	data.setJsonData(textContent.getDataJson());
    	
    	return data;    	
    }
    
   
    @Test
    @Rollback(false)
    public void createContext() throws Exception {
    	
    	List<String> contextIds = new ArrayList<String>();
    	List<String> dataLinks = new ArrayList<String>();
    	List<String> commitLinks = new ArrayList<String>();
    	List<String> perspectiveLinks = new ArrayList<String>();
    	
    	contextIds.addAll(createContext(
    			new ContextDto(),
    			authorizationTokenUser1));
    	
    	dataLinks.addAll(createData(
    			newTextData("My first context"), 
    			authorizationTokenUser1));
    	
    	commitLinks.addAll(createCommit(
    			new CommitDto("message 01", new ArrayList<String>(), dataLinks.get(0)),
    			authorizationTokenUser1));
    	
    	perspectiveLinks.addAll(createPerspective(
    			new PerspectiveDto(contextIds.get(0), "my perspective", commitLinks.get(0)),
    			authorizationTokenUser1));
    	

    }
    
}

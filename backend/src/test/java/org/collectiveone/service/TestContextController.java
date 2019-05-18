package org.collectiveone.service;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.collectiveone.AbstractTest;
import org.collectiveone.common.dto.GetResult;
import org.collectiveone.common.dto.PostResult;
import org.collectiveone.modules.c1.data.dtos.DataDto;
import org.collectiveone.modules.c1.data.dtos.DraftDto;
import org.collectiveone.modules.c1.data.dtos.LinkDto;
import org.collectiveone.modules.c1.data.dtos.NodeDataDto;
import org.collectiveone.modules.c1.data.dtos.TextDataDto;
import org.collectiveone.modules.c1.data.entities.ExternalLink;
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
    
    private String createData(DataDto dataDto, String auth) throws Exception {
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
        
        return postResult.getElementIds().get(0);        
    }
    
    private String createCommit(CommitDto commitDto, String auth) throws Exception {
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
        
        return postResult.getElementIds().get(0);        
    }
    
    private String createPerspective(PerspectiveDto perspectiveDto, String auth) throws Exception {
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
        
        return postResult.getElementIds().get(0);        
    }
    
    private String createContext(ContextDto contextDto, String auth) throws Exception {
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
        
        return postResult.getElementIds().get(0);        
    }
    
    private ContextDto getContext(String contextId) throws Exception {
    	MvcResult result = 
        		this.mockMvc
    	    	.perform(get("/1/ctx/" + contextId))
    	    	.andReturn();
    	
		assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
	    		200, result.getResponse().getStatus());
         
 		GetResult<ContextDto> getResult = 
        		gson.fromJson(result.getResponse().getContentAsString(), 
         				new TypeToken<GetResult<ContextDto>>(){}.getType());
         
        assertEquals("error getting perspective: " + getResult.getMessage(),
        		"success", getResult.getResult());
         
        return getResult.getData();
    }
    
    private PerspectiveDto getPerspective(String perspectiveLink) throws Exception {
    	ExternalLink link = new ExternalLink(perspectiveLink);
    	
    	MvcResult result = 
        		this.mockMvc
    	    	.perform(get("/1/persp/" + link.getElement()))
    	    	.andReturn();
    	
		assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
	    		200, result.getResponse().getStatus());
         
 		GetResult<PerspectiveDto> getResult = 
        		gson.fromJson(result.getResponse().getContentAsString(), 
         				new TypeToken<GetResult<PerspectiveDto>>(){}.getType());
         
        assertEquals("error getting perspective: " + getResult.getMessage(),
        		"success", getResult.getResult());
         
        return getResult.getData();
    }
    
    private CommitDto getCommit(String commitLink) throws Exception {
    	ExternalLink link = new ExternalLink(commitLink);
    	
    	MvcResult result = 
        		this.mockMvc
    	    	.perform(get("/1/commit/" + link.getElement()))
    	    	.andReturn();
    	
		assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
	    		200, result.getResponse().getStatus());
         
 		GetResult<CommitDto> getResult = 
        		gson.fromJson(result.getResponse().getContentAsString(), 
         				new TypeToken<GetResult<CommitDto>>(){}.getType());
         
        assertEquals("error getting commit: " + getResult.getMessage(),
        		"success", getResult.getResult());
         
        return getResult.getData();
    }
    
    private TextDataDto getTextData(String link,  String auth) throws Exception {
    	ExternalLink extLink = new ExternalLink(link);
    	
    	MvcResult result = 
        		this.mockMvc
    	    	.perform(get("/1/data/" + extLink.getElement()))
    	    	.andReturn();
    	
		assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
	    		200, result.getResponse().getStatus());
         
 		GetResult<DataDto> getResult = 
        		gson.fromJson(result.getResponse().getContentAsString(), 
         				new TypeToken<GetResult<DataDto>>(){}.getType());
         
        assertEquals("error getting context: " + getResult.getMessage(),
        		"success", getResult.getResult());
         
        return gson.fromJson(getResult.getData().getJsonData(), TextDataDto.class);
    }
    
    private NodeDataDto getNodeData(String link) throws Exception {
    	ExternalLink extLink = new ExternalLink(link);
    	
    	MvcResult result = 
        		this.mockMvc
    	    	.perform(get("/1/data/" + extLink.getElement()))
    	    	.andReturn();
    	
		assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
	    		200, result.getResponse().getStatus());
         
 		GetResult<DataDto> getResult = 
        		gson.fromJson(result.getResponse().getContentAsString(), 
         				new TypeToken<GetResult<DataDto>>(){}.getType());
         
        assertEquals("error getting context: " + getResult.getMessage(),
        		"success", getResult.getResult());
         
        return gson.fromJson(getResult.getData().getJsonData(), NodeDataDto.class);
    }
    
    
    private String commitToPerspective(String perspectiveLink, String commitLink, String auth) throws Exception {
    	ExternalLink link = new ExternalLink(perspectiveLink);
    	
    	MvcResult result = 
    		this.mockMvc
	    	.perform(put("/1/persp/" + link.getElement())
	    	.param("headLink", commitLink)
	    	.header("Authorization", "Bearer " + auth))
	    	.andReturn();
    	
    	assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
        		200, result.getResponse().getStatus());
        
        PostResult postResult = gson.fromJson(result.getResponse().getContentAsString(), PostResult.class); 
        
        assertEquals("error creating context: " + postResult.getMessage(),
        		"success", postResult.getResult());
        
        return postResult.getElementIds().get(0);        
    }
    
    private void updateDraft(DraftDto draft, String auth) throws Exception {
    	String json = gson.toJson(Arrays.asList(draft));
    	
    	MvcResult result = 
    		this.mockMvc
	    	.perform(put("/1/draft")
	    	.contentType(MediaType.APPLICATION_JSON)
	    	.content(json)
	    	.header("Authorization", "Bearer " + auth))
	    	.andReturn();
    	
    	assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
        		200, result.getResponse().getStatus());
    	
    }
    
    private DataDto newTextData(String text) throws JsonProcessingException {
    	DataDto data = new DataDto();
    	data.setType(DataType.TEXT);
    	
    	TextDataDto textContent = new TextDataDto();
    	textContent.setText(text);
		
    	data.setJsonData(textContent.getDataJson());
    	
    	return data;    	
    }
    
    private DataDto newNodeData(String text, List<LinkDto> links) throws JsonProcessingException {
    	DataDto data = new DataDto();
    	data.setType(DataType.NODE);
    	
    	NodeDataDto nodeContent = new NodeDataDto();
    	nodeContent.setText(text);
    	nodeContent.setLinks(links);
		
    	data.setJsonData(nodeContent.getDataJson());
    	
    	return data;    	
    }
    
    private DraftDto newTextDraft(String persctiveLink, DataDto data) {
    	DraftDto draft = new DraftDto();
    	
    	draft.setElementId(persctiveLink);
    	draft.setData(data);
    	
    	return draft;    	
    }
    
    private NodeDataDto getNodeDataOfPerspective(String perspectiveLink) throws Exception {
    	PerspectiveDto perspective = getPerspective(perspectiveLink);
    	CommitDto commit = getCommit(perspective.getHeadLink());
    	return getNodeData(commit.getDataLink());
    }
    
    private TextDataDto getTextDataDraft(String perspectiveLink, String auth) throws Exception {
    	MvcResult result = 
        		this.mockMvc
    	    	.perform(get("/1/draft")
    	    	.param("elementId", perspectiveLink)
    	    	.header("Authorization", "Bearer " + auth))
    	    	.andReturn();
    	
		assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
	    		200, result.getResponse().getStatus());
         
 		GetResult<DataDto> getResult = 
        		gson.fromJson(result.getResponse().getContentAsString(), 
         				new TypeToken<GetResult<DataDto>>(){}.getType());
         
        assertEquals("error getting commit: " + getResult.getMessage(),
        		"success", getResult.getResult());
         
        return gson.fromJson(getResult.getData().getJsonData(), TextDataDto.class); 
    }
    
    private NodeDataDto getNodeDataDraft(String perspectiveLink, String auth) throws Exception {
    	ExternalLink link = new ExternalLink(perspectiveLink);
    	
    	MvcResult result = 
        		this.mockMvc
    	    	.perform(get("/1/draft/" + link.getElement())
    	    	.header("Authorization", "Bearer " + auth))
    	    	.andReturn();
    	
		assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
	    		200, result.getResponse().getStatus());
         
 		GetResult<DataDto> getResult = 
        		gson.fromJson(result.getResponse().getContentAsString(), 
         				new TypeToken<GetResult<DataDto>>(){}.getType());
         
        assertEquals("error getting commit: " + getResult.getMessage(),
        		"success", getResult.getResult());
         
        return gson.fromJson(getResult.getData().getJsonData(), NodeDataDto.class); 
    }
       
    @Test
    @Rollback(false)
    public void createContext() throws Exception {
    	
    	/* creates a context and fill it with one perspective/commit/data triplet */
    	String contextId01 = createContext(
    			new ContextDto(),
    			authorizationTokenUser1);
    	
    	String dataLink01 = createData(
    			newTextData("data 01"), 
    			authorizationTokenUser1);
    	
    	String commitLink01 = createCommit(
    			new CommitDto("message 01", new ArrayList<String>(), dataLink01),
    			authorizationTokenUser1);
    	
    	String perspectiveLink01 = createPerspective(
    			new PerspectiveDto(contextId01, "perspective 01", commitLink01),
    			authorizationTokenUser1);
    	
    	
    	/* add the just created context (perspective) as subcontext of user 1 root */
    	String dataLink02 = createData(
    			newNodeData(
    					getTextData(dataLink01, authorizationTokenUser1).getText(), 
    					Arrays.asList(new LinkDto(perspectiveLink01))), 
    			authorizationTokenUser1);
    	
    	/* commit this to the root perspective */
    	String commitLink02 = createCommit(
    			new CommitDto("added subcontext", Arrays.asList(commitLink01), dataLink02),
    			authorizationTokenUser1);
    	
    	commitToPerspective(user1.getRootPerspectiveLink(), commitLink02, authorizationTokenUser1);
    	
    	/* get root perspective */
    	NodeDataDto data01 = getNodeDataOfPerspective(user1.getRootPerspectiveLink());
    	
    	assertEquals("unexpected number of subperspectives", 
    			1, data01.getLinks().size());
    	
    	assertEquals("unexpected subperspective id", 
    			perspectiveLink01, data01.getLinks().get(0).getLink());
    	
    	/* add a draft data to perspective 01 */
    	String dataDraftText = "data 01 draft";
    	
    	updateDraft(newTextDraft(
    			perspectiveLink01,
    			newTextData(dataDraftText)), 
    		authorizationTokenUser1);
    	
    	TextDataDto data03 = getTextDataDraft(perspectiveLink01, authorizationTokenUser1);
    	
    	assertEquals("unexpected draft data", 
    			dataDraftText, data03.getText());
    	

    }
    
}

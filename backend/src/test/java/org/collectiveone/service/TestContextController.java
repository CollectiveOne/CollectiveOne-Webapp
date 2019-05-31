package org.collectiveone.service;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.sql.Timestamp;
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
import org.collectiveone.modules.c1.data.enums.DataType;
import org.collectiveone.modules.c1.views.ElementViewType;
import org.collectiveone.modules.c1.views.ViewDto;
import org.collectiveone.modules.ipld.IpldService;
import org.collectiveone.modules.uprtcl.dtos.CommitDto;
import org.collectiveone.modules.uprtcl.dtos.ContextDto;
import org.collectiveone.modules.uprtcl.dtos.PerspectiveDto;
import org.collectiveone.modules.uprtcl.entities.Commit;
import org.collectiveone.modules.uprtcl.entities.Context;
import org.collectiveone.modules.uprtcl.entities.Perspective;
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

import io.ipfs.multihash.Multihash.Type;


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
	
	@Value("${TEST_USER_EMAIL_2}")
	private String testEmail2;
	
	@Value("${TEST_USER_PWD_2}")
	private String testPwd2;
	
	private AppUserAuthDto user1;
	
	private AppUserAuthDto user2;
	
	private AuthAPI auth;
	
	private AuthAPI getAuth() {
		if (auth == null) {
			auth = new AuthAPI(auth0Domain, clientId, clientSecret);
		} 
		return auth;
	}
	
	@Before
    public void setUp() throws Exception {
		user1 = signUpUser(testEmail1, testPwd1);
		user2 = signUpUser(testEmail2, testPwd2);
    }

    @After
    public void tearDown() {
        // clean up after each test method
    }
    
    private AppUserAuthDto signUpUser(String testEmail, String testPwd) throws Exception {
    	AppUserAuthDto user = new AppUserAuthDto();
    	
    	AuthRequest request = getAuth().login(testEmail, testPwd)
    		    .setScope("openid contacts");
		try {
		    TokenHolder holder = request.execute();
		    user.setAuthToken(holder.getIdToken());
		} catch (APIException exception) {
			System.out.println(exception);
		} catch (Auth0Exception exception) {
			System.out.println(exception);
		}
		
		MvcResult result = this.mockMvc
	    	.perform(get("/1/u/me")
	        .header("Authorization", "Bearer " + user.getAuthToken()))	    	
	    	.andReturn();
		
		assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
        		200, result.getResponse().getStatus());
		
		
		GetResult<AppUserDto> getResult = 
        		gson.fromJson(result.getResponse().getContentAsString(), 
        				new TypeToken<GetResult<AppUserDto>>(){}.getType());
        
        user.setUser(getResult.getData());
        
        List<PerspectiveDto> perspectives = getPerspectivesOfContext(user.getUser().getRootContextId());
        
        assertEquals("unexpected number of perspectives", 1, perspectives.size());
        
        user.setRootPerspectiveId(perspectives.get(0).getId());
        
        return user;
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
    
    private PerspectiveDto getPerspective(String perspectiveId) throws Exception {
    	
    	MvcResult result = 
        		this.mockMvc
    	    	.perform(get("/1/persp/" + perspectiveId))
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
    
    private List<PerspectiveDto> getPerspectivesOfContext(String contextId) throws Exception {
    	MvcResult result = 
        		this.mockMvc
    	    	.perform(get("/1/ctx/" + contextId + "/perspectives"))
    	    	.andReturn();
    	
		assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
	    		200, result.getResponse().getStatus());
         
 		GetResult<List<PerspectiveDto>> getResult = 
        		gson.fromJson(result.getResponse().getContentAsString(), 
         				new TypeToken<GetResult<List<PerspectiveDto>>>(){}.getType());
         
        assertEquals("error getting perspective: " + getResult.getMessage(),
        		"success", getResult.getResult());
         
        return getResult.getData();
    }
    
    private CommitDto getCommit(String commitId) throws Exception {
    	MvcResult result = 
        		this.mockMvc
    	    	.perform(get("/1/commit/" + commitId))
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
    
    private TextDataDto getTextData(String dataId,  String auth) throws Exception {
    	MvcResult result = 
        		this.mockMvc
    	    	.perform(get("/1/data/" + dataId))
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
    
    private NodeDataDto getNodeData(String dataId) throws Exception {
    	MvcResult result = 
        		this.mockMvc
    	    	.perform(get("/1/data/" + dataId))
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
    
    private String commitToPerspective(String perspectiveId, String commitId, String auth) throws Exception {
    	
    	MvcResult result = 
    		this.mockMvc
	    	.perform(put("/1/persp/" + perspectiveId)
	    	.param("headId", commitId)
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
    
    private void setView(ViewDto view, String auth) throws Exception {
    	String json =  gson.toJson(view);
    	
    	MvcResult result = 
    		this.mockMvc
	    	.perform(put("/1/view")
	    	.contentType(MediaType.APPLICATION_JSON)
	    	.content(json)
	    	.header("Authorization", "Bearer " + auth))
	    	.andReturn();
    	
    	assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
        		200, result.getResponse().getStatus());
    	
    }
    
    private ViewDto getView(String elementId, String inElementId, String auth) throws Exception {
    	MvcResult result = 
        		this.mockMvc
    	    	.perform(get("/1/view/" + elementId)
    	    	.param("inElementId", inElementId)
    	    	.header("Authorization", "Bearer " + auth))
    	    	.andReturn();
    	
		assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
	    		200, result.getResponse().getStatus());
         
 		GetResult<ViewDto> getResult = 
        		gson.fromJson(result.getResponse().getContentAsString(), 
         				new TypeToken<GetResult<ViewDto>>(){}.getType());
         
        assertEquals("error getting view: " + getResult.getMessage(),
        		"success", getResult.getResult());
         
        return getResult.getData();
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
    
    private NodeDataDto getNodeDataOfPerspective(String perspectiveLink) throws Exception {
    	PerspectiveDto perspective = getPerspective(perspectiveLink);
    	CommitDto commit = getCommit(perspective.getHeadId());
    	return getNodeData(commit.getDataId());
    }
    
    private TextDataDto getTextDataDraft(String perspectiveId, String auth) throws Exception {
    	
    	MvcResult result = 
        		this.mockMvc
    	    	.perform(get("/1/draft")
    	    	.param("elementId", perspectiveId)
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
    
    private String getOrigin() throws Exception {
    	MvcResult result = 
        		this.mockMvc
    	    	.perform(get("/1/discovery/you"))
    	    	.andReturn();
    	
		assertEquals("error in http request: " + result.getResponse().getErrorMessage(),
	    		200, result.getResponse().getStatus());
         
 		GetResult<String> getResult = 
        		gson.fromJson(result.getResponse().getContentAsString(), 
         				new TypeToken<GetResult<String>>(){}.getType());
         
        assertEquals("error getting commit: " + getResult.getMessage(),
        		"success", getResult.getResult());
         
        return getResult.getData(); 
    }
    
    private NodeDataDto getNodeDataDraft(String perspectiveId, String auth) throws Exception {
    	
    	MvcResult result = 
        		this.mockMvc
    	    	.perform(get("/1/draft/" + perspectiveId)
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
    
    
    private ContextDto createContextLocal(String creatorId) throws Exception {
    	Context context = new Context();
		
    	context.setCreatorId(creatorId);
		context.setTimestamp(new Timestamp(System.currentTimeMillis()));
		context.setNonce(0L);
		
		context.setId(Type.sha2_256);
		
		return context.toDto();
    }
    
    private PerspectiveDto createPerspectiveLocal(String creatorId, String contextId) throws Exception {
    	Perspective perspective = new Perspective();
		
    	perspective.setCreatorId(creatorId);
    	perspective.setContextId(IpldService.encode(contextId).toBytes());
    	perspective.setHeadId(null);
    	perspective.setName("DEFAULT");
    	perspective.setOrigin(getOrigin());
    	perspective.setTimestamp(new Timestamp(System.currentTimeMillis()));
    	
    	perspective.setId(Type.sha2_256);
		
		return perspective.toDto();
    }
    
    private CommitDto createCommitLocal(
    		String creatorId,
    		String dataId,
    		List<String> parentsIds) throws Exception {
    	
    	Commit commit = new Commit();
		
    	commit.setCreatorId(creatorId);
    	commit.setDataId(IpldService.encode(dataId).toBytes());
    	commit.setMessage("a commit message");
    	commit.setTimestamp(new Timestamp(System.currentTimeMillis()));
    	
    	for (String id : parentsIds) {
    		commit.getParentsIds().add(IpldService.encode(id).toBytes());
    	}
    	
    	commit.setId(Type.sha2_256);
		
		return commit.toDto();
    }
    
       
    @Test
    @Rollback(false)
    public void createContext() throws Exception {
    	
    	/* creates a context and fill it with one perspective/commit/data triplet */
    	String contextId01 = createContext(
    			new ContextDto(),
    			user1.getAuthToken());
    	
    	String dataId01 = createData(
    			newTextData("data 01"), 
    			user1.getAuthToken());
    	
    	String commitId01 = createCommit(
    			new CommitDto("message 01", new ArrayList<String>(), dataId01),
    			user1.getAuthToken());
    	
    	String perspectiveId01 = createPerspective(
    			new PerspectiveDto(contextId01, "perspective 01", commitId01),
    			user1.getAuthToken());
    	
    	
    	/* add the just created context (perspective) as subcontext of user 1 root */
    	String dataId02 = createData(
    			newNodeData(
    					getTextData(dataId01, user1.getAuthToken()).getText(), 
    					Arrays.asList(new LinkDto(perspectiveId01))), 
    			user1.getAuthToken());
    	
    	/* commit this to the root perspective */
    	String commitId02 = createCommit(
    			new CommitDto("added subcontext", Arrays.asList(commitId01), dataId02),
    			user1.getAuthToken());
    	
    	commitToPerspective(user1.getRootPerspectiveId(), commitId02, user1.getAuthToken());
    	
    	/* get root perspective */
    	NodeDataDto data01 = getNodeDataOfPerspective(user1.getRootPerspectiveId());
    	
    	assertEquals("unexpected number of subperspectives", 
    			1, data01.getLinks().size());
    	
    	assertEquals("unexpected subperspective id", 
    			perspectiveId01, data01.getLinks().get(0).getLink());
    	
    	/* add a draft data to perspective 01 */
    	String dataDraftText = "data 01 draft";
    	
    	updateDraft(new DraftDto(
    			perspectiveId01,
    			newTextData(dataDraftText)), 
    			user1.getAuthToken());
    	
    	TextDataDto data03 = getTextDataDraft(perspectiveId01, user1.getAuthToken());
    	
    	assertEquals("unexpected draft data", 
    			dataDraftText, data03.getText());
    	
    	
    	/* set a view for a perspective */
    	setView(
			new ViewDto(perspectiveId01, user1.getRootPerspectiveId(), ElementViewType.TITLE), 
			user1.getAuthToken());
    	
    	ViewDto view01 = getView(perspectiveId01, user1.getRootPerspectiveId(), user1.getAuthToken());
    	
    	assertEquals("unexpectedView", ElementViewType.TITLE, view01.getElementViewType());
    	
    	/* test clone objects */
    	String contextCloneId01 = createContext(
    			createContextLocal(user2.getUser().getDid()),
    			user1.getAuthToken());
    	
    	/* perspective cloned */
    	String contextPerspectiveId01 = createPerspective(
    			createPerspectiveLocal(user2.getUser().getDid(), contextCloneId01),
    			user1.getAuthToken());
    	
    	String dataId03 = createData(
    			newTextData("data 03"), 
    			user1.getAuthToken());
    	
    	String commitCloneId01 = createCommit(
    			createCommitLocal(user2.getUser().getDid(), dataId03, Arrays.asList(commitId01)),
    			user1.getAuthToken());
    	
    	
    	
    }
    
}

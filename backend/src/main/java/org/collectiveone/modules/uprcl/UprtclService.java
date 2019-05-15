package org.collectiveone.modules.uprcl;

import java.sql.Timestamp;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.collectiveone.modules.c1.data.DataType;
import org.collectiveone.modules.c1.data.ExternalLink;
import org.collectiveone.modules.c1.data.Link;
import org.collectiveone.modules.c1.data.LinkObjectType;
import org.collectiveone.modules.c1.data.LinkRepositoryIf;
import org.collectiveone.modules.c1.data.LinkType;
import org.collectiveone.modules.c1.data.NodeData;
import org.collectiveone.modules.c1.data.NodeDataRepositoryIf;
import org.collectiveone.modules.c1.data.TextData;
import org.collectiveone.modules.c1.data.TextDataRepositoryIf;
import org.collectiveone.modules.c1.data.dtos.DataDto;
import org.collectiveone.modules.c1.data.dtos.LinkDto;
import org.collectiveone.modules.c1.data.dtos.NodeDataDto;
import org.collectiveone.modules.c1.data.dtos.PositionedLinkDto;
import org.collectiveone.modules.c1.data.dtos.TextDataDto;
import org.collectiveone.modules.c1.userSupport.WorkingCommit;
import org.collectiveone.modules.c1.userSupport.WorkingCommitRepositoryIf;
import org.collectiveone.modules.uprcl.dtos.CommitDto;
import org.collectiveone.modules.uprcl.dtos.ContextDto;
import org.collectiveone.modules.uprcl.dtos.PerspectiveDto;
import org.collectiveone.modules.uprcl.dtos.PerspectiveDtoLight;
import org.collectiveone.modules.uprcl.entities.Commit;
import org.collectiveone.modules.uprcl.entities.Context;
import org.collectiveone.modules.uprcl.entities.Data;
import org.collectiveone.modules.uprcl.entities.Perspective;
import org.collectiveone.modules.uprcl.entities.PerspectiveType;
import org.collectiveone.modules.uprcl.repositories.CommitRepositoryIf;
import org.collectiveone.modules.uprcl.repositories.ContextRepositoryIf;
import org.collectiveone.modules.uprcl.repositories.DataRepositoryIf;
import org.collectiveone.modules.uprcl.repositories.PerspectiveRepositoryIf;
import org.collectiveone.modules.users.AppUserRepositoryIf;
import org.collectiveone.modules.users.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UprtclService {
	
	private static final Logger logger = LogManager.getLogger(Service.class);
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private AppUserRepositoryIf appUserRepository;
	
	@Autowired
	private ContextRepositoryIf contextRepository;
	
	@Autowired
	private PerspectiveRepositoryIf perspectiveRepository;
	
	@Autowired
	private CommitRepositoryIf commitRepository; 
	
	@Autowired
	private DataRepositoryIf dataRepository;
	
	@Autowired
	private LinkRepositoryIf linkRepository;
	
	@Autowired
	private TextDataRepositoryIf textDataRepository;
	
	@Autowired
	private NodeDataRepositoryIf nodeDataRepository;
	
	
	@Autowired
	private WorkingCommitRepositoryIf workingCommitRepository;
	
	
	
	@Transactional(rollbackOn = Exception.class)
	public Perspective getOrCreateUserContext(String did) throws Exception {
		
		Context context = new Context();
		
		context.setCreator(did);
		context.setNonce(0L);
		context.setTimestamp(new Timestamp(0L));
		
		context.setId();
		context = contextRepository.save(context);
		
		/* create one empty perspective */
		Perspective perspective = createEmptyPerspective(context.getId(), did, null);
				
		return perspective;
	}
	
	@Transactional(rollbackOn = Exception.class)
	public Context getOrCreateContext(ContextDto contextDto, String requestBy) throws Exception {
		
		Context context = null;
		
		if (contextDto.getId() != null) context = contextRepository.findOne(contextDto.getId());
		
		if (context == null) {
			context = createContext(contextDto, requestBy);
		} 
		
		return context;
	}

	@Transactional(rollbackOn = Exception.class)
	public Context createContext(ContextDto contextDto, String requestBy) throws Exception {
		
		Context context = new Context();
		
		context.setCreator(requestBy);
		context.setNonce(contextDto.getNonce());
		context.setTimestamp(new Timestamp(contextDto.getTimestamp()));
		
		context.setId();
		context = contextRepository.save(context);
		
		return context;
		
	}
	
	@Transactional(rollbackOn = Exception.class)
	public PerspectiveDto getPerspective(String perspectiveId, String requestBy, Integer levels) throws Exception {
		return perspectiveRepository.findOne(perspectiveId).toDto(levels);
	}
	
	@Transactional(rollbackOn = Exception.class)
	public Perspective getOrCreatePerspective(PerspectiveDto perspectiveDto, String requestBy) throws Exception {
		
		Perspective perspective = null;
				
		if (perspectiveDto.getId() != null) perspective = perspectiveRepository.findOne(perspectiveDto.getId());
		
		if (perspective == null) {
			perspective = createPerspective(perspectiveDto, requestBy);
		} 
		
		return perspective;
	}
	
	@Transactional(rollbackOn = Exception.class)
	public Perspective createPerspectiveLight(PerspectiveDtoLight perspectiveDto, String requestBy) throws Exception {
		
		Context context = contextRepository.getOne(perspectiveDto.getContextId());
		if (context == null) throw new Exception("context not found");

		ExternalLink externalLink = new ExternalLink(perspectiveDto.getHeadLink());
		
		if (externalLink.isLocal()) {
			Link headLink = linkRepository.getOne(Link.computeId(externalLink).toString()));	
		} else {
			
		}
		
		if (headLink == null) throw new Exception("commit not found");
		
		Perspective perspective = createPerspective(perspectiveDto, context, headLink, requestBy);

		return perspective;
	}
	
	
	@Transactional(rollbackOn = Exception.class)
	public Perspective createPerspective(PerspectiveDtoLight perspectiveDto, Context context, Link headLink, String requestBy) throws Exception {
		Perspective perspective = new Perspective();
		
		perspective.setCreator(requestBy);
		perspective.setName(perspectiveDto.getName());
		perspective.setContext(context);
		perspective.setHeadLink(headLink);
		perspective.setType(perspectiveDto.getType() != null ? perspectiveDto.getType() : PerspectiveType.DYNAMIC);
		
		perspective.setId();
		perspective = perspectiveRepository.save(perspective);
		
		return perspective;
	}
	
	
	@Transactional(rollbackOn = Exception.class)
	public Perspective createPerspective(PerspectiveDto perspectiveDto, String requestBy) throws Exception {
		
		Context context = getOrCreateContext(perspectiveDto.getContext(), requestBy);
		Commit head = getOrCreateCommit(perspectiveDto.getHead(), requestBy);
		
		Link headLink = new Link();
		
		headLink.setType(LinkType.LOCAL);
		headLink.setCommit(head);
		
		Perspective perspective = createPerspective(perspectiveDto.toLight(), context, headLink, requestBy);
		
		return perspective;
	}
	
	@Transactional(rollbackOn = Exception.class)
	public Perspective createEmptyPerspective(String contextId, String requestBy, LinkDto linkDto) throws Exception {
		PerspectiveDto perspectiveDto = new PerspectiveDto();
		
		perspectiveDto.setCreator(requestBy);
		perspectiveDto.setContext(new ContextDto(contextId));
		perspectiveDto.setHead(null);
		perspectiveDto.setName("DEFAULT");
		perspectiveDto.setTimestamp(System.currentTimeMillis());
		perspectiveDto.setType(PerspectiveType.DYNAMIC);
		
		return createPerspective(perspectiveDto, requestBy);
	}
	
	@Transactional(rollbackOn = Exception.class)
	public Link getOrCreateCommitLink(String commitLinkString, String requestBy, CommitDto commitDto) throws Exception {
		ExternalLink externalLink = new ExternalLink(commitLinkString);
		
		Link commitLink = linkRepository.getOne(Link.computeId(externalLink.toString()));
		
		if (commitLink != null) {
			return commitLink;
		} 
	
		commitLink = new Link();
		
		if (externalLink.isLocal()) {
			commitLink.setType(LinkType.LOCAL);
			commitLink.setObjectType(LinkObjectType.COMMIT);
			
			Commit commit = commitRepository.getOne(externalLink.getElement());
			commitLink.setCommit(commit);
			
		} else {
			commitLink.setType(LinkType.EXTERNAL);
			commitLink.setLink(externalLink);
		}
		
		commitLink = linkRepository.save(commitLink);

		return commitLink; 
	}
	
	
	@Transactional(rollbackOn = Exception.class)
	public Commit getOrCreateCommit(CommitDto commitDto, String requestBy) throws Exception {
		Commit commit = null;
				
		if (commitDto.getId() != null) commit = commitRepository.findOne(commitDto.getId());
		
		if (commit == null) {
			commit = createCommit(commitDto, requestBy);
		} 
		
		return commit;
	}
	
	@Transactional(rollbackOn = Exception.class)
	public Commit createCommit(CommitDto commitDto, String requestBy) throws Exception {

		Commit commit = new Commit();
		
		commit.setCreator(requestBy);
		commit.setMessage(commitDto.getMessage());
		
		Data data = getOrCreateData(commitDto.getData(), requestBy);
		commit.setData(data);
		
		for (Map.Entry<String, CommitDto> parentEntry : commitDto.getParents().entrySet()) {
			
			CommitDto parentDto = parentEntry.getValue();
			
			Commit parent = getOrCreateCommit(parentDto, requestBy);
			commit.getParents().put(parent.getId(), parent);	
		}

		commit.setId();
		commit = commitRepository.save(commit);
		
		return commit;
	}
	
	
	@Transactional(rollbackOn = Exception.class)
	public Data getOrCreateData(DataDto dataDto, String requestBy) throws Exception {
		Data data = null;
		
		if (dataDto.getId() != null) data = dataRepository.findOne(dataDto.getId()); 
		
		if (data == null) {
			data = createData(dataDto);
		} 
		
		return data;
	}
	
	
	@Transactional(rollbackOn = Exception.class)
	public Data createData(DataDto dataDto) throws Exception {	
		
		Data data = new Data();
		
		switch (dataDto.getType()) {
		
		case TEXT:
			data.setType(DataType.TEXT);
			TextDataDto textDataDto = new ObjectMapper().readValue(dataDto.getJsonData(), TextDataDto.class);
			
			TextData textData = new TextData();
			textData.setText(textDataDto.getText());
			
			textData = textDataRepository.save(textData);
			data.setTextData(textData);
			break;
			
		case NODE:
			data.setType(DataType.NODE);
			NodeDataDto nodeDataDto = new ObjectMapper().readValue(dataDto.getJsonData(), NodeDataDto.class);
			
			TextData textDataOnNode = new TextData();
			textDataOnNode.setText(nodeDataDto.getText());
			textDataOnNode = textDataRepository.save(textDataOnNode);
			
			NodeData nodeData = new NodeData();
			nodeData.setTextData(textDataOnNode);
			
			for (PositionedLinkDto linkDto : nodeDataDto.getLinks()) {
				nodeData.getLinks().add(createLink(linkDto));
			}
			
			nodeData = nodeDataRepository.save(nodeData);
			
			data.setNodeData(nodeData);
			
			break;
			
		default:
			throw new Exception();
			
		}
		
		data.setId();
		return dataRepository.save(data);
	}
	
	public Link createLink(LinkDto linkDto) throws Exception {
		
		Perspective perspective =perspectiveRepository.getOne(linkDto.getPerspective().getId()); 
		Perspective parent = perspectiveRepository.getOne(linkDto.getParent().getId());
		
		Link link = new Link(); 
		link.setParent(parent);
		link.setPerspective(perspective);
		
		if (linkDto instanceof PositionedLinkDto) {
			PositionedLinkDto positionedLinkDto = (PositionedLinkDto) linkDto;
			link.setAfter(perspectiveRepository.getOne(positionedLinkDto.getPosition().getAfter()));
			link.setBefore(perspectiveRepository.getOne(positionedLinkDto.getPosition().getBefore()));
		}
		
		link = linkRepository.save(link);
		
		return link;
	}
	
	@Transactional(rollbackOn = Exception.class)
	public WorkingCommit getOrCreateWorkingCommit(String requestBy, String perspectiveId) throws Exception {
		WorkingCommit workingCommit = workingCommitRepository.findByUserIdAndPerspectiveId(requestBy, perspectiveId);
		
		if (workingCommit != null) {
			return workingCommit;
		}
		
		workingCommit = new WorkingCommit();
		
		workingCommit.setUser(appUserRepository.getOne(requestBy));
		workingCommit.setPerspective(perspectiveRepository.getOne(perspectiveId));
		workingCommit.setType(DataType.TEXT);
		
		return workingCommitRepository.save(workingCommit);
	}
	
	

}
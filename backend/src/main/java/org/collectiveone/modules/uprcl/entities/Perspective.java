package org.collectiveone.modules.uprcl.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.collectiveone.modules.c1.data.entities.ExternalLink;
import org.collectiveone.modules.ipld.Ipld;
import org.collectiveone.modules.uprcl.dtos.PerspectiveDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.ipfs.multibase.Multibase.Base;
import io.ipfs.multihash.Multihash.Type;

@Entity
@Table(name = "perspectives")
@JsonPropertyOrder({ "creator", "name", "context", "type", "head" })
public class Perspective {

	@Id
	@Column(name = "id", updatable = false, nullable = false, unique = true)
	@JsonIgnore
	private String id;

	private String creatorId;

	private Timestamp timestamp;
	
	private String contextId;
	
	private String name;
	
	private ExternalLink headLink;
	
	
	/* working commits are uncommitted changes, there cab be zero or more per user and perspective. */
	@OneToMany
	@JsonIgnore
	private List<Commit> workingCommits = new ArrayList<Commit>();

	
	public String computeId(io.ipfs.multihash.Multihash.Type t, Base b) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(this);	
		return Ipld.hash(json, t, b);
	}
	
	public PerspectiveDto toDto() throws Exception {
		PerspectiveDto dto = new PerspectiveDto();
		
		dto.setId(id);
		dto.setCreatorId(creatorId);
		dto.setTimestamp(timestamp.getTime());
		dto.setContextId(contextId);
		dto.setName(name);
		dto.setHeadLink(headLink != null ? headLink.toString() : null);
		
		return dto;
	}
	
	@Override
	public String toString() {
		return "     name: " + name + "\n" + 
			   "       id: " + id + "\n" + 
			   "contextId: " + contextId + "\n" +
			   " headLink: " + headLink;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(Type t, Base b) throws Exception {
		this.id = this.computeId(t, b);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getContextId() {
		return contextId;
	}

	public void setContextId(String contextId) {
		this.contextId = contextId;
	}

	public List<Commit> getWorkingCommits() {
		return workingCommits;
	}

	public void setWorkingCommits(List<Commit> workingCommits) {
		this.workingCommits = workingCommits;
	}

	public ExternalLink getHeadLink() {
		return headLink;
	}

	public void setHeadLink(ExternalLink headLink) {
		this.headLink = headLink;
	}
	

}

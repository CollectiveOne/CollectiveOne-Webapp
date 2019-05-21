package org.collectiveone.modules.uprcl.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.collectiveone.modules.c1.data.entities.ExternalLink;
import org.collectiveone.modules.ipld.Ipld;
import org.collectiveone.modules.uprcl.dtos.CommitDto;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.ipfs.multibase.Multibase.Base;

@Entity
@Table(name = "commits")
@JsonPropertyOrder({ "message", "creator", "parents", "dataLink" })
public class Commit {
	
	@Id
	@Column(name = "id", updatable = false, nullable = false, unique = true)
	@JsonIgnore
	private String id;
	
	private String creatorId;
	
	private Timestamp timestamp;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String message;
	
	@ElementCollection
	private List<ExternalLink> parentsLinks = new ArrayList<ExternalLink>();
	
	private ExternalLink dataLink;
		
	public Commit() {
		super();
	}
	
	public String computeId(io.ipfs.multihash.Multihash.Type t, Base b) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(this);
		return Ipld.hash(json, t, b);	
	}
	
	@JsonGetter("parentsLinks")
	public String parentsLinksJson() throws Exception {
		List<String> links = new ArrayList<String>();
		for (ExternalLink parentLink : parentsLinks) {
			links.add(parentLink.toString());
		}
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(links);
		return json;
	}
	
	@JsonGetter("dataLink")
	public String dataLinkJson() {
		return dataLink != null ? dataLink.toString() : null;
	}
	
	public CommitDto toDto() throws JsonProcessingException {
		
		CommitDto dto = new CommitDto();
		
		dto.setId(id);
		dto.setCreatorId(creatorId);
		dto.setTimestamp(timestamp.getTime());
		dto.setMessage(message);
		
		for (ExternalLink parent : parentsLinks) {
			dto.getParentsLinks().add(parent.toString());
		}
		
		dto.setDataLink(dataLink.toString());
		
		return dto;
	}
	
	@Override
	public String toString() {
		return "       id: " + id + "\n" + 
			   "creatorId: " + creatorId + "\n" +
			   " dataLink: " + dataLink.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(io.ipfs.multihash.Multihash.Type t, Base b) throws Exception {
		this.id = this.computeId(t, b);
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<ExternalLink> getParentsLinks() {
		return parentsLinks;
	}

	public void setParentsLinks(List<ExternalLink> parentsLinks) {
		this.parentsLinks = parentsLinks;
	}

	public ExternalLink getDataLink() {
		return dataLink;
	}

	public void setDataLink(ExternalLink dataLink) {
		this.dataLink = dataLink;
	}
	
}

package org.collectiveone.modules.uprcl.entities;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.bitcoinj.core.Base58;
import org.collectiveone.modules.uprcl.dtos.CommitDto;
import org.hibernate.annotations.SortNatural;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "commits")
@JsonPropertyOrder({ "message", "creator", "nonce", "parents", "content" })
public class Commit {
	
	@Id
	@Column(name = "id", updatable = false, nullable = false, unique = true)
	@JsonIgnore
	private String id;
	
	private String creator;
	
	private Long nonce;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String message;
	
	@OneToMany
	@MapKey(name="id")
	@SortNatural
	private SortedMap<String, Commit> parents = new TreeMap<String, Commit>();
	
	@ManyToOne
	private Data data;	
	
	public Commit() {
		super();
	}
	
	public String computeId() {
		try {
			MessageDigest digestInstance = MessageDigest.getInstance("SHA-256");
			ObjectMapper objectMapper = new ObjectMapper();
			
			String json = objectMapper.writeValueAsString(this);
			byte[] hash = digestInstance.digest(json.getBytes());
			
			return Base58.encode(hash);	
		} catch (Exception e) {
			// TODO
		}
		return null;
	}
	
	public CommitDto toDto() throws JsonProcessingException {
		
		CommitDto dto = new CommitDto();
		
		dto.setId(id);
		dto.setMessage(message);
		dto.setNonce(nonce);
		
		for (Map.Entry<String, Commit> parentEntry : parents.entrySet()) {
			dto.getParents().put(parentEntry.getKey(), parentEntry.getValue().toDto());
		}
		
		dto.setData(data.toDto());
		
		return dto;
	}
	
	@JsonGetter("parents")
	public String getParentsArray() {
		return Arrays.toString(parents.keySet().toArray()); 
	}
	
	@JsonGetter("content")
	public String getContentId() {
		return data.getId(); 
	}
	
	@Override
	public String toString() {
		return "     id: " + id + "\n" + 
			   "creator: " + creator + "\n" +
			   "   data: " + (data != null ? data.getId() : "null");
	}

	public String getId() {
		return id;
	}

	public void setId() {
		this.id = this.computeId();
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Long getNonce() {
		return nonce;
	}

	public void setNonce(Long nonce) {
		this.nonce = nonce;
	}

	public SortedMap<String, Commit> getParents() {
		return parents;
	}

	public void setParents(SortedMap<String, Commit> parents) {
		this.parents = parents;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
}

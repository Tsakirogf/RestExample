package com.tsakirogf.RestExample.Model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="contacts")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int code;
	@JsonProperty("contact_name")
	private String contactName;
	@JsonProperty("contact_type")
	private ContactType contactType;
	
	@JsonBackReference
	@ManyToOne
	Friend friend;
	
	
	public Contact(int code, String contactName, ContactType contactType) {
		this.code = code;
		this.contactName = contactName;
		this.contactType = contactType;
	}
	
	public Contact() {
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Friend getFriend() {
		return friend;
	}

	public void setFriend(Friend friend) {
		this.friend = friend;
	}
	
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public ContactType getContactType() {
		return contactType;
	}
	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}
	
	
}

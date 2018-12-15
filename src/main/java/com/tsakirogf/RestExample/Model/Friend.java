package com.tsakirogf.RestExample.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="friend")
public class Friend 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int id;
	@JsonProperty("first-name")
	private String firstName;
	@JsonProperty("last-name")
	private String lastName;
	
	@Embedded
	Address address;

	@JsonManagedReference
	@OneToMany(mappedBy = "friend", cascade = CascadeType.MERGE)
	List<Contact> contacts;

	public Friend(String firstName, String lastName, Address address, ArrayList<Contact> contacts)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contacts = contacts;
    }
	
    public Friend()
    {
    }

    public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	public Address getAddress() 
	{
		return address;
	}
	public void setAddress(Address address) 
	{
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}

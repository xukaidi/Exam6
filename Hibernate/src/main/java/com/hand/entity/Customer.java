package com.hand.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Customer entity. @author MyEclipse Persistence Tools
 */

public class Customer implements java.io.Serializable {

	// Fields
	private int customerid;
	private String firstname;
	private String email;
	private String lastname;
	private String last_update;
	private int store_id;
	private int address_id;
	private String create_date;

	public Customer() {
	}

	public Customer(String firstname, String lastname, String email,
			int store_id, int address_id, String last_update,
			String create_date) {
		this.firstname = firstname;
		this.email = email;
		this.last_update = last_update;
		this.store_id = store_id;
		this.address_id = address_id;
		this.create_date = create_date;
		this.lastname = lastname;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLast_update() {
		return last_update;
	}

	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(short store_id) {
		this.store_id = store_id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Short address_id) {
		this.address_id = address_id;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

}
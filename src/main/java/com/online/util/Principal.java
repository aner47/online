package com.online.util;

import java.io.Serializable;

public class Principal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 577808610308846545L;
	
	private Integer id;
	private String username;
	private String usernameType;
	private String userType;
	private Integer proejctId;
	private Integer enterpriseId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsernameType() {
		return usernameType;
	}
	public void setUsernameType(String usernameType) {
		this.usernameType = usernameType;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public Integer getProejctId() {
		return proejctId;
	}
	public void setProejctId(Integer proejctId) {
		this.proejctId = proejctId;
	}
	
	
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterPriseId) {
		this.enterpriseId = enterPriseId;
	}
	
	
	
	public Principal() {
		super();
	}
	
	
	public Principal(Integer id, String username, String usernameType, String userType) {
		super();
		this.id = id;
		this.username = username;
		this.usernameType = usernameType;
		this.userType = userType;
	}
	
	public Principal(Integer id, String username, String usernameType, String userType,Integer projectId,Integer enterpriseId) {
		super();
		this.id = id;
		this.username = username;
		this.usernameType = usernameType;
		this.userType = userType;
		this.proejctId = projectId;
		this.enterpriseId = enterpriseId;
	}
	@Override
	public String toString() {
		return "Principal [id=" + id + ", username=" + username + ", usernameType=" + usernameType + ", userType="
				+ userType + ", proejctId=" + proejctId + ", enterpriseId=" + enterpriseId + "]";
	}

	
	
}

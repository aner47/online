package com.online.entity.online.a12ceit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.entity.online.Enterprise;
import com.online.entity.online.Project;

/**
 * 市经信委-工业企业信息表
 */
@EntityDescription(caption="工业企业信息表")
@Entity
@Table(name="ol_ceit_enterprise")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_ceit_enterprise")
public class CeitEnterprise extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9222660567026914264L;

	
	/**
	 * 项目
	 */
	private Project project;
	
	private Enterprise enterprise;
	
	/**
	 * 备注
	 */
	private String description;
	
	/**
	 * 法定代表人/联系人
	 */
	@ColumnDescription(caption="01_法定代表人/联系人")
	private String contacts;
	
	/**
	 * 联系电话
	 */
	@ColumnDescription(caption="02_联系电话")
	private String contactNumber;
	
	/**
	 * 行政区
	 */
	@ColumnDescription(caption="03_行政区")
	private String city;
	
	/**
	 * 详细地址
	 */
	@ColumnDescription(caption="04_详细地址")
	private String houseNumber;
	
	/**
	 * 用电量信息
	 */
	@ColumnDescription(caption="05_用电量信息")
	private Double electricityConsumption;
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '法定代表人/联系人'")
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '联系电话'")
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '行政区'")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '详细地址'")
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	
	@Column(columnDefinition="DOUBLE COMMENT '用电量信息'")
	public Double getElectricityConsumption() {
		return electricityConsumption;
	}
	public void setElectricityConsumption(Double electricityConsumption) {
		this.electricityConsumption = electricityConsumption;
	}
	
	
	@ManyToOne
//	@JoinColumn(updatable=false)
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne(cascade=CascadeType.REMOVE,fetch=FetchType.LAZY)
    @JoinColumn()
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '备注'")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}

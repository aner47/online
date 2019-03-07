package com.online.entity.online.a11industrialcommercial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.entity.online.Enterprise;
import com.online.entity.online.Project;

/**
 * 市工商局-干洗企业工商登记信息表
 */
@EntityDescription(caption="干洗企业工商登记信息表")
@Entity
@Table(name="ol_in_co_dry_clean")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_in_co_dry_clean")
public class InCoDryClean extends BaseEntity<Integer> {

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
	 * 干洗剂用量/吨
	 */
	@ColumnDescription(caption="05_干洗剂用量/吨")
	private Double detergent;
	
	
	
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
	
	@Column(columnDefinition="DOUBLE COMMENT '干洗剂用量/吨'")
	public Double getDetergent() {
		return detergent;
	}
	public void setDetergent(Double detergent) {
		this.detergent = detergent;
	}
	
	@ManyToOne
//	@JoinColumn(updatable=false)
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne
//	@JoinColumn(updatable=false)
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

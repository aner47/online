package com.online.entity.online.a13powercompany;

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
 * 供电分公司-企业用电信息表
 */
@EntityDescription(caption="企业用电信息表")
@Entity
@Table(name="ol_enterprise_power")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_enterprise_power")
public class EnterprisePower extends BaseEntity<Integer> {

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
	 * 行政区
	 */
	@ColumnDescription(caption="01_行政区")
	private String city;
	
	/**
	 * 详细地址
	 */
	@ColumnDescription(caption="02_详细地址")
	private String houseNumber;
	
	/**
	 * 用电户号
	 */
	@ColumnDescription(caption="03_用电户号")
	private String powerNo;
	
	/**
	 *年份
	 */
	@ColumnDescription(caption="04_年份")
	private String year;
	/**
	 *2017年用电量（万千瓦时）
	 */
	@ColumnDescription(caption="05_年用电量（万千瓦时）")
	private Double electricityConsumption;
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '年份'")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '用电户号'")
	public String getPowerNo() {
		return powerNo;
	}
	public void setPowerNo(String powerNo) {
		this.powerNo = powerNo;
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

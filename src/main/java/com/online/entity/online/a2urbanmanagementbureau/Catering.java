package com.online.entity.online.a2urbanmanagementbureau;

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
 * 城管局-餐饮行业信息
 */
@EntityDescription(caption="餐饮行业信息")
@Entity
@Table(name="ol_catering")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_catering")
public class Catering extends BaseEntity<Integer> {

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
	 * 是否安装油烟净化装置
	 */
	@ColumnDescription(caption="03_是否安装油烟净化装置")
	private String isLampblackPurification;
	
	/**
	 * 油烟净化效率
	 */
	@ColumnDescription(caption="04_油烟净化效率")
	private Double lampblackPurificationRate;
	
	
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
	@Column(columnDefinition="VARCHAR(255) COMMENT '是否安装油烟净化装置'")
	public String getIsLampblackPurification() {
		return isLampblackPurification;
	}
	public void setIsLampblackPurification(String isLampblackPurification) {
		this.isLampblackPurification = isLampblackPurification;
	}
	@Column(columnDefinition="DOUBLE COMMENT '油烟净化效率'")
	public Double getLampblackPurificationRate() {
		return lampblackPurificationRate;
	}
	public void setLampblackPurificationRate(Double lampblackPurificationRate) {
		this.lampblackPurificationRate = lampblackPurificationRate;
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

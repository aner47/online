package com.online.entity.online.a17fuelgas;

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
 * 长沙新奥燃气有限公司-工业企业燃气消耗信息表
 */
@EntityDescription(caption="工业企业燃气消耗信息表")
@Entity
@Table(name="ol_enterprise_fuel_gas")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_enterprise_fuel_gas")
public class EnterpriseFuelGas extends BaseEntity<Integer> {

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
	 * 天然气消耗量,2015年（万立方米）
	 */
	@ColumnDescription(caption="03_天然气消耗量1（万立方米）")
	private Double year1;
	/**
	 * 天然气消耗量,2016年（万立方米）
	 */
	@ColumnDescription(caption="04_天然气消耗量2（万立方米）")
	private Double year2;
	/**
	 * 天然气消耗量,2017年（万立方米）
	 */
	@ColumnDescription(caption="05_天然气消耗量3（万立方米）")
	private Double year3;
	
	
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
	
	
	
	@Column(columnDefinition="DOUBLE COMMENT '天然气消耗量1'")
	public Double getYear1() {
		return year1;
	}
	public void setYear1(Double year1) {
		this.year1 = year1;
	}
	@Column(columnDefinition="DOUBLE COMMENT '天然气消耗量2'")
	public Double getYear2() {
		return year2;
	}
	public void setYear2(Double year2) {
		this.year2 = year2;
	}
	@Column(columnDefinition="DOUBLE COMMENT '天然气消耗量3'")
	public Double getYear3() {
		return year3;
	}
	public void setYear3(Double year3) {
		this.year3 = year3;
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

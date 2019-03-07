package com.online.entity.online.a9agriculture;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.entity.online.Project;

/**
 * 市农业委-耕地信息表
 */
@EntityDescription(caption="耕地信息表")
@Entity
@Table(name="ol_plough")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_plough")
public class Plough extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9222660567026914264L;

	
	/**
	 * 项目
	 */
	private Project project;
	
	/**
	 * 备注
	 */
	private String description;
	
	/**
	 * 行政区
	 */
	@ColumnDescription(caption="行政区")
	private String city;
	
	/**
	 * 土壤酸碱度
	 */
	@ColumnDescription(caption="土壤酸碱度")
	private String soilPh;
	
	/**
	 * 耕地面积（亩）
	 */
	@ColumnDescription(caption="耕地面积（亩）")
	private Double agriculturalAcreage;
	
	/**
	 * 大豆种植面积（亩）
	 */
	@ColumnDescription(caption="大豆种植面积（亩）")
	private Double soyaAcreage;
	
	/**
	 * 花生种植面积（亩）
	 */
	@ColumnDescription(caption="花生种植面积（亩）")
	private Double peanutAcreage;
	/**
	 * 绿肥种植面积（亩）
	 */
	@ColumnDescription(caption="绿肥种植面积（亩）")
	private Double greenManureAcreage;

	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '行政区'")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '土壤酸碱度'")
	public String getSoilPh() {
		return soilPh;
	}
	public void setSoilPh(String soilPh) {
		this.soilPh = soilPh;
	}
	@Column(columnDefinition="DOUBLE COMMENT '耕地面积（亩）'")
	public Double getAgriculturalAcreage() {
		return agriculturalAcreage;
	}
	public void setAgriculturalAcreage(Double agriculturalAcreage) {
		this.agriculturalAcreage = agriculturalAcreage;
	}
	@Column(columnDefinition="DOUBLE COMMENT '大豆种植面积（亩）'")
	public Double getSoyaAcreage() {
		return soyaAcreage;
	}
	public void setSoyaAcreage(Double soyaAcreage) {
		this.soyaAcreage = soyaAcreage;
	}
	@Column(columnDefinition="DOUBLE COMMENT '花生种植面积（亩）'")
	public Double getPeanutAcreage() {
		return peanutAcreage;
	}
	public void setPeanutAcreage(Double peanutAcreage) {
		this.peanutAcreage = peanutAcreage;
	}
	@Column(columnDefinition="DOUBLE COMMENT '绿肥种植面积（亩）'")
	public Double getGreenManureAcreage() {
		return greenManureAcreage;
	}
	public void setGreenManureAcreage(Double greenManureAcreage) {
		this.greenManureAcreage = greenManureAcreage;
	}
	@ManyToOne
//	@JoinColumn(updatable=false)
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '备注'")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}

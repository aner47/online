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
 * 供电分公司-电厂发电量分月统计信息表
 */
@EntityDescription(caption="电厂发电量分月统计信息表")
@Entity
@Table(name="ol_power_plant_month")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_power_plant_month")
public class PowerPlantMonth extends BaseEntity<Integer> {

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
	 * 一月
	 */
	@ColumnDescription(caption="01_一月（千瓦时）")
	private Double january;
	
	/**
	 * 二月
	 */
	@ColumnDescription(caption="02_二月（千瓦时）")
	private Double february;
	
	/**
	 * 三月
	 */
	@ColumnDescription(caption="03_三月（千瓦时）")
	private Double march;
	
	/**
	 * 四月
	 */
	@ColumnDescription(caption="04_四月（千瓦时）")
	private Double april;
	
	/**
	 * 五月
	 */
	@ColumnDescription(caption="05_五月（千瓦时）")
	private Double may;
	
	/**
	 * 六月
	 */
	@ColumnDescription(caption="06_六月（千瓦时）")
	private Double june; 
	
	/**
	 * 七月
	 */
	@ColumnDescription(caption="07_七月（千瓦时）")
	private Double july;
	
	/**
	 * 八月
	 */
	@ColumnDescription(caption="08_八月（千瓦时）")
	private Double august; 
	
	/**
	 * 九月
	 */
	@ColumnDescription(caption="09_九月（千瓦时）")
	private Double september; 
	
	/**
	 * 十月
	 */
	@ColumnDescription(caption="10_十月（千瓦时）")
	private Double october; 
	
	/**
	 * 十一月
	 */
	@ColumnDescription(caption="11_十一月（千瓦时）")
	private Double november;
	
	/**
	 * 十二月
	 */
	@ColumnDescription(caption="12_十二月（千瓦时）")
	private Double december;

	
	@Column(columnDefinition="DOUBLE COMMENT '一月'")
	public Double getJanuary() {
		return january;
	}
	public void setJanuary(Double january) {
		this.january = january;
	}
	@Column(columnDefinition="DOUBLE COMMENT '二月'")
	public Double getFebruary() {
		return february;
	}
	public void setFebruary(Double february) {
		this.february = february;
	}
	@Column(columnDefinition="DOUBLE COMMENT '三月'")
	public Double getMarch() {
		return march;
	}
	public void setMarch(Double march) {
		this.march = march;
	}
	@Column(columnDefinition="DOUBLE COMMENT '四月'")
	public Double getApril() {
		return april;
	}
	public void setApril(Double april) {
		this.april = april;
	}
	@Column(columnDefinition="DOUBLE COMMENT '五月'")
	public Double getMay() {
		return may;
	}
	public void setMay(Double may) {
		this.may = may;
	}
	@Column(columnDefinition="DOUBLE COMMENT '六月'")
	public Double getJune() {
		return june;
	}
	public void setJune(Double june) {
		this.june = june;
	}
	@Column(columnDefinition="DOUBLE COMMENT '七月'")
	public Double getJuly() {
		return july;
	}
	public void setJuly(Double july) {
		this.july = july;
	}
	@Column(columnDefinition="DOUBLE COMMENT '八月'")
	public Double getAugust() {
		return august;
	}
	public void setAugust(Double august) {
		this.august = august;
	}
	@Column(columnDefinition="DOUBLE COMMENT '九月'")
	public Double getSeptember() {
		return september;
	}
	public void setSeptember(Double september) {
		this.september = september;
	}
	@Column(columnDefinition="DOUBLE COMMENT '十月'")
	public Double getOctober() {
		return october;
	}
	public void setOctober(Double october) {
		this.october = october;
	}
	@Column(columnDefinition="DOUBLE COMMENT '十一月'")
	public Double getNovember() {
		return november;
	}
	public void setNovember(Double november) {
		this.november = november;
	}
	@Column(columnDefinition="DOUBLE COMMENT '十二月'")
	public Double getDecember() {
		return december;
	}
	public void setDecember(Double december) {
		this.december = december;
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

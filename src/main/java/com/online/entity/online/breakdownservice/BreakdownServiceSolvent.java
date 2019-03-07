package com.online.entity.online.breakdownservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.entity.online.Enterprise;
import com.online.entity.online.Project;
import com.online.util.SpringUtils;

/**
 * 有机溶剂使用情况
 *
 */
@EntityDescription(caption="有机溶剂使用情况")
@Entity
@Table(name="ol_breakdown_service_solvent")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_breakdown_service_solvent")
public class BreakdownServiceSolvent extends BaseEntity<Integer> {

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
	 * 溶剂类型
	 */
	@ColumnDescription(caption="溶剂类型")
	private String solventType;
	
	/**
	 * 溶剂性质
	 */
	@ColumnDescription(caption="溶剂性质")
	private String solventNature;
	
	/**
	 * VOCs含量（%）
	 */
	@ColumnDescription(caption="VOCs含量（%）")
	private Double vocsContent;
	
	/**
	 * VOCs挥发度（%）
	 */
	@ColumnDescription(caption="VOCs挥发度（%）")
	private Double vocsVolatility;
	
	
	/**
	 * 年用量
	 */
	@ColumnDescription(caption="年用量")
	private Double annual;
	
	
	
	
	
	/**
	 * 单位
	 */
	@ColumnDescription(caption="单位")
	private String unit;
	
	/**
	 * 一月
	 */
	@ColumnDescription(caption="一月")
	private Double january;
	
	/**
	 * 二月
	 */
	@ColumnDescription(caption="二月")
	private Double february;
	
	/**
	 * 三月
	 */
	@ColumnDescription(caption="三月")
	private Double march;
	
	/**
	 * 四月
	 */
	@ColumnDescription(caption="四月")
	private Double april;
	
	/**
	 * 五月
	 */
	@ColumnDescription(caption="五月")
	private Double may;
	
	/**
	 * 六月
	 */
	@ColumnDescription(caption="六月")
	private Double june; 
	
	/**
	 * 七月
	 */
	@ColumnDescription(caption="七月")
	private Double july;
	
	/**
	 * 八月
	 */
	@ColumnDescription(caption="八月")
	private Double august; 
	
	/**
	 * 九月
	 */
	@ColumnDescription(caption="九月")
	private Double september; 
	
	/**
	 * 十月
	 */
	@ColumnDescription(caption="十月")
	private Double october; 
	
	/**
	 * 十一月
	 */
	@ColumnDescription(caption="十一月")
	private Double november;
	
	/**
	 * 十二月
	 */
	@ColumnDescription(caption="十二月")
	private Double december;
	
	
	

	@Column(columnDefinition="VARCHAR(255) COMMENT '溶剂类型'")
	public String getSolventType() {
		return solventType;
	}

	public void setSolventType(String solventType) {
		this.solventType = solventType;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '溶剂性质'")
	public String getSolventNature() {
		return solventNature;
	}

	public void setSolventNature(String solventNature) {
		this.solventNature = solventNature;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'VOCs含量（%）'")
	public Double getVocsContent() {
		return vocsContent;
	}

	public void setVocsContent(Double vocsContent) {
		this.vocsContent = vocsContent;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'VOCs挥发度（%）'")
	public Double getVocsVolatility() {
		return vocsVolatility;
	}

	public void setVocsVolatility(Double vocsVolatility) {
		this.vocsVolatility = vocsVolatility;
	}
	@Column(columnDefinition="DOUBLE COMMENT '年用量'")
	public Double getAnnual() {
		return annual;
	}

	public void setAnnual(Double annual) {
		this.annual = annual;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '单位'")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getJanuary() {
		return january;
	}

	public void setJanuary(Double january) {
		this.january = january;
	}

	public Double getFebruary() {
		return february;
	}

	public void setFebruary(Double february) {
		this.february = february;
	}

	public Double getMarch() {
		return march;
	}

	public void setMarch(Double march) {
		this.march = march;
	}

	public Double getApril() {
		return april;
	}

	public void setApril(Double april) {
		this.april = april;
	}

	public Double getMay() {
		return may;
	}

	public void setMay(Double may) {
		this.may = may;
	}

	public Double getJune() {
		return june;
	}

	public void setJune(Double june) {
		this.june = june;
	}

	public Double getJuly() {
		return july;
	}

	public void setJuly(Double july) {
		this.july = july;
	}

	public Double getAugust() {
		return august;
	}

	public void setAugust(Double august) {
		this.august = august;
	}

	public Double getSeptember() {
		return september;
	}

	public void setSeptember(Double september) {
		this.september = september;
	}

	public Double getOctober() {
		return october;
	}

	public void setOctober(Double october) {
		this.october = october;
	}

	public Double getNovember() {
		return november;
	}

	public void setNovember(Double november) {
		this.november = november;
	}

	public Double getDecember() {
		return december;
	}

	public void setDecember(Double december) {
		this.december = december;
	}

	@ManyToOne
	@JoinColumn(updatable=false)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne
	@JoinColumn(updatable=false)
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

	@PrePersist
	public void initProjectEnterpise(){
		project = SpringUtils.getCurrentProject();
		enterprise = SpringUtils.getCurrentEnterprise();
	}

	
}

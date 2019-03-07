package com.online.entity.online.a9agriculture;

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
 * 市农业委-渔船每月活动水平信息
 */
@EntityDescription(caption="渔船每月活动水平信息")
@Entity
@Table(name="ol_fishing_boat_month")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_fishing_boat_month")
public class FishingBoatMonth extends BaseEntity<Integer> {

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
	 * 港区名称
	 */
	@ColumnDescription(caption="港区名称")
	private String harbourName;
	
	/**
	 * 渔船类型
	 */
	@ColumnDescription(caption="渔船类型")
	private String boatType;
	
	/**
	 * 一月
	 */
	@ColumnDescription(caption="一月")
	private Integer january;
	
	/**
	 * 二月
	 */
	@ColumnDescription(caption="二月")
	private Integer february;
	
	/**
	 * 三月
	 */
	@ColumnDescription(caption="三月")
	private Integer march;
	
	/**
	 * 四月
	 */
	@ColumnDescription(caption="四月")
	private Integer april;
	
	/**
	 * 五月
	 */
	@ColumnDescription(caption="五月")
	private Integer may;
	
	/**
	 * 六月
	 */
	@ColumnDescription(caption="六月")
	private Integer june; 
	
	/**
	 * 七月
	 */
	@ColumnDescription(caption="七月")
	private Integer july;
	
	/**
	 * 八月
	 */
	@ColumnDescription(caption="八月")
	private Integer august; 
	
	/**
	 * 九月
	 */
	@ColumnDescription(caption="九月")
	private Integer september; 
	
	/**
	 * 十月
	 */
	@ColumnDescription(caption="十月")
	private Integer october; 
	
	/**
	 * 十一月
	 */
	@ColumnDescription(caption="十一月")
	private Integer november;
	
	/**
	 * 十二月
	 */
	@ColumnDescription(caption="十二月")
	private Integer december;

	
	@Column(columnDefinition="VARCHAR(255) COMMENT '船型'")
	public String getBoatType() {
		return boatType;
	}
	public void setBoatType(String boatType) {
		this.boatType = boatType;
	}
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '港区名称'")
	public String getHarbourName() {
		return harbourName;
	}
	public void setHarbourName(String harbourName) {
		this.harbourName = harbourName;
	}
	@Column(columnDefinition="Int COMMENT '一月'")
	public Integer getJanuary() {
		return january;
	}
	public void setJanuary(Integer january) {
		this.january = january;
	}
	@Column(columnDefinition="Int COMMENT '二月'")
	public Integer getFebruary() {
		return february;
	}
	public void setFebruary(Integer february) {
		this.february = february;
	}
	@Column(columnDefinition="Int COMMENT '三月'")
	public Integer getMarch() {
		return march;
	}
	public void setMarch(Integer march) {
		this.march = march;
	}
	@Column(columnDefinition="Int COMMENT '四月'")
	public Integer getApril() {
		return april;
	}
	public void setApril(Integer april) {
		this.april = april;
	}
	@Column(columnDefinition="Int COMMENT '五月'")
	public Integer getMay() {
		return may;
	}
	public void setMay(Integer may) {
		this.may = may;
	}
	@Column(columnDefinition="Int COMMENT '六月'")
	public Integer getJune() {
		return june;
	}
	public void setJune(Integer june) {
		this.june = june;
	}
	@Column(columnDefinition="Int COMMENT '七月'")
	public Integer getJuly() {
		return july;
	}
	public void setJuly(Integer july) {
		this.july = july;
	}
	@Column(columnDefinition="Int COMMENT '八月'")
	public Integer getAugust() {
		return august;
	}
	public void setAugust(Integer august) {
		this.august = august;
	}
	@Column(columnDefinition="Int COMMENT '九月'")
	public Integer getSeptember() {
		return september;
	}
	public void setSeptember(Integer september) {
		this.september = september;
	}
	@Column(columnDefinition="Int COMMENT '十月'")
	public Integer getOctober() {
		return october;
	}
	public void setOctober(Integer october) {
		this.october = october;
	}
	@Column(columnDefinition="Int COMMENT '十一月'")
	public Integer getNovember() {
		return november;
	}
	public void setNovember(Integer november) {
		this.november = november;
	}
	@Column(columnDefinition="Int COMMENT '十二月'")
	public Integer getDecember() {
		return december;
	}
	public void setDecember(Integer december) {
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

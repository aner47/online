package com.online.entity.online.a1constructioncommittee;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.entity.online.Project;

/**
 * 住建委-房屋建筑施工和竣工面积情况统计
 */
@EntityDescription(caption="房屋建筑施工和竣工面积情况统计")
@Entity
@Table(name="ol_build_area")
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING,name="dtype")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_build_area")
public class BaseBuildArea extends BaseEntity<Integer> {

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
	 * 县（市、区）
	 */
	private String county;
	
	/**
	 * 建筑施工面积（平方米）
	 */
	@ColumnDescription(caption="建筑施工面积（平方米）")
	private Double buildArea;
	
	/**
	 * 建筑竣工面积（平方米）
	 */
	@ColumnDescription(caption="建筑竣工面积（平方米）")
	private Double completedArea;
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '县（市、区）'")
	public String getCounty() {
		return county;
	}


	public void setCounty(String county) {
		this.county = county;
	}

	@Column(columnDefinition="DOUBLE COMMENT '建筑施工面积（平方米）'")
	public Double getBuildArea() {
		return buildArea;
	}


	public void setBuildArea(Double buildArea) {
		this.buildArea = buildArea;
	}

	@Column(columnDefinition="DOUBLE COMMENT '建筑竣工面积（平方米）'")
	public Double getCompletedArea() {
		return completedArea;
	}


	public void setCompletedArea(Double completedArea) {
		this.completedArea = completedArea;
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

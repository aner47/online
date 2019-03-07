package com.online.entity.online.a3roadbureau;

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
 * 交通局-道路类型及数量
 */
@EntityDescription(caption="道路类型及数量")
@Entity
@Table(name="ol_road_type_num")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_road_type_num")
public class RoadTypeNum extends BaseEntity<Integer> {

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
	 * 道路类型
	 */
	@ColumnDescription(caption="道路类型")
	private String roadType;
	
	/**
	 * 道路数量（条）
	 */
	@ColumnDescription(caption="道路数量（条）")
	private Integer roadNum;
	
	/**
	 * 道路累计长度（千米）
	 */
	@ColumnDescription(caption="道路累计长度（千米）")
	private Double roadlength;
	
	/**
	 * 核定面积（平方米）
	 */
	@ColumnDescription(caption="核定面积（平方米）")
	private Double checkArea;
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '行政区'")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '道路类型'")
	public String getRoadType() {
		return roadType;
	}
	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	@Column(columnDefinition="Int COMMENT '道路数量（条）'")
	public Integer getRoadNum() {
		return roadNum;
	}
	public void setRoadNum(Integer roadNum) {
		this.roadNum = roadNum;
	}
	@Column(columnDefinition="DOUBLE COMMENT '道路累计长度（千米）'")
	public Double getRoadlength() {
		return roadlength;
	}
	public void setRoadlength(Double roadlength) {
		this.roadlength = roadlength;
	}
	@Column(columnDefinition="DOUBLE COMMENT '核定面积（平方米）'")
	public Double getCheckArea() {
		return checkArea;
	}
	public void setCheckArea(Double checkArea) {
		this.checkArea = checkArea;
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

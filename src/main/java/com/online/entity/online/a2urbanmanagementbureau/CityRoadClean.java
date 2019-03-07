package com.online.entity.online.a2urbanmanagementbureau;

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
 * 城管局-城市道路保洁信息汇总表
 */
@EntityDescription(caption="城市道路保洁信息汇总表")
@Entity
@Table(name="ol_city_road_clean")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_city_road_clean")
public class CityRoadClean extends BaseEntity<Integer> {

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
	 * 清扫面积（平方米）
	 */
	@ColumnDescription(caption="清扫面积（平方米）")
	private Double cleanArea;
	
	/**
	 * 每天清扫频次（次）
	 */
	@ColumnDescription(caption="每天清扫频次（次）")
	private Integer cleanFrequency;
	
	/**
	 * 机扫率（%）
	 */
	@ColumnDescription(caption="机扫率（%）")
	private Double machineCleanRate;
	
	/**
	 * 清扫方式（干法/湿法）
	 */
	@ColumnDescription(caption="清扫方式（干法/湿法）")
	private String cleanMethod;
	
	/**
	 * 每天洒水频次（次）
	 */
	@ColumnDescription(caption="每天洒水频次（次）")
	private Double wateringFrequency;
	
	
	
	
	
	
	
	
	
	
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
	@Column(columnDefinition="DOUBLE COMMENT '清扫面积（平方米）'")
	public Double getCleanArea() {
		return cleanArea;
	}
	public void setCleanArea(Double cleanArea) {
		this.cleanArea = cleanArea;
	}
	@Column(columnDefinition="Int COMMENT '每天清扫频次（次）'")
	public Integer getCleanFrequency() {
		return cleanFrequency;
	}
	public void setCleanFrequency(Integer cleanFrequency) {
		this.cleanFrequency = cleanFrequency;
	}
	@Column(columnDefinition="DOUBLE COMMENT '机扫率（%）'")
	public Double getMachineCleanRate() {
		return machineCleanRate;
	}
	public void setMachineCleanRate(Double machineCleanRate) {
		this.machineCleanRate = machineCleanRate;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '清扫方式（干法/湿法）'")
	public String getCleanMethod() {
		return cleanMethod;
	}
	public void setCleanMethod(String cleanMethod) {
		this.cleanMethod = cleanMethod;
	}
	@Column(columnDefinition="DOUBLE COMMENT '每天洒水频次（次）'")
	public Double getWateringFrequency() {
		return wateringFrequency;
	}
	public void setWateringFrequency(Double wateringFrequency) {
		this.wateringFrequency = wateringFrequency;
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

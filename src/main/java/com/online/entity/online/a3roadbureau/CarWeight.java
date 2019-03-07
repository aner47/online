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
 * 交通局-车重信息表
 */
@EntityDescription(caption="车重信息表")
@Entity
@Table(name="ol_car_weight")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_car_weight")
public class CarWeight extends BaseEntity<Integer> {

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
	 * 车型
	 */
	@ColumnDescription(caption="车型")
	private String carType;
	
	/**
	 * 平均整备质量（吨）
	 */
	@ColumnDescription(caption="平均整备质量（吨）")
	private Double avgQuality;
	
	/**
	 * 平均驾乘客及行李货物质量（吨）
	 */
	@ColumnDescription(caption="平均驾乘客及行李货物质量（吨）")
	private Double avgPassengerQuality;
	
	/**
	 * 平均总质量（吨）
	 */
	@ColumnDescription(caption="平均总质量（吨）")
	private Double avgTotal;
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '车型'")
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	@Column(columnDefinition="DOUBLE COMMENT '平均整备质量（吨）'")
	public Double getAvgQuality() {
		return avgQuality;
	}
	public void setAvgQuality(Double avgQuality) {
		this.avgQuality = avgQuality;
	}
	@Column(columnDefinition="DOUBLE COMMENT '平均驾乘客及行李货物质量（吨）'")
	public Double getAvgPassengerQuality() {
		return avgPassengerQuality;
	}
	public void setAvgPassengerQuality(Double avgPassengerQuality) {
		this.avgPassengerQuality = avgPassengerQuality;
	}
	@Column(columnDefinition="DOUBLE COMMENT '平均总质量（吨）'")
	public Double getAvgTotal() {
		return avgTotal;
	}
	public void setAvgTotal(Double avgTotal) {
		this.avgTotal = avgTotal;
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

package com.online.entity.online.a7trafficpolice;

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
 * 市公安局交警支队-车流量信息
 */
@EntityDescription(caption="车流量信息")
@Entity
@Table(name="ol_vehicle_flowrate")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_vehicle_flowrate")
public class VehicleFlowrate extends BaseEntity<Integer> {

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
	 * 道路名称
	 */
	@ColumnDescription(caption="道路名称")
	private String roadName;
	
	/**
	 * 道路类型
	 */
	@ColumnDescription(caption="道路类型")
	private String roadType;
	
	/**
	 * 日期
	 */
	@ColumnDescription(caption="日期")
	private String roadDate;
	
	/**
	 * 时间段
	 */
	@ColumnDescription(caption="时间段")
	private String roadTime;
	/**
	 * 开始时间
	 */
	@ColumnDescription(caption="开始时间")
	private String startTime;
	/**
	 * 结束时间
	 */
	@ColumnDescription(caption="结束时间")
	private String endTime;
	
	/**
	 * 大型车车流量（辆/小时）
	 */
	@ColumnDescription(caption="大型车车流量（辆/小时）")
	private Integer flowLargeCar;
	
	/**
	 * 小型车车流量（辆/小时）
	 */
	@ColumnDescription(caption="小型车车流量（辆/小时）")
	private Integer flowSmallCar;
	/**
	 * 公交车车流量（辆/小时）
	 */
	@ColumnDescription(caption="公交车车流量（辆/小时）")
	private Integer flowBus;
	/**
	 * 摩托车车流量（辆/小时）
	 */
	@ColumnDescription(caption="摩托车车流量（辆/小时）")
	private Integer flowMotorbike;
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '开始时间'")
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '结束时间'")
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '道路类型'")
	public String getRoadType() {
		return roadType;
	}
	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	
	
	
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '道路名称'")
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '日期'")
	public String getRoadDate() {
		return roadDate;
	}
	public void setRoadDate(String roadDate) {
		this.roadDate = roadDate;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '时间段'")
	public String getRoadTime() {
		return roadTime;
	}
	public void setRoadTime(String roadTime) {
		this.roadTime = roadTime;
	}
	@Column(columnDefinition="Int COMMENT '大型车车流量（辆/小时）'")
	public Integer getFlowLargeCar() {
		return flowLargeCar;
	}
	public void setFlowLargeCar(Integer flowLargeCar) {
		this.flowLargeCar = flowLargeCar;
	}
	@Column(columnDefinition="Int COMMENT '小型车车流量（辆/小时）'")
	public Integer getFlowSmallCar() {
		return flowSmallCar;
	}
	public void setFlowSmallCar(Integer flowSmallCar) {
		this.flowSmallCar = flowSmallCar;
	}
	@Column(columnDefinition="Int COMMENT '公交车车流量（辆/小时）'")
	public Integer getFlowBus() {
		return flowBus;
	}
	public void setFlowBus(Integer flowBus) {
		this.flowBus = flowBus;
	}
	@Column(columnDefinition="Int COMMENT '摩托车车流量（辆/小时）'")
	public Integer getFlowMotorbike() {
		return flowMotorbike;
	}
	public void setFlowMotorbike(Integer flowMotorbike) {
		this.flowMotorbike = flowMotorbike;
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

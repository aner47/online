package com.online.entity.online.a10marineboard;

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
 * 市海事局-港口信息
 */
@EntityDescription(caption="港口信息")
@Entity
@Table(name="ol_port")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_port")
public class Port extends BaseEntity<Integer> {

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
	 * 中心经度
	 */
	@ColumnDescription(caption="中心经度")
	private Double longitude;
	
	/**
	 * 中心纬度
	 */
	@ColumnDescription(caption="中心纬度")
	private Double latitude;
	
	/**
	 * 泊位数
	 */
	@ColumnDescription(caption="泊位数")
	private Integer berths;
	
	/**
	 * 客运量（万人次）
	 */
	@ColumnDescription(caption="客运量（万人次）")
	private Double passengerCapacity;
	
	/**
	 * 货运量（万吨）
	 */
	@ColumnDescription(caption="货运量（万吨）")
	private Double commodityCapacity;
	
	/**
	 * 集装箱吞吐量（万标准箱）
	 */
	@ColumnDescription(caption="集装箱吞吐量（万标准箱）")
	private Double containerCapacity;
	/**
	 * 船舶抵离港总次数
	 */
	@ColumnDescription(caption="船舶抵离港总次数")
	private Double arriveLeaveTotal;
	
	
	
	@Column(columnDefinition="DOUBLE COMMENT '中心经度'")
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	@Column(columnDefinition="DOUBLE COMMENT '中心纬度'")
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	
	
	@Column(columnDefinition="Int COMMENT '泊位数'")
	public Integer getBerths() {
		return berths;
	}
	public void setBerths(Integer berths) {
		this.berths = berths;
	}
	@Column(columnDefinition="DOUBLE COMMENT '客运量（万人次）'")
	public Double getPassengerCapacity() {
		return passengerCapacity;
	}
	public void setPassengerCapacity(Double passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}
	@Column(columnDefinition="DOUBLE COMMENT '货运量（万吨）'")
	public Double getCommodityCapacity() {
		return commodityCapacity;
	}
	public void setCommodityCapacity(Double commodityCapacity) {
		this.commodityCapacity = commodityCapacity;
	}
	@Column(columnDefinition="DOUBLE COMMENT '集装箱吞吐量（万标准箱）'")
	public Double getContainerCapacity() {
		return containerCapacity;
	}
	public void setContainerCapacity(Double containerCapacity) {
		this.containerCapacity = containerCapacity;
	}
	@Column(columnDefinition="DOUBLE COMMENT '船舶抵离港总次数'")
	public Double getArriveLeaveTotal() {
		return arriveLeaveTotal;
	}
	public void setArriveLeaveTotal(Double arriveLeaveTotal) {
		this.arriveLeaveTotal = arriveLeaveTotal;
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

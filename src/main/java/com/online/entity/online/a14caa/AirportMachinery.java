package com.online.entity.online.a14caa;

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
 * 黄花机场-机场车辆信息
 */
@EntityDescription(caption="机场车辆信息")
@Entity
@Table(name="ol_airport_machinery")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_airport_machinery")
public class AirportMachinery extends BaseEntity<Integer> {

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
	 * 机械类型
	 */
	@ColumnDescription(caption="机械类型")
	private String machineryType;
	
	/**
	 * 机械保有量（台/辆）
	 */
	@ColumnDescription(caption="机械保有量（台/辆）")
	private Integer machineryInventory;
	
	/**
	 * 单台机械平均功率（千瓦）
	 */
	@ColumnDescription(caption="单台机械平均功率（千瓦）")
	private Integer avgPower;
	
	/**
	 * 机械总功率（千瓦）
	 */
	@ColumnDescription(caption="机械总功率（千瓦）")
	private Integer totalPower;
	
	/**
	 * 燃料类型（柴油/汽油/LNG/电等）
	 */
	@ColumnDescription(caption="燃料类型")
	private String fuelType;
	/**
	 * 燃料消耗量（吨）
	 */
	@ColumnDescription(caption="燃料消耗量（吨）")
	private Integer fuelConsume;
	/**
	 * 燃料含硫率（％）
	 */
	@ColumnDescription(caption="燃料含硫率（％）")
	private Double fuelSulfurRate;
	
	
	
	
	
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '机械类型'")
	public String getMachineryType() {
		return machineryType;
	}
	public void setMachineryType(String machineryType) {
		this.machineryType = machineryType;
	}
	@Column(columnDefinition="Int COMMENT '机械保有量（台/辆）'")
	public Integer getMachineryInventory() {
		return machineryInventory;
	}
	public void setMachineryInventory(Integer machineryInventory) {
		this.machineryInventory = machineryInventory;
	}
	@Column(columnDefinition="Int COMMENT '总功率（千瓦）'")
	public Integer getTotalPower() {
		return totalPower;
	}
	public void setTotalPower(Integer totalPower) {
		this.totalPower = totalPower;
	}
	@Column(columnDefinition="Int COMMENT '燃料消耗量（吨）'")
	public Integer getFuelConsume() {
		return fuelConsume;
	}
	public void setFuelConsume(Integer fuelConsume) {
		this.fuelConsume = fuelConsume;
	}
	@Column(columnDefinition="DOUBLE COMMENT '燃料含硫率（％）'")
	public Double getFuelSulfurRate() {
		return fuelSulfurRate;
	}
	public void setFuelSulfurRate(Double fuelSulfurRate) {
		this.fuelSulfurRate = fuelSulfurRate;
	}
	
	
	
	@Column(columnDefinition="Int COMMENT '单台机械平均功率（千瓦）'")
	public Integer getAvgPower() {
		return avgPower;
	}
	public void setAvgPower(Integer avgPower) {
		this.avgPower = avgPower;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '燃料类型'")
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	
	@ManyToOne
//	@JoinColumn(updatable=false)
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
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

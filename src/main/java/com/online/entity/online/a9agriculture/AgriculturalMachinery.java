package com.online.entity.online.a9agriculture;

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
 * 市农业委-农业机械信息表
 */
@EntityDescription(caption="农业机械信息表")
@Entity
@Table(name="ol_agricultural_machinery")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_agricultural_machinery")
public class AgriculturalMachinery extends BaseEntity<Integer> {

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
	 * 总功率（千瓦）
	 */
	@ColumnDescription(caption="总功率（千瓦）")
	private Double totalPower;
	
	/**
	 * 燃料消耗量（吨）
	 */
	@ColumnDescription(caption="燃料消耗量（吨）")
	private Double fuelConsume;
	/**
	 * 燃料含硫率（％）
	 */
	@ColumnDescription(caption="燃料含硫率（％）")
	private Double fuelSulfurRate;
	/**
	 * 年工作时间（小时）
	 */
	@ColumnDescription(caption="年工作时间（小时）")
	private Integer annualWorkHours;

	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '行政区'")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	
	
	
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
	@Column(columnDefinition="Double COMMENT '总功率（千瓦）'")
	public Double getTotalPower() {
		return totalPower;
	}
	public void setTotalPower(Double totalPower) {
		this.totalPower = totalPower;
	}
	@Column(columnDefinition="Double COMMENT '燃料消耗量（吨）'")
	public Double getFuelConsume() {
		return fuelConsume;
	}
	public void setFuelConsume(Double fuelConsume) {
		this.fuelConsume = fuelConsume;
	}
	@Column(columnDefinition="DOUBLE COMMENT '燃料含硫率（％）'")
	public Double getFuelSulfurRate() {
		return fuelSulfurRate;
	}
	public void setFuelSulfurRate(Double fuelSulfurRate) {
		this.fuelSulfurRate = fuelSulfurRate;
	}
	@Column(columnDefinition="Int COMMENT '年工作时间（小时）'")
	public Integer getAnnualWorkHours() {
		return annualWorkHours;
	}
	public void setAnnualWorkHours(Integer annualWorkHours) {
		this.annualWorkHours = annualWorkHours;
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

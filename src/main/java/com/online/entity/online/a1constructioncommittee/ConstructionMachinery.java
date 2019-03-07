package com.online.entity.online.a1constructioncommittee;

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
 * 住建委-工程机械信息
 */
@EntityDescription(caption="工程机械信息")
@Entity
@Table(name="ol_construction_machinery")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_construction_machinery")
public class ConstructionMachinery extends BaseEntity<Integer> {

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
	 * 机械类型
	 */
	@ColumnDescription(caption="机械类型")
	private String machineryType;
	
	/**
	 * 机械保有量（台）
	 */
	@ColumnDescription(caption="机械保有量（台）")
	private Integer machineryInventory;
	
	
	/**
	 * 机械总功率（千瓦）
	 */
	@ColumnDescription(caption="机械总功率（千瓦）")
	private Double machineryPower;
	
	/**
	 * 单机燃料年消耗量（吨／台）
	 */
	@ColumnDescription(caption="单机燃料年消耗量（吨／台）")
	private Integer annualConsumption;
	/**
	 * 燃料含硫率（%）
	 */
	@ColumnDescription(caption="燃料含硫率（%）")
	private Double fuelSulfur;
	
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '机械类型'")
	public String getMachineryType() {
		return machineryType;
	}
	public void setMachineryType(String machineryType) {
		this.machineryType = machineryType;
	}
	@Column(columnDefinition="Int COMMENT '机械保有量（台）'")
	public Integer getMachineryInventory() {
		return machineryInventory;
	}
	public void setMachineryInventory(Integer machineryInventory) {
		this.machineryInventory = machineryInventory;
	}
	@Column(columnDefinition="DOUBLE COMMENT '机械总功率（千瓦）'")
	public Double getMachineryPower() {
		return machineryPower;
	}
	public void setMachineryPower(Double machineryPower) {
		this.machineryPower = machineryPower;
	}
	@Column(columnDefinition="INT COMMENT '单机燃料年消耗量（吨／台）'")
	public Integer getAnnualConsumption() {
		return annualConsumption;
	}
	public void setAnnualConsumption(Integer annualConsumption) {
		this.annualConsumption = annualConsumption;
	}
	@Column(columnDefinition="DOUBLE COMMENT '燃料含硫率（%）'")
	public Double getFuelSulfur() {
		return fuelSulfur;
	}
	public void setFuelSulfur(Double fuelSulfur) {
		this.fuelSulfur = fuelSulfur;
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

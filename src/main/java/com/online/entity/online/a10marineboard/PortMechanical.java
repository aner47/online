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
 * 市海事局-全市港口机械信息
 */
@EntityDescription(caption="全市港口机械信息")
@Entity
@Table(name="ol_port_mechanical")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_port_mechanical")
public class PortMechanical extends BaseEntity<Integer> {

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
	private String mechanicalType;
	
	/**
	 * 机械保有量（台）
	 */
	@ColumnDescription(caption="机械保有量（台）")
	private Integer mechanicalInventory;
	
	
	/**
	 * 总功率（千瓦）
	 */
	@ColumnDescription(caption="总功率（千瓦）")
	private Double totalPower;
	
	
	/**
	 * 燃料消耗量（吨）
	 */
	@ColumnDescription(caption="燃料消耗量（吨）")
	private Double fuelConsumption;
	/**
	 * 燃料含硫率（%）
	 */
	@ColumnDescription(caption="燃料含硫率（%）")
	private Double fuelSulfurRate;
	
	
	
	
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '机械类型'")
	public String getMechanicalType() {
		return mechanicalType;
	}
	public void setMechanicalType(String mechanicalType) {
		this.mechanicalType = mechanicalType;
	}
	@Column(columnDefinition="Int COMMENT '机械保有量（台）'")
	public Integer getMechanicalInventory() {
		return mechanicalInventory;
	}
	public void setMechanicalInventory(Integer mechanicalInventory) {
		this.mechanicalInventory = mechanicalInventory;
	}
	@Column(columnDefinition="DOUBLE COMMENT '总功率（千瓦）'")
	public Double getTotalPower() {
		return totalPower;
	}
	public void setTotalPower(Double totalPower) {
		this.totalPower = totalPower;
	}
	@Column(columnDefinition="DOUBLE COMMENT '燃料消耗量（吨）'")
	public Double getFuelConsumption() {
		return fuelConsumption;
	}
	public void setFuelConsumption(Double fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}
	@Column(columnDefinition="DOUBLE COMMENT '燃料含硫率（%）'")
	public Double getFuelSulfurRate() {
		return fuelSulfurRate;
	}
	public void setFuelSulfurRate(Double fuelSulfurRate) {
		this.fuelSulfurRate = fuelSulfurRate;
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

package com.online.entity.online;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.entity.BaseEntity;
import com.online.exportpdf.DataBean;
import com.online.util.SpringUtils;

/**
 * 燃料信息表
 * 
 * @author DEV2
 *
 */


@Entity
@Table(name="ol_fule")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_fule")
public  class Fuel extends BaseEntity<Integer> implements DataBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9222660567026914264L;

	
	/**
	 * 燃料年份
	 */
	@ColumnDescription(caption="燃料年份")
	private String fuelYear;
	/**
	 * 燃料类型
	 */
	@ColumnDescription(caption="燃料类型")
	private String fuelType;
	
	/**
	 * 燃料消耗量
	 */
	@ColumnDescription(caption="燃料消耗量")
	private Double fuelConsumption;
	
	/**
	 * 燃料消耗量单位
	 */
	@ColumnDescription(caption="燃料单位")
	private String fuelUnit;
	
	/**
	 * 燃料含硫率
	 */
	@ColumnDescription(caption="燃料含硫率")
	private Double fuelSulfurContent;
	
	/**
	 * 燃料灰分
	 */
	@ColumnDescription(caption="燃料灰分")
	private Double fuelAsh;
	
	/**
	 * 燃料挥发分
	 */
	@ColumnDescription(caption="燃料挥发分")
	private Double fuelVolatiles;
	
	/**
	 * 项目
	 */
	private Project project;
	
	private Enterprise enterprise;
	
	private String description;
	
	
	/**
	 * 发电机组id
	 */
	private Integer powerPlantId;
	
	/**
	 * 锅炉id
	 */
	private Integer boilerId;
	
	/**
	 * 煤气发生炉id
	 */
	private Integer gasstoveId;
	
	/**
	 * 炉窑id
	 */
	private Integer kilnId;
	
	
	
	
	@Column(columnDefinition="INTEGER COMMENT '发电机组id'")
	public Integer getPowerPlantId() {
		return powerPlantId;
	}

	public void setPowerPlantId(Integer powerPlantId) {
		this.powerPlantId = powerPlantId;
	}
	
	@Column(columnDefinition="INTEGER COMMENT '锅炉id'")
	public Integer getBoilerId() {
		return boilerId;
	}

	public void setBoilerId(Integer boilerId) {
		this.boilerId = boilerId;
	}
	
	@Column(columnDefinition="INTEGER COMMENT '煤气发生炉id'")
	public Integer getGasstoveId() {
		return gasstoveId;
	}

	public void setGasstoveId(Integer gasstoveId) {
		this.gasstoveId = gasstoveId;
	}

	@Column(columnDefinition="INTEGER COMMENT '炉窑id'")
	public Integer getKilnId() {
		return kilnId;
	}

	public void setKilnId(Integer kilnId) {
		this.kilnId = kilnId;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '燃料年份'")
	public String getFuelYear() {
		return fuelYear;
	}

	public void setFuelYear(String fuelYear) {
		this.fuelYear = fuelYear;
	}
	

	@ManyToOne
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '燃料类型'")
	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	@Column(columnDefinition="DOUBLE COMMENT '燃料消耗量'")
	public Double getFuelConsumption() {
		return fuelConsumption;
	}

	public void setFuelConsumption(Double fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '燃料单位'")
	public String getFuelUnit() {
		return fuelUnit;
	}

	public void setFuelUnit(String fuelUnit) {
		this.fuelUnit = fuelUnit;
	}
	@Column(columnDefinition="DOUBLE COMMENT '燃料含硫率'")
	public Double getFuelSulfurContent() {
		return fuelSulfurContent;
	}

	public void setFuelSulfurContent(Double fuelSulfurContent) {
		this.fuelSulfurContent = fuelSulfurContent;
	}
	@Column(columnDefinition="DOUBLE COMMENT '燃料灰分'")
	public Double getFuelAsh() {
		return fuelAsh;
	}

	public void setFuelAsh(Double fuelAsh) {
		this.fuelAsh = fuelAsh;
	}
	@Column(columnDefinition="DOUBLE COMMENT '燃料挥发分'")
	public Double getFuelVolatiles() {
		return fuelVolatiles;
	}

	public void setFuelVolatiles(Double fuelVolatiles) {
		this.fuelVolatiles = fuelVolatiles;
	}

	
	

	@ManyToOne
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	

	@PrePersist
	public void initProjectEnterpise(){
		project = SpringUtils.getCurrentProject();
//		enterprise.setEnterprise(SpringUtils.getCurrentEnterprise());
		enterprise = SpringUtils.getCurrentEnterprise();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

	
}

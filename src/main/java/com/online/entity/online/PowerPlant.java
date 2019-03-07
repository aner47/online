package com.online.entity.online;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.util.SpringUtils;

/**
 * 自备电厂
 */
@EntityDescription(caption="自备电厂")
@Entity
@Table(name="ol_power_plant")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_power_plant")
public class PowerPlant extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3023548453277542334L;
	
	/**
	 * 机组编号
	 */
	@ColumnDescription(caption="机组编号")
	private String no;
	
	/**
	 * 装机容量（兆瓦）
	 */
	@ColumnDescription(caption="装机容量（兆瓦）")
	private Double installedCapacity;
	
	/**
	 * 投运时间
	 */
	@ColumnDescription(caption="投运时间")
	private Date putOnTime;
	
	/**
	 * 关停时间
	 */
	@ColumnDescription(caption="关停时间")
	private Date stopTime;
	
	/**
	 * 年发电量（万千瓦时）
	 */
	@ColumnDescription(caption="年发电量（万千瓦时）")
	private Double annualPowerGeneration;
	
	/**
	 * 年供热量（吉焦）
	 */
	@ColumnDescription(caption="年供热量（吉焦）")
	private Double annualHeatSupply;
	
	/**
	 * 锅炉类型
	 */
	@ColumnDescription(caption="锅炉类型")
	private String boilerType;
	
	/**
	 * 锅炉型号
	 */
	@ColumnDescription(caption="锅炉型号")
	private String boilerModel;
	
	/**
	 * 锅炉蒸吨(蒸吨/小时)	
	 */
	@ColumnDescription(caption="锅炉蒸吨(蒸吨/小时)")
	private Double steamTon;
	
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
	 * 燃料单位
	 */
	@ColumnDescription(caption="燃料单位")
	private String fuelUnit;
	
	

	/**
	 * 燃料含硫率
	 */
	@ColumnDescription(caption="燃料含硫率")
	private Double fuelSulfurContent;
	
	/**
	 * 燃料灰分-
	 */
	@ColumnDescription(caption="燃料灰分")
	private Double fuelAshContent;
	
	
	/**
	 * 燃料挥发分
	 */
	@ColumnDescription(caption="燃料挥发分")
	private Double fuelVolatiles;
	
	/**
	 * 企业信息
	 */
	private Enterprise enterprise;
	/**
	 * 排放口
	 */
	private ExhaustionHole exhaustionHole;
	
	/**
	 * 治理信息1
	 */
	private GovernanceMeasures governanceMeasures1;
	
	/**
	 * 治理信息2
	 */
	private GovernanceMeasures governanceMeasures2;
	
	/**
	 * 治理信息3
	 */
	private GovernanceMeasures governanceMeasures3;
	
	
	
	
	
	
	
	
	
	private Project project;
	
	/**
	 * 是否热电联产
	 */
	@ColumnDescription(caption="是否热电联产")
	private String isRedian;
	
	
	
	/**
	 * 使用状态
	 */
	@ColumnDescription(caption="使用状态")
	private String useStatus;
	
	/**
	 * 整改时间
	 */
	@ColumnDescription(caption="整改时间")
	private Date abarbeitungTime;
	
	
	/**
	 * 燃料年份
	 */
	@ColumnDescription(caption="燃料年份")
	private String fuelYear;
	

	@Column(columnDefinition="VARCHAR(255) COMMENT '燃料年份'")
	public String getFuelYear() {
		return fuelYear;
	}


	public void setFuelYear(String fuelYear) {
		this.fuelYear = fuelYear;
	}


	@Column(columnDefinition="VARCHAR(255) COMMENT '是否热电联产'")
	public String getIsRedian() {
		return isRedian;
	}


	public void setIsRedian(String isRedian) {
		this.isRedian = isRedian;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '机组编号'")
	public String getNo() {
		return no;
	}


	public void setNo(String no) {
		this.no = no;
	}



	@Column(columnDefinition="DOUBLE COMMENT '装机容量（兆瓦）'")
    public Double getInstalledCapacity() {
		return installedCapacity;
	}


	public void setInstalledCapacity(Double installedCapacity) {
		this.installedCapacity = installedCapacity;
	}

	@Column(columnDefinition="DATETIME COMMENT '投运时间'")
	public Date getPutOnTime() {
		return putOnTime;
	}


	public void setPutOnTime(Date putOnTime) {
		this.putOnTime = putOnTime;
	}

	@Column(columnDefinition="DATETIME COMMENT '关停时间'")
	public Date getStopTime() {
		return stopTime;
	}


	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	@Column(columnDefinition="DOUBLE COMMENT '年发电量（万千瓦时）'")
	public Double getAnnualPowerGeneration() {
		return annualPowerGeneration;
	}


	public void setAnnualPowerGeneration(Double annualPowerGeneration) {
		this.annualPowerGeneration = annualPowerGeneration;
	}

	@Column(columnDefinition="DOUBLE COMMENT '年供热量（吉焦）'")
	public Double getAnnualHeatSupply() {
		return annualHeatSupply;
	}


	public void setAnnualHeatSupply(Double annualHeatSupply) {
		this.annualHeatSupply = annualHeatSupply;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '锅炉类型'")
	public String getBoilerType() {
		return boilerType;
	}


	public void setBoilerType(String boilerType) {
		this.boilerType = boilerType;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '锅炉型号'")
	public String getBoilerModel() {
		return boilerModel;
	}


	public void setBoilerModel(String boilerModel) {
		this.boilerModel = boilerModel;
	}

	@Column(columnDefinition="DOUBLE COMMENT '锅炉蒸吨(蒸吨/小时)'")
	public Double getSteamTon() {
		return steamTon;
	}


	public void setSteamTon(Double steamTon) {
		this.steamTon = steamTon;
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
	public Double getFuelAshContent() {
		return fuelAshContent;
	}


	public void setFuelAshContent(Double fuelAshContent) {
		this.fuelAshContent = fuelAshContent;
	}

	@Column(columnDefinition="DOUBLE COMMENT '燃料挥发分'")
	public Double getFuelVolatiles() {
		return fuelVolatiles;
	}


	public void setFuelVolatiles(Double fuelVolatiles) {
		this.fuelVolatiles = fuelVolatiles;
	}
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '使用状态'")
	public String getUseStatus() {
		return useStatus;
	}


	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	@Column(columnDefinition="DATETIME COMMENT '整改时间'")
	public Date getAbarbeitungTime() {
		return abarbeitungTime;
	}


	public void setAbarbeitungTime(Date abarbeitungTime) {
		this.abarbeitungTime = abarbeitungTime;
	}


	@ManyToOne
	@JoinColumn(updatable=false)
	public Enterprise getEnterprise() {
		return enterprise;
	}


	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	@ManyToOne
	public ExhaustionHole getExhaustionHole() {
		return exhaustionHole;
	}


	public void setExhaustionHole(ExhaustionHole exhaustionHole) {
		this.exhaustionHole = exhaustionHole;
	}

	@ManyToOne
	public GovernanceMeasures getGovernanceMeasures1() {
		return governanceMeasures1;
	}


	public void setGovernanceMeasures1(GovernanceMeasures governanceMeasures1) {
		this.governanceMeasures1 = governanceMeasures1;
	}

	@ManyToOne
	public GovernanceMeasures getGovernanceMeasures2() {
		return governanceMeasures2;
	}


	public void setGovernanceMeasures2(GovernanceMeasures governanceMeasures2) {
		this.governanceMeasures2 = governanceMeasures2;
	}

	@ManyToOne
	public GovernanceMeasures getGovernanceMeasures3() {
		return governanceMeasures3;
	}


	public void setGovernanceMeasures3(GovernanceMeasures governanceMeasures3) {
		this.governanceMeasures3 = governanceMeasures3;
	}


	@ManyToOne
	@JoinColumn(updatable=false)
	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}
	
	@PrePersist
	protected void init() {
		project = SpringUtils.getCurrentProject();
		enterprise = SpringUtils.getCurrentEnterprise();
	}
	
	

}

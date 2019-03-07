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
 * 市海事局-客货运输船舶活动信息
 */
@EntityDescription(caption="客货运输船舶活动信息")
@Entity
@Table(name="ol_transport_ship")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_transport_ship")
public class TransportShip extends BaseEntity<Integer> {

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
	 * 船舶类型
	 */
	@ColumnDescription(caption="船舶类型")
	private String shipType;
	
	/**
	 * 数量（艘）
	 */
	@ColumnDescription(caption="数量（艘）")
	private Integer number;
	
	/**
	 * 抵离港次数（次）
	 */
	@ColumnDescription(caption="抵离港次数（次）")
	private Integer arriveLeaveTotal;
	
	/**
	 * 平均载重吨（吨）
	 */
	@ColumnDescription(caption="平均载重吨（吨）")
	private Double avgLoad;
	
	/**
	 * 主机平均功率（千瓦）
	 */
	@ColumnDescription(caption="主机平均功率（千瓦）")
	private Double mainAvgPower;
	
	/**
	 * 辅机平均功率（千瓦）
	 */
	@ColumnDescription(caption="辅机平均功率（千瓦）")
	private Double auxiliaryAvgPower;
	
	/**
	 * 锅炉功率（千瓦）
	 */
	@ColumnDescription(caption="锅炉功率（千瓦）")
	private Double boilerPower;
	
	/**
	 * 燃油类型
	 */
	@ColumnDescription(caption="燃油类型")
	private String fuelType;
	/**
	 * 消耗量（吨/年）
	 */
	@ColumnDescription(caption="消耗量（吨/年）")
	private Double consumption;
	/**
	 * 含硫率（％）
	 */
	@ColumnDescription(caption="含硫率（％）")
	private Double sulfurRate;
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '船舶类型'")
	public String getShipType() {
		return shipType;
	}
	public void setShipType(String shipType) {
		this.shipType = shipType;
	}
	@Column(columnDefinition="Int COMMENT '数量（艘）'")
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	@Column(columnDefinition="Int COMMENT '抵离港次数（次）'")
	public Integer getArriveLeaveTotal() {
		return arriveLeaveTotal;
	}
	public void setArriveLeaveTotal(Integer arriveLeaveTotal) {
		this.arriveLeaveTotal = arriveLeaveTotal;
	}
	@Column(columnDefinition="DOUBLE COMMENT '平均载重吨（吨）'")
	public Double getAvgLoad() {
		return avgLoad;
	}
	public void setAvgLoad(Double avgLoad) {
		this.avgLoad = avgLoad;
	}
	@Column(columnDefinition="DOUBLE COMMENT '主机平均功率（千瓦）'")
	public Double getMainAvgPower() {
		return mainAvgPower;
	}
	public void setMainAvgPower(Double mainAvgPower) {
		this.mainAvgPower = mainAvgPower;
	}
	@Column(columnDefinition="DOUBLE COMMENT '辅机平均功率（千瓦）'")
	public Double getAuxiliaryAvgPower() {
		return auxiliaryAvgPower;
	}
	public void setAuxiliaryAvgPower(Double auxiliaryAvgPower) {
		this.auxiliaryAvgPower = auxiliaryAvgPower;
	}
	@Column(columnDefinition="DOUBLE COMMENT '锅炉功率（千瓦）'")
	public Double getBoilerPower() {
		return boilerPower;
	}
	public void setBoilerPower(Double boilerPower) {
		this.boilerPower = boilerPower;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '燃油类型'")
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	@Column(columnDefinition="DOUBLE COMMENT '消耗量（吨/年）'")
	public Double getConsumption() {
		return consumption;
	}
	public void setConsumption(Double consumption) {
		this.consumption = consumption;
	}
	@Column(columnDefinition="DOUBLE COMMENT '含硫率（％）'")
	public Double getSulfurRate() {
		return sulfurRate;
	}
	public void setSulfurRate(Double sulfurRate) {
		this.sulfurRate = sulfurRate;
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

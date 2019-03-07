package com.online.entity.online.catering;

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
import com.online.entity.online.Enterprise;
import com.online.entity.online.Project;
import com.online.util.SpringUtils;

/**
 * 餐饮燃料
 */
@EntityDescription(caption="餐饮燃料表")
@Entity
@Table(name="ol_catering_fuel")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_catering_fuel")
public class CateringFuel extends BaseEntity<Integer> {

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
	 * 燃料类型
	 */
	@ColumnDescription(caption="燃料类型")
	private String fuelType;
	
	/**
	 * 年使用量
	 */
	@ColumnDescription(caption="年使用量")
	private Double annualUse;
	
	
	/**
	 * 单位
	 */
	@ColumnDescription(caption="单位")
	private String unit;
	
	
	/**
	 * 油烟净化器型号
	 */
	@ColumnDescription(caption="油烟净化器型号")
	private String lampblackModel;
	
	/**
	 * 油烟净化器安装日期*
	 */
	@ColumnDescription(caption="油烟净化器安装日期")
	private Date lampblackDate;
	
	/**
	 * 油烟净化器维护周期（天）
	 */
	@ColumnDescription(caption="油烟净化器维护周期")
	private Integer maintainDays;
	
	/**
	 * 烟气排放速率（立方米/小时）
	 */
	@ColumnDescription(caption="烟气排放速率（立方米/小时）")
	private Double smokeReleaseRatio;
	
	/**
	 * 净化器投运率（%）
	 */
	@ColumnDescription(caption="净化器投运率（%）")
	private Double commissioningRatio;
	
	/**
	 * 净化效率（%）
	 */
	@ColumnDescription(caption="净化效率（%）")
	private Double cleansingRatio;
	
	
	/**
	 * 总灶头数（个）
	 */
	@ColumnDescription(caption="总灶头数（个）")
	private Integer kitchenNum;
	
	
	
	
	@Column(columnDefinition="INT(11) COMMENT '总灶头数（个）'")
	public Integer getKitchenNum() {
		return kitchenNum;
	}

	public void setKitchenNum(Integer kitchenNum) {
		this.kitchenNum = kitchenNum;
	}

	@Column(columnDefinition="DOUBLE COMMENT '烟气排放速率（立方米/小时）'")
	public Double getSmokeReleaseRatio() {
		return smokeReleaseRatio;
	}

	public void setSmokeReleaseRatio(Double smokeReleaseRatio) {
		this.smokeReleaseRatio = smokeReleaseRatio;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '燃料类型'")
	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	@Column(columnDefinition="DOUBLE COMMENT '年使用量'")
	public Double getAnnualUse() {
		return annualUse;
	}

	public void setAnnualUse(Double annualUse) {
		this.annualUse = annualUse;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '单位'")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '油烟净化器型号'")
	public String getLampblackModel() {
		return lampblackModel;
	}

	public void setLampblackModel(String lampblackModel) {
		this.lampblackModel = lampblackModel;
	}
	@Column(columnDefinition="DATETIME COMMENT '油烟净化器安装日期'")
	public Date getLampblackDate() {
		return lampblackDate;
	}

	public void setLampblackDate(Date lampblackDate) {
		this.lampblackDate = lampblackDate;
	}
	@Column(columnDefinition="INT(11) COMMENT '油烟净化器维护周期'")
	public Integer getMaintainDays() {
		return maintainDays;
	}

	public void setMaintainDays(Integer maintainDays) {
		this.maintainDays = maintainDays;
	}
	@Column(columnDefinition="DOUBLE COMMENT '净化器投运率（%）'")
	public Double getCommissioningRatio() {
		return commissioningRatio;
	}

	public void setCommissioningRatio(Double commissioningRatio) {
		this.commissioningRatio = commissioningRatio;
	}
	@Column(columnDefinition="DOUBLE COMMENT '净化效率（%）'")
	public Double getCleansingRatio() {
		return cleansingRatio;
	}

	public void setCleansingRatio(Double cleansingRatio) {
		this.cleansingRatio = cleansingRatio;
	}

	@ManyToOne
	@JoinColumn(updatable=false)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne
	@JoinColumn(updatable=false)
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

	@PrePersist
	public void initProjectEnterpise(){
		project = SpringUtils.getCurrentProject();
		enterprise = SpringUtils.getCurrentEnterprise();
	}

	
}

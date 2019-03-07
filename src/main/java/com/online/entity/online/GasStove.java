package com.online.entity.online;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.exportpdf.DataBean;

/** 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2018年3月26日 下午2:36:09 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return  
	*/
@EntityDescription(caption="煤气炉")
@Entity
@DiscriminatorValue(value = "gasStove")
public class GasStove extends BaseBoilerInformation {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 炉膛直径(m)
	 */
	@ColumnDescription(caption="炉膛直径(m)")
	private Double diameter;
	
	
	/**
	 * 煤气出口温度(℃)
	 */
	@ColumnDescription(caption="煤气出口温度(℃)")
	private Double outTemperature;
	
	/**
	 * 煤气流量(标立方米/小时)
	 */
	@ColumnDescription(caption="煤气流量(标立方米/小时)")
	private Double rateOfFlow;
	
	/**
	 * 煤气年产量(万立方米)
	 */
	@ColumnDescription(caption="煤气年产量(万立方米)")
	private Double annualOutput;
	
	/**
	 * 配套装置(多选)
	 */
	@ColumnDescription(caption="配套装置(多选)")
	private String appliance;
	
	/**
	 * 装置去除效率（%）
	 */
	@ColumnDescription(caption="装置去除效率（%）")
	private Double applianceRemovalRate;
	
	
	
	

	@Column(columnDefinition="DOUBLE COMMENT '炉膛直径(m)'")
	public Double getDiameter() {
		return diameter;
	}

	public void setDiameter(Double diameter) {
		this.diameter = diameter;
	}
	@Column(columnDefinition="DOUBLE COMMENT '煤气出口温度(℃)'")
	public Double getOutTemperature() {
		return outTemperature;
	}

	public void setOutTemperature(Double outTemperature) {
		this.outTemperature = outTemperature;
	}
	@Column(columnDefinition="DOUBLE COMMENT '煤气流量(标立方米/小时)'")
	public Double getRateOfFlow() {
		return rateOfFlow;
	}

	public void setRateOfFlow(Double rateOfFlow) {
		this.rateOfFlow = rateOfFlow;
	}
	@Column(columnDefinition="DOUBLE COMMENT '煤气年产量(万立方米)'")
	public Double getAnnualOutput() {
		return annualOutput;
	}

	public void setAnnualOutput(Double annualOutput) {
		this.annualOutput = annualOutput;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '配套装置(多选)'")
	public String getAppliance() {
		return appliance;
	}

	public void setAppliance(String appliance) {
		this.appliance = appliance;
	}
	@Column(columnDefinition="DOUBLE COMMENT '装置去除效率（%）'")
	public Double getApplianceRemovalRate() {
		return applianceRemovalRate;
	}

	public void setApplianceRemovalRate(Double applianceRemovalRate) {
		this.applianceRemovalRate = applianceRemovalRate;
	}
	
	
	
	

}

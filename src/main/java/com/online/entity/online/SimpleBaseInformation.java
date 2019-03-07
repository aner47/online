package com.online.entity.online;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;

/** 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2017年7月17日 上午11:10:15 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return  
	*/
@EntityDescription(caption="简版企业基础信息")
@Entity
@DiscriminatorValue(value = "simple")
public class SimpleBaseInformation extends Information{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	/**
	 * 连续/间歇生产
	 */
	@ColumnDescription(caption="连续/间歇生产")
	private String continuousIntermittence;
	
	
	
	
	
	
	
	/**
	 * 年生产天数
	 */
	@ColumnDescription(caption="年生产天数")
	private Double workDay2017;
	
	
	/**
	 * 企业状态
	 */
	@ColumnDescription(caption="企业状态")
	private String enterpriseState2017;
	
	/**
	 * 关停时间
	 */
	@ColumnDescription(caption="关停时间")
	@Temporal(TemporalType.DATE)
	private Date stopTime2017;
	
	/**
	 * 全年生产总值
	 */
	@ColumnDescription(caption="全年生产总值")
	private Double grossProduct2017;
	
	/**
	 * 	全年用电量（千瓦时）	
	 */
	@ColumnDescription(caption="全年用电量")
	private Double energyUsed2017;
	
	/**
	 * 生产月份
	 */
	@ColumnDescription(caption="生产月份")
	private String productionMonth2017;
	
	
	/**
	 * 连续/间歇生产
	 */
	@ColumnDescription(caption="连续/间歇生产")
	private String continuousIntermittence2017;
	
	/**
	 * 环评手续是否完善
	 */
	@ColumnDescription(caption="环评手续是否完善")
	private String procedureComplete;

	
	/**
	 * 厂门是否安装监控
	 */
	@ColumnDescription(caption="厂门是否安装监控")
	private String isMonitoring;
	
	/**
	 * 企业类型
	 */
	@ColumnDescription(caption="企业类型")
	private String enterpriseType;
	
	
	
	
	
	/**
	 * 烟囱低于20米数量
	 */
	@ColumnDescription(caption="烟囱低于20米数量")
	private Double chimney20Num;
	
	/**
	 * 烟囱高20米至45米数量
	 */
	@ColumnDescription(caption="烟囱高20米至45米数量")
	private Double chimney2045Num;
	
	/**
	 * 烟囱高超过45米数量
	 */
	@ColumnDescription(caption="烟囱高超过45米数量")
	private Double chimney45Num;
	
	/**
	 * 脱硫设施数量
	 */
	@ColumnDescription(caption="脱硫设施数量")
	private Double vitriolFacilityNum;
	
	/**
	 * 脱硝设施数量
	 */
	@ColumnDescription(caption="脱硝设施数量")
	private Double nitricAcidFacilityNum;
	/**
	 * 除尘设施数量
	 */
	@ColumnDescription(caption="除尘设施数量")
	private Double dedustingFacilityNum;
	/**
	 * VOC设施数量
	 */
	@ColumnDescription(caption="VOC设施数量")
	private Double vocFacilityNum;
	
	
	
	/**
	 * 烟囱数量
	 */
	@ColumnDescription(caption="烟囱数量")
	private Double total;
	
	
	
	@Column(columnDefinition="DOUBLE COMMENT '烟囱数量'")
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '企业类型'")
	public String getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '厂门是否安装监控'")
	public String getIsMonitoring() {
		return isMonitoring;
	}

	public void setIsMonitoring(String isMonitoring) {
		this.isMonitoring = isMonitoring;
	}
	@Column(columnDefinition="DOUBLE COMMENT '年生产天数'")
	public Double getWorkDay2017() {
		return workDay2017;
	}

	public void setWorkDay2017(Double workDay2017) {
		this.workDay2017 = workDay2017;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '企业状态'")
	public String getEnterpriseState2017() {
		return enterpriseState2017;
	}

	public void setEnterpriseState2017(String enterpriseState2017) {
		this.enterpriseState2017 = enterpriseState2017;
	}
	@Column(columnDefinition="DATETIME COMMENT '关停时间'")
	public Date getStopTime2017() {
		return stopTime2017;
	}

	public void setStopTime2017(Date stopTime2017) {
		this.stopTime2017 = stopTime2017;
	}
	@Column(columnDefinition="DOUBLE COMMENT '全年生产总值'")
	public Double getGrossProduct2017() {
		return grossProduct2017;
	}

	public void setGrossProduct2017(Double grossProduct2017) {
		this.grossProduct2017 = grossProduct2017;
	}
	@Column(columnDefinition="DOUBLE COMMENT '全年用电量'")
	public Double getEnergyUsed2017() {
		return energyUsed2017;
	}

	public void setEnergyUsed2017(Double energyUsed2017) {
		this.energyUsed2017 = energyUsed2017;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '生产月份'")
	public String getProductionMonth2017() {
		return productionMonth2017;
	}

	public void setProductionMonth2017(String productionMonth2017) {
		this.productionMonth2017 = productionMonth2017;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '连续/间歇生产'")
	public String getContinuousIntermittence2017() {
		return continuousIntermittence2017;
	}

	public void setContinuousIntermittence2017(String continuousIntermittence2017) {
		this.continuousIntermittence2017 = continuousIntermittence2017;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '环评手续是否完善'")
	public String getProcedureComplete() {
		return procedureComplete;
	}

	public void setProcedureComplete(String procedureComplete) {
		this.procedureComplete = procedureComplete;
	}
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '连续/间歇生产'")
	public String getContinuousIntermittence() {
		return continuousIntermittence;
	}

	public void setContinuousIntermittence(String continuousIntermittence) {
		this.continuousIntermittence = continuousIntermittence;
	}
	
	@Column(columnDefinition="DOUBLE COMMENT '烟囱低于20米数量'")
	public Double getChimney20Num() {
		return chimney20Num;
	}

	public void setChimney20Num(Double chimney20Num) {
		this.chimney20Num = chimney20Num;
	}
	@Column(columnDefinition="DOUBLE COMMENT '烟囱高20米至45米数量'")
	public Double getChimney2045Num() {
		return chimney2045Num;
	}

	public void setChimney2045Num(Double chimney2045Num) {
		this.chimney2045Num = chimney2045Num;
	}
	@Column(columnDefinition="DOUBLE COMMENT '烟囱高超过45米数量'")
	public Double getChimney45Num() {
		return chimney45Num;
	}

	public void setChimney45Num(Double chimney45Num) {
		this.chimney45Num = chimney45Num;
	}
	@Column(columnDefinition="DOUBLE COMMENT '脱硫设施数量'")
	public Double getVitriolFacilityNum() {
		return vitriolFacilityNum;
	}

	public void setVitriolFacilityNum(Double vitriolFacilityNum) {
		this.vitriolFacilityNum = vitriolFacilityNum;
	}
	@Column(columnDefinition="DOUBLE COMMENT '脱硝设施数量'")
	public Double getNitricAcidFacilityNum() {
		return nitricAcidFacilityNum;
	}

	public void setNitricAcidFacilityNum(Double nitricAcidFacilityNum) {
		this.nitricAcidFacilityNum = nitricAcidFacilityNum;
	}
	@Column(columnDefinition="DOUBLE COMMENT '除尘设施数量'")
	public Double getDedustingFacilityNum() {
		return dedustingFacilityNum;
	}

	public void setDedustingFacilityNum(Double dedustingFacilityNum) {
		this.dedustingFacilityNum = dedustingFacilityNum;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'VOC设施数量'")
	public Double getVocFacilityNum() {
		return vocFacilityNum;
	}

	public void setVocFacilityNum(Double vocFacilityNum) {
		this.vocFacilityNum = vocFacilityNum;
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

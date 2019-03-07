package com.online.entity.online;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.entity.online.Enterprise.EnterpriseType;

/**
 * 污染治理措施信息表
 * 
 * @author DEV2
 *
 */
@EntityDescription(caption="污染治理措施")
@Entity
@Table(name="ol_governance_measures")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_governance_measures")
public class GovernanceMeasures extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7711964065096998194L;

	@ColumnDescription(caption="企业")
	private Enterprise enterprise;
	/**
	 * 治理工艺
	 */
	@ColumnDescription(caption="工艺名称")
	private String name;

	/**
	 * 年工作时间（小时/年）
	 */
	@ColumnDescription(caption="年工作时间")
	private Double workHours;

	/**
	 * 废气收集效率（%）
	 */
	@ColumnDescription(caption="废气收集效率（%）")
	private Double collectEfficiency;
	
	/**
	 * so2去除效率
	 */
	@ColumnDescription(caption="SO2")
	private Double so2;
	
	/**
	 * nox去除效率
	 */
	@ColumnDescription(caption="NOX")
	private Double nox;
	
	/**
	 * co去除效率
	 */
	@ColumnDescription(caption="CO")
	private Double co;
	
	/**
	 * pm10去除效率
	 */
	@ColumnDescription(caption="PM10")
	private Double pm10;
	
	
	/**
	 * pm25去除效率
	 */
	@ColumnDescription(caption="PM2.5")
	private Double pm25;
	
	
	/**
	 * bc去除效率
	 */
	@ColumnDescription(caption="BC")
	private Double bc;
	
	
	/**
	 * oc去除效率
	 */
	@ColumnDescription(caption="OC")
	private Double oc;
	
	
	/**
	 * voc去除效率
	 */
	@ColumnDescription(caption="VOCs")
	private Double voc;
	
	
	/**
	 * nh3去除效率
	 */
	@ColumnDescription(caption="NH3")
	private Double nh3;
	
	
	/**
	 * tsp去除效率
	 */
	@ColumnDescription(caption="TSP")
	private Double tsp;
	
	/**
	 * 项目
	 */
	@ColumnDescription(caption="项目")
	private Project project;
	
	/**
	 * 治理编号
	 */
	@ColumnDescription(caption="治理编号")
	private String gmno;
	
	/**
	 * 备注
	 */
	@ColumnDescription(caption="备注")
	private String description;
	
	/**
	 * 使用类型
	 */
	@ColumnDescription(caption="使用类型")
	private EnterpriseType userType;
	
	
	/**
	 * 主要脱硫剂类型
	 */
	@ColumnDescription(caption="主要脱硫剂类型")
	private String desulfurationType;
	
	/**
	 * 脱硫剂用量（吨）
	 */
	@ColumnDescription(caption="脱硫剂用量（吨）")
	private Double desulfurationDosage;
	
	/**
	 * 脱硫剂更换频率（次/年）
	 */
	@ColumnDescription(caption="脱硫剂更换频率（次/年）")
	private Double desulfurationfrequency;
	
	
	
	/**
	 * 主要脱硝剂类型
	 */
	@ColumnDescription(caption="主要脱硝剂类型")
	private String denitrationType;
	
	/**
	 * 脱硝剂用量（吨）
	 */
	@ColumnDescription(caption="脱硝剂用量（吨）")
	private Double denitrationDosage;
	
	
	/**
	 * 脱硝剂更换频率（次/年）
	 */
	@ColumnDescription(caption="脱硝剂更换频率（次/年）")
	private Double denitrationfrequency;
	
	
	/**
	 * 吸附剂名称
	 */
	@ColumnDescription(caption="吸附剂名称")
	private String adsorbentName;
	
	/**
	 * 吸附剂用量（吨）
	 */
	@ColumnDescription(caption="吸附剂用量（吨）")
	private Double adsorbentDosage;
	
	
	/**
	 * 吸附剂更换频率（次/年）
	 */
	@ColumnDescription(caption="吸附剂更换频率（次/年）")
	private Double adsorbentfrequency;
	
	
	/**
	 * 投运时间
	 */
	@ColumnDescription(caption="投运时间")
	private Date putDate;
	
	/**
	 * 停运时间(年/月）
	 */
	@ColumnDescription(caption="停运时间(年/月）")
	private Date stopDate;
	
	
	
	/**
	 * 措施
	 */
	@ColumnDescription(caption="措施")
	private String measure;
	
	
	/**
	 * 投运率（%）
	 */
	@ColumnDescription(caption="投运率（%）")
	private Double putintoRate;
	
	
	/**
	 * 脱硫率（%）
	 */
	@ColumnDescription(caption="脱硫率（%）")
	private Double desulfurationRate;
	
	/**
	 * 脱硝率（%）
	 */
	@ColumnDescription(caption="脱硝率（%）")
	private Double denitrationRate;
	
	/**
	 * 除尘率（%）
	 */
	@ColumnDescription(caption="除尘率（%）")
	private Double dedustingRate;
	/**
	 * 设备风量(立方米/小时)
	 */
	@ColumnDescription(caption="设备风量(立方米/小时)")
	private Double equipmentWindMeasure;
	
	/**
	 * 有机废气排放浓度(毫克/立方米)
	 */
	@ColumnDescription(caption="有机废气排放浓度(毫克/立方米)")
	private Double fluegasLetoutConcentration;
	
	
	
	
	
	
	/**
	 * 治理设施初次投入成本（万元）
	 */
	@ColumnDescription(caption="治理设施初次投入成本（万元）")
	private Double firstPut;
	
	/**
	 * 治理实施运行成本（万元/月）
	 */
	@ColumnDescription(caption="治理实施运行成本（万元/月）")
	private Double operatingCost;
	
	
	/**
	 * 布袋清洗频率（次/年）
	 */
	@ColumnDescription(caption="布袋清洗频率（次/年）")
	private Double dedustingFrequency;
	
	
	/**
	 * 是否安装GGH(烟气加热)
	 */
	@ColumnDescription(caption="是否安装GGH(烟气加热)")
	private String isGGH;
	
	/**
	 * 污染物类型
	 */
	@ColumnDescription(caption="污染物类型")
	private String contaminantType;
	
	/**
	 * 污染物去除效率
	 */
	@ColumnDescription(caption="污染物去除效率")
	private String contaminantRate;
	
	
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '措施类型'")
	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}
	@Column(columnDefinition="DOUBLE COMMENT '投运率（%）'")
	public Double getPutintoRate() {
		return putintoRate;
	}

	public void setPutintoRate(Double putintoRate) {
		this.putintoRate = putintoRate;
	}
	@Column(columnDefinition="DOUBLE COMMENT '脱硫率（%）'")
	public Double getDesulfurationRate() {
		return desulfurationRate;
	}

	public void setDesulfurationRate(Double desulfurationRate) {
		this.desulfurationRate = desulfurationRate;
	}
	@Column(columnDefinition="DOUBLE COMMENT '脱硝率（%）'")
	public Double getDenitrationRate() {
		return denitrationRate;
	}

	public void setDenitrationRate(Double denitrationRate) {
		this.denitrationRate = denitrationRate;
	}
	@Column(columnDefinition="DOUBLE COMMENT '除尘率（%）'")
	public Double getDedustingRate() {
		return dedustingRate;
	}

	public void setDedustingRate(Double dedustingRate) {
		this.dedustingRate = dedustingRate;
	}
	@Column(columnDefinition="DOUBLE COMMENT '设备风量(立方米/小时)'")
	public Double getEquipmentWindMeasure() {
		return equipmentWindMeasure;
	}

	public void setEquipmentWindMeasure(Double equipmentWindMeasure) {
		this.equipmentWindMeasure = equipmentWindMeasure;
	}
	@Column(columnDefinition="DOUBLE COMMENT '有机废气排放浓度(毫克/立方米)'")
	public Double getFluegasLetoutConcentration() {
		return fluegasLetoutConcentration;
	}

	public void setFluegasLetoutConcentration(Double fluegasLetoutConcentration) {
		this.fluegasLetoutConcentration = fluegasLetoutConcentration;
	}
	@Column(columnDefinition="DATETIME COMMENT '投运时间'")
	public Date getPutDate() {
		return putDate;
	}

	public void setPutDate(Date putDate) {
		this.putDate = putDate;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '主要脱硫剂类型'")
	public String getDesulfurationType() {
		return desulfurationType;
	}

	public void setDesulfurationType(String desulfurationType) {
		this.desulfurationType = desulfurationType;
	}
	@Column(columnDefinition="DOUBLE COMMENT '脱硫剂用量（吨）'")
	public Double getDesulfurationDosage() {
		return desulfurationDosage;
	}

	public void setDesulfurationDosage(Double desulfurationDosage) {
		this.desulfurationDosage = desulfurationDosage;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '主要脱硝剂类型'")
	public String getDenitrationType() {
		return denitrationType;
	}

	public void setDenitrationType(String denitrationType) {
		this.denitrationType = denitrationType;
	}
	@Column(columnDefinition="DOUBLE COMMENT '脱硝剂用量（吨）'")
	public Double getDenitrationDosage() {
		return denitrationDosage;
	}

	public void setDenitrationDosage(Double denitrationDosage) {
		this.denitrationDosage = denitrationDosage;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '备注'")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '工艺名称'")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Column(columnDefinition="DOUBLE COMMENT '年工作时间'")
	public Double getWorkHours() {
		return workHours;
	}


	public void setWorkHours(Double workHours) {
		this.workHours = workHours;
	}

	@Column(columnDefinition="DOUBLE COMMENT '废气收集效率（%）'")
	public Double getCollectEfficiency() {
		return collectEfficiency;
	}


	public void setCollectEfficiency(Double collectEfficiency) {
		this.collectEfficiency = collectEfficiency;
	}

	@Column(columnDefinition="DOUBLE COMMENT 'so2去除效率'")
	public Double getSo2() {
		return so2;
	}


	public void setSo2(Double so2) {
		this.so2 = so2;
	}

	@Column(columnDefinition="DOUBLE COMMENT 'nox去除效率'")
	public Double getNox() {
		return nox;
	}


	public void setNox(Double nox) {
		this.nox = nox;
	}

	@Column(columnDefinition="DOUBLE COMMENT 'co去除效率'")
	public Double getCo() {
		return co;
	}


	public void setCo(Double co) {
		this.co = co;
	}

	@Column(columnDefinition="DOUBLE COMMENT 'pm10去除效率'")
	public Double getPm10() {
		return pm10;
	}


	public void setPm10(Double pm10) {
		this.pm10 = pm10;
	}

	@Column(columnDefinition="DOUBLE COMMENT 'pm25去除效率'")
	public Double getPm25() {
		return pm25;
	}


	public void setPm25(Double pm25) {
		this.pm25 = pm25;
	}

	@Column(columnDefinition="DOUBLE COMMENT 'bc去除效率'")
	public Double getBc() {
		return bc;
	}


	public void setBc(Double bc) {
		this.bc = bc;
	}

	@Column(columnDefinition="DOUBLE COMMENT 'oc去除效率'")
	public Double getOc() {
		return oc;
	}


	public void setOc(Double oc) {
		this.oc = oc;
	}

	@Column(columnDefinition="DOUBLE COMMENT 'voc去除效率'")
	public Double getVoc() {
		return voc;
	}


	public void setVoc(Double voc) {
		this.voc = voc;
	}

	@Column(columnDefinition="DOUBLE COMMENT 'nh3去除效率'")
	public Double getNh3() {
		return nh3;
	}


	public void setNh3(Double nh3) {
		this.nh3 = nh3;
	}

	@ManyToOne
	public Enterprise getEnterprise() {
		return enterprise;
	}


	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'tsp去除效率'")
	public Double getTsp() {
		return tsp;
	}

	public void setTsp(Double tsp) {
		this.tsp = tsp;
	}

	@Column(length=16,updatable=false,columnDefinition="VARCHAR(255) COMMENT '治理编号'")
	public String getGmno() {
		return gmno;
	}

	public void setGmno(String gmno) {
		this.gmno = gmno;
	}

	@Enumerated(EnumType.STRING)
	public EnterpriseType getUserType() {
		return userType;
	}

	public void setUserType(EnterpriseType userType) {
		this.userType = userType;
	}

	@Column(columnDefinition="DATETIME COMMENT '停运时间'")
	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	
	
	
	@Column(columnDefinition="DOUBLE COMMENT '初次投入成本（万元）'")
	public Double getFirstPut() {
		return firstPut;
	}

	public void setFirstPut(Double firstPut) {
		this.firstPut = firstPut;
	}
	@Column(columnDefinition="DOUBLE COMMENT '运行成本（万元/月）'")
	public Double getOperatingCost() {
		return operatingCost;
	}

	public void setOperatingCost(Double operatingCost) {
		this.operatingCost = operatingCost;
	}
	
	@Column(columnDefinition="DOUBLE COMMENT '脱硫剂更换频率（次/年）'")
	public Double getDesulfurationfrequency() {
		return desulfurationfrequency;
	}

	public void setDesulfurationfrequency(Double desulfurationfrequency) {
		this.desulfurationfrequency = desulfurationfrequency;
	}
	@Column(columnDefinition="DOUBLE COMMENT '脱硝剂更换频率（次/年）'")
	public Double getDenitrationfrequency() {
		return denitrationfrequency;
	}

	public void setDenitrationfrequency(Double denitrationfrequency) {
		this.denitrationfrequency = denitrationfrequency;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '吸附剂名称'")
	public String getAdsorbentName() {
		return adsorbentName;
	}

	public void setAdsorbentName(String adsorbentName) {
		this.adsorbentName = adsorbentName;
	}
	@Column(columnDefinition="DOUBLE COMMENT '吸附剂用量（吨）'")
	public Double getAdsorbentDosage() {
		return adsorbentDosage;
	}

	public void setAdsorbentDosage(Double adsorbentDosage) {
		this.adsorbentDosage = adsorbentDosage;
	}
	@Column(columnDefinition="DOUBLE COMMENT '吸附剂更换频率（次/年）'")
	public Double getAdsorbentfrequency() {
		return adsorbentfrequency;
	}

	public void setAdsorbentfrequency(Double adsorbentfrequency) {
		this.adsorbentfrequency = adsorbentfrequency;
	}
	@Column(columnDefinition="DOUBLE COMMENT '布袋清洗频率（次/年）'")
	public Double getDedustingFrequency() {
		return dedustingFrequency;
	}

	public void setDedustingFrequency(Double dedustingFrequency) {
		this.dedustingFrequency = dedustingFrequency;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '是否安装GGH(烟气加热)'")
	public String getIsGGH() {
		return isGGH;
	}

	public void setIsGGH(String isGGH) {
		this.isGGH = isGGH;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '污染物类型'")
	public String getContaminantType() {
		return contaminantType;
	}

	public void setContaminantType(String contaminantType) {
		this.contaminantType = contaminantType;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '污染物去除效率'")
	public String getContaminantRate() {
		return contaminantRate;
	}

	public void setContaminantRate(String contaminantRate) {
		this.contaminantRate = contaminantRate;
	}
	
	
	
	

}

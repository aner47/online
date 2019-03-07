package com.online.entity.online;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.exportpdf.DataBean;

@EntityDescription(caption="基础信息")
@Entity
@Table(name="ol_base_info")
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING,name="dtype")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_base_info")
public class Information extends BaseEntity<Integer> implements DataBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7861716210430776014L;
	/**
	 * 企业
	 */
	@ColumnDescription(caption="企业")
	private Enterprise enterprise;
	/**
	 * 年生产天数
	 */
	@ColumnDescription(caption="年生产天数")
	private Double workDay;
	
	/**
	 * 日平均生产小时数
	 */
	@ColumnDescription(caption="日平均生产小时数")
	private Double dayHours;
	/**
	 * 全年生产总值
	 */
	@ColumnDescription(caption="全年生产总值")
	private Double grossProduct;
	
	/**
	 * 年生产小时数（小时）
	 */
	@ColumnDescription(caption="年生产小时数（小时）")
	private Double nomalProductionHour;
	
	/**
	 * 	全年用电量（千瓦时）	
	 */
	@ColumnDescription(caption="全年用电量")
	private Double energyUsed;
	
	
	/**
	 * 项目
	 */
	@ColumnDescription(caption="项目")
	private Project project;
	
	/**
	 * 是否废气重点污染源
	 */
	@ColumnDescription(caption="是否废气重点污染源")
	private String  emphasisPolluteSource;
	
	/**
	 * 总量核查是否覆盖
	 */
	@ColumnDescription(caption="总量核查是否覆盖")
	private String grossExamineCoverage;
	
	/**
	 * 2016年总量核查信息-->SO2控制限制值
	 */
	@ColumnDescription(caption="SO2控制限制值")
	private Double so2ControlLimitationValue;
	/**
	 * 2016年总量核查信息-->SO2核查排放量
	 */
	@ColumnDescription(caption="SO2核查排放量")
	private Double so2ExamineDischargeValue;
	/**
	 * 2016年总量核查信息-->NOx控制限制值
	 */
	@ColumnDescription(caption="NOx控制限制值")
	private Double noxControlLimitationValue;
	/**
	 * 2016年总量核查信息-->NOx核查排放量
	 */
	@ColumnDescription(caption="NOx核查排放量")
	private Double noxExamineDischargeValue;
	
	/**
	 * 环统是否覆盖
	 */
	@ColumnDescription(caption="环统是否覆盖")
	private String statisticsCoverage;
	
	/**
	 * 2016年环境统计信息--->SO2
	 */
	@ColumnDescription(caption="统计信息SO2")
	private Double so2Statistics;
	
	/**
	 * 2016年环境统计信息--->NOx
	 */
	@ColumnDescription(caption="统计信息NOx")
	private Double noxStatistics;
	/**
	 * 2016年环境统计信息--->烟粉尘
	 */
	@ColumnDescription(caption="统计信息烟粉尘")
	private Double smokeStive;
	/**
	 * 2016年环境统计信息--->VOC
	 */
	@ColumnDescription(caption="统计信息VOC")
	private Double vocStatistics;
	
	
	
	/**
	 * 污水排放量（吨）
	 */
	@ColumnDescription(caption="污水排放量")
	private Double sewageDischargeValue;
	
	/**
	 * 废水自处理率（%）
	 */
	@ColumnDescription(caption="废水自处理率")
	private Double effluentDisposeRate;
	
	/**
	 * 污水处理池是否加盖
	 */
	@ColumnDescription(caption="污水处理池是否加盖")
	private String sewageCap;
	
	/**
	 * 排污许可证编号
	 */
	@ColumnDescription(caption="排污许可证编号")
	private String licenceNo;
	
	/**
	 * 煤炭来源地
	 */
	@ColumnDescription(caption="煤炭来源地")
	private String coalSourcePlace;
	
	/**
	 * 主要原料来源地
	 */
	@ColumnDescription(caption="主要原料来源地")
	private String rawSourcePlace;
	
	/**
	 * 产能规模
	 */
	@ColumnDescription(caption="产能规模")
	private String capacityScale;
	
	/**
	 * 生产月份
	 */
	@ColumnDescription(caption="生产月份")
	private String productionMonth;
	
	
	/**
	 * 环保专工人数
	 */
	@ColumnDescription(caption="环保专工人数")
	private Double specialtyPeoples;

	/**
	 * 是否有GGH烟气再加热系统（仅电厂）
	 */
	@ColumnDescription(caption="是否有GGH烟气再加热系统")
	private String isGGH;
	
	/**
	 * 煤炭运输方式及其比例-火车
	 */
	@ColumnDescription(caption="煤炭运输方式及其比例-火车")
	private Double proportion1;
	/**
	 * 煤炭运输方式及其比例-汽车
	 */
	@ColumnDescription(caption="煤炭运输方式及其比例-汽车")
	private Double proportion2;
	/**
	 * 煤炭运输方式及其比例-其他
	 */
	@ColumnDescription(caption="煤炭运输方式及其比例-其他")
	private Double proportion3;
	
	/**
	 * 煤堆场是否封闭
	 */
	@ColumnDescription(caption="煤堆场是否封闭")
	private Boolean coalClosed;
	
	/**
	 * 卸煤沟是否封闭
	 */
	@ColumnDescription(caption="卸煤沟是否封闭")
	private Boolean unloadCoalClosed;
	
	/**
	 * 食堂灶头数
	 */
	@ColumnDescription(caption="食堂灶头数")
	private Integer kitchenNum;
	
	
	/**
	 * 是否安装油烟去除装置
	 */
	@ColumnDescription(caption="是否安装油烟去除装置")
	private Boolean isLampblackWipe;
	
	/**
	 * 烟气排放速率（立方米/时）
	 */
	@ColumnDescription(caption="烟气排放速率（立方米/时）")
	private Double smokeDischargeRate;
	
	/**
	 * 油烟去除率
	 */
	@ColumnDescription(caption="油烟去除率")
	private Double lampblackWipeRate;
	
	/**
	 * 生产时段-开始
	 */
	@ColumnDescription(caption="生产时段-开始")
	@Temporal(TemporalType.TIME)
	private Date startManufacture;
	
	/**
	 * 生产时段-结束
	 */
	@ColumnDescription(caption="生产时段-结束")
	@Temporal(TemporalType.TIME)
	private Date endManufacture;
	
	/**
	 * 停机维修时段
	 */
	@ColumnDescription(caption="停机维修时段")
	private String stopServiceTime;
	
	/**
	 * 投产年份
	 */
	@ColumnDescription(caption="投产年份")
	private String putProductionYear;
	
	
	/**
	 * 年煤炭消耗量(吨)
	 */
	@ColumnDescription(caption="年煤炭消耗量(吨)")
	private Double annualCoalConsume;
	
	
	/**
	 * 年燃气消耗量(万立方米)
	 */
	@ColumnDescription(caption="年燃气消耗量(万立方米)")
	private Double annualFuelgasConsume;
	
	/**
	 * 年燃油消耗量（吨）
	 */
	@ColumnDescription(caption="年燃油消耗量（吨）")
	private Double annualFueloilConsume;
	
	/**
	 * 锅炉数（台）
	 */
	@ColumnDescription(caption="锅炉数（台）")
	private Integer boilerAmount;
	
	/**
	 * 锅炉总蒸吨数(吨/小时)
	 */
	@ColumnDescription(caption="锅炉总蒸吨数(吨/小时)")
	private Double boilerZhengdun;
	
	/**
	 * 自备电厂发电机组数（个）
	 */
	@ColumnDescription(caption="自备电厂发电机组数（个）")
	private Integer ownedpowerAmount;
	
	/**
	 * 露天堆场数（个）
	 */
	@ColumnDescription(caption="露天堆场数（个）")
	private Integer openyardAmount;
	
	/**
	 * 有机液体储罐数（个）
	 */
	@ColumnDescription(caption="有机液体储罐数（个）")
	private Integer organicTankAmount;
	



    
    /**
     * 备注
     */
    @ColumnDescription(caption="备注")
    private String description;
    /**
     * 锅炉备注信息
     */
    @ColumnDescription(caption="锅炉信息")
    private Boolean boilerStatus;
    
    /**
     * 排放口状态
     */
    @ColumnDescription(caption="排放口状态")
    private Boolean exhaustionHoleStatus;
    
    /**
     * 控制措施状态
     */
    @ColumnDescription(caption="控制措施状态")
    private Boolean bovernanceMeasuresStatus;
    
    /**
     * 分月状态
     */
    @ColumnDescription(caption="分月状态")
    private Boolean monthlyStatus;
    
    /**
     * 石油储罐状态
     */
    @ColumnDescription(caption="石油储罐状态")
    private Boolean oilTankStatus;
    
    /**
     * 工段状态
     */
    @ColumnDescription(caption="工段状态")
    private Boolean sectionStatus;

    
    
    /**
	 * 生产时段-上午
	 */
	@ColumnDescription(caption="生产时段-上午")
	private String forenoonManufacture;
    
    
	/**
	 * 生产时段-下午
	 */
	@ColumnDescription(caption="生产时段-下午")
	private String afternoonManufacture;
    
	/**
	 * 生产时段-晚上
	 */
	@ColumnDescription(caption="生产时段-晚上")
	private String nightManufacture;
	
    
    
	/**
	 * 油品运输方式及其比例-火车
	 */
	@ColumnDescription(caption="油品运输方式及其比例-火车")
	private Double oilproportion1;
	/**
	 * 油品运输方式及其比例-汽车
	 */
	@ColumnDescription(caption="油品运输方式及其比例-汽车")
	private Double oilproportion2;
	/**
	 * 油品运输方式及其比例-管道
	 */
	@ColumnDescription(caption="油品运输方式及其比例-管道")
	private Double oilproportion3;
    
	
	/**
	 * 车间是否整洁
	 */
	@ColumnDescription(caption="车间是否整洁")
	private String isClean;
	
	/**
	 * 粉尘无组织排放是否严重
	 */
	@ColumnDescription(caption="粉尘无组织排放是否严重")
	private String isSeverityDust;
	
	/**
	 * VOC气味是否浓重
	 */
	@ColumnDescription(caption="VOC气味是否浓重")
	private String isSeverityVOC;
	
	/**
	 * 通风设施是否常开
	 */
	@ColumnDescription(caption="通风设施是否常开")
	private String isVentilateOpen;
	
	/**
	 * 生产设备是否封闭
	 */
	@ColumnDescription(caption="生产设备是否封闭")
	private String isEquipmentClosed;
	
	
	/**
	 * 改扩建时间
	 */
	@ColumnDescription(caption="改扩建时间")
	private String rebuildTime;
	
	/**
	 * 排污许可是否覆盖
	 */
	@ColumnDescription(caption="排污许可是否覆盖")
	private String isDrainContaminationCoverage;
	
	
	/**
	 * 年生产成本（万元）
	 */
	@ColumnDescription(caption="年生产成本（万元）")
	private Double annualProductionCost;
	
	/**
	 * 是否有喷漆工序
	 */
	@ColumnDescription(caption="是否有喷漆工序")
	private String isPaint;
	
	/**
	 * 职工数量
	 */
	@ColumnDescription(caption="职工数量")
	private Integer employeeNum;
    
	
	
	/**
	 * 装置安装时间
	 */
	@ColumnDescription(caption="装置安装时间")
	@Temporal(TemporalType.TIME)
	private Date installTime;
	
	
	/**
	 * 污水废气治理措施
	 */
	@ColumnDescription(caption="污水废气治理措施")
	private String sewageMeasure;
	
	/**
	 * 关停时间
	 */
	@ColumnDescription(caption="关停时间")
	@Temporal(TemporalType.DATE)
	private Date stopTime;
	
	/**
	 * 企业状态
	 */
	@ColumnDescription(caption="企业状态")
	private String enterpriseState;
	
	
	/**
	 * 排放量--->SO2
	 */
	@ColumnDescription(caption="排放量SO2")
	private Double so2Discharge;
	
	/**
	 * 排放量--->NOx
	 */
	@ColumnDescription(caption="排放量NOx")
	private Double noxDischarge;
	/**
	 * 排放量--->烟粉尘
	 */
	@ColumnDescription(caption="排放量烟粉尘")
	private Double smokeDischarge;
	/**
	 * 排放量--->VOC
	 */
	@ColumnDescription(caption="排放量VOC")
	private Double vocDischarge;
	
	/**
	 * 生产时段-上午结束
	 */
	@ColumnDescription(caption="生产时段-上午结束")
	private String forenoonManufactureEnd;
	
	
	/**
	 * 生产时段-下午结束
	 */
	@ColumnDescription(caption="生产时段-下午结束")
	private String afternoonManufactureEnd;
	
	/**
	 * 生产时段-晚上结束
	 */
	@ColumnDescription(caption="生产时段-晚上结束")
	private String nightManufactureEnd;
	
	
	/**
	 * 用电户号
	 */
	@ColumnDescription(caption="用电户号")
	private String powerHouseNo;
	
	
	/**
	 * 是否保存
	 */
	@ColumnDescription(caption="是否保存")
	private String isSave;
	
	
	/**
	 * 调查人
	 */
	@ColumnDescription(caption="调查人")
	private String inquirer;
	
	
	/**
	 * 调查人联系电话
	 */
	@ColumnDescription(caption="调查人联系电话")
	private String inquirerPhone;
	
	
	/**
	 * 年消耗量-其他燃料
	 */
	@ColumnDescription(caption="其他燃料")
	private Double otherfuel;
	
	
	
	@Column(columnDefinition="DOUBLE COMMENT '其他燃料'")
	public Double getOtherfuel() {
		return otherfuel;
	}

	public void setOtherfuel(Double otherfuel) {
		this.otherfuel = otherfuel;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '调查人'")
	public String getInquirer() {
		return inquirer;
	}

	public void setInquirer(String inquirer) {
		this.inquirer = inquirer;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '调查人联系电话'")
	public String getInquirerPhone() {
		return inquirerPhone;
	}

	public void setInquirerPhone(String inquirerPhone) {
		this.inquirerPhone = inquirerPhone;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '是否保存'")
	public String getIsSave() {
		return isSave;
	}

	public void setIsSave(String isSave) {
		this.isSave = isSave;
	}
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '用电户号'")
	public String getPowerHouseNo() {
		return powerHouseNo;
	}

	public void setPowerHouseNo(String powerHouseNo) {
		this.powerHouseNo = powerHouseNo;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '生产时段上午结束'")
	public String getForenoonManufactureEnd() {
		return forenoonManufactureEnd;
	}

	public void setForenoonManufactureEnd(String forenoonManufactureEnd) {
		this.forenoonManufactureEnd = forenoonManufactureEnd;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '生产时段下午结束'")
	public String getAfternoonManufactureEnd() {
		return afternoonManufactureEnd;
	}

	public void setAfternoonManufactureEnd(String afternoonManufactureEnd) {
		this.afternoonManufactureEnd = afternoonManufactureEnd;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '生产时段晚上结束'")
	public String getNightManufactureEnd() {
		return nightManufactureEnd;
	}

	public void setNightManufactureEnd(String nightManufactureEnd) {
		this.nightManufactureEnd = nightManufactureEnd;
	}

	@Column(columnDefinition="DOUBLE COMMENT 'so2排放许可量'")
	public Double getSo2Discharge() {
		return so2Discharge;
	}

	public void setSo2Discharge(Double so2Discharge) {
		this.so2Discharge = so2Discharge;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'Nox排放许可量'")
	public Double getNoxDischarge() {
		return noxDischarge;
	}

	public void setNoxDischarge(Double noxDischarge) {
		this.noxDischarge = noxDischarge;
	}
	@Column(columnDefinition="DOUBLE COMMENT '烟粉尘排放许可量'")
	public Double getSmokeDischarge() {
		return smokeDischarge;
	}

	public void setSmokeDischarge(Double smokeDischarge) {
		this.smokeDischarge = smokeDischarge;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'VOCs排放许可量'")
	public Double getVocDischarge() {
		return vocDischarge;
	}

	public void setVocDischarge(Double vocDischarge) {
		this.vocDischarge = vocDischarge;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '企业状态'")
	public String getEnterpriseState() {
		return enterpriseState;
	}

	public void setEnterpriseState(String enterpriseState) {
		this.enterpriseState = enterpriseState;
	}
	
	
	@Column(columnDefinition="DATETIME COMMENT '关停时间'")
	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}
    
	@Column(columnDefinition="DATE COMMENT '装置安装时间'")
	public Date getInstallTime() {
		return installTime;
	}

	public void setInstallTime(Date installTime) {
		this.installTime = installTime;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '污水废气治理措施'")
	public String getSewageMeasure() {
		return sewageMeasure;
	}

	public void setSewageMeasure(String sewageMeasure) {
		this.sewageMeasure = sewageMeasure;
	}

	@Column(columnDefinition="DOUBLE COMMENT '职工数量'")
	public Integer getEmployeeNum() {
		return employeeNum;
	}

	public void setEmployeeNum(Integer employeeNum) {
		this.employeeNum = employeeNum;
	}

	@Column(columnDefinition="DOUBLE COMMENT '年煤炭消耗量(吨)'")
	public Double getAnnualCoalConsume() {
		return annualCoalConsume;
	}

	public void setAnnualCoalConsume(Double annualCoalConsume) {
		this.annualCoalConsume = annualCoalConsume;
	}
	@Column(columnDefinition="DOUBLE COMMENT '年燃气消耗量(万立方米)'")
	public Double getAnnualFuelgasConsume() {
		return annualFuelgasConsume;
	}

	public void setAnnualFuelgasConsume(Double annualFuelgasConsume) {
		this.annualFuelgasConsume = annualFuelgasConsume;
	}
	@Column(columnDefinition="DOUBLE COMMENT '年燃油消耗量（吨）'")
	public Double getAnnualFueloilConsume() {
		return annualFueloilConsume;
	}

	public void setAnnualFueloilConsume(Double annualFueloilConsume) {
		this.annualFueloilConsume = annualFueloilConsume;
	}
	@Column(columnDefinition="INT COMMENT '锅炉数（台）'")
	public Integer getBoilerAmount() {
		return boilerAmount;
	}

	public void setBoilerAmount(Integer boilerAmount) {
		this.boilerAmount = boilerAmount;
	}
	@Column(columnDefinition="DOUBLE COMMENT '锅炉总蒸吨数(吨/小时)'")
	public Double getBoilerZhengdun() {
		return boilerZhengdun;
	}

	public void setBoilerZhengdun(Double boilerZhengdun) {
		this.boilerZhengdun = boilerZhengdun;
	}
	@Column(columnDefinition="INT COMMENT '自备电厂发电机组数（个）'")
	public Integer getOwnedpowerAmount() {
		return ownedpowerAmount;
	}

	public void setOwnedpowerAmount(Integer ownedpowerAmount) {
		this.ownedpowerAmount = ownedpowerAmount;
	}
	@Column(columnDefinition="INT COMMENT '露天堆场数（个）'")
	public Integer getOpenyardAmount() {
		return openyardAmount;
	}

	public void setOpenyardAmount(Integer openyardAmount) {
		this.openyardAmount = openyardAmount;
	}
	@Column(columnDefinition="INT COMMENT '有机液体储罐数（个）'")
	public Integer getOrganicTankAmount() {
		return organicTankAmount;
	}

	public void setOrganicTankAmount(Integer organicTankAmount) {
		this.organicTankAmount = organicTankAmount;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '投产年份'")
	public String getPutProductionYear() {
		return putProductionYear;
	}

	public void setPutProductionYear(String putProductionYear) {
		this.putProductionYear = putProductionYear;
	}

	
	@Column(columnDefinition="INT COMMENT '食堂灶头数'")
	public Integer getKitchenNum() {
		return kitchenNum;
	}

	public void setKitchenNum(Integer kitchenNum) {
		this.kitchenNum = kitchenNum;
	}
	@Column(columnDefinition="BOOLEAN COMMENT '是否安装油烟去除装置'")
	public Boolean getIsLampblackWipe() {
		return isLampblackWipe;
	}

	public void setIsLampblackWipe(Boolean isLampblackWipe) {
		this.isLampblackWipe = isLampblackWipe;
	}
	@Column(columnDefinition="DOUBLE COMMENT '烟气排放速率（立方米/时）'")
	public Double getSmokeDischargeRate() {
		return smokeDischargeRate;
	}

	public void setSmokeDischargeRate(Double smokeDischargeRate) {
		this.smokeDischargeRate = smokeDischargeRate;
	}
	@Column(columnDefinition="DOUBLE COMMENT '油烟去除率'")
	public Double getLampblackWipeRate() {
		return lampblackWipeRate;
	}

	public void setLampblackWipeRate(Double lampblackWipeRate) {
		this.lampblackWipeRate = lampblackWipeRate;
	}
	@Column(columnDefinition="DATE COMMENT '生产时段-开始'")
	public Date getStartManufacture() {
		return startManufacture;
	}

	public void setStartManufacture(Date startManufacture) {
		this.startManufacture = startManufacture;
	}
	@Column(columnDefinition="DATE COMMENT '生产时段-结束'")
	public Date getEndManufacture() {
		return endManufacture;
	}

	public void setEndManufacture(Date endManufacture) {
		this.endManufacture = endManufacture;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '停机维修时段'")
	public String getStopServiceTime() {
		return stopServiceTime;
	}

	public void setStopServiceTime(String stopServiceTime) {
		this.stopServiceTime = stopServiceTime;
	}
	@Column(columnDefinition="BOOLEAN COMMENT '煤堆场是否封闭'")
	public Boolean getCoalClosed() {
		return coalClosed;
	}

	public void setCoalClosed(Boolean coalClosed) {
		this.coalClosed = coalClosed;
	}
	@Column(columnDefinition="BOOLEAN COMMENT '卸煤沟是否封闭'")
	public Boolean getUnloadCoalClosed() {
		return unloadCoalClosed;
	}

	public void setUnloadCoalClosed(Boolean unloadCoalClosed) {
		this.unloadCoalClosed = unloadCoalClosed;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '是否有GGH烟气再加热系统'")
	public String getIsGGH() {
		return isGGH;
	}

	public void setIsGGH(String isGGH) {
		this.isGGH = isGGH;
	}
	@Column(columnDefinition="DOUBLE COMMENT '煤炭运输方式及其比例-火车'")
	public Double getProportion1() {
		return proportion1;
	}

	public void setProportion1(Double proportion1) {
		this.proportion1 = proportion1;
	}
	@Column(columnDefinition="DOUBLE COMMENT '煤炭运输方式及其比例-汽车'")
	public Double getProportion2() {
		return proportion2;
	}

	public void setProportion2(Double proportion2) {
		this.proportion2 = proportion2;
	}
	@Column(columnDefinition="DOUBLE COMMENT '煤炭运输方式及其比例-其他'")
	public Double getProportion3() {
		return proportion3;
	}

	public void setProportion3(Double proportion3) {
		this.proportion3 = proportion3;
	}
	@Column(columnDefinition="DOUBLE COMMENT '环保专工人数'")
	public Double getSpecialtyPeoples() {
		return specialtyPeoples;
	}

	public void setSpecialtyPeoples(Double specialtyPeoples) {
		this.specialtyPeoples = specialtyPeoples;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '生产月份'")
	public String getProductionMonth() {
		return productionMonth;
	}

	public void setProductionMonth(String productionMonth) {
		this.productionMonth = productionMonth;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '排污许可证编号'")
	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '煤炭来源地'")
	public String getCoalSourcePlace() {
		return coalSourcePlace;
	}

	public void setCoalSourcePlace(String coalSourcePlace) {
		this.coalSourcePlace = coalSourcePlace;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '主要原料来源地'")
	public String getRawSourcePlace() {
		return rawSourcePlace;
	}

	public void setRawSourcePlace(String rawSourcePlace) {
		this.rawSourcePlace = rawSourcePlace;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '是否废气重点污染源'")
	public String getEmphasisPolluteSource() {
		return emphasisPolluteSource;
	}

	public void setEmphasisPolluteSource(String emphasisPolluteSource) {
		this.emphasisPolluteSource = emphasisPolluteSource;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '总量核查是否覆盖'")
	public String getGrossExamineCoverage() {
		return grossExamineCoverage;
	}

	public void setGrossExamineCoverage(String grossExamineCoverage) {
		this.grossExamineCoverage = grossExamineCoverage;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'SO2控制限制值'")
	public Double getSo2ControlLimitationValue() {
		return so2ControlLimitationValue;
	}

	public void setSo2ControlLimitationValue(Double so2ControlLimitationValue) {
		this.so2ControlLimitationValue = so2ControlLimitationValue;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'SO2核查排放量'")
	public Double getSo2ExamineDischargeValue() {
		return so2ExamineDischargeValue;
	}

	public void setSo2ExamineDischargeValue(Double so2ExamineDischargeValue) {
		this.so2ExamineDischargeValue = so2ExamineDischargeValue;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'NOx控制限制值'")
	public Double getNoxControlLimitationValue() {
		return noxControlLimitationValue;
	}

	public void setNoxControlLimitationValue(Double noxControlLimitationValue) {
		this.noxControlLimitationValue = noxControlLimitationValue;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'NOx核查排放量'")
	public Double getNoxExamineDischargeValue() {
		return noxExamineDischargeValue;
	}

	public void setNoxExamineDischargeValue(Double noxExamineDischargeValue) {
		this.noxExamineDischargeValue = noxExamineDischargeValue;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '环统是否覆盖'")
	public String getStatisticsCoverage() {
		return statisticsCoverage;
	}

	public void setStatisticsCoverage(String statisticsCoverage) {
		this.statisticsCoverage = statisticsCoverage;
	}
	@Column(columnDefinition="DOUBLE COMMENT '统计信息SO2'")
	public Double getSo2Statistics() {
		return so2Statistics;
	}

	public void setSo2Statistics(Double so2Statistics) {
		this.so2Statistics = so2Statistics;
	}
	@Column(columnDefinition="DOUBLE COMMENT '统计信息NOx'")
	public Double getNoxStatistics() {
		return noxStatistics;
	}

	public void setNoxStatistics(Double noxStatistics) {
		this.noxStatistics = noxStatistics;
	}
	@Column(columnDefinition="DOUBLE COMMENT '统计信息烟粉尘'")
	public Double getSmokeStive() {
		return smokeStive;
	}

	public void setSmokeStive(Double smokeStive) {
		this.smokeStive = smokeStive;
	}
	@Column(columnDefinition="DOUBLE COMMENT '统计信息VOC'")
	public Double getVocStatistics() {
		return vocStatistics;
	}

	public void setVocStatistics(Double vocStatistics) {
		this.vocStatistics = vocStatistics;
	}

	
	@Column(columnDefinition="DOUBLE COMMENT '污水排放量'")
	public Double getSewageDischargeValue() {
		return sewageDischargeValue;
	}

	public void setSewageDischargeValue(Double sewageDischargeValue) {
		this.sewageDischargeValue = sewageDischargeValue;
	}
	@Column(columnDefinition="DOUBLE COMMENT '废水自处理率'")
	public Double getEffluentDisposeRate() {
		return effluentDisposeRate;
	}

	public void setEffluentDisposeRate(Double effluentDisposeRate) {
		this.effluentDisposeRate = effluentDisposeRate;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '污水处理池是否加盖'")
	public String getSewageCap() {
		return sewageCap;
	}

	public void setSewageCap(String sewageCap) {
		this.sewageCap = sewageCap;
	}
	
	@Column(columnDefinition="DOUBLE COMMENT '年生产天数'")
	public Double getWorkDay() {
		return workDay;
	}

	public void setWorkDay(Double workDay) {
		this.workDay = workDay;
	}
	@Column(columnDefinition="DOUBLE COMMENT '日平均生产小时数'")
	public Double getDayHours() {
		return dayHours;
	}

	public void setDayHours(Double dayHours) {
		this.dayHours = dayHours;
	}
	@Column(columnDefinition="DOUBLE COMMENT '全年生产总值'")
	public Double getGrossProduct() {
		return grossProduct;
	}

	public void setGrossProduct(Double grossProduct) {
		this.grossProduct = grossProduct;
	}
	@Column(columnDefinition="DOUBLE COMMENT '年正常生产时间'")
	public Double getNomalProductionHour() {
		return nomalProductionHour;
	}

	public void setNomalProductionHour(Double nomalProductionHour) {
		this.nomalProductionHour = nomalProductionHour;
	}
	@Column(columnDefinition="DOUBLE COMMENT '全年用电量'")
	public Double getEnergyUsed() {
		return energyUsed;
	}

	public void setEnergyUsed(Double energyUsed) {
		this.energyUsed = energyUsed;
	}
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '产能规模'")
	public String getCapacityScale() {
		return capacityScale;
	}

	public void setCapacityScale(String capacityScale) {
		this.capacityScale = capacityScale;
	}

	@ManyToOne
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	
	@ManyToOne
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	@Lob
	@Column(columnDefinition="VARCHAR(255) COMMENT '备注'")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(columnDefinition="BOOLEAN COMMENT '锅炉信息'")
	public Boolean getBoilerStatus() {
		return boilerStatus;
	}

	public void setBoilerStatus(Boolean boilerStatus) {
		this.boilerStatus = boilerStatus;
	}

	@Column(columnDefinition="BOOLEAN COMMENT '排放口状态'")
	public Boolean getExhaustionHoleStatus() {
		return exhaustionHoleStatus;
	}

	public void setExhaustionHoleStatus(Boolean exhaustionHoleStatus) {
		this.exhaustionHoleStatus = exhaustionHoleStatus;
	}
	
	@Column(columnDefinition="BOOLEAN COMMENT '控制措施状态'")
	public Boolean getBovernanceMeasuresStatus() {
		return bovernanceMeasuresStatus;
	}

	public void setBovernanceMeasuresStatus(Boolean bovernanceMeasuresStatus) {
		this.bovernanceMeasuresStatus = bovernanceMeasuresStatus;
	}
	@Column(columnDefinition="BOOLEAN COMMENT '分月状态'")
	public Boolean getMonthlyStatus() {
		return monthlyStatus;
	}

	public void setMonthlyStatus(Boolean monthlyStatus) {
		this.monthlyStatus = monthlyStatus;
	}
	@Column(columnDefinition="BOOLEAN COMMENT '石油储罐状态'")
	public Boolean getOilTankStatus() {
		return oilTankStatus;
	}

	public void setOilTankStatus(Boolean oilTankStatus) {
		this.oilTankStatus = oilTankStatus;
	}
	@Column(columnDefinition="BOOLEAN COMMENT '工段状态'")
	public Boolean getSectionStatus() {
		return sectionStatus;
	}

	public void setSectionStatus(Boolean sectionStatus) {
		this.sectionStatus = sectionStatus;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '生产时段-上午'")
	public String getForenoonManufacture() {
		return forenoonManufacture;
	}

	public void setForenoonManufacture(String forenoonManufacture) {
		this.forenoonManufacture = forenoonManufacture;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '生产时段-下午'")
	public String getAfternoonManufacture() {
		return afternoonManufacture;
	}

	public void setAfternoonManufacture(String afternoonManufacture) {
		this.afternoonManufacture = afternoonManufacture;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '生产时段-晚上'")
	public String getNightManufacture() {
		return nightManufacture;
	}

	public void setNightManufacture(String nightManufacture) {
		this.nightManufacture = nightManufacture;
	}

	@Column(columnDefinition="DOUBLE COMMENT '油品运输方式及其比例-火车'")
	public Double getOilproportion1() {
		return oilproportion1;
	}

	public void setOilproportion1(Double oilproportion1) {
		this.oilproportion1 = oilproportion1;
	}
	@Column(columnDefinition="DOUBLE COMMENT '油品运输方式及其比例-汽车'")
	public Double getOilproportion2() {
		return oilproportion2;
	}

	public void setOilproportion2(Double oilproportion2) {
		this.oilproportion2 = oilproportion2;
	}
	@Column(columnDefinition="DOUBLE COMMENT '油品运输方式及其比例-管道'")
	public Double getOilproportion3() {
		return oilproportion3;
	}

	public void setOilproportion3(Double oilproportion3) {
		this.oilproportion3 = oilproportion3;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '车间是否整洁'")
	public String getIsClean() {
		return isClean;
	}

	public void setIsClean(String isClean) {
		this.isClean = isClean;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '粉尘无组织排放是否严重'")
	public String getIsSeverityDust() {
		return isSeverityDust;
	}

	public void setIsSeverityDust(String isSeverityDust) {
		this.isSeverityDust = isSeverityDust;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT 'VOC气味是否浓重'")
	public String getIsSeverityVOC() {
		return isSeverityVOC;
	}

	public void setIsSeverityVOC(String isSeverityVOC) {
		this.isSeverityVOC = isSeverityVOC;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '通风设施是否常开'")
	public String getIsVentilateOpen() {
		return isVentilateOpen;
	}

	public void setIsVentilateOpen(String isVentilateOpen) {
		this.isVentilateOpen = isVentilateOpen;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '生产设备是否封闭'")
	public String getIsEquipmentClosed() {
		return isEquipmentClosed;
	}

	public void setIsEquipmentClosed(String isEquipmentClosed) {
		this.isEquipmentClosed = isEquipmentClosed;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '改扩建时间'")
	public String getRebuildTime() {
		return rebuildTime;
	}

	public void setRebuildTime(String rebuildTime) {
		this.rebuildTime = rebuildTime;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '排污许可是否覆盖'")
	public String getIsDrainContaminationCoverage() {
		return isDrainContaminationCoverage;
	}

	public void setIsDrainContaminationCoverage(String isDrainContaminationCoverage) {
		this.isDrainContaminationCoverage = isDrainContaminationCoverage;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '年生产成本（万元）'")
	public Double getAnnualProductionCost() {
		return annualProductionCost;
	}

	public void setAnnualProductionCost(Double annualProductionCost) {
		this.annualProductionCost = annualProductionCost;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '是否有喷漆工序'")
	public String getIsPaint() {
		return isPaint;
	}

	public void setIsPaint(String isPaint) {
		this.isPaint = isPaint;
	}
	
	
	
	
	
	
}

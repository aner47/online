package com.online.entity.online;

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

/**
 * 分月信息展示
 * @author DEV2
 *
 */
@EntityDescription(caption="分月数据")
@Entity
@Table(name="ol_monthly_information")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_monthly_information")
public class MonthlyInformation extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3780316964274329726L;

	private Enterprise enterprise;
	/**
	 * 分页信息类型
	 * @author DEV2
	 *
	 */
	public enum MonthlyInformationType{
		product,
		fuel,
		rawMaterials
	}
	
	/**
	 * 分月信息所属流程
	 *
	 */
	public enum MonthlyInformationProcess{
		section,
		boiler,
		kiln,
		powerPlant,
		steel
	}
	
	/**
	 * 所属流程类型
	 */
	private MonthlyInformationProcess monthlyInformationProcess;
	
	
	/**
	 * 类型
	 */
	@ColumnDescription(caption="类型")
	private MonthlyInformationType monthType;
	/**
	 * 名称
	 */
	@ColumnDescription(caption="名称")
	private String name ;
	
	/**
	 * 一月
	 */
	@ColumnDescription(caption="一月")
	private Double january;
	
	/**
	 * 二月
	 */
	@ColumnDescription(caption="二月")
	private Double february;
	
	/**
	 * 三月
	 */
	@ColumnDescription(caption="三月")
	private Double march;
	
	/**
	 * 四月
	 */
	@ColumnDescription(caption="四月")
	private Double april;
	
	/**
	 * 五月
	 */
	@ColumnDescription(caption="五月")
	private Double may;
	
	/**
	 * 六月
	 */
	@ColumnDescription(caption="六月")
	private Double june; 
	
	/**
	 * 七月
	 */
	@ColumnDescription(caption="七月")
	private Double july;
	
	/**
	 * 八月
	 */
	@ColumnDescription(caption="八月")
	private Double august; 
	
	/**
	 * 九月
	 */
	@ColumnDescription(caption="九月")
	private Double september; 
	
	/**
	 * 十月
	 */
	@ColumnDescription(caption="十月")
	private Double october; 
	
	/**
	 * 十一月
	 */
	@ColumnDescription(caption="十一月")
	private Double november;
	
	/**
	 * 十二月
	 */
	@ColumnDescription(caption="十二月")
	private Double december;
	
	/**
	 * 单位
	 */
	@ColumnDescription(caption="单位")
	private String unit;
	
	/**
	 * 项目
	 */
	private Project project;
	
	
	/**
	 * 备注
	 */
	private String description;
	
	/**
	 * 年份
	 */
	private String year;
	
	
	/**
	 * 发电机组id
	 */
	private Integer powerPlantId;
	
	/**
	 * 锅炉id
	 */
	private Integer boilerId;
	
	/**
	 * 煤气炉id
	 */
	private Integer gasstoveId;
	
	/**
	 * 炉窑id
	 */
	private Integer kilnId;
	/**
	 * 生产线id
	 */
	private Integer sectionId;
	
	/**
	 * 原辅料Id
	 */
	private Integer rawMaterialsId;
	

	
	

	public Integer getRawMaterialsId() {
		return rawMaterialsId;
	}

	public void setRawMaterialsId(Integer rawMaterialsId) {
		this.rawMaterialsId = rawMaterialsId;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public Integer getKilnId() {
		return kilnId;
	}

	public void setKilnId(Integer kilnId) {
		this.kilnId = kilnId;
	}

	public Integer getGasstoveId() {
		return gasstoveId;
	}

	public void setGasstoveId(Integer gasstoveId) {
		this.gasstoveId = gasstoveId;
	}

	public Integer getPowerPlantId() {
		return powerPlantId;
	}

	public void setPowerPlantId(Integer powerPlantId) {
		this.powerPlantId = powerPlantId;
	}
	
	

	public Integer getBoilerId() {
		return boilerId;
	}

	public void setBoilerId(Integer boilerId) {
		this.boilerId = boilerId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

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


	@Enumerated(EnumType.STRING)
	public MonthlyInformationType getMonthType() {
		return monthType;
	}

	public void setMonthType(MonthlyInformationType monthType) {
		this.monthType = monthType;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '名称'")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Double getJanuary() {
		return january;
	}

	public void setJanuary(Double january) {
		this.january = january;
	}
	
	public Double getFebruary() {
		return february;
	}

	public void setFebruary(Double february) {
		this.february = february;
	}

	public Double getMarch() {
		return march;
	}

	public void setMarch(Double march) {
		this.march = march;
	}
	
	public Double getApril() {
		return april;
	}

	public void setApril(Double april) {
		this.april = april;
	}

	public Double getMay() {
		return may;
	}

	public void setMay(Double may) {
		this.may = may;
	}
	
	public Double getJune() {
		return june;
	}

	public void setJune(Double june) {
		this.june = june;
	}
	
	public Double getJuly() {
		return july;
	}

	public void setJuly(Double july) {
		this.july = july;
	}
	
	public Double getAugust() {
		return august;
	}

	public void setAugust(Double august) {
		this.august = august;
	}
	
	public Double getSeptember() {
		return september;
	}

	public void setSeptember(Double september) {
		this.september = september;
	}
	public Double getOctober() {
		return october;
	}

	public void setOctober(Double october) {
		this.october = october;
	}
	public Double getNovember() {
		return november;
	}

	public void setNovember(Double november) {
		this.november = november;
	}
	public Double getDecember() {
		return december;
	}

	public void setDecember(Double december) {
		this.december = december;
	}

	@Column(length=16)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@ManyToOne
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public MonthlyInformationProcess getMonthlyInformationProcess() {
		return monthlyInformationProcess;
	}

	public void setMonthlyInformationProcess(MonthlyInformationProcess monthlyInformationProcess) {
		this.monthlyInformationProcess = monthlyInformationProcess;
	}

}

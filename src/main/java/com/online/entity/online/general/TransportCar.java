package com.online.entity.online.general;

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
import com.online.exportpdf.DataBean;
import com.online.util.SpringUtils;

/**
 * 普查表运输车
 */
@EntityDescription(caption="普查表运输车")
@Entity
@Table(name="ol_transport_car")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_transport_car")
public class TransportCar extends BaseEntity<Integer> implements DataBean{

	private static final long serialVersionUID = 9222660567026914264L;

	/**
	 * 运输物料名称
	 */
	@ColumnDescription(caption="运输物料名称")
	private String materialName;
	
	/**
	 * 运输车排放标准
	 */
	@ColumnDescription(caption="运输车排放标准")
	private String dischargeStandard;
	
	/**
	 * 数量
	 */
	@ColumnDescription(caption="数量")
	private Integer num;
	
	
	/**
	 * 年份
	 */
	@ColumnDescription(caption="年份")
	private String year;
	
	
	
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
	 * 平均单次运距（km）
	 */
	@ColumnDescription(caption="平均单次运距（km）")
	private Double avgDistance;
	
	/**
	 * 平均载重（吨）
	 */
	@ColumnDescription(caption="平均载重（吨）")
	private Double avgLoad;
	
	
	/**
	 * 运输车辆类型
	 */
	@ColumnDescription(caption="运输车辆类型")
	private String carType;
	
	
	/**
	 * 燃料类型
	 */
	@ColumnDescription(caption="燃料类型")
	private String fuelType;
	
	/**
	 * 运输车次（次）
	 */
	@ColumnDescription(caption="运输车次（次）")
	private Double transportNumber;
	
	
	/**
	 * 额定功率(kw)
	 */
	@ColumnDescription(caption="额定功率(kw)")
	private Double ratedPower;
	
	/**
	 * 平均运行功率(kw)
	 */
	@ColumnDescription(caption="平均运行功率(kw)")
	private Double avgPower;
	
	/**
	 * 单车油耗量(L/km)
	 */
	@ColumnDescription(caption="单车油耗量(L/km)")
	private Double oilWear;
	
	/**
	 * 额定载重(t)
	 */
	@ColumnDescription(caption="额定载重(t)")
	private Double ratedLoad;
	
	
	
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
	 * 主要来源地或目的地
	 */
	@ColumnDescription(caption="主要来源地或目的地")
	private String source;
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '运输物料名称'")
	public String getMaterialName() {
		return materialName;
	}


	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '运输车排放标准'")
	public String getDischargeStandard() {
		return dischargeStandard;
	}


	public void setDischargeStandard(String dischargeStandard) {
		this.dischargeStandard = dischargeStandard;
	}

	@Column(columnDefinition="Integer COMMENT '数量'")
	public Integer getNum() {
		return num;
	}


	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '年份'")
	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}

	@Column(columnDefinition="DOUBLE COMMENT '平均单次运距（km）'")
	public Double getAvgDistance() {
		return avgDistance;
	}


	public void setAvgDistance(Double avgDistance) {
		this.avgDistance = avgDistance;
	}

	@Column(columnDefinition="DOUBLE COMMENT '平均载重（吨）'")
	public Double getAvgLoad() {
		return avgLoad;
	}


	public void setAvgLoad(Double avgLoad) {
		this.avgLoad = avgLoad;
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

	
	


	@Column(columnDefinition="VARCHAR(255) COMMENT '运输车辆类型'")
	public String getCarType() {
		return carType;
	}


	public void setCarType(String carType) {
		this.carType = carType;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '燃料类型'")
	public String getFuelType() {
		return fuelType;
	}


	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	@Column(columnDefinition="DOUBLE COMMENT '运输车次（次）'")
	public Double getTransportNumber() {
		return transportNumber;
	}


	public void setTransportNumber(Double transportNumber) {
		this.transportNumber = transportNumber;
	}

	@Column(columnDefinition="DOUBLE COMMENT '额定功率(kw)'")
	public Double getRatedPower() {
		return ratedPower;
	}


	public void setRatedPower(Double ratedPower) {
		this.ratedPower = ratedPower;
	}

	@Column(columnDefinition="DOUBLE COMMENT '平均运行功率(kw)'")
	public Double getAvgPower() {
		return avgPower;
	}


	public void setAvgPower(Double avgPower) {
		this.avgPower = avgPower;
	}

	@Column(columnDefinition="DOUBLE COMMENT '单车油耗量(L/km)'")
	public Double getOilWear() {
		return oilWear;
	}


	public void setOilWear(Double oilWear) {
		this.oilWear = oilWear;
	}

	@Column(columnDefinition="DOUBLE COMMENT '额定载重(t)'")
	public Double getRatedLoad() {
		return ratedLoad;
	}


	public void setRatedLoad(Double ratedLoad) {
		this.ratedLoad = ratedLoad;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '主要来源地或目的地'")
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}


	@PrePersist
	public void initProjectEnterpise(){
		project = SpringUtils.getCurrentProject();
		enterprise = SpringUtils.getCurrentEnterprise();
	}

	
}

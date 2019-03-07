package com.online.entity.online.a5businessbureau;

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
 * 市商务局-加油站/加气站信息
 */
@EntityDescription(caption="加油站/加气站信息")
@Entity
@Table(name="ol_petrol_gas_station")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_petrol_gas_station")
public class PetrolGasStation extends BaseEntity<Integer> {

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
	 * 行政区
	 */
	@ColumnDescription(caption="01_行政区")
	private String city;
	
	/**
	 * 详细地址
	 */
	@ColumnDescription(caption="02_详细地址")
	private String houseNumber;
	
	/**
	 * 中心经度
	 */
	@ColumnDescription(caption="03_中心经度")
	private Double longitude;
	
	/**
	 * 中心纬度
	 */
	@ColumnDescription(caption="04_中心纬度")
	private Double latitude;
	
	
	/**
	 * 油品类型
	 */
	@ColumnDescription(caption="05_油品类型")
	private String oilType;
	
	/**
	 * 年销售量（吨）
	 */
	@ColumnDescription(caption="06_年销售量（吨）")
	private Double annualSell;
	
	/**
	 * 油品标准
	 */
	@ColumnDescription(caption="07_油品标准")
	private String oilStandard;
	
	/**
	 * 地下储罐容量（立方米）
	 */
	@ColumnDescription(caption="08_地下储罐容量（立方米）")
	private Integer undergroundStorage;
	
	/**
	 * 补给频率（次/月）
	 */
	@ColumnDescription(caption="09_补给频率（次/月）")
	private Integer supplyFrequency;
	
	/**
	 * 是否安装油气回收装置
	 */
	@ColumnDescription(caption="10_是否安装油气回收装置")
	private String isOilRecycle;
	
	/**
	 * 油气回收率（%）
	 */
	@ColumnDescription(caption="11_油气回收率（%）")
	private Double oilRecycleRate;
	
	
	
	
	
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '行政区'")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '详细地址'")
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	@Column(columnDefinition="DOUBLE COMMENT '中心经度'")
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	@Column(columnDefinition="DOUBLE COMMENT '中心纬度'")
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '油品类型'")
	public String getOilType() {
		return oilType;
	}
	public void setOilType(String oilType) {
		this.oilType = oilType;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '装卸车过程是否已安装油气回收'")
	public String getIsOilRecycle() {
		return isOilRecycle;
	}
	public void setIsOilRecycle(String isOilRecycle) {
		this.isOilRecycle = isOilRecycle;
	}
	
	
	@Column(columnDefinition="DOUBLE COMMENT '年销售量（吨）'")
	public Double getAnnualSell() {
		return annualSell;
	}
	public void setAnnualSell(Double annualSell) {
		this.annualSell = annualSell;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '油品标准'")
	public String getOilStandard() {
		return oilStandard;
	}
	public void setOilStandard(String oilStandard) {
		this.oilStandard = oilStandard;
	}
	@Column(columnDefinition="Int COMMENT '地下储罐容量（立方米）'")
	public Integer getUndergroundStorage() {
		return undergroundStorage;
	}
	public void setUndergroundStorage(Integer undergroundStorage) {
		this.undergroundStorage = undergroundStorage;
	}
	@Column(columnDefinition="Int COMMENT '补给频率（次/月）'")
	public Integer getSupplyFrequency() {
		return supplyFrequency;
	}
	public void setSupplyFrequency(Integer supplyFrequency) {
		this.supplyFrequency = supplyFrequency;
	}
	@Column(columnDefinition="DOUBLE COMMENT '油气回收率（%）'")
	public Double getOilRecycleRate() {
		return oilRecycleRate;
	}
	public void setOilRecycleRate(Double oilRecycleRate) {
		this.oilRecycleRate = oilRecycleRate;
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

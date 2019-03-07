package com.online.entity.online.a4fooddrugbureau;

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
 * 市食药监局-餐饮行业信息
 */
@EntityDescription(caption="餐饮行业信息")
@Entity
@Table(name="ol_food_drug_catering")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_food_drug_catering")
public class FoodDrugCatering extends BaseEntity<Integer> {

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
	 * 经营场所面积（平方米）
	 */
	@ColumnDescription(caption="05_经营场所面积（平方米）")
	private Double area;
	
	/**
	 * 固定灶头数（个）
	 */
	@ColumnDescription(caption="06_固定灶头数（个）")
	private Integer cookingRange;
	
	/**
	 * 燃料类型
	 */
	@ColumnDescription(caption="07_燃料类型")
	private String fuelType;
	
	/**
	 * 年燃料消耗量（吨/立方米/度）
	 */
	@ColumnDescription(caption="08_年燃料消耗量（吨/立方米/度）")
	private Double fuelConsumption;
	
	/**
	 * 单个灶头平均排风量（立方米/小时）
	 */
	@ColumnDescription(caption="09_单个灶头平均排风量（立方米/小时）")
	private Double exhaustAir;
	
	
	
	
	
	
	
	
	
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
	
	@Column(columnDefinition="DOUBLE COMMENT '经营场所面积（平方米）'")
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	@Column(columnDefinition="Int COMMENT '固定灶头数（个）'")
	public Integer getCookingRange() {
		return cookingRange;
	}
	public void setCookingRange(Integer cookingRange) {
		this.cookingRange = cookingRange;
	}
	@Column(columnDefinition="DOUBLE COMMENT '单个灶头平均排风量（立方米/小时）'")
	public Double getExhaustAir() {
		return exhaustAir;
	}
	public void setExhaustAir(Double exhaustAir) {
		this.exhaustAir = exhaustAir;
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

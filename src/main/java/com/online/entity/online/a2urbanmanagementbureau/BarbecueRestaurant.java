package com.online.entity.online.a2urbanmanagementbureau;

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
 * 城管局-全市烧烤店统计信息
 */
@EntityDescription(caption="全市烧烤店统计信息")
@Entity
@Table(name="ol_barbecue_restaurant")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_barbecue_restaurant")
public class BarbecueRestaurant extends BaseEntity<Integer> {

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
	 * 联系人
	 */
	@ColumnDescription(caption="05_联系人")
	private String contacts;
	
	/**
	 * 联系号码
	 */
	@ColumnDescription(caption="06_联系号码")
	private String contactNumber;
	
	/**
	 * 是否露天烧烤
	 */
	@ColumnDescription(caption="07_是否露天烧烤")
	private String isOpenBarbecue;
	
	/**
	 * 每年经营天数（天）
	 */
	@ColumnDescription(caption="08_每年经营天数（天）")
	private Integer annualBusinessDays;
	
	/**
	 * 每日经营时间（时）
	 */
	@ColumnDescription(caption="09_每日经营时间（时）")
	private String manageHour;
	
	
	/**
	 * 燃料类型
	 */
	@ColumnDescription(caption="10_燃料类型")
	private String fuelType;
	
	/**
	 * 燃料消耗量
	 */
	@ColumnDescription(caption="11_燃料消耗量")
	private Double fuelConsumption;
	
	/**
	 * 燃料消耗量单位
	 */
	@ColumnDescription(caption="12_燃料单位")
	private String fuelUnit;
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '联系人'")
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '联系电话'")
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
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
	@Column(columnDefinition="VARCHAR(255) COMMENT '是否露天烧烤'")
	public String getIsOpenBarbecue() {
		return isOpenBarbecue;
	}
	public void setIsOpenBarbecue(String isOpenBarbecue) {
		this.isOpenBarbecue = isOpenBarbecue;
	}
	@Column(columnDefinition="Int COMMENT '每年经营天数（天）'")
	public Integer getAnnualBusinessDays() {
		return annualBusinessDays;
	}
	public void setAnnualBusinessDays(Integer annualBusinessDays) {
		this.annualBusinessDays = annualBusinessDays;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '每日经营时间（时）'")
	public String getManageHour() {
		return manageHour;
	}
	public void setManageHour(String manageHour) {
		this.manageHour = manageHour;
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
	@Column(columnDefinition="VARCHAR(255) COMMENT '燃料单位'")
	public String getFuelUnit() {
		return fuelUnit;
	}
	public void setFuelUnit(String fuelUnit) {
		this.fuelUnit = fuelUnit;
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

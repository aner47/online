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
 * 市商务局-储油库信息
 */
@EntityDescription(caption="储油库信息")
@Entity
@Table(name="ol_oil_storage")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_oil_storage")
public class OilStorage extends BaseEntity<Integer> {

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
	 * 年总储量（吨）
	 */
	@ColumnDescription(caption="06_年总储量（吨）")
	private Double annualTotal;
	
	/**
	 * 储罐类型
	 */
	@ColumnDescription(caption="07_储罐类型")
	private String storageType;
	
	/**
	 * 储存周期（天）
	 */
	@ColumnDescription(caption="08_储存周期（天）")
	private Integer storageCycle;
	
	/**
	 * 装卸车过程是否已安装油气回收
	 */
	@ColumnDescription(caption="09_装卸车过程是否已安装油气回收")
	private String isOilRecycle;
	
	
	
	
	
	
	
	
	
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
	@Column(columnDefinition="DOUBLE COMMENT '年总储量（吨）'")
	public Double getAnnualTotal() {
		return annualTotal;
	}
	public void setAnnualTotal(Double annualTotal) {
		this.annualTotal = annualTotal;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '储罐类型'")
	public String getStorageType() {
		return storageType;
	}
	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}
	@Column(columnDefinition="Int COMMENT '储存周期（天）'")
	public Integer getStorageCycle() {
		return storageCycle;
	}
	public void setStorageCycle(Integer storageCycle) {
		this.storageCycle = storageCycle;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '装卸车过程是否已安装油气回收'")
	public String getIsOilRecycle() {
		return isOilRecycle;
	}
	public void setIsOilRecycle(String isOilRecycle) {
		this.isOilRecycle = isOilRecycle;
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

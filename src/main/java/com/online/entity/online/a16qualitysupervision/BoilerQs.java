package com.online.entity.online.a16qualitysupervision;

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
 * 市质监局-锅炉信息表
 */
@EntityDescription(caption="锅炉信息")
@Entity
@Table(name="ol_boiler_qs")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_boiler_qs")
public class BoilerQs extends BaseEntity<Integer> {

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
	 * 所在地
	 */
	@ColumnDescription(caption="01_所在地")
	private String palce;
	
	/**
	 * 行政区
	 */
	@ColumnDescription(caption="02_行政区")
	private String city;
	
	/**
	 * 详细地址
	 */
	@ColumnDescription(caption="03_详细地址")
	private String houseNumber;
	
	/**
	 * 中心经度
	 */
	@ColumnDescription(caption="04_中心经度")
	private Double longitude;
	
	/**
	 * 中心纬度
	 */
	@ColumnDescription(caption="05_中心纬度")
	private Double latitude;
	
	/**
	 * 锅炉型号
	 */
	@ColumnDescription(caption="06_锅炉型号")
	private String boilerModel;
	/**
	 * 燃料类型
	 */
	@ColumnDescription(caption="07_燃料类型")
	private String fuelType;
	/**
	 * 蒸吨数
	 */
	@ColumnDescription(caption="08_蒸吨数")
	private Double steamTon;
	/**
	 * 上次检测日期
	 */
	@ColumnDescription(caption="09_上次检测日期")
	private String lastExamineDate;
	/**
	 * 下次检测日期
	 */
	@ColumnDescription(caption="10_下次检测日期")
	private String nextExamineDate;
	
	
	
	
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
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '所在地'")
	public String getPalce() {
		return palce;
	}
	public void setPalce(String palce) {
		this.palce = palce;
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
	@Column(columnDefinition="VARCHAR(255) COMMENT '锅炉型号'")
	public String getBoilerModel() {
		return boilerModel;
	}
	public void setBoilerModel(String boilerModel) {
		this.boilerModel = boilerModel;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '燃料类型'")
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	@Column(columnDefinition="DOUBLE COMMENT '蒸吨数'")
	public Double getSteamTon() {
		return steamTon;
	}
	public void setSteamTon(Double steamTon) {
		this.steamTon = steamTon;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '上次检测日期'")
	public String getLastExamineDate() {
		return lastExamineDate;
	}
	public void setLastExamineDate(String lastExamineDate) {
		this.lastExamineDate = lastExamineDate;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '下次检测日期'")
	public String getNextExamineDate() {
		return nextExamineDate;
	}
	public void setNextExamineDate(String nextExamineDate) {
		this.nextExamineDate = nextExamineDate;
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

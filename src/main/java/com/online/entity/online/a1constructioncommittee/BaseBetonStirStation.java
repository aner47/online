package com.online.entity.online.a1constructioncommittee;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
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
 * 住建委-混凝土搅拌站信息
 */
@EntityDescription(caption="混凝土搅拌站信息")
@Entity
@Table(name="ol_beton_stir_station")
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING,name="dtype")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_beton_stir_station")
public class BaseBetonStirStation extends BaseEntity<Integer> {

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
	 * 产能（立方米/月）
	 */
	@ColumnDescription(caption="05_产能（立方米/月）")
	private Double capacity;
	
	/**
	 * 占地面积（亩）
	 */
	@ColumnDescription(caption="06_占地面积（亩）")
	private Double floorSpace;
	
	
	/**
	 * 料场抑尘措施
	 */
	@ColumnDescription(caption="07_料场抑尘措施")
	private String stockgroundDustMeasures;
	
	/**
	 * 上料口抑尘措施
	 */
	@ColumnDescription(caption="08_上料口抑尘措施")
	private String feedinletDustMeasures;
	/**
	 * 粉料筒仓上部是否配套高效布袋除尘器
	 */
	@ColumnDescription(caption="09_粉料筒仓上部是否配套高效布袋除尘器")
	private String isBagTypeDuster;
	
	
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
	
	
	@Column(columnDefinition="DOUBLE COMMENT '产能（立方米/月）'")
	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}

	@Column(columnDefinition="DOUBLE COMMENT '占地面积（亩）'")
	public Double getFloorSpace() {
		return floorSpace;
	}

	public void setFloorSpace(Double floorSpace) {
		this.floorSpace = floorSpace;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '料场抑尘措施'")
	public String getStockgroundDustMeasures() {
		return stockgroundDustMeasures;
	}

	public void setStockgroundDustMeasures(String stockgroundDustMeasures) {
		this.stockgroundDustMeasures = stockgroundDustMeasures;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '上料口抑尘措施'")
	public String getFeedinletDustMeasures() {
		return feedinletDustMeasures;
	}

	public void setFeedinletDustMeasures(String feedinletDustMeasures) {
		this.feedinletDustMeasures = feedinletDustMeasures;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '粉料筒仓上部是否配套高效布袋除尘器'")
	public String getIsBagTypeDuster() {
		return isBagTypeDuster;
	}

	public void setIsBagTypeDuster(String isBagTypeDuster) {
		this.isBagTypeDuster = isBagTypeDuster;
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

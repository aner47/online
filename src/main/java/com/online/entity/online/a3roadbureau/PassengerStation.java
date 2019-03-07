package com.online.entity.online.a3roadbureau;

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
 * 交通局-客运站/货运物流中心
 */
@EntityDescription(caption="客运站/货运物流中心")
@Entity
@Table(name="ol_passenger_station")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_passenger_station")
public class PassengerStation extends BaseEntity<Integer> {

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
	 * 行政区划
	 */
	@ColumnDescription(caption="01_行政区划")
	private String city;
	
	/**
	 * 详细地址
	 */
	@ColumnDescription(caption="02_详细地址")
	private String houseNumber;
	
	/**
	 * 占地面积
	 */
	@ColumnDescription(caption="03_占地面积")
	private Double area;
	
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
	 * 全年进/出车辆次数
	 */
	@ColumnDescription(caption="06_全年进/出车辆次数")
	private Integer yearInout;
	
	
	
	/**
	 * 每天进/出车辆次数
	 */
	@ColumnDescription(caption="07_每天进/出车辆次数")
	private Integer dayInout;
	
	/**
	 * 车辆类型
	 */
	@ColumnDescription(caption="08_车辆类型")
	private String carType;
	
	/**
	 * 燃油类型
	 */
	@ColumnDescription(caption="09_燃油类型")
	private String oilType;
	/**
	 * 车辆平均停留时间
	 */
	@ColumnDescription(caption="10_车辆平均停留时间")
	private String stayTime;
	
	
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
	@Column(columnDefinition="DOUBLE COMMENT '占地面积'")
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
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
	@Column(columnDefinition="Int COMMENT '全年进/出车辆次数'")
	public Integer getYearInout() {
		return yearInout;
	}
	public void setYearInout(Integer yearInout) {
		this.yearInout = yearInout;
	}
	@Column(columnDefinition="Int COMMENT '每天进/出车辆次数'")
	public Integer getDayInout() {
		return dayInout;
	}
	public void setDayInout(Integer dayInout) {
		this.dayInout = dayInout;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '车辆类型'")
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '燃油类型'")
	public String getOilType() {
		return oilType;
	}
	public void setOilType(String oilType) {
		this.oilType = oilType;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '车辆平均停留时间'")
	public String getStayTime() {
		return stayTime;
	}
	public void setStayTime(String stayTime) {
		this.stayTime = stayTime;
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

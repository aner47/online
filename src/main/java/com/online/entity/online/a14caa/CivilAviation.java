package com.online.entity.online.a14caa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.entity.online.Enterprise;
import com.online.entity.online.Project;

/**
 * 黄花机场-民航飞机信息表
 */
@EntityDescription(caption="民航飞机信息表")
@Entity
@Table(name="ol_civil_aviation")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_civil_aviation")
public class CivilAviation extends BaseEntity<Integer> {

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
	 * 详细地址
	 */
	@ColumnDescription(caption="详细地址")
	private String houseNumber;
	
	/**
	 * 中心经度
	 */
	@ColumnDescription(caption="中心经度")
	private Double longitude;
	
	/**
	 * 中心纬度
	 */
	@ColumnDescription(caption="中心纬度")
	private Double latitude;
	
	
	/**
	 * 客运量（万人次）
	 */
	@ColumnDescription(caption="客运量（万人次）")
	private Double passengerCapacity;
	
	/**
	 * 货运量（万吨）
	 */
	@ColumnDescription(caption="货运量（万吨）")
	private Double commodityCapacity;
	
	/**
	 * 总起降次数
	 */
	@ColumnDescription(caption="总起降次数")
	private Integer totalOffLand;
	
	/**
	 * 一月
	 */
	@ColumnDescription(caption="一月")
	private Integer january;
	
	/**
	 * 二月
	 */
	@ColumnDescription(caption="二月")
	private Integer february;
	
	/**
	 * 三月
	 */
	@ColumnDescription(caption="三月")
	private Integer march;
	
	/**
	 * 四月
	 */
	@ColumnDescription(caption="四月")
	private Integer april;
	
	/**
	 * 五月
	 */
	@ColumnDescription(caption="五月")
	private Integer may;
	
	/**
	 * 六月
	 */
	@ColumnDescription(caption="六月")
	private Integer june; 
	
	/**
	 * 七月
	 */
	@ColumnDescription(caption="七月")
	private Integer july;
	
	/**
	 * 八月
	 */
	@ColumnDescription(caption="八月")
	private Integer august; 
	
	/**
	 * 九月
	 */
	@ColumnDescription(caption="九月")
	private Integer september; 
	
	/**
	 * 十月
	 */
	@ColumnDescription(caption="十月")
	private Integer october; 
	
	/**
	 * 十一月
	 */
	@ColumnDescription(caption="十一月")
	private Integer november;
	
	/**
	 * 十二月
	 */
	@ColumnDescription(caption="十二月")
	private Integer december;
	
	
	private List<AirplaneType> airplaneTypes;
	
	
	
	
	@JsonIgnore
	@OneToMany(mappedBy="civilAviation",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<AirplaneType> getAirplaneTypes() {
		return airplaneTypes;
	}
	public void setAirplaneTypes(List<AirplaneType> airplaneTypes) {
		this.airplaneTypes = airplaneTypes;
	}
	/**
	 * A300
	 *//*
	@ColumnDescription(caption="A300")
	private Double A300;
	*//**
	 * A310
	 *//*
	@ColumnDescription(caption="A310")
	private Double A310;
	*//**
	 * A319
	 *//*
	@ColumnDescription(caption="A319")
	private Double A319;
	*//**
	 * A320
	 *//*
	@ColumnDescription(caption="A320")
	private Double A320;
	*//**
	 * A321
	 *//*
	@ColumnDescription(caption="A321")
	private Double A321;
	*//**
	 * A330
	 *//*
	@ColumnDescription(caption="A330")
	private Double A330;
	*//**
	 * A340
	 *//*
	@ColumnDescription(caption="A340")
	private Double A340;
	*//**
	 * B707
	 *//*
	@ColumnDescription(caption="B707")
	private Double B707;
	*//**
	 * B727
	 *//*
	@ColumnDescription(caption="B727")
	private Double B727;
	*//**
	 * B737
	 *//*
	@ColumnDescription(caption="B737")
	private Double B737;
	*//**
	 * B747
	 *//*
	@ColumnDescription(caption="B747")
	private Double B747;
	*//**
	 * B757
	 *//*
	@ColumnDescription(caption="B757")
	private Double B757;
	*//**
	 * B767
	 *//*
	@ColumnDescription(caption="B767")
	private Double B767;
	*//**
	 * B777
	 *//*
	@ColumnDescription(caption="B777")
	private Double B777;
	*//**
	 * CL60
	 *//*
	@ColumnDescription(caption="CL60")
	private Double CL60;
	*//**
	 * DC10
	 *//*
	@ColumnDescription(caption="DC10")
	private Double DC10;
	*//**
	 * DC8
	 *//*
	@ColumnDescription(caption="DC8")
	private Double DC8;
	*//**
	 * DC9
	 *//*
	@ColumnDescription(caption="DC9")
	private Double DC9;
	*//**
	 * DF3
	 *//*
	@ColumnDescription(caption="DF3")
	private Double DF3;
	*//**
	 * F100
	 *//*
	@ColumnDescription(caption="F100")
	private Double F100;
	*//**
	 * GRJ
	 *//*
	@ColumnDescription(caption="GRJ")
	private Double GRJ;
	*//**
	 * L1011
	 *//*
	@ColumnDescription(caption="L1011")
	private Double L1011;
	*//**
	 * LRJ
	 *//*
	@ColumnDescription(caption="LRJ")
	private Double LRJ;
	*//**
	 * MD11
	 *//*
	@ColumnDescription(caption="MD11")
	private Double MD11;
	
	*//**
	 * MD80
	 *//*
	@ColumnDescription(caption="MD80")
	private Double MD80;
	*//**
	 * MD90
	 *//*
	@ColumnDescription(caption="MD90")
	private Double MD90;
	*//**
	 * 其他
	 *//*
	@ColumnDescription(caption="其他")
	private Double other;
	*/
	
	
	
	
	
	
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
	@Column(columnDefinition="DOUBLE COMMENT '客运量（万人次）'")
	public Double getPassengerCapacity() {
		return passengerCapacity;
	}
	public void setPassengerCapacity(Double passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}
	@Column(columnDefinition="DOUBLE COMMENT '货运量（万吨）'")
	public Double getCommodityCapacity() {
		return commodityCapacity;
	}
	public void setCommodityCapacity(Double commodityCapacity) {
		this.commodityCapacity = commodityCapacity;
	}
	
	@Column(columnDefinition="Int COMMENT '一月'")
	public Integer getJanuary() {
		return january;
	}
	public void setJanuary(Integer january) {
		this.january = january;
	}
	@Column(columnDefinition="Int COMMENT '二月'")
	public Integer getFebruary() {
		return february;
	}
	public void setFebruary(Integer february) {
		this.february = february;
	}
	@Column(columnDefinition="Int COMMENT '三月'")
	public Integer getMarch() {
		return march;
	}
	public void setMarch(Integer march) {
		this.march = march;
	}
	@Column(columnDefinition="Int COMMENT '四月'")
	public Integer getApril() {
		return april;
	}
	public void setApril(Integer april) {
		this.april = april;
	}
	@Column(columnDefinition="Int COMMENT '五月'")
	public Integer getMay() {
		return may;
	}
	public void setMay(Integer may) {
		this.may = may;
	}
	@Column(columnDefinition="Int COMMENT '六月'")
	public Integer getJune() {
		return june;
	}
	public void setJune(Integer june) {
		this.june = june;
	}
	@Column(columnDefinition="Int COMMENT '七月'")
	public Integer getJuly() {
		return july;
	}
	public void setJuly(Integer july) {
		this.july = july;
	}
	@Column(columnDefinition="Int COMMENT '八月'")
	public Integer getAugust() {
		return august;
	}
	public void setAugust(Integer august) {
		this.august = august;
	}
	@Column(columnDefinition="Int COMMENT '九月'")
	public Integer getSeptember() {
		return september;
	}
	public void setSeptember(Integer september) {
		this.september = september;
	}
	@Column(columnDefinition="Int COMMENT '十月'")
	public Integer getOctober() {
		return october;
	}
	public void setOctober(Integer october) {
		this.october = october;
	}
	@Column(columnDefinition="Int COMMENT '十一月'")
	public Integer getNovember() {
		return november;
	}
	public void setNovember(Integer november) {
		this.november = november;
	}
	@Column(columnDefinition="Int COMMENT '十二月'")
	public Integer getDecember() {
		return december;
	}
	public void setDecember(Integer december) {
		this.december = december;
	}
	
	
	
	
	
	@Column(columnDefinition="Int COMMENT '总起降次数'")
	public Integer getTotalOffLand() {
		return totalOffLand;
	}
	public void setTotalOffLand(Integer totalOffLand) {
		this.totalOffLand = totalOffLand;
	}
	/*@Column(columnDefinition="DOUBLE COMMENT 'A300'")
	public Double getA300() {
		return A300;
	}
	public void setA300(Double a300) {
		A300 = a300;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'A310'")
	public Double getA310() {
		return A310;
	}
	public void setA310(Double a310) {
		A310 = a310;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'A319'")
	public Double getA319() {
		return A319;
	}
	public void setA319(Double a319) {
		A319 = a319;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'A320'")
	public Double getA320() {
		return A320;
	}
	public void setA320(Double a320) {
		A320 = a320;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'A321'")
	public Double getA321() {
		return A321;
	}
	public void setA321(Double a321) {
		A321 = a321;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'A330'")
	public Double getA330() {
		return A330;
	}
	public void setA330(Double a330) {
		A330 = a330;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'A340'")
	public Double getA340() {
		return A340;
	}
	public void setA340(Double a340) {
		A340 = a340;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'B707'")
	public Double getB707() {
		return B707;
	}
	public void setB707(Double b707) {
		B707 = b707;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'B727'")
	public Double getB727() {
		return B727;
	}
	public void setB727(Double b727) {
		B727 = b727;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'B737'")
	public Double getB737() {
		return B737;
	}
	public void setB737(Double b737) {
		B737 = b737;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'B747'")
	public Double getB747() {
		return B747;
	}
	public void setB747(Double b747) {
		B747 = b747;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'B757'")
	public Double getB757() {
		return B757;
	}
	public void setB757(Double b757) {
		B757 = b757;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'B767'")
	public Double getB767() {
		return B767;
	}
	public void setB767(Double b767) {
		B767 = b767;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'B777'")
	public Double getB777() {
		return B777;
	}
	public void setB777(Double b777) {
		B777 = b777;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'CL60'")
	public Double getCL60() {
		return CL60;
	}
	public void setCL60(Double cL60) {
		CL60 = cL60;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'DC10'")
	public Double getDC10() {
		return DC10;
	}
	public void setDC10(Double dC10) {
		DC10 = dC10;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'DC8'")
	public Double getDC8() {
		return DC8;
	}
	public void setDC8(Double dC8) {
		DC8 = dC8;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'DC9'")
	public Double getDC9() {
		return DC9;
	}
	public void setDC9(Double dC9) {
		DC9 = dC9;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'DF3'")
	public Double getDF3() {
		return DF3;
	}
	public void setDF3(Double dF3) {
		DF3 = dF3;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'F100'")
	public Double getF100() {
		return F100;
	}
	public void setF100(Double f100) {
		F100 = f100;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'GRJ'")
	public Double getGRJ() {
		return GRJ;
	}
	public void setGRJ(Double gRJ) {
		GRJ = gRJ;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'L1011'")
	public Double getL1011() {
		return L1011;
	}
	public void setL1011(Double l1011) {
		L1011 = l1011;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'LRJ'")
	public Double getLRJ() {
		return LRJ;
	}
	public void setLRJ(Double lRJ) {
		LRJ = lRJ;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'MD11'")
	public Double getMD11() {
		return MD11;
	}
	public void setMD11(Double mD11) {
		MD11 = mD11;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'MD80'")
	public Double getMD80() {
		return MD80;
	}
	public void setMD80(Double mD80) {
		MD80 = mD80;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'MD90'")
	public Double getMD90() {
		return MD90;
	}
	public void setMD90(Double mD90) {
		MD90 = mD90;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'other'")
	public Double getOther() {
		return other;
	}
	public void setOther(Double other) {
		this.other = other;
	}*/
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

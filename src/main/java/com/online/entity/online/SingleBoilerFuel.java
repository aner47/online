package com.online.entity.online;

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
import com.online.exportpdf.DataBean;
import com.online.util.SpringUtils;

/**
 * 单独锅炉燃料
 * 
 * @author DEV2
 *
 */
@EntityDescription(caption="单独锅炉燃料")
@Entity
@Table(name="ol_single_boiler_fuel")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_single_boiler_fuel")
public class SingleBoilerFuel extends BaseEntity<Integer> implements DataBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9222660567026914264L;

	
	
	@ColumnDescription(caption="锅炉编号")
	private String no;
	
	
	
	/**
	 * 燃料类型
	 */
	@ColumnDescription(caption="燃料类型")
	private String fuelType;
	
	/**
	 * 年消耗量
	 */
	@ColumnDescription(caption="年消耗量")
	private Double annualConsumption;
	
	/**
	 * 单位
	 */
	@ColumnDescription(caption="单位")
	private String unit;
	
	
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
	 * 项目
	 */
	private Project project;
	
	private Enterprise enterprise;
	
	/**
	 * 备注
	 */
	private String description;
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '锅炉编号'")
	public String getNo() {
		return no;
	}


	public void setNo(String no) {
		this.no = no;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '燃料类型'")
	public String getFuelType() {
		return fuelType;
	}


	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	@Column(columnDefinition="DOUBLE COMMENT '年消耗量'")
	public Double getAnnualConsumption() {
		return annualConsumption;
	}


	public void setAnnualConsumption(Double annualConsumption) {
		this.annualConsumption = annualConsumption;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '单位'")
	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(columnDefinition="DOUBLE COMMENT '一月'")
	public Double getJanuary() {
		return january;
	}


	public void setJanuary(Double january) {
		this.january = january;
	}

	@Column(columnDefinition="DOUBLE COMMENT '二月'")
	public Double getFebruary() {
		return february;
	}


	public void setFebruary(Double february) {
		this.february = february;
	}

	@Column(columnDefinition="DOUBLE COMMENT '三月'")
	public Double getMarch() {
		return march;
	}


	public void setMarch(Double march) {
		this.march = march;
	}

	@Column(columnDefinition="DOUBLE COMMENT '四月'")
	public Double getApril() {
		return april;
	}


	public void setApril(Double april) {
		this.april = april;
	}

	@Column(columnDefinition="DOUBLE COMMENT '五月'")
	public Double getMay() {
		return may;
	}


	public void setMay(Double may) {
		this.may = may;
	}

	@Column(columnDefinition="DOUBLE COMMENT '六月'")
	public Double getJune() {
		return june;
	}


	public void setJune(Double june) {
		this.june = june;
	}

	@Column(columnDefinition="DOUBLE COMMENT '七月'")
	public Double getJuly() {
		return july;
	}


	public void setJuly(Double july) {
		this.july = july;
	}

	@Column(columnDefinition="DOUBLE COMMENT '八月'")
	public Double getAugust() {
		return august;
	}


	public void setAugust(Double august) {
		this.august = august;
	}

	@Column(columnDefinition="DOUBLE COMMENT '九月'")
	public Double getSeptember() {
		return september;
	}


	public void setSeptember(Double september) {
		this.september = september;
	}

	@Column(columnDefinition="DOUBLE COMMENT '十月'")
	public Double getOctober() {
		return october;
	}


	public void setOctober(Double october) {
		this.october = october;
	}

	@Column(columnDefinition="DOUBLE COMMENT '十一月'")
	public Double getNovember() {
		return november;
	}


	public void setNovember(Double november) {
		this.november = november;
	}

	@Column(columnDefinition="DOUBLE COMMENT '十二月'")
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




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	@PrePersist
	public void initProjectEnterpise(){
		project = SpringUtils.getCurrentProject();
		enterprise = SpringUtils.getCurrentEnterprise();
	}

	
}

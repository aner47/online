package com.online.entity.online.catering;

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
 * 餐饮
 */
@EntityDescription(caption="餐饮表")
@Entity
@Table(name="ol_catering_base")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_catering_base")
public class CateringBase extends BaseEntity<Integer> {

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
	 * 餐饮类型
	 */
	@ColumnDescription(caption="餐饮类型")
	private String cateringType;
	
	/**
	 * 	全年用电量（度）	
	 */
	@ColumnDescription(caption="全年用电量")
	private Double energyUsed;
	

	/**
	 * 年营业额（万元）
	 */
	@ColumnDescription(caption="年营业额（万元）")
	private Double annualSalesVolume;
	
	/**
	 * 总灶头数（个）
	 */
	@ColumnDescription(caption="总灶头数（个）")
	private Integer kitchenNum;
	
	/**
	 * 营业面积
	 */
	@ColumnDescription(caption="营业面积")
	private Double salesArea;
	
	
	/**
	 * 营业月份
	 */
	@ColumnDescription(caption="营业月份")
	private String salesMonth;
	
	/**
	 * 营业时段
	 */
	@ColumnDescription(caption="营业时段")
	private String salesHour;
	
	
	/**
	 * 调查人
	 */
	@ColumnDescription(caption="调查人")
	private String inquirer;
	
	
	/**
	 * 调查人联系电话
	 */
	@ColumnDescription(caption="调查人联系电话")
	private String inquirerPhone;
	
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '调查人'")
	public String getInquirer() {
		return inquirer;
	}

	public void setInquirer(String inquirer) {
		this.inquirer = inquirer;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '调查人联系电话'")
	public String getInquirerPhone() {
		return inquirerPhone;
	}

	public void setInquirerPhone(String inquirerPhone) {
		this.inquirerPhone = inquirerPhone;
	}
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '餐饮类型'")
	public String getCateringType() {
		return cateringType;
	}


	public void setCateringType(String cateringType) {
		this.cateringType = cateringType;
	}

	@Column(columnDefinition="DOUBLE COMMENT '全年用电量'")
	public Double getEnergyUsed() {
		return energyUsed;
	}


	public void setEnergyUsed(Double energyUsed) {
		this.energyUsed = energyUsed;
	}

	@Column(columnDefinition="DOUBLE COMMENT '年营业额（万元）'")
	public Double getAnnualSalesVolume() {
		return annualSalesVolume;
	}


	public void setAnnualSalesVolume(Double annualSalesVolume) {
		this.annualSalesVolume = annualSalesVolume;
	}

	@Column(columnDefinition="INT(11) COMMENT '总灶头数（个）'")
	public Integer getKitchenNum() {
		return kitchenNum;
	}


	public void setKitchenNum(Integer kitchenNum) {
		this.kitchenNum = kitchenNum;
	}

	@Column(columnDefinition="DOUBLE COMMENT '营业面积'")
	public Double getSalesArea() {
		return salesArea;
	}


	public void setSalesArea(Double salesArea) {
		this.salesArea = salesArea;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '营业月份'")
	public String getSalesMonth() {
		return salesMonth;
	}


	public void setSalesMonth(String salesMonth) {
		this.salesMonth = salesMonth;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '营业时段'")
	public String getSalesHour() {
		return salesHour;
	}


	public void setSalesHour(String salesHour) {
		this.salesHour = salesHour;
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




//	@PrePersist
//	public void initProjectEnterpise(){
//		project = SpringUtils.getCurrentProject();
//		enterprise = SpringUtils.getCurrentEnterprise();
//	}

	
}

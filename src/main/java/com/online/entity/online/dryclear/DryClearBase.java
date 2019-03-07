package com.online.entity.online.dryclear;

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
 * 干洗调查表基本信息
 * 
 * @author DEV2
 *
 */
@EntityDescription(caption="干洗调查表")
@Entity
@Table(name="ol_dry_clear_base")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_dry_clear_base")
public class DryClearBase extends BaseEntity<Integer> {

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
	 * 干洗设备型号
	 */
	@ColumnDescription(caption="干洗设备型号")
	private String equipmentType;
	
	/**
	 * 干洗设备品牌
	 */
	@ColumnDescription(caption="干洗设备品牌")
	private String equipmentModel;
	
	/**
	 * 封闭式台数
	 */
	@ColumnDescription(caption="封闭式台数")
	private String closedNum;
	
	/**
	 * 开启式台数
	 */
	@ColumnDescription(caption="开启式台数")
	private String openNum;
	
	/**
	 * 设备总容量（千克）
	 */
	@ColumnDescription(caption="设备总容量（千克）")
	private String equipmentCapacity;
	
	/**
	 * 废气有机溶剂是否进行回收
	 */
	@ColumnDescription(caption="废气有机溶剂是否进行回收")
	private String isDispose;
	
	/**
	 * 回收方式
	 */
	@ColumnDescription(caption="回收方式")
	private String disposeType;
	
	/**
	 * 处理方式
	 */
	@ColumnDescription(caption="处理方式")
	private String processMode;
	
	/**
	 * 单位
	 */
	@ColumnDescription(caption="单位")
	private String unit;
	
	
	/**
	 * 2016年营业额（万元）
	 */
	@ColumnDescription(caption="年营业额（万元）")
	private Double annualSalesVolume;
	
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
	
	
	
	@Column(columnDefinition="DOUBLE COMMENT '年营业额（万元）'")
	public Double getAnnualSalesVolume() {
		return annualSalesVolume;
	}


	public void setAnnualSalesVolume(Double annualSalesVolume) {
		this.annualSalesVolume = annualSalesVolume;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '干洗设备型号'")
	public String getEquipmentType() {
		return equipmentType;
	}


	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '干洗设备品牌'")
	public String getEquipmentModel() {
		return equipmentModel;
	}


	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '封闭式台数'")
	public String getClosedNum() {
		return closedNum;
	}


	public void setClosedNum(String closedNum) {
		this.closedNum = closedNum;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '开启式台数'")
	public String getOpenNum() {
		return openNum;
	}


	public void setOpenNum(String openNum) {
		this.openNum = openNum;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '设备总容量（千克）'")
	public String getEquipmentCapacity() {
		return equipmentCapacity;
	}


	public void setEquipmentCapacity(String equipmentCapacity) {
		this.equipmentCapacity = equipmentCapacity;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '废气有机溶剂是否进行回收'")
	public String getIsDispose() {
		return isDispose;
	}


	public void setIsDispose(String isDispose) {
		this.isDispose = isDispose;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '回收方式'")
	public String getDisposeType() {
		return disposeType;
	}


	public void setDisposeType(String disposeType) {
		this.disposeType = disposeType;
	}


	

	@Column(columnDefinition="VARCHAR(255) COMMENT '处理方式'")
	public String getProcessMode() {
		return processMode;
	}


	public void setProcessMode(String processMode) {
		this.processMode = processMode;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '单位'")
	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
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

package com.online.entity.online.petrol;

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
 * 加油站调查表
 * 
 * @author DEV2
 *
 */
@EntityDescription(caption="加油站调查表")
@Entity
@Table(name="ol_petrol_station_base")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_petrol_station_base")
public class PetrolStationBase extends BaseEntity<Integer> {

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
	 * 控股集团
	 */
	@ColumnDescription(caption="控股集团")
	private String holdingGroup;
	
	/**
	 * 年度经营额（万元）
	 */
	@ColumnDescription(caption="年度经营额（万元）")
	private String annualSalesVolume;
	
	/**
	 * 占地面积（平方米）
	 */
	@ColumnDescription(caption="占地面积（平方米）")
	private Double occupyArea;
	
	/**
	 * 储罐个数
	 */
	@ColumnDescription(caption="储罐个数")
	private Double storageTinNumber;
	
	/**
	 * 储库面积（平方米）
	 */
	@ColumnDescription(caption="储库面积（平方米）")
	private Double storageArea;
	
	/**
	 * 是否进行
	 */
	@ColumnDescription(caption="是否进行")
	private String isRecycle1;
	
	/**
	 * 油气回收率
	 */
	@ColumnDescription(caption="油气回收率")
	private Double recycleRate1;
	/**
	 * 油气回收率数据来源
	 */
	@ColumnDescription(caption="油气回收率数据来源")
	private String dataSource1;
	
	
	/**
	 * 是否进行
	 */
	@ColumnDescription(caption="是否进行")
	private String isRecycle2;
	
	/**
	 * 油气回收率
	 */
	@ColumnDescription(caption="油气回收率")
	private Double recycleRate2;
	/**
	 * 油气回收率数据来源
	 */
	@ColumnDescription(caption="油气回收率数据来源")
	private String dataSource2;
	
	/**
	 * 是否进行
	 */
	@ColumnDescription(caption="是否进行")
	private String isRecycle3;
	
	/**
	 * 油气回收率
	 */
	@ColumnDescription(caption="油气回收率")
	private Double recycleRate3;
	/**
	 * 油气回收率数据来源
	 */
	@ColumnDescription(caption="油气回收率数据来源")
	private String dataSource3;


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
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '控股集团'")
	public String getHoldingGroup() {
		return holdingGroup;
	}


	public void setHoldingGroup(String holdingGroup) {
		this.holdingGroup = holdingGroup;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '年度经营额（万元）'")
	public String getAnnualSalesVolume() {
		return annualSalesVolume;
	}


	public void setAnnualSalesVolume(String annualSalesVolume) {
		this.annualSalesVolume = annualSalesVolume;
	}

	@Column(columnDefinition="DOUBLE COMMENT '占地面积（平方米）'")
	public Double getOccupyArea() {
		return occupyArea;
	}


	public void setOccupyArea(Double occupyArea) {
		this.occupyArea = occupyArea;
	}

	@Column(columnDefinition="DOUBLE COMMENT '储罐个数'")
	public Double getStorageTinNumber() {
		return storageTinNumber;
	}


	public void setStorageTinNumber(Double storageTinNumber) {
		this.storageTinNumber = storageTinNumber;
	}

	@Column(columnDefinition="DOUBLE COMMENT '储库面积（平方米）'")
	public Double getStorageArea() {
		return storageArea;
	}


	public void setStorageArea(Double storageArea) {
		this.storageArea = storageArea;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '是否进行'")
	public String getIsRecycle1() {
		return isRecycle1;
	}


	public void setIsRecycle1(String isRecycle1) {
		this.isRecycle1 = isRecycle1;
	}

	@Column(columnDefinition="DOUBLE COMMENT '油气回收率'")
	public Double getRecycleRate1() {
		return recycleRate1;
	}


	public void setRecycleRate1(Double recycleRate1) {
		this.recycleRate1 = recycleRate1;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '油气回收率数据来源'")
	public String getDataSource1() {
		return dataSource1;
	}


	public void setDataSource1(String dataSource1) {
		this.dataSource1 = dataSource1;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '是否进行'")
	public String getIsRecycle2() {
		return isRecycle2;
	}


	public void setIsRecycle2(String isRecycle2) {
		this.isRecycle2 = isRecycle2;
	}

	@Column(columnDefinition="DOUBLE COMMENT '油气回收率'")
	public Double getRecycleRate2() {
		return recycleRate2;
	}


	public void setRecycleRate2(Double recycleRate2) {
		this.recycleRate2 = recycleRate2;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '油气回收率数据来源'")
	public String getDataSource2() {
		return dataSource2;
	}


	public void setDataSource2(String dataSource2) {
		this.dataSource2 = dataSource2;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '是否进行'")
	public String getIsRecycle3() {
		return isRecycle3;
	}


	public void setIsRecycle3(String isRecycle3) {
		this.isRecycle3 = isRecycle3;
	}

	@Column(columnDefinition="DOUBLE COMMENT '油气回收率'")
	public Double getRecycleRate3() {
		return recycleRate3;
	}


	public void setRecycleRate3(Double recycleRate3) {
		this.recycleRate3 = recycleRate3;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '油气回收率数据来源'")
	public String getDataSource3() {
		return dataSource3;
	}


	public void setDataSource3(String dataSource3) {
		this.dataSource3 = dataSource3;
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

package com.online.entity.online.petrol;

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
import com.online.entity.online.Enterprise;
import com.online.entity.online.Project;
import com.online.util.SpringUtils;

/**
 * 储罐类型
 * 
 * @author DEV2
 *
 */
@EntityDescription(caption="储罐类型")
@Entity
@Table(name="ol_petrol_storage_type")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_petrol_storage_type")
public class PetrolStorageType extends BaseEntity<Integer> {

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
	 * 储罐类型
	 */
	@ColumnDescription(caption="储罐类型")
	private String storageTinType;
	
	/**
	 * 存储物质名称
	 */
	@ColumnDescription(caption="存储物质名称")
	private String storageMatterName;
	
	/**
	 * 平均工作容量（立方米）
	 */
	@ColumnDescription(caption="平均工作容量（立方米）")
	private Double avgWorkCubage;
	
	/**
	 * 年使用天数
	 */
	@ColumnDescription(caption="年使用天数")
	private Double annualUseDays;
	
	/**
	 * 年填充次数
	 */
	@ColumnDescription(caption="年填充次数")
	private Double annualPadNumber;
	
	/**
	 * 年总存储量
	 */
	@ColumnDescription(caption="年总存储量")
	private Double annualStorageSum;
	/**
	 * 年总存储量单位
	 */
	@ColumnDescription(caption="年总存储量单位")
	private String annualStorageSumUnit;
	
	
	/**
	 * 颜色
	 */
	@ColumnDescription(caption="颜色")
	private String colour;
	
	/**
	 * 外壳
	 */
	@ColumnDescription(caption="外壳")
	private String shell;
	
	
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '年总存储量单位'")
	public String getAnnualStorageSumUnit() {
		return annualStorageSumUnit;
	}

	public void setAnnualStorageSumUnit(String annualStorageSumUnit) {
		this.annualStorageSumUnit = annualStorageSumUnit;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '储罐类型'")
	public String getStorageTinType() {
		return storageTinType;
	}

	public void setStorageTinType(String storageTinType) {
		this.storageTinType = storageTinType;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '存储物质名称'")
	public String getStorageMatterName() {
		return storageMatterName;
	}

	public void setStorageMatterName(String storageMatterName) {
		this.storageMatterName = storageMatterName;
	}
	@Column(columnDefinition="DOUBLE COMMENT '平均工作容量（立方米）'")
	public Double getAvgWorkCubage() {
		return avgWorkCubage;
	}

	public void setAvgWorkCubage(Double avgWorkCubage) {
		this.avgWorkCubage = avgWorkCubage;
	}
	@Column(columnDefinition="DOUBLE COMMENT '年使用天数'")
	public Double getAnnualUseDays() {
		return annualUseDays;
	}

	public void setAnnualUseDays(Double annualUseDays) {
		this.annualUseDays = annualUseDays;
	}
	@Column(columnDefinition="DOUBLE COMMENT '年填充次数'")
	public Double getAnnualPadNumber() {
		return annualPadNumber;
	}

	public void setAnnualPadNumber(Double annualPadNumber) {
		this.annualPadNumber = annualPadNumber;
	}
	@Column(columnDefinition="DOUBLE COMMENT '年总存储量'")
	public Double getAnnualStorageSum() {
		return annualStorageSum;
	}

	public void setAnnualStorageSum(Double annualStorageSum) {
		this.annualStorageSum = annualStorageSum;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '颜色'")
	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '外壳'")
	public String getShell() {
		return shell;
	}

	public void setShell(String shell) {
		this.shell = shell;
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
	@Column(columnDefinition="VARCHAR(255) COMMENT '备注'")
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

package com.online.entity.online;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.util.SpringUtils;

/**
 * 装载信息
 * @author DEV2
 *
 */
@EntityDescription(caption="装载信息")
@Entity
@Table(name="ol_loading_information")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_loading_information")
public class LoadingInformation extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1259687498570849646L;

	/**
	 * 装载环节编号
	 */
	@ColumnDescription(caption="编号")
	private String no;
	
	/**
	 * 作业部/车间
	 */
	@ColumnDescription(caption="作业部/车间")
	private String location;
	
	
	/**
	 * 装车站台编号
	 */
	@ColumnDescription(caption="装车站台编号")
	private String platformNo;
	
	
	/**
	 * 物料类型
	 */
	@ColumnDescription(caption="物料类型")
	private String materialType;
	
	/**
	 * 装载方式
	 */
	@ColumnDescription(caption="装载方式")
	private String loadMethod;
	
	/**
	 * 操作方式
	 */
	@ColumnDescription(caption="操作方式")
	private String operationMethod;
	
	/**
	 * 年装载量
	 */
	@ColumnDescription(caption="年装载量")
	private Double annualLoad;
	
	/**
	 * 物料密度
	 */
	@ColumnDescription(caption="物料密度")
	private Double materialDensity;
	
	/**
	 * 回收技术
	 */
	@ColumnDescription(caption="回收技术")
	private String recycling;
	
	/**
	 * 回收效率（%）
	 */
	@ColumnDescription(caption="回收效率（%）")
	private Double recoveryRate;
	
	/**
	 * 项目
	 */
	private Project project;
	
	/**
	 * 企业
	 */
	private Enterprise enterprise;

	@Column(columnDefinition="VARCHAR(255) COMMENT '编号'")
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '作业部/车间'")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '物料类型'")
	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '装载方式'")
	public String getLoadMethod() {
		return loadMethod;
	}

	public void setLoadMethod(String loadMethod) {
		this.loadMethod = loadMethod;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '操作方式'")
	public String getOperationMethod() {
		return operationMethod;
	}

	public void setOperationMethod(String operationMethod) {
		this.operationMethod = operationMethod;
	}
	@Column(columnDefinition="DOUBLE COMMENT '年装载量'")
	public Double getAnnualLoad() {
		return annualLoad;
	}

	public void setAnnualLoad(Double annualLoad) {
		this.annualLoad = annualLoad;
	}
	@Column(columnDefinition="DOUBLE COMMENT '物料密度'")
	public Double getMaterialDensity() {
		return materialDensity;
	}

	public void setMaterialDensity(Double materialDensity) {
		this.materialDensity = materialDensity;
	}
	@ManyToOne
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '回收技术'")
	public String getRecycling() {
		return recycling;
	}

	public void setRecycling(String recycling) {
		this.recycling = recycling;
	}
	@Column(columnDefinition="DOUBLE COMMENT '回收效率（%）'")
	public Double getRecoveryRate() {
		return recoveryRate;
	}

	public void setRecoveryRate(Double recoveryRate) {
		this.recoveryRate = recoveryRate;
	}
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '装车站台编号'")
	public String getPlatformNo() {
		return platformNo;
	}

	public void setPlatformNo(String platformNo) {
		this.platformNo = platformNo;
	}

	@PrePersist
	protected void init() {
		enterprise = SpringUtils.getCurrentEnterprise();
		project = SpringUtils.getCurrentProject();
	}
	
}

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
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2018年3月26日 下午2:36:09 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return  
	*/
@EntityDescription(caption="污水处理")
@Entity
@Table(name="ol_sewage_dispose")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_sewage_dispose")
public class SewageDispose extends BaseEntity<Integer> implements DataBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 污水类型
	 */
	@ColumnDescription(caption="污水类型")
	private String sewagetype;
	
	/**
	 * 污水排放量(吨)
	 */
	@ColumnDescription(caption="污水排放量(吨)")
	private Double emissionQuantity;
	
	/**
	 * 污水自处理率(%)
	 */
	@ColumnDescription(caption="污水自处理率(%)")
	private Double disposeRate;
	
	/**
	 * 污水处理工艺
	 */
	@ColumnDescription(caption="污水处理工艺")
	private String disposeTechnology;
	
	/**
	 * 治理效率（%）
	 */
	@ColumnDescription(caption="治理效率（%）")
	private Double disposeEfficiency;
	
	/**
	 * 污水处理池是否加盖(是/否)
	 */
	@ColumnDescription(caption="污水处理池是否加盖(是/否)")
	private String isCapping;
	
	/**
	 * 废气是否收集处理(是/否)
	 */
	@ColumnDescription(caption="废气是否收集处理(是/否)")
	private String isGather;
	
	/**
	 * 废气处理方式（请说明）
	 */
	@ColumnDescription(caption="废气处理方式（请说明）")
	private String disposeWay;

	/**
	 * 项目
	 */
	@ColumnDescription(caption="项目")
	private Project project;
	/**
	 * 企业
	 */
	@ColumnDescription(caption="企业")
	private Enterprise enterprise;
	
	/**
	 * 备注
	 */
	@ColumnDescription(caption="备注")
	private String description;
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '污水类型'")
	public String getSewagetype() {
		return sewagetype;
	}

	public void setSewagetype(String sewagetype) {
		this.sewagetype = sewagetype;
	}

	@Column(columnDefinition="DOUBLE COMMENT '污水排放量(吨)'")
	public Double getEmissionQuantity() {
		return emissionQuantity;
	}

	public void setEmissionQuantity(Double emissionQuantity) {
		this.emissionQuantity = emissionQuantity;
	}

	@Column(columnDefinition="DOUBLE COMMENT '污水自处理率(%)'")
	public Double getDisposeRate() {
		return disposeRate;
	}

	public void setDisposeRate(Double disposeRate) {
		this.disposeRate = disposeRate;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '污水处理工艺'")
	public String getDisposeTechnology() {
		return disposeTechnology;
	}

	public void setDisposeTechnology(String disposeTechnology) {
		this.disposeTechnology = disposeTechnology;
	}

	@Column(columnDefinition="DOUBLE COMMENT '治理效率（%）'")
	public Double getDisposeEfficiency() {
		return disposeEfficiency;
	}

	public void setDisposeEfficiency(Double disposeEfficiency) {
		this.disposeEfficiency = disposeEfficiency;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '污水处理池是否加盖(是/否)'")
	public String getIsCapping() {
		return isCapping;
	}

	public void setIsCapping(String isCapping) {
		this.isCapping = isCapping;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '废气是否收集处理(是/否)'")
	public String getIsGather() {
		return isGather;
	}

	public void setIsGather(String isGather) {
		this.isGather = isGather;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '废气处理方式（请说明）'")
	public String getDisposeWay() {
		return disposeWay;
	}

	public void setDisposeWay(String disposeWay) {
		this.disposeWay = disposeWay;
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

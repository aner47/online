package com.online.entity.online;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.util.SpringUtils;
/**
 * 产品信息
 * @author zuozhiping
 *
 */
@EntityDescription(caption="产品")
@Entity
@Table(name="ol_product")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_product")
public class Product extends BaseEntity<Integer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5303420736260366879L;

	/**
	 * 产量计量单位
	 */
	@ColumnDescription(caption="单位")
	private String unit;
	
	/**
	 * 产品名称
	 */
	@ColumnDescription(caption="名称")
	private String name ; 
	
	/**
	 * 年产量
	 */
	@ColumnDescription(caption="年产量")
	private Double yield;
	
	/**
	 * 项目
	 */
	private Integer project;
	
	/**
	 * 企业信息
	 */
	private Integer enterprise;
	
	
	/**
	 * 产品类型
	 */
	@ColumnDescription(caption="产品类型")
	private String productType ; 
	
	/**
	 * 含硫量
	 */
	@ColumnDescription(caption="含硫量")
	private Double fuelSulfurContent;
	
	
	@Column(columnDefinition="DOUBLE COMMENT '含硫量'")
	public Double getFuelSulfurContent() {
		return fuelSulfurContent;
	}

	public void setFuelSulfurContent(Double fuelSulfurContent) {
		this.fuelSulfurContent = fuelSulfurContent;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '产品类型'")
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	@Column(updatable=false)
	public Integer getProject() {
		return project;
	}

	public void setProject(Integer project) {
		this.project = project;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '名称'")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '单位'")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(columnDefinition="DOUBLE COMMENT '年产量'")
	public Double getYield() {
		return yield;
	}

	public void setYield(Double yield) {
		this.yield = yield;
	}
	
	@Column(updatable=false)
	public Integer getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Integer enterprise) {
		this.enterprise = enterprise;
	}

	
	@PrePersist
	private void initEnterprise(){
		setEnterprise(SpringUtils.getPrincipal().getEnterpriseId());
		setProject(SpringUtils.getProjectId());
	}
	

}

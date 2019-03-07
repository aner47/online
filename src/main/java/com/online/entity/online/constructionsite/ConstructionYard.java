package com.online.entity.online.constructionsite;

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
 * 露天堆场信息
 * 
 * @author DEV2
 *
 */
@EntityDescription(caption="露天堆场信息")
@Entity
@Table(name="ol_construction_yard")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_construction_yard")
public class ConstructionYard extends BaseEntity<Integer> {

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
	 * 料堆材料
	 */
	@ColumnDescription(caption="料堆材料")
	private String materials;
	
	/**
	 * 占地面积（平方米）
	 */
	@ColumnDescription(caption="占地面积（平方米）")
	private Double holdArea;
	
	
	/**
	 * 最高高度（米）
	 */
	@ColumnDescription(caption="最高高度（米）")
	private Double height;
	
	
	/**
	 * 日常储存量（吨）
	 */
	@ColumnDescription(caption="日常储存量（吨）")
	private Double stock;
	
	/**
	 * 年物料运载车次（车）
	 */
	@ColumnDescription(caption="年物料运载车次（车）")
	private Double trainNumber;
	
	/**
	 * 单车运载量（吨/车）
	 */
	@ColumnDescription(caption="单车运载量（吨/车）")
	private Double carryingCapacities;
	
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '料堆材料'")
	public String getMaterials() {
		return materials;
	}

	public void setMaterials(String materials) {
		this.materials = materials;
	}
	@Column(columnDefinition="DOUBLE COMMENT '占地面积（平方米）'")
	public Double getHoldArea() {
		return holdArea;
	}

	public void setHoldArea(Double holdArea) {
		this.holdArea = holdArea;
	}
	@Column(columnDefinition="DOUBLE COMMENT '最高高度（米）'")
	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}
	@Column(columnDefinition="DOUBLE COMMENT '日常储存量（吨）'")
	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}
	@Column(columnDefinition="DOUBLE COMMENT '年物料运载车次（车）'")
	public Double getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(Double trainNumber) {
		this.trainNumber = trainNumber;
	}
	@Column(columnDefinition="DOUBLE COMMENT '单车运载量（吨/车）'")
	public Double getCarryingCapacities() {
		return carryingCapacities;
	}

	public void setCarryingCapacities(Double carryingCapacities) {
		this.carryingCapacities = carryingCapacities;
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

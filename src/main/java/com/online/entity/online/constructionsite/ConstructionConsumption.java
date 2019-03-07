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
 * 机械消耗
 * 
 * @author DEV2
 *
 */
@EntityDescription(caption="机械消耗")
@Entity
@Table(name="ol_construction_consumption")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_construction_consumption")
public class ConstructionConsumption extends BaseEntity<Integer> {

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
	 * 机械平均功率（千瓦/辆）
	 */
	@ColumnDescription(caption="机械平均功率（千瓦/辆）")
	private Double avgRate;
	
	/**
	 * 单车油耗量（吨/辆）
	 */
	@ColumnDescription(caption="单车油耗量（吨/辆）")
	private Double avgConsumption;
	/**
	 * 单车油耗量单位
	 */
	@ColumnDescription(caption="单车油耗量单位")
	private String avgConsumptionUnit;
	
	/**
	 * 机械
	 */
	@ColumnDescription(caption="机械")
	private String mechanical;
	

	/**
	 * 油品标准
	 */
	@ColumnDescription(caption="油品标准")
	private String oilStandard;
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '单车油耗量单位'")
	public String getAvgConsumptionUnit() {
		return avgConsumptionUnit;
	}

	public void setAvgConsumptionUnit(String avgConsumptionUnit) {
		this.avgConsumptionUnit = avgConsumptionUnit;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '油品标准'")
	public String getOilStandard() {
		return oilStandard;
	}

	public void setOilStandard(String oilStandard) {
		this.oilStandard = oilStandard;
	}
	@Column(columnDefinition="DOUBLE COMMENT '机械平均功率（千瓦/辆）'")
	public Double getAvgRate() {
		return avgRate;
	}

	public void setAvgRate(Double avgRate) {
		this.avgRate = avgRate;
	}
	@Column(columnDefinition="DOUBLE COMMENT '单车油耗量（吨/辆）'")
	public Double getAvgConsumption() {
		return avgConsumption;
	}

	public void setAvgConsumption(Double avgConsumption) {
		this.avgConsumption = avgConsumption;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '机械'")
	public String getMechanical() {
		return mechanical;
	}

	public void setMechanical(String mechanical) {
		this.mechanical = mechanical;
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

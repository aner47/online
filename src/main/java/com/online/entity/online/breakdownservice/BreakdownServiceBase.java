package com.online.entity.online.breakdownservice;

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
 * 汽修调查表
 * 
 * @author DEV2
 *
 */
@EntityDescription(caption="汽修调查表")
@Entity
@Table(name="ol_breakdown_service_base")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_breakdown_service_base")
public class BreakdownServiceBase extends BaseEntity<Integer> {

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
	 * 喷烘两用房数量（个）
	 */
	@ColumnDescription(caption="喷烘两用房数量（个）")
	private Double sprayBakeHouse;
	
	/**
	 * 单烘房数量（个）
	 */
	@ColumnDescription(caption="单烘房数量（个）")
	private Double bakeHouse;
	
	/**
	 * 年营业额（万元）
	 */
	@ColumnDescription(caption="年营业额（万元）")
	private Double annualSalesVolume;
	
	
	
	/**
	 * 废气是否进行处理
	 */
	@ColumnDescription(caption="废气是否进行处理")
	private String isprocess;
	
	/**
	 * 是否进行预处理
	 */
	@ColumnDescription(caption="是否进行预处理")
	private String ispreprocess;
	
	
	/**
	 * 处理工艺类型
	 */
	@ColumnDescription(caption="处理工艺类型")
	private String processType;
	
	/**
	 * 处理效率（%）
	 */
	@ColumnDescription(caption="处理效率（%）")
	private Double processEfficiency;
	
	
	/**
	 * 活性炭更换周期（天）
	 */
	@ColumnDescription(caption="活性炭更换周期（天）")
	private Integer carbonChangePeriod;
	
	
	
	/**
	 * 废有机溶剂是否进行回收
	 */
	@ColumnDescription(caption="废有机溶剂是否进行回收")
	private String isrecycle;
	
	
	/**
	 * 回收方式
	 */
	@ColumnDescription(caption="回收方式")
	private String recycleMode;
	
	/**
	 * 处理方式
	 */
	@ColumnDescription(caption="处理方式")
	private String processMode;
	
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
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '是否进行预处理'")
	public String getIspreprocess() {
		return ispreprocess;
	}


	public void setIspreprocess(String ispreprocess) {
		this.ispreprocess = ispreprocess;
	}

	@Column(columnDefinition="INT(11) COMMENT '活性炭更换周期（天）'")
	public Integer getCarbonChangePeriod() {
		return carbonChangePeriod;
	}


	public void setCarbonChangePeriod(Integer carbonChangePeriod) {
		this.carbonChangePeriod = carbonChangePeriod;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '废有机溶剂是否进行回收'")
	public String getIsrecycle() {
		return isrecycle;
	}


	public void setIsrecycle(String isrecycle) {
		this.isrecycle = isrecycle;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '回收方式'")
	public String getRecycleMode() {
		return recycleMode;
	}


	public void setRecycleMode(String recycleMode) {
		this.recycleMode = recycleMode;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '处理方式'")
	public String getProcessMode() {
		return processMode;
	}


	public void setProcessMode(String processMode) {
		this.processMode = processMode;
	}

	@Column(columnDefinition="DOUBLE COMMENT '喷烘两用房数量（个）'")
	public Double getSprayBakeHouse() {
		return sprayBakeHouse;
	}


	public void setSprayBakeHouse(Double sprayBakeHouse) {
		this.sprayBakeHouse = sprayBakeHouse;
	}

	@Column(columnDefinition="DOUBLE COMMENT '单烘房数量（个）'")
	public Double getBakeHouse() {
		return bakeHouse;
	}


	public void setBakeHouse(Double bakeHouse) {
		this.bakeHouse = bakeHouse;
	}

	@Column(columnDefinition="DOUBLE COMMENT '年营业额（万元）'")
	public Double getAnnualSalesVolume() {
		return annualSalesVolume;
	}


	public void setAnnualSalesVolume(Double annualSalesVolume) {
		this.annualSalesVolume = annualSalesVolume;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '废气是否进行处理'")
	public String getIsprocess() {
		return isprocess;
	}


	public void setIsprocess(String isprocess) {
		this.isprocess = isprocess;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '处理工艺类型'")
	public String getProcessType() {
		return processType;
	}


	public void setProcessType(String processType) {
		this.processType = processType;
	}

	@Column(columnDefinition="DOUBLE COMMENT '处理效率（%）'")
	public Double getProcessEfficiency() {
		return processEfficiency;
	}


	public void setProcessEfficiency(Double processEfficiency) {
		this.processEfficiency = processEfficiency;
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

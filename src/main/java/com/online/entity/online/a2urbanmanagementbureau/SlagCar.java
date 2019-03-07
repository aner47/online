package com.online.entity.online.a2urbanmanagementbureau;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.entity.online.Project;

/**
 * 城管局	-全市渣土车信息表
 */
@EntityDescription(caption="全市渣土车信息表")
@Entity
@Table(name="ol_slag_car")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_slag_car")
public class SlagCar extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9222660567026914264L;

	
	/**
	 * 项目
	 */
	private Project project;
	
	/**
	 * 备注
	 */
	private String description;
	
	/**
	 * 所属公司
	 */
	@ColumnDescription(caption="所属公司")
	private String companies;
	
	/**
	 * 输出地
	 */
	@ColumnDescription(caption="输出地")
	private String outputPlace;
	
	
	/**
	 * 输入地
	 */
	@ColumnDescription(caption="输入地")
	private String inputPlace;
	
	/**
	 * 平均运输距离
	 */
	@ColumnDescription(caption="平均运输距离")
	private Double avgDistance;
	
	/**
	 * 单车运载量（吨）
	 */
	@ColumnDescription(caption="单车运载量（吨）")
	private Double carryingcapacity;
	
	/**
	 * 每日活动时间
	 */
	@ColumnDescription(caption="每日活动时间")
	private String activityTime;
	
	/**
	 * 月运载次数
	 */
	@ColumnDescription(caption="月运载次数")
	private Integer monthCarryingNum;
	
	/**
	 * 尺寸（长*宽*高）
	 */
	@ColumnDescription(caption="尺寸（长*宽*高）")
	private String size;
	/**
	 *	是否封闭
	 */
	@ColumnDescription(caption="是否封闭")
	private String isClose;
	/**
	 *	出入工地/场地是否冲洗
	 */
	@ColumnDescription(caption="出入工地/场地是否冲洗")
	private String isClean;
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '产能（立方米/月）'")
	public String getCompanies() {
		return companies;
	}

	public void setCompanies(String companies) {
		this.companies = companies;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '输出地'")
	public String getOutputPlace() {
		return outputPlace;
	}

	public void setOutputPlace(String outputPlace) {
		this.outputPlace = outputPlace;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '输入地'")
	public String getInputPlace() {
		return inputPlace;
	}

	public void setInputPlace(String inputPlace) {
		this.inputPlace = inputPlace;
	}
	@Column(columnDefinition="DOUBLE COMMENT '平均运输距离'")
	public Double getAvgDistance() {
		return avgDistance;
	}

	public void setAvgDistance(Double avgDistance) {
		this.avgDistance = avgDistance;
	}
	@Column(columnDefinition="DOUBLE COMMENT '单车运载量（吨）'")
	public Double getCarryingcapacity() {
		return carryingcapacity;
	}

	public void setCarryingcapacity(Double carryingcapacity) {
		this.carryingcapacity = carryingcapacity;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '每日活动时间'")
	public String getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}
	@Column(columnDefinition="Int COMMENT '月运载次数'")
	public Integer getMonthCarryingNum() {
		return monthCarryingNum;
	}

	public void setMonthCarryingNum(Integer monthCarryingNum) {
		this.monthCarryingNum = monthCarryingNum;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '尺寸（长*宽*高）'")
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '是否封闭'")
	public String getIsClose() {
		return isClose;
	}

	public void setIsClose(String isClose) {
		this.isClose = isClose;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '出入工地/场地是否冲洗'")
	public String getIsClean() {
		return isClean;
	}

	public void setIsClean(String isClean) {
		this.isClean = isClean;
	}

	
	
	@ManyToOne
//	@JoinColumn(updatable=false)
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '备注'")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}

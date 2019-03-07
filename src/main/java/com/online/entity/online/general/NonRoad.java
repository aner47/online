package com.online.entity.online.general;

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
import com.online.exportpdf.DataBean;
import com.online.util.SpringUtils;

/**
 * 普查表非道路机械
 */
@EntityDescription(caption="普查表非道路机械")
@Entity
@Table(name="ol_non_road")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_non_road")
public class NonRoad extends BaseEntity<Integer> implements DataBean{
	private static final long serialVersionUID = 9222660567026914264L;
	/**
	 * 非道路机械
	 */
	@ColumnDescription(caption="非道路机械")
	private String nonroadMechanical;
	
	/**
	 * 排放标准
	 */
	@ColumnDescription(caption="排放标准")
	private String dischargeStandard;
	
	/**
	 * 数量
	 */
	@ColumnDescription(caption="数量")
	private String num;
	
	/**
	 * 平均功率（kw）
	 */
	@ColumnDescription(caption="平均功率（kw）")
	private Double avgPower;
	
	/**
	 * 单车油耗量（L/h）
	 */
	@ColumnDescription(caption="单车油耗量（L/h）")
	private Double singleOilConsumption;
	
	
	/**
	 * 燃料类型
	 */
	@ColumnDescription(caption="燃料类型")
	private String fuelType;
	
	
	/**
	 * 额定功率(kw)
	 */
	@ColumnDescription(caption="额定功率(kw)")
	private Double ratedPower;
	
	
	/**
	 * 单机油耗花费（元/月）
	 */
	@ColumnDescription(caption="单机油耗花费（元/月）")
	private Double oilWearExpenditure;
	
	/**
	 * 日均工作小时(小时)
	 */
	@ColumnDescription(caption="日均工作小时(小时)")
	private Double avgDayofhourWork;
	/**
	 * 年均工作天数(天)
	 */
	@ColumnDescription(caption="年均工作天数(天)")
	private Double avgYearofdayWork;
	
	
	/**
	 *自有或租赁
	 */
	@ColumnDescription(caption="自有或租赁")
	private String ownOrLease;
	
	/**
	 * 燃油来源地
	 */
	@ColumnDescription(caption="燃油来源地")
	private String oilSource;
	
	
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
	 * 机械类型
	 */
	private String mechanicalType;
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '机械类型'")
	public String getMechanicalType() {
		return mechanicalType;
	}


	public void setMechanicalType(String mechanicalType) {
		this.mechanicalType = mechanicalType;
	}


	@Column(columnDefinition="VARCHAR(255) COMMENT '非道路机械'")
	public String getNonroadMechanical() {
		return nonroadMechanical;
	}


	public void setNonroadMechanical(String nonroadMechanical) {
		this.nonroadMechanical = nonroadMechanical;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '排放标准'")
	public String getDischargeStandard() {
		return dischargeStandard;
	}


	public void setDischargeStandard(String dischargeStandard) {
		this.dischargeStandard = dischargeStandard;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '数量'")
	public String getNum() {
		return num;
	}


	public void setNum(String num) {
		this.num = num;
	}

	@Column(columnDefinition="DOUBLE COMMENT '平均功率（kw）'")
	public Double getAvgPower() {
		return avgPower;
	}


	public void setAvgPower(Double avgPower) {
		this.avgPower = avgPower;
	}

	@Column(columnDefinition="DOUBLE COMMENT '单车油耗量（L/h）'")
	public Double getSingleOilConsumption() {
		return singleOilConsumption;
	}


	public void setSingleOilConsumption(Double singleOilConsumption) {
		this.singleOilConsumption = singleOilConsumption;
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

	
	


	@Column(columnDefinition="VARCHAR(255) COMMENT '备注'")
	public String getFuelType() {
		return fuelType;
	}


	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	@Column(columnDefinition="DOUBLE COMMENT '额定功率(kw)'")
	public Double getRatedPower() {
		return ratedPower;
	}


	public void setRatedPower(Double ratedPower) {
		this.ratedPower = ratedPower;
	}

	@Column(columnDefinition="DOUBLE COMMENT '单机油耗花费（元/月）'")
	public Double getOilWearExpenditure() {
		return oilWearExpenditure;
	}


	public void setOilWearExpenditure(Double oilWearExpenditure) {
		this.oilWearExpenditure = oilWearExpenditure;
	}

	@Column(columnDefinition="DOUBLE COMMENT '日均工作小时(小时)'")
	public Double getAvgDayofhourWork() {
		return avgDayofhourWork;
	}


	public void setAvgDayofhourWork(Double avgDayofhourWork) {
		this.avgDayofhourWork = avgDayofhourWork;
	}

	@Column(columnDefinition="DOUBLE COMMENT '年均工作天数(天)'")
	public Double getAvgYearofdayWork() {
		return avgYearofdayWork;
	}


	public void setAvgYearofdayWork(Double avgYearofdayWork) {
		this.avgYearofdayWork = avgYearofdayWork;
	}
	
	
	

	@Column(columnDefinition="VARCHAR(255) COMMENT '自有或租赁'")
	public String getOwnOrLease() {
		return ownOrLease;
	}


	public void setOwnOrLease(String ownOrLease) {
		this.ownOrLease = ownOrLease;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '燃油来源地'")
	public String getOilSource() {
		return oilSource;
	}


	public void setOilSource(String oilSource) {
		this.oilSource = oilSource;
	}


	@PrePersist
	public void initProjectEnterpise(){
		project = SpringUtils.getCurrentProject();
		enterprise = SpringUtils.getCurrentEnterprise();
	}

	
}

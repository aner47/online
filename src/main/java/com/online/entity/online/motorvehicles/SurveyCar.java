package com.online.entity.online.motorvehicles;

import java.util.Date;

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
import com.online.util.SpringUtils;

/**
 * 调查车辆
 */
@EntityDescription(caption="调查车辆")
@Entity
@Table(name="ol_survey_car")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_survey_car")
public class SurveyCar extends BaseEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4155144841194254713L;

	/**
	 * 车辆代码/ 品牌型号
	 */
	@ColumnDescription(caption="车辆代码/品牌型号")
	private String  carModel;
	
	/**
	 * 购买日期（年/月/日
	 */
	@ColumnDescription(caption="购买日期（年/月/日")
	private Date  buyDate;
	
	/**
	 * 车辆类型（选项）
	 */
	@ColumnDescription(caption="车辆类型（选项）")
	private String  carType;
	
	/**
	 * 总质量（吨）
	 */
	@ColumnDescription(caption="总质量（吨）")
	private Double  weight;
	/**
	 * 燃料类型（汽油/柴油/其他-填表说明）
	 */
	@ColumnDescription(caption="燃料类型")
	private String  fuelType;
	
	/**
	 * 排放标准（国一/国二/国三/国四/国五）
	 */
	@ColumnDescription(caption="排放标准")
	private String   emissionStandard;
	
	/**
	 * 累积行驶里程（公里）
	 */
	@ColumnDescription(caption="累积行驶里程（公里）")
	private Double   mileage;
	
	
	
	
	
	
	
	
	private Integer project;
	
	/**
	 * 机动车调查表
	 */
	private MotorVehicles motorVehicles;


	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '车辆代码/品牌型号'")
	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	@Column(columnDefinition="DATETIME COMMENT '购买日期（年/月/日'")
	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '车辆类型（选项）'")
	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}
	@Column(columnDefinition="DOUBLE COMMENT '总质量（吨）'")
	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '燃料类型'")
	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '排放标准'")
	public String getEmissionStandard() {
		return emissionStandard;
	}

	public void setEmissionStandard(String emissionStandard) {
		this.emissionStandard = emissionStandard;
	}
	@Column(columnDefinition="DOUBLE COMMENT '累积行驶里程（公里）'")
	public Double getMileage() {
		return mileage;
	}

	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}

	@ManyToOne
	@JoinColumn(name = "motor_vehicles",updatable=false)
	public MotorVehicles getMotorVehicles() {
		return motorVehicles;
	}

	public void setMotorVehicles(MotorVehicles motorVehicles) {
		this.motorVehicles = motorVehicles;
	}

	 
	
	
	
	
	
	
	
	@Column(updatable=false)
	public Integer getProject() {
		return project;
	}

	public void setProject(Integer project) {
		this.project = project;
	}
	
	@PrePersist
	protected void init() {
		project = SpringUtils.getProjectId();
	}

}

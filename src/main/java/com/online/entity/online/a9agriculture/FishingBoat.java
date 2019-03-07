package com.online.entity.online.a9agriculture;

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
 * 市农业委-全市渔船信息
 */
@EntityDescription(caption="全市渔船信息")
@Entity
@Table(name="ol_fishing_boat")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_fishing_boat")
public class FishingBoat extends BaseEntity<Integer> {

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
	 * 船型
	 */
	@ColumnDescription(caption="船型")
	private String boatType;
	
	/**
	 * 功率（千瓦）
	 */
	@ColumnDescription(caption="功率（千瓦）")
	private Double power;
	
	/**
	 * 数量（艘）
	 */
	@ColumnDescription(caption="数量（艘）")
	private Integer number;
	
	/**
	 * 燃油类型（柴油/重油/船用柴油/船用汽油）
	 */
	@ColumnDescription(caption="燃油类型（柴油/重油/船用柴油/船用汽油）")
	private String fuelType;
	
	/**
	 * 消耗量（吨/年/艘）
	 */
	@ColumnDescription(caption="消耗量（吨/年/艘）")
	private Double annualConsume;
	/**
	 * 燃料含硫率（％）
	 */
	@ColumnDescription(caption="燃料含硫率（％）")
	private Double fuelSulphurRate;

	
	@Column(columnDefinition="VARCHAR(255) COMMENT '船型'")
	public String getBoatType() {
		return boatType;
	}
	public void setBoatType(String boatType) {
		this.boatType = boatType;
	}
	@Column(columnDefinition="DOUBLE COMMENT '功率（千瓦）'")
	public Double getPower() {
		return power;
	}
	public void setPower(Double power) {
		this.power = power;
	}
	@Column(columnDefinition="Int COMMENT '数量（艘）'")
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '燃油类型'")
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	@Column(columnDefinition="DOUBLE COMMENT '消耗量'")
	public Double getAnnualConsume() {
		return annualConsume;
	}
	public void setAnnualConsume(Double annualConsume) {
		this.annualConsume = annualConsume;
	}
	@Column(columnDefinition="DOUBLE COMMENT '燃料含硫率（％）'")
	public Double getFuelSulphurRate() {
		return fuelSulphurRate;
	}
	public void setFuelSulphurRate(Double fuelSulphurRate) {
		this.fuelSulphurRate = fuelSulphurRate;
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

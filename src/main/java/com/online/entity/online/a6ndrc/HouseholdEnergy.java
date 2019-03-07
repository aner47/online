package com.online.entity.online.a6ndrc;

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
 * 发改委-居民能源统计信息
 */
@EntityDescription(caption="居民能源统计信息")
@Entity
@Table(name="ol_household_energy")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_household_energy")
public class HouseholdEnergy extends BaseEntity<Integer> {

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
	 * 行政区
	 */
	@ColumnDescription(caption="行政区")
	private String city;
	
	/**
	 * 乡镇
	 */
	@ColumnDescription(caption="乡镇")
	private String town;
	
	/**
	 * 居民户数
	 */
	@ColumnDescription(caption="居民户数")
	private Integer residentsNumber;
	
	/**
	 * 累计散煤户数
	 */
	@ColumnDescription(caption="累计散煤户数")
	private Integer scatteredCoalNumber;
	
	/**
	 * 累计年散煤用量（吨）
	 */
	@ColumnDescription(caption="累计年散煤用量（吨）")
	private Double scatteredCoaluse;
	/**
	 * 累计天然气户数
	 */
	@ColumnDescription(caption="累计天然气户数")
	private Integer naturalGasNum;
	/**
	 * 累计天然气用量（万立方米）
	 */
	@ColumnDescription(caption="累计天然气用量（万立方米）")
	private Double naturalGasUse;
	/**
	 * 累计液化石油气户数
	 */
	@ColumnDescription(caption="累计液化石油气户数")
	private Integer liquefiedGasNum;
	/**
	 * 累计液化石油气用量（万立方米）
	 */
	@ColumnDescription(caption="累计液化石油气用量（万立方米）")
	private Double liquefiedGasUse;
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '行政区'")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '乡镇'")
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	@Column(columnDefinition="Int COMMENT '居民户数'")
	public Integer getResidentsNumber() {
		return residentsNumber;
	}
	public void setResidentsNumber(Integer residentsNumber) {
		this.residentsNumber = residentsNumber;
	}
	@Column(columnDefinition="Int COMMENT '累计散煤户数'")
	public Integer getScatteredCoalNumber() {
		return scatteredCoalNumber;
	}
	public void setScatteredCoalNumber(Integer scatteredCoalNumber) {
		this.scatteredCoalNumber = scatteredCoalNumber;
	}
	@Column(columnDefinition="DOUBLE COMMENT '累计年散煤用量（吨）'")
	public Double getScatteredCoaluse() {
		return scatteredCoaluse;
	}
	public void setScatteredCoaluse(Double scatteredCoaluse) {
		this.scatteredCoaluse = scatteredCoaluse;
	}
	@Column(columnDefinition="Int COMMENT '累计天然气户数'")
	public Integer getNaturalGasNum() {
		return naturalGasNum;
	}
	public void setNaturalGasNum(Integer naturalGasNum) {
		this.naturalGasNum = naturalGasNum;
	}
	@Column(columnDefinition="DOUBLE COMMENT '累计天然气用量（万立方米）'")
	public Double getNaturalGasUse() {
		return naturalGasUse;
	}
	public void setNaturalGasUse(Double naturalGasUse) {
		this.naturalGasUse = naturalGasUse;
	}
	@Column(columnDefinition="Int COMMENT '累计液化石油气户数'")
	public Integer getLiquefiedGasNum() {
		return liquefiedGasNum;
	}
	public void setLiquefiedGasNum(Integer liquefiedGasNum) {
		this.liquefiedGasNum = liquefiedGasNum;
	}
	@Column(columnDefinition="DOUBLE COMMENT '累计液化石油气用量（万立方米）'")
	public Double getLiquefiedGasUse() {
		return liquefiedGasUse;
	}
	public void setLiquefiedGasUse(Double liquefiedGasUse) {
		this.liquefiedGasUse = liquefiedGasUse;
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

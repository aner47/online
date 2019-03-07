package com.online.entity.online.a3roadbureau;

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
 * 交通局-公交车保有量
 */
@EntityDescription(caption="公交车保有量")
@Entity
@Table(name="ol_bus_inventory")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_bus_inventory")
public class BusInventory extends BaseEntity<Integer> {

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
	 * 车型
	 */
	@ColumnDescription(caption="车型")
	private String carType;
	
	/**
	 * 汽油
	 */
	@ColumnDescription(caption="汽油")
	private Integer gasoline;
	
	/**
	 * 柴油
	 */
	@ColumnDescription(caption="柴油")
	private Integer diesel;
	
	/**
	 * LNG/CNG
	 */
	@ColumnDescription(caption="LNG/CNG")
	private Integer lngCng;
	/**
	 * 油电混合
	 */
	@ColumnDescription(caption="油电混合")
	private Integer oilElectricity;
	
	/**
	 * 油气混合
	 */
	@ColumnDescription(caption="油气混合")
	private Integer oilGas;
	
	/**
	 * 纯电动
	 */
	@ColumnDescription(caption="纯电动")
	private Integer electricity;
	
	/**
	 * 其它
	 */
	@ColumnDescription(caption="其它")
	private Integer other;
	
	
	
	
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '车型'")
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	@Column(columnDefinition="Int COMMENT '汽油'")
	public Integer getGasoline() {
		return gasoline;
	}
	public void setGasoline(Integer gasoline) {
		this.gasoline = gasoline;
	}
	@Column(columnDefinition="Int COMMENT '柴油'")
	public Integer getDiesel() {
		return diesel;
	}
	public void setDiesel(Integer diesel) {
		this.diesel = diesel;
	}
	@Column(columnDefinition="Int COMMENT 'LNG/CNG'")
	public Integer getLngCng() {
		return lngCng;
	}
	public void setLngCng(Integer lngCng) {
		this.lngCng = lngCng;
	}
	@Column(columnDefinition="Int COMMENT '油电混合'")
	public Integer getOilElectricity() {
		return oilElectricity;
	}
	public void setOilElectricity(Integer oilElectricity) {
		this.oilElectricity = oilElectricity;
	}
	@Column(columnDefinition="Int COMMENT '油气混合'")
	public Integer getOilGas() {
		return oilGas;
	}
	public void setOilGas(Integer oilGas) {
		this.oilGas = oilGas;
	}
	@Column(columnDefinition="Int COMMENT '纯电动'")
	public Integer getElectricity() {
		return electricity;
	}
	public void setElectricity(Integer electricity) {
		this.electricity = electricity;
	}
	@Column(columnDefinition="Int COMMENT '其它'")
	public Integer getOther() {
		return other;
	}
	public void setOther(Integer other) {
		this.other = other;
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

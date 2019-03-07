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
 * 市农业委-农作物产量及秸秆利用情况
 */
@EntityDescription(caption="农作物产量及秸秆利用情况")
@Entity
@Table(name="ol_crop")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_crop")
public class Crop extends BaseEntity<Integer> {

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
	 * 农作物类型
	 */
	@ColumnDescription(caption="农作物类型")
	private String cropType;
	
	/**
	 * 产量（吨）
	 */
	@ColumnDescription(caption="产量（吨）")
	private Double yield;
	
	/**
	 * 秸秆肥料
	 */
	@ColumnDescription(caption="秸秆肥料")
	private Double strawManure;
	
	/**
	 * 秸秆饲料
	 */
	@ColumnDescription(caption="秸秆饲料")
	private Double strawFodder;
	
	/**
	 * 秸秆燃料
	 */
	@ColumnDescription(caption="秸秆燃料")
	private Double strawFuel;
	
	/**
	 * 工业原料
	 */
	@ColumnDescription(caption="工业原料")
	private Double industrialRaw;
	
	/**
	 * 食用菌基料
	 */
	@ColumnDescription(caption="食用菌基料")
	private Double edibleRaw;
	/**
	 * 露天焚烧
	 */
	@ColumnDescription(caption="露天焚烧")
	private Double openBurning;
	/**
	 * 其他
	 */
	@ColumnDescription(caption="其他")
	private Double other;
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '行政区'")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '农作物类型'")
	public String getCropType() {
		return cropType;
	}
	public void setCropType(String cropType) {
		this.cropType = cropType;
	}
	@Column(columnDefinition="DOUBLE COMMENT '产量（吨）'")
	public Double getYield() {
		return yield;
	}
	public void setYield(Double yield) {
		this.yield = yield;
	}
	@Column(columnDefinition="DOUBLE COMMENT '秸秆肥料'")
	public Double getStrawManure() {
		return strawManure;
	}
	public void setStrawManure(Double strawManure) {
		this.strawManure = strawManure;
	}
	@Column(columnDefinition="DOUBLE COMMENT '秸秆饲料'")
	public Double getStrawFodder() {
		return strawFodder;
	}
	public void setStrawFodder(Double strawFodder) {
		this.strawFodder = strawFodder;
	}
	@Column(columnDefinition="DOUBLE COMMENT '秸秆燃料'")
	public Double getStrawFuel() {
		return strawFuel;
	}
	public void setStrawFuel(Double strawFuel) {
		this.strawFuel = strawFuel;
	}
	@Column(columnDefinition="DOUBLE COMMENT '工业原料'")
	public Double getIndustrialRaw() {
		return industrialRaw;
	}
	public void setIndustrialRaw(Double industrialRaw) {
		this.industrialRaw = industrialRaw;
	}
	@Column(columnDefinition="DOUBLE COMMENT '食用菌基料'")
	public Double getEdibleRaw() {
		return edibleRaw;
	}
	public void setEdibleRaw(Double edibleRaw) {
		this.edibleRaw = edibleRaw;
	}
	@Column(columnDefinition="DOUBLE COMMENT '露天焚烧'")
	public Double getOpenBurning() {
		return openBurning;
	}
	public void setOpenBurning(Double openBurning) {
		this.openBurning = openBurning;
	}
	@Column(columnDefinition="DOUBLE COMMENT '其他'")
	public Double getOther() {
		return other;
	}
	public void setOther(Double other) {
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

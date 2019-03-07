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
 * 城管局-城市道路保洁信息机械化统计表
 */
@EntityDescription(caption="城市道路保洁信息机械化统计表")
@Entity
@Table(name="ol_city_road_clean_machine")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_city_road_clean_machine")
public class CityRoadCleanMachine extends BaseEntity<Integer> {

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
	 * 道路名称
	 */
	@ColumnDescription(caption="道路名称")
	private String roadName;
	
	/**
	 * 道路级别（主/次/支）
	 */
	@ColumnDescription(caption="道路级别（主/次/支）")
	private String roadGrade;
	
	/**
	 * 起止点
	 */
	@ColumnDescription(caption="起止点")
	private String enthesis;
	
	/**
	 * 长度（m）
	 */
	@ColumnDescription(caption="长度（m）")
	private Double length;
	
	/**
	 * 快车道宽度（m）
	 */
	@ColumnDescription(caption="快车道宽度（m）")
	private Double fastRoadWidth;
	
	/**
	 * 人行道宽度（m）
	 */
	@ColumnDescription(caption="人行道宽度（m）")
	private Double sidewalkWidth;
	
	/**
	 * 快车道道路面积（m2）
	 */
	@ColumnDescription(caption="快车道道路面积（m2）")
	private Double fastRoadArea;
	
	/**
	 * 人行道道路面积（m2）
	 */
	@ColumnDescription(caption="人行道道路面积（m2）")
	private Double sidewalkArea;
	/**
	 * 合计
	 */
	@ColumnDescription(caption="合计")
	private Double areaTotal;
	/**
	 * 洗扫车（台）
	 */
	@ColumnDescription(caption="洗扫车（台）")
	private Integer cleaningSweeperTruck;
	/**
	 * 吸尘车（台）
	 */
	@ColumnDescription(caption="吸尘车（台）")
	private Integer vacuumSweeper;
	/**
	 * 雾炮车（台）
	 */
	@ColumnDescription(caption="雾炮车（台）")
	private Integer mistCannonTruck;
	/**
	 * 其他车辆（台）
	 */
	@ColumnDescription(caption="其他车辆（台）")
	private Integer otherTruck;
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '行政区'")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '道路名称'")
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '道路级别（主/次/支）'")
	public String getRoadGrade() {
		return roadGrade;
	}
	public void setRoadGrade(String roadGrade) {
		this.roadGrade = roadGrade;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '起止点'")
	public String getEnthesis() {
		return enthesis;
	}
	public void setEnthesis(String enthesis) {
		this.enthesis = enthesis;
	}
	@Column(columnDefinition="DOUBLE COMMENT '长度（m）'")
	public Double getLength() {
		return length;
	}
	public void setLength(Double length) {
		this.length = length;
	}
	@Column(columnDefinition="DOUBLE COMMENT '快车道宽度（m）'")
	public Double getFastRoadWidth() {
		return fastRoadWidth;
	}
	public void setFastRoadWidth(Double fastRoadWidth) {
		this.fastRoadWidth = fastRoadWidth;
	}
	@Column(columnDefinition="DOUBLE COMMENT '人行道宽度（m）'")
	public Double getSidewalkWidth() {
		return sidewalkWidth;
	}
	public void setSidewalkWidth(Double sidewalkWidth) {
		this.sidewalkWidth = sidewalkWidth;
	}
	@Column(columnDefinition="DOUBLE COMMENT '快车道道路面积（m2）'")
	public Double getFastRoadArea() {
		return fastRoadArea;
	}
	public void setFastRoadArea(Double fastRoadArea) {
		this.fastRoadArea = fastRoadArea;
	}
	@Column(columnDefinition="DOUBLE COMMENT '人行道道路面积（m2）'")
	public Double getSidewalkArea() {
		return sidewalkArea;
	}
	public void setSidewalkArea(Double sidewalkArea) {
		this.sidewalkArea = sidewalkArea;
	}
	@Column(columnDefinition="DOUBLE COMMENT '合计'")
	public Double getAreaTotal() {
		return areaTotal;
	}
	public void setAreaTotal(Double areaTotal) {
		this.areaTotal = areaTotal;
	}
	@Column(columnDefinition="Int COMMENT '洗扫车（台）'")
	public Integer getCleaningSweeperTruck() {
		return cleaningSweeperTruck;
	}
	public void setCleaningSweeperTruck(Integer cleaningSweeperTruck) {
		this.cleaningSweeperTruck = cleaningSweeperTruck;
	}
	@Column(columnDefinition="Int COMMENT '吸尘车（台）'")
	public Integer getVacuumSweeper() {
		return vacuumSweeper;
	}
	public void setVacuumSweeper(Integer vacuumSweeper) {
		this.vacuumSweeper = vacuumSweeper;
	}
	@Column(columnDefinition="Int COMMENT '雾炮车（台）'")
	public Integer getMistCannonTruck() {
		return mistCannonTruck;
	}
	public void setMistCannonTruck(Integer mistCannonTruck) {
		this.mistCannonTruck = mistCannonTruck;
	}
	@Column(columnDefinition="Int COMMENT '其他车辆（台）'")
	public Integer getOtherTruck() {
		return otherTruck;
	}
	public void setOtherTruck(Integer otherTruck) {
		this.otherTruck = otherTruck;
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

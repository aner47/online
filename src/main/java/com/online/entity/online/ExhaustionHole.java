package com.online.entity.online;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;

/**
 * 排放口信息
 * 
 * @author DEV2
 *
 */
@EntityDescription(caption="排放口")
@Entity
@Table(name="ol_exhaustion_hole")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_exhaustion_hole")
public class ExhaustionHole extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6550344167880802922L;
	
	
	public enum ExhaustionHoleType{
		/**
		 * 烟囱
		 */
		chimney,
		/**
		 * 排气筒
		 */
		exhaustPipe
		
	}
	
	/**
	 * 所属企业信息
	 */
	@ColumnDescription(caption="企业")
	private Enterprise enterprise;
	
	/**
	 * 排气口类型
	 */
	@ColumnDescription(caption="类型")
	private ExhaustionHoleType type;
	
	/**
	 * 排放口高度(米)
	 */
	@ColumnDescription(caption="高度")
	private Double height;
	
	/**
	 * 排放口直径(米)
	 */
	@ColumnDescription(caption="直径")
	private Double diameter;
	
	/**
	 * 出口废气温度(℃)
	 */
	@ColumnDescription(caption="温度")
	private Double temperature;
	
	/**
	 * 废气排放流量（标立方米/小时）
	 */
	@ColumnDescription(caption="流量")
	private Double flow;
	
	/**
	 * 年总废气排放量(万标立方米)
	 */
	@ColumnDescription(caption="排放量")
	private Double emissions;
	
	/**
	 * so2监测排放浓度（毫克/立方米）
	 */
	@ColumnDescription(caption="so2浓度")
	private Double so2;	
	
	/**
	 * nox监测排放浓度（毫克/立方米）
	 */
	@ColumnDescription(caption="nox浓度")
	private Double nox;	
	
	/**
	 * tsp监测排放浓度（毫克/立方米）
	 */
	@ColumnDescription(caption="tsp浓度")
	private Double tsp;	
	
	/**
	 * voc监测排放浓度（毫克/立方米）
	 */
	@ColumnDescription(caption="voc浓度")
	private Double voc;
	
	/**
	 * 含氧量
	 */
	@ColumnDescription(caption="oxygen")
	private Double oxygen;
	
	/**
	 * 在线监测类型
	 */
	@ColumnDescription(caption="在线监测类型")
	private String onlineDetectionType;
	
	/**
	 * 项目
	 */
	@ColumnDescription(caption="项目")
	private Project project;
	
	/**
	 * 在线监测设备
	 */
	@ColumnDescription(caption="在线监测")
	private boolean testingEquipment;
	
	/**
	 * 排放口编号
	 */
	@ColumnDescription(caption="编号")
	private Integer exhaustionHoleNum;
	
	/**
	 * 备注
	 */
	@ColumnDescription(caption="备注")
	private String description;
	
	
	/**
	 * 经度 
	 */
	@ColumnDescription(caption="经度")
	private Double longitude;
	
	
	/**
	 * 纬度 
	 */
	@ColumnDescription(caption="纬度")
	private Double latitude;
	
	
	/**
	 * 使用状态
	 */
	@ColumnDescription(caption="使用状态")
	private String useStatus;
	
	/**
	 * 材质
	 */
	@ColumnDescription(caption="材质")
	private String texture;
	
	
	
	/**
	 * 非甲烷总烃（毫克/立方米）
	 */
	@ColumnDescription(caption="非甲烷总烃")
	private Double unMethane;
	
	
	
	
	
	@Column(columnDefinition="DOUBLE COMMENT '非甲烷总烃'")
	public Double getUnMethane() {
		return unMethane;
	}

	public void setUnMethane(Double unMethane) {
		this.unMethane = unMethane;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '备注'")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(updatable=false,columnDefinition="VARCHAR(255) COMMENT '编号'")
	public Integer getExhaustionHoleNum() {
		return exhaustionHoleNum;
	}

	public void setExhaustionHoleNum(Integer exhaustionHoleNum) {
		this.exhaustionHoleNum = exhaustionHoleNum;
	}

	@ManyToOne
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	
	@ManyToOne
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	@Column(columnDefinition="INT(11) COMMENT '排气口类型'")
	public ExhaustionHoleType getType() {
		return type;
	}

	public void setType(ExhaustionHoleType type) {
		this.type = type;
	}
	@Column(columnDefinition="DOUBLE COMMENT '高度'")
	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}
	@Column(columnDefinition="DOUBLE COMMENT '直径'")
	public Double getDiameter() {
		return diameter;
	}

	public void setDiameter(Double diameter) {
		this.diameter = diameter;
	}
	@Column(columnDefinition="DOUBLE COMMENT '温度'")
	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	@Column(columnDefinition="DOUBLE COMMENT '流量'")
	public Double getFlow() {
		return flow;
	}

	public void setFlow(Double flow) {
		this.flow = flow;
	}
	@Column(columnDefinition="DOUBLE COMMENT '排放量'")
	public Double getEmissions() {
		return emissions;
	}

	public void setEmissions(Double emissions) {
		this.emissions = emissions;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'so2浓度'")
	public Double getSo2() {
		return so2;
	}

	public void setSo2(Double so2) {
		this.so2 = so2;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'nox浓度'")
	public Double getNox() {
		return nox;
	}

	public void setNox(Double nox) {
		this.nox = nox;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'tsp浓度'")
	public Double getTsp() {
		return tsp;
	}

	public void setTsp(Double tsp) {
		this.tsp = tsp;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'voc浓度'")
	public Double getVoc() {
		return voc;
	}

	public void setVoc(Double voc) {
		this.voc = voc;
	}
	@Column(columnDefinition="BIT(1) COMMENT '在线监测'")
	public boolean isTestingEquipment() {
		return testingEquipment;
	}

	public void setTestingEquipment(boolean testingEquipment) {
		this.testingEquipment = testingEquipment;
	}
	@Column(columnDefinition="DOUBLE COMMENT '含氧量'")
	public Double getOxygen() {
		return oxygen;
	}

	public void setOxygen(Double oxygen) {
		this.oxygen = oxygen;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '在线监测类型'")
	public String getOnlineDetectionType() {
		return onlineDetectionType;
	}

	public void setOnlineDetectionType(String onlineDetectionType) {
		this.onlineDetectionType = onlineDetectionType;
	}
	
	@Column(columnDefinition="DOUBLE COMMENT '经度'")
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	@Column(columnDefinition="DOUBLE COMMENT '纬度 '")
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '使用状态'")
	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '材质'")
	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}
	
	
	

}

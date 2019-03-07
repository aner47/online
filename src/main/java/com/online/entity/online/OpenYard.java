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
 * 露天堆场
 * 
 * @author DEV2
 *
 */
@EntityDescription(caption="露天堆场")
@Entity
@Table(name="ol_open_yard")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_open_yard")
public class OpenYard extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1036727632818805537L;
	

	
	/**
	 * 料堆类型▼
	 */
	@ColumnDescription(caption="料堆类型")
	private String materialType;
	
	/**
	 * 料堆材料
	 */
	@ColumnDescription(caption="料堆材料")
	private String material;
	
	/**
	 * 堆场装卸总量(吨)
	 */
	@ColumnDescription(caption="堆场装卸总量(吨)")
	private  Double loadAmount;
	
	/**
	 * 每年物料运载车次（车）
	 */
	@ColumnDescription(caption="每年物料运载车次（车）")
	private  Double cargoTrips;
	
	/**
	 * 每车运载量(吨/车)
	 */
	@ColumnDescription(caption="每车运载量(吨/车)")
	private  Double carryAmount;
	
	/**
	 * 料堆占地面积(平方米)
	 */
	@ColumnDescription(caption="料堆占地面积(平方米)")
	private Double area;
	
	/**
	 * 料堆最高高度(米)
	 */
	@ColumnDescription(caption="料堆最高高度(米)")
	private Double  height;
	
	/**
	 * 物料含水率（%）
	 */
	@ColumnDescription(caption="物料含水率（%）")
	private Double moistureContent;
	
	/**
	 * 出入车辆是否清洗
	 */
	@ColumnDescription(caption="出入车辆是否清洗")
	private Boolean carCleaning;
	
	/**
	 * 是否进行破碎、筛选
	 */
	@ColumnDescription(caption="是否进行破碎、筛选")
	private Boolean broken;
	
	/**
	 * 破碎环境是否封闭
	 */
	@ColumnDescription(caption="破碎环境是否封闭")
	private Boolean closed;
	
	/**
	 * 煤堆场是否封闭
	 */
	@ColumnDescription(caption="煤堆场是否封闭")
	private Boolean coalClosed;
	
	/**
	 * 卸煤沟是否封闭
	 */
	@ColumnDescription(caption="卸煤沟是否封闭")
	private Boolean unloadCoalClosed;
	
	
	
	
	
	/**
	 * 措施1
	 */
	@ColumnDescription(caption="措施1")
	private String measure1;
	
	/**
	 * 措施2
	 */
	@ColumnDescription(caption="措施2")
	private String measure2;
	
	/**
	 * 措施3
	 */
	@ColumnDescription(caption="措施3")
	private String measure3;
	
	/**
	 * 项目
	 */
	private Project project;
	
	/**
	 * 企业
	 */
	private Enterprise enterprise;
	
	/**
	 * 备注
	 */
	private String description;
	
	
	/**
	 * 物料运输方式
	 */
	@ColumnDescription(caption="物料运输方式")
	private String transportWay;
	
	
	/**
	 *物料方式运输比例(%)
	 */
	@ColumnDescription(caption="物料方式运输比例(%)")
	private Double transportWayRatio;
	
	
	/**
	 *料堆数量
	 */
	@ColumnDescription(caption="料堆数量")
	private Double pileNum;
	
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '物料运输方式'")
	public String getTransportWay() {
		return transportWay;
	}

	public void setTransportWay(String transportWay) {
		this.transportWay = transportWay;
	}
	@Column(columnDefinition="DOUBLE COMMENT '物料方式运输比例(%)'")
	public Double getTransportWayRatio() {
		return transportWayRatio;
	}

	public void setTransportWayRatio(Double transportWayRatio) {
		this.transportWayRatio = transportWayRatio;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '备注'")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '料堆类型'")
	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	@Column(columnDefinition="DOUBLE COMMENT '堆场装卸总量(吨)'")
	public Double getLoadAmount() {
		return loadAmount;
	}

	public void setLoadAmount(Double loadAmount) {
		this.loadAmount = loadAmount;
	}
	@Column(columnDefinition="DOUBLE COMMENT '每年物料运载车次（车）'")
	public Double getCargoTrips() {
		return cargoTrips;
	}

	public void setCargoTrips(Double cargoTrips) {
		this.cargoTrips = cargoTrips;
	}
	@Column(columnDefinition="DOUBLE COMMENT '每车运载量(吨/车)'")
	public Double getCarryAmount() {
		return carryAmount;
	}

	public void setCarryAmount(Double carryAmount) {
		this.carryAmount = carryAmount;
	}
	@Column(columnDefinition="DOUBLE COMMENT '料堆占地面积(平方米)'")
	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}
	@Column(columnDefinition="DOUBLE COMMENT '料堆最高高度(米)'")
	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}
	@Column(columnDefinition="DOUBLE COMMENT '物料含水率（%）'")
	public Double getMoistureContent() {
		return moistureContent;
	}

	public void setMoistureContent(Double moistureContent) {
		this.moistureContent = moistureContent;
	}
	@Column(columnDefinition="BIT(1) COMMENT '出入车辆是否清洗'")
	public Boolean getCarCleaning() {
		return carCleaning;
	}

	public void setCarCleaning(Boolean carCleaning) {
		this.carCleaning = carCleaning;
	}
	@Column(columnDefinition="BIT(1) COMMENT '是否进行破碎、筛选'")
	public Boolean getBroken() {
		return broken;
	}

	public void setBroken(Boolean broken) {
		this.broken = broken;
	}
	@Column(columnDefinition="BIT(1) COMMENT '破碎环境是否封闭'")
	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '措施1'")
	public String getMeasure1() {
		return measure1;
	}

	public void setMeasure1(String measure1) {
		this.measure1 = measure1;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '措施2'")
	public String getMeasure2() {
		return measure2;
	}

	public void setMeasure2(String measure2) {
		this.measure2 = measure2;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '措施3'")
	public String getMeasure3() {
		return measure3;
	}

	public void setMeasure3(String measure3) {
		this.measure3 = measure3;
	}

	@ManyToOne
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '料堆材料'")
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
	@Column(columnDefinition="BIT(1) COMMENT '煤堆场是否封闭'")
	public Boolean getCoalClosed() {
		return coalClosed;
	}

	public void setCoalClosed(Boolean coalClosed) {
		this.coalClosed = coalClosed;
	}
	@Column(columnDefinition="BIT(1) COMMENT '卸煤沟是否封闭'")
	public Boolean getUnloadCoalClosed() {
		return unloadCoalClosed;
	}

	public void setUnloadCoalClosed(Boolean unloadCoalClosed) {
		this.unloadCoalClosed = unloadCoalClosed;
	}
	
	@Column(columnDefinition="DOUBLE COMMENT '料堆数量'")
	public Double getPileNum() {
		return pileNum;
	}

	public void setPileNum(Double pileNum) {
		this.pileNum = pileNum;
	}
	
	
	
	
	
}

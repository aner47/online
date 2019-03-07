package com.online.entity.online;

import java.util.Date;

import javax.persistence.CascadeType;
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

@EntityDescription(caption="钢铁信息")
@Entity
@Table(name="ol_steel")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_steel")
public class Steel extends BaseEntity<Integer> {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7646494801680440545L;
	
	/**
	 * 企业信息
	 */
	@ColumnDescription(caption="企业信息")
	private Enterprise enterprise;
	
	/**
	 * 生产工序
	 */
	@ColumnDescription(caption="生产工序")
	private String manufactureProcess;
	
	/**
	 * 设备编号
	 */
	@ColumnDescription(caption="设备编号")
	private String no;
	
	
	/**
	 * 设备类型
	 */
	@ColumnDescription(caption="设备类型")
	private String equipmentType;
	
	
	/**
	 * 设计生产能力
	 */
	@ColumnDescription(caption="设计生产能力")
	private String productionCapacity;
	
	
	/**
	 * 规模
	 */
	@ColumnDescription(caption="规模")
	private String scale;
	
	
	/**
	 * 投运时间
	 */
	@ColumnDescription(caption="投运时间")
	private Date putOnDate;
	/**
	 * 关停时间
	 */
	@ColumnDescription(caption="关停时间")
	private Date stopData;
	
	
	
	
	
	
	
	/**
	 * 燃料类型
	 */
	@ColumnDescription(caption="燃料类型")
	private String fuelType;
	
	/**
	 * 燃料消耗量
	 */
	@ColumnDescription(caption="燃料消耗量")
	private Double fuelConsumption;
	
	/**
	 * 燃料单位
	 */
	@ColumnDescription(caption="燃料单位")
	private String fuelUnit;
	
	/**
	 * 燃料含硫率
	 */
	@ColumnDescription(caption="燃料含硫率")
	private Double fuelSulfurContent;
	
	/**
	 * 主要原辅料1
	 */
	@ColumnDescription(caption="主要原辅料1")
	private RawMaterials rawMaterials1;
	/**
	 * 主要原辅料2
	 */
	@ColumnDescription(caption="主要原辅料2")
	private RawMaterials rawMaterials2;
	/**
	 * 主要原辅料3
	 */
	@ColumnDescription(caption="主要原辅料3")
	private RawMaterials rawMaterials3;
	/**
	 * 主要原辅料4
	 */
	@ColumnDescription(caption="主要原辅料4")
	private RawMaterials rawMaterials4;
	/**
	 * 主要原辅料5
	 */
	@ColumnDescription(caption="主要原辅料5")
	private RawMaterials rawMaterials5;
	
	/**
	 * 主要产品
	 */
	@ColumnDescription(caption="产品")
	private Product product;
	
	/**
	 * 项目
	 */
	private Project project;
	
	/**
	 * 备注
	 */
	@ColumnDescription(caption="描述")
	private String description;
	
	
	/**
	 * 煤气产生量(万立方米)
	 */
	@ColumnDescription(caption="煤气产生量(万立方米)")
	private Double coalGasContent;
	
	/**
	 * 煤气自用量(万立方米)
	 */
	@ColumnDescription(caption="煤气自用量(万立方米)")
	private Double coalGasUseContent;
	
	
	/**
	 * 是否存在化学加工
	 */
	@ColumnDescription(caption="是否存在化学加工")
	private String isChemical;
	
	/**
	 * 熄焦工艺
	 */
	@ColumnDescription(caption="熄焦工艺")
	private String coke;
	
	
	/**
	 * 年份
	 */
	@ColumnDescription(caption="年份")
	private String year;
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '年份'")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '生产工序'")
	public String getManufactureProcess() {
		return manufactureProcess;
	}

	public void setManufactureProcess(String manufactureProcess) {
		this.manufactureProcess = manufactureProcess;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '编号'")
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '设备类型'")
	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '设计生产能力'")
	public String getProductionCapacity() {
		return productionCapacity;
	}

	public void setProductionCapacity(String productionCapacity) {
		this.productionCapacity = productionCapacity;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '规模'")
	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}
	@Column(columnDefinition="DOUBLE COMMENT '煤气产生量(万立方米)'")
	public Double getCoalGasContent() {
		return coalGasContent;
	}

	public void setCoalGasContent(Double coalGasContent) {
		this.coalGasContent = coalGasContent;
	}
	@Column(columnDefinition="DOUBLE COMMENT '煤气自用量(万立方米)'")
	public Double getCoalGasUseContent() {
		return coalGasUseContent;
	}

	public void setCoalGasUseContent(Double coalGasUseContent) {
		this.coalGasUseContent = coalGasUseContent;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '是否存在化学加工'")
	public String getIsChemical() {
		return isChemical;
	}

	public void setIsChemical(String isChemical) {
		this.isChemical = isChemical;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '熄焦工艺'")
	public String getCoke() {
		return coke;
	}

	public void setCoke(String coke) {
		this.coke = coke;
	}

	@JoinColumn(updatable = false)
	@ManyToOne
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	@Column(columnDefinition="DATETIME COMMENT '关停时间'")
	public Date getStopData() {
		return stopData;
	}

	public void setStopData(Date stopData) {
		this.stopData = stopData;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '备注'")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	@Column(columnDefinition="VARCHAR(255) COMMENT '燃料类型'")
	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	@Column(columnDefinition="DOUBLE COMMENT '燃料消耗量'")
	public Double getFuelConsumption() {
		return fuelConsumption;
	}

	public void setFuelConsumption(Double fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '燃料单位'")
	public String getFuelUnit() {
		return fuelUnit;
	}

	public void setFuelUnit(String fuelUnit) {
		this.fuelUnit = fuelUnit;
	}
	@Column(columnDefinition="DOUBLE COMMENT '燃料含硫率'")
	public Double getFuelSulfurContent() {
		return fuelSulfurContent;
	}

	public void setFuelSulfurContent(Double fuelSulfurContent) {
		this.fuelSulfurContent = fuelSulfurContent;
	}
	@ManyToOne(cascade=CascadeType.REMOVE)
	public RawMaterials getRawMaterials1() {
		return rawMaterials1;
	}

	public void setRawMaterials1(RawMaterials rawMaterials1) {
		this.rawMaterials1 = rawMaterials1;
	}
	@ManyToOne(cascade=CascadeType.REMOVE)
	public RawMaterials getRawMaterials2() {
		return rawMaterials2;
	}

	public void setRawMaterials2(RawMaterials rawMaterials2) {
		this.rawMaterials2 = rawMaterials2;
	}
	@ManyToOne(cascade=CascadeType.REMOVE)
	public RawMaterials getRawMaterials3() {
		return rawMaterials3;
	}

	public void setRawMaterials3(RawMaterials rawMaterials3) {
		this.rawMaterials3 = rawMaterials3;
	}
	@ManyToOne(cascade=CascadeType.REMOVE)
	public RawMaterials getRawMaterials4() {
		return rawMaterials4;
	}

	public void setRawMaterials4(RawMaterials rawMaterials4) {
		this.rawMaterials4 = rawMaterials4;
	}
	@ManyToOne(cascade=CascadeType.REMOVE)
	public RawMaterials getRawMaterials5() {
		return rawMaterials5;
	}

	public void setRawMaterials5(RawMaterials rawMaterials5) {
		this.rawMaterials5 = rawMaterials5;
	}

	@ManyToOne(cascade=CascadeType.REMOVE)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	@Column(columnDefinition="DATETIME COMMENT '投运时间'")
	public Date getPutOnDate() {
		return putOnDate;
	}

	public void setPutOnDate(Date putOnDate) {
		this.putOnDate = putOnDate;
	}

	@JoinColumn(updatable = false)
	@ManyToOne
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	@PrePersist
	public void initProjectEnterpise(){
		project = SpringUtils.getCurrentProject();
		enterprise = SpringUtils.getCurrentEnterprise();
	}
	

}

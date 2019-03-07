package com.online.entity.online;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.entity.online.embeddable.EnterpriseEmissionsManagement;

@EntityDescription(caption="窑炉信息")
@Entity
@Table(name="ol_kilnn")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_kilnn")
public class Kiln extends BaseEntity<Integer> {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7646494801680440545L;
	
	/**
	 * 管理信息
	 */
	private EnterpriseEmissionsManagement enterpriseEmissionsManagement;
	/**
	 * 工艺名称
	 */
	@ColumnDescription(caption="工艺名称")
	private String name;
	
	/**
	 * 投运时间
	 */
	@ColumnDescription(caption="投运时间")
	private Date putOnDate;
	
	/**
	 * 窑炉类型
	 */
	@ColumnDescription(caption="窑炉类型")
	private String kilnType;
	
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
	 * 关停时间
	 */
	@ColumnDescription(caption="关停时间")
	private Date stopData;
	
	/**
	 * 排放口（窑尾）
	 */
	@ColumnDescription(caption="窑尾")
	private ExhaustionHole exhaustionHoleTail;
	
	
	
	/**
	 * 生产月份
	 */
	@ColumnDescription(caption="生产月份")
	private String productionMonth;
	
	
	/**
	 * 生产时段-开始
	 */
	@ColumnDescription(caption="生产时段-开始")
	private Date startManufacture;
	
	/**
	 * 生产时段-结束
	 */
	@ColumnDescription(caption="生产时段-结束")
	private Date endManufacture;
	
	/**
	 * 年份
	 */
	@ColumnDescription(caption="年份")
	private String year;
	
	
	/**
	 * 使用状态
	 */
	@ColumnDescription(caption="使用状态")
	private String useStatus;
	
	/**
	 * 整改时间
	 */
	@ColumnDescription(caption="整改时间")
	private Date abarbeitungTime;
	
	/**
	 * 编号
	 */
	@ColumnDescription(caption="编号")
	private Integer no;
	
	
	
	@Column(columnDefinition="int(11) COMMENT '编号'")
	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '年份'")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '生产月份'")
	public String getProductionMonth() {
		return productionMonth;
	}

	public void setProductionMonth(String productionMonth) {
		this.productionMonth = productionMonth;
	}
	@Column(columnDefinition="DATETIME COMMENT '生产时段-开始'")
	public Date getStartManufacture() {
		return startManufacture;
	}

	public void setStartManufacture(Date startManufacture) {
		this.startManufacture = startManufacture;
	}
	@Column(columnDefinition="DATETIME COMMENT '生产时段-结束'")
	public Date getEndManufacture() {
		return endManufacture;
	}

	public void setEndManufacture(Date endManufacture) {
		this.endManufacture = endManufacture;
	}
	@Column(columnDefinition="DATETIME COMMENT '关停时间'")
	public Date getStopData() {
		return stopData;
	}

	public void setStopData(Date stopData) {
		this.stopData = stopData;
	}

	@ManyToOne
	public ExhaustionHole getExhaustionHoleTail() {
		return exhaustionHoleTail;
	}

	public void setExhaustionHoleTail(ExhaustionHole exhaustionHoleTail) {
		this.exhaustionHoleTail = exhaustionHoleTail;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '备注'")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Embedded
	public EnterpriseEmissionsManagement getEnterpriseEmissionsManagement() {
		return enterpriseEmissionsManagement;
	}

	public void setEnterpriseEmissionsManagement(EnterpriseEmissionsManagement enterpriseEmissionsManagement) {
		this.enterpriseEmissionsManagement = enterpriseEmissionsManagement;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '工艺名称'")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '窑炉类型'")
	public String getKilnType() {
		return kilnType;
	}

	public void setKilnType(String kilnType) {
		this.kilnType = kilnType;
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
	@ManyToOne(cascade=CascadeType.ALL)
	public RawMaterials getRawMaterials1() {
		return rawMaterials1;
	}

	public void setRawMaterials1(RawMaterials rawMaterials1) {
		this.rawMaterials1 = rawMaterials1;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	public RawMaterials getRawMaterials2() {
		return rawMaterials2;
	}

	public void setRawMaterials2(RawMaterials rawMaterials2) {
		this.rawMaterials2 = rawMaterials2;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	public RawMaterials getRawMaterials3() {
		return rawMaterials3;
	}

	public void setRawMaterials3(RawMaterials rawMaterials3) {
		this.rawMaterials3 = rawMaterials3;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	public RawMaterials getRawMaterials4() {
		return rawMaterials4;
	}

	public void setRawMaterials4(RawMaterials rawMaterials4) {
		this.rawMaterials4 = rawMaterials4;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	public RawMaterials getRawMaterials5() {
		return rawMaterials5;
	}

	public void setRawMaterials5(RawMaterials rawMaterials5) {
		this.rawMaterials5 = rawMaterials5;
	}

	@ManyToOne(cascade=CascadeType.ALL)
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
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '使用状态'")
	public String getUseStatus() {
		return useStatus;
	}


	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	@Column(columnDefinition="DATETIME COMMENT '整改时间'")
	public Date getAbarbeitungTime() {
		return abarbeitungTime;
	}


	public void setAbarbeitungTime(Date abarbeitungTime) {
		this.abarbeitungTime = abarbeitungTime;
	}
	

	@ManyToOne
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	

}

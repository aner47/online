package com.online.entity.online;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.entity.online.embeddable.EnterpriseEmissionsManagement;

/**
 * 工段信息表
 * @author DEV2
 *
 */
@EntityDescription(caption="工段")
@Entity
@Table(name="ol_section")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_section")
public class Section extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3585568295880555340L;
	
	/**
	 * 企业排放治理信息
	 */
	private EnterpriseEmissionsManagement enterpriseEmissionsManagement;
	
	/**
	 * 工段名称
	 */
	@ColumnDescription(caption="名称")
	private String name;
	
	/**
     * 生产线数量
     */
	@ColumnDescription(caption="生产线数量")
	private Double productionLines;
	
	/**
	 * 设计生产能力（产品吨/年）
	 */
	@ColumnDescription(caption="设计生产能力（产品吨/年）")
	private Double productCapacity;
	/**
	 * 投运时间
	 */
	@ColumnDescription(caption="投运时间")
	private Date putOnDate;
	
	/**
	 * 停运时间
	 */
	@ColumnDescription(caption="停运时间")
	private Date stopDate;
	/**
	 * 产品
	 */
	@ColumnDescription(caption="产品")
	private Product product;
	
	/**
	 * 原辅料1
	 */
	private RawMaterials rawMaterials1;
	
	/**
	 * 原辅料2
	 */
	private RawMaterials rawMaterials2;
	
	/**
	 * 原辅料3
	 */
	private RawMaterials rawMaterials3;
	
	/**
	 * 原辅料4
	 */
	private RawMaterials rawMaterials4;
	
	public Date getPutOnDate() {
		return putOnDate;
	}

	public void setPutOnDate(Date putOnDate) {
		this.putOnDate = putOnDate;
	}

	/**
	 * 原辅料5
	 */
	private RawMaterials rawMaterials5;
	
	/**
	 * 项目
	 */
	private Project project;

	/**
	 * 备注
	 */
	private String description;
	
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
	 * 燃料消耗量单位
	 */
	@ColumnDescription(caption="燃料单位")
	private String fuelUnit;
	
	/**
	 * 年份
	 */
	@ColumnDescription(caption="年份")
	private String year;
	
	/**
	 * 生产月份
	 */
	@ColumnDescription(caption="生产月份")
	private String productionMonth;
	
	
	/**
	 * 生产工艺
	 */
	@ColumnDescription(caption="生产工艺")
	private String productionTechnique;
	
	/**
	 * 是否封闭作业
	 */
	@ColumnDescription(caption="是否封闭作业")
	private String isSealing;
	
	
	/**
	 * 整改时间
	 */
	@ColumnDescription(caption="整改时间")
	private Date abarbeitungTime;
	
	
	
	/**
	 * 生产设施名称
	 */
	@ColumnDescription(caption="生产设施名称")
	private String facilityName;
	
	
	/**
	 * 生产设施编号
	 */
	@ColumnDescription(caption="生产设施编号")
	private String facilityNo;
	
	/**
	 * 参数名称
	 */
	@ColumnDescription(caption="参数名称")
	private String facilityParamName;
	
	/**
	 * 设计值
	 */
	@ColumnDescription(caption="设计值")
	private String facilityDesignValue;
	
	/**
	 * 计量单位
	 */
	@ColumnDescription(caption="计量单位")
	private String facilityUnit;
	
	/**
	 * 污染物种类
	 */
	@ColumnDescription(caption="污染物种类")
	private String contaminantType;
	
	/**
	 * 排放形式
	 */
	@ColumnDescription(caption="排放形式")
	private String dischargeModality;
	
	
	/**
	 * 生产设施数量
	 */
	@ColumnDescription(caption="生产设施数量")
	private Integer facilityNum;
	
	
	/**
	 * 技术/产品类型
	 */
	@ColumnDescription(caption="技术/产品类型")
	private String tachnologyProductType;
	
	
	/**
	 * 设备/工序使用状态
	 */
	@ColumnDescription(caption="设备/工序使用状态")
	private String useStatus;
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '设备/工序使用状态'")
	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '技术/产品类型'")
	public String getTachnologyProductType() {
		return tachnologyProductType;
	}

	public void setTachnologyProductType(String tachnologyProductType) {
		this.tachnologyProductType = tachnologyProductType;
	}

	@Column(columnDefinition="Integer COMMENT '生产设施数量'")
	public Integer getFacilityNum() {
		return facilityNum;
	}

	public void setFacilityNum(Integer facilityNum) {
		this.facilityNum = facilityNum;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '污染物种类'")
	public String getContaminantType() {
		return contaminantType;
	}

	public void setContaminantType(String contaminantType) {
		this.contaminantType = contaminantType;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '排放形式'")
	public String getDischargeModality() {
		return dischargeModality;
	}

	public void setDischargeModality(String dischargeModality) {
		this.dischargeModality = dischargeModality;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '生产设施名称'")
	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '生产设施编号'")
	public String getFacilityNo() {
		return facilityNo;
	}

	public void setFacilityNo(String facilityNo) {
		this.facilityNo = facilityNo;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '参数名称'")
	public String getFacilityParamName() {
		return facilityParamName;
	}

	public void setFacilityParamName(String facilityParamName) {
		this.facilityParamName = facilityParamName;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '设计值'")
	public String getFacilityDesignValue() {
		return facilityDesignValue;
	}

	public void setFacilityDesignValue(String facilityDesignValue) {
		this.facilityDesignValue = facilityDesignValue;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '计量单位'")
	public String getFacilityUnit() {
		return facilityUnit;
	}

	public void setFacilityUnit(String facilityUnit) {
		this.facilityUnit = facilityUnit;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '生产月份'")
	public String getProductionMonth() {
		return productionMonth;
	}

	public void setProductionMonth(String productionMonth) {
		this.productionMonth = productionMonth;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '年份'")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	//	@Lob
//	@Basic(fetch = FetchType.EAGER)
//	@Column(columnDefinition = "LONGTEXT")
	@Column(columnDefinition="VARCHAR(255) COMMENT '备注'")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(columnDefinition="DOUBLE COMMENT '生产线数量'")
	public Double getProductionLines() {
        return productionLines;
    }

    public void setProductionLines(Double productionLines) {
        this.productionLines = productionLines;
    }
    @Column(columnDefinition="DATETIME COMMENT '停运时间'")
    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    @ManyToOne
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public EnterpriseEmissionsManagement getEnterpriseEmissionsManagement() {
		return enterpriseEmissionsManagement;
	}

	public void setEnterpriseEmissionsManagement(EnterpriseEmissionsManagement enterpriseEmissionsManagement) {
		this.enterpriseEmissionsManagement = enterpriseEmissionsManagement;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '工段名称'")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
	
	
	
	@Column(columnDefinition="DOUBLE COMMENT '设计生产能力（产品吨/年）'")
	public Double getProductCapacity() {
		return productCapacity;
	}

	public void setProductCapacity(Double productCapacity) {
		this.productCapacity = productCapacity;
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
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '生产工艺'")
	public String getProductionTechnique() {
		return productionTechnique;
	}

	public void setProductionTechnique(String productionTechnique) {
		this.productionTechnique = productionTechnique;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '是否封闭作业'")
	public String getIsSealing() {
		return isSealing;
	}

	public void setIsSealing(String isSealing) {
		this.isSealing = isSealing;
	}

	@Column(columnDefinition="DATETIME COMMENT '整改时间'")
	public Date getAbarbeitungTime() {
		return abarbeitungTime;
	}

	public void setAbarbeitungTime(Date abarbeitungTime) {
		this.abarbeitungTime = abarbeitungTime;
	}

	@Override
	public String toString() {
		return "Section [enterpriseEmissionsManagement=" + enterpriseEmissionsManagement + ", name=" + name
				+ ", product=" + product + ", rawMaterials1=" + rawMaterials1 + ", rawMaterials2=" + rawMaterials2
				+ ", rawMaterials3=" + rawMaterials3 + ", rawMaterials4=" + rawMaterials4 + ", rawMaterials5="
				+ rawMaterials5 + ", project=" + project + ", description=" + description + ", fuelType=" + fuelType
				+ ", fuelConsumption=" + fuelConsumption + ", fuelUnit=" + fuelUnit + "]";
	}

	
	
	
	
	

}

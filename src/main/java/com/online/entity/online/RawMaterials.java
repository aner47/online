package com.online.entity.online;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.util.SpringUtils;
/**
 * 辅料信息
 * @author zuozhiping
 *
 */
@EntityDescription(caption="原辅料")
@Entity
@Table(name="ol_raw_materials")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_raw_materials")
public class RawMaterials extends BaseEntity<Integer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4734247264737711844L;
	
	public enum RawMaterialsType{
		/**
		 * 有机物
		 */
		organic,
		/**
		 * 无机物
		 */
		inorganic
	}
	
//	public enum SolventType{
//		/**
//		 * 油性
//		 */
//		oil,
//		/**
//		 * 水性
//		 */
//		water
//	}
	
	/**
	 * 溶剂类别
	 */
	@ColumnDescription(caption="溶剂类别")
	private String solventCategory;

	/**
	 * 活动数据计量单位
	 */
	@ColumnDescription(caption="计量单位")
	private String unit;
	
	/**
	 * 辅料名称
	 */
	@ColumnDescription(caption="名称")
	private String name ; 
	
	/**
	 * 年消耗量
	 */
	@ColumnDescription(caption="年消耗量")
	private Double consumption;
	
	/**
	 * 物料类型
	 */
	@ColumnDescription(caption="物料类型")
	private RawMaterialsType rawMaterialsType;
	
	
	/**
	 * 溶剂类型
	 */
	@ColumnDescription(caption="溶剂类型")
	private String solventType; 
	
	/**
	 * 企业信息
	 */
	private  Integer enterprise;
	
	/**
	 * 项目
	 */
	private Integer project;
	/**
     * 备注
     */
	@ColumnDescription(caption="备注")
    private String description;
    
	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
	 * voc含量
	 */
	@ColumnDescription(caption="voc含量")
	private Double vocRate;
	
	/**
	 * voc挥发度（%）
	 */
	@ColumnDescription(caption="voc挥发度（%）")
	private Double vocVolatility;
	
	
	private Integer sectionId;
	
	/**
	 * 年份
	 */
	@ColumnDescription(caption="年份")
	private String year;
	
	
	/**
	 * 含硫量
	 */
	@ColumnDescription(caption="含硫量")
	private Double fuelSulfurContent;
	
	/**
	 * 原料类型
	 */
	@ColumnDescription(caption="原料类型")
	private String type;
	
	/**
	 * 产品名称
	 */
	@ColumnDescription(caption="产品名称")
	private String productName;
	

	@Column(columnDefinition="VARCHAR(255) COMMENT '产品名称'")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '原料类型'")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(columnDefinition="DOUBLE COMMENT '含硫量'")
	public Double getFuelSulfurContent() {
		return fuelSulfurContent;
	}

	public void setFuelSulfurContent(Double fuelSulfurContent) {
		this.fuelSulfurContent = fuelSulfurContent;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '年份'")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(updatable=false)
	public Integer getProject() {
		return project;
	}

	public void setProject(Integer project) {
		this.project = project;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '辅料名称'")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '单位'")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(columnDefinition="DOUBLE COMMENT '年消耗量'")
	public Double getConsumption() {
		return consumption;
	}

	public void setConsumption(Double consumption) {
		this.consumption = consumption;
	}
	@Enumerated(EnumType.STRING)
	public RawMaterialsType getRawMaterialsType() {
		return rawMaterialsType;
	}

	public void setRawMaterialsType(RawMaterialsType rawMaterialsType) {
		this.rawMaterialsType = rawMaterialsType;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '溶剂类型'")
	public String getSolventType() {
		return solventType;
	}

	public void setSolventType(String solventType) {
		this.solventType = solventType;
	}
	
	@Column(updatable=false)
	public Integer getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Integer enterprise) {
		this.enterprise = enterprise;
	}

	@PrePersist
	private void init(){
		setEnterprise(SpringUtils.getPrincipal().getEnterpriseId());
		setProject(SpringUtils.getProjectId());
	}
	@Column(columnDefinition="DOUBLE COMMENT 'voc含量'")
	public Double getVocRate() {
		return vocRate;
	}

	public void setVocRate(Double vocRate) {
		this.vocRate = vocRate;
	}
	@Column(columnDefinition="DOUBLE COMMENT 'voc挥发度（%）'")
	public Double getVocVolatility() {
		return vocVolatility;
	}

	public void setVocVolatility(Double vocVolatility) {
		this.vocVolatility = vocVolatility;
	}
	@Column(columnDefinition="INT(11) COMMENT '工段id'")
	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '溶剂类别'")
	public String getSolventCategory() {
		return solventCategory;
	}

	public void setSolventCategory(String solventCategory) {
		this.solventCategory = solventCategory;
	}

	@Override
	public String toString() {
		return "RawMaterials [unit=" + unit + ", name=" + name + ", consumption=" + consumption + ", rawMaterialsType="
				+ rawMaterialsType + ", solventType=" + solventType + ", project=" + project + "]";
	}

	

}

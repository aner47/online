package com.online.entity.online;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.entity.BaseEntity;
/**
 * 标准原辅料
 * 
 * @author zuozhiping
 *
 */
@Entity
@Table(name="ol_raw_materials_standard")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_raw_materials_standard")
public class RawMaterialsStandardName extends BaseEntity<Integer>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5897882747969883106L;

	/**
	 * 行业代码
	 */
	private String industryCode;
	
	/**
	 * 名称
	 */
	private String standardName;
	
	/**
	 * 标准单位
	 */
	private String standardUnit;
		
	/**
	 * 常用转换单位
	 */
	private String defaultUnit;
	
	
	/**
	 * 常用转换系数
	 */
	private Double defaultConversionFactor;
	
	/**
	 * 污染源分类代码
	 */
	private String pscc;
	
	private String status ="unsynchronized";

	@Column(columnDefinition="VARCHAR(255) COMMENT '行业代码'")
	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '名称'",unique=true,nullable=false)
	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '污染源分类代码'",unique=true,nullable=false)
	public String getPscc() {
		return pscc;
	}

	public void setPscc(String pscc) {
		this.pscc = pscc;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '标准单位'",nullable=false)
	public String getStandardUnit() {
		return standardUnit;
	}

	public void setStandardUnit(String standardUnit) {
		this.standardUnit = standardUnit;
	}
	

	@Column(columnDefinition="VARCHAR(255) COMMENT '常用转换单位'")
	public String getDefaultUnit() {
		return defaultUnit;
	}
	
	public void setDefaultUnit(String defaultUnit) {
		this.defaultUnit = defaultUnit;
	}
	@Column(columnDefinition="DOUBLE COMMENT '常用转换系数'")
	public Double getDefaultConversionFactor() {
		return defaultConversionFactor;
	}

	public void setDefaultConversionFactor(Double defaultConversionFactor) {
		this.defaultConversionFactor = defaultConversionFactor;
	}

	@Column(columnDefinition="VARCHAR(255) DEFAULT 'unsynchronized' COMMENT '同步状态'")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}

package com.online.entity.online;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.entity.BaseEntity;

/**
 * 产品名称配置
 * @author zuozhiping
 *
 */
@Entity
@Table(name="ol_product_standard_name")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_product_standard_name")
public class ProductStandardName extends BaseEntity<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2545228479238327166L;

	/**
	 * 标准名称
	 */
	private String standardName;
	
	/**
	 * 行业代码
	 */
	private String industryCode;
	
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
	 * 污染源分类
	 */
	private String pscc;
	
	private String status ="unsynchronized";
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '标准名称'",unique=true,nullable=false)
	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '行业代码'")
	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '污染源分类'",unique=true,nullable=false)
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

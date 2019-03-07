package com.online.entity.online;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.online.entity.BaseEntity;

@Entity
@Table(name="ol_raw_meterials_unit_config",uniqueConstraints={@UniqueConstraint(columnNames={"nameCh", "rawMaterialsOtherName"})})
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_raw_meterials_unit_config")
public class RawMeterialsUnitConfig extends BaseEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2556777014147688413L;

	/**
	 * 原辅料名称配置
	 */
	private RawMaterialsOtherName rawMaterialsOtherName;
	
	/**
	 * 计量单位英文名称
	 */
	private String nameEn;
	
	/**
	 * 计量单位中文名称
	 */
	private String nameCh;
	  
	/**
	 * 转换系数
	 */
	private Double conversionFactor;
	
	
	private String status ="unsynchronized";

	@ManyToOne
	@JoinColumn(name="rawMaterialsOtherName")
	public RawMaterialsOtherName getRawMaterialsOtherName() {
		return rawMaterialsOtherName;
	}

	public void setRawMaterialsOtherName(RawMaterialsOtherName rawMaterialsOtherName) {
		this.rawMaterialsOtherName = rawMaterialsOtherName;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '计量单位英文名称'")
	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '计量单位中文名称'")
	public String getNameCh() {
		return nameCh;
	}

	public void setNameCh(String nameCh) {
		this.nameCh = nameCh;
	}
	@Column(columnDefinition="DOUBLE COMMENT '转换系数'")
	public Double getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(Double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}
	
	@Column(columnDefinition="VARCHAR(255) DEFAULT 'unsynchronized' COMMENT '同步状态'")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}

package com.online.entity.online;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online.entity.BaseEntity;

/**
 * 原辅料归类
 * @author zuozhiping
 *
 */
@Entity
@Table(name="ol_raw_materials_other",uniqueConstraints={@UniqueConstraint(columnNames={"otherName", "industryCode"})})
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_raw_materials_other")
public class RawMaterialsOtherName extends BaseEntity<Integer> {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 行业代码
	 */
	private String industryCode;
	
	/**
	 * 名称
	 */
	private String otherName;

	/**
	 * 标准名称
	 */
	private RawMaterialsStandardName rawMaterialsStandardName;
	
	private String status ="unsynchronized";
	

	private Set<RawMeterialsUnitConfig> rawMeterialsUnitConfigs;
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '行业代码'")
	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '名称'")
	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	@ManyToOne
	public RawMaterialsStandardName getRawMaterialsStandardName() {
		return rawMaterialsStandardName;
	}

	public void setRawMaterialsStandardName(RawMaterialsStandardName rawMaterialsStandardName) {
		this.rawMaterialsStandardName = rawMaterialsStandardName;
	}
	
	@JsonIgnore
	@XmlTransient
	@OneToMany(mappedBy="rawMaterialsOtherName",fetch=FetchType.EAGER)
	public Set<RawMeterialsUnitConfig> getRawMeterialsUnitConfigs() {
		return rawMeterialsUnitConfigs;
	}

	public void setRawMeterialsUnitConfigs(Set<RawMeterialsUnitConfig> rawMeterialsUnitConfigs) {
		this.rawMeterialsUnitConfigs = rawMeterialsUnitConfigs;
	}


	@Column(columnDefinition="VARCHAR(255) DEFAULT 'unsynchronized' COMMENT '同步状态'")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}

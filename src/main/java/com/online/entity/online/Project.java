package com.online.entity.online;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.entity.SysProductIndustry;

@EntityDescription(caption="项目")
@Entity
@Table(name="ol_project")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_project")
public class Project extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1478992801369502218L;

	/**
	 * 地区编码
	 */
	@ColumnDescription(caption="地区")
	private String areaCode;
	
	/**
	 * 项目名称
	 */
	@ColumnDescription(caption="名称")
	private String name;
	
	/**
	 * 开始时间
	 */
	@ColumnDescription(caption="开始时间",inputType=ColumnDescription.INPUT_TYPE_DATE)
	private Date startDate;
	
	/**
	 * 结束时间
	 */
	@ColumnDescription(caption="结束时间",inputType=ColumnDescription.INPUT_TYPE_DATE)
	private Date endDate;
	
	/**
	 * 当前状态
	 */
	private String status ;
	
	/**
	 * 邀请码
	 */
	@ColumnDescription(caption="邀请码")
	private String invitationCode;
	
	/**
	 * 数据年份
	 */
	@ColumnDescription(caption="数据年份")
	private String dataYear;
	
	/**
	 * 省
	 */
	private String provinces ;
	
	/**
	 * 市
	 */
	private String city ;
	
	/**
	 * 县
	 */
	private String county ;
	
	
	private String sysProductIndustrys;
	
	/**
	 * 项目类型，普通类型和部门类型
	 */
	private String projectType;
	
	
	
	
	
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '县'")
	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '产品行业'")
	public String getSysProductIndustrys() {
		return sysProductIndustrys;
	}

	public void setSysProductIndustrys(String sysProductIndustrys) {
		this.sysProductIndustrys = sysProductIndustrys;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '地区编码'")
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(unique=true,nullable=false,columnDefinition="VARCHAR(255) COMMENT '项目名称'")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(nullable=false,columnDefinition="DATETIME COMMENT '开始时间'")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(nullable=false,columnDefinition="DATETIME COMMENT '结束时间'")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '当前状态'")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(unique=true,length=8,columnDefinition="VARCHAR(255) COMMENT '邀请码'")
	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '数据年份'")
	public String getDataYear() {
		return dataYear;
	}

	public void setDataYear(String dataYear) {
		this.dataYear = dataYear;
	} 
	@Column(columnDefinition="VARCHAR(255) COMMENT '省'")
	public String getProvinces() {
		return provinces;
	}

	public void setProvinces(String provinces) {
		this.provinces = provinces;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '市'")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}

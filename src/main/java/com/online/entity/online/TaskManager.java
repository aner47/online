package com.online.entity.online;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.entity.online.Enterprise.EnterpriseType;
import com.online.entity.online.embeddable.Address;

/**
 * 调查任务管理
 * @author DEV2
 *
 */
@EntityDescription(caption="调查任务")
@Entity
@Table(name="ol_task_manager")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_task_manager")
public class TaskManager extends BaseEntity<Long>  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5252214804193845528L;

	/**
	 * 企业名称
	 */
	@ColumnDescription(caption="企业名称")
	private String  enterpriseName;
	
	/**
	 * 联系人
	 */
	@ColumnDescription(caption="联系人")
	private String contactPerson;
	
	/**
	 * 联系电话
	 */
	@ColumnDescription(caption="联系电话")
	private String contactPhone;
	
	/**
	 * 县（地区）
	 */
	@ColumnDescription(caption="县（地区，街道）")
	private String countyName;
	
	/**
	 * 任务执行人
	 */
	@ColumnDescription(caption="执行人")
	private String executor;
	
	/**
	 * 详细地址
	 */
	@ColumnDescription(caption="详细地址")
	private String detailAddress;
	
	/**
	 * 任务状态
	 */
	@ColumnDescription(caption="任务状态")
	private String status;
	
	/**
	 * 所属项目Id
	 */
	@ColumnDescription(caption="所属项目Id")
	private Integer projectId;
	
	@ColumnDescription(caption="基础信息")
	private String baseInfo;
	
	/**
	 * 企业状态
	 */
	@ColumnDescription(caption="企业状态")
	private String enterpriseStatus;
	
	@ColumnDescription(caption="是否上报")
	private Boolean report;
	
	/**
	 * 备注
	 */
	@ColumnDescription(caption="备注")
	private String remarks;
	
	@ColumnDescription(caption="照片路径")
	private String photoPath;
	
	@ColumnDescription(caption="上报企业名称")
	private String reportEnterpriseName;
	
	@ColumnDescription(caption="相关企业填报信息")
	private Enterprise enterprise;
	
	/**
	 * 调查人
	 */
	@ColumnDescription(caption="调查人")
	private String inquirer;
	
	/**
	 * 调查人联系电话
	 */
	@ColumnDescription(caption="调查人联系电话")
	private String inquirerPhone;
	
	@ColumnDescription(caption="重复任务")
	private Long repeatId;
	
	@ColumnDescription(caption="搬迁日期")
	private Date relocationDate;
	
	@ColumnDescription(caption="搬迁地址")
	private String relocationAddress;
	
	private Address address;
	
	/**
	 * 填表类型
	 */
	private EnterpriseType enterpriseType;
	
	/**
	 * 组织机构代码/社会统一信用码
	 */
	private String code;
	
	/**
	 * 法人
	 */
	@ColumnDescription(caption="法人")
	private String corporation;
	
	
	/**
	 * 已调查企业id
	 */
	@ColumnDescription(caption="已调查企业id")
	private Integer alreadyEnterpriseId;
	
	/**
	 * 输入行业
	 */
	@ColumnDescription(caption="输入行业")
	private String inputIndustry;
	
	
	
	
	/**
	 * 项目类型
	 */
	@ColumnDescription(caption="项目类型")
	private Integer projectTypeId;
	
	
	
	
	

	

	public Integer getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(Integer projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public String getInputIndustry() {
		return inputIndustry;
	}

	public void setInputIndustry(String inputIndustry) {
		this.inputIndustry = inputIndustry;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Column(length=1000)
	public String getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(String baseInfo) {
		this.baseInfo = baseInfo;
	}

	public String getEnterpriseStatus() {
		return enterpriseStatus;
	}

	public void setEnterpriseStatus(String enterpriseStatus) {
		this.enterpriseStatus = enterpriseStatus;
	}

	public Boolean getReport() {
		return report;
	}

	public void setReport(Boolean report) {
		this.report = report;
	}

	public String getReportEnterpriseName() {
		return reportEnterpriseName;
	}

	public void setReportEnterpriseName(String reportEnterpriseName) {
		this.reportEnterpriseName = reportEnterpriseName;
	}

	@ManyToOne(cascade={CascadeType.ALL})
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@Enumerated(EnumType.STRING)
	public EnterpriseType getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(EnterpriseType enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	public String getInquirer() {
		return inquirer;
	}

	public void setInquirer(String inquirer) {
		this.inquirer = inquirer;
	}

	public String getInquirerPhone() {
		return inquirerPhone;
	}

	public void setInquirerPhone(String inquirerPhone) {
		this.inquirerPhone = inquirerPhone;
	}
	public Long getRepeatId() {
		return repeatId;
	}

	public void setRepeatId(Long repeatId) {
		this.repeatId = repeatId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@DateTimeFormat(pattern="yyyyMMdd")
	public Date getRelocationDate() {
		return relocationDate;
	}

	public void setRelocationDate(Date relocationDate) {
		this.relocationDate = relocationDate;
	}

	public String getRelocationAddress() {
		return relocationAddress;
	}

	public void setRelocationAddress(String relocationAddress) {
		this.relocationAddress = relocationAddress;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '组织机构代码/社会统一信用码'")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '法人'")
	public String getCorporation() {
		return corporation;
	}
	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	
	@Column(columnDefinition="INTEGER COMMENT '已调查企业id'")
	public Integer getAlreadyEnterpriseId() {
		return alreadyEnterpriseId;
	}

	public void setAlreadyEnterpriseId(Integer alreadyEnterpriseId) {
		this.alreadyEnterpriseId = alreadyEnterpriseId;
	}

}

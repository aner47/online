package com.online.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online.annotation.ColumnDescription;
import com.online.entity.online.embeddable.Address;


/**
 * 名录库
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2018年1月30日 上午10:50:14 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return
 */
@Entity
@Table(name="sys_enterprise_dictionary")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_sys_enterprise_dictionary")
public class EnterpriseDictionary extends BaseEntity<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 企业名字
	 */
	private String enterpriseName ; 
	
	/**
	 * 县（地区）
	 */
	@ColumnDescription(caption="县（地区，街道）")
	private String countyName;
	
	/**
	 * 详细地址
	 */
	@ColumnDescription(caption="详细地址")
	private String detailAddress;
	
	/**
	 * 地址信息
	 */
	private Address address;
	
	/**
	 * 输入行业
	 */
	private String inputIndustry;
	
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 数据来源,提交人
	 */
	private String source;
	
	/**
	 * 组织机构代码/社会统一信用码
	 */
	private String code;
	
	/**
	 * 联系人
	 */
	@ColumnDescription(caption="联系人")
	private String contacts;
	
	/**
	 * 法人
	 */
	@ColumnDescription(caption="法人")
	private String corporation;
	
	/**
	 * 所属项目
	 */
	@ColumnDescription(caption="所属项目")
	private Integer projectId;
	
	/**
	 * 审核意见
	 */
	@ColumnDescription(caption="审核意见")
	private String opinion;
	
	/**
	 * 联系电话
	 */
	@ColumnDescription(caption="联系电话")
	private String contactsPhone;
	
	
	/**
	 * 已调查企业id
	 */
	@ColumnDescription(caption="已调查企业id")
	private Integer alreadyEnterpriseId;
	
	private EnterpriseDictionaryRep enterpriseDictionaryRep;
	
	
	
	
	/**
	 * 标记项
	 */
	@ColumnDescription(caption="标记项")
	private String sign;
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '标记项'")
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@JsonIgnore
	@ManyToOne
	public EnterpriseDictionaryRep getEnterpriseDictionaryRep() {
		return enterpriseDictionaryRep;
	}

	public void setEnterpriseDictionaryRep(EnterpriseDictionaryRep enterpriseDictionaryRep) {
		this.enterpriseDictionaryRep = enterpriseDictionaryRep;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '企业名称'",nullable=false)
	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '区县'")
	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '详细地址'")
	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}


	@Column(columnDefinition="VARCHAR(255) COMMENT '状态'")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '数据来源，提交人'")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '输入行业'")
	public String getInputIndustry() {
		return inputIndustry;
	}

	public void setInputIndustry(String inputIndustry) {
		this.inputIndustry = inputIndustry;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '组织机构代码/社会统一信用码'")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '联系人'")
	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '法人'")
	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	
	@Column(columnDefinition="INTEGER COMMENT '所属项目'")
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	
	@Column(columnDefinition="VARCHAR(255) COMMENT '审核意见'")
	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '联系电话'")
	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	@Column(columnDefinition="INTEGER COMMENT '已调查企业id'")
	public Integer getAlreadyEnterpriseId() {
		return alreadyEnterpriseId;
	}

	public void setAlreadyEnterpriseId(Integer alreadyEnterpriseId) {
		this.alreadyEnterpriseId = alreadyEnterpriseId;
	}
	
	
	
	
	
	
	
}

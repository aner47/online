package com.online.entity.online;

import java.util.Date;

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
import com.online.entity.online.Enterprise.EnterpriseType;
import com.online.entity.online.embeddable.Address;
import com.online.exportpdf.DataBean;

/**
 * 企业信息表
 * @author DEV2
 *
 */
@EntityDescription(caption="企业")
@Entity
@Table(name="ol_enterprise_backup")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_enterprise_backup")
public class EnterpriseBackup extends BaseEntity<Integer> implements DataBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4483290561418443847L;
	
	
	
	
	/**
	 * 企业账号
	 */
	private Integer account;

	/**
	 * 1.企业名称(公章)	河北省衡水市有限公司
	 */
	@ColumnDescription(caption="名称",filter=true)
	private String name;
	
	/**
	 * 行业类别
	 */
	@ColumnDescription(caption="行业类别大类",filter=true)
	private String industryCategoryCodeMain;
	
	/**
	 * 行业名称
	 */
	private String industryCategoryNameMain;
	
	/**
	 * 行业类别
	 */
	@ColumnDescription(caption="行业类别大类",filter=true)
	private String industryCategoryCodeMiddle;
	
	/**
	 * 行业名称
	 */
	private String industryCategoryNameMiddle;
	
	/**
	 * 组织机构代码 /社会统一信息代码	     
	 */
	@ColumnDescription(caption="组织编码",filter=true)
	private String code;
	


	/**
	 * 地址信息
	 */
	private Address address;

	/**
	 * 联系号码
	 */
	private String contactNumber;
	
	/**
	 * 联系人
	 */
	@ColumnDescription(caption="联系人")
	private String contacts;
	
	/**
	 * 企业类型
	 */
	@ColumnDescription(caption="企业类型")
	private EnterpriseType enterpriseType;
	
	
	/**
	 * 法人
	 */
	@ColumnDescription(caption="法人")
	private String corporation;
	
	
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
	
	
	
	/**
	 * 修改用户
	 */
	@ColumnDescription(caption="修改用户")
	private String updateUser;
	
	/**
	 * 旧企业id
	 */
	@ColumnDescription(caption="旧企业id")
	private String oldId;
	
	
	/** "创建日期"属性名称 */
	private Date oldCreateDate;

	/** "修改日期"属性名称 */
	private  Date oldModifyDate;

	/** "版本"属性名称 */
	private Long oldVersion;
	
	
	
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

	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	public EnterpriseType getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(EnterpriseType enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndustryCategoryCodeMain() {
		return industryCategoryCodeMain;
	}

	public void setIndustryCategoryCodeMain(String industryCategoryCodeMain) {
		this.industryCategoryCodeMain = industryCategoryCodeMain;
	}

	public String getIndustryCategoryNameMain() {
		return industryCategoryNameMain;
	}

	public void setIndustryCategoryNameMain(String industryCategoryNameMain) {
		this.industryCategoryNameMain = industryCategoryNameMain;
	}

	public String getIndustryCategoryCodeMiddle() {
		return industryCategoryCodeMiddle;
	}

	public void setIndustryCategoryCodeMiddle(String industryCategoryCodeMiddle) {
		this.industryCategoryCodeMiddle = industryCategoryCodeMiddle;
	}

	public String getIndustryCategoryNameMiddle() {
		return industryCategoryNameMiddle;
	}

	public void setIndustryCategoryNameMiddle(String industryCategoryNameMiddle) {
		this.industryCategoryNameMiddle = industryCategoryNameMiddle;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}



	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	@PrePersist
	public void defualtType(){
		if(this.enterpriseType == null){
			enterpriseType = EnterpriseType.NORMAL;
		}
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getOldId() {
		return oldId;
	}

	public void setOldId(String oldId) {
		this.oldId = oldId;
	}

	public Date getOldCreateDate() {
		return oldCreateDate;
	}

	public void setOldCreateDate(Date oldCreateDate) {
		this.oldCreateDate = oldCreateDate;
	}

	public Date getOldModifyDate() {
		return oldModifyDate;
	}

	public void setOldModifyDate(Date oldModifyDate) {
		this.oldModifyDate = oldModifyDate;
	}

	public Long getOldVersion() {
		return oldVersion;
	}

	public void setOldVersion(Long oldVersion) {
		this.oldVersion = oldVersion;
	}

	
	
	
	
}

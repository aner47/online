package com.online.entity.online;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.entity.online.embeddable.Address;
import com.online.exportpdf.DataBean;

/**
 * 企业信息表
 * @author DEV2
 *
 */
@EntityDescription(caption="企业")
@Entity
@Table(name="ol_enterprise")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_enterprise")
public class Enterprise extends BaseEntity<Integer> implements DataBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4483290561418443847L;
	
	public enum TaskType{
		UNTREATED,
		PROCESSED
	}
	
	/**
	 * 企业类型
	 */
	public enum EnterpriseType{
		/**
		 * 正常（详细）
		 */
		NORMAL,
		
		/**
		 * 简版
		 */
		SIMPLE,
		
		/**
		 * 单一锅炉
		 */
		SINGLE_BOILER,
		
		/**
		 * 施工工地
		 */
		CONSTRUCTION_SITE,
		/**
		 * 加油站
		 */
		PETROL_STATION,
		/**
		 * 干洗
		 */
		DRY_CLEAR,
		/**
		 * 汽修
		 */
		BREAKDOWN_SERVICE,
		/**
		 * 畜禽养殖
		 */
		BEASTS_BIRDS,
		/**
		 * 餐饮
		 */
		CATERING,
		/**
		 * 普查表
		 */
		GENERAL
		
		
	}
	
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
	@ColumnDescription(caption="行业类别大类Code",filter=true)
	private String industryCategoryCodeMain;
	
	/**
	 * 行业名称
	 */
	private String industryCategoryNameMain;
	
	/**
	 * 行业类别
	 */
	@ColumnDescription(caption="行业类别中类",filter=true)
	private String industryCategoryCodeMiddle;
	
	/**
	 * 行业名称
	 */
	private String industryCategoryNameMiddle;
	
	/**
	 * 行业类别(小类)
	 */
	@ColumnDescription(caption="行业类别中类",filter=true)
	private String industryCategoryCodeSmall;
	
	/**
	 * 行业名称
	 */
	private String industryCategoryNameSmall;
	
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
	 * 项目
	 */
	private Set<Project>  projects ;
	
	/**
	 * 任务状态
	 */
	private TaskType taskType;
	
	/**
	 * 是否散乱污
	 */
	@ColumnDescription(caption="是否散乱污")
	private String isSanluanwu;
	
	/**
	 * 图片地址
	 */
	@ColumnDescription(caption="图片地址")
	private String photoUrl;
	
	
	/**
	 * 填报状态 1-未提交，2已提交
	 
	@ColumnDescription(caption="填报状态")
	private Integer status;
	*/
	
	/**
	 * 审核意见
	 
	@ColumnDescription(caption="审核意见")
	private String opinion;
	*/
	
	/**
	 * 邮箱
	 */
	@ColumnDescription(caption="邮箱")
	private String email;
	
	/**
	 * 企业状态
	
	@ColumnDescription(caption="企业状态")
	private String enterpriseStatus;
	 */
	/**
	 * 是否纳入应急名单
	 */
	@ColumnDescription(caption="是否纳入应急名单")
	private String isEmergency;
	
	/**
	 * 是否错峰企业
	 */
	@ColumnDescription(caption="是否错峰企业")
	private String isStaggerFastigium;
	
	
	
	/**
	 * 产品行业
	 */
	@ColumnDescription(caption="产品行业")
	private String productIndustry;
	
	

	@Column(columnDefinition="VARCHAR(255) COMMENT '产品行业'")
	public String getProductIndustry() {
		return productIndustry;
	}

	public void setProductIndustry(String productIndustry) {
		this.productIndustry = productIndustry;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '邮箱'")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	/*@Column(columnDefinition="VARCHAR(255) COMMENT '审核意见'")
	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}*/
	/*@Column(columnDefinition="INT(11) COMMENT '填报状态'")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}*/
	@Column(columnDefinition="VARCHAR(255) COMMENT '是否散乱污'")
	public String getIsSanluanwu() {
		return isSanluanwu;
	}

	public void setIsSanluanwu(String isSanluanwu) {
		this.isSanluanwu = isSanluanwu;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '图片地址'")
	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition="VARCHAR(255) COMMENT '任务状态'")
	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="ol_project_enterprise")
	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	
	public void addProject(Project project){
		if(this.projects == null){
			Set<Project> sets = new HashSet<>();
			setProjects(sets);
		}
		if(!this.projects.contains(project)){
			this.projects.add(project);
        }
		
	}
	
	public void removeProject(Project project){
        if(this.projects.contains(project)){
            this.projects.remove(project);
        }
    }
	@Column(columnDefinition="VARCHAR(255) COMMENT '调查人'")
	public String getInquirer() {
		return inquirer;
	}

	public void setInquirer(String inquirer) {
		this.inquirer = inquirer;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '调查人联系电话'")
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
	@Column(columnDefinition="VARCHAR(255) COMMENT '名称'")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '行业类别大类Code'")
	public String getIndustryCategoryCodeMain() {
		return industryCategoryCodeMain;
	}

	public void setIndustryCategoryCodeMain(String industryCategoryCodeMain) {
		this.industryCategoryCodeMain = industryCategoryCodeMain;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '行业类别大类名称'")
	public String getIndustryCategoryNameMain() {
		return industryCategoryNameMain;
	}

	public void setIndustryCategoryNameMain(String industryCategoryNameMain) {
		this.industryCategoryNameMain = industryCategoryNameMain;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '行业类别中类Code'")
	public String getIndustryCategoryCodeMiddle() {
		return industryCategoryCodeMiddle;
	}

	public void setIndustryCategoryCodeMiddle(String industryCategoryCodeMiddle) {
		this.industryCategoryCodeMiddle = industryCategoryCodeMiddle;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '行业类别中类名称'")
	public String getIndustryCategoryNameMiddle() {
		return industryCategoryNameMiddle;
	}

	public void setIndustryCategoryNameMiddle(String industryCategoryNameMiddle) {
		this.industryCategoryNameMiddle = industryCategoryNameMiddle;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '组织编码'")
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
	@Column(columnDefinition="VARCHAR(255) COMMENT '联系号码'")
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '联系人'")
	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}


	@Column(columnDefinition="INT(11) COMMENT '企业账号'")
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
		if(getAddress() == null){
			taskType = TaskType.UNTREATED;
		}else{
			taskType = TaskType.PROCESSED;
		}
	}
	
	@PreUpdate
	public void preUpdate(){
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '法人'")
	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '行业类别小类Code'")
	public String getIndustryCategoryCodeSmall() {
		return industryCategoryCodeSmall;
	}

	public void setIndustryCategoryCodeSmall(String industryCategoryCodeSmall) {
		this.industryCategoryCodeSmall = industryCategoryCodeSmall;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '行业类别小类名称'")
	public String getIndustryCategoryNameSmall() {
		return industryCategoryNameSmall;
	}

	public void setIndustryCategoryNameSmall(String industryCategoryNameSmall) {
		this.industryCategoryNameSmall = industryCategoryNameSmall;
	}

	/*@Column(columnDefinition="VARCHAR(255) COMMENT '企业状态'")
	public String getEnterpriseStatus() {
		return enterpriseStatus;
	}

	public void setEnterpriseStatus(String enterpriseStatus) {
		this.enterpriseStatus = enterpriseStatus;
	}*/

	@Column(columnDefinition="VARCHAR(255) COMMENT '是否纳入应急名单'")
	public String getIsEmergency() {
		return isEmergency;
	}

	public void setIsEmergency(String isEmergency) {
		this.isEmergency = isEmergency;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '是否错峰企业'")
	public String getIsStaggerFastigium() {
		return isStaggerFastigium;
	}

	public void setIsStaggerFastigium(String isStaggerFastigium) {
		this.isStaggerFastigium = isStaggerFastigium;
	}

	
	
	
	
	
	
}

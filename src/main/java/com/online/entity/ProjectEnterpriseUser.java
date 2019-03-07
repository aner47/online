package com.online.entity;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.online.annotation.ColumnDescription;
import com.online.entity.online.Enterprise;
import com.online.entity.online.Project;

@Cacheable
@Entity
@Table(name="sys_project_enterprise_user",uniqueConstraints = {@UniqueConstraint(columnNames={"project", "enterprise"})})
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_sys_project_enterprise_user")
public class ProjectEnterpriseUser extends BaseEntity<Integer> {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Project project;
	
	private Enterprise  enterprise;
	
	private SystemUser user;
	
	/**
	 * 企业任务状态
	 */
	@ColumnDescription(caption="企业任务状态")
	private String taskStatus;
	
	
	/**
	 * 项目类型
	 */
	@ColumnDescription(caption="项目类型")
	private Integer projectType;
	
	
	/**
	 * PSCC任务状态
	 */
	@ColumnDescription(caption="PSCC任务状态")
	private String pscctaskStatus;
	
	
	/**
	 * 审核员
	 */
	@ColumnDescription(caption="审核员")
	private Integer assessor;
	
	/**
	 * 提交时间
	 */
	private Date submitDate;
	
	
	/**
	 * 审核意见
	 */ 
	@ColumnDescription(caption="审核意见")
	private String opinion;
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '审核意见'")
	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	@Column(columnDefinition="DATETIME COMMENT '提交时间'")
	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	
	
	@Column(columnDefinition="INT COMMENT '审核员'")
	public Integer getAssessor() {
		return assessor;
	}

	public void setAssessor(Integer assessor) {
		this.assessor = assessor;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT 'PSCC任务状态'")
	public String getPscctaskStatus() {
		return pscctaskStatus;
	}

	public void setPscctaskStatus(String pscctaskStatus) {
		this.pscctaskStatus = pscctaskStatus;
	}

	@ManyToOne
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	@ManyToOne
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	@ManyToOne
	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '企业任务状态'")
	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '项目类型'")
	public Integer getProjectType() {
		return projectType;
	}

	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}
	

}

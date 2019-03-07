package com.online.entity.online;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;

/**
 * PSCC任务状态管理
 */
@EntityDescription(caption="PSCC任务状态管理")
@Entity
@Table(name="ol_pscc_status",uniqueConstraints={@UniqueConstraint(columnNames={"psccTaskId", "enterpriseId"})})
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_pscc_status")
public class PsccStatusTaskManage extends BaseEntity<Integer>  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5252214804193845528L;

	
	/**
	 * psss任务Id
	 */
	@ColumnDescription(caption="psss任务Id")
	private Integer psccTaskId;
	
	/**
	 * 企业Id
	 */
	@ColumnDescription(caption="企业Id")
	private Integer enterpriseId;
	
	/**
	 * 项目Id
	 */
	@ColumnDescription(caption="项目Id")
	private Integer projectId;
	
	/**
	 * pscc任务管理状态
	 */
	@ColumnDescription(caption="pscc任务管理状态")
	private String psccTaskStatus;
	
	
	@Column(columnDefinition="INTEGER COMMENT '项目Id'")
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	@Column(columnDefinition="INTEGER COMMENT 'psss任务Id'")
	public Integer getPsccTaskId() {
		return psccTaskId;
	}

	public void setPsccTaskId(Integer psccTaskId) {
		this.psccTaskId = psccTaskId;
	}

	@Column(columnDefinition="INTEGER COMMENT '企业Id'")
	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getPsccTaskStatus() {
		return psccTaskStatus;
	}

	public void setPsccTaskStatus(String psccTaskStatus) {
		this.psccTaskStatus = psccTaskStatus;
	}

	
	
	

}

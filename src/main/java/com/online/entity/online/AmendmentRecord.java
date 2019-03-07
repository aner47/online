package com.online.entity.online;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.entity.BaseEntity;
import com.online.exportpdf.DataBean;
import com.online.util.SpringUtils;

/**
 * 锅炉信息表
 * 
 * @author DEV2
 *
 */


@Entity
@Table(name="ol_amendment_record")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_amendment_record")
public  class AmendmentRecord extends BaseEntity<Integer> implements DataBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9222660567026914264L;

	/**
	 * 修改者
	 */
	private String modifier;
	
	/**
	 * 修改内容
	 */
	private String description;
	
	
	/**
	 * 项目
	 */
	private Project project;
	
	private Enterprise enterprise;
	
	
	
	

	@Column(columnDefinition="VARCHAR(255) COMMENT '修改者'")
	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	
	@Column(columnDefinition="VARCHAR(4000) COMMENT '修改内容'",updatable = false, length = 4000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	

	@PrePersist
	public void initProjectEnterpise(){
		project = SpringUtils.getCurrentProject();
		enterprise = SpringUtils.getCurrentEnterprise();
	}


	
}

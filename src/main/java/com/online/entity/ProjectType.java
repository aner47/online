package com.online.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.online.annotation.ColumnDescription;

@Cacheable
@Entity
@Table(name="sys_project_type",uniqueConstraints = {@UniqueConstraint(columnNames={"project","enterpriseType", "projectType"})})
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_sys_project_type")
public class ProjectType extends BaseEntity<Integer> {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer project;
	
	/**
	 * 企业类型
	 */
	private String enterpriseType;
	
	/**
	 * 项目类型
	 */
	@ColumnDescription(caption="项目类型")
	private String projectType;
	
	
	/**
	 * 菜单
	 */
	private String menus;
	
	/**
	 * 显示菜单名称
	 */
	private String menusName;
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '菜单'")
	public String getMenus() {
		return menus;
	}

	public void setMenus(String menus) {
		this.menus = menus;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '显示菜单名称'")
	public String getMenusName() {
		return menusName;
	}

	public void setMenusName(String menusName) {
		this.menusName = menusName;
	}
	
	
	public Integer getProject() {
		return project;
	}

	public void setProject(Integer project) {
		this.project = project;
	}

	public String getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	

}

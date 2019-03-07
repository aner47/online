package com.online.entity.online;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;


@EntityDescription(caption="照片文件")
@Entity
@Table(name="ol_photo_file")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_photo_file")
public class PhotoFile extends BaseEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -592294576972791475L;

	/**
	 * 文件名称
	 */
	private String filename;
	
	/**
	 * 链接地址
	 */
	private String url;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 项目
	 */
	private Project project;
	
	/**
	 * 企业
	 */
	private Enterprise enterprise;
	
	/**
	 * 部门类型
	 */
	private String departmentType;
	
	
	

	public String getDepartmentType() {
		return departmentType;
	}

	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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
	
	
	

}

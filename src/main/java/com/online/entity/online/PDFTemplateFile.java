package com.online.entity.online;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;


@EntityDescription(caption="pdf模板文件")
@Entity
@Table(name="ol_pdftemplate_file")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_pdftemplate_file")
public class PDFTemplateFile extends BaseEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -592294576972791475L;
	
	/**
	 * pdf模板文件名称 -->固定
	 */
	private String enterpriseType;
	
	/**
	 * pdf项目模板名称 -->对应导出表格的模块
	 */
	private String projectTemplateName;
	
	/**
	 * 项目模块名称 -->对应基本信息的模块
	 */
	private String projectModelName;
	
	

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
	
	private Integer projectId;
	
	
	private Integer projectTypeId;
	
	
	private String pdftemplateName;
	
	
	

	public String getPdftemplateName() {
		return pdftemplateName;
	}

	public void setPdftemplateName(String pdftemplateName) {
		this.pdftemplateName = pdftemplateName;
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
	

	public String getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	public String getProjectTemplateName() {
		return projectTemplateName;
	}

	public void setProjectTemplateName(String projectTemplateName) {
		this.projectTemplateName = projectTemplateName;
	}
	public String getProjectModelName() {
		return projectModelName;
	}

	public void setProjectModelName(String projectModelName) {
		this.projectModelName = projectModelName;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	@Column(columnDefinition="INT COMMENT '项目类型'")
	public Integer getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(Integer projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	
	
	

}

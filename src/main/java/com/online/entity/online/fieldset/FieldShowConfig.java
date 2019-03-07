package com.online.entity.online.fieldset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.entity.BaseEntity;
import com.online.entity.ProjectType;
/**
 * 字段显示配置
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2018年1月16日 上午10:46:04 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return
 */
@Entity
@Table(name="sys_field_show_config")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_sys_field_show_config")
public class FieldShowConfig extends BaseEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5895698869795389906L;
	
	/**
	 * 项目id
	 */
	@ColumnDescription(caption="项目id",filter=true)
	private Integer projectId;
	
	/**
	 * 填表类型
	 */
	@ColumnDescription(caption="填表类型",filter=true)
	private String tableType;
	
	/**
	 * 显示模块
	 */
	@ColumnDescription(caption="显示模块",filter=true)
	private String fieldModule;
	
	/**
	 * 隐藏字段
	 */
	@ColumnDescription(caption="隐藏字段",filter=true)
	private String hideField;
	
	
	/**
	 * 项目类型
	 */
	@ColumnDescription(caption="项目类型")
	private ProjectType projectType;
	
	
	@Column(columnDefinition="INTEGER COMMENT '项目id'")
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '显示模块'")
	public String getFieldModule() {
		return fieldModule;
	}

	public void setFieldModule(String fieldModule) {
		this.fieldModule = fieldModule;
	}
	

	@Column(columnDefinition="VARCHAR(255) COMMENT '所属填表类型'")
	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	
	@Column(columnDefinition="TEXT COMMENT '隐藏字段'")
	public String getHideField() {
		return hideField;
	}

	public void setHideField(String hideField) {
		this.hideField = hideField;
	}

	@ManyToOne
	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	
	
	
}

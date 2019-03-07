package com.online.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;


/**
 * 名录库重复筛选
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2018年1月30日 上午10:50:14 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return
 */
@Entity
@Table(name="sys_enterprise_dictionary_rep")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_sys_enterprise_dictionary_rep")
public class EnterpriseDictionaryRep extends BaseEntity<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 所属项目
	 */
	@ColumnDescription(caption="所属项目")
	private Integer projectId;
	
	/**
	 * 分组名称
	 */
	@ColumnDescription(caption="分组名称")
	private String groupName;
	
	private Set<EnterpriseDictionary> setEnterpriseDictionary;


	@Column(columnDefinition="INTEGER COMMENT '所属项目'",nullable=false)
	public Integer getProjectId() {
		return projectId;
	}


	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '分组名称'",nullable=false)
	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@OneToMany(mappedBy="enterpriseDictionaryRep")
	public Set<EnterpriseDictionary> getSetEnterpriseDictionary() {
		return setEnterpriseDictionary;
	}


	public void setSetEnterpriseDictionary(Set<EnterpriseDictionary> setEnterpriseDictionary) {
		this.setEnterpriseDictionary = setEnterpriseDictionary;
	}
	
	
	
	
	
	
	
	
	
	
}

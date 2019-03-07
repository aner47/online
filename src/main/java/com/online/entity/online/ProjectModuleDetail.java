package com.online.entity.online;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;

/**
 * 项目模块导出模块明细
 * @author DEV2
 *
 */
@EntityDescription(caption="项目模块导出模块明细")
@Entity
@Table(name="ol_project_module_detail")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_project_module_detail")
public class ProjectModuleDetail extends BaseEntity<Integer> {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4317333379255950895L;
	/**
	 * 项目Id
	 */
	private Integer projectId;
	/**
	 * 所属项目模块
	 */
	private ProjectModuleInfo projectModuleInfo;
	
	/**
	 * 数据对象字段名称
	 */
	private String key;
	
	/**
	 * excel显示头标题
	 */
	private String head;
	
	/**
	 * 模块内部排序
	 */
	private short moduleOrder;
	
	/**
	 * 所属分组
	 */
	private String headgroup;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@ManyToOne
	public ProjectModuleInfo getProjectModuleInfo() {
		return projectModuleInfo;
	}

	public void setProjectModuleInfo(ProjectModuleInfo projectModuleInfo) {
		this.projectModuleInfo = projectModuleInfo;
	}

	@Column(name="module_key")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public short getModuleOrder() {
		return moduleOrder;
	}

	public void setModuleOrder(short moduleOrder) {
		this.moduleOrder = moduleOrder;
	}

	public String getHeadgroup() {
		return headgroup;
	}

	public void setHeadgroup(String headgroup) {
		this.headgroup = headgroup;
	}

	
	
	
	

}

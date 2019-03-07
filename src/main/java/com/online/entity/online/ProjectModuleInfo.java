package com.online.entity.online;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;

/**
 * 项目模块信息表
 * @author DEV2
 *
 */
@EntityDescription(caption="项目导出模块信息")
@Entity
@Table(name="ol_project_module_info")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_project_module_info")
public class ProjectModuleInfo extends BaseEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8622165580536479305L;
	/**
	 * 项目
	 */
	private Integer projectId;
	/**
	 * 模块类型
	 */
	private String moduleType;
	
	/**
	 * 模块排序
	 */
	private short order ;
	
	/**
	 * 状态
	 */
    private String status;
    
    /**
     * 模块名称
     */
    private String name;
    
    /**
     * 模块分类
     */
    @ColumnDescription(caption="模块分类")
    private String moduleClassification ;
    
    public String getModuleClassification() {
        return moduleClassification;
    }

    public void setModuleClassification(String moduleClassification) {
        this.moduleClassification = moduleClassification;
    }

    /**
     * 模板信息
     */
    private ProjectTemplates projectTemplates;
    /**
     * 项目模块明细
     */
    private List<ProjectModuleDetail> projectModuleDetails ; 
    
    @ManyToOne
	public ProjectTemplates getProjectTemplates() {
        return projectTemplates;
    }

    public void setProjectTemplates(ProjectTemplates projectTemplates) {
        this.projectTemplates = projectTemplates;
    }

    public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	@Column(name="module_order")
	public short getOrder() {
		return order;
	}

	public void setOrder(short order) {
		this.order = order;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy="projectModuleInfo",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<ProjectModuleDetail> getProjectModuleDetails() {
		return projectModuleDetails;
	}

	public void setProjectModuleDetails(List<ProjectModuleDetail> projectModuleDetails) {
		this.projectModuleDetails = projectModuleDetails;
	}
    
    
    public List<ProjectModuleDetail> sortProjectModuleDetails(){
    	 Collections.sort(projectModuleDetails,(o1,o2)->o1.getModuleOrder()-o2.getModuleOrder());
    	 return projectModuleDetails;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}

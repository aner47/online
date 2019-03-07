package com.online.entity.online;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.entity.online.Enterprise.EnterpriseType;

/**
 * 项目模板信息
 * @author DEV2
 *
 */
@EntityDescription(caption="项目模板信息")
@Entity
@Table(name="ol_project_template")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_project_template")
public class ProjectTemplates extends BaseEntity<Integer> {/**
     * 
     */
    private static final long serialVersionUID = 7837483345010708223L;

    
    /**
     * 项目id
     */
    private Integer projectId;
    /**
     * 填表类型（与企业类型一致）
     */
    private String templateType;
    
    
    private List<ProjectModuleInfo> projectModuleInfos;
    
    /**
     * 模板名称
     */
    private String name;
    
    /**
     * 模板类型
     */
    private String templatesType;
    
    
    

    public String getTemplatesType() {
		return templatesType;
	}

	public void setTemplatesType(String templatesType) {
		this.templatesType = templatesType;
	}

	public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    @OneToMany(mappedBy="projectTemplates",cascade=CascadeType.REMOVE)
	public List<ProjectModuleInfo> getProjectModuleInfos() {
		return projectModuleInfos;
	}

	public void setProjectModuleInfos(List<ProjectModuleInfo> projectModuleInfos) {
		this.projectModuleInfos = projectModuleInfos;
	}
    

    
    
}

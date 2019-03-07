package com.online.entity.online;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.online.entity.BaseEntity;
import com.online.entity.listener.EntityListener;

@EntityListeners(EntityListener.class)
@MappedSuperclass	
public class TaskListBase extends BaseEntity<Integer>{

	public enum TaskStatus {
	    /**
	     * 待分配
	     */
	    assigned,
	    /**
	     * 待处理
	     */
	    active,
	    /**
	     * 待审核
	     */
	    pendingreview,
	    /**
	     * 已审核
	     */
	    Audited,
	   
	}

    /**
     * 
     */
    private static final long serialVersionUID = 7209254774434021897L;

    /**
     * 任务状态
     */
    private TaskStatus status;
    
    /**
     * 任务类型
     */
    private String taskType;
    
    /**
     * 数据来源ID
     */
    private Integer dataSourceId;
    
    /**
     * 行业代码
     */
    private String industryCode;
    
    /**
     * 数据名称
     */
    private String dataName;
    
    /**
     * 数据单位
     */
    private String dataUnit;
    
    /**
     * 单位转换系数
     */
    private Double conversionFactor;
    
    /**
     * 标准名称
     */
    private String standardName;
    
    /**
     * 污染源分类代码
     */
    private String pscc;
    
    /**
     * 任务分配人
     */
    private String taskAccount;
    
    /**
     * 任务处理人
     */
    private String disposeAccount;
    
    /**
     * 任务复核人
     */
    private String checkAccount;

    /**
     * 描述
     */
    private String description;
    
    /**
     * 数据是否完整
     */
    private String isCompletion;
    
    /**
	 * 企业信息
	 */
	private  Integer enterprise;
	
	/**
	 * 项目
	 */
	private Integer project;
    
    
	@Column(updatable=false)
	public Integer getProject() {
		return project;
	}

	public void setProject(Integer project) {
		this.project = project;
	}
	
	@Column(updatable=false)
	public Integer getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Integer enterprise) {
		this.enterprise = enterprise;
	}
	

    public String getIsCompletion() {
		return isCompletion;
	}

	public void setIsCompletion(String isCompletion) {
		this.isCompletion = isCompletion;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisposeAccount() {
        return disposeAccount;
    }

    public void setDisposeAccount(String disposeAccount) {
        this.disposeAccount = disposeAccount;
    }

    public void setStatus(TaskStatus status) {
		this.status = status;
	}

    @Enumerated(EnumType.STRING)
    public TaskStatus getStatus() {
		return status;
	}

	public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public Integer getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Integer dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataUnit() {
        return dataUnit;
    }

    public void setDataUnit(String dataUnit) {
        this.dataUnit = dataUnit;
    }

    public Double getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(Double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public String getPscc() {
        return pscc;
    }

    public void setPscc(String pscc) {
        this.pscc = pscc;
    }

    public String getTaskAccount() {
        return taskAccount;
    }

    public void setTaskAccount(String taskAccount) {
        this.taskAccount = taskAccount;
    }

    public String getCheckAccount() {
        return checkAccount;
    }

    public void setCheckAccount(String checkAccount) {
        this.checkAccount = checkAccount;
    }

	
   
    
}

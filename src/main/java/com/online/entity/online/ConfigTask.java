package com.online.entity.online;

/**
 * 配置任务
 * @author DEV2
 *
 */
public class ConfigTask {

	/**
	 * 任务状态
	 */
	private String status;
	
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
	 * 任务填写人
	 */
	private String taskAccount;
	
	/**
	 * 任务复核人
	 */
	private String checkAccount;
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public Double getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(Double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getDataUnit() {
		return dataUnit;
	}

	public void setDataUnit(String dataUnit) {
		this.dataUnit = dataUnit;
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

package com.online;

import java.io.Serializable;

/**
 * 筛选
 * 
 * 
 * 
 */
public class CustomFilter implements Serializable {

	private static final long serialVersionUID = -8712382358441065075L;
	
	private Pageable pageable;

	//项目
	private String project;
	//用户
	private String user;
	//企业名称
	private String enterpriseName;
	//省
	private String provincesCode;
	//市
	private String cityCode;
	//县
	private String countyCode;
	//大类
	private String industryCategoryNameMain;
	//中类
	private String industryCategoryNameMiddle;
	//大类
	private String industryCategoryCodeMain;
	//中类
	private String industryCategoryCodeMiddle;
	//填表类型
	private String enterpriseType;
	//开始日期
	private String startDate;
	//结束日期
	private String endDate;
	//状态
	private String status;
	
	
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public CustomFilter(Pageable pageable) {
		super();
		this.pageable = pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}

	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}

	public String getProvincesCode() {
		return provincesCode;
	}

	public void setProvincesCode(String provincesCode) {
		this.provincesCode = provincesCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getIndustryCategoryNameMain() {
		return industryCategoryNameMain;
	}

	public void setIndustryCategoryNameMain(String industryCategoryNameMain) {
		this.industryCategoryNameMain = industryCategoryNameMain;
	}

	public String getIndustryCategoryNameMiddle() {
		return industryCategoryNameMiddle;
	}

	public void setIndustryCategoryNameMiddle(String industryCategoryNameMiddle) {
		this.industryCategoryNameMiddle = industryCategoryNameMiddle;
	}

	public String getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	public String getIndustryCategoryCodeMain() {
		return industryCategoryCodeMain;
	}

	public void setIndustryCategoryCodeMain(String industryCategoryCodeMain) {
		this.industryCategoryCodeMain = industryCategoryCodeMain;
	}

	public String getIndustryCategoryCodeMiddle() {
		return industryCategoryCodeMiddle;
	}

	public void setIndustryCategoryCodeMiddle(String industryCategoryCodeMiddle) {
		this.industryCategoryCodeMiddle = industryCategoryCodeMiddle;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	
	
	

}
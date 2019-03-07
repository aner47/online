package com.online.entity.online;

import java.io.Serializable;

/** 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2017年8月16日 下午2:32:19 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return  
	*/
public class Condition implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String project;

	private String provinces;
	
	private String city;
	
	private String county;
	
	private String startDate;
	
	private String endDate;
	
	private String industry_category_code_main;
	
	private String industry_category_code_middle;
	
	
	
	
	

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

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getProvinces() {
		return provinces;
	}

	public void setProvinces(String provinces) {
		this.provinces = provinces;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getIndustry_category_code_main() {
		return industry_category_code_main;
	}

	public void setIndustry_category_code_main(String industry_category_code_main) {
		this.industry_category_code_main = industry_category_code_main;
	}

	public String getIndustry_category_code_middle() {
		return industry_category_code_middle;
	}

	public void setIndustry_category_code_middle(String industry_category_code_middle) {
		this.industry_category_code_middle = industry_category_code_middle;
	}
	
	
	
	
	

}

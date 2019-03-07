package com.online.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.entity.online.Project;

/**
 * 产品行业
 */
@Cacheable
@Entity
@Table(name="sys_productindustry")
@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_sys_productindustry")
public class SysProductIndustry extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2405382569675588949L;
	/**
	 * 行业名称
	 */
	@ColumnDescription(caption="行业名称")
	private String industryName;
	
	/**
	 * 行业名称对应值
	 */
	@ColumnDescription(caption="行业名称对应值")
	private String industryValue;
	
	/**
	 * 排序
	 */
	@ColumnDescription(caption="排序")
	private int sort;
	
	@Column(unique = true)
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	@Column(unique = true)
	public String getIndustryValue() {
		return industryValue;
	}
	public void setIndustryValue(String industryValue) {
		this.industryValue = industryValue;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
	
}

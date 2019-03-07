package com.online.entity.online;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.entity.BaseEntity;

/**
 * 行业分类
 * @author wangpan
 * @2016年12月21日
 *
 */
@Entity
@Table(name="ol_category")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_category")
public class Category extends BaseEntity<Integer>{

	private static final long serialVersionUID = 4958934760696053684L;
	
	public enum CategoryLevel{
		/**
		 * 门类
		 */
		CATEGORIES,
		/**
		 * 大类
		 */
		MAIN,
		/**
		 * 中类
		 */
		MIDDLE,
		/**
		 * 小类
		 */
		SMALL
		
	}
	
	/**
	 * 分类代码
	 */
	private String code;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 分类说明
	 */
	private String description;

	/**
	 * 上级分类代码
	 */
	private String parent;
	
	/**
	 * 燃料煤污染源分类
	 */
	private String coalPscc;
	
	/**
	 * 燃料油污染源分类
	 */
	private String oilPscc;
	
	/**
	 * 天然气污染源分类
	 */
	private String naturalGasPscc;
	
	/**
	 * 焦炭污染源分类
	 */
	private String cokePscc;
	
	
	private CategoryLevel categoryLevel;
	@Column(columnDefinition="VARCHAR(255) COMMENT '分类代码'")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '分类名称'")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '备注'")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '上级分类代码'")
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '燃料煤污染源分类'")
	public String getCoalPscc() {
		return coalPscc;
	}

	public void setCoalPscc(String coalPscc) {
		this.coalPscc = coalPscc;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '燃料油污染源分类'")
	public String getOilPscc() {
		return oilPscc;
	}

	public void setOilPscc(String oilPscc) {
		this.oilPscc = oilPscc;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '天然气污染源分类'")
	public String getNaturalGasPscc() {
		return naturalGasPscc;
	}

	public void setNaturalGasPscc(String naturalGasPscc) {
		this.naturalGasPscc = naturalGasPscc;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '焦炭污染源分类'")
	public String getCokePscc() {
		return cokePscc;
	}

	public void setCokePscc(String cokePscc) {
		this.cokePscc = cokePscc;
	}
	@Enumerated(EnumType.STRING)
	public CategoryLevel getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(CategoryLevel categoryLevel) {
		this.categoryLevel = categoryLevel;
	}
	
}

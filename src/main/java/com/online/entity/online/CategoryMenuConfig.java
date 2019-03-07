package com.online.entity.online;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.entity.BaseEntity;

/**
 * 行业菜单配置
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2018年2月8日 下午4:39:05 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return
 */
@Entity
@Table(name="sys_category_menu_config")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_sys_category_menu_config")
public class CategoryMenuConfig extends BaseEntity<Integer>{

	private static final long serialVersionUID = 4958934760696053684L;
	
	/**
	 * 填报类型
	 */
	private String enterpriseType;
	
	/**
	 * 行业
	 */
	private String categoryId;
	
	/**
	 * 菜单
	 */
	private String menus;
	
	/**
	 * 显示菜单名称
	 */
	private String menusName;

	public String getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	
	@Column(columnDefinition="VARCHAR(255) COMMENT '行业'",unique=true)
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '菜单'")
	public String getMenus() {
		return menus;
	}

	public void setMenus(String menus) {
		this.menus = menus;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '显示菜单名称'")
	public String getMenusName() {
		return menusName;
	}

	public void setMenusName(String menusName) {
		this.menusName = menusName;
	}

	
	
	
	
	
	
}

package com.online.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.online.annotation.ColumnDescription;

/**
 * 系统配置
 * @author qianyue
 *
 */
@Cacheable
@Entity
@Table(name="sys_menu_config",uniqueConstraints={@UniqueConstraint(columnNames={"systemMenuFrontId", "systemMenuEndId"})})
@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_sys_menu_config")
public class SystemMenuConfig extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2405382569675588949L;
	
	
	private String enterpriseType;
	
	/**
	 * 前台菜单名称
	 */
	@ColumnDescription(caption="前台菜单名称")
	private Integer systemMenuFrontId;
	
	/**
	 * 后台菜单名称
	 */
	@ColumnDescription(caption="后台菜单名称")
	private Integer systemMenuEndId;

	public String getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	@Column(unique=true)
	public Integer getSystemMenuFrontId() {
		return systemMenuFrontId;
	}

	public void setSystemMenuFrontId(Integer systemMenuFrontId) {
		this.systemMenuFrontId = systemMenuFrontId;
	}

	public Integer getSystemMenuEndId() {
		return systemMenuEndId;
	}

	public void setSystemMenuEndId(Integer systemMenuEndId) {
		this.systemMenuEndId = systemMenuEndId;
	}

	
	
	
	
	
	
}

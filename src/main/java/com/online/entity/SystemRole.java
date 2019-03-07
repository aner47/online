package com.online.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;

@Entity
@Table(name="sys_role")
@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_sys_role")
public class SystemRole extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2515883169591706611L;
	
	/**
	 * 角色名称
	 */
	@ColumnDescription(caption="角色名称")
	private String name;
	
	/**
	 * 菜单
	 */
	private Set<SystemMenu> systemMenus;
	


	@Column(unique=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="sys_role_menu")
	public Set<SystemMenu> getSystemMenus() {
		return systemMenus;
	}

	public void setSystemMenus(Set<SystemMenu> systemMenus) {
		this.systemMenus = systemMenus;
	}
	


	
	
	

}

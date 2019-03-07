package com.online.entity;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online.annotation.ColumnDescription;

@Cacheable
@Entity
@Table(name="sys_department")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_sys_department")
public class SystemDepartment extends BaseEntity<Integer> {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 部门名称
	 */
	@ColumnDescription(caption="部门名称",filter=true)
	private String name;
	
	/**
	 * 部门描述
	 */
	@ColumnDescription(caption="部门描述")
	private String description;
	
	/**
	 * 所属部门
	 */
	@ColumnDescription(caption="所属部门")
	private Integer pid;
	
	
	/**
	 * 排序
	 */
	@ColumnDescription(caption="排序")
	private Integer orders;
	
	
	
	/**
	 * 角色
	 */
	private Set<SystemRole>  roles ; 
	
	
	
	
	
 	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	
	public Integer getOrders() {
		return orders;
	}
	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER)
 	@JoinTable(name="sys_department_role")
	public Set<SystemRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<SystemRole> roles) {
		this.roles = roles;
	}
	
	
	
	

}

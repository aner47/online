package com.online.entity;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online.annotation.ColumnDescription;
import com.online.entity.online.Project;

@Cacheable
@Entity
@Table(name="sys_user")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_sys_user")
public class SystemUser extends BaseEntity<Integer> {
	
	public enum UserType{
		enterprise,
		investigator,
		system
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户名
	 */
	@ColumnDescription(caption="用户名",filter=true)
	private String username;
	/**
	 * 密码
	 */
	@ColumnDescription(caption="密码")
	private String password;
	/**
	 * 邮箱
	 */
	@ColumnDescription(caption="邮箱")
	private String email;
	
	private UserType userType;
	/**
	 * 角色
	 */
	private Set<SystemRole>  roles ; 
	/**
	 * 手机
	 */
	@ColumnDescription(caption="手机",filter=true)
	private String phone;
	
	
    private Project project;
    
    private Set<SystemDepartment>  departments;
    
    private String passwordVisible;
    
    
    
    @JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
 	@JoinTable(name="sys_user_department")
    public Set<SystemDepartment> getDepartments() {
		return departments;
	}
	public void setDepartments(Set<SystemDepartment> departments) {
		this.departments = departments;
	}
	
    
    
    
	//optional=true：可选，表示此对象可以没有，可以为null；false表示必须存在
    //( cascade = { CascadeType.REFRESH, CascadeType.MERGE },optional = true)
     @ManyToOne
     //这里name是本类（表）在数据库的字段名，referencedColumnName 是关联类（表）在数据库中的关联字段名 
    // @JoinColumn(name = "project_invitationCode",referencedColumnName="invitationCode")
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	@Column(unique=true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
 	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
 	@JoinTable(name="sys_user_role")
	public Set<SystemRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<SystemRole> roles) {
		this.roles = roles;
	}
	
	@Enumerated(EnumType.STRING)
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public String getPasswordVisible() {
		return passwordVisible;
	}
	public void setPasswordVisible(String passwordVisible) {
		this.passwordVisible = passwordVisible;
	}
	
	

}

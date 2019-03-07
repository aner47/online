package com.online.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;

/**
 * 系统配置
 * @author qianyue
 *
 */
@Cacheable
@Entity
@Table(name="sys_menu")
@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_sys_menu")
public class SystemMenu extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2405382569675588949L;
	/**
	 * 菜单名称
	 */
	@ColumnDescription(caption="菜单名称")
	private String name;
	/**
	 * 链接地址
	 */
	/**
	 * 权限
	 */
	@ColumnDescription(caption="授权")
	private String perms;
	
	@Column(length=125)
	public String getPerms() {
		return perms;
	}
	public void setPerms(String perms) {
		this.perms = perms;
	}
	@ColumnDescription(caption="链接地址")
	private String url;
	/**
	 * 图标
	 */
	@ColumnDescription(caption="图标")
	private String icon;
	/**
	 * 上级菜单
	 */
	@ColumnDescription(caption="上级菜单")
	private int pid;
	/**
	 * 菜单排序
	 */
	@ColumnDescription(caption="菜单排序")
	private int sort;
	
	@Column(length=128)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length=256)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(length=32)
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
}

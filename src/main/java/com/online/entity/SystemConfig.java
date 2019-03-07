package com.online.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;

/**
 * 系统配置
 * @author qianyue
 *
 */
@Entity
@Table(name="sys_config")
@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_sys_config")
public class SystemConfig extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2405382569675588949L;
	/**
	 * 关键词
	 */
	@ColumnDescription(caption="系统识别码",hidden=true)
	private String  syskey;
	/**
	 * 值
	 */
	@ColumnDescription(caption="值")
	private String sysValue;
	/**
	 * 描述
	 */
	@ColumnDescription(caption="描述")
	private String description;

	public String getSyskey() {
		return syskey;
	}

	public void setSyskey(String syskey) {
		this.syskey = syskey;
	}

	public String getSysValue() {
		return sysValue;
	}

	public void setSysValue(String sysValue) {
		this.sysValue = sysValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

	

}

package com.online.entity.online.fieldset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.entity.BaseEntity;
/**
 * 字段模块表
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2018年1月16日 上午10:46:04 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return
 */
@Entity
@Table(name="sys_field_module")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_sys_field_module")
public class FieldModule extends BaseEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5895698869795389906L;
	
	/**
	 * 模块名称
	 */
	@ColumnDescription(caption="模块名称",filter=true)
	private String name;
	
	/**
	 * 所属填表类型
	 */
	@ColumnDescription(caption="所属填表类型",filter=true)
	private String tableType;
	
	
	/**
	 * 排序
	 */
	@ColumnDescription(caption="排序",filter=true)
	private Integer orders;
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '模块名称'")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '所属填表类型'")
	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	
	@Column(columnDefinition="INTEGER COMMENT '排序'")
	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	
	
	
}

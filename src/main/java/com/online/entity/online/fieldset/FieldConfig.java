package com.online.entity.online.fieldset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.online.annotation.ColumnDescription;
import com.online.entity.BaseEntity;
/**
 * 字段配置
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2018年1月16日 上午10:46:04 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return
 */
@Entity
@Table(name="sys_field_config",uniqueConstraints={@UniqueConstraint(columnNames={"value", "fieldModule"})})
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_sys_field_config")
public class FieldConfig extends BaseEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5895698869795389906L;
	
	/**
	 * 字段名称
	 */
	@ColumnDescription(caption="字段名称",filter=true)
	private String name;
	
	/**
	 * 字段名称对应key
	 */
	@ColumnDescription(caption="字段名称对应key",filter=true)
	private String value;
	
	/**
	 * 所属父类
	 */
	@ColumnDescription(caption="所属父类",filter=true)
	private Integer parent;
	
	
	/**
	 * 所属模块
	 */
	@ColumnDescription(caption="所属模块",filter=true)
	private FieldModule fieldModule;

	@Column(columnDefinition="VARCHAR(255) COMMENT '字段名称'")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '字段名称对应key'")
	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}

	@Column(columnDefinition="INTEGER COMMENT '所属父类'")
	public Integer getParent() {
		return parent;
	}


	public void setParent(Integer parent) {
		this.parent = parent;
	}

	@ManyToOne
	@JoinColumn(name="fieldModule")
	public FieldModule getFieldModule() {
		return fieldModule;
	}


	public void setFieldModule(FieldModule fieldModule) {
		this.fieldModule = fieldModule;
	}
	
	
	 
	
}

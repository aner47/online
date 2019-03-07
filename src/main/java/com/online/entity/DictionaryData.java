package com.online.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="sys_dictionary_data")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_sys_dictionary_data")
public class DictionaryData extends BaseEntity<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 代码
	 */
	private String code;
	/**
	 * 代码对照值
	 */
	private String codeValue;
	/**
	 * 是否可用
	 */
	private boolean enabled;
	
	/**
	 * 排序
	 */
	private int order;
	/**
	 * 代码集合
	 */
	private Dictionary dictionary;
	
	
	private String groupbyName;
	
	private String secondGroupbyName;
	
	/**
     * 其他值（json格式）
     */
    private String otherValue;
    
    /**
	 * 其他值（json格式）
	 * @return String
	 */
	@Column(columnDefinition="VARCHAR(255) COMMENT '字典其他值'")
	public String getOtherValue() {
		return otherValue;
	}
	
	/**
	 * 设置 其他值（json格式） 
	 * @param String
	 */
	public void setOtherValue(String otherValue) {
		this.otherValue = otherValue;
	}
	
	
	public String getSecondGroupbyName() {
		return secondGroupbyName;
	}
	public void setSecondGroupbyName(String secondGroupbyName) {
		this.secondGroupbyName = secondGroupbyName;
	}
	public String getGroupbyName() {
		return groupbyName;
	}
	public void setGroupbyName(String groupbyName) {
		this.groupbyName = groupbyName;
	}
	/**
	 * 代码集合
	 */
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)   
	public Dictionary getDictionary() {
		return dictionary;
	}
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	/**
	 * 代码
	 */
	@Column(length=32)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 代码对照值
	 */
	@Column(length=128)
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Column(name="order_field")
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}

	
	
}

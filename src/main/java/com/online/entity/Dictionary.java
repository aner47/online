package com.online.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="sys_dictionary")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_sys_dictionary")
public class Dictionary extends BaseEntity<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 字典名称
	 */
	@Column(length=128)
	private String name ; 
	/**
	 * 是否可用
	 */
	private boolean enabled;
	
	/**
	 * beanName;
	 */
	private String beanName;
	/**
	 * 字典数据
	 */
 
	private Set<DictionaryData> dictionaryDatas;
	
	/**
	 * 字典数据
	 */
	@JsonIgnore
	@XmlTransient
	@OneToMany(mappedBy="dictionary") 
	public Set<DictionaryData> getDictionaryDatas() {
		return dictionaryDatas;
	}
	public void setDictionaryDatas(Set<DictionaryData> dictionaryDatas) {
		this.dictionaryDatas = dictionaryDatas;
	}
	/**
	 * 字典名称
	 */
	@Column(length=128)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	
	

}

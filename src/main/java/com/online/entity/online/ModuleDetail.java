package com.online.entity.online;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;


/**
 * 模块导出模块明细
 * @author DEV2
 *
 */
@EntityDescription(caption="模块导出模块明细")
@Entity
@Table(name="ol_module_detail")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_module_detail")
public class ModuleDetail extends BaseEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2364443077005402149L;

	/**
	 * 所属模块
	 */
	
	private ModuleInfo moduleInfo;
	
	/**
	 * 数据对象字段名称
	 */
	@ColumnDescription(caption="数据关键字")
	private String key;
	
	/**
	 * excel显示头标题
	 */
	@ColumnDescription(caption="对应表头")
	private String head;
	
	/**
	 * 模块内部排序
	 */
	@ColumnDescription(caption="排序字段")
	private short moduleOrder;
	
	

	@ManyToOne
	public ModuleInfo getModuleInfo() {
		return moduleInfo;
	}

	public void setModuleInfo(ModuleInfo moduleInfo) {
		this.moduleInfo = moduleInfo;
	}
	@Column(name="module_key")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public short getModuleOrder() {
		return moduleOrder;
	}

	public void setModuleOrder(short moduleOrder) {
		this.moduleOrder = moduleOrder;
	}

	
	
	
	

}

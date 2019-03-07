package com.online.entity.online;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;


/**
 * 模块信息表
 * @author DEV2
 *
 */
@EntityDescription(caption="项目导出模块信息")
@Entity
@Table(name="ol_module_info")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_module_info")
public class ModuleInfo extends BaseEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6715470193213003218L;

	/**
	 * 模块类型
	 */
	@ColumnDescription(caption="模块类型")
	private String moduleType;
	
	/**
	 * 模块名称
	 */
	@ColumnDescription(caption="模块名称")
	private String name ;
	
	/**
     * 模块分类
     */
    @ColumnDescription(caption="模块分类")
    private String moduleClassification ;
	/**
	 * 模块排序
	 */
	@ColumnDescription(caption="模块排序")
	private short order;
	
	/**
	 * 模块类型：enterprise:企业,base:基本信息,table:表格信息
	 */
	@ColumnDescription(caption="模块类型")
	private String type;
	
	public String getModuleClassification() {
        return moduleClassification;
    }

    public void setModuleClassification(String moduleClassification) {
        this.moduleClassification = moduleClassification;
    }

    /**
	 * 状态
	 */
	@ColumnDescription(caption="状态")
    private String status;

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	@Column(name="module_order")
	public short getOrder() {
		return order;
	}

	public void setOrder(short order) {
		this.order = order;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}

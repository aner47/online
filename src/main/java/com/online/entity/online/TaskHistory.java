package com.online.entity.online;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;

/**任务历史记录
 * @time 2017年9月20日
 */
@Entity
@Table(name="ol_task_history")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_task_history")
public class TaskHistory extends  TaskListBase{

    
    
    /**
     * 
     */
    private static final long serialVersionUID = 8756003338339729306L;

    /**
	 * 历史id
	 */
	@ColumnDescription(caption="历史id")
	private String oldId;
	
	/** "创建日期"属性名称 */
	private Date oldCreateDate;

	/** "修改日期"属性名称 */
	private  Date oldModifyDate;

	/** "版本"属性名称 */
	private Long oldVersion;
	
	/**
     * 操作
     */
	@ColumnDescription(caption="操作")
    private String operating;
	/**
	 * 操作人
	 */
	@ColumnDescription(caption="操作人")
	private String operatingPeople;
	
	/**
	 * 当前处理人
	 */
	@ColumnDescription(caption="当前处理人")
	private String currentPeople;

	
	
	
	public String getCurrentPeople() {
		return currentPeople;
	}

	public void setCurrentPeople(String currentPeople) {
		this.currentPeople = currentPeople;
	}

	public String getOperatingPeople() {
		return operatingPeople;
	}

	public void setOperatingPeople(String operatingPeople) {
		this.operatingPeople = operatingPeople;
	}

	public String getOldId() {
		return oldId;
	}

	public void setOldId(String oldId) {
		this.oldId = oldId;
	}

	public Date getOldCreateDate() {
		return oldCreateDate;
	}

	public void setOldCreateDate(Date oldCreateDate) {
		this.oldCreateDate = oldCreateDate;
	}

	public Date getOldModifyDate() {
		return oldModifyDate;
	}

	public void setOldModifyDate(Date oldModifyDate) {
		this.oldModifyDate = oldModifyDate;
	}

	public Long getOldVersion() {
		return oldVersion;
	}

	public void setOldVersion(Long oldVersion) {
		this.oldVersion = oldVersion;
	}

	public String getOperating() {
		return operating;
	}

	public void setOperating(String operating) {
		this.operating = operating;
	}
	
	
	
    
}

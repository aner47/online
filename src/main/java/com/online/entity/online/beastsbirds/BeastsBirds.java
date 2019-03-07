package com.online.entity.online.beastsbirds;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.entity.online.Enterprise;
import com.online.entity.online.Project;
import com.online.util.SpringUtils;

/**
 * 畜禽养殖场
 * 
 * @author DEV2
 *
 */
@EntityDescription(caption="畜禽养殖场")
@Entity
@Table(name="ol_beasts_birds")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_beasts_birds")
public class BeastsBirds extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9222660567026914264L;

	/**
	 * 项目
	 */
	private Project project;
	
	private Enterprise enterprise;
	
	/**
	 * 备注
	 */
	private String description;
	
	
	/**
	 * 畜禽种类
	 */
	@ColumnDescription(caption="畜禽种类")
	private String kind;
	
	/**
	 * 饲养量（只）
	 */
	@ColumnDescription(caption="饲养量（只）")
	private Double feedingNum;
	
	/**
	 * 存栏数（只）
	 */
	@ColumnDescription(caption="存栏数（只）")
	private Double saveNum;
	
	/**
	 * 出栏数（只）
	 */
	@ColumnDescription(caption="出栏数（只）")
	private Double outNum;
	
	/**
	 * 饲养周期（天）
	 */
	@ColumnDescription(caption="饲养周期（天）")
	private Double feedingPeriod;
	
	/**
	 * 饲养室内比例（%）
	 */
	@ColumnDescription(caption="饲养室内比例（%）")
	private Double feedingIndoorProportion;
	
	/**
	 * 尿液粪便处理方式
	 */
	@ColumnDescription(caption="尿液粪便处理方式")
	private String process;
	
	/**
	 * 粪肥作饲料的比重（%）
	 */
	@ColumnDescription(caption="粪肥作饲料的比重（%）")
	private Double fodderProportion;
	
	
	
	
	
	
	
	
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '畜禽种类'")
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	@Column(columnDefinition="DOUBLE COMMENT '饲养量（只）'")
	public Double getFeedingNum() {
		return feedingNum;
	}

	public void setFeedingNum(Double feedingNum) {
		this.feedingNum = feedingNum;
	}
	@Column(columnDefinition="DOUBLE COMMENT '存栏数（只）'")
	public Double getSaveNum() {
		return saveNum;
	}

	public void setSaveNum(Double saveNum) {
		this.saveNum = saveNum;
	}
	@Column(columnDefinition="DOUBLE COMMENT '出栏数（只）'")
	public Double getOutNum() {
		return outNum;
	}

	public void setOutNum(Double outNum) {
		this.outNum = outNum;
	}
	@Column(columnDefinition="DOUBLE COMMENT '饲养周期（天）'")
	public Double getFeedingPeriod() {
		return feedingPeriod;
	}

	public void setFeedingPeriod(Double feedingPeriod) {
		this.feedingPeriod = feedingPeriod;
	}
	@Column(columnDefinition="DOUBLE COMMENT '饲养室内比例（%）'")
	public Double getFeedingIndoorProportion() {
		return feedingIndoorProportion;
	}

	public void setFeedingIndoorProportion(Double feedingIndoorProportion) {
		this.feedingIndoorProportion = feedingIndoorProportion;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '尿液粪便处理方式'")
	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}
	@Column(columnDefinition="DOUBLE COMMENT '粪肥作饲料的比重（%）'")
	public Double getFodderProportion() {
		return fodderProportion;
	}

	public void setFodderProportion(Double fodderProportion) {
		this.fodderProportion = fodderProportion;
	}

	@ManyToOne
	@JoinColumn(updatable=false)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne
	@JoinColumn(updatable=false)
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '备注'")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@PrePersist
	public void initProjectEnterpise(){
		project = SpringUtils.getCurrentProject();
		enterprise = SpringUtils.getCurrentEnterprise();
	}

	
}

package com.online.entity.online.constructionsite;

import java.util.Date;

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
 * 施工周期
 * 
 * @author DEV2
 *
 */
@EntityDescription(caption="施工周期")
@Entity
@Table(name="ol_construction_period")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_construction_period")
public class ConstructionPeriod extends BaseEntity<Integer> {

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
	 * 施工阶段
	 */
	@ColumnDescription(caption="施工阶段")
	private String constructionPhases;
	
	/**
	 * 开工日期
	 */
	@ColumnDescription(caption="开工日期")
	private Date workDate;
	
	/**
	 * 竣工日期
	 */
	@ColumnDescription(caption="竣工日期")
	private Date completedDate;
	
	
	/**
	 * 总施工周期（天）
	 */
	@ColumnDescription(caption="总施工周期（天）")
	private Double sumDays;
	
	/**
	 * 挖掘机
	 */
	@ColumnDescription(caption="挖掘机")
	private String mechanical1;
	/**
	 * 装载机
	 */
	@ColumnDescription(caption="装载机")
	private String mechanical2;
	/**
	 * 推土机
	 */
	@ColumnDescription(caption="推土机")
	private String mechanical3;
	/**
	 * 铲运机
	 */
	@ColumnDescription(caption="铲运机")
	private String mechanical4;
	/**
	 * 平地机
	 */
	@ColumnDescription(caption="平地机")
	private String mechanical5;
	/**
	 * 起重机械
	 */
	@ColumnDescription(caption="起重机械")
	private String mechanical6;
	/**
	 * 桩工机械
	 */
	@ColumnDescription(caption="桩工机械")
	private String mechanical7;
	/**
	 * 工程车辆
	 */
	@ColumnDescription(caption="工程车辆")
	private String mechanical8;
	/**
	 * 叉车
	 */
	@ColumnDescription(caption="叉车")
	private String mechanical9;
	/**
	 * 其他
	 */
	@ColumnDescription(caption="其他")
	private String mechanical10;
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '施工阶段'")
	public String getConstructionPhases() {
		return constructionPhases;
	}

	public void setConstructionPhases(String constructionPhases) {
		this.constructionPhases = constructionPhases;
	}
	@Column(columnDefinition="DATETIME COMMENT '开工日期'")
	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	@Column(columnDefinition="DATETIME COMMENT '竣工日期'")
	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
	@Column(columnDefinition="DOUBLE COMMENT '总施工周期（天）'")
	public Double getSumDays() {
		return sumDays;
	}

	public void setSumDays(Double sumDays) {
		this.sumDays = sumDays;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '挖掘机'")
	public String getMechanical1() {
		return mechanical1;
	}

	public void setMechanical1(String mechanical1) {
		this.mechanical1 = mechanical1;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '装载机'")
	public String getMechanical2() {
		return mechanical2;
	}

	public void setMechanical2(String mechanical2) {
		this.mechanical2 = mechanical2;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '推土机'")
	public String getMechanical3() {
		return mechanical3;
	}

	public void setMechanical3(String mechanical3) {
		this.mechanical3 = mechanical3;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '铲运机'")
	public String getMechanical4() {
		return mechanical4;
	}

	public void setMechanical4(String mechanical4) {
		this.mechanical4 = mechanical4;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '平地机'")
	public String getMechanical5() {
		return mechanical5;
	}

	public void setMechanical5(String mechanical5) {
		this.mechanical5 = mechanical5;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '起重机械'")
	public String getMechanical6() {
		return mechanical6;
	}

	public void setMechanical6(String mechanical6) {
		this.mechanical6 = mechanical6;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '桩工机械'")
	public String getMechanical7() {
		return mechanical7;
	}

	public void setMechanical7(String mechanical7) {
		this.mechanical7 = mechanical7;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '工程车辆'")
	public String getMechanical8() {
		return mechanical8;
	}

	public void setMechanical8(String mechanical8) {
		this.mechanical8 = mechanical8;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '叉车'")
	public String getMechanical9() {
		return mechanical9;
	}

	public void setMechanical9(String mechanical9) {
		this.mechanical9 = mechanical9;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '其他'")
	public String getMechanical10() {
		return mechanical10;
	}

	public void setMechanical10(String mechanical10) {
		this.mechanical10 = mechanical10;
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

package com.online.entity.online.motorvehicles;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.entity.online.Project;
import com.online.util.SpringUtils;

/**
 * 机动车调查表
 *
 */
@EntityDescription(caption="机动车调查表")
@Entity
@Table(name="ol_motor_vehicles")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_motor_vehicles")
public class MotorVehicles extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7824518575977537444L;
	
	
	/**
	 * 调查地点
	 */
	@ColumnDescription(caption="调查地点")
	private String surveyPlace;
	
	/**
	 * 调查人
	 */
	@ColumnDescription(caption="调查人")
	private String surveyPeople;
	
	/**
	 * 调查日期
	 */
	@ColumnDescription(caption="调查日期")
	private Date surveyDate;
	
	
	/**
	 * 调查车辆
	 */
	Set<SurveyCar> surveyCars;
	
	
	/**
	 * 项目
	 */
	private Project project;
	
	
	
	
	
	
	
	
	


	@Column(columnDefinition="VARCHAR(255) COMMENT '调查地点'")
	public String getSurveyPlace() {
		return surveyPlace;
	}

	public void setSurveyPlace(String surveyPlace) {
		this.surveyPlace = surveyPlace;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '调查人'")
	public String getSurveyPeople() {
		return surveyPeople;
	}

	public void setSurveyPeople(String surveyPeople) {
		this.surveyPeople = surveyPeople;
	}
	@Column(columnDefinition="DATETIME COMMENT '调查日期'")
	public Date getSurveyDate() {
		return surveyDate;
	}

	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}

	@JsonIgnore
	@OneToMany(mappedBy="motorVehicles")
	public Set<SurveyCar> getSurveyCars() {
		return surveyCars;
	}

	public void setSurveyCars(Set<SurveyCar> surveyCars) {
		this.surveyCars = surveyCars;
	}

	@ManyToOne
	@JoinColumn(updatable=false)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	
	@PrePersist
	public void init(){
		project = SpringUtils.getCurrentProject();
	}
	
	
}

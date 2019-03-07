package com.online.entity.online.a14caa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.entity.online.Enterprise;
import com.online.entity.online.Project;

/**
 * 市质监局-飞机机型
 */
@EntityDescription(caption="飞机机型")
@Entity
@Table(name="ol_airplane_type")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_airplane_type")
public class AirplaneType extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9222660567026914264L;

	
	/**
	 * 项目
	 */
	private Project project;
	
	private Enterprise enterprise;
	
	private CivilAviation civilAviation;
	
	/**
	 * 机型
	 */
	private String airplaneType;
	
	
	/**
	 * 总起降次数
	 */
	@ColumnDescription(caption="总起降次数")
	private Double totalOffLand;
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '机型'")
	public String getAirplaneType() {
		return airplaneType;
	}
	public void setAirplaneType(String airplaneType) {
		this.airplaneType = airplaneType;
	}
	@Column(columnDefinition="DOUBLE COMMENT '总起降次数'")
	public Double getTotalOffLand() {
		return totalOffLand;
	}
	public void setTotalOffLand(Double totalOffLand) {
		this.totalOffLand = totalOffLand;
	}
	@ManyToOne
//	@JoinColumn(updatable=false)
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	@ManyToOne
	public CivilAviation getCivilAviation() {
		return civilAviation;
	}
	public void setCivilAviation(CivilAviation civilAviation) {
		this.civilAviation = civilAviation;
	}
	@ManyToOne
//	@JoinColumn(updatable=false)
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	

}

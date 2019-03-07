package com.online.entity.online.a9agriculture;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;
import com.online.entity.online.Project;

/**
 * 市农业委-化肥与农药施用信息表
 */
@EntityDescription(caption="化肥与农药施用信息表")
@Entity
@Table(name="ol_fertilizer_pesticide")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_fertilizer_pesticide")
public class FertilizerPesticide extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9222660567026914264L;

	
	/**
	 * 项目
	 */
	private Project project;
	
	/**
	 * 备注
	 */
	private String description;
	
	/**
	 * 化肥类型/农药类型
	 */
	@ColumnDescription(caption="化肥类型/农药类型")
	private String type;
	
	/**
	 * 实际类型
	 */
	@ColumnDescription(caption="实际类型")
	private String actualType;
	
	/**
	 * 使用量（吨）
	 */
	@ColumnDescription(caption="使用量（吨）")
	private Double useQuantity;
	
	/**
	 * 氮折纯量（吨）
	 */
	@ColumnDescription(caption="氮折纯量（吨）")
	private Double nitrogenPurification;
	
	/**
	 * 施用月份
	 */
	@ColumnDescription(caption="施用月份")
	private String useMonth;
	
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '化肥类型/农药类型'")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '实际类型'")
	public String getActualType() {
		return actualType;
	}
	public void setActualType(String actualType) {
		this.actualType = actualType;
	}
	@Column(columnDefinition="DOUBLE COMMENT '使用量（吨）'")
	public Double getUseQuantity() {
		return useQuantity;
	}
	public void setUseQuantity(Double useQuantity) {
		this.useQuantity = useQuantity;
	}
	@Column(columnDefinition="DOUBLE COMMENT '氮折纯量（吨）'")
	public Double getNitrogenPurification() {
		return nitrogenPurification;
	}
	public void setNitrogenPurification(Double nitrogenPurification) {
		this.nitrogenPurification = nitrogenPurification;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '施用月份'")
	public String getUseMonth() {
		return useMonth;
	}
	public void setUseMonth(String useMonth) {
		this.useMonth = useMonth;
	}
	@ManyToOne
//	@JoinColumn(updatable=false)
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '备注'")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}

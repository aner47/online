package com.online.entity.online.a9agriculture;

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
 * 市农业委-畜禽养殖信息表
 */
@EntityDescription(caption="畜禽养殖信息表")
@Entity
@Table(name="ol_livestock_breeding")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_livestock_breeding")
public class LivestockBreeding extends BaseEntity<Integer> {

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
	 * 区县
	 */
	@ColumnDescription(caption="01_区县")
	private String city;
	
	/**
	 * 乡镇
	 */
	@ColumnDescription(caption="02_乡镇")
	private String town;
	
	/**
	 * 畜禽类型
	 */
	@ColumnDescription(caption="03_畜禽类型")
	private String livestockType;
	
	/**
	 * 养殖方式
	 */
	@ColumnDescription(caption="04_养殖方式")
	private String breedingMethod;
	
	/**
	 * 平均饲养周期（天）
	 */
	@ColumnDescription(caption="05_平均饲养周期（天）")
	private Integer avgBreedingPeriod;
	/**
	 * 总饲养量（万只）
	 */
	@ColumnDescription(caption="06_总饲养量（万只）")
	private Double breedNum;
	/**
	 * 总出栏数（万只）
	 */
	@ColumnDescription(caption="07_总出栏数（万只）")
	private Double outputTotal;
	/**
	 * 年末存栏量（万只）
	 */
	@ColumnDescription(caption="08_年末存栏量（万只）")
	private Double reserveNum;

	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '行政区'")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '乡镇'")
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	
	
	
	
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '畜禽类型'")
	public String getLivestockType() {
		return livestockType;
	}
	public void setLivestockType(String livestockType) {
		this.livestockType = livestockType;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '养殖方式'")
	public String getBreedingMethod() {
		return breedingMethod;
	}
	public void setBreedingMethod(String breedingMethod) {
		this.breedingMethod = breedingMethod;
	}
	@Column(columnDefinition="Int COMMENT '平均饲养周期（天）'")
	public Integer getAvgBreedingPeriod() {
		return avgBreedingPeriod;
	}
	public void setAvgBreedingPeriod(Integer avgBreedingPeriod) {
		this.avgBreedingPeriod = avgBreedingPeriod;
	}
	@Column(columnDefinition="DOUBLE COMMENT '总饲养量（万只）'")
	public Double getBreedNum() {
		return breedNum;
	}
	public void setBreedNum(Double breedNum) {
		this.breedNum = breedNum;
	}
	@Column(columnDefinition="DOUBLE COMMENT '总出栏数（万只）'")
	public Double getOutputTotal() {
		return outputTotal;
	}
	public void setOutputTotal(Double outputTotal) {
		this.outputTotal = outputTotal;
	}
	@Column(columnDefinition="DOUBLE COMMENT '年末存栏量（万只）'")
	public Double getReserveNum() {
		return reserveNum;
	}
	public void setReserveNum(Double reserveNum) {
		this.reserveNum = reserveNum;
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
//	@JoinColumn(updatable=false)
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

}

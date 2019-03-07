package com.online.entity.online.a1constructioncommittee;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
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
 * 住建委-房屋建筑工地信息
 */
@EntityDescription(caption="房屋建筑工地信息")
@Entity
@Table(name="ol_house_build_site")
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING,name="dtype")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_house_build_site")
public class BaseHouseBuildSite extends BaseEntity<Integer> {

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
	 * 占地面积（平方米）
	 */
	@ColumnDescription(caption="05_占地面积（平方米）")
	private Double floorSpace;
	
	/**
	 * 建筑面积（平方米）
	 */
	@ColumnDescription(caption="06_建筑面积（平方米）")
	private Double buildSpace;
	
	/**
	 * 设计开挖土方量（立方米）
	 */
	@ColumnDescription(caption="07_设计开挖土方量（立方米）")
	private Double designExcavations;
	
	/**
	 * 已开挖土方量（立方米）
	 */
	@ColumnDescription(caption="08_已开挖土方量（立方米）")
	private Double excavatedVolume;
	/**
	 * 开工时间（年/月/日）
	 */
	@ColumnDescription(caption="09_开工时间（年/月/日）")
	private Date startDate;
	/**
	 * 竣工时间（年/月/日）
	 */
	@ColumnDescription(caption="10_竣工时间（年/月/日）")
	private Date endDate;
	/**
	 * 扬尘控制措施
	 */
	@ColumnDescription(caption="11_扬尘控制措施")
	private String dustMeasures;
	/**
	 * 建筑涂料使用量（吨）
	 */
	@ColumnDescription(caption="12_建筑涂料使用量（吨）")
	private Double paintUseVol;
	
	
	/**
	 * 行政区
	 */
	@ColumnDescription(caption="02_行政区")
	private String city;
	
	/**
	 * 详细地址
	 */
	@ColumnDescription(caption="01_详细地址")
	private String houseNumber;
	
	/**
	 * 中心经度
	 */
	@ColumnDescription(caption="03_中心经度")
	private Double longitude;
	
	/**
	 * 中心纬度
	 */
	@ColumnDescription(caption="04_中心纬度")
	private Double latitude;
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '行政区'")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '详细地址'")
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	@Column(columnDefinition="DOUBLE COMMENT '中心经度'")
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	@Column(columnDefinition="DOUBLE COMMENT '中心纬度'")
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	
	@Column(columnDefinition="DOUBLE COMMENT '占地面积（平方米）'")
	public Double getFloorSpace() {
		return floorSpace;
	}

	public void setFloorSpace(Double floorSpace) {
		this.floorSpace = floorSpace;
	}

	@Column(columnDefinition="DOUBLE COMMENT '建筑面积（平方米）'")
	public Double getBuildSpace() {
		return buildSpace;
	}

	public void setBuildSpace(Double buildSpace) {
		this.buildSpace = buildSpace;
	}

	@Column(columnDefinition="DOUBLE COMMENT '设计开挖土方量（立方米）'")
	public Double getDesignExcavations() {
		return designExcavations;
	}

	public void setDesignExcavations(Double designExcavations) {
		this.designExcavations = designExcavations;
	}

	@Column(columnDefinition="DOUBLE COMMENT '已开挖土方量（立方米）'")
	public Double getExcavatedVolume() {
		return excavatedVolume;
	}

	public void setExcavatedVolume(Double excavatedVolume) {
		this.excavatedVolume = excavatedVolume;
	}

	@Column(columnDefinition="DATE COMMENT '开工时间（年/月/日）'")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(columnDefinition="DATE COMMENT '竣工时间（年/月/日）'")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '扬尘控制措施'")
	public String getDustMeasures() {
		return dustMeasures;
	}

	public void setDustMeasures(String dustMeasures) {
		this.dustMeasures = dustMeasures;
	}

	@Column(columnDefinition="DOUBLE COMMENT '建筑涂料使用量（吨）'")
	public Double getPaintUseVol() {
		return paintUseVol;
	}

	public void setPaintUseVol(Double paintUseVol) {
		this.paintUseVol = paintUseVol;
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




//	@PrePersist
//	public void initProjectEnterpise(){
//		project = SpringUtils.getCurrentProject();
//		enterprise = SpringUtils.getCurrentEnterprise();
//	}

	
}

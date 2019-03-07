package com.online.entity.online.constructionsite;

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
 * 施工工地调查表
 * 
 * @author DEV2
 *
 */
@EntityDescription(caption="施工工地调查表")
@Entity
@Table(name="ol_construction_site")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_construction_site")
public class ConstructionSiteBase extends BaseEntity<Integer> {

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
	 * 建设单位
	 */
	@ColumnDescription(caption="建设单位")
	private String developmentOrganization;
	
	/**
	 * 施工单位
	 */
	@ColumnDescription(caption="施工单位")
	private String constructionOrganization;
	
	/**
	 * 工地名称
	 */
	@ColumnDescription(caption="工地名称")
	private String siteName;
	
	/**
	 * 工地类型
	 */
	@ColumnDescription(caption="工地类型")
	private String siteType;
	
	/**
	 * 每日开工时间
	 */
	@ColumnDescription(caption="每日开工时间")
	private String everydayWorkTime;
	
	/**
	 * 每日收工时间
	 */
	@ColumnDescription(caption="每日收工时间")
	private String everydayOffTime;
	
	/**
	 * 施工面积（平方米）
	 */
	@ColumnDescription(caption="施工面积（平方米）")
	private Double constructionArea;
	
	/**
	 * 建筑面积（平方米）
	 */
	@ColumnDescription(caption="建筑面积（平方米）")
	private Double buildArea;
	
	/**
	 * 占地面积（平方米）
	 */
	@ColumnDescription(caption="占地面积（平方米）")
	private Double occupyArea;
	
	/**
	 * 容积率
	 */
	@ColumnDescription(caption="容积率")
	private Double cubageRate;
	
	/**
	 * 硬化道路面积
	 */
	@ColumnDescription(caption="硬化道路面积")
	private Double hardenRoadArea;
	
	/**
	 * 裸土面积（平方米）
	 */
	@ColumnDescription(caption="裸土面积（平方米）")
	private Double nakedArea;
	
	/**
	 * 开挖土方量
	 */
	@ColumnDescription(caption="开挖土方量")
	private Double excavationQuantity;
	
	/**
	 * 扬尘控制措施（可多选）
	 */
	@ColumnDescription(caption="扬尘控制措施")
	private String governanceMeasures;
	
	
	/**
	 * 渣土清运量
	 */
	@ColumnDescription(caption="渣土清运量")
	private Double muckQuantity;
	
	/**
	 * 渣土清运量单位
	 */
	@ColumnDescription(caption="渣土清运量单位")
	private String muckQuantityUnit;
	
	
	
	/**
	 * 建筑涂料使用量（吨）
	 */
	@ColumnDescription(caption="建筑涂料使用量（吨）")
	private Double paintUsageAmount;
	
	/**
	 * 建筑涂料类型
	 */
	@ColumnDescription(caption="建筑涂料类型")
	private String paintType;
	
	/**
	 * 调查人
	 */
	@ColumnDescription(caption="调查人")
	private String inquirer;
	
	
	/**
	 * 调查人联系电话
	 */
	@ColumnDescription(caption="调查人联系电话")
	private String inquirerPhone;
	
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '调查人'")
	public String getInquirer() {
		return inquirer;
	}

	public void setInquirer(String inquirer) {
		this.inquirer = inquirer;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '调查人联系电话'")
	public String getInquirerPhone() {
		return inquirerPhone;
	}

	public void setInquirerPhone(String inquirerPhone) {
		this.inquirerPhone = inquirerPhone;
	}
	
	
	
	@Column(columnDefinition="DOUBLE COMMENT '建筑涂料使用量（吨）'")
	public Double getPaintUsageAmount() {
		return paintUsageAmount;
	}


	public void setPaintUsageAmount(Double paintUsageAmount) {
		this.paintUsageAmount = paintUsageAmount;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '建筑涂料类型'")
	public String getPaintType() {
		return paintType;
	}


	public void setPaintType(String paintType) {
		this.paintType = paintType;
	}

	@Column(columnDefinition="DOUBLE COMMENT '渣土清运量'")
	public Double getMuckQuantity() {
		return muckQuantity;
	}


	public void setMuckQuantity(Double muckQuantity) {
		this.muckQuantity = muckQuantity;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '渣土清运量单位'")
	public String getMuckQuantityUnit() {
		return muckQuantityUnit;
	}


	public void setMuckQuantityUnit(String muckQuantityUnit) {
		this.muckQuantityUnit = muckQuantityUnit;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '建设单位'")
	public String getDevelopmentOrganization() {
		return developmentOrganization;
	}


	public void setDevelopmentOrganization(String developmentOrganization) {
		this.developmentOrganization = developmentOrganization;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '施工单位'")
	public String getConstructionOrganization() {
		return constructionOrganization;
	}


	public void setConstructionOrganization(String constructionOrganization) {
		this.constructionOrganization = constructionOrganization;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '工地名称'")
	public String getSiteName() {
		return siteName;
	}


	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '工地类型'")
	public String getSiteType() {
		return siteType;
	}


	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '每日开工时间'")
	public String getEverydayWorkTime() {
		return everydayWorkTime;
	}


	public void setEverydayWorkTime(String everydayWorkTime) {
		this.everydayWorkTime = everydayWorkTime;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '每日收工时间'")
	public String getEverydayOffTime() {
		return everydayOffTime;
	}


	public void setEverydayOffTime(String everydayOffTime) {
		this.everydayOffTime = everydayOffTime;
	}

	@Column(columnDefinition="DOUBLE COMMENT '施工面积（平方米）'")
	public Double getConstructionArea() {
		return constructionArea;
	}


	public void setConstructionArea(Double constructionArea) {
		this.constructionArea = constructionArea;
	}

	@Column(columnDefinition="DOUBLE COMMENT '建筑面积（平方米）'")
	public Double getBuildArea() {
		return buildArea;
	}


	public void setBuildArea(Double buildArea) {
		this.buildArea = buildArea;
	}

	@Column(columnDefinition="DOUBLE COMMENT '占地面积（平方米）'")
	public Double getOccupyArea() {
		return occupyArea;
	}


	public void setOccupyArea(Double occupyArea) {
		this.occupyArea = occupyArea;
	}

	@Column(columnDefinition="DOUBLE COMMENT '容积率'")
	public Double getCubageRate() {
		return cubageRate;
	}


	public void setCubageRate(Double cubageRate) {
		this.cubageRate = cubageRate;
	}

	@Column(columnDefinition="DOUBLE COMMENT '硬化道路面积'")
	public Double getHardenRoadArea() {
		return hardenRoadArea;
	}


	public void setHardenRoadArea(Double hardenRoadArea) {
		this.hardenRoadArea = hardenRoadArea;
	}

	@Column(columnDefinition="DOUBLE COMMENT '裸土面积（平方米）'")
	public Double getNakedArea() {
		return nakedArea;
	}


	public void setNakedArea(Double nakedArea) {
		this.nakedArea = nakedArea;
	}

	@Column(columnDefinition="DOUBLE COMMENT '开挖土方量'")
	public Double getExcavationQuantity() {
		return excavationQuantity;
	}


	public void setExcavationQuantity(Double excavationQuantity) {
		this.excavationQuantity = excavationQuantity;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '扬尘控制措施'")
	public String getGovernanceMeasures() {
		return governanceMeasures;
	}


	public void setGovernanceMeasures(String governanceMeasures) {
		this.governanceMeasures = governanceMeasures;
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

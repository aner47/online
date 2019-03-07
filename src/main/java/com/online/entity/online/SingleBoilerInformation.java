package com.online.entity.online;

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
import com.online.exportpdf.DataBean;
import com.online.util.SpringUtils;

/**
 * 单独锅炉信息表
 * 
 * @author DEV2
 *
 */
@EntityDescription(caption="单独锅炉信息")
@Entity
@Table(name="ol_single_boiler_information")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_single_boiler_information")
public class SingleBoilerInformation extends BaseEntity<Integer> implements DataBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9222660567026914264L;

	
	
	@ColumnDescription(caption="锅炉编号")
	private String no;
	
	/**
	 * 锅炉类型▼
	 */
	@ColumnDescription(caption="锅炉类型",filter=true)
	private String boilerType;
	
	/**
	 * 锅炉型号
	 */
	
	@ColumnDescription(caption="锅炉型号",filter=true)
	private String boilerModel;
	
	/**
	 * 锅炉蒸吨(蒸吨/小时)	
	 */
	@ColumnDescription(caption="锅炉蒸吨",filter=true)
	private Double steamTon;
	
	/**
	 * 5.投运时间（年/月）
	 */
	@ColumnDescription(caption="投运时间",filter=true)
	private Date operation;
	
	
	/**
	 * 关停时间
	 */
	@ColumnDescription(caption="关停时间")
	private Date stopData;
	
	/**
	 * 锅炉用途
	 */
	@ColumnDescription(caption="锅炉用途")
	private String purpose;
	
	
	/**
	 * 燃料类型
	 */
	@ColumnDescription(caption="燃料类型")
	private String fuelType;
	
	/**
	 * 污染物治理工艺（脱硫）
	 */
	@ColumnDescription(caption="污染物治理工艺（脱硫）")
	private String governanceMeasures1;
	/**
	 * 污染物治理工艺（脱硝）
	 */
	@ColumnDescription(caption="污染物治理工艺（脱硝）")
	private String governanceMeasures2;
	/**
	 * 污染物治理工艺（除尘）
	 */
	@ColumnDescription(caption="污染物治理工艺（除尘）")
	private String governanceMeasures3;
	
	/**
	 * 排放口高度（米）
	 */
	@ColumnDescription(caption="排放口高度")
	private Double height;
	
	/**
	 * 锅炉调查是否覆盖
	 */
	@ColumnDescription(caption="锅炉调查是否覆盖")
	private String isSurveyCover;
	
	/**
	 * 项目
	 */
	@ColumnDescription(caption="项目")
	private Project project;
	/**
	 * 企业
	 */
	@ColumnDescription(caption="企业")
	private Enterprise enterprise;
	
	/**
	 * 备注
	 */
	@ColumnDescription(caption="备注")
	private String description;
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '锅炉编号'")
	public String getNo() {
		return no;
	}




	public void setNo(String no) {
		this.no = no;
	}



	@Column(columnDefinition="VARCHAR(255) COMMENT '锅炉类型'")
	public String getBoilerType() {
		return boilerType;
	}




	public void setBoilerType(String boilerType) {
		this.boilerType = boilerType;
	}



	@Column(columnDefinition="VARCHAR(255) COMMENT '锅炉型号'")
	public String getBoilerModel() {
		return boilerModel;
	}




	public void setBoilerModel(String boilerModel) {
		this.boilerModel = boilerModel;
	}



	@Column(columnDefinition="DOUBLE COMMENT '锅炉蒸吨'")
	public Double getSteamTon() {
		return steamTon;
	}




	public void setSteamTon(Double steamTon) {
		this.steamTon = steamTon;
	}



	@Column(columnDefinition="DATETIME COMMENT '投运时间'")
	public Date getOperation() {
		return operation;
	}




	public void setOperation(Date operation) {
		this.operation = operation;
	}



	@Column(columnDefinition="DATETIME COMMENT '关停时间'")
	public Date getStopData() {
		return stopData;
	}




	public void setStopData(Date stopData) {
		this.stopData = stopData;
	}



	@Column(columnDefinition="VARCHAR(255) COMMENT '锅炉用途'")
	public String getPurpose() {
		return purpose;
	}




	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}



	@Column(columnDefinition="VARCHAR(255) COMMENT '燃料类型'")
	public String getFuelType() {
		return fuelType;
	}




	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}



	@Column(columnDefinition="VARCHAR(255) COMMENT '污染物治理工艺（脱硫）'")
	public String getGovernanceMeasures1() {
		return governanceMeasures1;
	}




	public void setGovernanceMeasures1(String governanceMeasures1) {
		this.governanceMeasures1 = governanceMeasures1;
	}



	@Column(columnDefinition="VARCHAR(255) COMMENT '污染物治理工艺（脱硝）'")
	public String getGovernanceMeasures2() {
		return governanceMeasures2;
	}




	public void setGovernanceMeasures2(String governanceMeasures2) {
		this.governanceMeasures2 = governanceMeasures2;
	}



	@Column(columnDefinition="VARCHAR(255) COMMENT '污染物治理工艺（除尘）'")
	public String getGovernanceMeasures3() {
		return governanceMeasures3;
	}




	public void setGovernanceMeasures3(String governanceMeasures3) {
		this.governanceMeasures3 = governanceMeasures3;
	}



	@Column(columnDefinition="DOUBLE COMMENT '排放口高度'")
	public Double getHeight() {
		return height;
	}




	public void setHeight(Double height) {
		this.height = height;
	}



	@Column(columnDefinition="VARCHAR(255) COMMENT '锅炉调查是否覆盖'")
	public String getIsSurveyCover() {
		return isSurveyCover;
	}




	public void setIsSurveyCover(String isSurveyCover) {
		this.isSurveyCover = isSurveyCover;
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

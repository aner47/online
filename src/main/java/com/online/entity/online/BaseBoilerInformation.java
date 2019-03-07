package com.online.entity.online;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.entity.BaseEntity;
import com.online.entity.online.embeddable.EnterpriseEmissionsManagement;
import com.online.exportpdf.DataBean;
import com.online.util.SpringUtils;

/**
 * 锅炉信息表
 * 
 * @author DEV2
 *
 */


@Entity
@Table(name="ol_boiler_information")
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING,name="dtype")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_boiler_information")
public  class BaseBoilerInformation extends BaseEntity<Integer> implements DataBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9222660567026914264L;

	public EnterpriseEmissionsManagement emissionsManagement;
	
	/**
	 * 锅炉类型▼
	 */
	@ColumnDescription(caption="锅炉类型",filter=true)
	private String boilerType;
	
	/**
	 * 锅炉名称
	 */
	
	@ColumnDescription(caption="锅炉名称",filter=true)
	private String boilerName;
	
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
	 * 燃料年份
	 */
	@ColumnDescription(caption="燃料年份")
	private String fuelYear;
	/**
	 * 燃料类型
	 */
	@ColumnDescription(caption="燃料类型")
	private String fuelType;
	
	/**
	 * 燃料消耗量
	 */
	@ColumnDescription(caption="燃料消耗量")
	private Double fuelConsumption;
	
	/**
	 * 燃料消耗量单位
	 */
	@ColumnDescription(caption="燃料单位")
	private String fuelUnit;
	
	/**
	 * 燃料含硫率
	 */
	@ColumnDescription(caption="燃料含硫率")
	private Double fuelSulfurContent;
	
	/**
	 * 燃料灰分
	 */
	@ColumnDescription(caption="燃料灰分")
	private Double fuelAsh;
	
	/**
	 * 燃料挥发分
	 */
	@ColumnDescription(caption="燃料挥发分")
	private Double fuelVolatiles;
	
	/**
	 * 关停时间
	 */
	@ColumnDescription(caption="关停时间")
	private Date stopData;
	
	/**
	 * 项目
	 */
	private Project project;
	
	/**
	 * 备注
	 */
	@ColumnDescription(caption="备注")
	private String description;
	
	/**
	 * 工段Id
	 */
	@ColumnDescription(caption="工段Id")
	private Integer sectionId;
	
	
	/**
	 * 生产月份
	 */
	@ColumnDescription(caption="生产月份")
	private String productionMonth;
	
	
	/**
	 * 生产时段-开始
	 */
	@ColumnDescription(caption="生产时段-开始")
	private Date startManufacture;
	
	/**
	 * 生产时段-结束
	 */
	@ColumnDescription(caption="生产时段-结束")
	private Date endManufacture;
	
	
	/**
	 * 锅炉状态
	 */
	@ColumnDescription(caption="锅炉状态")
	private String status;
	
	
	/**
	 * 锅炉用途
	 */
	@ColumnDescription(caption="锅炉用途")
	private String purpose;
	
	/**
	 * 锅炉型号
	 */
	@ColumnDescription(caption="锅炉型号")
	private String typeOfBoiler;
	
	
	/**
	 * 整改时间
	 */
	@ColumnDescription(caption="整改时间")
	private Date abarbeitungTime;
	
	
	/**
	 * 编号
	 */
	@ColumnDescription(caption="编号")
	private Integer no;
	
	
	
	@Column(columnDefinition="int(11) COMMENT '编号'")
	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	@Column(columnDefinition="VARCHAR(255) COMMENT '燃料年份'")
	public String getFuelYear() {
		return fuelYear;
	}

	public void setFuelYear(String fuelYear) {
		this.fuelYear = fuelYear;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '锅炉型号'")
	public String getTypeOfBoiler() {
		return typeOfBoiler;
	}

	public void setTypeOfBoiler(String typeOfBoiler) {
		this.typeOfBoiler = typeOfBoiler;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '锅炉用途'")
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '锅炉状态'")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '生产月份'")
	public String getProductionMonth() {
		return productionMonth;
	}

	public void setProductionMonth(String productionMonth) {
		this.productionMonth = productionMonth;
	}
	@Column(columnDefinition="DATETIME COMMENT '生产时段-开始'")
	public Date getStartManufacture() {
		return startManufacture;
	}

	public void setStartManufacture(Date startManufacture) {
		this.startManufacture = startManufacture;
	}
	@Column(columnDefinition="DATETIME COMMENT '生产时段-结束'")
	public Date getEndManufacture() {
		return endManufacture;
	}

	public void setEndManufacture(Date endManufacture) {
		this.endManufacture = endManufacture;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '备注'")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '锅炉类型'")
	public String getBoilerType() {
		return boilerType;
	}

	public void setBoilerType(String boilerType) {
		this.boilerType = boilerType;
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
	@Column(columnDefinition="VARCHAR(255) COMMENT '锅炉名称'")
	public String getBoilerName() {
		return boilerName;
	}

	public void setBoilerName(String boilerName) {
		this.boilerName = boilerName;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '燃料类型'")
	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	@Column(columnDefinition="DOUBLE COMMENT '燃料消耗量'")
	public Double getFuelConsumption() {
		return fuelConsumption;
	}

	public void setFuelConsumption(Double fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '燃料单位'")
	public String getFuelUnit() {
		return fuelUnit;
	}

	public void setFuelUnit(String fuelUnit) {
		this.fuelUnit = fuelUnit;
	}
	@Column(columnDefinition="DOUBLE COMMENT '燃料含硫率'")
	public Double getFuelSulfurContent() {
		return fuelSulfurContent;
	}

	public void setFuelSulfurContent(Double fuelSulfurContent) {
		this.fuelSulfurContent = fuelSulfurContent;
	}
	@Column(columnDefinition="DOUBLE COMMENT '燃料灰分'")
	public Double getFuelAsh() {
		return fuelAsh;
	}

	public void setFuelAsh(Double fuelAsh) {
		this.fuelAsh = fuelAsh;
	}
	@Column(columnDefinition="DOUBLE COMMENT '燃料挥发分'")
	public Double getFuelVolatiles() {
		return fuelVolatiles;
	}

	public void setFuelVolatiles(Double fuelVolatiles) {
		this.fuelVolatiles = fuelVolatiles;
	}

	@Enumerated
	public EnterpriseEmissionsManagement getEmissionsManagement() {
		return emissionsManagement;
	}

	public void setEmissionsManagement(EnterpriseEmissionsManagement emissionsManagement) {
		this.emissionsManagement = emissionsManagement;
	}
	@Column(columnDefinition="int(11) COMMENT '工段Id'")
	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	@Column(columnDefinition="DATETIME COMMENT '关停时间'")
	public Date getStopData() {
		return stopData;
	}

	public void setStopData(Date stopData) {
		this.stopData = stopData;
	}
	
	@Column(columnDefinition="DATETIME COMMENT '整改时间'")
	public Date getAbarbeitungTime() {
		return abarbeitungTime;
	}


	public void setAbarbeitungTime(Date abarbeitungTime) {
		this.abarbeitungTime = abarbeitungTime;
	}
	

	@PrePersist
	public void initProjectEnterpise(){
		project = SpringUtils.getCurrentProject();
		emissionsManagement.setEnterprise(SpringUtils.getCurrentEnterprise());
	}

	
}

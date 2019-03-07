package com.online.entity.online;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;
import com.online.entity.BaseEntity;

/**
 * 石油储罐信息
 * 
 * @author DEV2
 *
 */
@EntityDescription(caption="石油产品储罐")
@Entity
@Table(name="ol_oil_tank")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_oil_tank")
public class OilTank extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2283018879519647438L;

	
	
	/**
	 * 材料类型▼
	 */
	@ColumnDescription(caption="材料类型")
	private String tankMaterialType;
	
	/**
	 * 储罐类型▼
	 */
	@ColumnDescription(caption="储罐类型")
	private String tankType;
	
	/**
	 * 高度/长度（米）
	 */
	@ColumnDescription(caption="高度（米）")
	private Double height;
	
	/**
	 * 平均直径（米）
	 */
	@ColumnDescription(caption="平均直径（米）")
	private Double avgDiameter;
	
	/**
	 * 储罐容量（立方米）
	 */
	@ColumnDescription(caption="储罐容量（立方米）")
	private Double capacity;
	
	/**
	 * 全年使用天数（天）
	 */
	@ColumnDescription(caption="全年使用天数（天）")
	private Double workDays;
	
	/**
	 * 储罐年总储量（吨）
	 */
	@ColumnDescription(caption="储罐年总储量（吨）")
	private Double storageCapacity;
	
	/**
	 * 储存周期（天）
	 */
	@ColumnDescription(caption="储存周期（天）")
	private Double storageCycle;
	
	/**
	 * 年装卸/填充次数
	 */
	@ColumnDescription(caption="年装卸/填充次数")
	private Integer fillTimes;
	
	/**
	 * 项目
	 */
	private Project project;
	
	/**
	 * 备注
	 */
	private String description;
	
	/**
	 * 数量
	 */
	@ColumnDescription(caption="数量")
	private Integer num;
	
	
	/**
	 * 编号
	 */
	@ColumnDescription(caption="编号")
	private Integer no;
	
	
	@Column(columnDefinition="INT(11) COMMENT '编号'")
	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	@Column(columnDefinition="INT(11) COMMENT '数量'")
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
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

	private Enterprise enterprise;
	@Column(columnDefinition="VARCHAR(255) COMMENT '材料类型'")
	public String getTankMaterialType() {
		return tankMaterialType;
	}

	public void setTankMaterialType(String tankMaterialType) {
		this.tankMaterialType = tankMaterialType;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '储罐类型'")
	public String getTankType() {
		return tankType;
	}

	public void setTankType(String tankType) {
		this.tankType = tankType;
	}
	@Column(columnDefinition="DOUBLE COMMENT '高度（米）'")
	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}
	@Column(columnDefinition="DOUBLE COMMENT '平均直径（米）'")
	public Double getAvgDiameter() {
		return avgDiameter;
	}

	public void setAvgDiameter(Double avgDiameter) {
		this.avgDiameter = avgDiameter;
	}
	@Column(columnDefinition="DOUBLE COMMENT '储罐容量（立方米）'")
	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}
	@Column(columnDefinition="DOUBLE COMMENT '全年使用天数（天）'")
	public Double getWorkDays() {
		return workDays;
	}

	public void setWorkDays(Double workDays) {
		this.workDays = workDays;
	}
	@Column(columnDefinition="DOUBLE COMMENT '储罐年总储量（吨）'")
	public Double getStorageCapacity() {
		return storageCapacity;
	}

	public void setStorageCapacity(Double storageCapacity) {
		this.storageCapacity = storageCapacity;
	}
	@Column(columnDefinition="DOUBLE COMMENT '储存周期（天）'")
	public Double getStorageCycle() {
		return storageCycle;
	}

	public void setStorageCycle(Double storageCycle) {
		this.storageCycle = storageCycle;
	}
	@Column(columnDefinition="INT(11) COMMENT '年装卸/填充次数'")
	public Integer getFillTimes() {
		return fillTimes;
	}

	public void setFillTimes(Integer fillTimes) {
		this.fillTimes = fillTimes;
	}

	@ManyToOne
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
					
					

}

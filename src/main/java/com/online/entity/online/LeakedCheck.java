package com.online.entity.online;

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
import com.online.util.SpringUtils;

/**
 * 设备泄露检测
 * @author DEV2
 *
 */
@EntityDescription(caption="设备泄露检测表")
@Entity
@Table(name="ol_leaked_check")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_leaked_check")
public class LeakedCheck extends BaseEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4155144841194254713L;

	/**
	 * 密封点(个)
	 */
	@ColumnDescription(caption="密封点(个")
	private Integer sealedPoint;
	
	/**
	 * 泄露密封点
	 */
	@ColumnDescription(caption="泄露点(个")
	private Integer leakedSealedPoint;
	
	/**
	 * 修复密封点（个）
	 */
	@ColumnDescription(caption="修复(个)")
	private Integer fixedSealedPoint;
	
	private Integer project;
	
	private Integer enterprise;
	/**
	 * 设备泄露信息表
	 */
	private EquipmentLeaked equipmentLeaked;

	@Column(columnDefinition="INT(11) COMMENT '密封点(个'")
	public Integer getSealedPoint() {
		return sealedPoint;
	}

	public void setSealedPoint(Integer sealedPoint) {
		this.sealedPoint = sealedPoint;
	}
	@Column(columnDefinition="INT(11) COMMENT '泄露点(个'")
	public Integer getLeakedSealedPoint() {
		return leakedSealedPoint;
	}

	public void setLeakedSealedPoint(Integer leakedSealedPoint) {
		this.leakedSealedPoint = leakedSealedPoint;
	}
	@Column(columnDefinition="INT(11) COMMENT '修复(个)'")
	public Integer getFixedSealedPoint() {
		return fixedSealedPoint;
	}

	public void setFixedSealedPoint(Integer fixedSealedPoint) {
		this.fixedSealedPoint = fixedSealedPoint;
	}

	@ManyToOne
	@JoinColumn(name = "equipment_leaked",updatable=false) 
	public EquipmentLeaked getEquipmentLeaked() {
		return equipmentLeaked;
	}

	public void setEquipmentLeaked(EquipmentLeaked equipmentLeaked) {
		this.equipmentLeaked = equipmentLeaked;
	}
	@Column(updatable=false)
	public Integer getProject() {
		return project;
	}

	public void setProject(Integer project) {
		this.project = project;
	}
	@Column(updatable=false)
	public Integer getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Integer enterprise) {
		this.enterprise = enterprise;
	}
	
	@PrePersist
	protected void init() {
		project = SpringUtils.getProjectId();
		enterprise = SpringUtils.getPrincipal().getEnterpriseId();
	}

}

package com.online.entity.online;

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
import com.online.util.SpringUtils;

/**
 * 设备泄露信息表
 * @author DEV2
 *
 */
@EntityDescription(caption="设备泄露")
@Entity
@Table(name="ol_equipment_leaked")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_equipment_leaked")
public class EquipmentLeaked extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7824518575977537444L;
	
	public enum EstimationEethod{
		实测法,
		公式法,
		系数法
	}
	
	
	/***************************基本信息****************************/
	/**
	 * 生产设备数量（套）
	 */
	@ColumnDescription(caption="生产设备数量（套）")
	private Integer production;
	
	/**
	 * 涉及voc设备数量（套）
	 */
	@ColumnDescription(caption="涉及voc设备数量（套）")
	private Integer involvedVoc;
	
	/**
	 * 开展LDAR设备数量（套）
	 */
	@ColumnDescription(caption="开展LDAR设备数量（套）")
	private Integer developLdar;
	
	/**
	 * 开展LDAR（轮）
	 */
	@ColumnDescription(caption="开展LDAR（轮）")
	private Integer developLdarTime;

	/**
	 * 未开展LDAR设备数量（套）
	 */
	@ColumnDescription(caption="未开展LDAR设备数量（套）")
	private Integer noDevelopLdar;
	
	/**
	 * 豁免装置（套）
	 */
	@ColumnDescription(caption="豁免装置（套）")
	private Integer exemption;
	
	/**
	 * 受控密封点（个）
	 */
	@ColumnDescription(caption="受控密封点（个）")
	private Integer controlledSealedPoint;
	
	/**
	 * 不可达点
	 */
	@ColumnDescription(caption="不可达点（个）")
	private Integer unreachablePoint;
	
	/**
	 * 估算方法
	 */
	@ColumnDescription(caption="估算方法")
	private EstimationEethod estimationEethod;
	
	/**
	 * 泄露排放量（吨）
	 */
	@ColumnDescription(caption="泄露排放量（吨）")
	private Double leakedEmissions;
	
	/**
	 * 检测记录
	 */
	Set<LeakedCheck> leakedChecks;
	
	
	/***************************设备泄露voc损耗量消减潜力分析****************************/
	/**
	 * 是否达标
	 */
	@ColumnDescription(caption="是否达标")
	private boolean standard;
	
	/**
	 * 是否达到国内水平
	 */
	@ColumnDescription(caption="是否达到国内水平")
	private boolean domesticLevel;
	
	/**
	 * 消减潜力（吨）
	 */
	@ColumnDescription(caption="消减潜力（吨）")
	private Double reducePotential;
	
	/**
	 * 是否达到国内先进水平
	 */
	@ColumnDescription(caption="国内先进水平等级")
	private boolean domesticAdvancedLevel;
	
	/**
	 * 阀气体（件）
	 */
	@ColumnDescription(caption="阀气体（件）")
	private Integer valveGas;
	
	/**
	 * 阀轻液体（件）
	 */
	@ColumnDescription(caption="阀轻液体（件）")
	private Integer valveLightLiquid;
	
	/**
	 * 阀重液体（件）
	 */
	@ColumnDescription(caption="阀重液体（件）")
	private Integer valveHeavyLiquid;
	
	/**
	 * 泵轻液体（件）
	 */
	@ColumnDescription(caption="泵轻液体（件）")
	private Integer pumpLightLiquid;
	
	/**
	 * 泵重液体（件）
	 */
	@ColumnDescription(caption="泵重液体（件）")
	private Integer pumpHeavyLiquid;
	
	/**
	 * 压缩机（件）
	 */
	@ColumnDescription(caption="压缩机（件）")
	private Integer compressor;
	
	/**
	 * 安全阀气体（件）
	 */
	@ColumnDescription(caption="安全阀气体（件）")
	private Integer safetyValveGas;
	
	/**
	 * 安全阀轻液体（件）
	 */
	@ColumnDescription(caption="安全阀轻液体（件）")
	private Integer safetyValveLightLiquid;
	
	/**
	 * 安全阀重液体（件）
	 */
	@ColumnDescription(caption="安全阀重液体（件）")
	private Integer safetyValveHeavyLiquid;
	
	/**
	 * 法兰气体（件）
	 */
	@ColumnDescription(caption="法兰气体（件）")
	private Integer flangeGas;
	
	/**
	 * 法兰轻液体（件）
	 */
	@ColumnDescription(caption="法兰轻液体（件）")
	private Integer flangeLightLiquid;
	
	/**
	 * 法兰重液体（件）
	 */
	@ColumnDescription(caption="法兰重液体（件）")
	private Integer flangeHeavyLiquid;
	
	/**
	 * 开口管线
	 */
	@ColumnDescription(caption="开口管线（件）")
	private Integer openPiping;
	
	/**
	 * 采样链接
	 */
	@ColumnDescription(caption="采样链接（件）")
	private Integer samplingLinks;
	
	/**
	 * 项目
	 */
	private Project project;
	
	/**
	 * 企业
	 */
	private Enterprise enterprise;

	@Column(columnDefinition="INT(11) COMMENT '生产设备数量（套）'")
	public Integer getProduction() {
		return production;
	}

	public void setProduction(Integer production) {
		this.production = production;
	}
	@Column(columnDefinition="INT(11) COMMENT '涉及voc设备数量（套）'")
	public Integer getInvolvedVoc() {
		return involvedVoc;
	}

	public void setInvolvedVoc(Integer involvedVoc) {
		this.involvedVoc = involvedVoc;
	}
	@Column(columnDefinition="INT(11) COMMENT '开展LDAR设备数量（套）'")
	public Integer getDevelopLdar() {
		return developLdar;
	}

	public void setDevelopLdar(Integer developLdar) {
		this.developLdar = developLdar;
	}
	@Column(columnDefinition="INT(11) COMMENT '开展LDAR（轮）'")
	public Integer getDevelopLdarTime() {
		return developLdarTime;
	}

	public void setDevelopLdarTime(Integer developLdarTime) {
		this.developLdarTime = developLdarTime;
	}
	@Column(columnDefinition="INT(11) COMMENT '未开展LDAR设备数量（套）'")
	public Integer getNoDevelopLdar() {
		return noDevelopLdar;
	}

	public void setNoDevelopLdar(Integer noDevelopLdar) {
		this.noDevelopLdar = noDevelopLdar;
	}
	@Column(columnDefinition="INT(11) COMMENT '豁免装置（套）'")
	public Integer getExemption() {
		return exemption;
	}

	public void setExemption(Integer exemption) {
		this.exemption = exemption;
	}
	@Column(columnDefinition="INT(11) COMMENT '受控密封点（个）'")
	public Integer getControlledSealedPoint() {
		return controlledSealedPoint;
	}

	public void setControlledSealedPoint(Integer controlledSealedPoint) {
		this.controlledSealedPoint = controlledSealedPoint;
	}
	@Column(columnDefinition="INT(11) COMMENT '不可达点（个）'")
	public Integer getUnreachablePoint() {
		return unreachablePoint;
	}

	public void setUnreachablePoint(Integer unreachablePoint) {
		this.unreachablePoint = unreachablePoint;
	}

	public EstimationEethod getEstimationEethod() {
		return estimationEethod;
	}

	public void setEstimationEethod(EstimationEethod estimationEethod) {
		this.estimationEethod = estimationEethod;
	}
	@Column(columnDefinition="DOUBLE COMMENT '泄露排放量（吨）'")
	public Double getLeakedEmissions() {
		return leakedEmissions;
	}

	public void setLeakedEmissions(Double leakedEmissions) {
		this.leakedEmissions = leakedEmissions;
	}

	@JsonIgnore
	@OneToMany(mappedBy="equipmentLeaked")
	public Set<LeakedCheck> getLeakedChecks() {
		return leakedChecks;
	}

	public void setLeakedChecks(Set<LeakedCheck> leakedChecks) {
		this.leakedChecks = leakedChecks;
	}
	@Column(columnDefinition="BIT(1) COMMENT '是否达标'")
	public boolean isStandard() {
		return standard;
	}

	public void setStandard(boolean standard) {
		this.standard = standard;
	}
	@Column(columnDefinition="BIT(1) COMMENT '是否达到国内水平'")
	public boolean isDomesticLevel() {
		return domesticLevel;
	}

	public void setDomesticLevel(boolean domesticLevel) {
		this.domesticLevel = domesticLevel;
	}
	@Column(columnDefinition="DOUBLE COMMENT '消减潜力（吨）'")
	public Double getReducePotential() {
		return reducePotential;
	}

	public void setReducePotential(Double reducePotential) {
		this.reducePotential = reducePotential;
	}
	@Column(columnDefinition="BIT(1) COMMENT '国内先进水平等级'")
	public boolean isDomesticAdvancedLevel() {
		return domesticAdvancedLevel;
	}

	public void setDomesticAdvancedLevel(boolean domesticAdvancedLevel) {
		this.domesticAdvancedLevel = domesticAdvancedLevel;
	}
	@Column(columnDefinition="INT(11) COMMENT '阀气体（件）'")
	public Integer getValveGas() {
		return valveGas;
	}

	public void setValveGas(Integer valveGas) {
		this.valveGas = valveGas;
	}
	@Column(columnDefinition="INT(11) COMMENT '阀轻液体（件）'")
	public Integer getValveLightLiquid() {
		return valveLightLiquid;
	}

	public void setValveLightLiquid(Integer valveLightLiquid) {
		this.valveLightLiquid = valveLightLiquid;
	}
	@Column(columnDefinition="INT(11) COMMENT '阀重液体（件）'")
	public Integer getValveHeavyLiquid() {
		return valveHeavyLiquid;
	}

	public void setValveHeavyLiquid(Integer valveHeavyLiquid) {
		this.valveHeavyLiquid = valveHeavyLiquid;
	}
	@Column(columnDefinition="INT(11) COMMENT '泵轻液体（件）'")
	public Integer getPumpLightLiquid() {
		return pumpLightLiquid;
	}

	public void setPumpLightLiquid(Integer pumpLightLiquid) {
		this.pumpLightLiquid = pumpLightLiquid;
	}
	@Column(columnDefinition="INT(11) COMMENT '泵重液体（件）'")
	public Integer getPumpHeavyLiquid() {
		return pumpHeavyLiquid;
	}

	public void setPumpHeavyLiquid(Integer pumpHeavyLiquid) {
		this.pumpHeavyLiquid = pumpHeavyLiquid;
	}
	@Column(columnDefinition="INT(11) COMMENT '压缩机（件）'")
	public Integer getCompressor() {
		return compressor;
	}

	public void setCompressor(Integer compressor) {
		this.compressor = compressor;
	}
	@Column(columnDefinition="INT(11) COMMENT '安全阀气体（件）'")
	public Integer getSafetyValveGas() {
		return safetyValveGas;
	}

	public void setSafetyValveGas(Integer safetyValveGas) {
		this.safetyValveGas = safetyValveGas;
	}
	@Column(columnDefinition="INT(11) COMMENT '安全阀轻液体（件）'")
	public Integer getSafetyValveLightLiquid() {
		return safetyValveLightLiquid;
	}

	public void setSafetyValveLightLiquid(Integer safetyValveLightLiquid) {
		this.safetyValveLightLiquid = safetyValveLightLiquid;
	}
	@Column(columnDefinition="INT(11) COMMENT '安全阀重液体（件）'")
	public Integer getSafetyValveHeavyLiquid() {
		return safetyValveHeavyLiquid;
	}

	public void setSafetyValveHeavyLiquid(Integer safetyValveHeavyLiquid) {
		this.safetyValveHeavyLiquid = safetyValveHeavyLiquid;
	}
	@Column(columnDefinition="INT(255) COMMENT '法兰气体（件）'")
	public Integer getFlangeGas() {
		return flangeGas;
	}

	public void setFlangeGas(Integer flangeGas) {
		this.flangeGas = flangeGas;
	}
	@Column(columnDefinition="INT(11) COMMENT '法兰轻液体（件）'")
	public Integer getFlangeLightLiquid() {
		return flangeLightLiquid;
	}

	public void setFlangeLightLiquid(Integer flangeLightLiquid) {
		this.flangeLightLiquid = flangeLightLiquid;
	}
	@Column(columnDefinition="INT(11) COMMENT '法兰重液体（件）'")
	public Integer getFlangeHeavyLiquid() {
		return flangeHeavyLiquid;
	}

	public void setFlangeHeavyLiquid(Integer flangeHeavyLiquid) {
		this.flangeHeavyLiquid = flangeHeavyLiquid;
	}
	@Column(columnDefinition="INT(11) COMMENT '开口管线（件）'")
	public Integer getOpenPiping() {
		return openPiping;
	}

	public void setOpenPiping(Integer openPiping) {
		this.openPiping = openPiping;
	}
	@Column(columnDefinition="INT(11) COMMENT '采样链接（件）'")
	public Integer getSamplingLinks() {
		return samplingLinks;
	}

	public void setSamplingLinks(Integer samplingLinks) {
		this.samplingLinks = samplingLinks;
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
	
	@PrePersist
	public void init(){
		enterprise = SpringUtils.getCurrentEnterprise();
		project = SpringUtils.getCurrentProject();
	}
	
	
}

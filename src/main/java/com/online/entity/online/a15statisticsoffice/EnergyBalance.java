package com.online.entity.online.a15statisticsoffice;

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
 * 统计局-能源平衡
 */
@EntityDescription(caption="能源平衡")
@Entity
@Table(name="ol_energy_balance")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_energy_balance")
public class EnergyBalance extends BaseEntity<Integer> {

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
	 * 项目
	 */
	@ColumnDescription(caption="项目")
	private String projectName;
	
	/**
	 * 煤合计(万吨)
	 */
	@ColumnDescription(caption="煤合计(万吨)")
	private Double meiHeji;
	
	/**
	 * 原煤(万吨)
	 */
	@ColumnDescription(caption="原煤(万吨)")
	private Double yuanmei;
	
	/**
	 * 洗精煤(万吨)
	 */
	@ColumnDescription(caption="洗精煤(万吨)")
	private Double xijingmei;
	
	/**
	 * 其他洗煤(万吨)
	 */
	@ColumnDescription(caption="其他洗煤(万吨)")
	private Double qitaXimei;
	/**
	 * 型煤(万吨)
	 */
	@ColumnDescription(caption="型煤(万吨)")
	private Double xingmei;
	/**
	 * 煤矸石(万吨)
	 */
	@ColumnDescription(caption="煤矸石(万吨)")
	private Double meiganshi;
	/**
	 * 焦炭(万吨)
	 */
	@ColumnDescription(caption="焦炭(万吨)")
	private Double jiaotan;
	/**
	 * 焦炉煤气(亿立方米)
	 */
	@ColumnDescription(caption="焦炉煤气(亿立方米)")
	private Double jiaoluMeiqi;
	/**
	 * 高炉煤气(亿立方米)
	 */
	@ColumnDescription(caption="高炉煤气(亿立方米)")
	private Double gaoluMeiqi;
	/**
	 * 转炉煤气(亿立方米)
	 */
	@ColumnDescription(caption="转炉煤气(亿立方米)")
	private Double zhuanluMeiqi;
	/**
	 * 其他煤气(亿立方米)
	 */
	@ColumnDescription(caption="其他煤气(亿立方米)")
	private Double qitaMeiqi;
	/**
	 * 其他焦化产品(万吨)
	 */
	@ColumnDescription(caption="其他焦化产品(万吨)")
	private Double qitaJiaohuaChanpin;
	/**
	 * 油品合计(万吨)
	 */
	@ColumnDescription(caption="油品合计(万吨)")
	private Double youpinHeji;
	/**
	 * 原油(万吨)
	 */
	@ColumnDescription(caption="原油(万吨)")
	private Double yuanyou;
	/**
	 * 柴油(万吨)
	 */
	@ColumnDescription(caption="柴油(万吨)")
	private Double chaiyou;
	/**
	 * 燃料油(万吨)
	 */
	@ColumnDescription(caption="燃料油(万吨)")
	private Double ranliaoyou;
	/**
	 * 石脑油(万吨)
	 */
	@ColumnDescription(caption="石脑油(万吨)")
	private Double shinaoyou;
	/**
	 * 润滑油(万吨)
	 */
	@ColumnDescription(caption="润滑油(万吨)")
	private Double runhuayou;
	/**
	 * 石蜡(万吨)
	 */
	@ColumnDescription(caption="石蜡(万吨)")
	private Double shila;
	/**
	 * 溶剂油(万吨)
	 */
	@ColumnDescription(caption="溶剂油(万吨)")
	private Double rongjiyou;
	/**
	 * 石油沥青(万吨)
	 */
	@ColumnDescription(caption="石油沥青(万吨)")
	private Double shiyouLiqing;
	/**
	 * 石油焦(万吨)
	 */
	@ColumnDescription(caption="石油焦(万吨)")
	private Double shiyoujiao;
	/**
	 * 液化石油气(万吨)
	 */
	@ColumnDescription(caption="液化石油气(万吨)")
	private Double yehuaShiyouqi;
	/**
	 * 炼厂干气(万吨)
	 */
	@ColumnDescription(caption="炼厂干气(万吨)")
	private Double lianchangganqi;
	/**
	 * 其他石油制品(万吨)
	 */
	@ColumnDescription(caption="其他石油制品(万吨)")
	private Double qitaShiyouZhipin;
	/**
	 * 天然气(亿立方米)
	 */
	@ColumnDescription(caption="天然气(亿立方米)")
	private Double tianranqi;
	/**
	 * 液化天然气(万吨)
	 */
	@ColumnDescription(caption="液化天然气(万吨)")
	private Double yehuaTianranqi;
	/**
	 * 热力(万百万千焦)
	 */
	@ColumnDescription(caption="热力(万百万千焦)")
	private Double reli;
	/**
	 * 电力(亿千瓦小时)
	 */
	@ColumnDescription(caption="电力(亿千瓦小时)")
	private Double dianli;
	/**
	 * 其他能源(万吨标煤)
	 */
	@ColumnDescription(caption="其他能源(万吨标煤)")
	private Double qitanengyuan;
	
	
	
	
	
	
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '项目名称'")
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	@Column(columnDefinition="DOUBLE COMMENT '煤合计(万吨)'")
	public Double getMeiHeji() {
		return meiHeji;
	}
	public void setMeiHeji(Double meiHeji) {
		this.meiHeji = meiHeji;
	}
	@Column(columnDefinition="DOUBLE COMMENT '原煤(万吨)'")
	public Double getYuanmei() {
		return yuanmei;
	}
	public void setYuanmei(Double yuanmei) {
		this.yuanmei = yuanmei;
	}
	@Column(columnDefinition="DOUBLE COMMENT '洗精煤(万吨)'")
	public Double getXijingmei() {
		return xijingmei;
	}
	public void setXijingmei(Double xijingmei) {
		this.xijingmei = xijingmei;
	}
	@Column(columnDefinition="DOUBLE COMMENT '其他洗煤(万吨)'")
	public Double getQitaXimei() {
		return qitaXimei;
	}
	public void setQitaXimei(Double qitaXimei) {
		this.qitaXimei = qitaXimei;
	}
	@Column(columnDefinition="DOUBLE COMMENT '型煤(万吨)'")
	public Double getXingmei() {
		return xingmei;
	}
	public void setXingmei(Double xingmei) {
		this.xingmei = xingmei;
	}
	@Column(columnDefinition="DOUBLE COMMENT '煤矸石(万吨)'")
	public Double getMeiganshi() {
		return meiganshi;
	}
	public void setMeiganshi(Double meiganshi) {
		this.meiganshi = meiganshi;
	}
	@Column(columnDefinition="DOUBLE COMMENT '焦炭(万吨)'")
	public Double getJiaotan() {
		return jiaotan;
	}
	public void setJiaotan(Double jiaotan) {
		this.jiaotan = jiaotan;
	}
	@Column(columnDefinition="DOUBLE COMMENT '焦炉煤气(亿立方米)'")
	public Double getJiaoluMeiqi() {
		return jiaoluMeiqi;
	}
	public void setJiaoluMeiqi(Double jiaoluMeiqi) {
		this.jiaoluMeiqi = jiaoluMeiqi;
	}
	@Column(columnDefinition="DOUBLE COMMENT '高炉煤气(亿立方米)'")
	public Double getGaoluMeiqi() {
		return gaoluMeiqi;
	}
	public void setGaoluMeiqi(Double gaoluMeiqi) {
		this.gaoluMeiqi = gaoluMeiqi;
	}
	@Column(columnDefinition="DOUBLE COMMENT '转炉煤气(亿立方米)'")
	public Double getZhuanluMeiqi() {
		return zhuanluMeiqi;
	}
	public void setZhuanluMeiqi(Double zhuanluMeiqi) {
		this.zhuanluMeiqi = zhuanluMeiqi;
	}
	@Column(columnDefinition="DOUBLE COMMENT '其他煤气(亿立方米)'")
	public Double getQitaMeiqi() {
		return qitaMeiqi;
	}
	public void setQitaMeiqi(Double qitaMeiqi) {
		this.qitaMeiqi = qitaMeiqi;
	}
	@Column(columnDefinition="DOUBLE COMMENT '其他焦化产品(万吨)'")
	public Double getQitaJiaohuaChanpin() {
		return qitaJiaohuaChanpin;
	}
	public void setQitaJiaohuaChanpin(Double qitaJiaohuaChanpin) {
		this.qitaJiaohuaChanpin = qitaJiaohuaChanpin;
	}
	@Column(columnDefinition="DOUBLE COMMENT '油品合计(万吨)'")
	public Double getYoupinHeji() {
		return youpinHeji;
	}
	public void setYoupinHeji(Double youpinHeji) {
		this.youpinHeji = youpinHeji;
	}
	@Column(columnDefinition="DOUBLE COMMENT '原油(万吨)'")
	public Double getYuanyou() {
		return yuanyou;
	}
	public void setYuanyou(Double yuanyou) {
		this.yuanyou = yuanyou;
	}
	@Column(columnDefinition="DOUBLE COMMENT '柴油(万吨)'")
	public Double getChaiyou() {
		return chaiyou;
	}
	public void setChaiyou(Double chaiyou) {
		this.chaiyou = chaiyou;
	}
	@Column(columnDefinition="DOUBLE COMMENT '燃料油(万吨)'")
	public Double getRanliaoyou() {
		return ranliaoyou;
	}
	public void setRanliaoyou(Double ranliaoyou) {
		this.ranliaoyou = ranliaoyou;
	}
	@Column(columnDefinition="DOUBLE COMMENT '石脑油(万吨)'")
	public Double getShinaoyou() {
		return shinaoyou;
	}
	public void setShinaoyou(Double shinaoyou) {
		this.shinaoyou = shinaoyou;
	}
	@Column(columnDefinition="DOUBLE COMMENT '润滑油(万吨)'")
	public Double getRunhuayou() {
		return runhuayou;
	}
	public void setRunhuayou(Double runhuayou) {
		this.runhuayou = runhuayou;
	}
	@Column(columnDefinition="DOUBLE COMMENT '石蜡(万吨)'")
	public Double getShila() {
		return shila;
	}
	public void setShila(Double shila) {
		this.shila = shila;
	}
	@Column(columnDefinition="DOUBLE COMMENT '溶剂油(万吨)'")
	public Double getRongjiyou() {
		return rongjiyou;
	}
	public void setRongjiyou(Double rongjiyou) {
		this.rongjiyou = rongjiyou;
	}
	@Column(columnDefinition="DOUBLE COMMENT '石油沥青(万吨)'")
	public Double getShiyouLiqing() {
		return shiyouLiqing;
	}
	public void setShiyouLiqing(Double shiyouLiqing) {
		this.shiyouLiqing = shiyouLiqing;
	}
	@Column(columnDefinition="DOUBLE COMMENT '石油焦(万吨)'")
	public Double getShiyoujiao() {
		return shiyoujiao;
	}
	public void setShiyoujiao(Double shiyoujiao) {
		this.shiyoujiao = shiyoujiao;
	}
	@Column(columnDefinition="DOUBLE COMMENT '液化石油气(万吨)'")
	public Double getYehuaShiyouqi() {
		return yehuaShiyouqi;
	}
	public void setYehuaShiyouqi(Double yehuaShiyouqi) {
		this.yehuaShiyouqi = yehuaShiyouqi;
	}
	@Column(columnDefinition="DOUBLE COMMENT '炼厂干气(万吨)'")
	public Double getLianchangganqi() {
		return lianchangganqi;
	}
	public void setLianchangganqi(Double lianchangganqi) {
		this.lianchangganqi = lianchangganqi;
	}
	@Column(columnDefinition="DOUBLE COMMENT '其他石油制品(万吨)'")
	public Double getQitaShiyouZhipin() {
		return qitaShiyouZhipin;
	}
	public void setQitaShiyouZhipin(Double qitaShiyouZhipin) {
		this.qitaShiyouZhipin = qitaShiyouZhipin;
	}
	@Column(columnDefinition="DOUBLE COMMENT '天然气(亿立方米)'")
	public Double getTianranqi() {
		return tianranqi;
	}
	public void setTianranqi(Double tianranqi) {
		this.tianranqi = tianranqi;
	}
	@Column(columnDefinition="DOUBLE COMMENT '液化天然气(万吨)'")
	public Double getYehuaTianranqi() {
		return yehuaTianranqi;
	}
	public void setYehuaTianranqi(Double yehuaTianranqi) {
		this.yehuaTianranqi = yehuaTianranqi;
	}
	@Column(columnDefinition="DOUBLE COMMENT '热力(万百万千焦)'")
	public Double getReli() {
		return reli;
	}
	public void setReli(Double reli) {
		this.reli = reli;
	}
	@Column(columnDefinition="DOUBLE COMMENT '电力(亿千瓦小时)'")
	public Double getDianli() {
		return dianli;
	}
	public void setDianli(Double dianli) {
		this.dianli = dianli;
	}
	@Column(columnDefinition="DOUBLE COMMENT '其他能源(万吨标煤)'")
	public Double getQitanengyuan() {
		return qitanengyuan;
	}
	public void setQitanengyuan(Double qitanengyuan) {
		this.qitanengyuan = qitanengyuan;
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

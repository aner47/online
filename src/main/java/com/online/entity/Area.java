package com.online.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.online.annotation.ColumnDescription;
/**
 * 地域信息表
 * 
 * @author zuozhiping
 *
 */
@Entity
@Table(name="sys_area")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_hq_area")
public class Area extends BaseEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5895698869795389906L;
	
	/**
	 * 地域名称
	 */
	@ColumnDescription(caption="名称",filter=true)
	private String name;
	
	private int level;
	
	/**
	 * 地域编码
	 */

	@ColumnDescription(caption="代码",filter=true)
	private String code;
	
	/**
	 * 上级地域
	 */
	@ColumnDescription(caption="上级单位",filter=true)
	private String parent;
	
	
	/**
	 * 纬度最大范围
	 */
	@ColumnDescription(caption="纬度最大范围")
	private Double latitudeMax;
	/**
	 * 纬度最小范围
	 */
	@ColumnDescription(caption="纬度最小范围")
	private Double latitudeMin;
	
	/**
	 * 经度最大范围
	 */
	@ColumnDescription(caption="经度最大范围")
	private Double longitudeMax;
	/**
	 * 经度最小范围
	 */
	@ColumnDescription(caption="经度最小范围")
	private Double longitudeMin;
	
	
	/**
	 * 来源，导入或自定义，1是导入，2是自定义
	 */
	@ColumnDescription(caption="来源")
	private Integer source;
	
	
	
	
	@Column(columnDefinition="INT(11) COMMENT '来源'")
	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}
	@Column(columnDefinition="DOUBLE COMMENT '纬度最大范围'")
	public Double getLatitudeMax() {
		return latitudeMax;
	}

	public void setLatitudeMax(Double latitudeMax) {
		this.latitudeMax = latitudeMax;
	}
	@Column(columnDefinition="DOUBLE COMMENT '纬度最小范围'")
	public Double getLatitudeMin() {
		return latitudeMin;
	}

	public void setLatitudeMin(Double latitudeMin) {
		this.latitudeMin = latitudeMin;
	}
	@Column(columnDefinition="DOUBLE COMMENT '经度最大范围'")
	public Double getLongitudeMax() {
		return longitudeMax;
	}

	public void setLongitudeMax(Double longitudeMax) {
		this.longitudeMax = longitudeMax;
	}
	@Column(columnDefinition="DOUBLE COMMENT '经度最小范围'")
	public Double getLongitudeMin() {
		return longitudeMin;
	}

	public void setLongitudeMin(Double longitudeMin) {
		this.longitudeMin = longitudeMin;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '上级地域'")
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '名称'")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(unique=true,columnDefinition="VARCHAR(255) COMMENT '代码'")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="area_level",columnDefinition="INT(11) COMMENT '等级'")
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
    @Override
	public boolean equals(Object obj) {
        if (obj!=null) {
            Area area1 = (Area) obj;
            return Objects.equals(this.getCode(), area1.getCode());
        }
        return false;
	}

}

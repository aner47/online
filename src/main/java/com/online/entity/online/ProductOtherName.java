package com.online.entity.online;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online.annotation.ColumnDescription;
import com.online.entity.BaseEntity;

/**
 * 产品名称配置
 * @author zuozhiping
 *
 */
@Entity
@Table(name="ol_product_other_name",uniqueConstraints={@UniqueConstraint(columnNames={"otherName", "industryCode"})})
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_product_other_name")
public class ProductOtherName extends BaseEntity<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2545228479238327166L;

	/**
	 * 其他名称
	 */
	@ColumnDescription(caption="其他名称")
	private String otherName;

	/**
	 * 标准命名
	 */
	private ProductStandardName productStandardName;
	
	/**
	 * 产品单位配置
	 */
	private Set<ProductUnitConfig> productUnitConfigs;
	
	
	/**
	 * 行业代码
	 */
	private String industryCode;
	
	
	private String status ="unsynchronized";
	
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '其他名称'")
	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
	@ManyToOne
	public ProductStandardName getProductStandardName() {
		return productStandardName;
	}

	public void setProductStandardName(ProductStandardName productStandardName) {
		this.productStandardName = productStandardName;
	}
	@Column(columnDefinition="VARCHAR(255) COMMENT '行业代码'")
	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}


	@JsonIgnore
	@XmlTransient
	@OneToMany(mappedBy="productOtherName",fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
	public Set<ProductUnitConfig> getProductUnitConfigs() {
		return productUnitConfigs;
	}

	public void setProductUnitConfigs(Set<ProductUnitConfig> productUnitConfigs) {
		this.productUnitConfigs = productUnitConfigs;
	}

	@Column(columnDefinition="VARCHAR(255) DEFAULT 'unsynchronized' COMMENT '同步状态'")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	


	
	

}

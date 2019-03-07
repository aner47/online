package com.online.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;

import org.apache.commons.lang.builder.CompareToBuilder;


/**
 * Entity - 排序基类
 * 
 * 
 * 
 */
@MappedSuperclass
public abstract class SortEntity<ID extends Serializable> extends BaseEntity<ID> implements Comparable<SortEntity<ID>> {

	private static final long serialVersionUID = 2985598734943661667L;

	/** "排序"属性名称 */
	public static final String SORT_PROPERTY_NAME = "sort";

	/** 排序 */
	private Integer sort;

	/**
	 * 获取排序
	 * 
	 * @return 排序
	 */
//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
//	@NumericField
	@Min(0)
	@Column(name = "sort")
	public Integer getSort() {
		return sort;
	}

	/**
	 * 设置排序
	 * 
	 * @param sort
	 *            排序
	 */
	public void setSort(Integer order) {
		this.sort = order;
	}

	/**
	 * 实现compareTo方法
	 * 
	 * @param orderEntity
	 *            排序对象
	 * @return 比较结果
	 */
	public int compareTo(SortEntity<ID> orderEntity) {
		if (orderEntity == null) {
			return 1;
		}
		return new CompareToBuilder().append(getSort(), orderEntity.getSort()).append(getId(), orderEntity.getId()).toComparison();
	}

}

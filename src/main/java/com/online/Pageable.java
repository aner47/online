package com.online;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online.Filter.Operator;

/**
 * 分页信息
 *
 */
public class Pageable implements Serializable {

	private static final long serialVersionUID = -3930180379790344299L;

	/** 默认页码 */
	private static final int DEFAULT_PAGE_NUMBER = 1;

	/** 默认每页记录数 */
	private static final int DEFAULT_PAGE_SIZE = 20;

	/** 最大每页记录数 */
	private static final int MAX_PAGE_SIZE = 1000;

	/** 页码 */
	private int pageNumber = DEFAULT_PAGE_NUMBER;

	/** 每页记录数 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/** 搜索属性 */
	private String searchProperty;

	/** 搜索值 */
	private String searchValue;

	/** 排序属性 */
	private String orderProperty;

	/** 排序方向 */
	private Order.Direction orderDirection = Order.Direction.asc;

	/** 筛选 */
	private List<Filter> filters = new ArrayList<Filter>();

	/** 排序 */
	private List<Order> orders = new ArrayList<Order>();

	/**
	 * 构造方法
	 */
	public Pageable() {
	}

	/**
	 * 构造方法
	 *
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 */
	public Pageable(Integer pageNumber, Integer pageSize) {
		if (pageNumber != null && pageNumber >= 1) {
			this.pageNumber = pageNumber;
		}
		if (pageSize != null && pageSize >= 1 && pageSize <= MAX_PAGE_SIZE) {
			this.pageSize = pageSize;
		}
	}

	/**
	 * 获取页码
	 *
	 * @return 页码
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * 设置页码
	 *
	 * @param pageNumber
	 *            页码
	 */
	public void setPageNumber(int pageNumber) {
		if (pageNumber < 1) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}
		this.pageNumber = pageNumber;
	}

	public Pageable addOrder(Order order) {
		this.orders.add(order);
		return this;
	}

	public Pageable addOrder(String property, Order.Direction direction) {
		this.orders.add(new Order(property, direction));
		return this;
	}

	public Pageable addFilter(Filter filter) {
		this.filters.add(filter);
		return this;
	}

	public Pageable addFilter(String property, Filter.Operator operator, Object value) {
		if (value != null) {
			if( value instanceof String){
				if(StringUtils.isNotEmpty((String)value)){
					this.filters.add(new Filter(property, operator, value));
				}
			}else{
				this.filters.add(new Filter(property, operator, value));
			}
			
		}
		return this;
	}

	public Pageable addFilter(String property, Filter.Operator operator, Object value, boolean empty) {
		if (empty) {
			if (value != null) {
				return addFilter(property, operator, value);
			} else {
				return this;
			}
		} else {
			return addFilter(property, operator, value);
		}

	}

	/**
	 * 获取每页记录数
	 *
	 * @return 每页记录数
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页记录数
	 *
	 * @param pageSize
	 *            每页记录数
	 */
	public void setPageSize(int pageSize) {
		if (pageSize < 1 || pageSize > MAX_PAGE_SIZE) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}

	/**
	 * 获取搜索属性
	 *
	 * @return 搜索属性
	 */
	@JsonIgnore
	public String getSearchProperty() {
		return searchProperty;
	}

	/**
	 * 设置搜索属性
	 *
	 * @param searchProperty
	 *            搜索属性
	 */
	public void setSearchProperty(String searchProperty) {
		this.searchProperty = searchProperty;
	}

	/**
	 * 获取搜索值
	 *
	 * @return 搜索值
	 */
	@JsonIgnore
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * 设置搜索值
	 *
	 * @param searchValue
	 *            搜索值
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	/**
	 * 获取排序属性
	 *
	 * @return 排序属性
	 */
	@JsonIgnore
	public String getOrderProperty() {
		return orderProperty;
	}

	/**
	 * 设置排序属性
	 *
	 * @param orderProperty
	 *            排序属性
	 */
	public void setOrderProperty(String orderProperty) {
		this.orderProperty = orderProperty;
	}

	/**
	 * 获取排序方向
	 *
	 * @return 排序方向
	 */
	@JsonIgnore
	public Order.Direction getOrderDirection() {
		return orderDirection;
	}

	/**
	 * 设置排序方向
	 *
	 * @param orderDirection
	 *            排序方向
	 */
	public void setOrderDirection(Order.Direction orderDirection) {
		this.orderDirection = orderDirection;
	}

	public void addEntity(Object entity) {
		String[] numbers = {double.class.getName(),int.class.getName(),long.class.getName(),float.class.getName(),short.class.getName(),Double.class.getName(),Long.class.getName(),Integer.class.getName(),Float.class.getName()};
		if(entity !=null){
				List<String> numberList = Arrays.asList(numbers);
				Field[] fields = entity.getClass().getDeclaredFields();
				for (Field field : fields) {
					String name = field.getName();
					Object value = "";
					try {
						Method method = entity.getClass().getMethod("get"+name.substring(0, 1).toUpperCase()+name.substring(1, name.length()), null);
						value = method.invoke(entity, null)==null?"": method.invoke(entity, null);
						if (StringUtils.isNotBlank(value.toString())){
							if(numberList.contains(field.getType().getName())){
								if((double) value != 0){
									addFilter(name, Operator.eq, value);
								}
							}else{
								addFilter(name, Operator.eq, value);
							}
							
						}
					} catch (Exception e) {
						continue;
					}
				}
			
		}
	}
	

	/**
	 * 获取筛选
	 *
	 * @return 筛选
	 */
	@JsonIgnore
	public List<Filter> getFilters() {
		return filters;
	}

	/**
	 * 设置筛选
	 *
	 * @param filters
	 *            筛选
	 */
	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	/**
	 * 获取排序
	 *
	 * @return 排序
	 */
	@JsonIgnore
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * 设置排序
	 *
	 * @param orders
	 *            排序
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	/**
	 * 重写equals方法
	 *
	 * @param obj
	 *            对象
	 * @return 是否相等
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Pageable other = (Pageable) obj;
		return new EqualsBuilder().append(getPageNumber(), other.getPageNumber())
				.append(getPageSize(), other.getPageSize()).append(getSearchProperty(), other.getSearchProperty())
				.append(getSearchValue(), other.getSearchValue()).append(getOrderProperty(), other.getOrderProperty())
				.append(getOrderDirection(), other.getOrderDirection()).append(getFilters(), other.getFilters())
				.append(getOrders(), other.getOrders()).isEquals();
	}

	/**
	 * 重写hashCode方法
	 *
	 * @return HashCode
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(getPageNumber()).append(getPageSize()).append(getSearchProperty())
				.append(getSearchValue()).append(getOrderProperty()).append(getOrderDirection()).append(getFilters())
				.append(getOrders()).toHashCode();
	}

}
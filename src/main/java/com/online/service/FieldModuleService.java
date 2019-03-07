package com.online.service;

import com.online.entity.online.fieldset.FieldModule;
/**
 * 字段模块
 */
public interface FieldModuleService extends BaseService<FieldModule, Integer> {
	
	/**
	 * 根据名称和填表类型查询菜单
	 * @author 郑有权
	 * @date 创建时间：2018年1月18日 上午11:42:20 
	 * @param name		菜单名称
	 * @param tableType	填表类型
	 * @return
	 */
	public FieldModule findByNameTableType(String name,String tableType);
	/**
	 * 根据名称查询菜单
	 * @author 郑有权
	 * @date 创建时间：2018年2月5日 下午4:42:49 
	 * @param name
	 * @return
	 */
	public FieldModule findByNameTableType(String name);


}
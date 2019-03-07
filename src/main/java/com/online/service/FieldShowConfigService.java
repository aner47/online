package com.online.service;

import com.online.entity.online.fieldset.FieldShowConfig;
/**
 * 字段显示配置
 */
public interface FieldShowConfigService extends BaseService<FieldShowConfig, Integer> {

	/**
	 * 根据项目名称、填报类型、菜单名称返回隐藏字段配置
	 * @author 郑有权
	 * @date 创建时间：2018年1月18日 上午11:34:23 
	 * @param projectId		项目id
	 * @param tableType		填表类型
	 * @param fieldModule	菜单名称
	 * @return
	 */
	public FieldShowConfig findByProjectIdTableTypeFieldModule(Integer projectId,String tableType,Integer fieldModule,Integer projectTypeId);
	public FieldShowConfig findByProjectIdTableTypeFieldModule(Integer fieldModule);
	
	/**
	 * 清除配置字段
	 * @author 郑有权
	 * @date 创建时间：2018年5月25日 下午4:34:13 
	 * @param moduleId
	 * @param value
	 */
	void removeField(Integer moduleId,String value);

}
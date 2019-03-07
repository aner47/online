package com.online.service;

import com.online.entity.online.fieldset.FieldConfig;
/**
 * 字段配置
 */
public interface FieldConfigService extends BaseService<FieldConfig, Integer> {

	void deleteFieldConfig(Integer moduleId,Integer id);

}
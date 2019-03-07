package com.online.dao;

import com.online.entity.online.fieldset.FieldShowConfig;

/**
 * 字段配置DAO
 *
 */
public interface FieldShowConfigDao extends BaseDao<FieldShowConfig, Integer> {

	void removeField(Integer moduleId, String value);

}
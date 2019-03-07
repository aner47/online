package com.online.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.online.dao.FieldShowConfigDao;
import com.online.entity.online.fieldset.FieldShowConfig;

/**
 * 字段显示配置
 */
@Repository("fieldShowConfigDaoImpl")
public class FieldShowConfigDaoImpl extends BaseDaoImpl<FieldShowConfig, Integer> implements FieldShowConfigDao {

	@Override
	public void removeField(Integer moduleId, String value) {
		// TODO Auto-generated method stub
		//郑有权
		String sql = "UPDATE sys_field_show_config SET hide_field = REPLACE(hide_field,?1,'') WHERE field_module=?2";
		
		Query createNativeQuery = entityManager.createNativeQuery(sql,FieldShowConfig.class);
		createNativeQuery.setParameter(1, value);
		createNativeQuery.setParameter(2, moduleId);

		createNativeQuery.executeUpdate();
	}

   



}
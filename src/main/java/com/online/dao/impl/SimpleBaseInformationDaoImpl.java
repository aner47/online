package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.SimpleBaseInformationDao;
import com.online.entity.online.EquipmentLeaked;
import com.online.entity.online.SimpleBaseInformation;

/**
 *简版企业基本信息
 */
@Repository("simpleBaseInformationDaoImpl")
public class SimpleBaseInformationDaoImpl extends BaseDaoImpl<SimpleBaseInformation, Integer> implements SimpleBaseInformationDao {

	@Override
	public void deleteByProjectAndEnterprise(Integer projectId, Integer enterpriseId) {
		//郑有权
		String sql15 = "delete t15 FROM ol_base_info t15 WHERE enterprise =?1 and project = ?2";
		entityManager.createNativeQuery(sql15, EquipmentLeaked.class).setParameter(1, enterpriseId).setParameter(2, projectId)
				.executeUpdate();
	}




}
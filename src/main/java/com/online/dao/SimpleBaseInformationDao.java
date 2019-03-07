package com.online.dao;

import com.online.entity.online.SimpleBaseInformation;

/**
 *简版企业基本信息
 */
public interface SimpleBaseInformationDao extends BaseDao<SimpleBaseInformation, Integer> {

	public void deleteByProjectAndEnterprise(Integer ProjectId,Integer EnterpriseId);

}
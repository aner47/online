package com.online.dao;

import com.online.entity.online.Steel;

/**
 * 钢铁信息
 *
 */
public interface SteelDao extends BaseDao<Steel, Integer> {

	void deleteSteelByEnterpriseId(Integer id) throws Exception;

}
package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.GasStoveDao;
import com.online.entity.online.GasStove;

/**
 * 煤气炉
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2018年3月26日 下午3:35:17 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return
 */
@Repository("gasStoveDaoImpl")
public class GasStoveDaoImpl extends BaseDaoImpl<GasStove, Integer> implements GasStoveDao {

	@Override
	public void deleteGasStoveByEnterpriseId(Integer id) throws Exception {

		String sql = "delete t1 FROM ol_boiler_information t1 WHERE enterprise =?1";
		int t1 = entityManager.createNativeQuery(sql, GasStove.class).setParameter(1, id).executeUpdate();
	}

   


}
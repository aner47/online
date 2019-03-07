package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.PetrolSalesDao;
import com.online.entity.online.petrol.PetrolSales;

/**
 * 
 * @author 左志平
 * 
 * dao-加油站调查表
 *
 */
@Repository("petrolSalesDaoImpl")
public class PetrolSalesDaoImpl extends BaseDaoImpl<PetrolSales, Integer> implements PetrolSalesDao {

	@Override
	public void deletePetrolSalesByEnterpriseId(Integer id) throws Exception {
		//郑有权
		String sql = "delete t1 FROM ol_petrol_sales t1 WHERE enterprise =?1";
		int t1 = entityManager.createNativeQuery(sql, PetrolSales.class).setParameter(1, id).executeUpdate();
		
	}

   


}
package com.online.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.SingleBoilerFuelDao;
import com.online.entity.online.SingleBoilerFuel;
import com.online.service.SingleBoilerFuelService;

/**
 * 
 * @author 左志平
 * 
 *         锅炉信息服务实现
 *
 */
@Service("singleBoilerFuelServiceImpl")
public class SingleBoilerFuelServiceImpl extends BaseServiceImpl<SingleBoilerFuel, Integer>
		implements  SingleBoilerFuelService {
	
	@Autowired
	private SingleBoilerFuelDao singleBoilerFuelDao;

	@Override
	public void deleteSingleBoilerFuelByEnterpriseId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		//郑有权
		singleBoilerFuelDao.deleteSingleBoilerFuelByEnterpriseId(id);
	}
	

}

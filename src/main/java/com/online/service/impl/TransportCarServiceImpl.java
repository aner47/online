package com.online.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.TransportCarDao;
import com.online.entity.online.general.TransportCar;
import com.online.service.TransportCarService;

/**
 *普查表运输车
 */
@Service("transportCarServiceImpl")
public class TransportCarServiceImpl extends BaseServiceImpl<TransportCar, Integer>
		implements  TransportCarService {
	
	@Autowired
	private TransportCarDao transportCarDao;

	@Override
	public void deleteTransportCarByEnterpriseId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		//郑有权
		transportCarDao.deleteTransportCarByEnterpriseId(id);
	}
	

}

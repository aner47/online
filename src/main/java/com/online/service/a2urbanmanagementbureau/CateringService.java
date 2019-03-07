package com.online.service.a2urbanmanagementbureau;

import com.online.entity.online.a2urbanmanagementbureau.Catering;
import com.online.service.BaseService;
import com.online.service.ExportService;
/**
 * 城管局-餐饮行业信息
 */
public interface CateringService extends BaseService<Catering, Integer>,ExportService {
	
	public Catering saveCatering(Catering catering) throws Exception;
	public Catering updateCatering(Catering catering) throws Exception;

}
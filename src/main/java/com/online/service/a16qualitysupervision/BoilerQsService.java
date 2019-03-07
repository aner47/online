package com.online.service.a16qualitysupervision;

import com.online.entity.online.a16qualitysupervision.BoilerQs;
import com.online.service.BaseService;
import com.online.service.ExportService;

/**
 * 锅炉信息服务接口
 *
 */
public interface BoilerQsService extends BaseService<BoilerQs, Integer>,ExportService {
		
		public BoilerQs saveBoilerQs(BoilerQs boilerQs) throws Exception;
		public BoilerQs updateBoilerQs(BoilerQs boilerQs) throws Exception;
	
}
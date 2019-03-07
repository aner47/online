package com.online.service.a11industrialcommercial;

import com.online.entity.online.a11industrialcommercial.InCoCatering;
import com.online.service.BaseService;
import com.online.service.ExportService;

/**
 * 餐饮企业工商登记信息服务接口
 *
 */
public interface InCoCateringService extends BaseService<InCoCatering, Integer>,ExportService {
		
		public InCoCatering saveInCoCatering(InCoCatering inCoCatering) throws Exception;
		public InCoCatering updateInCoCatering(InCoCatering inCoCatering) throws Exception;
	
}
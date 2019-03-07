package com.online.service.a11industrialcommercial;

import com.online.entity.online.a11industrialcommercial.InCoBreakdownService;
import com.online.service.BaseService;
import com.online.service.ExportService;

/**
 * 汽修企业工商登记信息服务接口
 *
 */
public interface InCoBreakdownServiceService extends BaseService<InCoBreakdownService, Integer>,ExportService {
		
		public InCoBreakdownService saveInCoBreakdownService(InCoBreakdownService inCoBreakdownService) throws Exception;
		public InCoBreakdownService updateInCoBreakdownService(InCoBreakdownService inCoBreakdownService) throws Exception;
	
}
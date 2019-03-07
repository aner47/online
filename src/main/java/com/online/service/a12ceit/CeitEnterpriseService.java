package com.online.service.a12ceit;

import com.online.entity.online.a12ceit.CeitEnterprise;
import com.online.service.BaseService;
import com.online.service.ExportService;

/**
 * 工业企业信息服务接口
 *
 */
public interface CeitEnterpriseService extends BaseService<CeitEnterprise, Integer> ,ExportService{
		
		public CeitEnterprise saveCeitEnterprise(CeitEnterprise ceitEnterprise) throws Exception;
		public CeitEnterprise updateCeitEnterprise(CeitEnterprise ceitEnterprise) throws Exception;
	
}
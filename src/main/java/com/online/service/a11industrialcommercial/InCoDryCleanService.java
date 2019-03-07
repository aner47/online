package com.online.service.a11industrialcommercial;

import com.online.entity.online.a11industrialcommercial.InCoDryClean;
import com.online.service.BaseService;
import com.online.service.ExportService;

/**
 * 干洗企业工商登记信息服务接口
 *
 */
public interface InCoDryCleanService extends BaseService<InCoDryClean, Integer>,ExportService {
		
		public InCoDryClean saveInCoDryClean(InCoDryClean inCoDryClean) throws Exception;
		public InCoDryClean updateInCoDryClean(InCoDryClean inCoDryClean) throws Exception;
	
}
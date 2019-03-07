package com.online.service;

import com.online.entity.online.SingleBoilerInformation;
/**
 * 
 * @author zuozhiping
 * 
 * 锅炉信息服务接口
 *
 */
public interface SingleBoilerInformationService extends BaseService<SingleBoilerInformation, Integer> {

	void deleteSingleBoilerInformationByEnterpriseId(Integer id) throws Exception;
}
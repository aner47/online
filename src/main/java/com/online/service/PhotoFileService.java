package com.online.service;

import com.online.entity.online.PhotoFile;
/**
 * 
 * @author zuozhiping
 * 
 * 照片服务接口
 *
 */
public interface PhotoFileService extends BaseService<PhotoFile, Integer> {
	
	void deleteByEnProType(Integer enterpriseId,Integer projectId,String Type);

}
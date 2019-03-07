package com.online.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.PhotoFileDao;
import com.online.entity.online.PhotoFile;
import com.online.service.PhotoFileService;
/**
 * 
 * @author 左志平
 * 
 * 照片服务实现
 *
 */
@Service("photoFileServiceImpl")
public class PhotoFileServiceImpl extends BaseServiceImpl<PhotoFile, Integer> implements PhotoFileService {

	@Autowired
	private PhotoFileDao photoFileDao;
	
	
	@Override
	public void deleteByEnProType(Integer enterpriseId, Integer projectId, String Type) {
		photoFileDao.deleteByEnProType(enterpriseId, projectId, Type);
		
	}
	
}

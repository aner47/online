package com.online.dao;

import com.online.entity.online.PhotoFile;

/**
 * 
 * @author zuozhiping
 * 
 * dao-照片
 *
 */
public interface PhotoFileDao extends BaseDao<PhotoFile, Integer> {

	public void deleteByEnProType(Integer enterpriseId, Integer projectId, String Type);

}
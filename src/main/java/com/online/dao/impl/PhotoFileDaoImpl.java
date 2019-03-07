package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.PhotoFileDao;
import com.online.entity.online.PhotoFile;

/**
 * 
 * @author 左志平
 * 
 * dao-照片实现
 *
 */
@Repository("photoFileDaoImpl")
public class PhotoFileDaoImpl extends BaseDaoImpl<PhotoFile, Integer> implements PhotoFileDao {

	@Override
	public void deleteByEnProType(Integer enterpriseId, Integer projectId, String Type) {
		// TODO Auto-generated method stub
		//郑有权
		 String sql="delete from ol_photo_file where enterprise=?1 and project =?2 and department_type = ?3 ";
	     entityManager.createNativeQuery(sql).setParameter(1,enterpriseId).setParameter(2,projectId).setParameter(3,Type).executeUpdate();
	}



}
package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.PDFTemplateDao;
import com.online.entity.online.PDFTemplateFile;

/**
 * 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2017年9月5日 下午3:59:28 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return
 */
@Repository("pdfTemplateDaoImpl")
public class PDFTemplateDaoImpl extends BaseDaoImpl<PDFTemplateFile, Integer> implements PDFTemplateDao {

	@Override
	public PDFTemplateFile findName(String name) {
		String jpql = "select project from PDFTemplateFile project where pdftemplateName = :name";
		
		try {
			return (PDFTemplateFile)entityManager.createQuery(jpql, PDFTemplateFile.class).setParameter("name", name).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
		 
	}



}
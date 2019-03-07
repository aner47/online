package com.online.dao;

import com.online.entity.online.PDFTemplateFile;

/**
 * pdf模板
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2017年9月5日 下午3:58:27 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return
 */
public interface PDFTemplateDao extends BaseDao<PDFTemplateFile, Integer> {

	public PDFTemplateFile findName(String name);

}
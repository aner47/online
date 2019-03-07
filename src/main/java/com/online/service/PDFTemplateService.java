package com.online.service;

import com.online.entity.online.PDFTemplateFile;
/**
 * pdf模板上传
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2017年9月5日 下午3:54:23 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return
 */
public interface PDFTemplateService extends BaseService<PDFTemplateFile, Integer> {
	
	/**
	 * 
	 * @author 郑有权
	 * @date 创建时间：2018年8月9日 下午4:31:03 
	 * @param name
	 * @param projectId
	 * @param projectTypeId
	 * @param pdftemplateName
	 * @return
	 */
	public PDFTemplateFile findName(String name,Integer projectId,Integer projectTypeId,String pdftemplateName);
	
	public PDFTemplateFile savePDFTemplateFile(PDFTemplateFile pDFTemplateFile)throws Exception;
	
	public PDFTemplateFile updatePDFTemplateFile(PDFTemplateFile pdfTemplateFile)throws Exception;
	
	

}
package com.online.service;

import javax.servlet.http.HttpServletResponse;

import com.online.util.ExportLog;

/**
 * 导入下载
 */
public interface ExportService{
	
	void downloanExcel(HttpServletResponse response);
	
	ExportLog importFile(String filePath) throws Exception;
	
	
}
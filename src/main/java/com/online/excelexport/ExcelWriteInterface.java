package com.online.excelexport;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import com.online.entity.online.Enterprise;
import com.online.entity.online.ProjectModuleInfo;
import com.online.exportpdf.DataBean;

public interface ExcelWriteInterface {

	
	/**
	 * 
	 * @param projectModuleInfo 模块信息
	 * @param moduleWirterBean 模块写入参数类型
	 * @param sheet sheet 页
	 * @param enterprise 企业信息
	 * @return 返回结束列
	 */
	public  ModuleWriteReturnBean writeExcel(ProjectModuleInfo projectModuleInfo,ModuleWirterBean moduleWirterBean,Sheet sheet,Enterprise enterprise);
	public  List<DataBean> readData(ProjectModuleInfo projectModuleInfo,Enterprise enterprise);
}

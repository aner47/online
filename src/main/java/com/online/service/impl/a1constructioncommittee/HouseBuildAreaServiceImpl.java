package com.online.service.impl.a1constructioncommittee;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.DataAccessException;
import com.online.entity.online.a1constructioncommittee.HouseBuildArea;
import com.online.excelexport.Export;
import com.online.service.a1constructioncommittee.HouseBuildAreaService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.SpringUtils;
import com.online.util.image.DrawSavePicService;

/**
 * 房屋建筑施工和竣工面积情况统计
 */
@Service("houseBuildAreaServiceImpl")
public class HouseBuildAreaServiceImpl extends BaseServiceImpl<HouseBuildArea, Integer>
		implements HouseBuildAreaService{

	@Autowired
	DrawSavePicService  drawSavePicService;
	
	@Override
	public HouseBuildArea saveHouseBuildArea(HouseBuildArea houseBuildArea) throws Exception {
		//郑有权
		houseBuildArea.setProject(SpringUtils.getCurrentProject());
		return save(houseBuildArea);
	}


	@Override
	public HouseBuildArea updateHouseBuildArea(HouseBuildArea houseBuildArea) throws Exception {
		//郑有权
		return update(houseBuildArea,"project");
	}

	
	private String modelName = "房屋建筑施竣工面积";
	private String[] head = Constants.ZJWFangWuJianZhuMianJiColConfighead;

	@Override
	public void downloanExcel(HttpServletResponse response) {
		Export.exportTemplate(response, modelName+"导入模板", head);
	}

	@Override
	public ExportLog importFile(String filePath) throws Exception {
		//郑有权
		ExportLog exportLog = new ExportLog(); 
		List<Fail> fails = new ArrayList<>();
		
		StringBuffer errorLog = new StringBuffer();
		int sucessProductStandard = 0;		
		Sheet sheet = ExcelUtil.getSheet(filePath, 0);
		if(sheet == null){
			throw new DataAccessException("文件错误");
		}
		
		//表头
		Row headRow = sheet.getRow(0);
		for(int i=0;i<head.length;i++){
			String headName = ExcelUtil.getStringValue(headRow, i);
			if(!head[i].equals(headName)){
				errorLog.append("<br/>第"+(i+1)+"列表头不是"+head[i]);
			}
		}
		if(StringUtils.isNotBlank(errorLog.toString())){
			throw new DataAccessException(errorLog.toString());
		}
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {//第一行是表头
			Row row = sheet.getRow(i);
			if(ExcelUtil.isEmptyRow(row))continue;
			
            String county = ExcelUtil.getStringValues(row, 0);		//县（市、区）
            Double buildArea = ExcelUtil.getDoubleValues(row, 1);		//建筑施工面积（平方米）
            Double completedArea = ExcelUtil.getDoubleValues(row, 2);		//建筑施工面积（平方米）
    		
			
            HouseBuildArea houseBuildArea = new HouseBuildArea();
			houseBuildArea.setCounty(county);
			houseBuildArea.setBuildArea(buildArea);
			houseBuildArea.setCompletedArea(completedArea);
			houseBuildArea.setProject(SpringUtils.getCurrentProject());
				
				try {
					save(houseBuildArea);
					sucessProductStandard ++;
				} catch (Exception e) {
					fails.add(new Fail(i+1, "保存"+modelName+"失败"));
				}
			
		}	
		exportLog.setListFails(fails);
		exportLog.setSuccessNum(sucessProductStandard);
		
		return exportLog;
	}
	
	
}

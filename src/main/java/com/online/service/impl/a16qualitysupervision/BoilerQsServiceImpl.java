package com.online.service.impl.a16qualitysupervision;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.DataAccessException;
import com.online.Filter;
import com.online.entity.online.Enterprise;
import com.online.entity.online.a13powercompany.PowerPlantMonth;
import com.online.entity.online.a16qualitysupervision.BoilerQs;
import com.online.excelexport.Export;
import com.online.service.EnterpriseService;
import com.online.service.PhotoFileService;
import com.online.service.a16qualitysupervision.BoilerQsService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.DateUtils;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.image.DrawSavePicService;
import com.online.util.SpringUtils;
/**
 * 
 * 锅炉信息服务实现
 *
 */
@Service("boilerQsServiceImpl")
public class BoilerQsServiceImpl extends BaseServiceImpl<BoilerQs, Integer> implements BoilerQsService {
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private PhotoFileService photoFileService;
	@Autowired
	private DrawSavePicService  drawSavePicService;
	
	private String enterpriseNameStr = "企业名称";
	
	@Override
	public BoilerQs saveBoilerQs(BoilerQs boilerQs) throws Exception {
		//郑有权
		String enterpriseName = boilerQs.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<BoilerQs> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception(enterpriseNameStr+"已存在");
		}
		
		boilerQs.setEnterprise(enterprise);
		boilerQs.setProject(SpringUtils.getCurrentProject());
		return save(boilerQs);
	}


	@Override
	public BoilerQs updateBoilerQs(BoilerQs boilerQs) throws Exception {
		//郑有权
		String enterpriseName = boilerQs.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		BoilerQs boilerQsBack = find(boilerQs.getId());
		List<BoilerQs> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != boilerQsBack.getEnterprise().getId()){
				throw new Exception(enterpriseNameStr+"已存在");
			}
		}
		boilerQs.setEnterprise(enterprise);
		return update(boilerQs,"project");
	}
	
	
	private String modelName = "锅炉信息";
	private String[] head = Constants.ZJJGuoLuColConfighead;
	
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
		
		Map<String, BoilerQs> stMap = findAll().stream().collect(Collectors.toMap(o1->o1.getEnterprise().getName(),Function.identity(), (o1,o2)->o1));
		
		List<BoilerQs> lists = new ArrayList<>();
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {//第一行是表头
			Row row = sheet.getRow(i);
			if(ExcelUtil.isEmptyRow(row))continue;
			
			String palce = ExcelUtil.getStringValues(row, 0);		//所在地
			String city = ExcelUtil.getStringValues(row, 1);		//行政区域
			String enterpriseName = ExcelUtil.getStringValues(row, 2);		//企业名称
            String houseNumber = ExcelUtil.getStringValues(row, 3);	//详细地址
            Double longitude = ExcelUtil.getDoubleValues(row, 4);	//中心经度
            Double latitude = ExcelUtil.getDoubleValues(row, 5);	//中心纬度
            String boilerModel = ExcelUtil.getStringValues(row, 6);	//锅炉型号
            String fuelType = ExcelUtil.getStringValues(row, 7);	//燃料类型
            Double steamTon = ExcelUtil.getDoubleValues(row, 8);	//蒸吨数
            Date lastExamineDate = ExcelUtil.getDateValue(row, 9);	//上次检测日期
            Date nextExamineDate = ExcelUtil.getDateValue(row, 10);	//下次检测日期
			
    		
            BoilerQs boilerQs = stMap.get(enterpriseName);
			
			if(boilerQs == null){
				Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
				boilerQs = new BoilerQs();
				boilerQs.setPalce(palce);
				boilerQs.setCity(city);
				boilerQs.setEnterprise(enterprise);
				boilerQs.setHouseNumber(houseNumber);
				boilerQs.setLongitude(longitude);
				boilerQs.setLatitude(latitude);
				boilerQs.setBoilerModel(boilerModel);
				boilerQs.setFuelType(fuelType);
				boilerQs.setSteamTon(steamTon);
				boilerQs.setLastExamineDate(DateUtils.formatDate(lastExamineDate,"yyyy-MM-dd"));
				boilerQs.setNextExamineDate(DateUtils.formatDate(nextExamineDate,"yyyy-MM-dd"));
				boilerQs.setProject(SpringUtils.getCurrentProject());
				
				try {
					save(boilerQs);
					stMap.put(enterpriseName, boilerQs);
					sucessProductStandard ++;
					lists.add(boilerQs);
				} catch (Exception e) {
					fails.add(new Fail(i+1, "保存"+modelName+"失败"));
				}
			}else{
				fails.add(new Fail(i+1, "【"+enterpriseNameStr+"："+enterpriseName+"】存在"));
			}
			
		}	
		exportLog.setListFails(fails);
		exportLog.setSuccessNum(sucessProductStandard);
		drawSavePicService.saveImg(lists);
		return exportLog;
	}
	
	
	@Override
	public void delete(Integer id) {
		Integer enterpriseId = find(id).getEnterprise().getId();
		Integer projectId =  SpringUtils.getCurrentProject().getId();
		photoFileService.deleteByEnProType(enterpriseId,projectId,Constants.PHOTO_TYPE_ZJJGUOLU);
		//郑有权
		super.delete(id);
	}
	
	
}

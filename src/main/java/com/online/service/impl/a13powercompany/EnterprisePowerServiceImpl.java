package com.online.service.impl.a13powercompany;



import java.util.ArrayList;
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
import com.online.entity.online.a13powercompany.EnterprisePower;
import com.online.entity.online.a9agriculture.LivestockBreeding;
import com.online.excelexport.Export;
import com.online.service.EnterpriseService;
import com.online.service.PhotoFileService;
import com.online.service.a13powercompany.EnterprisePowerService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.image.DrawSavePicService;
import com.online.util.SpringUtils;
/**
 * 
 * 企业用电信息表服务实现
 *
 */
@Service("enterprisePowerServiceImpl")
public class EnterprisePowerServiceImpl extends BaseServiceImpl<EnterprisePower, Integer> implements EnterprisePowerService {
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private PhotoFileService photoFileService;
	@Autowired
	private DrawSavePicService  drawSavePicService;
	
	private String enterpriseNameStr = "企业名称";
	
	@Override
	public EnterprisePower saveEnterprisePower(EnterprisePower enterprisePower) throws Exception {
		
		//郑有权
		String enterpriseName = enterprisePower.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<EnterprisePower> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception(enterpriseNameStr+"已存在");
		}
		enterprisePower.setEnterprise(enterprise);
		enterprisePower.setProject(SpringUtils.getCurrentProject());
		return save(enterprisePower);
	}


	@Override
	public EnterprisePower updateEnterprisePower(EnterprisePower enterprisePower) throws Exception {
		//郑有权
		String enterpriseName = enterprisePower.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		EnterprisePower enterprisePowerBack = find(enterprisePower.getId());
		List<EnterprisePower> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != enterprisePowerBack.getEnterprise().getId()){
				throw new Exception(enterpriseNameStr+"已存在");
			}
		}
		enterprisePower.setEnterprise(enterprise);
		return update(enterprisePower,"project");
	}
	
	
	private String modelName = "企业用电信息";
	private String[] head = Constants.GDJQiYeYongDianColConfighead;
	
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
		
		Map<String, EnterprisePower> stMap = findAll().stream().collect(Collectors.toMap(o1->o1.getEnterprise().getName(),Function.identity(), (o1,o2)->o1));
		
		List<EnterprisePower> lists = new ArrayList<>();
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {//第一行是表头
			Row row = sheet.getRow(i);
			if(ExcelUtil.isEmptyRow(row))continue;
			
			String enterpriseName = ExcelUtil.getStringValues(row, 0);		//企业名称
            String city = ExcelUtil.getStringValues(row, 1);		//行政区
            String houseNumber = ExcelUtil.getStringValues(row, 2);	//详细地址
            String powerNo = ExcelUtil.getStringValues(row, 3);	//用电户号
            String year = ExcelUtil.getStringValues(row, 4);	//年份
            Double electricityConsumption = ExcelUtil.getDoubleValues(row, 5);	//年用电量（万千瓦时）
			
    		
            EnterprisePower enterprisePower = stMap.get(enterpriseName);
			
			if(enterprisePower == null){
				Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
				enterprisePower = new EnterprisePower();
				enterprisePower.setEnterprise(enterprise);
				enterprisePower.setCity(city);
				enterprisePower.setHouseNumber(houseNumber);
				enterprisePower.setPowerNo(powerNo);
				enterprisePower.setYear(year);
				enterprisePower.setElectricityConsumption(electricityConsumption);
				enterprisePower.setProject(SpringUtils.getCurrentProject());
				
				try {
					save(enterprisePower);
					stMap.put(enterpriseName, enterprisePower);
					sucessProductStandard ++;
					lists.add(enterprisePower);
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
		photoFileService.deleteByEnProType(enterpriseId,projectId,Constants.PHOTO_TYPE_GDJQIYEYONGDIAN);
		//郑有权
		super.delete(id);
	}
	
}

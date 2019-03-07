package com.online.service.impl.a17fuelgas;



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
import com.online.entity.online.a17fuelgas.EnterpriseFuelGas;
import com.online.excelexport.Export;
import com.online.service.EnterpriseService;
import com.online.service.PhotoFileService;
import com.online.service.a17fuelgas.EnterpriseFuelGasService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.SpringUtils;
import com.online.util.image.DrawSavePicService;
/**
 * 
 * 工业企业燃气消耗信息服务实现
 *
 */
@Service("enterpriseFuelGasServiceImpl")
public class EnterpriseFuelGasServiceImpl extends BaseServiceImpl<EnterpriseFuelGas, Integer> implements EnterpriseFuelGasService {
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private PhotoFileService photoFileService;
	@Autowired
	private DrawSavePicService  drawSavePicService;
	
	private String enterpriseNameStr = "工业企业名称";
	
	@Override
	public EnterpriseFuelGas saveEnterpriseFuelGas(EnterpriseFuelGas enterpriseFuelGas) throws Exception {
		//郑有权
		String enterpriseName = enterpriseFuelGas.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<EnterpriseFuelGas> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception(enterpriseNameStr+"已存在");
		}
		enterpriseFuelGas.setEnterprise(enterprise);
		enterpriseFuelGas.setProject(SpringUtils.getCurrentProject());
		return save(enterpriseFuelGas);
	}


	@Override
	public EnterpriseFuelGas updateEnterpriseFuelGas(EnterpriseFuelGas enterpriseFuelGas) throws Exception {
		//郑有权
		String enterpriseName = enterpriseFuelGas.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		EnterpriseFuelGas enterpriseFuelGasBack = find(enterpriseFuelGas.getId());
		List<EnterpriseFuelGas> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != enterpriseFuelGasBack.getEnterprise().getId()){
				throw new Exception(enterpriseNameStr+"已存在");
			}
		}
		enterpriseFuelGas.setEnterprise(enterprise);
		return update(enterpriseFuelGas,"project");
	}
	
	
	private String modelName = "工业企业燃气消耗信息";
	private String[] head = Constants.RQGSGongYeXiaoHaoColConfighead;
	
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
		
		Map<String, EnterpriseFuelGas> stMap = findAll().stream().collect(Collectors.toMap(o1->o1.getEnterprise().getName(),Function.identity(), (o1,o2)->o1));
		
		List<EnterpriseFuelGas> lists = new ArrayList<>();
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {//第一行是表头
			Row row = sheet.getRow(i);
			if(ExcelUtil.isEmptyRow(row))continue;
			
			String enterpriseName = ExcelUtil.getStringValues(row, 0);		//工业企业名称
			String city = ExcelUtil.getStringValues(row, 1);		//行政区
			String houseNumber = ExcelUtil.getStringValues(row, 2);		//详细地址
			Double year1 = ExcelUtil.getDoubleValues(row, 3);		//2015年天然气消耗量（万立方米）
			Double year2 = ExcelUtil.getDoubleValues(row, 4);		//2016年天然气消耗量（万立方米）
			Double year3 = ExcelUtil.getDoubleValues(row, 5);		//2017年天然气消耗量（万立方米）
    		
            EnterpriseFuelGas enterpriseFuelGas = stMap.get(enterpriseName);
			
			if(enterpriseFuelGas == null){
				Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
				enterpriseFuelGas = new EnterpriseFuelGas();
				enterpriseFuelGas.setCity(city);
				enterpriseFuelGas.setEnterprise(enterprise);
				enterpriseFuelGas.setHouseNumber(houseNumber);
				enterpriseFuelGas.setYear1(year1);
				enterpriseFuelGas.setYear2(year2);
				enterpriseFuelGas.setYear3(year3);
				enterpriseFuelGas.setProject(SpringUtils.getCurrentProject());
				
				try {
					save(enterpriseFuelGas);
					stMap.put(enterpriseName, enterpriseFuelGas);
					sucessProductStandard ++;
					lists.add(enterpriseFuelGas);
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
		photoFileService.deleteByEnProType(enterpriseId,projectId,Constants.PHOTO_TYPE_RQGSGONGYEXIAOHAO);
		//郑有权
		super.delete(id);
	}
	
}

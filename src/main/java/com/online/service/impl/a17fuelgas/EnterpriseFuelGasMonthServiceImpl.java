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
import com.online.entity.online.a17fuelgas.EnterpriseFuelGasMonth;
import com.online.excelexport.Export;
import com.online.service.EnterpriseService;
import com.online.service.PhotoFileService;
import com.online.service.a17fuelgas.EnterpriseFuelGasMonthService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.image.DrawSavePicService;
import com.online.util.SpringUtils;
/**
 * 
 * 企业燃气消耗分月统计服务实现
 *
 */
@Service("enterpriseFuelGasMonthServiceImpl")
public class EnterpriseFuelGasMonthServiceImpl extends BaseServiceImpl<EnterpriseFuelGasMonth, Integer> implements EnterpriseFuelGasMonthService {
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private PhotoFileService photoFileService;
	@Autowired
	private DrawSavePicService  drawSavePicService;
	private String enterpriseNameStr = "工业企业名称";
	
	@Override
	public EnterpriseFuelGasMonth saveEnterpriseFuelGasMonth(EnterpriseFuelGasMonth enterpriseFuelGasMonth) throws Exception {
		//郑有权
		String enterpriseName = enterpriseFuelGasMonth.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<EnterpriseFuelGasMonth> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception(enterpriseNameStr+"已存在");
		}
		enterpriseFuelGasMonth.setEnterprise(enterprise);
		enterpriseFuelGasMonth.setProject(SpringUtils.getCurrentProject());
		return save(enterpriseFuelGasMonth);
	}


	@Override
	public EnterpriseFuelGasMonth updateEnterpriseFuelGasMonth(EnterpriseFuelGasMonth enterpriseFuelGasMonth) throws Exception {
		//郑有权
		String enterpriseName = enterpriseFuelGasMonth.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		EnterpriseFuelGasMonth enterpriseFuelGasMonthBack = find(enterpriseFuelGasMonth.getId());
		List<EnterpriseFuelGasMonth> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != enterpriseFuelGasMonthBack.getEnterprise().getId()){
				throw new Exception(enterpriseNameStr+"已存在");
			}
		}
		enterpriseFuelGasMonth.setEnterprise(enterprise);
		return update(enterpriseFuelGasMonth,"project");
	}
	
	
	private String modelName = "企业燃气消耗分月统计";
	private String[] head = Constants.RQGSGongYeXiaoHaoMeiYueColConfighead;
	
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
		
		Map<String, EnterpriseFuelGasMonth> stMap = findAll().stream().collect(Collectors.toMap(o1->o1.getEnterprise().getName(),Function.identity(), (o1,o2)->o1));
		
		List<EnterpriseFuelGasMonth> lists = new ArrayList<>();
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {//第一行是表头
			Row row = sheet.getRow(i);
			if(ExcelUtil.isEmptyRow(row))continue;
			
			String enterpriseName = ExcelUtil.getStringValues(row, 0);		//工业企业名称
			Double january = ExcelUtil.getDoubleValues(row, 1);		//一月（万立方米）
            Double february = ExcelUtil.getDoubleValues(row, 2);		//二月（万立方米）
            Double march = ExcelUtil.getDoubleValues(row, 3);		//三月（万立方米）
            Double april = ExcelUtil.getDoubleValues(row, 4);		//四月（万立方米）
            Double may = ExcelUtil.getDoubleValues(row, 5);		//五月（万立方米）
            Double june = ExcelUtil.getDoubleValues(row, 6);		//六月（万立方米）
            Double july = ExcelUtil.getDoubleValues(row, 7);		//七月（万立方米）
            Double august = ExcelUtil.getDoubleValues(row, 8);		//八月（万立方米）
            Double september = ExcelUtil.getDoubleValues(row, 9);		//九月（万立方米）
            Double october = ExcelUtil.getDoubleValues(row, 10);		//十月（万立方米）
            Double november = ExcelUtil.getDoubleValues(row, 11);		//十一月（万立方米）
            Double december = ExcelUtil.getDoubleValues(row, 12);		//十二月（万立方米）
    		
			EnterpriseFuelGasMonth enterpriseFuelGasMonth = stMap.get(enterpriseName);
			
			if(enterpriseFuelGasMonth == null){
				Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
				enterpriseFuelGasMonth = new EnterpriseFuelGasMonth();
				enterpriseFuelGasMonth.setEnterprise(enterprise);
				enterpriseFuelGasMonth.setJanuary(january);
				enterpriseFuelGasMonth.setFebruary(february);
				enterpriseFuelGasMonth.setMarch(march);
				enterpriseFuelGasMonth.setApril(april);
				enterpriseFuelGasMonth.setMay(may);
				enterpriseFuelGasMonth.setJune(june);
				enterpriseFuelGasMonth.setJuly(july);
				enterpriseFuelGasMonth.setAugust(august);
				enterpriseFuelGasMonth.setSeptember(september);
				enterpriseFuelGasMonth.setOctober(october);
				enterpriseFuelGasMonth.setNovember(november);
				enterpriseFuelGasMonth.setDecember(december);
				enterpriseFuelGasMonth.setProject(SpringUtils.getCurrentProject());
				try {
					save(enterpriseFuelGasMonth);
					stMap.put(enterpriseName, enterpriseFuelGasMonth);
					sucessProductStandard ++;
					lists.add(enterpriseFuelGasMonth);
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
		photoFileService.deleteByEnProType(enterpriseId,projectId,Constants.PHOTO_TYPE_RQGSGONGYEXIAOHAOMEIYUE);
		//郑有权
		super.delete(id);
	}
	
}

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
import com.online.entity.online.a13powercompany.PowerPlantMonth;
import com.online.excelexport.Export;
import com.online.service.EnterpriseService;
import com.online.service.PhotoFileService;
import com.online.service.a13powercompany.PowerPlantMonthService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.SpringUtils;
import com.online.util.image.DrawSavePicService;
/**
 * 
 * 电厂发电量分月统计信息服务实现
 *
 */
@Service("powerPlantMonthServiceImpl")
public class PowerPlantMonthServiceImpl extends BaseServiceImpl<PowerPlantMonth, Integer> implements PowerPlantMonthService {
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private PhotoFileService photoFileService;
	@Autowired
	private DrawSavePicService  drawSavePicService;
	
	private String enterpriseNameStr = "电厂名称";
	@Override
	public PowerPlantMonth savePowerPlantMonth(PowerPlantMonth powerPlantMonth) throws Exception {
		//郑有权
		String enterpriseName = powerPlantMonth.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<PowerPlantMonth> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception(enterpriseNameStr+"已存在");
		}
		powerPlantMonth.setEnterprise(enterprise);
		powerPlantMonth.setProject(SpringUtils.getCurrentProject());
		return save(powerPlantMonth);
	}


	@Override
	public PowerPlantMonth updatePowerPlantMonth(PowerPlantMonth powerPlantMonth) throws Exception {
		//郑有权
		String enterpriseName = powerPlantMonth.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		PowerPlantMonth powerPlantMonthBack = find(powerPlantMonth.getId());
		List<PowerPlantMonth> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != powerPlantMonthBack.getEnterprise().getId()){
				throw new Exception(enterpriseNameStr+"已存在");
			}
		}
		powerPlantMonth.setEnterprise(enterprise);
		return update(powerPlantMonth,"project");
	}
	
	private String modelName = "电厂发电量分月统计信息";
	private String[] head = Constants.GDJFaDianMeiYueColConfighead;
	
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
		
		Map<String, PowerPlantMonth> stMap = findAll().stream().collect(Collectors.toMap(o1->o1.getEnterprise().getName(),Function.identity(), (o1,o2)->o1));
		
		List<PowerPlantMonth> lists = new ArrayList<>();
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {//第一行是表头
			Row row = sheet.getRow(i);
			if(ExcelUtil.isEmptyRow(row))continue;
			
			String enterpriseName = ExcelUtil.getStringValues(row, 0);		//电厂名称
            Double january = ExcelUtil.getDoubleValues(row, 1);		//一月（千瓦时）
            Double february = ExcelUtil.getDoubleValues(row, 2);		//二月（千瓦时）
            Double march = ExcelUtil.getDoubleValues(row, 3);		//三月（千瓦时）
            Double april = ExcelUtil.getDoubleValues(row, 4);		//四月（千瓦时）
            Double may = ExcelUtil.getDoubleValues(row, 5);		//五月（千瓦时）
            Double june = ExcelUtil.getDoubleValues(row, 6);		//六月（千瓦时）
            Double july = ExcelUtil.getDoubleValues(row, 7);		//七月（千瓦时）
            Double august = ExcelUtil.getDoubleValues(row, 8);		//八月（千瓦时）
            Double september = ExcelUtil.getDoubleValues(row, 9);		//九月（千瓦时）
            Double october = ExcelUtil.getDoubleValues(row, 10);		//十月（千瓦时）
            Double november = ExcelUtil.getDoubleValues(row, 11);		//十一月（千瓦时）
            Double december = ExcelUtil.getDoubleValues(row, 12);		//十二月（千瓦时）
			
    		
            PowerPlantMonth powerPlantMonth = stMap.get(enterpriseName);
			
			if(powerPlantMonth == null){
				Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
				powerPlantMonth = new PowerPlantMonth();
				powerPlantMonth.setEnterprise(enterprise);
				powerPlantMonth.setJanuary(january);
				powerPlantMonth.setFebruary(february);
				powerPlantMonth.setMarch(march);
				powerPlantMonth.setApril(april);
				powerPlantMonth.setMay(may);
				powerPlantMonth.setJune(june);
				powerPlantMonth.setJuly(july);
				powerPlantMonth.setAugust(august);
				powerPlantMonth.setSeptember(september);
				powerPlantMonth.setOctober(october);
				powerPlantMonth.setNovember(november);
				powerPlantMonth.setDecember(december);
				powerPlantMonth.setProject(SpringUtils.getCurrentProject());
				
				try {
					save(powerPlantMonth);
					stMap.put(enterpriseName, powerPlantMonth);
					sucessProductStandard ++;
					lists.add(powerPlantMonth);
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
		photoFileService.deleteByEnProType(enterpriseId,projectId,Constants.PHOTO_TYPE_GDJFADIANMEIYUE);
		//郑有权
		super.delete(id);
	}
	
}

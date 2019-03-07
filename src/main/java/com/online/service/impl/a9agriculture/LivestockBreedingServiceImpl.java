package com.online.service.impl.a9agriculture;



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
import com.online.entity.online.a9agriculture.LivestockBreeding;
import com.online.excelexport.Export;
import com.online.service.EnterpriseService;
import com.online.service.PhotoFileService;
import com.online.service.a9agriculture.LivestockBreedingService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.SpringUtils;
import com.online.util.image.DrawSavePicService;
/**
 * 
 * 畜禽养殖信息服务实现
 *
 */
@Service("livestockBreedingServiceImpl")
public class LivestockBreedingServiceImpl extends BaseServiceImpl<LivestockBreeding, Integer> implements LivestockBreedingService {
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private PhotoFileService photoFileService;
	@Autowired
	private DrawSavePicService  drawSavePicService;
	
	private String enterpriseNameStr = "养殖场名称";
	
	@Override
	public LivestockBreeding saveLivestockBreeding(LivestockBreeding livestockBreeding) throws Exception {
		
		String enterpriseName = livestockBreeding.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		livestockBreeding.setEnterprise(enterprise);
		List<LivestockBreeding> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception(enterpriseNameStr+"已存在");
		}
		livestockBreeding.setProject(SpringUtils.getCurrentProject());
		return save(livestockBreeding);
	}


	@Override
	public LivestockBreeding updateLivestockBreeding(LivestockBreeding livestockBreeding) throws Exception {
		//郑有权
		String enterpriseName = livestockBreeding.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		LivestockBreeding livestockBreedingBack = find(livestockBreeding.getId());
		List<LivestockBreeding> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != livestockBreedingBack.getEnterprise().getId()){
				throw new Exception(enterpriseNameStr+"已存在");
			}
		}
		livestockBreeding.setEnterprise(enterprise);
		return update(livestockBreeding,"project");
	}
	
	private String modelName = "畜禽养殖信息";
	private String[] head = Constants.NYWXuQinYangZhiColConfighead;
	
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
		
		Map<String, LivestockBreeding> stMap = findAll().stream().collect(Collectors.toMap(o1->o1.getEnterprise().getName(),Function.identity(), (o1,o2)->o1));
		
		List<LivestockBreeding> lists = new ArrayList<>();
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {//第一行是表头
			Row row = sheet.getRow(i);
			if(ExcelUtil.isEmptyRow(row))continue;
			
			String city = ExcelUtil.getStringValues(row, 0);		//区县
			String town = ExcelUtil.getStringValues(row, 1);		//乡镇
			String enterpriseName = ExcelUtil.getStringValues(row, 2);		//养殖场名称
			String livestockType = ExcelUtil.getStringValues(row, 3);		//畜禽类型
			String breedingMethod = ExcelUtil.getStringValues(row, 4);		//养殖方式
			Integer avgBreedingPeriod = ExcelUtil.getIntegerValue(row, 5);		//平均饲养周期（天）
			Double breedNum = ExcelUtil.getDoubleValues(row, 6);		//总饲养量（万只）
			Double outputTotal = ExcelUtil.getDoubleValues(row, 7);		//总出栏数（万只）
			Double reserveNum = ExcelUtil.getDoubleValues(row, 8);		//年末存栏量（万只）
			
    		
            LivestockBreeding livestockBreeding = stMap.get(enterpriseName);
			
			if(livestockBreeding == null){
				Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
				livestockBreeding = new LivestockBreeding();
				livestockBreeding.setCity(city);
				livestockBreeding.setEnterprise(enterprise);
				livestockBreeding.setTown(town);
				livestockBreeding.setLivestockType(livestockType);
				livestockBreeding.setBreedingMethod(breedingMethod);
				livestockBreeding.setAvgBreedingPeriod(avgBreedingPeriod);
				livestockBreeding.setBreedNum(breedNum);
				livestockBreeding.setOutputTotal(outputTotal);
				livestockBreeding.setReserveNum(reserveNum);
				livestockBreeding.setProject(SpringUtils.getCurrentProject());
				
				try {
					save(livestockBreeding);
					stMap.put(enterpriseName, livestockBreeding);
					sucessProductStandard ++;
					lists.add(livestockBreeding);
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
		photoFileService.deleteByEnProType(enterpriseId,projectId,Constants.PHOTO_TYPE_NYWXUQINYANGZHI);
		//郑有权
		super.delete(id);
	}
	
	
}

package com.online.service.impl.a4fooddrugbureau;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.DataAccessException;
import com.online.Filter;
import com.online.entity.online.Enterprise;
import com.online.entity.online.a1constructioncommittee.HouseBuildSite;
import com.online.entity.online.a4fooddrugbureau.FoodDrugCatering;
import com.online.excelexport.Export;
import com.online.service.EnterpriseService;
import com.online.service.PhotoFileService;
import com.online.service.a4fooddrugbureau.FoodDrugCateringService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.image.DrawSavePicService;
import com.online.util.SpringUtils;

/**
 * 市食药监局-餐饮行业信息
 */
@Service("foodDrugCateringServiceImpl")
public class FoodDrugCateringServiceImpl extends BaseServiceImpl<FoodDrugCatering, Integer>
		implements FoodDrugCateringService{

	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private PhotoFileService photoFileService;
	@Autowired
	private DrawSavePicService  drawSavePicService;
	
	@Override
	public FoodDrugCatering saveFoodDrugCatering(FoodDrugCatering foodDrugCatering) throws Exception {
		//郑有权
		String enterpriseName = foodDrugCatering.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<FoodDrugCatering> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception("餐企名称已存在");
		}
		foodDrugCatering.setEnterprise(enterprise);
		foodDrugCatering.setProject(SpringUtils.getCurrentProject());
		return save(foodDrugCatering);
	}


	@Override
	public FoodDrugCatering updateFoodDrugCatering(FoodDrugCatering foodDrugCatering) throws Exception {
		//郑有权
		String enterpriseName = foodDrugCatering.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		FoodDrugCatering foodDrugCateringBack = find(foodDrugCatering.getId());
		List<FoodDrugCatering> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != foodDrugCateringBack.getEnterprise().getId()){
				throw new Exception("餐企名称已存在");
			}
		}
		foodDrugCatering.setEnterprise(enterprise);
		return update(foodDrugCatering,"project");
	}

	@Override
	public void downloanExcel(HttpServletResponse response) {
		Export.exportTemplate(response, "餐饮行业信息导入模板", Constants.SYJJCanYinColConfighead);
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
		for(int i=0;i<Constants.SYJJCanYinColConfighead.length;i++){
			String headName = ExcelUtil.getStringValue(headRow, i);
			if(!Constants.SYJJCanYinColConfighead[i].equals(headName)){
				errorLog.append("<br/>第"+(i+1)+"列表头不是"+Constants.SYJJCanYinColConfighead[i]);
			}
		}
		if(StringUtils.isNotBlank(errorLog.toString())){
			throw new DataAccessException(errorLog.toString());
		}
		
		Map<String, FoodDrugCatering> stMap = findAll().stream().collect(Collectors.toMap(o1->o1.getEnterprise().getName(),Function.identity(), (o1,o2)->o1));
		
		List<FoodDrugCatering> lists = new ArrayList<>();
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {//第一行是表头
			Row row = sheet.getRow(i);
			if(ExcelUtil.isEmptyRow(row))continue;
			
            String city = ExcelUtil.getStringValues(row, 0);		//行政区
            String houseNumber = ExcelUtil.getStringValues(row, 1);	//详细地址
            String enterpriseName = ExcelUtil.getStringValues(row, 2);		//餐企名称
            Double longitude =  ExcelUtil.getDoubleValue(row, 3);		//中心经度
            Double latitude =  ExcelUtil.getDoubleValue(row, 4);		//中心纬度
            Double area =  ExcelUtil.getDoubleValue(row, 5);		//经营场所面积（平方米）
            Integer cookingRange =  ExcelUtil.getIntegerValue(row, 6);		//固定灶头数（个）
            String fuelType =  ExcelUtil.getStringValues(row, 7);		//燃料类型
            Double fuelConsumption =  ExcelUtil.getDoubleValue(row, 8);		//年燃料消耗量（吨/立方米/度）
            Double exhaustAir =  ExcelUtil.getDoubleValue(row, 9);		//单个灶头平均排风量（立方米/小时）
			
    		
            FoodDrugCatering foodDrugCatering = stMap.get(enterpriseName);
			
			if(foodDrugCatering == null){
				Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
				foodDrugCatering = new FoodDrugCatering();
				foodDrugCatering.setCity(city);
				foodDrugCatering.setHouseNumber(houseNumber);
				foodDrugCatering.setEnterprise(enterprise);
				foodDrugCatering.setLongitude(longitude);
				foodDrugCatering.setLatitude(latitude);
				foodDrugCatering.setArea(area);
				foodDrugCatering.setCookingRange(cookingRange);
				foodDrugCatering.setFuelType(fuelType);
				foodDrugCatering.setFuelConsumption(fuelConsumption);
				foodDrugCatering.setExhaustAir(exhaustAir);
				foodDrugCatering.setProject(SpringUtils.getCurrentProject());
				try {
					save(foodDrugCatering);
					stMap.put(enterpriseName, foodDrugCatering);
					sucessProductStandard ++;
					lists.add(foodDrugCatering);
				} catch (Exception e) {
					fails.add(new Fail(i+1, "保存餐饮行业信息失败"));
				}
			}else{
				fails.add(new Fail(i+1, "【餐企名称："+enterpriseName+"】存在"));
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
		photoFileService.deleteByEnProType(enterpriseId,projectId,Constants.PHOTO_TYPE_SYJJCANYIN);
		//郑有权
		super.delete(id);
	}
	
	
}

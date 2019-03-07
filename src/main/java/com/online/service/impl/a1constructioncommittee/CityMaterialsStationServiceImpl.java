package com.online.service.impl.a1constructioncommittee;

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
import com.online.entity.online.a1constructioncommittee.CityMaterialsStation;
import com.online.excelexport.Export;
import com.online.service.EnterpriseService;
import com.online.service.PhotoFileService;
import com.online.service.a1constructioncommittee.CityMaterialsStationService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.SpringUtils;
import com.online.util.image.DrawSavePicService;

/**
 * 住建委-道路施工材料站
 */
@Service("cityMaterialsStationServiceImpl")
public class CityMaterialsStationServiceImpl extends BaseServiceImpl<CityMaterialsStation, Integer>
		implements CityMaterialsStationService{

	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private PhotoFileService photoFileService;
	
	@Autowired
	private DrawSavePicService  drawSavePicService;
	@Override
	public CityMaterialsStation saveCityMaterialsStation(CityMaterialsStation cityMaterialsStation) throws Exception {
		//郑有权
		String enterpriseName = cityMaterialsStation.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<CityMaterialsStation> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception("材料站已存在");
		}
		cityMaterialsStation.setEnterprise(enterprise);
		cityMaterialsStation.setProject(SpringUtils.getCurrentProject());
		return save(cityMaterialsStation);
	}


	@Override
	public CityMaterialsStation updateCityMaterialsStation(CityMaterialsStation cityMaterialsStation) throws Exception {
		//郑有权
		String enterpriseName = cityMaterialsStation.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		CityMaterialsStation cityMaterialsStationBack = find(cityMaterialsStation.getId());
		List<CityMaterialsStation> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != cityMaterialsStationBack.getEnterprise().getId()){
				throw new Exception("材料站已存在");
			}
		}
		cityMaterialsStation.setEnterprise(enterprise);
		return update(cityMaterialsStation,"project");
	}

	@Override
	public void downloanExcel(HttpServletResponse response) {
		Export.exportTemplate(response, "全市道路施工材料站导入模板", Constants.ZJWCaiLiaoZhanColConfighead);
	}
	

	@Override
	public ExportLog importFile(String filePath) throws DataAccessException {
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
		for(int i=0;i<Constants.ZJWCaiLiaoZhanColConfighead.length;i++){
			String headName = ExcelUtil.getStringValue(headRow, i);
			if(!Constants.ZJWCaiLiaoZhanColConfighead[i].equals(headName)){
				errorLog.append("<br/>第"+(i+1)+"列表头不是"+Constants.ZJWCaiLiaoZhanColConfighead[i]);
			}
		}
		if(StringUtils.isNotBlank(errorLog.toString())){
			throw new DataAccessException(errorLog.toString());
		}
		
		Map<String, CityMaterialsStation> stMap = findAll().stream().collect(Collectors.toMap(o1->o1.getEnterprise().getName(),Function.identity(), (o1,o2)->o1));
		
		List<CityMaterialsStation> lists = new ArrayList<>();
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {//第一行是表头
			Row row = sheet.getRow(i);
			if(ExcelUtil.isEmptyRow(row))continue;
			
            String city = ExcelUtil.getStringValues(row, 0);		//行政区
            String enterpriseName = ExcelUtil.getStringValues(row, 1);		//材料站名称
            String houseNumber = ExcelUtil.getStringValues(row, 2);	//详细地址
            Double longitude =  ExcelUtil.getDoubleValue(row, 3);		//经度
            Double latitude =  ExcelUtil.getDoubleValue(row, 4);		//纬度
            Double capacity =  ExcelUtil.getDoubleValue(row, 5);		//产能（立方米/月）
            Double floorSpace =  ExcelUtil.getDoubleValue(row, 6);		//占地面积（亩）
            String stockgroundDustMeasures =  ExcelUtil.getStringValues(row, 7);		//料场抑尘措施
            String feedinletDustMeasures =  ExcelUtil.getStringValues(row, 8);		//上料口抑尘措施
            String isBagTypeDuster =  ExcelUtil.getStringValues(row, 9);		//粉料筒仓上部是否配套高效布袋除尘器
            
			
    		
            CityMaterialsStation cityMaterialsStation = stMap.get(enterpriseName);
			
			if(cityMaterialsStation == null){
				Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
				cityMaterialsStation = new CityMaterialsStation();
				cityMaterialsStation.setCity(city);
				cityMaterialsStation.setEnterprise(enterprise);
				cityMaterialsStation.setHouseNumber(houseNumber);
				cityMaterialsStation.setLongitude(longitude);
				cityMaterialsStation.setLatitude(latitude);
				cityMaterialsStation.setCapacity(capacity);
				cityMaterialsStation.setFloorSpace(floorSpace);
				cityMaterialsStation.setStockgroundDustMeasures(stockgroundDustMeasures);
				cityMaterialsStation.setFeedinletDustMeasures(feedinletDustMeasures);
				cityMaterialsStation.setIsBagTypeDuster(isBagTypeDuster);
				cityMaterialsStation.setProject(SpringUtils.getCurrentProject());
	            
				
				try {
					save(cityMaterialsStation);
					stMap.put(enterpriseName, cityMaterialsStation);
					sucessProductStandard ++;
					lists.add(cityMaterialsStation);
				} catch (Exception e) {
					fails.add(new Fail(i+1, "保存全市道路施工材料站信息失败"));
				}
			}else{
				fails.add(new Fail(i+1, "【材料站名称："+enterpriseName+"】存在"));
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
		photoFileService.deleteByEnProType(enterpriseId,projectId,Constants.PHOTO_TYPE_ZJWCAILIAOZHAN);
		//郑有权
		super.delete(id);
	}

	

}

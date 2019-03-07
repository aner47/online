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
import com.online.entity.online.a1constructioncommittee.BetonStirStation;
import com.online.excelexport.Export;
import com.online.service.EnterpriseService;
import com.online.service.ExportService;
import com.online.service.PhotoFileService;
import com.online.service.a1constructioncommittee.BetonStirStationService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.SpringUtils;
import com.online.util.image.DrawSavePicService;

/**
 * 住建委-混凝土搅拌站信息
 */
@Service("betonStirStationServiceImpl")
public class BetonStirStationServiceImpl extends BaseServiceImpl<BetonStirStation, Integer>
		implements BetonStirStationService,ExportService{

	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private PhotoFileService photoFileService;
	@Autowired
	private DrawSavePicService  drawSavePicService;
	
	@Override
	public BetonStirStation saveBetonStirStation(BetonStirStation betonStirStation) throws Exception {
		//郑有权
		String enterpriseName = betonStirStation.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<BetonStirStation> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception("搅拌站已存在");
		}
		betonStirStation.setEnterprise(enterprise);
		betonStirStation.setProject(SpringUtils.getCurrentProject());
		return save(betonStirStation);
	}


	@Override
	public BetonStirStation updateBetonStirStation(BetonStirStation betonStirStation) throws Exception {
		//郑有权
		
		String enterpriseName = betonStirStation.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		BetonStirStation betonStirStationBack = find(betonStirStation.getId());
		List<BetonStirStation> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != betonStirStationBack.getEnterprise().getId()){
				throw new Exception("搅拌站已存在");
			}
		}
		betonStirStation.setEnterprise(enterprise);
		return update(betonStirStation,"project");
	}


	@Override
	public void downloanExcel(HttpServletResponse response) {
		// TODO Auto-generated method stub
		//郑有权
		Export.exportTemplate(response, "混凝土搅拌站信息导入模板", Constants.ZJWJiaoBanZhanColConfighead);
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
		for(int i=0;i<Constants.ZJWJiaoBanZhanColConfighead.length;i++){
			String headName = ExcelUtil.getStringValue(headRow, i);
			if(!Constants.ZJWJiaoBanZhanColConfighead[i].equals(headName)){
				errorLog.append("<br/>第"+(i+1)+"列表头不是"+Constants.ZJWJiaoBanZhanColConfighead[i]);
			}
		}
		if(StringUtils.isNotBlank(errorLog.toString())){
			throw new DataAccessException(errorLog.toString());
		}
		
		Map<String, BetonStirStation> stMap = findAll().stream().collect(Collectors.toMap(o1->o1.getEnterprise().getName(),Function.identity(), (o1,o2)->o1));
		
		List<BetonStirStation> lists = new ArrayList<>();
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {//第一行是表头
			Row row = sheet.getRow(i);
			if(ExcelUtil.isEmptyRow(row))continue;
			
            String city = ExcelUtil.getStringValues(row, 0);		//行政区
            String enterpriseName = ExcelUtil.getStringValues(row, 1);		//搅拌站名称
            String houseNumber = ExcelUtil.getStringValues(row, 2);	//详细地址
            Double longitude =  ExcelUtil.getDoubleValue(row, 3);		//经度
            Double latitude =  ExcelUtil.getDoubleValue(row, 4);		//纬度
            Double capacity =  ExcelUtil.getDoubleValue(row, 5);		//产能（立方米/月）
            Double floorSpace =  ExcelUtil.getDoubleValue(row, 6);		//占地面积（亩）
            String stockgroundDustMeasures =  ExcelUtil.getStringValues(row, 7);		//料场抑尘措施
            String feedinletDustMeasures =  ExcelUtil.getStringValues(row, 8);		//上料口抑尘措施
            String isBagTypeDuster =  ExcelUtil.getStringValues(row, 9);		//粉料筒仓上部是否配套高效布袋除尘器
            
			
    		
            BetonStirStation betonStirStation = stMap.get(enterpriseName);
			
			if(betonStirStation == null){
				Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
				betonStirStation = new BetonStirStation();
				betonStirStation.setCity(city);
				betonStirStation.setEnterprise(enterprise);
				betonStirStation.setHouseNumber(houseNumber);
				betonStirStation.setLongitude(longitude);
				betonStirStation.setLatitude(latitude);
				betonStirStation.setCapacity(capacity);
				betonStirStation.setFloorSpace(floorSpace);
				betonStirStation.setStockgroundDustMeasures(stockgroundDustMeasures);
				betonStirStation.setFeedinletDustMeasures(feedinletDustMeasures);
				betonStirStation.setIsBagTypeDuster(isBagTypeDuster);
				betonStirStation.setProject(SpringUtils.getCurrentProject());
	            
				
				try {
					save(betonStirStation);
					stMap.put(enterpriseName, betonStirStation);
					sucessProductStandard ++;
					lists.add(betonStirStation);
				} catch (Exception e) {
					fails.add(new Fail(i+1, "保存混凝土搅拌站信息失败"));
				}
			}else{
				fails.add(new Fail(i+1, "【搅拌站名称："+enterpriseName+"】存在"));
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
		photoFileService.deleteByEnProType(enterpriseId,projectId,Constants.PHOTO_TYPE_ZJWJIAOBANZHAN);
		//郑有权
		super.delete(id);
	}
	

}

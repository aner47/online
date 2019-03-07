package com.online.service.impl.a3roadbureau;

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
import com.online.entity.online.a3roadbureau.PassengerStation;
import com.online.excelexport.Export;
import com.online.service.EnterpriseService;
import com.online.service.PhotoFileService;
import com.online.service.a3roadbureau.PassengerStationService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.SpringUtils;
import com.online.util.image.DrawSavePicService;

/**
 * 交通局-客运站/货运物流中心
 */
@Service("passengerStationServiceImpl")
public class PassengerStationServiceImpl extends BaseServiceImpl<PassengerStation, Integer>
		implements PassengerStationService{

	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private PhotoFileService photoFileService;
	@Autowired
	private DrawSavePicService  drawSavePicService;
	
	private String enterpriseNameStr = "客运站";
	
	@Override
	public PassengerStation savePassengerStation(PassengerStation passengerStation) throws Exception {
		//郑有权
		String enterpriseName = passengerStation.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<PassengerStation> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception(enterpriseNameStr+"已存在");
		}
		passengerStation.setEnterprise(enterprise);
		passengerStation.setProject(SpringUtils.getCurrentProject());
		return save(passengerStation);
	}


	@Override
	public PassengerStation updatePassengerStation(PassengerStation passengerStation) throws Exception {
		//郑有权
		String enterpriseName = passengerStation.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		PassengerStation passengerStationBack = find(passengerStation.getId());
		List<PassengerStation> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != passengerStationBack.getEnterprise().getId()){
				throw new Exception(enterpriseNameStr+"已存在");
			}
		}
		passengerStation.setEnterprise(enterprise);
		return update(passengerStation,"project");
	}

	
	private String modelName = "客运站/货运物流中心";
	private String[] head = Constants.JTJKeYunZhanColConfighead;

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
		
		Map<String, PassengerStation> stMap = findAll().stream().collect(Collectors.toMap(o1->o1.getEnterprise().getName(),Function.identity(), (o1,o2)->o1));
		
		List<PassengerStation> lists = new ArrayList<>();
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {//第一行是表头
			Row row = sheet.getRow(i);
			if(ExcelUtil.isEmptyRow(row))continue;
			
            String city = ExcelUtil.getStringValues(row, 0);		//行政区
            String enterpriseName = ExcelUtil.getStringValues(row, 1);		//项目名称
            String houseNumber = ExcelUtil.getStringValues(row, 2);	//工程详细地址
            Double area =  ExcelUtil.getDoubleValue(row, 3);		//占地面积
            Double longitude =  ExcelUtil.getDoubleValue(row, 4);		//经度
            Double latitude =  ExcelUtil.getDoubleValue(row, 5);		//纬度
            Integer yearInout =  ExcelUtil.getIntegerValue(row, 6);		//全年进/出车辆次数
            Integer dayInout =  ExcelUtil.getIntegerValue(row, 7);		//每天进/出车辆次数
            String carType =  ExcelUtil.getStringValues(row, 8);		//车辆类型
            String oilType =  ExcelUtil.getStringValues(row, 9);		//燃油类型
            String stayTime =  ExcelUtil.getStringValues(row, 10);		//车辆平均停留时间
			
    		
            PassengerStation passengerStation = stMap.get(enterpriseName);
			
			if(passengerStation == null){
				Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
				passengerStation = new PassengerStation();
				passengerStation.setCity(city);
				passengerStation.setEnterprise(enterprise);
				passengerStation.setHouseNumber(houseNumber);
				passengerStation.setArea(area);
				passengerStation.setLongitude(longitude);
				passengerStation.setLatitude(latitude);
				passengerStation.setYearInout(yearInout);
				passengerStation.setDayInout(dayInout);
				passengerStation.setCarType(carType);
				passengerStation.setOilType(oilType);
				passengerStation.setStayTime(stayTime);
				
				passengerStation.setProject(SpringUtils.getCurrentProject());
				
				try {
					save(passengerStation);
					stMap.put(enterpriseName, passengerStation);
					sucessProductStandard ++;
					lists.add(passengerStation);
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
			photoFileService.deleteByEnProType(enterpriseId,projectId,Constants.PHOTO_TYPE_JTJKEYUNZHAN);
			//郑有权
			super.delete(id);
		}


	
		
	

}

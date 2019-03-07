package com.online.service.impl.a5businessbureau;



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
import com.online.entity.online.a1constructioncommittee.HouseBuildSite;
import com.online.entity.online.a5businessbureau.PetrolGasStation;
import com.online.excelexport.Export;
import com.online.service.EnterpriseService;
import com.online.service.PhotoFileService;
import com.online.service.a5businessbureau.PetrolGasStationService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.image.DrawSavePicService;
import com.online.util.SpringUtils;
/**
 * 
 * @author 左志平
 * 
 * 加油站/加气站信息服务实现
 *
 */
@Service("petrolGasStationServiceImpl")
public class PetrolGasStationServiceImpl extends BaseServiceImpl<PetrolGasStation, Integer> implements PetrolGasStationService {
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private PhotoFileService photoFileService;
	@Autowired
	private DrawSavePicService  drawSavePicService;
	
	@Override
	public PetrolGasStation savePetrolGasStation(PetrolGasStation petrolGasStation) throws Exception {
		//郑有权
		String enterpriseName = petrolGasStation.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<PetrolGasStation> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception("加油站/加气站已存在");
		}
		petrolGasStation.setEnterprise(enterprise);
		petrolGasStation.setProject(SpringUtils.getCurrentProject());
		return save(petrolGasStation);
	}


	@Override
	public PetrolGasStation updatePetrolGasStation(PetrolGasStation petrolGasStation) throws Exception {
		//郑有权
		String enterpriseName = petrolGasStation.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		PetrolGasStation petrolGasStationBack = find(petrolGasStation.getId());
		List<PetrolGasStation> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != petrolGasStationBack.getEnterprise().getId()){
				throw new Exception("加油站/加气站已存在");
			}
		}
		petrolGasStation.setEnterprise(enterprise);
		return update(petrolGasStation,"project");
	}


	@Override
	public void downloanExcel(HttpServletResponse response) {
		Export.exportTemplate(response, "加油站/加气站信息导入模板", Constants.SWJJiaYouZhanColConfighead);
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
		for(int i=0;i<Constants.SWJJiaYouZhanColConfighead.length;i++){
			String headName = ExcelUtil.getStringValue(headRow, i);
			if(!Constants.SWJJiaYouZhanColConfighead[i].equals(headName)){
				errorLog.append("<br/>第"+(i+1)+"列表头不是"+Constants.SWJJiaYouZhanColConfighead[i]);
			}
		}
		if(StringUtils.isNotBlank(errorLog.toString())){
			throw new DataAccessException(errorLog.toString());
		}
		
		Map<String, PetrolGasStation> stMap = findAll().stream().collect(Collectors.toMap(o1->o1.getEnterprise().getName(),Function.identity(), (o1,o2)->o1));
		
		List<PetrolGasStation> lists = new ArrayList<>();
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {//第一行是表头
			Row row = sheet.getRow(i);
			if(ExcelUtil.isEmptyRow(row))continue;
			
			String enterpriseName = ExcelUtil.getStringValues(row, 0);		//加油站名称
            String city = ExcelUtil.getStringValues(row, 1);		//行政区
            String houseNumber = ExcelUtil.getStringValues(row, 2);	//详细地址
            Double longitude =  ExcelUtil.getDoubleValue(row, 3);		//中心经度
            Double latitude =  ExcelUtil.getDoubleValue(row, 4);		//中心纬度
            String oilType =  ExcelUtil.getStringValues(row, 5);		//油品类型
            Double annualSell =  ExcelUtil.getDoubleValue(row, 6);		//年销售量（吨）
            String oilStandard =  ExcelUtil.getStringValues(row, 7);		//油品标准
            Integer undergroundStorage =  ExcelUtil.getIntegerValue(row, 8);	//地下储罐容量（立方米）
            Integer supplyFrequency =  ExcelUtil.getIntegerValue(row, 9);	//补给频率（次/月）
            String isOilRecycle =  ExcelUtil.getStringValues(row, 10);		//是否安装油气回收装置
            Double oilRecycleRate =  ExcelUtil.getDoubleValue(row, 11);		//油气回收率（%）
			
    		
            PetrolGasStation petrolGasStation = stMap.get(enterpriseName);
			
			if(petrolGasStation == null){
				Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
				petrolGasStation = new PetrolGasStation();
				petrolGasStation.setEnterprise(enterprise);
				petrolGasStation.setCity(city);
				petrolGasStation.setHouseNumber(houseNumber);
				petrolGasStation.setLongitude(longitude);
				petrolGasStation.setLatitude(latitude);
				petrolGasStation.setOilType(oilType);
				petrolGasStation.setAnnualSell(annualSell);
				petrolGasStation.setOilStandard(oilStandard);
				petrolGasStation.setUndergroundStorage(undergroundStorage);
				petrolGasStation.setSupplyFrequency(supplyFrequency);
				petrolGasStation.setIsOilRecycle(isOilRecycle);
				petrolGasStation.setOilRecycleRate(oilRecycleRate);
				petrolGasStation.setProject(SpringUtils.getCurrentProject());
				
				try {
					save(petrolGasStation);
					stMap.put(enterpriseName, petrolGasStation);
					sucessProductStandard ++;
					lists.add(petrolGasStation);
				} catch (Exception e) {
					fails.add(new Fail(i+1, "保存加油站/加气站信息失败"));
				}
			}else{
				fails.add(new Fail(i+1, "【加油站名称："+enterpriseName+"】存在"));
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
		photoFileService.deleteByEnProType(enterpriseId,projectId,Constants.PHOTO_TYPE_SWJJIAYOUZHAN);
		//郑有权
		super.delete(id);
	}
	
}

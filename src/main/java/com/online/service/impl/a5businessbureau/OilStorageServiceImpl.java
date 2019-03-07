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
import com.online.entity.online.a5businessbureau.OilStorage;
import com.online.excelexport.Export;
import com.online.service.EnterpriseService;
import com.online.service.PhotoFileService;
import com.online.service.a5businessbureau.OilStorageService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.image.DrawSavePicService;
import com.online.util.SpringUtils;

/**
 * 市商务局-储油库信息
 */
@Service("oilStorageServiceImpl")
public class OilStorageServiceImpl extends BaseServiceImpl<OilStorage, Integer>
		implements OilStorageService{

	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private PhotoFileService photoFileService;
	@Autowired
	private DrawSavePicService  drawSavePicService;
	
	@Override
	public OilStorage saveOilStorage(OilStorage oilStorage) throws Exception {
		//郑有权
		String enterpriseName = oilStorage.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<OilStorage> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception("储油库名称已存在");
		}
		oilStorage.setEnterprise(enterprise);
		oilStorage.setProject(SpringUtils.getCurrentProject());
		return save(oilStorage);
	}


	@Override
	public OilStorage updateOilStorage(OilStorage oilStorage) throws Exception {
		//郑有权
		String enterpriseName = oilStorage.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		OilStorage oilStorageBack = find(oilStorage.getId());
		List<OilStorage> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != oilStorageBack.getEnterprise().getId()){
				throw new Exception("储油库名称已存在");
			}
		}
		oilStorage.setEnterprise(enterprise);
		return update(oilStorage,"project");
	}

	@Override
	public void downloanExcel(HttpServletResponse response) {
		Export.exportTemplate(response, "储油库信息导入模板", Constants.SWJChuYouKuColConfighead);
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
		for(int i=0;i<Constants.SWJChuYouKuColConfighead.length;i++){
			String headName = ExcelUtil.getStringValue(headRow, i);
			if(!Constants.SWJChuYouKuColConfighead[i].equals(headName)){
				errorLog.append("<br/>第"+(i+1)+"列表头不是"+Constants.SWJChuYouKuColConfighead[i]);
			}
		}
		if(StringUtils.isNotBlank(errorLog.toString())){
			throw new DataAccessException(errorLog.toString());
		}
		
		Map<String, OilStorage> stMap = findAll().stream().collect(Collectors.toMap(o1->o1.getEnterprise().getName(),Function.identity(), (o1,o2)->o1));
		
		List<OilStorage> lists = new ArrayList<>();
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {//第一行是表头
			Row row = sheet.getRow(i);
			if(ExcelUtil.isEmptyRow(row))continue;
			
			String enterpriseName = ExcelUtil.getStringValues(row, 0);		//储油库名称
            String city = ExcelUtil.getStringValues(row, 1);		//行政区
            String houseNumber = ExcelUtil.getStringValues(row, 2);	//详细地址
            Double longitude =  ExcelUtil.getDoubleValue(row, 3);		//中心经度
            Double latitude =  ExcelUtil.getDoubleValue(row, 4);		//中心纬度
            String oilType =  ExcelUtil.getStringValues(row, 5);		//油品类型
            Double annualTotal =  ExcelUtil.getDoubleValue(row, 6);		//年总储量（吨）
            String storageType =  ExcelUtil.getStringValues(row, 7);		//储罐类型
            Integer storageCycle =  ExcelUtil.getIntegerValue(row, 8);		//储存周期（天）
            String isOilRecycle =  ExcelUtil.getStringValues(row, 9);		//装卸车过程是否已安装油气回收
			
    		
            OilStorage oilStorage = stMap.get(enterpriseName);
			
			if(oilStorage == null){
				Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
				oilStorage = new OilStorage();
				oilStorage.setEnterprise(enterprise);
				oilStorage.setCity(city);
				oilStorage.setHouseNumber(houseNumber);
				oilStorage.setLongitude(longitude);
				oilStorage.setLatitude(latitude);
				oilStorage.setOilType(oilType);
				oilStorage.setAnnualTotal(annualTotal);
				oilStorage.setStorageType(storageType);
				oilStorage.setStorageCycle(storageCycle);
				oilStorage.setIsOilRecycle(isOilRecycle);
				oilStorage.setProject(SpringUtils.getCurrentProject());
				
				try {
					save(oilStorage);
					stMap.put(enterpriseName, oilStorage);
					sucessProductStandard ++;
					lists.add(oilStorage);
				} catch (Exception e) {
					fails.add(new Fail(i+1, "保存储油库信息失败"));
				}
			}else{
				fails.add(new Fail(i+1, "【储油库名称："+enterpriseName+"】存在"));
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
		photoFileService.deleteByEnProType(enterpriseId,projectId,Constants.PHOTO_TYPE_SWJCHUYOUKU);
		//郑有权
		super.delete(id);
	}
	
}

package com.online.service.impl.a2urbanmanagementbureau;

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
import com.online.entity.online.a2urbanmanagementbureau.BarbecueRestaurant;
import com.online.entity.online.a2urbanmanagementbureau.Catering;
import com.online.excelexport.Export;
import com.online.service.EnterpriseService;
import com.online.service.PhotoFileService;
import com.online.service.a2urbanmanagementbureau.BarbecueRestaurantService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.SpringUtils;
import com.online.util.image.DrawSavePicService;

/**
 * 城管局-全市烧烤店统计信息
 */
@Service("barbecueRestaurantServiceImpl")
public class BarbecueRestaurantServiceImpl extends BaseServiceImpl<BarbecueRestaurant, Integer>
		implements BarbecueRestaurantService{

	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private PhotoFileService photoFileService;
	@Autowired
	private DrawSavePicService  drawSavePicService;
	
	@Override
	public BarbecueRestaurant saveBarbecueRestaurant(BarbecueRestaurant barbecueRestaurant) throws Exception {
		//郑有权
		String enterpriseName = barbecueRestaurant.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<BarbecueRestaurant> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception("烧烤店已存在");
		}
		barbecueRestaurant.setEnterprise(enterprise);
		barbecueRestaurant.setProject(SpringUtils.getCurrentProject());
		return save(barbecueRestaurant);
	}


	@Override
	public BarbecueRestaurant updateBarbecueRestaurant(BarbecueRestaurant barbecueRestaurant) throws Exception {
		//郑有权
		String enterpriseName = barbecueRestaurant.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		BarbecueRestaurant barbecueRestaurantBack = find(barbecueRestaurant.getId());
		List<BarbecueRestaurant> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != barbecueRestaurantBack.getEnterprise().getId()){
				throw new Exception("烧烤店已存在");
			}
		}
		barbecueRestaurant.setEnterprise(enterprise);
		return update(barbecueRestaurant,"project");
	}

	@Override
	public void downloanExcel(HttpServletResponse response) {
		Export.exportTemplate(response, "烧烤店统计信息导入模板", Constants.CGJShaoKaoDianColConfighead);
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
		for(int i=0;i<Constants.CGJShaoKaoDianColConfighead.length;i++){
			String headName = ExcelUtil.getStringValue(headRow, i);
			if(!Constants.CGJShaoKaoDianColConfighead[i].equals(headName)){
				errorLog.append("<br/>第"+(i+1)+"列表头不是"+Constants.CGJShaoKaoDianColConfighead[i]);
			}
		}
		if(StringUtils.isNotBlank(errorLog.toString())){
			throw new DataAccessException(errorLog.toString());
		}
		
		Map<String, BarbecueRestaurant> stMap = findAll().stream().collect(Collectors.toMap(o1->o1.getEnterprise().getName(),Function.identity(), (o1,o2)->o1));
		
		List<BarbecueRestaurant> lists = new ArrayList<>();
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {//第一行是表头
			Row row = sheet.getRow(i);
			if(ExcelUtil.isEmptyRow(row))continue;
			
			String enterpriseName = ExcelUtil.getStringValues(row, 0);		//烧烤店名称
            String city = ExcelUtil.getStringValues(row, 1);		//行政区
            String houseNumber = ExcelUtil.getStringValues(row, 2);	//详细地址
            Double longitude =  ExcelUtil.getDoubleValue(row, 3);		//中心经度
            Double latitude =  ExcelUtil.getDoubleValue(row, 4);		//中心纬度
            String contacts =  ExcelUtil.getStringValues(row, 5);	//联系人
            String contactNumber =  ExcelUtil.getStringValues(row, 6);	//联系电话
            String isOpenBarbecue =  ExcelUtil.getStringValues(row, 7);	//是否露天烧烤
            Integer annualBusinessDays =  ExcelUtil.getIntegerValue(row, 8);	//每年经营天数（天）
            String manageHour =  ExcelUtil.getStringValues(row, 9);	//每日经营时间（时）
            String fuelType =  ExcelUtil.getStringValues(row, 10);	//燃料类型
            Double fuelConsumption =  ExcelUtil.getDoubleValue(row, 11);	//燃料消耗量
            String fuelUnit =  ExcelUtil.getStringValues(row, 12);	//燃料单位
			
    		
            BarbecueRestaurant barbecueRestaurant = stMap.get(enterpriseName);
			
			if(barbecueRestaurant == null){
				Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
				barbecueRestaurant = new BarbecueRestaurant();
				barbecueRestaurant.setEnterprise(enterprise);
				barbecueRestaurant.setCity(city);
				barbecueRestaurant.setHouseNumber(houseNumber);
				barbecueRestaurant.setLongitude(longitude);
				barbecueRestaurant.setLatitude(latitude);
				barbecueRestaurant.setContacts(contacts);
				barbecueRestaurant.setContactNumber(contactNumber);
				barbecueRestaurant.setIsOpenBarbecue(isOpenBarbecue);
				barbecueRestaurant.setAnnualBusinessDays(annualBusinessDays);
				barbecueRestaurant.setManageHour(manageHour);
				barbecueRestaurant.setFuelType(fuelType);
				barbecueRestaurant.setFuelConsumption(fuelConsumption);
				barbecueRestaurant.setFuelUnit(fuelUnit);
				barbecueRestaurant.setProject(SpringUtils.getCurrentProject());
				try {
					save(barbecueRestaurant);
					stMap.put(enterpriseName, barbecueRestaurant);
					sucessProductStandard ++;
					lists.add(barbecueRestaurant);
				} catch (Exception e) {
					fails.add(new Fail(i+1, "保存烧烤店统计信息失败"));
				}
			}else{
				fails.add(new Fail(i+1, "【烧烤店名称："+enterpriseName+"】存在"));
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
		photoFileService.deleteByEnProType(enterpriseId,projectId,Constants.PHOTO_TYPE_CGJSHAOKAODIAN);
		//郑有权
		super.delete(id);
	}

}

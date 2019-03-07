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
import com.online.entity.online.a1constructioncommittee.HouseBuildSite;
import com.online.entity.online.a2urbanmanagementbureau.Catering;
import com.online.excelexport.Export;
import com.online.service.EnterpriseService;
import com.online.service.PhotoFileService;
import com.online.service.a2urbanmanagementbureau.CateringService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.image.DrawSavePicService;
import com.online.util.SpringUtils;

/**
 * 城管局-餐饮行业信息
 */
@Service("cateringServiceImpl")
public class CateringServiceImpl extends BaseServiceImpl<Catering, Integer>
		implements CateringService{

	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private PhotoFileService photoFileService;
	@Autowired
	private DrawSavePicService  drawSavePicService;
	
	@Override
	public Catering saveCatering(Catering catering) throws Exception {
		//郑有权
		String enterpriseName = catering.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<Catering> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception("餐企已存在");
		}
		catering.setEnterprise(enterprise);
		catering.setProject(SpringUtils.getCurrentProject());
		return save(catering);
	}


	@Override
	public Catering updateCatering(Catering catering) throws Exception {
		//郑有权
		String enterpriseName = catering.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		Catering cateringBack = find(catering.getId());
		List<Catering> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != cateringBack.getEnterprise().getId()){
				throw new Exception("餐企已存在");
			}
		}
		catering.setEnterprise(enterprise);
		return update(catering,"project");
	}


	@Override
	public void downloanExcel(HttpServletResponse response) {
		Export.exportTemplate(response, "餐饮行业信息导入模板", Constants.CGJCanYinColConfighead);
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
		for(int i=0;i<Constants.CGJCanYinColConfighead.length;i++){
			String headName = ExcelUtil.getStringValue(headRow, i);
			if(!Constants.CGJCanYinColConfighead[i].equals(headName)){
				errorLog.append("<br/>第"+(i+1)+"列表头不是"+Constants.CGJCanYinColConfighead[i]);
			}
		}
		if(StringUtils.isNotBlank(errorLog.toString())){
			throw new DataAccessException(errorLog.toString());
		}
		
		Map<String, Catering> stMap = findAll().stream().collect(Collectors.toMap(o1->o1.getEnterprise().getName(),Function.identity(), (o1,o2)->o1));
		
		List<Catering> lists = new ArrayList<>();
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {//第一行是表头
			Row row = sheet.getRow(i);
			if(ExcelUtil.isEmptyRow(row))continue;
			
            String city = ExcelUtil.getStringValues(row, 0);		//行政区
            String houseNumber = ExcelUtil.getStringValues(row, 1);	//详细地址
            String enterpriseName = ExcelUtil.getStringValues(row, 2);		//餐企名称
            String isLampblackPurification =  ExcelUtil.getStringValues(row, 3);		//是否安装油烟净化装置
            Double lampblackPurificationRate =  ExcelUtil.getDoubleValue(row, 4);		//油烟净化效率
			
    		
            Catering catering = stMap.get(enterpriseName);
			
			if(catering == null){
				Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
				catering = new Catering();
				catering.setCity(city);
				catering.setHouseNumber(houseNumber);
				catering.setEnterprise(enterprise);
				catering.setIsLampblackPurification(isLampblackPurification);
				catering.setLampblackPurificationRate(lampblackPurificationRate);
				catering.setProject(SpringUtils.getCurrentProject());
				try {
					save(catering);
					stMap.put(enterpriseName, catering);
					sucessProductStandard ++;
					lists.add(catering);
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
		photoFileService.deleteByEnProType(enterpriseId,projectId,Constants.PHOTO_TYPE_CGJCANYIN);
		//郑有权
		super.delete(id);
	}

	

}

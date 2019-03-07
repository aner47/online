package com.online.service.impl.a1constructioncommittee;

import java.util.ArrayList;
import java.util.Date;
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
import com.online.entity.online.a1constructioncommittee.CityBuildSite;
import com.online.excelexport.Export;
import com.online.service.EnterpriseService;
import com.online.service.PhotoFileService;
import com.online.service.a1constructioncommittee.CityBuildSiteService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.SpringUtils;
import com.online.util.image.DrawSavePicService;

/**
 * 市政项目建筑工地信息
 */
@Service("cityBuildSiteServiceImpl")
public class CityBuildSiteServiceImpl extends BaseServiceImpl<CityBuildSite, Integer>
		implements CityBuildSiteService{

	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private PhotoFileService photoFileService;
	
	@Autowired
	DrawSavePicService  drawSavePicService;
	
	@Override
	public CityBuildSite saveCityBuildSite(CityBuildSite cityBuildSite) throws Exception {
		//郑有权
		String enterpriseName = cityBuildSite.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<CityBuildSite> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception("项目已存在");
		}
		cityBuildSite.setEnterprise(enterprise);
		cityBuildSite.setProject(SpringUtils.getCurrentProject());
		return save(cityBuildSite);
	}


	@Override
	public CityBuildSite updateCityBuildSite(CityBuildSite cityBuildSite) throws Exception {
		//郑有权
		String enterpriseName = cityBuildSite.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		CityBuildSite cityBuildSiteBack = find(cityBuildSite.getId());
		List<CityBuildSite> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != cityBuildSiteBack.getEnterprise().getId()){
				throw new Exception("项目已存在");
			}
		}
		
		cityBuildSite.setEnterprise(enterprise);
		return update(cityBuildSite,"project");
	}

	@Override
	public void downloanExcel(HttpServletResponse response) {
		Export.exportTemplate(response, "市政项目建筑工地信息导入模板", Constants.ZJWShiZhengJianZhuGongDiColConfighead);
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
		for(int i=0;i<Constants.ZJWShiZhengJianZhuGongDiColConfighead.length;i++){
			String headName = ExcelUtil.getStringValue(headRow, i);
			if(!Constants.ZJWShiZhengJianZhuGongDiColConfighead[i].equals(headName)){
				errorLog.append("<br/>第"+(i+1)+"列表头不是"+Constants.ZJWShiZhengJianZhuGongDiColConfighead[i]);
			}
		}
		if(StringUtils.isNotBlank(errorLog.toString())){
			throw new DataAccessException(errorLog.toString());
		}
		
		Map<String, CityBuildSite> stMap = findAll().stream().collect(Collectors.toMap(o1->o1.getEnterprise().getName(),Function.identity(), (o1,o2)->o1));
		
		List<CityBuildSite> lists = new ArrayList<>();
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {//第一行是表头
			Row row = sheet.getRow(i);
			if(ExcelUtil.isEmptyRow(row))continue;
			
            String city = ExcelUtil.getStringValues(row, 0);		//行政区
            String enterpriseName = ExcelUtil.getStringValues(row, 1);		//项目名称
            String houseNumber = ExcelUtil.getStringValues(row, 2);	//工程详细地址
            Double longitude =  ExcelUtil.getDoubleValue(row, 3);		//经度
            Double latitude =  ExcelUtil.getDoubleValue(row, 4);		//纬度
            Double floorSpace =  ExcelUtil.getDoubleValue(row, 5);		//占地面积
            Double buildSpace =  ExcelUtil.getDoubleValue(row, 6);		//建筑面积
            Double designExcavations =  ExcelUtil.getDoubleValue(row, 7);		//设计开挖土方量
            Double excavatedVolume =  ExcelUtil.getDoubleValue(row, 8);		//已开挖土方量
            Date startDate =  ExcelUtil.getDateValue(row, 9);		//开工时间
            Date endDate =  ExcelUtil.getDateValue(row, 10);		//竣工时间
            String dustMeasures =  ExcelUtil.getStringValues(row, 11);		//扬尘控制措施
            Double paintUseVol =  ExcelUtil.getDoubleValue(row, 12);		//建筑涂料使用量
			
    		
            CityBuildSite cityBuildSite = stMap.get(enterpriseName);
			
			if(cityBuildSite == null){
				Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
				cityBuildSite = new CityBuildSite();
				cityBuildSite.setCity(city);
				cityBuildSite.setEnterprise(enterprise);
				cityBuildSite.setHouseNumber(houseNumber);
				cityBuildSite.setLongitude(longitude);
				cityBuildSite.setLatitude(latitude);
				cityBuildSite.setFloorSpace(floorSpace);
				cityBuildSite.setBuildSpace(buildSpace);
				cityBuildSite.setDesignExcavations(designExcavations);
				cityBuildSite.setExcavatedVolume(excavatedVolume);
				cityBuildSite.setStartDate(startDate);
				cityBuildSite.setEndDate(endDate);
				cityBuildSite.setDustMeasures(dustMeasures);
				cityBuildSite.setPaintUseVol(paintUseVol);
				cityBuildSite.setProject(SpringUtils.getCurrentProject());
				
				try {
					save(cityBuildSite);
					stMap.put(enterpriseName, cityBuildSite);
					sucessProductStandard ++;
					lists.add(cityBuildSite);
				} catch (Exception e) {
					fails.add(new Fail(i+1, "保存市政项目建筑工地信息失败"));
				}
			}else{
				fails.add(new Fail(i+1, "【项目名称："+enterpriseName+"】存在"));
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
		photoFileService.deleteByEnProType(enterpriseId,projectId,Constants.PHOTO_TYPE_ZJWSHIZHENGJIANZHUGONGDI);
		//郑有权
		super.delete(id);
	}

}

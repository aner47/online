package com.online.service.impl.a1constructioncommittee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.DataAccessException;
import com.online.Filter;
import com.online.entity.online.Enterprise;
import com.online.entity.online.a1constructioncommittee.HouseBuildSite;
import com.online.excelexport.Export;
import com.online.service.EnterpriseService;
import com.online.service.ExportService;
import com.online.service.PhotoFileService;
import com.online.service.a1constructioncommittee.HouseBuildSiteService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.SpringUtils;
import com.online.util.image.DrawSavePicService;

/**
 * 房屋建筑工地信息
 */
@Service("houseBuildSiteServiceImpl")
public class HouseBuildSiteServiceImpl extends BaseServiceImpl<HouseBuildSite, Integer>
		implements HouseBuildSiteService,ExportService{

	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private PhotoFileService photoFileService;
	@Autowired
	private DrawSavePicService  drawSavePicService;
	
	private String enterpriseNameStr = "项目名称";
	
	
	
	
	@Override
	public HouseBuildSite saveHouseBuildSite(HouseBuildSite houseBuildSite) throws Exception {
		//郑有权
		String enterpriseName = houseBuildSite.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<HouseBuildSite> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception(enterpriseNameStr+"已存在");
		}
		houseBuildSite.setEnterprise(enterprise);
		houseBuildSite.setProject(SpringUtils.getCurrentProject());
		return save(houseBuildSite);
	}


	@Override
	public HouseBuildSite updateHouseBuildSite(HouseBuildSite houseBuildSite) throws Exception {
		//郑有权
		HouseBuildSite houseBuildSiteBack = find(houseBuildSite.getId());
		String enterpriseName = houseBuildSite.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<HouseBuildSite> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != houseBuildSiteBack.getEnterprise().getId()){
				throw new Exception(enterpriseNameStr+"已存在");
			}
		}
		/*if(lists !=null && lists.size()>0){
			throw new Exception("项目已存在");
		}*/
		houseBuildSite.setEnterprise(enterprise);
		return update(houseBuildSite,"project");
	}
	
	private String modelName = "房屋建筑工地信息";
	private String[] head = Constants.ZJWFangWuJianZhuGongDiColConfighead;

	@Override
	public void downloanExcel(HttpServletResponse response) {
		Export.exportTemplate(response, modelName+"导入模板", head);
	}

	@Override
	@Transactional
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
		
		Map<String, HouseBuildSite> stMap = findAll().stream().collect(Collectors.toMap(o1->o1.getEnterprise().getName(),Function.identity(), (o1,o2)->o1));
		
		List<HouseBuildSite> lists = new ArrayList<>();
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		long startTime = System.currentTimeMillis();
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
			
    		
            HouseBuildSite houseBuildSite = stMap.get(enterpriseName);
			
			if(houseBuildSite == null){
				Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
				houseBuildSite = new HouseBuildSite();
				houseBuildSite.setCity(city);
				houseBuildSite.setEnterprise(enterprise);
				houseBuildSite.setHouseNumber(houseNumber);
				houseBuildSite.setLongitude(longitude);
				houseBuildSite.setLatitude(latitude);
				houseBuildSite.setFloorSpace(floorSpace);
				houseBuildSite.setBuildSpace(buildSpace);
				houseBuildSite.setDesignExcavations(designExcavations);
				houseBuildSite.setExcavatedVolume(excavatedVolume);
				houseBuildSite.setStartDate(startDate);
				houseBuildSite.setEndDate(endDate);
				houseBuildSite.setDustMeasures(dustMeasures);
				houseBuildSite.setPaintUseVol(paintUseVol);
				houseBuildSite.setProject(SpringUtils.getCurrentProject());
				
				try {
					save(houseBuildSite);
					stMap.put(enterpriseName, houseBuildSite);
					sucessProductStandard ++;
					lists.add(houseBuildSite);
				} catch (Exception e) {
					fails.add(new Fail(i+1, "保存"+modelName+"失败"));
				}
			}else{
				fails.add(new Fail(i+1, "【"+enterpriseNameStr+"："+enterpriseName+"】存在"));
			}
			
		}	
		exportLog.setListFails(fails);
		exportLog.setSuccessNum(sucessProductStandard);
		long endTime = System.currentTimeMillis();
		System.out.println("写入数据时间："+(endTime-startTime)/1000);
		
		
		long startTime1 = System.currentTimeMillis();
		drawSavePicService.saveImg(lists);
		long endTime1 = System.currentTimeMillis();
		System.out.println("保存图片时间："+(endTime1-startTime1)/1000);
		return exportLog;
	}


	
		@Override
		public void delete(Integer id) {
			Integer enterpriseId = find(id).getEnterprise().getId();
			Integer projectId =  SpringUtils.getCurrentProject().getId();
			photoFileService.deleteByEnProType(enterpriseId,projectId,Constants.PHOTO_TYPE_ZJWFANGWUJIANZHUGONGDI);
			//郑有权
			super.delete(id);
		}


	

}

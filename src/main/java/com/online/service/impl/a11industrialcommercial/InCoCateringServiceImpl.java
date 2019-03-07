package com.online.service.impl.a11industrialcommercial;



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
import com.online.entity.online.a11industrialcommercial.InCoCatering;
import com.online.excelexport.Export;
import com.online.service.EnterpriseService;
import com.online.service.PhotoFileService;
import com.online.service.a11industrialcommercial.InCoCateringService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.SpringUtils;
import com.online.util.image.DrawSavePicService;
/**
 * 
 * 餐饮企业工商登记信息服务实现
 *
 */
@Service("inCoCateringServiceImpl")
public class InCoCateringServiceImpl extends BaseServiceImpl<InCoCatering, Integer> implements InCoCateringService {
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private PhotoFileService photoFileService;
	@Autowired
	private DrawSavePicService  drawSavePicService;
	
	@Override
	public InCoCatering saveInCoCatering(InCoCatering inCoCatering) throws Exception {
		//郑有权
		String enterpriseName = inCoCatering.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<InCoCatering> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception("餐企名称已存在");
		}
		inCoCatering.setEnterprise(enterprise);
		inCoCatering.setProject(SpringUtils.getCurrentProject());
		return save(inCoCatering);
	}


	@Override
	public InCoCatering updateInCoCatering(InCoCatering inCoCatering) throws Exception {
		//郑有权
		String enterpriseName = inCoCatering.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		InCoCatering inCoCateringBack = find(inCoCatering.getId());
		List<InCoCatering> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != inCoCateringBack.getEnterprise().getId()){
				throw new Exception("餐企名称已存在");
			}
		}
		inCoCatering.setEnterprise(enterprise);
		return update(inCoCatering,"project");
	}


	@Override
	public void downloanExcel(HttpServletResponse response) {
		Export.exportTemplate(response, "餐饮企业工商登记信息导入模板", Constants.GSJCanYinColConfighead);
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
		for(int i=0;i<Constants.GSJCanYinColConfighead.length;i++){
			String headName = ExcelUtil.getStringValue(headRow, i);
			if(!Constants.GSJCanYinColConfighead[i].equals(headName)){
				errorLog.append("<br/>第"+(i+1)+"列表头不是"+Constants.GSJCanYinColConfighead[i]);
			}
		}
		if(StringUtils.isNotBlank(errorLog.toString())){
			throw new DataAccessException(errorLog.toString());
		}
		
		Map<String, InCoCatering> stMap = findAll().stream().collect(Collectors.toMap(o1->o1.getEnterprise().getName(),Function.identity(), (o1,o2)->o1));
		
		List<InCoCatering> lists = new ArrayList<>();
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {//第一行是表头
			Row row = sheet.getRow(i);
			if(ExcelUtil.isEmptyRow(row))continue;
			
			String enterpriseName = ExcelUtil.getStringValues(row, 0);		//餐企名称
			String contacts = ExcelUtil.getStringValues(row, 1);		//法定代表人/联系人
			String contactNumber = ExcelUtil.getStringValues(row, 2);		//联系电话
            String city = ExcelUtil.getStringValues(row, 3);		//行政区
            String houseNumber = ExcelUtil.getStringValues(row, 4);	//工程详细地址
            String businessScope = ExcelUtil.getStringValues(row, 5);	//经营范围
			
    		
            InCoCatering inCoCatering = stMap.get(enterpriseName);
			
			if(inCoCatering == null){
				Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
				inCoCatering = new InCoCatering();
				inCoCatering.setEnterprise(enterprise);
				inCoCatering.setContacts(contacts);
				inCoCatering.setContactNumber(contactNumber);
				inCoCatering.setCity(city);
				inCoCatering.setHouseNumber(houseNumber);
				inCoCatering.setBusinessScope(businessScope);
				inCoCatering.setProject(SpringUtils.getCurrentProject());
				
				try {
					save(inCoCatering);
					stMap.put(enterpriseName, inCoCatering);
					sucessProductStandard ++;
					lists.add(inCoCatering);
				} catch (Exception e) {
					fails.add(new Fail(i+1, "保存餐饮企业工商登记信息失败"));
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
		photoFileService.deleteByEnProType(enterpriseId,projectId,Constants.PHOTO_TYPE_GSJCANYIN);
		//郑有权
		super.delete(id);
	}
	
}

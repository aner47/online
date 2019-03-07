package com.online.service.impl.a12ceit;



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
import com.online.entity.online.a12ceit.CeitEnterprise;
import com.online.excelexport.Export;
import com.online.service.EnterpriseService;
import com.online.service.PhotoFileService;
import com.online.service.a12ceit.CeitEnterpriseService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.SpringUtils;
import com.online.util.image.DrawSavePicService;
/**
 * 
 * 工业企业信息服务实现
 *
 */
@Service("ceitEnterpriseServiceImpl")
public class CeitEnterpriseServiceImpl extends BaseServiceImpl<CeitEnterprise, Integer> implements CeitEnterpriseService {
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private PhotoFileService photoFileService;
	@Autowired
	private DrawSavePicService  drawSavePicService;
	
	private String enterpriseNameStr = "工业企业名称";
	
	@Override
	public CeitEnterprise saveCeitEnterprise(CeitEnterprise ceitEnterprise) throws Exception {
		//郑有权
		String enterpriseName = ceitEnterprise.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<CeitEnterprise> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception(enterpriseNameStr+"已存在");
		}
		ceitEnterprise.setEnterprise(enterprise);
		ceitEnterprise.setProject(SpringUtils.getCurrentProject());
		return save(ceitEnterprise);
	}


	@Override
	public CeitEnterprise updateCeitEnterprise(CeitEnterprise ceitEnterprise) throws Exception {
		//郑有权
		String enterpriseName = ceitEnterprise.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		CeitEnterprise ceitEnterpriseBack = find(ceitEnterprise.getId());
		List<CeitEnterprise> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != ceitEnterpriseBack.getEnterprise().getId()){
				throw new Exception(enterpriseNameStr+"已存在");
			}
		}
		ceitEnterprise.setEnterprise(enterprise);
		return update(ceitEnterprise,"project");
	}
	
	private String modelName = "工业企业信息";
	private String[] head = Constants.JXWGongYeColConfighead;
	
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
		
		Map<String, CeitEnterprise> stMap = findAll().stream().collect(Collectors.toMap(o1->o1.getEnterprise().getName(),Function.identity(), (o1,o2)->o1));
		
		List<CeitEnterprise> lists = new ArrayList<>();
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {//第一行是表头
			Row row = sheet.getRow(i);
			if(ExcelUtil.isEmptyRow(row))continue;
			
			String enterpriseName = ExcelUtil.getStringValues(row, 0);		//工业企业名称
			String contacts = ExcelUtil.getStringValues(row, 1);		//联系人
			String contactNumber = ExcelUtil.getStringValues(row, 2);		//联系电话
            String city = ExcelUtil.getStringValues(row, 3);		//行政区
            String houseNumber = ExcelUtil.getStringValues(row, 4);	//详细地址
            Double electricityConsumption = ExcelUtil.getDoubleValues(row, 5);	//用电量信息
            String description = ExcelUtil.getStringValues(row, 6);	//备注
			
    		
            CeitEnterprise ceitEnterprise = stMap.get(enterpriseName);
			
			if(ceitEnterprise == null){
				Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
				ceitEnterprise = new CeitEnterprise();
				ceitEnterprise.setEnterprise(enterprise);
				ceitEnterprise.setContacts(contacts);
				ceitEnterprise.setContactNumber(contactNumber);
				ceitEnterprise.setCity(city);
				ceitEnterprise.setHouseNumber(houseNumber);
				ceitEnterprise.setElectricityConsumption(electricityConsumption);
				ceitEnterprise.setDescription(description);
				ceitEnterprise.setProject(SpringUtils.getCurrentProject());
				
				try {
					save(ceitEnterprise);
					stMap.put(enterpriseName, ceitEnterprise);
					sucessProductStandard ++;
					lists.add(ceitEnterprise);
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
		photoFileService.deleteByEnProType(enterpriseId,projectId,Constants.PHOTO_TYPE_JXWGONGYE);
		//郑有权
		super.delete(id);
	}
	
}

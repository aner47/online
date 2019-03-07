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
import com.online.entity.online.a11industrialcommercial.InCoBreakdownService;
import com.online.excelexport.Export;
import com.online.service.EnterpriseService;
import com.online.service.PhotoFileService;
import com.online.service.a11industrialcommercial.InCoBreakdownServiceService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.Constants;
import com.online.util.ExcelUtil;
import com.online.util.ExportLog;
import com.online.util.ExportLog.Fail;
import com.online.util.SpringUtils;
import com.online.util.image.DrawSavePicService;
/**
 * 
 * 汽修企业工商登记信息服务实现
 *
 */
@Service("inCoBreakdownServiceServiceImpl")
public class InCoBreakdownServiceServiceImpl extends BaseServiceImpl<InCoBreakdownService, Integer> implements InCoBreakdownServiceService {
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private PhotoFileService photoFileService;
	@Autowired
	private DrawSavePicService  drawSavePicService;
	
	@Override
	public InCoBreakdownService saveInCoBreakdownService(InCoBreakdownService inCoBreakdownService) throws Exception {
		//郑有权
		String enterpriseName = inCoBreakdownService.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<InCoBreakdownService> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception("汽修企业名称已存在");
		}
		inCoBreakdownService.setEnterprise(enterprise);
		inCoBreakdownService.setProject(SpringUtils.getCurrentProject());
		return save(inCoBreakdownService);
	}


	@Override
	public InCoBreakdownService updateInCoBreakdownService(InCoBreakdownService inCoBreakdownService) throws Exception {
		//郑有权
		String enterpriseName = inCoBreakdownService.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		InCoBreakdownService inCoBreakdownServiceBack = find(inCoBreakdownService.getId());
		List<InCoBreakdownService> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != inCoBreakdownServiceBack.getEnterprise().getId()){
				throw new Exception("汽修企业名称已存在");
			}
		}
		inCoBreakdownService.setEnterprise(enterprise);
		return update(inCoBreakdownService,"project");
	}
	
	
	@Override
	public void downloanExcel(HttpServletResponse response) {
		Export.exportTemplate(response, "汽修企业工商登记信息导入模板", Constants.GSJQiXiuColConfighead);
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
		for(int i=0;i<Constants.GSJQiXiuColConfighead.length;i++){
			String headName = ExcelUtil.getStringValue(headRow, i);
			if(!Constants.GSJQiXiuColConfighead[i].equals(headName)){
				errorLog.append("<br/>第"+(i+1)+"列表头不是"+Constants.GSJQiXiuColConfighead[i]);
			}
		}
		if(StringUtils.isNotBlank(errorLog.toString())){
			throw new DataAccessException(errorLog.toString());
		}
		
		Map<String, InCoBreakdownService> stMap = findAll().stream().collect(Collectors.toMap(o1->o1.getEnterprise().getName(),Function.identity(), (o1,o2)->o1));
		
		List<InCoBreakdownService> lists = new ArrayList<>();
		
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {//第一行是表头
			Row row = sheet.getRow(i);
			if(ExcelUtil.isEmptyRow(row))continue;
			
			String enterpriseName = ExcelUtil.getStringValues(row, 0);		//汽修企业名称
			String contacts = ExcelUtil.getStringValues(row, 1);		//法定代表人/联系人
			String contactNumber = ExcelUtil.getStringValues(row, 2);		//联系电话
            String city = ExcelUtil.getStringValues(row, 3);		//行政区
            String houseNumber = ExcelUtil.getStringValues(row, 4);	//工程详细地址
            Double usePaintQuantity = ExcelUtil.getDoubleValues(row, 5);	//油漆使用量/吨
			
    		
            InCoBreakdownService inCoBreakdownService = stMap.get(enterpriseName);
			
			if(inCoBreakdownService == null){
				Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
				inCoBreakdownService = new InCoBreakdownService();
				inCoBreakdownService.setEnterprise(enterprise);
				inCoBreakdownService.setContacts(contacts);
				inCoBreakdownService.setContactNumber(contactNumber);
				inCoBreakdownService.setCity(city);
				inCoBreakdownService.setHouseNumber(houseNumber);
				inCoBreakdownService.setUsePaintQuantity(usePaintQuantity);
				inCoBreakdownService.setProject(SpringUtils.getCurrentProject());
				
				try {
					save(inCoBreakdownService);
					stMap.put(enterpriseName, inCoBreakdownService);
					sucessProductStandard ++;
					lists.add(inCoBreakdownService);
				} catch (Exception e) {
					fails.add(new Fail(i+1, "保存汽修企业工商登记信息失败"));
				}
			}else{
				fails.add(new Fail(i+1, "【汽修企业名称："+enterpriseName+"】存在"));
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
		photoFileService.deleteByEnProType(enterpriseId,projectId,Constants.PHOTO_TYPE_GSJQIXIU);
		//郑有权
		super.delete(id);
	}
	
}

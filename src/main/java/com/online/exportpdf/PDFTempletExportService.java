package com.online.exportpdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.online.DataAccessException;
import com.online.Filter;
import com.online.entity.ProjectEnterpriseUser;
import com.online.entity.ProjectType;
import com.online.entity.SystemConfig;
import com.online.entity.online.Enterprise;
import com.online.entity.online.ModuleDetail;
import com.online.entity.online.PDFTemplateFile;
import com.online.entity.online.Project;
import com.online.entity.online.ProjectModuleDetail;
import com.online.entity.online.ProjectModuleInfo;
import com.online.excelexport.BeanUtils;
import com.online.excelexport.ExcelWriteInterface;
import com.online.excelexport.Export;
import com.online.service.BaseInformationService;
import com.online.service.BreakdownServiceBaseService;
import com.online.service.CateringBaseService;
import com.online.service.ConstructionSiteBaseService;
import com.online.service.DryClearBaseService;
import com.online.service.ModuleDetailService;
import com.online.service.PDFTemplateService;
import com.online.service.PetrolStationBaseService;
import com.online.service.ProjectEnterpriseUserService;
import com.online.service.ProjectService;
import com.online.service.ProjectTypeService;
import com.online.service.SimpleBaseInformationService;
import com.online.service.SystemConfigService;
import com.online.util.Constants;
import com.online.util.ZipUtils;

/**
 * @author 作者 E-mail: 郑有权
 * @date 创建时间：2017年8月21日 下午2:01:48
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Component
public class PDFTempletExportService {

	@Autowired
	private BaseInformationService baseInformationService;
	@Autowired
	private SimpleBaseInformationService simpleBaseInformationService;
	@Autowired
	private ConstructionSiteBaseService constructionSiteBaseService;
	@Autowired
	private PetrolStationBaseService petrolStationBaseService;
	@Autowired
	private DryClearBaseService dryClearBaseService;
	@Autowired
	private BreakdownServiceBaseService breakdownServiceBaseService;
	@Autowired
	private CateringBaseService cateringBaseService;
	@Autowired
	private ModuleDetailService moduleDetailService;
	@Autowired
	private PDFTemplateService pdfTemplateService;
	@Autowired
	private Export export;
	
	@Autowired 
	private ProjectEnterpriseUserService projectEnterpriseUserService;
	
	@Autowired
	private SystemConfigService  systemConfigService;
	@Autowired
	private ProjectTypeService  projectTypeService;
	@Autowired
	private ProjectService  projectService;

	/**
	 * 导出pdf
	 * @author 郑有权
	 * @date 创建时间：2017年9月14日 上午10:19:24 
	 * @param out			输出流	
	 * @param projectid		项目id
	 * @param password		查看密码
	 * @param readPassword	修改权限密码
	 * @param imageFile		水印图片
	 * @param waterChar		水印文字
	 * @param enterprises	企业集合
	 * @throws IOException	
	 * @throws DocumentException
	 * @throws DataAccessException
	 */
	public void exprotAllEnterprise(OutputStream out, Integer projectid, String password, String readPassword,String imageFile,
			String waterChar, List<Enterprise> enterprises,boolean isFinish) throws IOException, DocumentException, DataAccessException {

		if(enterprises != null && enterprises.size() == 1){
			//如果完成不显示水印
			if(isFinish){
				exportPdf(enterprises.get(0), projectid, out);
			}else{
				String newPDFPath = UUID.randomUUID().toString()  + ".pdf";
				OutputStream fileout = new FileOutputStream(newPDFPath);
				// 一个企业生成一个pdf文件
				exportPdf(enterprises.get(0), projectid, fileout);
				try {
					fileout.flush();
					fileout.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
				PDFTempletTicket.waterMark(password.getBytes(), readPassword.getBytes(), newPDFPath,imageFile,
						out, waterChar, PdfWriter.ALLOW_PRINTING);
				File file = new File(newPDFPath);
				file.delete();
			}
			
			
		}else{
			ZipOutputStream zipout = new ZipOutputStream(out);
			// 根据项目查询所有企业
			if (enterprises != null && enterprises.size() > 0) {
				for (int i = 0; i < enterprises.size(); i++) {
					// 生成的新文件路径
					String newPDFPath = UUID.randomUUID().toString() + i + ".pdf";
					OutputStream fileout = new FileOutputStream(newPDFPath);
					// 一个企业生成一个pdf文件
					exportPdf(enterprises.get(i), projectid, fileout);
					String newPDFPath1 = enterprises.get(i).getName().replaceAll("\\\\", "").replaceAll("/", "") + "_" + i + ".pdf";
					PDFTempletTicket.waterMark(password.getBytes(), readPassword.getBytes(), newPDFPath,imageFile,
							new FileOutputStream(newPDFPath1), waterChar, PdfWriter.ALLOW_SCREENREADERS);
					
					ZipUtils.doCompress(newPDFPath1, zipout);
				}
			}
			
			try {
				zipout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * 
	 * @author 郑有权
	 * @date 创建时间：2017年8月30日 下午1:53:36
	 * @param enterprise
	 *            企业
	 * @param projectid
	 *            项目 id
	 * @param out
	 *            输出文件流
	 * @throws DataAccessException
	 * @throws DocumentException
	 * @throws IOException
	 * @throws Exception
	 */
	public void exportPdf(Enterprise enterprise, Integer projectid)
			throws IOException, DocumentException, DataAccessException {

		ProjectEnterpriseUser projectEnterpriseUser = projectEnterpriseUserService.findProjectTypeByProjectIdAndEnterpriseId(projectid,
				enterprise.getId());
		
		Integer projectType = -1;
		if(projectEnterpriseUser != null){
			projectType = projectEnterpriseUser.getProjectType();
		}
		if(projectType == null || projectType ==-1  ){
			throw new DataAccessException("未配置项目类型，请联系管理员");
		}
		
		PDFTemplateFile pdfTemplateFile = pdfTemplateService.findName(enterprise.getEnterpriseType().toString(),projectid,projectType,"");
		// 默认模板路径
		String templatePath = "template.pdf";
		if (pdfTemplateFile != null) {
			// 模板路径
			if(StringUtils.isNotBlank(pdfTemplateFile.getUrl())){
				templatePath = pdfTemplateFile.getUrl();
				try {
					// 读取pdf模板
					PdfReader reader = new PdfReader(templatePath);
					reader.close();
				} catch (IOException e1) {
					throw new DataAccessException("读取不到模板，请联系管理员");
				}
			}else{
				//未上传pdf模板
				throw new DataAccessException("未上传pdf模板，请联系管理员");
			}
			
		}else{
			//未配置pdf导出模块
			throw new DataAccessException("未配置pdf导出模块，请联系管理员");
		}

	}
	/**
	 * 
	 * @author 郑有权
	 * @date 创建时间：2017年8月30日 下午1:53:36
	 * @param enterprise
	 *            企业
	 * @param projectid
	 *            项目 id
	 * @param out
	 *            输出文件流
	 * @throws DataAccessException
	 * @throws DocumentException
	 * @throws IOException
	 * @throws Exception
	 */
	public void exportPdf(Enterprise enterprise, Integer projectid, OutputStream out)
			throws IOException, DocumentException, DataAccessException {
		
		PDFTempletTicket pdfTT = new PDFTempletTicket();
		
		// 获取模板对应字段--企业信息
		Map<String, String> fieldnames = new LinkedHashMap<>();
		List<ModuleDetail> enterprisemoduleDetails = moduleDetailService.getListByModuleId(1);
		for (ModuleDetail moduleDetail : enterprisemoduleDetails) {
			fieldnames.put(moduleDetail.getHead(), Constants.convert(moduleDetail.getHead(),pdfTT.getFieldValue(moduleDetail.getKey(), enterprise)));
		}
		
		if (enterprise != null && enterprise.getAddress() != null) {
			double log = enterprise.getAddress().getLongitude();
			int log1 = (int) log;
			int log2;
			float log3;
			
			if (log < 0) {
				log2 = (int) (((log * -1) - (log1 * -1)) * 60);
				log3 = Float.parseFloat(((((log * -1) - (log1 * -1)) * 60 - log2) * 60) + "");
			} else {
				log2 = (int) ((log - log1) * 60);
				log3 = Float.parseFloat((((log - log1) * 60 - log2) * 60) + "");
			}
			
			double lat = enterprise.getAddress().getLatitude();
			int lat1 = (int) lat;
			int lat2;
			float lat3;
			if (lat1 < 0) {
				lat2 = (int) (((lat * -1) - (lat1 * -1)) * 60);
				lat3 = Float.parseFloat(((((lat * -1) - (lat1 * -1)) * 60 - lat2) * 60) + "");
			} else {
				lat2 = (int) ((lat - lat1) * 60);
				lat3 = Float.parseFloat((((lat - lat1) * 60 - lat2) * 60) + "");
			}
			
			// 企业特殊字段处理
			fieldnames.put("经度度", log1 + "");
			fieldnames.put("经度分", log2 + "");
			fieldnames.put("经度秒", log3 + "");
			fieldnames.put("纬度度", lat1 + "");
			fieldnames.put("纬度分", lat2 + "");
			fieldnames.put("纬度秒", lat3 + "");
		}
		
		
		ProjectEnterpriseUser projectEnterpriseUser = projectEnterpriseUserService.findProjectTypeByProjectIdAndEnterpriseId(projectid,
				enterprise.getId());
		
		Integer projectType = -1;
		if(projectEnterpriseUser != null){
			projectType = projectEnterpriseUser.getProjectType();
			fieldnames.put("提交时间",projectEnterpriseUser.getSubmitDate() == null?"":projectEnterpriseUser.getSubmitDate()+"");
		}
		
		if(projectType == null || projectType ==-1  ){
			throw new DataAccessException("未配置项目类型,企业项目可能不匹配，请联系管理员");
		}
		
		
		PDFTemplateFile pdfTemplateFile = pdfTemplateService.findName(enterprise.getEnterpriseType().toString(),projectid,projectType,"");
		// 默认模板路径
		String templatePath = "template.pdf";
		if (pdfTemplateFile != null) {
			// 模板路径
			if(StringUtils.isNotBlank(pdfTemplateFile.getUrl())){
				templatePath = pdfTemplateFile.getUrl();
			}else{
				//未上传pdf模板
				
			}
			DataBean dataBean = findDataBeanByEnterprise(enterprise, projectid);
			// 获取基本信息，对应关系
			if (pdfTemplateFile != null && StringUtils.isNotBlank(pdfTemplateFile.getProjectModelName())) {
				//
				List<ModuleDetail> moduleDetails = moduleDetailService
						.getListByModuleId(Integer.parseInt(pdfTemplateFile.getProjectModelName()));
				for (ModuleDetail moduleDetail : moduleDetails) {
					/*if(moduleDetail.getHead().indexOf("关停时间") != -1 || moduleDetail.getHead().indexOf("投运时间") != -1){
						fieldnames.put(moduleDetail.getHead(), pdfTT.getFieldValue(moduleDetail.getKey(), dataBean) != null &&pdfTT.getFieldValue(moduleDetail.getKey(), dataBean).length()>8 ?pdfTT.getFieldValue(moduleDetail.getKey(), dataBean).substring(0, 7):"");
					}
					else if(moduleDetail.getHead().indexOf("装置安装时间") != -1 || moduleDetail.getHead().indexOf("投运时间") != -1){
						fieldnames.put(moduleDetail.getHead(), pdfTT.getFieldValue(moduleDetail.getKey(), dataBean) != null &&pdfTT.getFieldValue(moduleDetail.getKey(), dataBean).length()>8 ?pdfTT.getFieldValue(moduleDetail.getKey(), dataBean).substring(0, 7):"");
					}
//					else if(moduleDetail.getHead().indexOf("生产时段") != -1 ){
//						fieldnames.put(moduleDetail.getHead(), pdfTT.getFieldValue(moduleDetail.getKey(), dataBean) != null &&pdfTT.getFieldValue(moduleDetail.getKey(), dataBean).length()>8 ?pdfTT.getFieldValue(moduleDetail.getKey(), dataBean).substring(10):"");
//					}
					else{
						fieldnames.put(moduleDetail.getHead(), convert(pdfTT.getFieldValue(moduleDetail.getKey(), dataBean)));
					}*/
					
					fieldnames.put(moduleDetail.getHead(),Constants.convert(moduleDetail.getHead(),pdfTT.getFieldValue(moduleDetail.getKey(), dataBean)));
					
				}
			}
		}else{
			//未配置pdf导出模块
			throw new DataAccessException("未配置pdf导出模块，请联系管理员");
		}
		pdfTT.setTemplatePath(templatePath);
		ByteArrayOutputStream outList = backTableOut(pdfTT, enterprise, projectid, pdfTemplateFile);
		pdfTT.fillTemplate(fieldnames, outList, out);
		
	}
	
	
	
	

	/**
	 * 返回基本信息数据
	 * 
	 * @author 郑有权
	 * @date 创建时间：2017年9月11日 下午9:05:13
	 * @param enterprise
	 * @param projectid
	 * @return
	 */
	public DataBean findDataBeanByEnterprise(Enterprise enterprise, Integer projectid) {

		// 详表
		if ("NORMAL".equals(enterprise.getEnterpriseType().toString())) {
			return baseInformationService.findListByProjectAndEnterprise(projectid, enterprise.getId());
			// 简表
		} else if ("SIMPLE".equals(enterprise.getEnterpriseType().toString())) {
			return simpleBaseInformationService.findListByProjectAndEnterprise(projectid, enterprise.getId());
			// 单一锅炉
		} else if ("SINGLE_BOILER".equals(enterprise.getEnterpriseType().toString())) {

			// 施工工地
		} else if ("CONSTRUCTION_SITE".equals(enterprise.getEnterpriseType().toString())) {
			return constructionSiteBaseService.findListByProjectAndEnterprise(projectid, enterprise.getId());
			// 加油站
		} else if ("PETROL_STATION".equals(enterprise.getEnterpriseType().toString())) {
			return petrolStationBaseService.findListByProjectAndEnterprise(projectid, enterprise.getId());
			// 干洗
		} else if ("DRY_CLEAR".equals(enterprise.getEnterpriseType().toString())) {
			return dryClearBaseService.findListByProjectAndEnterprise(projectid, enterprise.getId());
			// 汽修
		} else if ("BREAKDOWN_SERVICE".equals(enterprise.getEnterpriseType().toString())) {
			return breakdownServiceBaseService.findListByProjectAndEnterprise(projectid, enterprise.getId());
			// 畜禽养殖
		} else if ("BEASTS_BIRDS".equals(enterprise.getEnterpriseType().toString())) {

			// 餐饮
		} else if ("CATERING".equals(enterprise.getEnterpriseType().toString())) {
			return cateringBaseService.findListByProjectAndEnterprise(projectid, enterprise.getId());
			// 普查表
		} else if ("GENERAL".equals(enterprise.getEnterpriseType().toString())) {
			return simpleBaseInformationService.findListByProjectAndEnterprise(projectid, enterprise.getId());
		}

		return null;

	}
	/**
	 * 表格数据填写
	 * @author 郑有权
	 * @date 创建时间：2017年9月12日 下午6:35:39 
	 * @param pdfTT
	 * @param enterprise	企业信息
	 * @param projectid		项目id
	 * @param pdfTemplateFile
	 * @return
	 * @throws BadElementException
	 */
	public ByteArrayOutputStream backTableOut(PDFTempletTicket pdfTT, Enterprise enterprise, Integer projectid,
			PDFTemplateFile pdfTemplateFile) throws BadElementException {
		// 表格信息
		List<PdfPTable> tables = new ArrayList<>();
		List<ProjectModuleInfo> projectModuleInfos = null;
		if (pdfTemplateFile != null && StringUtils.isNotBlank(pdfTemplateFile.getProjectTemplateName())) {
			projectModuleInfos = export.getModuleList(Integer.parseInt(pdfTemplateFile.getProjectTemplateName()),"");
		}
		if (projectModuleInfos != null && projectModuleInfos.size() > 0) {
			for (int i = 0; i < projectModuleInfos.size(); i++) {
				// 读取导出模块-->一个模块代表一个表格
				ProjectModuleInfo projectModuleInfo = projectModuleInfos.get(i);
				// 读取模块的字段-->相当与表头
				List<ProjectModuleDetail> projectModuleDetails = projectModuleInfo.sortProjectModuleDetails();
				//判断展现的字段大于15，分表展现
				SystemConfig  systemConfig  =systemConfigService.findBySysKey(Constants.PDF_FIELD_NUM);
				int b = 25;
				if(systemConfig != null){
					
					b = Integer.parseInt(systemConfig.getSysValue());
				}
				if(projectModuleDetails.size()>b){
					
					int a = projectModuleDetails.size();
					
					int tableNum = a%b==0?a/b:a/b+1;
					
					for(int j=0;j<tableNum;j++){
						Map<String, String> headers = new LinkedHashMap<>();
						Map<String, List<String>> mergeHeaders = new LinkedHashMap<>();
						List<DataBean> lists = new ArrayList<>();
						int leng = (j+1)*b<projectModuleDetails.size()?(j+1)*b:projectModuleDetails.size();
						for (int k =j*b;k<leng;k++) {
							headers.put(projectModuleDetails.get(k).getKey(), projectModuleDetails.get(k).getHead());
							//如果有所属分组，则创建list
							if(StringUtils.isNotBlank(projectModuleDetails.get(k).getHeadgroup())){
								List<String> heardslists = mergeHeaders.get(projectModuleDetails.get(k).getHeadgroup());
								
								if( heardslists != null){
									heardslists.add(projectModuleDetails.get(k).getHead());
									mergeHeaders.put(projectModuleDetails.get(k).getHeadgroup(), heardslists);
								}else{
									heardslists = new ArrayList<>();
									heardslists.add(projectModuleDetails.get(k).getHead());
									mergeHeaders.put(projectModuleDetails.get(k).getHeadgroup(), heardslists);
								}
							}else{
								mergeHeaders.put(projectModuleDetails.get(k).getHead(), null);
							}
						}
						// 根据项目和企业获取表格对应bean。--->表格填充数据
						projectModuleInfo.setProjectId(projectid);
						ExcelWriteInterface excelWriteInterface = export.getExcelWriteInterface(projectModuleInfo);
						if (excelWriteInterface != null) {
							List<DataBean> databeans = excelWriteInterface.readData(projectModuleInfo, enterprise);
							for (DataBean dataBean : databeans) {
								lists.add(dataBean);
							}
						}
						tables.add(pdfTT.setTable(projectModuleInfos.get(i).getName(), headers,mergeHeaders, lists));
					}
				}else{
					Map<String, String> headers = new LinkedHashMap<>();
					Map<String, List<String>> mergeHeaders = new LinkedHashMap<>();
					List<DataBean> lists = new ArrayList<>();
					for (ProjectModuleDetail projectModuleDetail:projectModuleDetails) {
						headers.put(projectModuleDetail.getKey(), projectModuleDetail.getHead());
						//如果有所属分组，则创建list
						if(StringUtils.isNotBlank(projectModuleDetail.getHeadgroup())){
							List<String> heardslists = mergeHeaders.get(projectModuleDetail.getHeadgroup());
							
							if( heardslists != null){
								heardslists.add(projectModuleDetail.getHead());
								mergeHeaders.put(projectModuleDetail.getHeadgroup(), heardslists);
							}else{
								heardslists = new ArrayList<>();
								heardslists.add(projectModuleDetail.getHead());
								mergeHeaders.put(projectModuleDetail.getHeadgroup(), heardslists);
							}
						}else{
							mergeHeaders.put(projectModuleDetail.getHead(), null);
						}
						
						
						
					}
					// 根据项目和企业获取表格对应bean。--->表格填充数据
					projectModuleInfo.setProjectId(projectid);
					ExcelWriteInterface excelWriteInterface = export.getExcelWriteInterface(projectModuleInfo);
					if (excelWriteInterface != null) {
						lists = excelWriteInterface.readData(projectModuleInfo, enterprise);
					}
					tables.add(pdfTT.setTable(projectModuleInfos.get(i).getName(), headers,mergeHeaders, lists));
				}
				
			}
		}

		return pdfTT.createTable(tables);
	}
	
	
	
	
	
	/**
	 * 
	 * @author 郑有权
	 * @date 创建时间：2018年8月9日 下午4:37:33 
	 * @param enterprise
	 * @param projectid
	 * @return
	 * @throws DataAccessException
	 */
	public Map<String, List<Object>> enterpriseDetail(Enterprise enterprise, Integer projectid)
			throws  DataAccessException {
		
		
		//返回的map数据。格式如下
		//企业基本信息:[{key: "企业id", value: "8567", orders: 1}, {key: "调查人", value: "", orders: 1},…]
		//原辅料信息:[[{key: "id", value: "104741", orders: 1}, {key: "溶剂类型", value: "", orders: 2},…],…]
		//排放口信息:[
		Map<String, List<Object>> fieldnames = new LinkedHashMap<>();
		List<Object> enterpriseDetails = new ArrayList<>();
		List<Object> enterpriseBaseDetails = new ArrayList<>();
		
		//获取项目类型
		ProjectEnterpriseUser projectEnterpriseUser = projectEnterpriseUserService.findProjectTypeByProjectIdAndEnterpriseId(projectid,
				enterprise.getId());
		Integer projectType = -1;
		if(projectEnterpriseUser != null){
			projectType = projectEnterpriseUser.getProjectType();
		}
		if(projectType == null || projectType ==-1  ){
			throw new DataAccessException("未配置项目类型,企业项目可能不匹配，请联系管理员");
		}
		
		//读取审核配置（与pdf配置共用一个数据库）
		PDFTemplateFile pdfTemplateFile = pdfTemplateService.findName(enterprise.getEnterpriseType().toString(),projectid,projectType,"审核");
		if (pdfTemplateFile != null) {
			if (StringUtils.isNotBlank(pdfTemplateFile.getProjectTemplateName())) {
				List<ProjectModuleInfo> projectModuleInfos = null;
				//读取配置中的企业信息
				projectModuleInfos = export.getModuleList(Integer.parseInt(pdfTemplateFile.getProjectTemplateName()),"enterprise");
				for (int i = 0; i < projectModuleInfos.size(); i++) {
					// 读取导出模块-->一个模块代表一个表格
					ProjectModuleInfo projectModuleInfo = projectModuleInfos.get(i);
					if("企业".equals(projectModuleInfo.getName())){
						
						// 读取模块的字段-->相当与表头
						List<ProjectModuleDetail> projectModuleDetails = projectModuleInfo.sortProjectModuleDetails();
						for (ProjectModuleDetail projectModuleDetail : projectModuleDetails) {
							enterpriseDetails.add(new EnterpriseDetail(projectModuleDetail.getHead(), Constants.convert(projectModuleDetail.getHead(),BeanUtils.getPropertyByName(enterprise, projectModuleDetail.getKey())), projectModuleDetail.getModuleOrder()));
						}
						fieldnames.put("企业信息", enterpriseDetails);
					}else{
						// 读取模块的字段-->相当与表头
						List<ProjectModuleDetail> projectModuleDetails = projectModuleInfo.sortProjectModuleDetails();
						
						List<DataBean> lists = new ArrayList<>();
						// 根据项目和企业获取表格对应bean。--->表格填充数据
						projectModuleInfo.setProjectId(projectid);
						ExcelWriteInterface excelWriteInterface = export.getExcelWriteInterface(projectModuleInfo);
						if (excelWriteInterface != null) {
							lists = excelWriteInterface.readData(projectModuleInfo, enterprise);
						}
						for(DataBean databean:lists){
							for (ProjectModuleDetail projectModuleDetail:projectModuleDetails) {
								enterpriseBaseDetails.add(
									new EnterpriseDetail(
										projectModuleDetail.getHead(),
										Constants.convert(projectModuleDetail.getHead(),BeanUtils.getPropertyByName(databean, projectModuleDetail.getKey())), 
										projectModuleDetail.getModuleOrder()
									)
								);
							}
						}
						
					}
					
				}
				
				//加载基本信息。
				DataBean dataBean = findDataBeanByEnterprise(enterprise, projectid);
				projectModuleInfos = export.getModuleList(Integer.parseInt(pdfTemplateFile.getProjectTemplateName()),"base");
				for (int i = 0; i < projectModuleInfos.size(); i++) {
					// 读取导出模块-->一个模块代表一个表格
					ProjectModuleInfo projectModuleInfo = projectModuleInfos.get(i);
					// 读取模块的字段-->相当与表头
					List<ProjectModuleDetail> projectModuleDetails = projectModuleInfo.sortProjectModuleDetails();
					for (ProjectModuleDetail projectModuleDetail : projectModuleDetails) {
						enterpriseBaseDetails.add(new EnterpriseDetail(projectModuleDetail.getHead(), Constants.convert(projectModuleDetail.getHead(),BeanUtils.getPropertyByName(dataBean,projectModuleDetail.getKey())), projectModuleDetail.getModuleOrder()));
					}
				}
			}
		}else{
			//未配置pdf导出模块
			throw new DataAccessException("未配置审核模块，请联系管理员");
		}
		fieldnames.put("基本信息", enterpriseBaseDetails);
		return enterpriseTable(fieldnames, enterprise, projectid, pdfTemplateFile);
	}
	
	
	
	public Map<String, List<Object>> enterpriseTable(Map<String, List<Object>> fieldnames, Enterprise enterprise, Integer projectid,
			PDFTemplateFile pdfTemplateFile) {
		// 表格信息
		List<ProjectModuleInfo> projectModuleInfos = null;
		if (pdfTemplateFile != null && StringUtils.isNotBlank(pdfTemplateFile.getProjectTemplateName())) {
			projectModuleInfos = export.getModuleList(Integer.parseInt(pdfTemplateFile.getProjectTemplateName()),"table");
		}
		if (projectModuleInfos != null && projectModuleInfos.size() > 0) {
			for (int i = 0; i < projectModuleInfos.size(); i++) {
				// 读取导出模块-->一个模块代表一个表格
				ProjectModuleInfo projectModuleInfo = projectModuleInfos.get(i);
				// 读取模块的字段-->相当与表头
				List<ProjectModuleDetail> projectModuleDetails = projectModuleInfo.sortProjectModuleDetails();
				
				List<DataBean> lists = new ArrayList<>();
				// 根据项目和企业获取表格对应bean。--->表格填充数据
				projectModuleInfo.setProjectId(projectid);
				ExcelWriteInterface excelWriteInterface = export.getExcelWriteInterface(projectModuleInfo);
				if (excelWriteInterface != null) {
					lists = excelWriteInterface.readData(projectModuleInfo, enterprise);
				}
				fieldnames.put(projectModuleInfos.get(i).getName(), tableDetatil(projectModuleDetails, lists)); 
				
				
			}
		}

		return fieldnames;
	}
	
	public List<Object> tableDetatil(List<ProjectModuleDetail> projectModuleDetails, List<DataBean> lists){
		List<Object> listrow = new ArrayList<>();
		
		for(DataBean databean:lists){
			List<Object> enterpriseDetails = new ArrayList<>();
			for (ProjectModuleDetail projectModuleDetail:projectModuleDetails) {
				enterpriseDetails.add(new EnterpriseDetail(projectModuleDetail.getHead(), BeanUtils.getPropertyByName(databean, projectModuleDetail.getKey()), projectModuleDetail.getModuleOrder()));
			}
			listrow.add(enterpriseDetails);
		}
		
		return listrow;
	};
	
	
	
	public class EnterpriseDetail{
		private String key;
		private String value;
		private short orders;
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public short getOrders() {
			return orders;
		}
		public void setOrders(short orders) {
			this.orders = orders;
		}
		public EnterpriseDetail(String key, String value, short orders) {
			super();
			this.key = key;
			this.value = value;
			this.orders = orders;
		}
		
		
	}
	
	
	
	
	
	
	/**
	 * 
	 * @author 郑有权
	 * @date 创建时间：2018年8月9日 下午4:37:33 
	 * @param enterprise
	 * @param projectid
	 * @return
	 * @throws DataAccessException
	 */
	public Map<Object, List<Object>> showEnterpriseDetail(Enterprise enterprise, Integer projectid)
			throws  DataAccessException {
		
		
		//返回的map数据。格式如下
		//企业基本信息:[{key: "企业id", value: "8567", orders: 1}, {key: "调查人", value: "", orders: 1},…]
		//原辅料信息:[[{key: "id", value: "104741", orders: 1}, {key: "溶剂类型", value: "", orders: 2},…],…]
		//排放口信息:[
		Map<Object, List<Object>> fieldnames = new LinkedHashMap<>();
		List<Object> enterpriseDetails = new ArrayList<>();
		List<Object> enterpriseBaseDetails = new ArrayList<>();
		
		if(enterprise == null){
			throw new DataAccessException("企业未找到");
		}
		
		//如果项目类型为部门，则为部门项目
		Integer projectType = -1;
		Project project = projectService.find(projectid);
		if("部门".equals(project.getProjectType())){
			projectType = null;
		}else{
			//获取项目类型
			ProjectEnterpriseUser projectEnterpriseUser = projectEnterpriseUserService.findProjectTypeByProjectIdAndEnterpriseId(projectid,
					enterprise.getId());
			
			if(projectEnterpriseUser != null){
				projectType = projectEnterpriseUser.getProjectType();
			}
			if(projectType == null || projectType ==-1  ){
				throw new DataAccessException("未配置项目类型,企业项目可能不匹配，请联系管理员");
			}
		}
		
		
		
		//读取审核配置（与pdf配置共用一个数据库）
		PDFTemplateFile pdfTemplateFile = pdfTemplateService.findName(enterprise.getEnterpriseType().toString(),projectid,projectType,"审核");
		if (pdfTemplateFile != null) {
			if (StringUtils.isNotBlank(pdfTemplateFile.getProjectTemplateName())) {
				List<ProjectModuleInfo> projectModuleInfos = null;
				//读取配置中的企业信息
				projectModuleInfos = export.getModuleList(Integer.parseInt(pdfTemplateFile.getProjectTemplateName()),"enterprise");
				for (int i = 0; i < projectModuleInfos.size(); i++) {
					// 读取导出模块-->一个模块代表一个表格
					ProjectModuleInfo projectModuleInfo = projectModuleInfos.get(i);
					if("企业".equals(projectModuleInfo.getName())){
						
						// 读取模块的字段-->相当与表头
						List<ProjectModuleDetail> projectModuleDetails = projectModuleInfo.sortProjectModuleDetails();
						for (ProjectModuleDetail projectModuleDetail : projectModuleDetails) {
							enterpriseDetails.add(new EnterpriseDetail(projectModuleDetail.getHead(), Constants.convert(projectModuleDetail.getHead(),BeanUtils.getPropertyByName(enterprise, projectModuleDetail.getKey())), projectModuleDetail.getModuleOrder()));
						}
						fieldnames.put(new EnterpriseKey("企业信息",1).toString(), enterpriseDetails);
					}else{
						// 读取模块的字段-->相当与表头
						List<ProjectModuleDetail> projectModuleDetails = projectModuleInfo.sortProjectModuleDetails();
						
						List<DataBean> lists = new ArrayList<>();
						// 根据项目和企业获取表格对应bean。--->表格填充数据
						projectModuleInfo.setProjectId(projectid);
						ExcelWriteInterface excelWriteInterface = export.getExcelWriteInterface(projectModuleInfo);
						if (excelWriteInterface != null) {
							lists = excelWriteInterface.readData(projectModuleInfo, enterprise);
						}
						for(DataBean databean:lists){
							for (ProjectModuleDetail projectModuleDetail:projectModuleDetails) {
								enterpriseBaseDetails.add(
									new EnterpriseDetail(
										projectModuleDetail.getHead(),
										Constants.convert(projectModuleDetail.getHead(),BeanUtils.getPropertyByName(databean, projectModuleDetail.getKey())), 
										projectModuleDetail.getModuleOrder()
									)
								);
							}
						}
						
					}
					
				}
				
				//加载基本信息。
				DataBean dataBean = findDataBeanByEnterprise(enterprise, projectid);
				projectModuleInfos = export.getModuleList(Integer.parseInt(pdfTemplateFile.getProjectTemplateName()),"base");
				for (int i = 0; i < projectModuleInfos.size(); i++) {
					// 读取导出模块-->一个模块代表一个表格
					ProjectModuleInfo projectModuleInfo = projectModuleInfos.get(i);
					// 读取模块的字段-->相当与表头
					List<ProjectModuleDetail> projectModuleDetails = projectModuleInfo.sortProjectModuleDetails();
					for (ProjectModuleDetail projectModuleDetail : projectModuleDetails) {
						enterpriseBaseDetails.add(new EnterpriseDetail(projectModuleDetail.getHead(), Constants.convert(projectModuleDetail.getHead(),BeanUtils.getPropertyByName(dataBean,projectModuleDetail.getKey())), projectModuleDetail.getModuleOrder()));
					}
				}
			}
		}else{
			//未配置pdf导出模块
			throw new DataAccessException("审核项目配置模块未配置，请联系管理员");
		}
		if(enterpriseBaseDetails != null && enterpriseBaseDetails.size()>0){
			fieldnames.put(new EnterpriseKey("基本信息",2).toString(), enterpriseBaseDetails);
		}
		return showEnterpriseTable(fieldnames, enterprise, projectid, pdfTemplateFile);
	}
	
	
	
	public Map<Object, List<Object>> showEnterpriseTable(Map<Object, List<Object>> fieldnames, Enterprise enterprise, Integer projectid,
			PDFTemplateFile pdfTemplateFile) {
		// 表格信息
		List<ProjectModuleInfo> projectModuleInfos = null;
		if (pdfTemplateFile != null && StringUtils.isNotBlank(pdfTemplateFile.getProjectTemplateName())) {
			projectModuleInfos = export.getModuleList(Integer.parseInt(pdfTemplateFile.getProjectTemplateName()),"table");
		}
		if (projectModuleInfos != null && projectModuleInfos.size() > 0) {
			for (int i = 0; i < projectModuleInfos.size(); i++) {
				// 读取导出模块-->一个模块代表一个表格
				ProjectModuleInfo projectModuleInfo = projectModuleInfos.get(i);
				// 读取模块的字段-->相当与表头
				List<ProjectModuleDetail> projectModuleDetails = projectModuleInfo.sortProjectModuleDetails();
				
				List<DataBean> lists = new ArrayList<>();
				// 根据项目和企业获取表格对应bean。--->表格填充数据
				projectModuleInfo.setProjectId(projectid);
				ExcelWriteInterface excelWriteInterface = export.getExcelWriteInterface(projectModuleInfo);
				if (excelWriteInterface != null) {
					lists = excelWriteInterface.readData(projectModuleInfo, enterprise);
				}
				if(lists !=  null && lists.size()>0){
					fieldnames.put(new EnterpriseKey(projectModuleInfos.get(i).getName(),projectModuleInfos.get(i).getOrder()).toString(), tableDetatil(projectModuleDetails, lists)); 
				}
				
				
			}
		}

		return fieldnames;
	}
	
	
	
	public static class EnterpriseKey implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String key;
		private int orders;
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public int getOrders() {
			return orders;
		}
		public void setOrders(short orders) {
			this.orders = orders;
		}
		public EnterpriseKey(String key, int orders) {
			super();
			this.key = key;
			this.orders = orders;
		}
		@Override
		public String toString() {
			return key + "_" + orders;
		}
		
		
		
		
	}
	
	
	
}

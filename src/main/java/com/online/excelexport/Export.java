package com.online.excelexport;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.online.controller.ScheduleSingletonData;
import com.online.entity.DictionaryData;
import com.online.entity.EnterpriseDictionary;
import com.online.entity.SysProductIndustry;
import com.online.entity.SystemUser;
import com.online.entity.online.Enterprise;
import com.online.entity.online.ProjectModuleDetail;
import com.online.entity.online.ProjectModuleInfo;
import com.online.entity.online.ProjectTemplates;
import com.online.entity.online.TaskManager;
import com.online.excelexport.impl.BaseInfoWriterImpl;
import com.online.excelexport.impl.BeastsBirdsWriterImpl;
import com.online.excelexport.impl.BoilerWriterImpl;
import com.online.excelexport.impl.BreakdownServiceBaseWriterImpl;
import com.online.excelexport.impl.BreakdownServiceSolventWriterImpl;
import com.online.excelexport.impl.CGJCanYinWriterImpl;
import com.online.excelexport.impl.CGJShaoKaoDianWriterImpl;
import com.online.excelexport.impl.CateringBaseWriterImpl;
import com.online.excelexport.impl.CateringFuelWriterImpl;
import com.online.excelexport.impl.ConstructionConsumptionWriterImpl;
import com.online.excelexport.impl.ConstructionMemberWriterImpl;
import com.online.excelexport.impl.ConstructionPeriodWriterImpl;
import com.online.excelexport.impl.ConstructionSiteBaseWriterImpl;
import com.online.excelexport.impl.ConstructionYardWriterImpl;
import com.online.excelexport.impl.DryClearBaseWriterImpl;
import com.online.excelexport.impl.DryClearSolventWriterImpl;
import com.online.excelexport.impl.EquipmentLeakedWriterImpl;
import com.online.excelexport.impl.ExhaustionHoleWriterImpl;
import com.online.excelexport.impl.GDJFaDianMeiYueWriterImpl;
import com.online.excelexport.impl.GDJQiYeYongDianWriterImpl;
import com.online.excelexport.impl.GSJCanYinWriterImpl;
import com.online.excelexport.impl.GSJGanxiWriterImpl;
import com.online.excelexport.impl.GSJQiXiuWriterImpl;
import com.online.excelexport.impl.GasStoveWriterImpl;
import com.online.excelexport.impl.GovernanceWriterImpl;
import com.online.excelexport.impl.HSJGangKouJiXieWriterImpl;
import com.online.excelexport.impl.HSJGangKouWriterImpl;
import com.online.excelexport.impl.HSJKeHuoChuanPoMeiYueWriterImpl;
import com.online.excelexport.impl.HSJKeHuoChuanPoWriterImpl;
import com.online.excelexport.impl.JTJKeYunZhanWriterImpl;
import com.online.excelexport.impl.JXWGongYeWriterImpl;
import com.online.excelexport.impl.KilnWriterImpl;
import com.online.excelexport.impl.LoadingWriterImpl;
import com.online.excelexport.impl.MHJJiChangCheLiangWriterImpl;
import com.online.excelexport.impl.MHJQiLuoWriterImpl;
import com.online.excelexport.impl.MonthlyWriterImpl;
import com.online.excelexport.impl.MotorVehiclesWriterImpl;
import com.online.excelexport.impl.NYWXuQinYangZhiWriterImpl;
import com.online.excelexport.impl.NonRoadWriterImpl;
import com.online.excelexport.impl.OilTankWriterImpl;
import com.online.excelexport.impl.OpenYardWriterImpl;
import com.online.excelexport.impl.PetrolSalesWriterImpl;
import com.online.excelexport.impl.PetrolStationBaseWriterImpl;
import com.online.excelexport.impl.PetrolStorageTypeWriterImpl;
import com.online.excelexport.impl.PowerPlantWriterImpl;
import com.online.excelexport.impl.ProjectEnterpriseWriterImpl;
import com.online.excelexport.impl.RQGSGongYeXiaoHaoMeiYueWriterImpl;
import com.online.excelexport.impl.RQGSGongYeXiaoHaoWriterImpl;
import com.online.excelexport.impl.RawMaterialsWriterImpl;
import com.online.excelexport.impl.SWJChuYouKuWriterImpl;
import com.online.excelexport.impl.SWJJiaYouZhanWriterImpl;
import com.online.excelexport.impl.SYJJCanYinWriterImpl;
import com.online.excelexport.impl.SectionWriterImpl;
import com.online.excelexport.impl.SewageDisposeWriterImpl;
import com.online.excelexport.impl.SimpleBaseInfoWriterImpl;
import com.online.excelexport.impl.SingleBoilerFuelWriterImpl;
import com.online.excelexport.impl.SingleBoilerInformationWriterImpl;
import com.online.excelexport.impl.SteelWriterImpl;
import com.online.excelexport.impl.SurveyCarWriterImpl;
import com.online.excelexport.impl.TransportCarWriterImpl;
import com.online.excelexport.impl.ZJJGuoLuWriterImpl;
import com.online.excelexport.impl.ZJWCaiLiaoZhanWriterImpl;
import com.online.excelexport.impl.ZJWFangWuJianZhuGongDiWriterImpl;
import com.online.excelexport.impl.ZJWJiaoBanZhanWriterImpl;
import com.online.excelexport.impl.ZJWShiZhengJianZhuGongDiWriterImpl;
import com.online.service.DictionaryService;
import com.online.service.SysProductIndustryService;
import com.online.util.Constants;

/**
 * 导出excel
 * 
 * @author DEV2
 *
 */
@Component
public class Export {

	@PersistenceContext
	protected EntityManager entityManager;

	private static XSSFWorkbook wb;
	
	public String [] enterpriseDictionaryHeader = {"已录入企业id","标记项","企业名称","区县","详细地址","行业"
			,"组织机构代码/社会统一信用码","联系人","联系电话","法人"};
	
	public String [] taskManagerHeader = {"企业名称","行业","联系人","联系电话","区县","调查人","详细地址","组织机构代码","法人","企业状态","任务状态"};

	
	public static void exportTemplate(HttpServletResponse response,String fileName,String[] strs) {
		
		//web浏览通过MIME类型判断文件是excel类型
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        String systemtime = df.format(System.currentTimeMillis());   
        
        // 对文件名进行处理。防止文件名乱码
        String filename;
		try {
			filename = new String((fileName).getBytes("GB2312"),"iso8859-1") +"_"+systemtime+".xlsx";
			response.setHeader("Content-disposition", "attachment;filename=" + filename);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        
		
        try {
        	ServletOutputStream serOutStream = response.getOutputStream();
			exportTemplate(serOutStream,strs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public static void exportTemplate(OutputStream os,String[] strs) {
		wb = new XSSFWorkbook();

		XSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 12); // 设置字体的大小
		font.setFontName("微软雅黑"); // 设置字体的样式，如：宋体、微软雅黑等
		font.setItalic(false); // 斜体true为斜体
		XSSFCellStyle style = wb.createCellStyle();
		style.setFont(font);

		Sheet sheet = wb.createSheet();

		// 第一行文字说明
		Row row = sheet.createRow(0);
		
		for(int i = 0;i<strs.length;i++){
			Cell cell = row.createCell(i, CellType.STRING);
			cell.setCellStyle(style);
			cell.setCellValue(strs[i]);
		}
		try {

			wb.write(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//导出任务列表
	public void exportTaskManager(OutputStream os,List<TaskManager> lists) {
		wb = new XSSFWorkbook();
		
		XSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 12); // 设置字体的大小
		font.setFontName("微软雅黑"); // 设置字体的样式，如：宋体、微软雅黑等
		font.setItalic(false); // 斜体true为斜体
		XSSFCellStyle style = wb.createCellStyle();
		style.setFont(font);
		
		Sheet sheet = wb.createSheet();
		
		// 第一行文字说明
		Row row = sheet.createRow(0);
		for(int i = 0;i<taskManagerHeader.length;i++){
			Cell cell = row.createCell(i, CellType.STRING);
			cell.setCellStyle(style);
			cell.setCellValue(taskManagerHeader[i]);
		}
		
		if(lists != null){
			for(int i = 0;i<lists.size();i++){
				Row row1 = sheet.createRow(i+1);
				for(int y = 0;y<taskManagerHeader.length;y++){
					Cell cell1 = row1.createCell(y, CellType.STRING);
					cell1.setCellStyle(style);
					cell1.setCellValue(getTaskManagerContent(lists.get(i),taskManagerHeader[y]));
				}
				
			}
		}
		
		
		try {
			
			wb.write(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//导出名录库
	public void exportTemplate(OutputStream os,List<EnterpriseDictionary> lists) {
		wb = new XSSFWorkbook();
		
		XSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 12); // 设置字体的大小
		font.setFontName("微软雅黑"); // 设置字体的样式，如：宋体、微软雅黑等
		font.setItalic(false); // 斜体true为斜体
		XSSFCellStyle style = wb.createCellStyle();
		style.setFont(font);
		
		Sheet sheet = wb.createSheet();
		
		// 第一行文字说明
		Row row = sheet.createRow(0);
		for(int i = 0;i<enterpriseDictionaryHeader.length;i++){
			Cell cell = row.createCell(i, CellType.STRING);
			cell.setCellStyle(style);
			cell.setCellValue(enterpriseDictionaryHeader[i]);
		}
		
		if(lists != null){
			for(int i = 0;i<lists.size();i++){
				Row row1 = sheet.createRow(i+1);
				for(int y = 0;y<enterpriseDictionaryHeader.length;y++){
					Cell cell1 = row1.createCell(y, CellType.STRING);
					cell1.setCellStyle(style);
					cell1.setCellValue(getEnterpriseDictionaryContent(lists.get(i),enterpriseDictionaryHeader[y]));
				}
				
			}
		}
		
		
		try {
			
			wb.write(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getString(Object str){
		if(str == null){
			return "";
		}else{
			switch (str+"") {
			
			case "T_01":
				return "正常生产";
			case "T_02":
				return "半停产";
			case "T_03":
				return "停产";
			case "T_04":
				return "关停/取缔";
			case "D_05":
				return "已搬迁";
			case "P_06":
				return "企业实体不存在";
			case "P_07":
				return "仅销售/门店/办公";
			case "D_08":
				return "重复";
			case "D_09":
				return "已上报";
			case "T_05":
				return "新建未投产";
			case "new":
				return "新任务";
			case "assigned":
				return "已分配";
			case "complete":
				return "完成";
			case "processed":
				return "已处理";
			

			default:
				return str+"";
			}
		}
	}
	
	public String getTaskManagerContent(TaskManager taskManager,String header){
		
		switch (header) {
		
		case "企业名称":
			return getString(taskManager.getEnterpriseName());
		case "行业":
			return getString(taskManager.getInputIndustry());
		case "联系人":
			return getString(taskManager.getContactPerson());
		case "联系电话":
			return getString(taskManager.getContactPhone());
		case "区县":
			return getString(taskManager.getCountyName());
		case "调查人":
			return getString(taskManager.getInquirer());
		case "详细地址":
			return getString(taskManager.getDetailAddress());
		case "组织机构代码":
			return getString(taskManager.getCode());
		case "法人":
			return getString(taskManager.getCorporation());
		case "企业状态":
			return getString(taskManager.getEnterpriseStatus());
		case "任务状态":
			return getString(taskManager.getStatus());

		default:
			return "";
		}
	}
	public String getEnterpriseDictionaryContent(EnterpriseDictionary enterpriseDictionary,String header){
		
		switch (header) {
		case "已录入企业id":
			return getString(enterpriseDictionary.getAlreadyEnterpriseId());
		case "标记项":
			return getString(enterpriseDictionary.getSign());
		case "企业名称":
			return getString(enterpriseDictionary.getEnterpriseName());
		case "区县":
			return getString(enterpriseDictionary.getCountyName());
		case "详细地址":
			return getString(enterpriseDictionary.getDetailAddress());
		case "行业":
			return getString(enterpriseDictionary.getInputIndustry());
		case "组织机构代码/社会统一信用码":
			return getString(enterpriseDictionary.getCode());
		case "联系人":
			return getString(enterpriseDictionary.getContacts());
		case "联系电话":
			return getString(enterpriseDictionary.getContactsPhone());
		case "法人":
			return getString(enterpriseDictionary.getCorporation());
			
		default:
			return "";
		}
	}
	

	public void exportUser(OutputStream os, List<SystemUser> lists) {
		wb = new XSSFWorkbook();

		XSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 12); // 设置字体的大小
		font.setFontName("微软雅黑"); // 设置字体的样式，如：宋体、微软雅黑等
		font.setItalic(false); // 斜体true为斜体
		XSSFCellStyle style = wb.createCellStyle();
		style.setFont(font);

		Sheet sheet = wb.createSheet();

		// 第一行文字说明
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0, CellType.STRING);
		cell.setCellStyle(style);
		cell.setCellValue("用户名");
		cell = row.createCell(1, CellType.STRING);
		cell.setCellStyle(style);
		cell.setCellValue("密码");

		for (int i = 0; i < lists.size(); i++) {
			Row row1 = sheet.createRow(i + 1);
			Cell cell1 = row1.createCell(0, CellType.STRING);
			cell1.setCellStyle(style);
			cell1.setCellValue(lists.get(i).getUsername());
			cell1 = row1.createCell(1, CellType.STRING);
			cell1.setCellStyle(style);
			cell1.setCellValue(lists.get(i).getPasswordVisible());

		}

		try {

			wb.write(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 导出
	 * 
	 * @param projectId
	 * @throws IOException
	 */
	@Transactional(readOnly = true)
	public void export2ExcelByPorjectId(ProjectTemplates projectTemplates, OutputStream outputStream,
			String scheduleKey) throws IOException {
		loadDictionary();
		ScheduleSingletonData.createTask(scheduleKey);// 创建任务进度信息
		Integer projectId = projectTemplates.getProjectId();
		String templateType = projectTemplates.getTemplateType();
		wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("在线填报导出");
		List<ProjectModuleInfo> projectModuleInfos = getModuleList(projectTemplates.getId(),"");
		ProjectModuleInfo enterPrise = projectModuleInfos.get(0);// 第一项目模块必须是企业信息。
		if (!enterPrise.getModuleType().equals(ExcelExprotConstants.MODULE_TYPE_ENTERPRISE)) {
			throw new RuntimeException("第一模块必须为企业信息！");
		}
		writTitle(projectModuleInfos, sheet);
		List<Enterprise> enterpriseDataList = getEnterpriseDataList(enterPrise, projectId, templateType);// 获得企业基本信息
		if (enterpriseDataList == null || enterpriseDataList.size() == 0) {
			ScheduleSingletonData.updateDegree(scheduleKey, 100);
			wb.write(outputStream);
			return;
		}
		int taskTotal = enterpriseDataList.size();

		int startCol = 0; //
		int startRow = 2;
		float degree = 0;
		int maxRow = startRow;
		for (Enterprise enterprise : enterpriseDataList) {
			try {
				int endCol = writeEnterprise(enterPrise, enterprise, startCol, startRow, sheet);
				for (int i = 1; i < projectModuleInfos.size(); i++) {// 加载项目模块
					ModuleWirterBean moduleWirterBean = new ModuleWirterBean(endCol, startRow);
					ProjectModuleInfo projectModuleInfo = projectModuleInfos.get(i);
					ExcelWriteInterface excelWriteInterface = getExcelWriteInterface(projectModuleInfo);// 获得写入接口
					ModuleWriteReturnBean moduleWriteReturnBean = excelWriteInterface.writeExcel(projectModuleInfo,
							moduleWirterBean, sheet, enterprise);
					if (maxRow < moduleWriteReturnBean.getEndRow())
						maxRow = moduleWriteReturnBean.getEndRow();
					endCol = moduleWriteReturnBean.getEndCol();
				}
				startRow = maxRow + 1;
				degree++;
				ScheduleSingletonData.updateDegree(scheduleKey, (degree * 100) / taskTotal);
			} catch (Exception e) {
				ScheduleSingletonData.error(scheduleKey, e);
			}

		}
		// 写文件
		wb.write(outputStream);

	}

	public void writTitle(List<ProjectModuleInfo> projectModuleInfos, Sheet sheet) {
		int rowNumber = 0, colNumber = 0;

		for (ProjectModuleInfo projectModuleInfo : projectModuleInfos) {
			List<ProjectModuleDetail> projectModuleDetails = projectModuleInfo.sortProjectModuleDetails();
			ExcelWriteUtils.writeValue2Excel(projectModuleInfo.getName(), sheet, rowNumber, colNumber);
			for (ProjectModuleDetail projectModuleDetail : projectModuleDetails) {
				ExcelWriteUtils.writeValue2Excel(projectModuleDetail.getHead(), sheet, rowNumber + 1, colNumber++);
			}

		}
	}

	@Autowired
	private BoilerWriterImpl boilerWriterImpl;
	@Autowired
	private KilnWriterImpl kilnWriterImpl;
	@Autowired
	private EquipmentLeakedWriterImpl equipmentLeakedWriterImpl;
	@Autowired
	private SectionWriterImpl sectionWriterImpl;
	@Autowired
	private OpenYardWriterImpl openYardWriterImpl;
	@Autowired
	private OilTankWriterImpl oilTankWriterImpl;
	@Autowired
	private PowerPlantWriterImpl powerPlantWriterImpl;
	@Autowired
	private LoadingWriterImpl loadingWriterImpl;
	@Autowired
	private BeastsBirdsWriterImpl beastsBirdsWriterImpl;
	@Autowired
	private BreakdownServiceBaseWriterImpl breakdownServiceBaseWriterImpl;
	@Autowired
	private BreakdownServiceSolventWriterImpl breakdownServiceSolventWriterImpl;
	@Autowired
	private ConstructionConsumptionWriterImpl constructionConsumptionWriterImpl;
	@Autowired
	private ConstructionMemberWriterImpl constructionMemberWriterImpl;
	@Autowired
	private ConstructionPeriodWriterImpl constructionPeriodWriterImpl;
	@Autowired
	private ConstructionSiteBaseWriterImpl constructionSiteBaseWriterImpl;
	@Autowired
	private ConstructionYardWriterImpl constructionYardWriterImpl;
	@Autowired
	private DryClearBaseWriterImpl dryClearBaseWriterImpl;
	@Autowired
	private DryClearSolventWriterImpl dryClearSolventWriterImpl;
	@Autowired
	private MotorVehiclesWriterImpl motorVehiclesWriterImpl;
	@Autowired
	private PetrolSalesWriterImpl petrolSalesWriterImpl;
	@Autowired
	private PetrolStationBaseWriterImpl petrolStationBaseWriterImpl;
	@Autowired
	private PetrolStorageTypeWriterImpl petrolStorageTypeWriterImpl;
	@Autowired
	private SurveyCarWriterImpl surveyCarWriterImpl;
	@Autowired
	private BaseInfoWriterImpl baseInfoWriterImpl;
	@Autowired
	private SimpleBaseInfoWriterImpl simpleBaseInfoWriterImpl;
	@Autowired
	private RawMaterialsWriterImpl rawMaterialsWriterImpl;
	@Autowired
	private MonthlyWriterImpl monthlyWriterImpl;
	@Autowired
	private CateringBaseWriterImpl cateringBaseWriterImpl;
	@Autowired
	private CateringFuelWriterImpl cateringFuelWriterImpl;
	@Autowired
	private TransportCarWriterImpl transportCarWriterImpl;
	@Autowired
	private NonRoadWriterImpl nonRoadWriterImpl;
	@Autowired
	private SingleBoilerInformationWriterImpl singleBoilerInformationWriterImpl;
	@Autowired
	private SingleBoilerFuelWriterImpl singleBoilerFuelWriterImpl;
	@Autowired
	private SteelWriterImpl steelWriterImpl;
	@Autowired
	private GasStoveWriterImpl gasStoveWriterImpl;
	@Autowired
	private SewageDisposeWriterImpl sewageDisposeWriterImpl;
	@Autowired
	private GovernanceWriterImpl governanceWriterImpl;
	@Autowired
	private ExhaustionHoleWriterImpl exhaustionHoleWriterImpl;
	@Autowired
	private ProjectEnterpriseWriterImpl projectenterpriseWriterImpl;
	@Autowired
	private ZJWFangWuJianZhuGongDiWriterImpl zJWFangWuJianZhuGongDiWriterImpl;
	@Autowired
	private ZJWJiaoBanZhanWriterImpl zJWJiaoBanZhanWriterImpl;
	@Autowired
	private ZJWCaiLiaoZhanWriterImpl zJWCaiLiaoZhanWriterImpl;
	@Autowired
	private ZJWShiZhengJianZhuGongDiWriterImpl zJWShiZhengJianZhuGongDiWriterImpl;
	@Autowired
	private CGJCanYinWriterImpl cGJCanYinWriterImpl;
	@Autowired
	private CGJShaoKaoDianWriterImpl cGJShaoKaoDianWriterImpl;
	@Autowired
	private JTJKeYunZhanWriterImpl jTJKeYunZhanWriterImpl;
	@Autowired
	private SYJJCanYinWriterImpl sYJJCanYinWriterImpl;
	@Autowired
	private SWJChuYouKuWriterImpl sWJChuYouKuWriterImpl;
	@Autowired
	private SWJJiaYouZhanWriterImpl sWJJiaYouZhanWriterImpl;
	@Autowired
	private NYWXuQinYangZhiWriterImpl nYWXuQinYangZhiWriterImpl;
	@Autowired
	private HSJGangKouWriterImpl hSJGangKouWriterImpl;
	@Autowired
	private HSJKeHuoChuanPoWriterImpl hSJKeHuoChuanPoWriterImpl;
	@Autowired
	private HSJKeHuoChuanPoMeiYueWriterImpl hSJKeHuoChuanPoMeiYueWriterImpl;
	@Autowired
	private HSJGangKouJiXieWriterImpl hSJGangKouJiXieWriterImpl;
	@Autowired
	private GSJCanYinWriterImpl gSJCanYinWriterImpl;
	@Autowired
	private GSJQiXiuWriterImpl gSJQiXiuWriterImpl;
	@Autowired
	private GSJGanxiWriterImpl gSJGanxiWriterImpl;
	@Autowired
	private JXWGongYeWriterImpl jXWGongYeWriterImpl;
	@Autowired
	private GDJQiYeYongDianWriterImpl gDJQiYeYongDianWriterImpl;
	@Autowired
	private GDJFaDianMeiYueWriterImpl gDJFaDianMeiYueWriterImpl;
	@Autowired
	private MHJQiLuoWriterImpl mHJQiLuoWriterImpl;
	@Autowired
	private MHJJiChangCheLiangWriterImpl mHJJiChangCheLiangWriterImpl;
	@Autowired
	private ZJJGuoLuWriterImpl zJJGuoLuWriterImpl;
	@Autowired
	private RQGSGongYeXiaoHaoWriterImpl rQGSGongYeXiaoHaoWriterImpl;
	@Autowired
	private RQGSGongYeXiaoHaoMeiYueWriterImpl rQGSGongYeXiaoHaoMeiYueWriterImpl;
	
	
	
	/**
	 * 获取模块写接口
	 * 
	 * @param projectModuleInfo
	 * @return
	 */
	public ExcelWriteInterface getExcelWriteInterface(ProjectModuleInfo projectModuleInfo) {
		String moduleType = projectModuleInfo.getModuleType();
		switch (moduleType) {
		case ExcelExprotConstants.MODULE_TYPE_BOILIER:
			return boilerWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_KILN:
			return kilnWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_EQUIPMENTLEAKED:
			return equipmentLeakedWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_SECTION:
			return sectionWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_LOADING:
			return loadingWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_POWERPLANT:
			return powerPlantWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_OILTANK:
			return oilTankWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_OPENYARD:
			return openYardWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_BEASTSBIRDS:
			return beastsBirdsWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_BREAKDOWNSERVICEBASE:
			return breakdownServiceBaseWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_BREAKDOWNSERVICESOLVENT:
			return breakdownServiceSolventWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_CONSTRUCTIONCONSUMPTION:
			return constructionConsumptionWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_CONSTRUCTIONMEMBER:
			return constructionMemberWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_CONSTRUCTIONPERIOD:
			return constructionPeriodWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_CONSTRUCTIONSITEBASE:
			return constructionSiteBaseWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_CONSTRUCTIONYARD:
			return constructionYardWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_DRYCLEARBASE:
			return dryClearBaseWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_DRYCLEARSOLVENT:
			return dryClearSolventWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_MOTORVEHICLES:
			return motorVehiclesWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_SURVEYCAR:
			return surveyCarWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_PETROLSALES:
			return petrolSalesWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_PETROLSTATIONBASE:
			return petrolStationBaseWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_PETROLSTORAGETYPE:
			return petrolStorageTypeWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_BASE:
			return baseInfoWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_SIMPLEBASE:
			return simpleBaseInfoWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_RAWMATERIALS:
			return rawMaterialsWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_MONTHLY:
			return monthlyWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_CATERINGBASE:
			return cateringBaseWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_CATERINGFUEL:
			return cateringFuelWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_TRANSPORTCAR:
			return transportCarWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_NONROAD:
			return nonRoadWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_SingleBoilerInformation:
			return singleBoilerInformationWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_SingleBoilerFuel:
			return singleBoilerFuelWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_Steel:
			return steelWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_GasStove:
			return gasStoveWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_SewageDispose:
			return sewageDisposeWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_GOVERNANCE:
			return governanceWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_EXHAUSTIONHOLE:
			return exhaustionHoleWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_PROJECTENTERPRISE:
			return projectenterpriseWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_ZJWFANGWUJIANZHUGONGDI:
			return zJWFangWuJianZhuGongDiWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_ZJWJIAOBANZHAN:
			return zJWJiaoBanZhanWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_ZJWCAILIAOZHAN:
			return zJWCaiLiaoZhanWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_ZJWSHIZHENGJIANZHUGONGDI:
			return zJWShiZhengJianZhuGongDiWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_CGJCANYIN:
			return cGJCanYinWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_CGJSHAOKAODIAN:
			return cGJShaoKaoDianWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_JTJKEYUNZHAN:
			return jTJKeYunZhanWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_SYJJCANYIN:
			return sYJJCanYinWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_SWJCHUYOUKU:
			return sWJChuYouKuWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_SWJJIAYOUZHAN:
			return sWJJiaYouZhanWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_NYWXUQINYANGZHI:
			return nYWXuQinYangZhiWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_HSJGANGKOU:
			return hSJGangKouWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_HSJKEHUOCHUANPO:
			return hSJKeHuoChuanPoWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_HSJKEHUOCHUANPOMEIYUE:
			return hSJKeHuoChuanPoMeiYueWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_HSJGANGKOUJIXIE:
			return hSJGangKouJiXieWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_GSJCANYIN:
			return gSJCanYinWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_GSJQIXIU:
			return gSJQiXiuWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_GSJGANXI:
			return gSJGanxiWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_JXWGONGYE:
			return jXWGongYeWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_GDJQIYEYONGDIAN:
			return gDJQiYeYongDianWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_GDJFADIANMEIYUE:
			return gDJFaDianMeiYueWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_MHJQILUO:
			return mHJQiLuoWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_MHJJICHANGCHELIANG:
			return mHJJiChangCheLiangWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_ZJJGUOLU:
			return zJJGuoLuWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_RQGSGONGYEXIAOHAO:
			return rQGSGongYeXiaoHaoWriterImpl;
		case ExcelExprotConstants.MODULE_TYPE_RQGSGONGYEXIAOHAOMEIYUE:
			return rQGSGongYeXiaoHaoMeiYueWriterImpl;
			
		default:
			return null;
		}
	}

	/**
	 * 获得项目模板列表
	 * 
	 * @return
	 */
	public List<ProjectModuleInfo> getModuleList(Integer projectTemplatesId,String moduleClassification) {
		
		StringBuffer jpql = new StringBuffer();
		jpql.append("select projectModuleInfo from ProjectModuleInfo projectModuleInfo where projectTemplates.id = :projectTemplatesId and status= 'normal'");
		if(StringUtils.isNotBlank(moduleClassification)){
			jpql.append(" and moduleClassification ='" + moduleClassification+"'");
		}
		jpql.append(" order by order");
		
		List<ProjectModuleInfo> resultList = entityManager.createQuery(jpql.toString(), ProjectModuleInfo.class)
				.setParameter("projectTemplatesId", projectTemplatesId).getResultList();
		return resultList;
	}

	/**
	 * 获得企业基本信息
	 * 
	 * @param projectModuleInfo
	 * @param project
	 * @return
	 */
	public List<Enterprise> getEnterpriseDataList(ProjectModuleInfo projectModuleInfo, Integer projectId,
			String templateType) {
//		String sql = "select enterprise from Enterprise enterprise join enterprise.projects project  where project.id = :projectId and enterprise.enterpriseType=:enterpriseType ORDER BY enterprise.createDate";
		String sql = "select enterprise from Enterprise enterprise,ProjectEnterpriseUser sysp  where sysp.enterprise = enterprise and   sysp.project.id = :projectId and enterprise.enterpriseType=:enterpriseType ORDER BY enterprise.createDate";
		
		return entityManager.createQuery(sql, Enterprise.class).setParameter("projectId", projectId)
				.setParameter("enterpriseType", Constants.getEnterpriseType(templateType)).getResultList();
	}
	
	
	

	public void loadDictionary() {// 加载字典
		List<DictionaryData> dictionaryDatas = dictionaryService.findByname("企业【企业类型】", "");
		map.put("企业【企业类型】", dictionaryDatas);
		List<SysProductIndustry> sysProductIndustrys =sysProductIndustryService.findAll();
		productIndustrymap = sysProductIndustrys.stream().collect(Collectors.toMap(SysProductIndustry::getIndustryValue, SysProductIndustry::getIndustryName));
		
	}

	public String translateValue(String value, String fieldName) {// 字符串转化
		if (StringUtils.isEmpty(value))
			return "";
		if ("enterpriseType".equals(fieldName)) {
			return getDictionShowValue(value, fieldName);
		} else if ("account".equals(fieldName)) {
			SystemUser systemUser = entityManager.find(SystemUser.class, Integer.parseInt(value));
			if (systemUser == null)
				return "";
			return systemUser.getUsername();
		}else if("productIndustry".equals(fieldName)){
			return productIndustrymap.get(value);
		}
		return value;
	}

	public String getDictionShowValue(String key, String fieldName) {// 查询字典
		String string = disctionary.get(fieldName);
		if (StringUtils.isEmpty(string))
			return key;

		List<DictionaryData> list = map.get(string);
		if (list == null) {
			return key;
		} else {
			for (DictionaryData dictionaryData : list) {
				if (dictionaryData.getCode().equals(key)) {
					return dictionaryData.getCodeValue();
				}
			}
		}
		return key;
	}

	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private SysProductIndustryService sysProductIndustryService;

	Map<String, List<DictionaryData>> map = new HashMap<>();
	Map<String, String> productIndustrymap = new HashMap<>();
	

	public static Map<String, String> disctionary = new HashMap<>();// 字段与字典的关系

	static {
		disctionary.put("enterpriseType", "企业【企业类型】");
	}

	/**
	 * 
	 * @param projectModuleInfo
	 *            项目模块信息
	 * @param enterprise
	 *            企业信息
	 */
	private int writeEnterprise(ProjectModuleInfo projectModuleInfo, Enterprise enterprise, int startCol, int startRow,
			Sheet sheet) {
		int currentRow = startRow;
		int currentCol = startCol;
		List<ProjectModuleDetail> projectModuleDetails = projectModuleInfo.sortProjectModuleDetails();
		for (ProjectModuleDetail projectModuleDetail : projectModuleDetails) {
			ExcelWriteUtils.writeValue2Excel(
					translateValue(BeanUtils.getPropertyByName(enterprise, projectModuleDetail.getKey()),
							projectModuleDetail.getKey()),
					sheet, currentRow, currentCol++);
		}
		return currentCol;

	}

}

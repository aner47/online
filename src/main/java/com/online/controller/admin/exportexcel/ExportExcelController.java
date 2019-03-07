package com.online.controller.admin.exportexcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.online.ExportExcel;
import com.online.controller.base.BaseController;
import com.online.entity.SystemUser;
import com.online.entity.online.Project;
import com.online.service.EnterpriseService;
import com.online.service.ProjectService;
import com.online.service.SystemUserService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;

/** 
 * @author 作者名 
 * @email  邮箱名
 * @time   2017年4月26日 下午2:15:47 
 */
@Controller("exportexcelController")
@RequestMapping("/admin/exportexcel")
public class ExportExcelController extends BaseController {
	
	@Autowired
	protected EnterpriseService enterpriseService;

	@Autowired
	private SystemUserService systemUserService;
	
	@Autowired
	private ProjectService	projectService;
	
	@RequestMapping("/exportexcellist")
	public String list(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		return "/admin/exportexcel/exportexcelList";
	}
	
	
	
	/**
	 * 导出企业
	 */
	@RequestMapping(value = "/export")
	// @ResponseBody
	public void exportEnterprise(HttpServletRequest request, HttpServletResponse response,
			Project project,String polluteSource) {
		try {
			 request.setCharacterEncoding("utf-8");
			 response.setCharacterEncoding("utf-8");
			 response.setContentType("octets/stream");
			 String filename = "";
			 	//锅炉
				if(Constants.EXPORT_POLLUTESOURCE_BOILER.equals(polluteSource)){
					filename = new String(("工业锅炉燃烧").getBytes("GB2312"),"iso8859-1") +"_"+System.currentTimeMillis()+".xls";
				}else if(Constants.EXPORT_POLLUTESOURCE_OPENYARD.equals(polluteSource)){
					//露天
					filename = new String(("扬尘源").getBytes("GB2312"),"iso8859-1") +"_"+System.currentTimeMillis()+".xls";
				}else if(Constants.EXPORT_POLLUTESOURCE_OILTANK.equals(polluteSource)){
					//石油
					filename = new String(("储存运输源").getBytes("GB2312"),"iso8859-1") +"_"+System.currentTimeMillis()+".xls";
				}else if(Constants.EXPORT_POLLUTESOURCE_OTHER.equals(polluteSource)){
					//工段和炉窑
					filename = new String(("工业生产过程").getBytes("GB2312"),"iso8859-1") +"_"+System.currentTimeMillis()+".xls";
				}	
			 response.addHeader("Content-Disposition", "attachment;filename="+filename);

			 Project findproject = projectService.find(project.getId());
			 
			// 根据项目查询用户
			List<Integer> accounts = new ArrayList<>();
			List<SystemUser> systemUsers = systemUserService.queryProjectInvitationCode(findproject.getInvitationCode());
			if (systemUsers != null && systemUsers.size() > 0) {
				for (int i = 0; i < systemUsers.size(); i++) {
					accounts.add(systemUsers.get(i).getId());
				}

			}
			
			List<Map<String, Object>> exportEnterprises = enterpriseService.findALLEnterpriseByAccount(accounts,polluteSource);
			if(exportEnterprises != null	){
				System.out.println("导出企业==="+exportEnterprises.size());
				Map<Integer,List<String>> map = new HashMap<>();
		        //锅炉
				if(Constants.EXPORT_POLLUTESOURCE_BOILER.equals(polluteSource)){
					String[] oneheaders = {"","","","","","","",""
							,"","","","","企业地址","企业地址","企业地址","企业地址"
							,"企业地址","行业类别","行业类别","行业类别","行业类别","行业类别","行业类别","行业类别"
							,"","","","","","","",""
							,"排放源分类名称","排放源分类名称","排放源分类名称","排放源分类名称","排放源分类名称","排放源分类名称"
							,"排放源分类名称","排放源分类名称","排放源分类名称","排放源分类名称"
							,"排放量","排放量","排放量","排放量","排放量","排放量","排放量","排放量","排放量"
							,"排放口信息","排放口信息","排放口信息","排放口信息"
							,"排放口信息","排放口信息","排放口信息","排放口信息","排放口信息"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							,"排放因子","排放因子","排放因子","排放因子","排放因子","排放因子","排放因子"
							,"排放因子","排放因子","排放因子","排放因子","排放因子","排放因子","排放因子"
							,"排放因子","排放因子","排放因子","排放因子","排放因子","排放因子","排放因子","排放因子","排放因子"
							,"排放因子","排放因子","排放因子","排放因子","排放因子","排放因子","排放因子","排放因子","排放因子"
							,"排放因子","排放因子","排放因子","排放因子","排放因子"
							,"数据来源","数据来源","数据来源","数据来源"
							,"燃料信息","燃料信息","燃料信息","燃料信息","燃料信息","燃料信息","燃料信息","燃料信息"
							,"燃料信息","燃料信息"
							,"控制措施1","控制措施1","控制措施1","控制措施1","控制措施1","控制措施1","控制措施1"
							,"控制措施1","控制措施1","控制措施1","控制措施1","控制措施1"
							,"控制措施2","控制措施2","控制措施2","控制措施2","控制措施2","控制措施2","控制措施2"
							,"控制措施2","控制措施2","控制措施2","控制措施2","控制措施2"
							,"控制措施3","控制措施3","控制措施3","控制措施3","控制措施3","控制措施3","控制措施3"
							,"控制措施3","控制措施3","控制措施3","控制措施3","控制措施3"
							,"最终估算使用去除率","最终估算使用去除率","最终估算使用去除率","最终估算使用去除率"
							,"最终估算使用去除率","最终估算使用去除率","最终估算使用去除率","最终估算使用去除率","最终估算使用去除率"
							,"计算参数","计算参数","计算参数","计算参数","计算参数","计算参数"
							};
					String[] headers = {"编号","性质","年份","名称","组织机构代码","生产总值","年用电量","年生产天数"
							,"日生产小时数","中心经度","中心纬度","地区代码"
							//企业地址
							,"省（自治区、直辖市）","城市","区县市","乡（镇）","详细地址 "
							//行业类别
							,"行业类别代码","大类","中类","小类","大类名称","中类名称","小类名称"
							//锅炉信息
							,"锅炉编号","锅炉状态","炉型","估算采用炉型","蒸吨数","年工作小时数","投运时间年","投运时间月"
							//排放源分类名称
							,"1级代码","1级名称","2级代码","2级名称","3级代码","3级名称","4级代码","4级名称","5级代码","自定义名称"
							//排放量
							,"SO2","NOx","CO","PM10","PM2.5","BC","OC","VOCs","NH3"
							//排放口信息
							,"排放高度","排放口直径","废气温度","废气流量","废气排放量","在线净烟SO2浓度"
							,"在线净烟Nox浓度","在线净烟TSP浓度","在线净烟VOCs浓度"
							//时间分配
							,"月分配数据来源","月分配数据类型","月分配数据单位","1月","2月","3月","4月","5月","6月","7月","8月","9月"
							,"10月","11月","12月","日分配数据来源","日分配数据类型","日分配数据单位","周一","周二","周三","周四","周五","周六"
							,"周日","小时分配数据来源","小时分配数据类型","小时分配数据单位"
							,"1h","2h","3h","4h","5h","6h","7h","8h","9h","10h","11h","12h","13h","14h","15h","16h"
							,"17h","18h","19h","20h","21h","22h","23h","24h"
							//排放因子
							,"计算用活动水平类型","SO2因子","SO2因子输出单位","SO2输入单位","SO2因子转换","NOx因子","NOx因子输出单位"
							,"NOx因子输入单位","NOx因子转换","CO因子","CO因子输出单位","CO因子输入单位","CO因子转换","PM10因子"
							,"PM10因子输出单位","PM10因子输入单位","PM10因子转换","PM2.5因子","PM2.5因子输出单位"
							,"PM2.5因子输入单位","PM2.5因子转换","BC因子","BC因子输出单位","BC因子输入单位"
							,"BC因子转换","OC因子","OC因子输出单位","OC因子输入单位","OC因子转换"
							,"VOCs因子","VOCs因子输出单位","VOCs因子输入单位","VOCs因子转换"
							,"NH3因子","NH3因子输出单位","NH3因子输入单位","NH3因子转换"
							//数据来源
							,"活动水平数据","排放因子","其他参数","计算方法"
							//燃料信息
							,"原始燃料名称","原始年消耗量","原始单位","单位转换系数","燃料标准名称","标准年消耗量","标准单位","含硫率"
							,"灰分","挥发分"
							//控制措施1
							,"控制措施1名称","年工作时间","废气收集率","SO2去除效率","NOx去除效率","CO去除效率","PM10去除效率"
							,"PM2.5去除效率","BC去除效率","OC去除效率","VOC去除效率","NH3去除效率"
							//控制措施2
							,"控制措施2名称","年工作时间","废气收集率","SO2去除效率","NOx去除效率","CO去除效率","PM10去除效率"
							,"PM2.5去除效率","BC去除效率","OC去除效率","VOC去除效率","NH3去除效率"
							//控制措施2
							,"控制措施3名称","年工作时间","废气收集率","SO2去除效率","NOx去除效率","CO去除效率","PM10去除效率"
							,"PM2.5去除效率","BC去除效率","OC去除效率","VOC去除效率","NH3去除效率"
							//最终估算使用去除率
							,"SO2去除效率","NOx去除效率","CO去除效率","PM10去除效率"
							,"PM2.5去除效率","BC去除效率","OC去除效率","VOC去除效率","NH3去除效率"
							//计算参数
							,"硫分进入底灰比例","灰分进入底灰比例","PM10占总颗粒比例","PM2.5占总颗粒比例","BC占PM2.5比例","OC占PM2.5比例"
					};
											
					String[] headerunits = {"单位","P/S","／","／","／","万元","万千瓦时","天"
							,"小时","度","度","／"
							//企业地址
							,"／","／","／","／","／ "
							//行业类别
							,"／","／","／","／","／","／","／"
							//锅炉信息
							,"／","／","／","／","t／h","h/年","年","月"
							//排放源分类名称
							,"／","／","／","／","／","／","／","／","／","／"
							//排放量
							,"吨","吨","吨","吨","吨","吨","吨","吨","吨"
							//排放口信息
							,"米","米","℃","标立方米/小时","万立方米","毫克/立方米","毫克/立方米","毫克/立方米","毫克/立方米"
							//时间分配
							,"／","／","／","／","／","／","／","／","／","／"
							,"／","／","／","／","／","／","／","／","／","／"
							,"／","／","／","／","／","／","／","／","／","／"
							,"／","／","／","／","／","／","／","／","／","／"
							,"／","／","／","／","／","／","／","／","／","／"
							,"／","／"
							//排放因子
							,"／","／","／","／","／","／","／","／","／","／"
							,"／","／","／","／","／","／","／","／","／","／"
							,"／","／","／","／","／","／","／","／","／","／"
							,"／","／","／","／","／","／","／"
							//数据来源
							,"／","／","／","／"
							//燃料信息
							,"／","／","／","／","／","／","／","%","%","%"
							//控制措施1
							,"／","小时/年","%","%","%","%","%","%","%","%","%","%"
							//控制措施2
							,"／","小时/年","%","%","%","%","%","%","%","%","%","%"
							//控制措施3
							,"／","小时/年","%","%","%","%","%","%","%","%","%","%"
							//最终估算使用去除率
							,"%","%","%","%","%","%","%","%","%"
							//计算参数
							,"无量纲","无量纲","无量纲","无量纲","无量纲","无量纲"
							};
					for(int i = 0;i<exportEnterprises.size();i++){
			        	List<String> list = new ArrayList<String>();
			        	list.add((i+1)+"");
			        	list.add("");
			        	list.add(findproject.getDataYear());
			        	list.add(exportEnterprises.get(i).get("name") !=null?exportEnterprises.get(i).get("name").toString():"");
			        	list.add(exportEnterprises.get(i).get("code") !=null?exportEnterprises.get(i).get("code").toString():"");
			        	list.add(exportEnterprises.get(i).get("gross_product") !=null?exportEnterprises.get(i).get("gross_product").toString():"");
			        	list.add(exportEnterprises.get(i).get("energy_used") !=null?exportEnterprises.get(i).get("energy_used").toString():"");
			        	list.add(exportEnterprises.get(i).get("work_day") !=null?exportEnterprises.get(i).get("work_day").toString():"");
			        	list.add(exportEnterprises.get(i).get("day_hours") !=null?exportEnterprises.get(i).get("day_hours").toString():"");
			        	list.add(exportEnterprises.get(i).get("longitude") !=null?exportEnterprises.get(i).get("longitude").toString():"");
			        	list.add(exportEnterprises.get(i).get("latitude") !=null?exportEnterprises.get(i).get("latitude").toString():"");
			        	list.add(exportEnterprises.get(i).get("street") !=null?exportEnterprises.get(i).get("street").toString():"");
			        	//企业地址
			        	list.add(exportEnterprises.get(i).get("provineces_name") !=null?exportEnterprises.get(i).get("provineces_name").toString():"");
			        	list.add(exportEnterprises.get(i).get("city_name") !=null?exportEnterprises.get(i).get("city_name").toString():"");
			        	list.add(exportEnterprises.get(i).get("county_name") !=null?exportEnterprises.get(i).get("county_name").toString():"");
			        	list.add(exportEnterprises.get(i).get("street_name") !=null?exportEnterprises.get(i).get("street_name").toString():"");
			        	list.add(exportEnterprises.get(i).get("house_number") !=null?exportEnterprises.get(i).get("house_number").toString():"");
			        	//行业类别
			        	list.add(exportEnterprises.get(i).get("industry_category_code_middle") !=null?exportEnterprises.get(i).get("industry_category_code_middle").toString():"");
			        	list.add(exportEnterprises.get(i).get("industry_category_code_main") !=null?exportEnterprises.get(i).get("industry_category_code_main").toString():"");
			        	list.add(exportEnterprises.get(i).get("industry_category_code_middle") !=null?exportEnterprises.get(i).get("industry_category_code_middle").toString():"");
			        	list.add("");
			        	list.add(exportEnterprises.get(i).get("industry_category_name_main") !=null?exportEnterprises.get(i).get("industry_category_name_main").toString():"");
			        	list.add(exportEnterprises.get(i).get("industry_category_name_middle") !=null?exportEnterprises.get(i).get("industry_category_name_middle").toString():"");
			        	list.add("");
			        	//锅炉信息
			        	list.add("");
			        	list.add("");
			        	list.add(exportEnterprises.get(i).get("boiler_type") !=null?exportEnterprises.get(i).get("boiler_type").toString():"");
			        	list.add("");
			        	list.add(exportEnterprises.get(i).get("steam_ton") !=null?exportEnterprises.get(i).get("steam_ton").toString():"");
			        	list.add(exportEnterprises.get(i).get("nomal_production_hour") !=null?exportEnterprises.get(i).get("nomal_production_hour").toString():"");
			        	String operation =  exportEnterprises.get(i).get("operation") !=null?exportEnterprises.get(i).get("operation").toString():"";
			        	list.add("".equals(operation)?"":operation.substring(0, 4));
			        	list.add("".equals(operation)?"":operation.substring(5, 7));
			        	//排放源分类名称
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	//排放量
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");
			        	//排放口信息
			        	list.add(exportEnterprises.get(i).get("height") !=null?exportEnterprises.get(i).get("height").toString():"");
			        	list.add(exportEnterprises.get(i).get("diameter") !=null?exportEnterprises.get(i).get("diameter").toString():"");
			        	list.add(exportEnterprises.get(i).get("temperature") !=null?exportEnterprises.get(i).get("temperature").toString():"");
			        	list.add(exportEnterprises.get(i).get("flow") !=null?exportEnterprises.get(i).get("flow").toString():"");
			        	list.add(exportEnterprises.get(i).get("emissions") !=null?exportEnterprises.get(i).get("emissions").toString():"");
			        	list.add(exportEnterprises.get(i).get("so2") !=null?exportEnterprises.get(i).get("so2").toString():"");
			        	list.add(exportEnterprises.get(i).get("nox") !=null?exportEnterprises.get(i).get("nox").toString():"");
			        	list.add(exportEnterprises.get(i).get("tsp") !=null?exportEnterprises.get(i).get("tsp").toString():"");
			        	list.add(exportEnterprises.get(i).get("voc") !=null?exportEnterprises.get(i).get("voc").toString():"");
			        	//时间分配
//			        	list.add(exportEnterprises.get(i).get("t12name") !=null?exportEnterprises.get(i).get("t12name").toString():"");
//			        	String t12month_type = exportEnterprises.get(i).get("t12month_type") !=null?exportEnterprises.get(i).get("t12month_type").toString():"";
//			        	list.add("".equals(t12month_type)?"":"燃料");
//			        	list.add(exportEnterprises.get(i).get("t12unit") !=null?exportEnterprises.get(i).get("t12unit").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t12january") !=null?exportEnterprises.get(i).get("t12january").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t12february") !=null?exportEnterprises.get(i).get("t12february").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t12march") !=null?exportEnterprises.get(i).get("t12march").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t12april") !=null?exportEnterprises.get(i).get("t12april").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t12may") !=null?exportEnterprises.get(i).get("t12may").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t12june") !=null?exportEnterprises.get(i).get("t12june").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t12july") !=null?exportEnterprises.get(i).get("t12july").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t12august") !=null?exportEnterprises.get(i).get("t12august").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t12september") !=null?exportEnterprises.get(i).get("t12september").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t12october") !=null?exportEnterprises.get(i).get("t12october").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t12november") !=null?exportEnterprises.get(i).get("t12november").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t12december") !=null?exportEnterprises.get(i).get("t12december").toString():"");
			        	
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");
			        	//排放因子
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");
			        	//数据来源
			        	list.add("");list.add("");list.add("");list.add("");
			        	//燃料信息
			        	list.add(exportEnterprises.get(i).get("fuel_type") !=null?exportEnterprises.get(i).get("fuel_type").toString():"");
			        	list.add(exportEnterprises.get(i).get("fuel_consumption") !=null?exportEnterprises.get(i).get("fuel_consumption").toString():"");
			        	list.add(exportEnterprises.get(i).get("fuel_unit") !=null?exportEnterprises.get(i).get("fuel_unit").toString():"");
			        	list.add("");list.add("");list.add("");list.add("");
			        	list.add(exportEnterprises.get(i).get("fuel_sulfur_content") !=null?exportEnterprises.get(i).get("fuel_sulfur_content").toString():"");
			        	list.add(exportEnterprises.get(i).get("fuel_ash") !=null?exportEnterprises.get(i).get("fuel_ash").toString():"");
			        	list.add(exportEnterprises.get(i).get("fuel_volatiles") !=null?exportEnterprises.get(i).get("fuel_volatiles").toString():"");
			        	//控制措施1
			        	list.add(exportEnterprises.get(i).get("t4name") !=null?exportEnterprises.get(i).get("t4name").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4work_hours") !=null?exportEnterprises.get(i).get("t4work_hours").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4collect_efficiency") !=null?exportEnterprises.get(i).get("t4collect_efficiency").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4so2") !=null?exportEnterprises.get(i).get("t4so2").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4nox") !=null?exportEnterprises.get(i).get("t4nox").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4co") !=null?exportEnterprises.get(i).get("t4co").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4pm10") !=null?exportEnterprises.get(i).get("t4pm10").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4pm25") !=null?exportEnterprises.get(i).get("t4pm25").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4bc") !=null?exportEnterprises.get(i).get("t4bc").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4oc") !=null?exportEnterprises.get(i).get("t4oc").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4voc") !=null?exportEnterprises.get(i).get("t4voc").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4nh3") !=null?exportEnterprises.get(i).get("t4nh3").toString():"");
			        	//控制措施2
			        	list.add(exportEnterprises.get(i).get("t5name") !=null?exportEnterprises.get(i).get("t5name").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5work_hours") !=null?exportEnterprises.get(i).get("t5work_hours").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5collect_efficiency") !=null?exportEnterprises.get(i).get("t5collect_efficiency").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5so2") !=null?exportEnterprises.get(i).get("t5so2").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5nox") !=null?exportEnterprises.get(i).get("t5nox").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5co") !=null?exportEnterprises.get(i).get("t5co").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5pm10") !=null?exportEnterprises.get(i).get("t5pm10").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5pm25") !=null?exportEnterprises.get(i).get("t5pm25").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5bc") !=null?exportEnterprises.get(i).get("t5bc").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5oc") !=null?exportEnterprises.get(i).get("t5oc").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5voc") !=null?exportEnterprises.get(i).get("t5voc").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5nh3") !=null?exportEnterprises.get(i).get("t5nh3").toString():"");
			        	//控制措施3
			        	list.add(exportEnterprises.get(i).get("t6name") !=null?exportEnterprises.get(i).get("t6name").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6work_hours") !=null?exportEnterprises.get(i).get("t6work_hours").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6collect_efficiency") !=null?exportEnterprises.get(i).get("t6collect_efficiency").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6so2") !=null?exportEnterprises.get(i).get("t6so2").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6nox") !=null?exportEnterprises.get(i).get("t6nox").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6co") !=null?exportEnterprises.get(i).get("t6co").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6pm10") !=null?exportEnterprises.get(i).get("t6pm10").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6pm25") !=null?exportEnterprises.get(i).get("t6pm25").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6bc") !=null?exportEnterprises.get(i).get("t6bc").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6oc") !=null?exportEnterprises.get(i).get("t6oc").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6voc") !=null?exportEnterprises.get(i).get("t6voc").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6nh3") !=null?exportEnterprises.get(i).get("t6nh3").toString():"");
			        	//最终估算使用去除率
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");
			        	//计算参数
			        	list.add("");list.add("");list.add("");list.add("");list.add("");list.add("");
			        	map.put(i+1, list);
			        	
			        }
					ExportExcel.exportExcel("工业锅炉燃烧",oneheaders, headers,headerunits, map, response.getOutputStream());
					
				}else if(Constants.EXPORT_POLLUTESOURCE_OPENYARD.equals(polluteSource)){
					//露天
					String[] oneheaders = {"","","","","","","","",
							//扬尘源地址
							"扬尘源地址","扬尘源地址","扬尘源地址","扬尘源地址","扬尘源地址 "
							//行业类别
							,"行业类别","行业类别","行业类别","行业类别","行业类别","行业类别","行业类别"
							//排放源分类名称
							,"排放源分类名称","排放源分类名称","排放源分类名称","排放源分类名称","排放源分类名称","排放源分类名称"
							,"排放源分类名称","排放源分类名称","排放源分类名称","排放源分类名称"
							//排放量
							,"排放量","排放量","排放量","排放量","排放量","排放量","排放量","排放量","排放量"
							//时间分配
							,"时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							//排放因子
							,"排放因子","排放因子","排放因子","排放因子","排放因子"
							,"排放因子","排放因子","排放因子","排放因子"
							,"排放因子","排放因子","排放因子","排放因子"
							,"排放因子","排放因子","排放因子","排放因子"
							//数据来源
							,"数据来源","数据来源","数据来源","数据来源"
							//区域参数
							,"区域参数","区域参数","区域参数","区域参数","区域参数","区域参数","区域参数"
							,"区域参数","区域参数"
							//土壤扬尘活动水平
							,"土壤扬尘活动水平","土壤扬尘活动水平"
							//土壤扬尘技术参数
							,"土壤扬尘技术参数","土壤扬尘技术参数","土壤扬尘技术参数","土壤扬尘技术参数","土壤扬尘技术参数"
							,"土壤扬尘技术参数","土壤扬尘技术参数","土壤扬尘技术参数","土壤扬尘技术参数"
							,"土壤扬尘技术参数","土壤扬尘技术参数","土壤扬尘技术参数"
							//道路扬尘活动水平
							,"道路扬尘活动水平","道路扬尘活动水平","道路扬尘活动水平"
							//铺装道路扬尘技术参数
							,"铺装道路扬尘技术参数","铺装道路扬尘技术参数","铺装道路扬尘技术参数","铺装道路扬尘技术参数"
							//未铺装道路扬尘技术参数
							,"未铺装道路扬尘技术参数","未铺装道路扬尘技术参数","未铺装道路扬尘技术参数","未铺装道路扬尘技术参数"
							,"未铺装道路扬尘技术参数","未铺装道路扬尘技术参数","未铺装道路扬尘技术参数"
							//施工扬尘活动水平
							,"施工扬尘活动水平","施工扬尘活动水平","施工扬尘活动水平","施工扬尘活动水平","施工扬尘活动水平"
							//施工扬尘技术参数
							,"施工扬尘技术参数","施工扬尘技术参数","施工扬尘技术参数","施工扬尘技术参数","施工扬尘技术参数"
							,"施工扬尘技术参数","施工扬尘技术参数"
							//堆场扬尘活动水平
							,"堆场扬尘活动水平 ","堆场扬尘活动水平","堆场扬尘活动水平","堆场扬尘活动水平","堆场扬尘活动水平"
							,"堆场扬尘活动水平","堆场扬尘活动水平","堆场扬尘活动水平"
							,"堆场扬尘活动水平","堆场扬尘活动水平","堆场扬尘活动水平"
							//装卸扬尘技术参数
							,"装卸扬尘技术参数","装卸扬尘技术参数","装卸扬尘技术参数"
							//风蚀扬尘技术参数
							,"风蚀扬尘技术参数","风蚀扬尘技术参数","风蚀扬尘技术参数","风蚀扬尘技术参数","风蚀扬尘技术参数"
							,"风蚀扬尘技术参数","风蚀扬尘技术参数"
							//混凝土搅拌站活动数据
							,"混凝土搅拌站活动数据"
							//控制措施1
							,"控制措施1","控制措施1","控制措施1","控制措施1","控制措施1","控制措施1"
							//控制措施2
							,"控制措施2","控制措施2","控制措施2","控制措施2","控制措施2","控制措施2"
							//控制措施3
							,"控制措施3","控制措施3","控制措施3","控制措施3","控制措施3","控制措施3"
							//最终估算使用去除率
							,"最终估算使用去除率","最终估算使用去除率","最终估算使用去除率","最终估算使用去除率","最终估算使用去除率"
							};
					String[] headers = {"编号","性质","年份","名称","组织机构代码","中心经度","中心纬度","地区代码",
							"省（自治区、直辖市）","城市","区县市","乡（镇）","详细地址 "
							,"行业类别代码","大类","中类","小类","大类名称","中类名称","小类名称"
							,"1级代码","1级名称","2级代码","2级名称","3级代码","3级名称","4级代码","4级名称","5级代码","自定义名称"
							//排放量
							,"SO2","NOx","CO","PM10","PM2.5","BC","OC","VOCs","NH3"
							//时间分配
							,"月分配数据来源","月分配数据类型","月分配数据单位"
							,"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"
							,"日分配数据来源","日分配数据类型","日分配数据单位","周一","周二","周三","周四","周五","周六","周日"
							,"小时分配数据来源","小时分配数据类型","小时分配数据单位"
							,"1h","2h","3h","4h","5h","6h","7h","8h","9h","10h","11h","12h","13h","14h","15h","16h"
							,"17h","18h","19h","20h","21h","22h","23h","24h"
							//排放因子
							,"计算用活动水平类型","PM10因子","PM10因子输出单位","PM10因子输入单位","PM10因子转换"
							,"PM2.5因子","PM2.5因子输出单位","PM2.5因子输入单位","PM2.5因子转换"
							,"BC因子","BC因子输出单位","BC因子输入单位","BC因子转换"
							,"OC因子","OC因子输出单位","OC因子输入单位","OC因子转换"
							//数据来源
							,"活动水平数据","排放因子","其他参数","计算方法"
							//区域参数
							,"土质主类","土质细类","地区类型","城市类型","无屏蔽宽度","平均风速","年降雨量"
							,"年平均温度","不起尘天数/降水大于0.25天数"
							//土壤扬尘活动水平
							,"土地面积","单位转化系数(土壤)"
							//土壤扬尘技术参数
							,"PM10百分含量","PM2.5百分含量","土壤风蚀指数-PM10","土壤风蚀指数PM2.5","地面粗糙因子"
							,"无屏蔽宽度因子","植被覆盖因子","	年潜在蒸发量","降水-蒸发指数"
							,"气候因子","起尘因子-PM10","起尘因子-PM2.5"
							//道路扬尘活动水平
							,"道路程度","车流量","单位转化系数(道路)"
							//铺装道路扬尘技术参数
							,"PM10粒度乘数","PM2.5粒度乘数","尘负荷","平均车重"
							//未铺装道路扬尘技术参数
							,"PM10粒度乘数","PM2.5粒度乘数","系数a","系数b","有效尘积负荷","平均车速","积尘含水率"
							//施工扬尘活动水平
							,"施工面积","施工周期（月份）","施工周期（小时）","每天工作小时数	","单位转化系数(施工)"
							//施工扬尘技术参数
							,"PM10粒径系数","PM2.5粒径系数","起尘面积率","地面2.5米处风速","积尘含水率","尘负荷","每小时运行的机动车数量"
							//堆场扬尘活动水平
							,"物料类型 ","装卸总次数","每次装卸量","装卸总量","料堆占地面积","料堆高度","料堆表面积","单位转化系数(堆场)"
							,"出入车辆是否清洗","是否进行破碎、筛选","破碎环境是否封闭"
							//装卸扬尘技术参数
							,"PM10粒径系数","PM2.5粒径系数","物料含水率"
							//风蚀扬尘技术参数
							,"PM10粒径系数","PM2.5粒径系数","每年受扰动次数","风蚀潜势","阈值摩擦风速","摩擦风速","料堆表面粗糙度"
							//混凝土搅拌站活动数据
							,"混凝土产量"
							//控制措施1
							,"控制技术名称","TSP去除效率","PM10去除效率","PM2.5去除效率","BC去除效率","OC去除效率"
							//控制措施2
							,"控制技术名称","TSP去除效率","PM10去除效率","PM2.5去除效率","BC去除效率","OC去除效率"
							//控制措施3
							,"控制技术名称","TSP去除效率","PM10去除效率","PM2.5去除效率","BC去除效率","OC去除效率"
							//最终估算使用去除率
							,"TSP去除效率","PM10去除效率","PM2.5去除效率","BC去除效率","OC去除效率"

					};
					String[] headerunits= {"单位","P/S","／","／","／","度","度","／",
							"／","／","／","／","／ "
							//行业类别
							,"／","／","／","／","／","／","／"
							//排放源分类名称
							,"／","／","／","／","／","／","／","／","／","／"
							//排放量
							,"吨","吨","吨","吨","吨","吨","吨","吨","吨"
							//时间分配
							,"／","／","／"
							,"／","／","／","／","／","／","／","／","／","／","／","／"
							,"／","／","／","／","／","／","／","／","／","／"
							,"／","／","／"
							,"／","／","／","／","／","／","／","／","／","／","／","／","／","／","／","／"
							,"／","／","／","／","／","／","／","／"
							//排放因子
							,"／","／","／","／","／"
							,"／","／","／","／"
							,"／","／","／","／"
							,"／","／","／","／"
							//数据来源
							,"／","／","／","／"
							//区域参数
							,"／","／","近海/海岛/海岸/湖岸/沙漠/其他","城市/郊区","米","米/秒","毫米"
							,"摄氏度","天"
							//土壤扬尘活动水平
							,"平方米","／"
							//土壤扬尘技术参数
							,"ki，无量纲","ki，无量纲","吨/万平方米","吨/万平方米","无量纲"
							,"无量纲","无量纲","mm","无量纲"
							,"无量纲","t/(104 m2 •a)","t/(104 m2 •a)"
							//道路扬尘活动水平
							,"千米","辆/年","／"
							//铺装道路扬尘技术参数
							,"ki，g/km","ki，g/km","g/m2","吨"
							//未铺装道路扬尘技术参数
							,"ki，g/km","ki，g/km","无量纲","无量纲","%","km/h","%"
							//施工扬尘活动水平
							,"平方米","月","小时","小时","／"
							//施工扬尘技术参数
							,"ki，无量纲","ki，无量纲","%","m/s","%","g/m2","辆"
							//堆场扬尘活动水平
							,"／","次","吨/次","吨","平方米","米","平方米","／"
							,"是/否","是/否","是/否"
							//装卸扬尘技术参数
							,"ki，无量纲","ki，无量纲","%"
							//风蚀扬尘技术参数
							,"ki，无量纲","ki，无量纲","次/年","g/m2","m/s","m/s","米"
							//混凝土搅拌站活动数据
							,"吨"
							//控制措施1
							,"／","%","%","%","%","%"
							//控制措施2
							,"／","%","%","%","%","%"
							//控制措施3
							,"／","%","%","%","%","%"
							//最终估算使用去除率
							,"%","%","%","%","%"
							};
					for(int i = 0;i<exportEnterprises.size();i++){
			        	List<String> list = new ArrayList<String>();
			        	list.add((i+1)+"");
			        	list.add("");
			        	list.add(findproject.getDataYear());
			        	list.add(exportEnterprises.get(i).get("name") !=null?exportEnterprises.get(i).get("name").toString():"");
			        	list.add(exportEnterprises.get(i).get("code") !=null?exportEnterprises.get(i).get("code").toString():"");
			        	list.add(exportEnterprises.get(i).get("longitude") !=null?exportEnterprises.get(i).get("longitude").toString():"");
			        	list.add(exportEnterprises.get(i).get("latitude") !=null?exportEnterprises.get(i).get("latitude").toString():"");
			        	list.add(exportEnterprises.get(i).get("street") !=null?exportEnterprises.get(i).get("street").toString():"");
			        	//扬尘源地址
			        	list.add(exportEnterprises.get(i).get("provineces_name") !=null?exportEnterprises.get(i).get("provineces_name").toString():"");
			        	list.add(exportEnterprises.get(i).get("city_name") !=null?exportEnterprises.get(i).get("city_name").toString():"");
			        	list.add(exportEnterprises.get(i).get("county_name") !=null?exportEnterprises.get(i).get("county_name").toString():"");
			        	list.add(exportEnterprises.get(i).get("street_name") !=null?exportEnterprises.get(i).get("street_name").toString():"");
			        	list.add(exportEnterprises.get(i).get("house_number") !=null?exportEnterprises.get(i).get("house_number").toString():"");
			        	//行业类别
			        	list.add(exportEnterprises.get(i).get("industry_category_code_middle") !=null?exportEnterprises.get(i).get("industry_category_code_middle").toString():"");
			        	list.add(exportEnterprises.get(i).get("industry_category_code_main") !=null?exportEnterprises.get(i).get("industry_category_code_main").toString():"");
			        	list.add(exportEnterprises.get(i).get("industry_category_code_middle") !=null?exportEnterprises.get(i).get("industry_category_code_middle").toString():"");
			        	list.add("");
			        	list.add(exportEnterprises.get(i).get("industry_category_name_main") !=null?exportEnterprises.get(i).get("industry_category_name_main").toString():"");
			        	list.add(exportEnterprises.get(i).get("industry_category_name_middle") !=null?exportEnterprises.get(i).get("industry_category_name_middle").toString():"");
			        	list.add("");
			        	//排放源分类名称
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	//排放量
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");
			        	//时间分配
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");
			        	//排放因子
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");
			        	//数据来源
			        	list.add("");list.add("");list.add("");list.add("");
						//区域参数
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");
						//土壤扬尘活动水平
			        	list.add("");list.add("");
						//土壤扬尘技术参数
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");
						//道路扬尘活动水平
			        	list.add("");list.add("");list.add("");
						//铺装道路扬尘技术参数
			        	list.add("");list.add("");list.add("");list.add("");
						//未铺装道路扬尘技术参数
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");
						//施工扬尘活动水平
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
						//施工扬尘技术参数
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");
			        	//堆场扬尘活动水平
			        	list.add(exportEnterprises.get(i).get("material_type") !=null?exportEnterprises.get(i).get("material_type").toString():"");
			        	list.add(exportEnterprises.get(i).get("cargo_trips") !=null?exportEnterprises.get(i).get("cargo_trips").toString():"");
			        	list.add(exportEnterprises.get(i).get("carry_amount") !=null?exportEnterprises.get(i).get("carry_amount").toString():"");
			        	list.add(exportEnterprises.get(i).get("load_amount") !=null?exportEnterprises.get(i).get("load_amount").toString():"");
			        	list.add(exportEnterprises.get(i).get("area") !=null?exportEnterprises.get(i).get("area").toString():"");
			        	list.add(exportEnterprises.get(i).get("height") !=null?exportEnterprises.get(i).get("height").toString():"");
			        	list.add("");list.add("");
			        	String car_cleaning = exportEnterprises.get(i).get("car_cleaning") !=null?exportEnterprises.get(i).get("car_cleaning").toString():"";
			        	list.add("".equals(car_cleaning) || "0".equals(car_cleaning)?"否":"是");
			        	String broken = exportEnterprises.get(i).get("broken") !=null?exportEnterprises.get(i).get("broken").toString():"";
			        	list.add("".equals(broken) || "0".equals(broken)?"否":"是");
			        	String closed = exportEnterprises.get(i).get("closed") !=null?exportEnterprises.get(i).get("closed").toString():"";
			        	list.add("".equals(closed) || "0".equals(closed)?"否":"是");
			        	//装卸扬尘技术参数
			        	list.add("");list.add("");
			        	list.add(exportEnterprises.get(i).get("moisture_content") !=null?exportEnterprises.get(i).get("moisture_content").toString():"");
						//风蚀扬尘技术参数
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");
						//混凝土搅拌站活动数据
			        	list.add("");
						//控制措施1
			        	list.add(exportEnterprises.get(i).get("measure1") !=null?exportEnterprises.get(i).get("measure1").toString():"");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
						//控制措施2
			        	list.add(exportEnterprises.get(i).get("measure2") !=null?exportEnterprises.get(i).get("measure2").toString():"");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
						//控制措施3
			        	list.add(exportEnterprises.get(i).get("measure3") !=null?exportEnterprises.get(i).get("measure3").toString():"");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
						//最终估算使用去除率
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	
			        	
			        	
			        	map.put(i+1, list);
			        	
			        }
					ExportExcel.exportExcel("扬尘源表",oneheaders, headers, headerunits,map, response.getOutputStream());

				}else if(Constants.EXPORT_POLLUTESOURCE_OILTANK.equals(polluteSource)){
					//石油
					String[] oneheaders = {"","","","","","","","",
							//加油站地址
							"加油站地址","加油站地址","加油站地址","加油站地址","加油站地址 "
							//行业类别
							,"行业类别","行业类别","行业类别","行业类别","行业类别","行业类别","行业类别"
							//排放源分类名称
							,"排放源分类名称","排放源分类名称","排放源分类名称","排放源分类名称","排放源分类名称","排放源分类名称"
							,"排放源分类名称","排放源分类名称","排放源分类名称","排放源分类名称"
							//排放量
							,"排放量","排放量","排放量","排放量","排放量","排放量","排放量","排放量","排放量"
							//时间分配
							,"时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							//排放因子
							,"排放因子","排放因子","排放因子","排放因子","排放因子"
							//数据来源
							,"数据来源","数据来源","数据来源","数据来源"
							//加油站销售信息
							,"加油站销售信息","加油站销售信息","加油站销售信息","加油站销售信息","加油站销售信息","加油站销售信息","加油站销售信息"
							//储油库信息
							,"储油库信息","储油库信息","储油库信息","储油库信息","储油库信息","储油库信息"
							//回收装置信息
							,"回收装置信息","回收装置信息","回收装置信息","回收装置信息","回收装置信息","回收装置信息"
							};
					String[] headers ={"编号","性质","年份","名称","组织机构代码","年营业额","中心经度","中心纬度","地区代码",
							//加油站地址
							"省（自治区、直辖市）","城市","区县市","乡（镇）","详细地址 "
							//行业类别
							,"行业类别代码","大类","中类","小类","大类名称","中类名称","小类名称"
							//排放源分类名称
							,"1级代码","1级名称","2级代码","2级名称","3级代码","3级名称","4级代码","4级名称","5级代码","自定义名称"
							//排放量
							,"SO2","NOx","CO","PM10","PM2.5","BC","OC","VOCs","NH3"
							//时间分配
							,"月分配数据来源","月分配数据类型","月分配数据单位"
							,"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"
							,"日分配数据来源","日分配数据类型","日分配数据单位","周一","周二","周三","周四","周五","周六","周日"
							,"小时分配数据来源","小时分配数据类型","小时分配数据单位"
							,"1h","2h","3h","4h","5h","6h","7h","8h","9h","10h","11h","12h","13h","14h","15h","16h"
							,"17h","18h","19h","20h","21h","22h","23h","24h"
							//排放因子
							,"计算用活动水平类型","VOCs因子","VOCs因子输出单位","VOCs因子输入单位","VOCs因子转换"
							//数据来源
							,"活动水平数据","排放因子","其他参数","计算方法"
							//加油站销售信息
							,"油品名称","年销售量/运输量","单位","单位转换系数","标准名称","标准年销售量/运输量","标准单位"
							//储油库信息
							,"油品类型","标准油品类型","储罐类型","总储量","储存周期","年装卸次数"
							//回收装置信息
							,"一次回收安装率","一次回收率","二次回收安装率","二次回收率","三次回收安装率","三次回收率"
												
					};
					String[] headerunits= {"单位","P/S","／","／","／","万元","度","度","／",
							//加油站地址
							"／","／","／","／","／ "
							//行业类别
							,"／","／","／","／","／","／","／"
							//排放源分类名称
							,"／","／","／","／","／","／","／","／","／","／"
							//排放量
							,"吨","吨","吨","吨","吨","吨","吨","吨","吨"
							//时间分配
							,"／","／","／"
							,"／","／","／","／","／","／","／","／","／","／","／","／"
							,"／","／","／","／","／","／","／","／","／","／"
							,"／","／","／"
							,"／","／","／","／","／","／","／","／","／","／","／","／","／","／","／","／"
							,"／","／","／","／","／","／","／","／"
							//排放因子
							,"／","／","／","／","／"
							//数据来源
							,"／","／","／","／"
							//加油站销售信息
							,"／","／","／","／","／","／","／"
							//储油库信息
							,"／","／","／","吨","天","次"
							//回收装置信息
							,"%","%","%","%","%","%"
							
							};
					for(int i = 0;i<exportEnterprises.size();i++){
			        	List<String> list = new ArrayList<String>();
			        	list.add((i+1)+"");
			        	list.add("");
			        	list.add(findproject.getDataYear());
			        	list.add(exportEnterprises.get(i).get("name") !=null?exportEnterprises.get(i).get("name").toString():"");
			        	list.add(exportEnterprises.get(i).get("code") !=null?exportEnterprises.get(i).get("code").toString():"");
			        	list.add("");
			        	list.add(exportEnterprises.get(i).get("longitude") !=null?exportEnterprises.get(i).get("longitude").toString():"");
			        	list.add(exportEnterprises.get(i).get("latitude") !=null?exportEnterprises.get(i).get("latitude").toString():"");
			        	list.add(exportEnterprises.get(i).get("street") !=null?exportEnterprises.get(i).get("street").toString():"");
			        	//加油站地址
			        	list.add(exportEnterprises.get(i).get("provineces_name") !=null?exportEnterprises.get(i).get("provineces_name").toString():"");
			        	list.add(exportEnterprises.get(i).get("city_name") !=null?exportEnterprises.get(i).get("city_name").toString():"");
			        	list.add(exportEnterprises.get(i).get("county_name") !=null?exportEnterprises.get(i).get("county_name").toString():"");
			        	list.add(exportEnterprises.get(i).get("street_name") !=null?exportEnterprises.get(i).get("street_name").toString():"");
			        	list.add(exportEnterprises.get(i).get("house_number") !=null?exportEnterprises.get(i).get("house_number").toString():"");
			        	//行业类别
			        	list.add(exportEnterprises.get(i).get("industry_category_code_middle") !=null?exportEnterprises.get(i).get("industry_category_code_middle").toString():"");
			        	list.add(exportEnterprises.get(i).get("industry_category_code_main") !=null?exportEnterprises.get(i).get("industry_category_code_main").toString():"");
			        	list.add(exportEnterprises.get(i).get("industry_category_code_middle") !=null?exportEnterprises.get(i).get("industry_category_code_middle").toString():"");
			        	list.add("");
			        	list.add(exportEnterprises.get(i).get("industry_category_name_main") !=null?exportEnterprises.get(i).get("industry_category_name_main").toString():"");
			        	list.add(exportEnterprises.get(i).get("industry_category_name_middle") !=null?exportEnterprises.get(i).get("industry_category_name_middle").toString():"");
			        	list.add("");
			        	//排放源分类名称
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	//排放量
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");
			        	//时间分配
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");
			        	//排放因子
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	//数据来源
			        	list.add("");list.add("");list.add("");list.add("");
						//加油站销售信息
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");
						//储油库信息
						//,"油品类型","标准油品类型","储罐类型","总储量","储存周期","年装卸次数"
			        	list.add(exportEnterprises.get(i).get("tank_material_type") !=null?exportEnterprises.get(i).get("tank_material_type").toString():"");
			        	list.add("");
			        	list.add(exportEnterprises.get(i).get("tank_type") !=null?exportEnterprises.get(i).get("tank_type").toString():"");
			        	list.add(exportEnterprises.get(i).get("storage_capacity") !=null?exportEnterprises.get(i).get("storage_capacity").toString():"");
			        	list.add(exportEnterprises.get(i).get("storage_cycle") !=null?exportEnterprises.get(i).get("storage_cycle").toString():"");
			        	list.add(exportEnterprises.get(i).get("fill_times") !=null?exportEnterprises.get(i).get("fill_times").toString():"");
			        	
						//回收装置信息
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");
			        	
			        	
			        	map.put(i+1, list);
			        	
			        }
					
					ExportExcel.exportExcel("储存运输源表",oneheaders, headers,headerunits, map, response.getOutputStream());

				}else if(Constants.EXPORT_POLLUTESOURCE_OTHER.equals(polluteSource)){
					//工段和炉窑
					String[] oneheaders = {"","","","","","","","","","","",""
							//企业地址
							,"企业地址","企业地址","企业地址","企业地址","企业地址"
							//行业类别
							,"行业类别","行业类别","行业类别","行业类别","行业类别","行业类别","行业类别"
							//窑炉编号
							,"","","","","","",""
//							//排放源分类名称
							,"排放源分类名称","排放源分类名称","排放源分类名称","排放源分类名称","排放源分类名称"
							,"排放源分类名称","排放源分类名称","排放源分类名称","排放源分类名称","排放源分类名称"
							//排放量
							,"排放量","排放量","排放量","排放量","排放量","排放量","排放量","排放量","排放量"
							//排放口信息
							,"排放口信息","排放口信息","排放口信息","排放口信息","排放口信息"
							,"排放口信息","排放口信息","排放口信息","排放口信息"
							//时间分配
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							,"时间分配","时间分配","时间分配","时间分配","时间分配","时间分配","时间分配"
							//排放因子
							,"排放因子","排放因子","排放因子","排放因子","排放因子","排放因子","排放因子"
							,"排放因子","排放因子","排放因子","排放因子","排放因子","排放因子","排放因子"
							,"排放因子","排放因子","排放因子","排放因子","排放因子","排放因子","排放因子","排放因子","排放因子"
							,"排放因子","排放因子","排放因子","排放因子","排放因子","排放因子","排放因子","排放因子","排放因子"
							,"排放因子","排放因子","排放因子","排放因子","排放因子"
							//数据来源
							,"数据来源","数据来源","数据来源","数据来源"
							//筛选计算用活动水平
							,"筛选计算用活动水平","筛选计算用活动水平","筛选计算用活动水平","筛选计算用活动水平","筛选计算用活动水平"
							,"筛选计算用活动水平","筛选计算用活动水平","筛选计算用活动水平","筛选计算用活动水平","筛选计算用活动水平"
							,"筛选计算用活动水平","筛选计算用活动水平","筛选计算用活动水平"
							//产品1
							,"产品1","产品1","产品1","产品1"
							//产品2
							,"产品2","产品2","产品2","产品2"
							//产品3
							,"产品3","产品3","产品3","产品3"
							//原料1
							,"原料1","原料1","原料1","原料1","原料1","原料1"
							//原料2
							,"原料2","原料2","原料2","原料2","原料2","原料2"
							//原料3
							,"原料3","原料3","原料3","原料3","原料3","原料3"
							//原料4
							,"原料4","原料4","原料4","原料4","原料4","原料4"
							//原料5
							,"原料5","原料5","原料5","原料5","原料5","原料5"
							//原料6
							,"原料6","原料6","原料6","原料6","原料6","原料6"
							//燃料信息
							,"燃料信息","燃料信息","燃料信息","燃料信息","燃料信息","燃料信息"
							//控制措施1
							,"控制措施1","控制措施1","控制措施1","控制措施1","控制措施1","控制措施1","控制措施1"
							,"控制措施1","控制措施1","控制措施1","控制措施1","控制措施1"
							//控制措施2
							,"控制措施2","控制措施2","控制措施2","控制措施2","控制措施2","控制措施2","控制措施2"
							,"控制措施2","控制措施2","控制措施2","控制措施2","控制措施2"
							//控制措施3
							,"控制措施3","控制措施3","控制措施3","控制措施3","控制措施3","控制措施3","控制措施3"
							,"控制措施3","控制措施3","控制措施3","控制措施3","控制措施3"
							//最终估算使用去除率
							,"最终估算使用去除率","最终估算使用去除率","最终估算使用去除率","最终估算使用去除率"
							,"最终估算使用去除率","最终估算使用去除率","最终估算使用去除率","最终估算使用去除率","最终估算使用去除率"
							};
					String[] headers = {"编号","性质","年份","名称","组织机构代码","生产总值","年用电量","年生产天数"
							,"日生产小时数","中心经度","中心纬度","地区代码"
							//企业地址
							,"省（自治区、直辖市）","城市","区县市","乡（镇）","详细地址 "
							//行业类别
							,"行业类别代码","大类","中类","小类","大类名称","中类名称","小类名称"
							//窑炉编号
							,"窑炉编号","窑炉状态","炉型","估算采用炉型","年工作小时数","投运时间年","投运时间月"
							//排放源分类名称
							,"1级代码","1级名称","2级代码","2级名称","3级代码","3级名称","4级代码","4级名称","5级代码","自定义名称"
							//排放量
							,"SO2","NOx","CO","PM10","PM2.5","BC","OC","VOCs","NH3"
							//排放口信息
							,"排放高度","排放口直径","废气温度","废气流量","废气排放量","在线净烟SO2浓度"
							,"在线净烟Nox浓度","在线净烟TSP浓度","在线净烟VOCs浓度"
							//时间分配
							,"月分配数据来源","月分配数据类型","月分配数据单位","1月","2月","3月","4月","5月","6月","7月","8月","9月"
							,"10月","11月","12月","日分配数据来源","日分配数据类型","日分配数据单位","周一","周二","周三","周四","周五","周六"
							,"周日","小时分配数据来源","小时分配数据类型","小时分配数据单位"
							,"1h","2h","3h","4h","5h","6h","7h","8h","9h","10h","11h","12h","13h","14h","15h","16h"
							,"17h","18h","19h","20h","21h","22h","23h","24h"
							//排放因子
							,"计算用活动水平类型","SO2因子","SO2因子输出单位","SO2输入单位","SO2因子转换","NOx因子","NOx因子输出单位"
							,"NOx因子输入单位","NOx因子转换","CO因子","CO因子输出单位","CO因子输入单位","CO因子转换","PM10因子"
							,"PM10因子输出单位","PM10因子输入单位","PM10因子转换","PM2.5因子","PM2.5因子输出单位"
							,"PM2.5因子输入单位","PM2.5因子转换","BC因子","BC因子输出单位","BC因子输入单位"
							,"BC因子转换","OC因子","OC因子输出单位","OC因子输入单位","OC因子转换"
							,"VOCs因子","VOCs因子输出单位","VOCs因子输入单位","VOCs因子转换"
							,"NH3因子","NH3因子输出单位","NH3因子输入单位","NH3因子转换"
							//数据来源
							,"活动水平数据","排放因子","其他参数","计算方法"
							//筛选计算用活动水平
							,"原始名称","性质","原始产量/用量","原始单位","VOC含量","VOC挥发度","含硫率","灰分"
							,"挥发分","单位转换系数","标准名称	","标准年产量/用量","标准单位"
							//产品1
							,"产品名称","生产线数量","产品年产量","产品单位"
							//产品2
							,"产品名称","生产线数量","产品年产量","产品单位"
							//产品3
							,"产品名称","生产线数量","产品年产量","产品单位"
							//原料1
							,"原料名称","性质","VOC含量","VOC挥发度","年消耗量","单位"
							//原料2
							,"原料名称","性质","VOC含量","VOC挥发度","年消耗量","单位"
							//原料3
							,"原料名称","性质","VOC含量","VOC挥发度","年消耗量","单位"
							//原料4
							,"原料名称","性质","VOC含量","VOC挥发度","年消耗量","单位"
							//原料5
							,"原料名称","性质","VOC含量","VOC挥发度","年消耗量","单位"
							//原料6
							,"原料名称","性质","VOC含量","VOC挥发度","年消耗量","单位"
							//燃料信息
							,"燃料名称","年消耗量","单位","含硫率","灰分","挥发分"
							//控制措施1
							,"控制措施1名称","年工作时间","废气收集率","SO2去除效率","NOx去除效率","CO去除效率","PM10去除效率"
							,"PM2.5去除效率","BC去除效率","OC去除效率","VOC去除效率","NH3去除效率"
							//控制措施2
							,"控制措施2名称","年工作时间","废气收集率","SO2去除效率","NOx去除效率","CO去除效率","PM10去除效率"
							,"PM2.5去除效率","BC去除效率","OC去除效率","VOC去除效率","NH3去除效率"
							//控制措施3
							,"控制措施3名称","年工作时间","废气收集率","SO2去除效率","NOx去除效率","CO去除效率","PM10去除效率"
							,"PM2.5去除效率","BC去除效率","OC去除效率","VOC去除效率","NH3去除效率"
							//最终估算使用去除率
							,"SO2去除效率","NOx去除效率","CO去除效率","PM10去除效率"
							,"PM2.5去除效率","BC去除效率","OC去除效率","VOC去除效率","NH3去除效率"
					};
											
					String[] headerunits = {"单位","P/S","／","／","／","万元","万千瓦时","天"
							,"小时","度","度","／"
							//企业地址
							,"／","／","／","／","／ "
							//行业类别
							,"／","／","／","／","／","／","／"
							//炉窑信息
							,"／","／","／","／","h/年","年","月"
							//排放源分类名称
							,"／","／","／","／","／","／","／","／","／","／"
							//排放量
							,"吨","吨","吨","吨","吨","吨","吨","吨","吨"
							//排放口信息
							,"米","米","℃","标立方米/小时","万立方米","毫克/立方米","毫克/立方米","毫克/立方米","毫克/立方米"
							//时间分配
							,"／","／","／","／","／","／","／","／","／","／"
							,"／","／","／","／","／","／","／","／","／","／"
							,"／","／","／","／","／","／","／","／","／","／"
							,"／","／","／","／","／","／","／","／","／","／"
							,"／","／","／","／","／","／","／","／","／","／"
							,"／","／"
							//排放因子
							,"／","／","／","／","／","／","／","／","／","／"
							,"／","／","／","／","／","／","／","／","／","／"
							,"／","／","／","／","／","／","／","／","／","／"
							,"／","／","／","／","／","／","／"
							//数据来源
							,"／","／","／","／"
							//筛选计算用活动水平
							,"／","水性/油性","／","／","%","%","%","%"
							,"%","／","／","／","／"
							//产品1
							,"／","条","／","／"
							//产品2
							,"／","条","／","／"
							//产品3
							,"／","条","／","／"
							//原料1
							,"／","水性/油性","%","%","／","／"
							//原料2
							,"／","水性/油性","%","%","／","／"
							//原料3
							,"／","水性/油性","%","%","／","／"
							//原料4
							,"／","水性/油性","%","%","／","／"
							//原料5
							,"／","水性/油性","%","%","／","／"
							//原料6
							,"／","水性/油性","%","%","／","／"
							//燃料信息
							,"／","／","／","%","%","%"
							//控制措施1
							,"／","小时/年","%","%","%","%","%","%","%","%","%","%"
							//控制措施2
							,"／","小时/年","%","%","%","%","%","%","%","%","%","%"
							//控制措施3
							,"／","小时/年","%","%","%","%","%","%","%","%","%","%"
							//最终估算使用去除率
							,"%","%","%","%","%","%","%","%","%"
							};
					for(int i = 0;i<exportEnterprises.size();i++){
			        	List<String> list = new ArrayList<String>();
			        	list.add((i+1)+"");
			        	list.add("");
			        	list.add(findproject.getDataYear());
			        	list.add(exportEnterprises.get(i).get("name") !=null?exportEnterprises.get(i).get("name").toString():"");
			        	list.add(exportEnterprises.get(i).get("code") !=null?exportEnterprises.get(i).get("code").toString():"");
			        	list.add(exportEnterprises.get(i).get("gross_product") !=null?exportEnterprises.get(i).get("gross_product").toString():"");
			        	list.add(exportEnterprises.get(i).get("energy_used") !=null?exportEnterprises.get(i).get("energy_used").toString():"");
			        	list.add(exportEnterprises.get(i).get("work_day") !=null?exportEnterprises.get(i).get("work_day").toString():"");
			        	list.add(exportEnterprises.get(i).get("day_hours") !=null?exportEnterprises.get(i).get("day_hours").toString():"");
			        	list.add(exportEnterprises.get(i).get("longitude") !=null?exportEnterprises.get(i).get("longitude").toString():"");
			        	list.add(exportEnterprises.get(i).get("latitude") !=null?exportEnterprises.get(i).get("latitude").toString():"");
			        	list.add(exportEnterprises.get(i).get("street") !=null?exportEnterprises.get(i).get("street").toString():"");
			        	//企业地址
			        	list.add(exportEnterprises.get(i).get("provineces_name") !=null?exportEnterprises.get(i).get("provineces_name").toString():"");
			        	list.add(exportEnterprises.get(i).get("city_name") !=null?exportEnterprises.get(i).get("city_name").toString():"");
			        	list.add(exportEnterprises.get(i).get("county_name") !=null?exportEnterprises.get(i).get("county_name").toString():"");
			        	list.add(exportEnterprises.get(i).get("street_name") !=null?exportEnterprises.get(i).get("street_name").toString():"");
			        	list.add(exportEnterprises.get(i).get("house_number") !=null?exportEnterprises.get(i).get("house_number").toString():"");
			        	//行业类别
			        	list.add(exportEnterprises.get(i).get("industry_category_code_middle") !=null?exportEnterprises.get(i).get("industry_category_code_middle").toString():"");
			        	list.add(exportEnterprises.get(i).get("industry_category_code_main") !=null?exportEnterprises.get(i).get("industry_category_code_main").toString():"");
			        	list.add(exportEnterprises.get(i).get("industry_category_code_middle") !=null?exportEnterprises.get(i).get("industry_category_code_middle").toString():"");
			        	list.add("");
			        	list.add(exportEnterprises.get(i).get("industry_category_name_main") !=null?exportEnterprises.get(i).get("industry_category_name_main").toString():"");
			        	list.add(exportEnterprises.get(i).get("industry_category_name_middle") !=null?exportEnterprises.get(i).get("industry_category_name_middle").toString():"");
			        	list.add("");
			        	//窑炉编号
			        	list.add("");list.add("");
			        	list.add(exportEnterprises.get(i).get("kiln_type") !=null?exportEnterprises.get(i).get("kiln_type").toString():"");
			        	list.add("");list.add("");
			        	String put_on_date =  exportEnterprises.get(i).get("put_on_date") !=null?exportEnterprises.get(i).get("put_on_date").toString():"";
			        	list.add("".equals(put_on_date)?"":put_on_date.substring(0, 4));
			        	list.add("".equals(put_on_date)?"":put_on_date.substring(5, 7));
						//排放源分类名称
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
						//排放量
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");
						//排放口信息
			        	list.add(exportEnterprises.get(i).get("t3height") !=null?exportEnterprises.get(i).get("t3height").toString():"");
			        	list.add(exportEnterprises.get(i).get("t3diameter") !=null?exportEnterprises.get(i).get("t3diameter").toString():"");
			        	list.add(exportEnterprises.get(i).get("t3temperature") !=null?exportEnterprises.get(i).get("t3temperature").toString():"");
			        	list.add(exportEnterprises.get(i).get("t3flow") !=null?exportEnterprises.get(i).get("t3flow").toString():"");
			        	list.add(exportEnterprises.get(i).get("t3emissions") !=null?exportEnterprises.get(i).get("t3emissions").toString():"");
			        	list.add(exportEnterprises.get(i).get("t3so2") !=null?exportEnterprises.get(i).get("t3so2").toString():"");
			        	list.add(exportEnterprises.get(i).get("t3nox") !=null?exportEnterprises.get(i).get("t3nox").toString():"");
			        	list.add(exportEnterprises.get(i).get("t3tsp") !=null?exportEnterprises.get(i).get("t3tsp").toString():"");
			        	list.add(exportEnterprises.get(i).get("t3voc") !=null?exportEnterprises.get(i).get("t3voc").toString():"");
			        	//时间分配
//			        	list.add(exportEnterprises.get(i).get("t29name") !=null?exportEnterprises.get(i).get("t29name").toString():"");
//			        	String t29month_type = exportEnterprises.get(i).get("t29month_type") !=null?exportEnterprises.get(i).get("t29month_type").toString():"";
//			        	if("".equals(t29month_type)){
//			        		list.add("");
//			        	}else if("fuel".equals(t29month_type)){
//			        		list.add("燃料");
//			        	}else if("product".equals(t29month_type)){
//			        		list.add("产品");
//			        	}else if("rawMaterials".equals(t29month_type)){
//			        		list.add("原料");
//			        	}
//			        	
//			        	list.add(exportEnterprises.get(i).get("t29unit") !=null?exportEnterprises.get(i).get("t29unit").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t29january") !=null?exportEnterprises.get(i).get("t29january").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t29february") !=null?exportEnterprises.get(i).get("t29february").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t29march") !=null?exportEnterprises.get(i).get("t29march").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t29april") !=null?exportEnterprises.get(i).get("t29april").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t29may") !=null?exportEnterprises.get(i).get("t29may").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t29june") !=null?exportEnterprises.get(i).get("t29june").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t29july") !=null?exportEnterprises.get(i).get("t29july").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t29august") !=null?exportEnterprises.get(i).get("t29august").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t29september") !=null?exportEnterprises.get(i).get("t29september").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t29october") !=null?exportEnterprises.get(i).get("t29october").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t29november") !=null?exportEnterprises.get(i).get("t29november").toString():"");
//			        	list.add(exportEnterprises.get(i).get("t29december") !=null?exportEnterprises.get(i).get("t29december").toString():"");
			        	
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");
			        	//排放因子
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");
						//数据来源
			        	list.add("");list.add("");list.add("");list.add("");
						//筛选计算用活动水平
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");
						//产品1，工段有数据优先导出工段，没有导窑炉
			        	list.add(exportEnterprises.get(i).get("t28name") !=null && !"".equals(exportEnterprises.get(i).get("t28name").toString())?exportEnterprises.get(i).get("t28name").toString():
			        		exportEnterprises.get(i).get("t27name") !=null?exportEnterprises.get(i).get("t27name").toString():"");
			        	list.add("");
			        	list.add(exportEnterprises.get(i).get("t28yield") !=null&& !"".equals(exportEnterprises.get(i).get("t28yield").toString())?exportEnterprises.get(i).get("t28yield").toString():
			        		exportEnterprises.get(i).get("t27yield") !=null?exportEnterprises.get(i).get("t27yield").toString():"");
			        	list.add(exportEnterprises.get(i).get("t28unit") !=null&& !"".equals(exportEnterprises.get(i).get("t28unit").toString())?exportEnterprises.get(i).get("t28unit").toString():
			        		exportEnterprises.get(i).get("t27unit") !=null?exportEnterprises.get(i).get("t27unit").toString():"");
						//产品2
			        	list.add("");list.add("");list.add("");list.add("");
						//产品3
			        	list.add("");list.add("");list.add("");list.add("");
						//原料1
			        	
			        	String t22_name = exportEnterprises.get(i).get("t22_name") !=null?exportEnterprises.get(i).get("t22_name").toString():"";
			        	if(!"".equals(t22_name)){
			        		list.add(t22_name);
			        		String t22_solvent_type = exportEnterprises.get(i).get("t22_solvent_type") !=null?exportEnterprises.get(i).get("t22_solvent_type").toString():"";
			        		if("".equals(t22_solvent_type)){
			        			list.add("");
			        		}else if("oil".equals(t22_solvent_type)){
			        			list.add("油性");
			        		}else if("water".equals(t22_solvent_type)){
			        			list.add("水性");
			        		}
			        	}else{
			        		list.add(exportEnterprises.get(i).get("t17_name") !=null?exportEnterprises.get(i).get("t17_name").toString():"");
			        		list.add("");
			        	}
			        	
			        	list.add("");
			        	list.add("");
			        	list.add(exportEnterprises.get(i).get("t22_consumption") !=null&& !"".equals(exportEnterprises.get(i).get("t22_consumption").toString())?exportEnterprises.get(i).get("t22_consumption").toString():
			        		exportEnterprises.get(i).get("t17_consumption") !=null?exportEnterprises.get(i).get("t17_consumption").toString():"");
			        	list.add(exportEnterprises.get(i).get("t22_unit") !=null&& !"".equals(exportEnterprises.get(i).get("t22_unit").toString())?exportEnterprises.get(i).get("t22_unit").toString():
			        		exportEnterprises.get(i).get("t17_unit") !=null?exportEnterprises.get(i).get("t17_unit").toString():"");
						//原料2
			        	String t23_name = exportEnterprises.get(i).get("t23_name") !=null?exportEnterprises.get(i).get("t23_name").toString():"";
			        	if(!"".equals(t23_name)){
			        		list.add(t23_name);
			        		String t23_solvent_type = exportEnterprises.get(i).get("t23_solvent_type") !=null?exportEnterprises.get(i).get("t23_solvent_type").toString():"";
			        		if("".equals(t23_solvent_type)){
			        			list.add("");
			        		}else if("oil".equals(t23_solvent_type)){
			        			list.add("油性");
			        		}else if("water".equals(t23_solvent_type)){
			        			list.add("水性");
			        		}
			        	}else{
			        		list.add(exportEnterprises.get(i).get("t18_name") !=null?exportEnterprises.get(i).get("t18_name").toString():"");
			        		list.add("");
			        	}
			        	
			        	list.add("");
			        	list.add("");
			        	list.add(exportEnterprises.get(i).get("t23_consumption") !=null&& !"".equals(exportEnterprises.get(i).get("t23_consumption").toString())?exportEnterprises.get(i).get("t23_consumption").toString():
			        		exportEnterprises.get(i).get("t18_consumption") !=null?exportEnterprises.get(i).get("t18_consumption").toString():"");
			        	list.add(exportEnterprises.get(i).get("t23_unit") !=null&& !"".equals(exportEnterprises.get(i).get("t23_unit").toString())?exportEnterprises.get(i).get("t23_unit").toString():
			        		exportEnterprises.get(i).get("t18_unit") !=null?exportEnterprises.get(i).get("t18_unit").toString():"");
						//原料3
			        	String t24_name = exportEnterprises.get(i).get("t24_name") !=null?exportEnterprises.get(i).get("t24_name").toString():"";
			        	if(!"".equals(t24_name)){
			        		list.add(t24_name);
			        		String t24_solvent_type = exportEnterprises.get(i).get("t24_solvent_type") !=null?exportEnterprises.get(i).get("t24_solvent_type").toString():"";
			        		if("".equals(t24_solvent_type)){
			        			list.add("");
			        		}else if("oil".equals(t24_solvent_type)){
			        			list.add("油性");
			        		}else if("water".equals(t24_solvent_type)){
			        			list.add("水性");
			        		}
			        	}else{
			        		list.add(exportEnterprises.get(i).get("t19_name") !=null?exportEnterprises.get(i).get("t19_name").toString():"");
			        		list.add("");
			        	}
			        	list.add("");
			        	list.add("");
			        	list.add(exportEnterprises.get(i).get("t24_consumption") !=null&& !"".equals(exportEnterprises.get(i).get("t24_consumption").toString())?exportEnterprises.get(i).get("t24_consumption").toString():
			        		exportEnterprises.get(i).get("t19_consumption") !=null?exportEnterprises.get(i).get("t19_consumption").toString():"");
			        	list.add(exportEnterprises.get(i).get("t24_unit") !=null&& !"".equals(exportEnterprises.get(i).get("t24_unit").toString())?exportEnterprises.get(i).get("t24_unit").toString():
			        		exportEnterprises.get(i).get("t19_unit") !=null?exportEnterprises.get(i).get("t19_unit").toString():"");
						//原料4
			        	String t25_name = exportEnterprises.get(i).get("t25_name") !=null?exportEnterprises.get(i).get("t25_name").toString():"";
			        	if(!"".equals(t25_name)){
			        		list.add(t25_name);
			        		String t25_solvent_type = exportEnterprises.get(i).get("t25_solvent_type") !=null?exportEnterprises.get(i).get("t25_solvent_type").toString():"";
			        		if("".equals(t25_solvent_type)){
			        			list.add("");
			        		}else if("oil".equals(t25_solvent_type)){
			        			list.add("油性");
			        		}else if("water".equals(t25_solvent_type)){
			        			list.add("水性");
			        		}
			        	}else{
			        		list.add(exportEnterprises.get(i).get("t20_name") !=null?exportEnterprises.get(i).get("t20_name").toString():"");
			        		list.add("");
			        	}
			        	list.add("");
			        	list.add("");
			        	list.add(exportEnterprises.get(i).get("t25_consumption") !=null&& !"".equals(exportEnterprises.get(i).get("t25_consumption").toString())?exportEnterprises.get(i).get("t25_consumption").toString():
			        		exportEnterprises.get(i).get("t20_consumption") !=null?exportEnterprises.get(i).get("t20_consumption").toString():"");
			        	list.add(exportEnterprises.get(i).get("t25_unit") !=null&& !"".equals(exportEnterprises.get(i).get("t25_unit").toString())?exportEnterprises.get(i).get("t25_unit").toString():
			        		exportEnterprises.get(i).get("t20_unit") !=null?exportEnterprises.get(i).get("t20_unit").toString():"");
						//原料5
			        	String t26_name = exportEnterprises.get(i).get("t26_name") !=null?exportEnterprises.get(i).get("t26_name").toString():"";
			        	if(!"".equals(t26_name)){
			        		list.add(t26_name);
			        		String t26_solvent_type = exportEnterprises.get(i).get("t26_solvent_type") !=null?exportEnterprises.get(i).get("t26_solvent_type").toString():"";
			        		if("".equals(t26_solvent_type)){
			        			list.add("");
			        		}else if("oil".equals(t26_solvent_type)){
			        			list.add("油性");
			        		}else if("water".equals(t26_solvent_type)){
			        			list.add("水性");
			        		}
			        	}else{
			        		list.add(exportEnterprises.get(i).get("t21_name") !=null?exportEnterprises.get(i).get("t21_name").toString():"");
			        		list.add("");
			        	}
			        	list.add("");
			        	list.add("");
			        	list.add(exportEnterprises.get(i).get("t26_consumption") !=null&& !"".equals(exportEnterprises.get(i).get("t26_consumption").toString())?exportEnterprises.get(i).get("t26_consumption").toString():
			        		exportEnterprises.get(i).get("t21_consumption") !=null?exportEnterprises.get(i).get("t21_consumption").toString():"");
			        	list.add(exportEnterprises.get(i).get("t26_unit") !=null&& !"".equals(exportEnterprises.get(i).get("t26_unit").toString())?exportEnterprises.get(i).get("t26_unit").toString():
			        		exportEnterprises.get(i).get("t21_unit") !=null?exportEnterprises.get(i).get("t21_unit").toString():"");
						//原料6
			        	list.add("");list.add("");list.add("");list.add("");list.add("");list.add("");
						//燃料信息
			        	list.add(exportEnterprises.get(i).get("t2name") !=null?exportEnterprises.get(i).get("t2name").toString():"");
			        	list.add(exportEnterprises.get(i).get("fuel_consumption") !=null?exportEnterprises.get(i).get("fuel_consumption").toString():"");
			        	list.add(exportEnterprises.get(i).get("fuel_unit") !=null?exportEnterprises.get(i).get("fuel_unit").toString():"");
			        	list.add(exportEnterprises.get(i).get("fuel_sulfur_content") !=null?exportEnterprises.get(i).get("fuel_sulfur_content").toString():"");
			        	list.add("");
			        	list.add("");
			        	//控制措施1
			        	list.add(exportEnterprises.get(i).get("t4name") !=null?exportEnterprises.get(i).get("t4name").toString():
			        		exportEnterprises.get(i).get("t14name") !=null?exportEnterprises.get(i).get("t14name").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4work_hours") !=null?exportEnterprises.get(i).get("t4work_hours").toString():
			        		exportEnterprises.get(i).get("t14work_hours") !=null?exportEnterprises.get(i).get("t14work_hours").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4collect_efficiency") !=null?exportEnterprises.get(i).get("t4collect_efficiency").toString():
			        		exportEnterprises.get(i).get("t14collect_efficiency") !=null?exportEnterprises.get(i).get("t14collect_efficiency").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4so2") !=null?exportEnterprises.get(i).get("t4so2").toString():
			        		exportEnterprises.get(i).get("t14so2") !=null?exportEnterprises.get(i).get("t14so2").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4nox") !=null?exportEnterprises.get(i).get("t4nox").toString():
			        		exportEnterprises.get(i).get("t14nox") !=null?exportEnterprises.get(i).get("t14nox").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4co") !=null?exportEnterprises.get(i).get("t4co").toString():
			        		exportEnterprises.get(i).get("t14co") !=null?exportEnterprises.get(i).get("t14co").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4pm10") !=null?exportEnterprises.get(i).get("t4pm10").toString():
			        		exportEnterprises.get(i).get("t14pm10") !=null?exportEnterprises.get(i).get("t14pm10").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4pm25") !=null?exportEnterprises.get(i).get("t4pm25").toString():
			        		exportEnterprises.get(i).get("t14pm25") !=null?exportEnterprises.get(i).get("t14pm25").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4bc") !=null?exportEnterprises.get(i).get("t4bc").toString():
			        		exportEnterprises.get(i).get("t14bc") !=null?exportEnterprises.get(i).get("t14bc").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4oc") !=null?exportEnterprises.get(i).get("t4oc").toString():
			        		exportEnterprises.get(i).get("t14oc") !=null?exportEnterprises.get(i).get("t14oc").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4voc") !=null?exportEnterprises.get(i).get("t4voc").toString():
			        		exportEnterprises.get(i).get("t14voc") !=null?exportEnterprises.get(i).get("t14voc").toString():"");
			        	list.add(exportEnterprises.get(i).get("t4nh3") !=null?exportEnterprises.get(i).get("t4nh3").toString():
			        		exportEnterprises.get(i).get("t14nh3") !=null?exportEnterprises.get(i).get("t14nh3").toString():"");
			        	//控制措施2
			        	list.add(exportEnterprises.get(i).get("t5name") !=null?exportEnterprises.get(i).get("t5name").toString():
			        		exportEnterprises.get(i).get("t15name") !=null?exportEnterprises.get(i).get("t15name").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5work_hours") !=null?exportEnterprises.get(i).get("t5work_hours").toString():
			        		exportEnterprises.get(i).get("t15work_hours") !=null?exportEnterprises.get(i).get("t15work_hours").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5collect_efficiency") !=null?exportEnterprises.get(i).get("t5collect_efficiency").toString():
			        		exportEnterprises.get(i).get("t15collect_efficiency") !=null?exportEnterprises.get(i).get("t15collect_efficiency").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5so2") !=null?exportEnterprises.get(i).get("t5so2").toString():
			        		exportEnterprises.get(i).get("t15so2") !=null?exportEnterprises.get(i).get("t15so2").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5nox") !=null?exportEnterprises.get(i).get("t5nox").toString():
			        		exportEnterprises.get(i).get("t15nox") !=null?exportEnterprises.get(i).get("t15nox").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5co") !=null?exportEnterprises.get(i).get("t5co").toString():
			        		exportEnterprises.get(i).get("t15co") !=null?exportEnterprises.get(i).get("t15co").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5pm10") !=null?exportEnterprises.get(i).get("t5pm10").toString():
			        		exportEnterprises.get(i).get("t15pm10") !=null?exportEnterprises.get(i).get("t15pm10").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5pm25") !=null?exportEnterprises.get(i).get("t5pm25").toString():
			        		exportEnterprises.get(i).get("t15pm25") !=null?exportEnterprises.get(i).get("t15pm25").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5bc") !=null?exportEnterprises.get(i).get("t5bc").toString():
			        		exportEnterprises.get(i).get("t15bc") !=null?exportEnterprises.get(i).get("t15bc").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5oc") !=null?exportEnterprises.get(i).get("t5oc").toString():
			        		exportEnterprises.get(i).get("t15oc") !=null?exportEnterprises.get(i).get("t15oc").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5voc") !=null?exportEnterprises.get(i).get("t5voc").toString():
			        		exportEnterprises.get(i).get("t15voc") !=null?exportEnterprises.get(i).get("t15voc").toString():"");
			        	list.add(exportEnterprises.get(i).get("t5nh3") !=null?exportEnterprises.get(i).get("t5nh3").toString():
			        		exportEnterprises.get(i).get("t15nh3") !=null?exportEnterprises.get(i).get("t15nh3").toString():"");
			        	//控制措施3
			        	list.add(exportEnterprises.get(i).get("t6name") !=null?exportEnterprises.get(i).get("t6name").toString():
			        		exportEnterprises.get(i).get("t16name") !=null?exportEnterprises.get(i).get("t16name").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6work_hours") !=null?exportEnterprises.get(i).get("t6work_hours").toString():
			        		exportEnterprises.get(i).get("t16work_hours") !=null?exportEnterprises.get(i).get("t16work_hours").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6collect_efficiency") !=null?exportEnterprises.get(i).get("t6collect_efficiency").toString():
			        		exportEnterprises.get(i).get("t16collect_efficiency") !=null?exportEnterprises.get(i).get("t16collect_efficiency").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6so2") !=null?exportEnterprises.get(i).get("t6so2").toString():
			        		exportEnterprises.get(i).get("t16so2") !=null?exportEnterprises.get(i).get("t16so2").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6nox") !=null?exportEnterprises.get(i).get("t6nox").toString():
			        		exportEnterprises.get(i).get("t16nox") !=null?exportEnterprises.get(i).get("t16nox").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6co") !=null?exportEnterprises.get(i).get("t6co").toString():
			        		exportEnterprises.get(i).get("t16co") !=null?exportEnterprises.get(i).get("t16co").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6pm10") !=null?exportEnterprises.get(i).get("t6pm10").toString():
			        		exportEnterprises.get(i).get("t16pm10") !=null?exportEnterprises.get(i).get("t16pm10").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6pm25") !=null?exportEnterprises.get(i).get("t6pm25").toString():
			        		exportEnterprises.get(i).get("t16pm25") !=null?exportEnterprises.get(i).get("t16pm25").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6bc") !=null?exportEnterprises.get(i).get("t6bc").toString():
			        		exportEnterprises.get(i).get("t16bc") !=null?exportEnterprises.get(i).get("t16bc").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6oc") !=null?exportEnterprises.get(i).get("t6oc").toString():
			        		exportEnterprises.get(i).get("t16oc") !=null?exportEnterprises.get(i).get("t16oc").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6voc") !=null?exportEnterprises.get(i).get("t6voc").toString():
			        		exportEnterprises.get(i).get("t16voc") !=null?exportEnterprises.get(i).get("t16voc").toString():"");
			        	list.add(exportEnterprises.get(i).get("t6nh3") !=null?exportEnterprises.get(i).get("t6nh3").toString():
			        		exportEnterprises.get(i).get("t16nh3") !=null?exportEnterprises.get(i).get("t16nh3").toString():"");
						//最终估算使用去除率
			        	list.add("");list.add("");list.add("");list.add("");list.add("");
			        	list.add("");list.add("");list.add("");list.add("");
						
			        	map.put(i+1, list);
			        	
			        }
					ExportExcel.exportExcel("工业生产过程表", oneheaders,headers,headerunits, map, response.getOutputStream());

				}
			}
			
			
			
		
			
			
																									
																		

			
			
			
			
			
			
//			List<Enterprise> enterprises = enterpriseDao.findAll();
			// 查询项目下所有用户填报企业
//			List<Enterprise> enterprises = enterpriseService.findALLByAccount(accounts);
//			if (enterprises != null) {
//				System.out.println(enterprises.size());
//				// List<SystemUser> systemUsers = systemUserService.findAll();
//				enterpriseService.exportEnterprise(response.getOutputStream(), systemUsers, enterprises);
//			}
			// return Message.success();
		} catch (Exception e) {
			e.printStackTrace();
			// return Message.error("导出错误", "");
		}

	}
	
	
}

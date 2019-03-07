package com.online.util;

import java.text.NumberFormat;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.online.entity.online.Enterprise.EnterpriseType;

public class Constants {
	
	public static final String WEBSOCKET_USERNAME="webksocket-user";
	public static final String SESSION_USERNAME="session-user";
	
	public static final String EXPORT_POLLUTESOURCE_BOILER="boiler";				//锅炉
	public static final String EXPORT_POLLUTESOURCE_OPENYARD="openYard";			//露天
	public static final String EXPORT_POLLUTESOURCE_OILTANK="oilTank";				//石油
	public static final String EXPORT_POLLUTESOURCE_OTHER="pullutesource-other";	//其他-工段和炉窑
	
	
	public static final String STATISTICS_AREA="area";	//地区
	public static final String STATISTICS_DATE="date";	//日期
	public static final String STATISTICS_USER="user";	//用户
	public static final String STATISTICS_INQUIRER="inquirer";	//填报人
	public static final String STATISTICS_RNTERPERSETYPE="enterpriseType";	//企业类型
	public static final String STATISTICS_NORMALMAIN="normalMain";	//详表大类
	public static final String STATISTICS_NORMALMIDDLE="normalMiddle";	//详表中类
	public static final String STATISTICS_SIMPLEMAIN="simpleMain";	//简表大类
	public static final String STATISTICS_SIMPLEMIDDLE="simpleMiddle";	//简表中类
	
	//任务状态
	public static final String TASK_STATUS_COMPLETE="complete";//完成状态
	public static final String TASK_STATUS_PROCESSED="processed";//已处理
	public static final String TASK_STATUS_ASSIGNED="assigned";//已分配
	public static final String TASK_STATUS_NEW="new";//新任务
	
	//项目企业配置状态
	public static final Integer ENTERPRISE_STATUS_NOTSUBMIT= 1;//未提交
	public static final Integer ENTERPRISE_STATUS_ALREADYSUBMIT= 2;//已提交
	public static final Integer ENTERPRISE_STATUS_APPROVE= 3;//审核通过
	public static final Integer ENTERPRISE_STATUS_NOAPPROVE= 4;//审核不通过
	
	//项目企业配置状态
	public static final String STR_ENTERPRISE_STATUS_NOTSUBMIT= "notSubmit";//未提交
	public static final String STR_ENTERPRISE_STATUS_ALREADYSUBMIT= "alreadySubmit";//已提交
	public static final String STR_ENTERPRISE_STATUS_APPROVE= "approve";//审核通过
	public static final String STR_ENTERPRISE_STATUS_NOAPPROVE= "noApprove";//审核不通过
	
	
	public static final String PDF_USER_PASSWORD= "123456";//pdf密码
	public static final String PDF_PASSWORD= "HongQing.";//pdf密码
	public static final String PDF_WATERCHAR= "未经许可严禁转载";//水印文字
	public static final String PDF_FIELD_NUM= "pdf_field_num";//pdf字段行列显示数量
	
	
	
	public static final String TASK_DISTRIBUTE= "每日任务分配员";//分配员
	public static final String TASK_DISPOSE= "每日任务处理员";//处理员
	public static final String TASK_CHECK= "每日任务审核员";//审核员
	
	public static final String TASK_PASS= "pass";//审核通过
	
	public static final String TASK_TYPE_PRODUCT= "产品";//审核员
	public static final String TASK_TYPE_RAWMATERIALS= "原辅料";//审核员
	
	public static final String TASK_LIST_Completion= "Completion"; //完整
	
	public static final String SCAN_rawMaterials= "rawMaterials"; //原辅料
	public static final String SCAN_product= "product"; //产品

	
	public static final String FIELD_productionInformation= "详表基本信息"; //详表生产信息
	public static final String FIELD_detail_exhaustionhole= "详表排放口"; 
	public static final String FIELD_detail_governancemeasures= "详表污染治理措施"; 
	public static final String FIELD_detail_powerplant= "详表自备发电机组"; 
	public static final String FIELD_detail_boilerinformation= "详表锅炉信息"; 
	public static final String FIELD_detail_kiln= "详表炉窑"; 
	public static final String FIELD_detail_section= "详表产品工段"; 
	public static final String FIELD_detail_openyard= "详表露天堆场"; 
	public static final String FIELD_detail_oiltank= "详表有机液体储罐"; 
	public static final String FIELD_detail_loadinginformation= "详表有机液体装载"; 
	public static final String FIELD_detail_equipmentleaked= "详表设备泄漏"; 
	public static final String FIELD_detail_monthlyinformation= "详表分月信息"; 
	public static final String FIELD_detail_steel= "详表钢铁生产信息"; 
	public static final String FIELD_detail_transportcar= "详表大宗物料运输车"; 
	public static final String FIELD_detail_nonroad= "详表非道路机械信息"; 
	public static final String FIELD_detail_gasstove= "详表煤气发生炉信息"; 
	public static final String FIELD_detail_sewage= "详表污水处理池信息"; 
	public static final String FIELD_detail_fuel= "详表燃料信息"; 
	public static final String FIELD_detail_rawmaterials= "详表原辅料信息"; 
	
	
	public static final String FIELD_simplebaseinformation= "简表基本信息"; //简表生产信息
	public static final String FIELD_simple_section= "简表生产工段"; 
	public static final String FIELD_simple_rawmaterials= "简表有机溶剂"; 
	public static final String FIELD_simple_boilerinformation= "简表锅炉信息"; 
	public static final String FIELD_simple_openyard= "简表露天堆场"; 
	public static final String FIELD_simple_oiltank= "简表储罐信息"; 
	
	public static final String FIELD_single_boilerinformation= "单独锅炉锅炉信息"; 
	public static final String FIELD_single_boilerinfuel= "单独锅炉锅炉燃料"; 
	
	
	public static final String FIELD_constructionsite_base= "施工工地基本信息"; 
	public static final String FIELD_constructionperiod= "施工工地施工周期"; 
	public static final String FIELD_constructionconsumption= "施工工地工程机械"; 
	public static final String FIELD_constructionmember= "施工工地机动车数量"; 
	public static final String FIELD_constructionyard= "施工工地露天堆场"; 
	
	public static final String FIELD_petrolstationbase= "加油站基本信息"; 
	public static final String FIELD_petrolstoragetype= "加油站储罐类型"; 
	public static final String FIELD_petrolsales= "加油站分月经销信息"; 
	
	
	public static final String FIELD_dryclearbase= "干洗基本信息"; 
	public static final String FIELD_dryclearsolvent= "干洗年有机溶剂使用"; 
	
	public static final String FIELD_breakdownservicebase= "汽修基本信息"; 
	public static final String FIELD_breakdownservicesolvent= "汽修有机溶剂使用情况";
	
	public static final String FIELD_cateringbase= "餐饮基本信息"; 
	public static final String FIELD_cateringfuel= "餐饮燃料和油烟"; 
	
	public static final String FIELD_beastsbirds= "畜禽养殖信息"; 
	
	public static final String FIELD_general_baseinformation= "普查表生产信息"; 
	public static final String FIELD_general_section= "普查表工段"; 
	public static final String FIELD_general_kiln= "普查表炉窑信息"; 
	public static final String FIELD_general_rawmaterials= "普查表有机溶剂"; 
	public static final String FIELD_general_boilerinformation= "普查表锅炉信息"; 
	public static final String FIELD_general_openyard= "普查表露天堆场"; 
	public static final String FIELD_general_oiltank= "普查表储罐信息"; 
	public static final String FIELD_general_transportcar= "普查表运输车"; 
	public static final String FIELD_general_nonroad= "普查表非道路机械"; 
	
	
	//名录库状态
	public static final String ENTERPRISE_DICTIONARY_STATUS_NEW= "newDictionary"; //新建名录库
	public static final String ENTERPRISE_DICTIONARY_STATUS_CREATETASK= "createTaskSucceed";//创建任务成功
	public static final String ENTERPRISE_DICTIONARY_STATUS_CHECKPENDING= "checkpending";//待审核
	public static final String ENTERPRISE_DICTIONARY_STATUS_CHECKNOT= "checknot";//审核不通过
	public static final String ENTERPRISE_DICTIONARY_STATUS_VERIFIED= "verified";//审核通过
	
	//企业下生成的pscc任务完成状态
	public static final String ENTERPRISE_PSCC_STATUS_FINISH= "finish";//完成
	public static final String ENTERPRISE_PSCC_STATUS_UNFINISH= "unFinish";//未完成
	
	//产品行业
	public static final String ENTERPRISE_PRODUCT_INDUSTRY_OTHER= "other";//其他
	
	
	//是否保存基本信息
	public static final String BASEINFO_ISSAVE_TRUE= "true";
	
	public static final HashMap<String, String > maps = new HashMap<String, String>(){{  
	      put("SIMPLE","简表");      
	      put("NORMAL","详表");      
	      put("SINGLE_BOILER","单独锅炉（非工业）");      
	      put("CONSTRUCTION_SITE","施工工地");      
	      put("PETROL_STATION","加油站/加气站/储油库");      
	      put("DRY_CLEAR","干洗企业");      
	      put("BREAKDOWN_SERVICE","汽修");      
	      put("BEASTS_BIRDS","畜禽养殖");      
	      put("CATERING","餐饮");      
	      put("GENERAL","普查表");      
	}};
	
	//分月信息类型
	/**发电机组*/
	public static final String MONTH_TYPE_POWER= "powerPlantId";
	/**锅炉*/
	public static final String MONTH_TYPE_BOILER= "boilerId";	
	/**煤气发生炉*/
	public static final String MONTH_TYPE_GASSTOVE= "gasstoveId";
	/**炉窑*/
	public static final String MONTH_TYPE_KILN= "kilnId";
	/**工段生产线*/
	public static final String MONTH_TYPE_SECTION= "sectionId";	
	/**原辅料*/
	public static final String MONTH_TYPE_RAWMATERIALS= "rawMaterialsId";
	
	/**审核员子成员*/
	public static final String SOLE_ASSESSOR= "任务审核员分部";
	
	public static final String ADMIN_SOLE_ASSESSOR= "企业任务审核员";
	
	public static EnterpriseType getEnterpriseType(String str){
		switch (str) {
		case "NORMAL":
			return EnterpriseType.NORMAL;
		case "SIMPLE":
			return EnterpriseType.SIMPLE;
		case "SINGLE_BOILER":
			return EnterpriseType.SINGLE_BOILER;
		case "CONSTRUCTION_SITE":
			return EnterpriseType.CONSTRUCTION_SITE;
		case "PETROL_STATION":
			return EnterpriseType.PETROL_STATION;
		case "DRY_CLEAR":
			return EnterpriseType.DRY_CLEAR;
		case "BREAKDOWN_SERVICE":
			return EnterpriseType.BREAKDOWN_SERVICE;
		case "BEASTS_BIRDS":
			return EnterpriseType.BEASTS_BIRDS;
		case "CATERING":
			return EnterpriseType.CATERING;
		case "GENERAL":
			return EnterpriseType.GENERAL;

		default:
			return EnterpriseType.NORMAL;
		}
		
	}
	
	
	
	/**
	 * 显示转换
	 * @author 郑有权
	 * @date 创建时间：2018年3月21日 上午11:01:57 
	 * @param str
	 * @return
	 */
	public static String convert(String header,String value){
		
		switch (value) {
		case "false":
			return "否";
		case "true":
			return "是";
		case "organic":
			return "有机溶剂";
		case "inorganic":
			return "无机溶剂";
		case "oil":
			return "油性";
		case "water":
			return "水性";
		case "section":
			return "生产线";
		case "boiler":
			return "锅炉";
		case "kiln":
			return "炉窑";
		case "powerPlant":
			return "自备发电机组";
		case "steel":
			return "钢铁";
		case "aluminum":
			return "铝行业";
		case "cement":
			return "水泥行业";
		case "glass":
			return "平板玻璃";
		case "tile":
			return "砖瓦";
		case "ceram":
			return "陶瓷";
		case "fireproofing":
			return "耐火材料";
		case "graphite":
			return "石墨碳素";
		case "car":
			return "汽车行业";
		case "electroplate":
			return "电镀";
		case "printing":
			return "印刷";
		case "rubber":
			return "橡胶";
		case "plastic":
			return "塑料";
		case "surface":
			return "表面处理行业";
		case "other":
			return "其他行业";
		case "alreadySubmit":
			return "已提交";
		case "NORMAL":
			return maps.get("NORMAL");
		case "SIMPLE":
			return maps.get("SIMPLE");
		case "SINGLE_BOILER":
			return maps.get("SINGLE_BOILER");
		case "CONSTRUCTION_SITE":
			return maps.get("CONSTRUCTION_SITE");
		case "PETROL_STATION":
			return maps.get("PETROL_STATION");
		case "DRY_CLEAR":
			return maps.get("DRY_CLEAR");
		case "BREAKDOWN_SERVICE":
			return maps.get("BREAKDOWN_SERVICE");
		case "BEASTS_BIRDS":
			return maps.get("BEASTS_BIRDS");
		case "CATERING":
			return maps.get("CATERING");
		case "GENERAL":
			return maps.get("GENERAL");
		case "product":
			return "产品";
		case "fuel":
			return "燃料";
		case "rawMaterials":
			return "原辅料";
		}
		
		switch (header) {
		case "全年生产总值":
		case "全年生产总值(万元)":
		case "年生产成本（万元）":
		case "年燃气消耗量(万立方米)":
		case "全年用电量":
			return StringUtils.isNotBlank(value)?doubleFormat(Double.valueOf(value)):value;
		case "投运时间":
		case "关停时间":
		case "停运时间":
		case "整改时间":
		case "装置安装时间":
		case "改扩建时间":
		case "开业时间":
			return StringUtils.isNotBlank(value)&&value.length()>7?value.substring(0, 7):value;
		case "开工日期":
		case "竣工日期":
			return StringUtils.isNotBlank(value)&&value.length()>10?value.substring(0, 10):value;
		case "生产时段-开始":
		case "生产时段-结束":
		case "日运行时间-开始":
		case "日运行时间-结束":
			return StringUtils.isNotBlank(value)&&value.length()>18?value.substring(11, 16):value;
		case "经度":
		case "纬度":
			return StringUtils.isNotBlank(value)?doubleFormat(Double.valueOf(value)):value;
		
		
		}
		return value;
		
	}
	
	public static String doubleFormat(Double price){
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(6);
        return nf.format(price);


	}
	//住建委-房屋建筑工地信息
	public final static String[] ZJWFangWuJianZhuGongDiColConfighead = {"行政区","项目名称"
			,"工程详细地址","中心经度","中心纬度","占地面积（平方米）","建筑面积（平方米）","设计开挖土方量（立方米）"
			,"已开挖土方量（立方米）","开工时间（年/月/日）","竣工时间（年/月/日）","扬尘控制措施","建筑涂料使用量（吨）"};	
	
	//住建委-房屋建筑施工和竣工面积信息（面）
	public final static String[] ZJWFangWuJianZhuMianJiColConfighead = {"县（市、区）","建筑施工面积（平方米）","建筑竣工面积（平方米）"};	
	
	//住建委-混凝土搅拌站信息
	public final static String[] ZJWJiaoBanZhanColConfighead = {"行政区","搅拌站名称"
			,"详细地址","中心经度","中心纬度","产能（立方米/月）","占地面积（平方米）","料场抑尘措施"
			,"上料口抑尘措施","粉料筒仓上部是否配套高效布袋除尘器"};	
	//住建委-道路施工材料站信息
	public final static String[] ZJWCaiLiaoZhanColConfighead = {"行政区","材料站名称"
			,"详细地址","中心经度","中心纬度","产能（立方米/月）","占地面积（平方米）","料场抑尘措施"
			,"上料口抑尘措施","粉料筒仓上部是否配套高效布袋除尘器"};	
	//住建委-市政项目建筑工地信息
	public final static String[] ZJWShiZhengJianZhuGongDiColConfighead = {"行政区","项目名称"
			,"工程详细地址","中心经度","中心纬度","占地面积（平方米）","建筑面积（平方米）","设计开挖土方量（立方米）"
			,"已开挖土方量（立方米）","开工时间（年/月/日）","竣工时间（年/月/日）","扬尘控制措施","建筑涂料使用量（吨）"};	
	//城管局-餐饮行业信息
	public final static String[] CGJCanYinColConfighead = {"行政区","详细地址","餐企名称","是否安装油烟净化装置","油烟净化效率"};	
	//城管局-烧烤店统计信息
	public final static String[] CGJShaoKaoDianColConfighead = {"烧烤店名称","行政区","详细地址"
			,"中心经度","中心纬度","联系人","联系电话","是否露天烧烤","每年经营天数（天）","每日经营时间（时）","燃料类型","燃料消耗量","燃料单位"};	
	
	//交通局-客运站/货运物流中心
	public final static String[] JTJKeYunZhanColConfighead = {"行政区域","公司名称","地址"
			,"占地面积","中心经度","中心纬度","全年进/出车辆次数","每天进/出车辆次数","车辆类型","燃油类型","车辆平均停留时间"};	
	
	//食药监局-餐饮行业信息
	public final static String[] SYJJCanYinColConfighead = {"行政区","详细地址","餐企名称"
			,"中心经度","中心纬度","经营场所面积（平方米）","固定灶头数（个）","燃料类型"
			,"年燃料消耗量（吨/立方米/度）","单个灶头平均排风量（立方米/小时）"};
	
	//商务局-储油库信息
	public final static String[] SWJChuYouKuColConfighead = {"储油库名称","行政区","详细地址"
			,"中心经度","中心纬度","油品类型","年总储量（吨）","储罐类型"
			,"储存周期（天）","装卸车过程是否已安装油气回收"};	
	//商务局-加油站信息
	public final static String[] SWJJiaYouZhanColConfighead = {"加油站名称","行政区","详细地址"
			,"中心经度","中心纬度","油品类型","年销售量（吨）","油品标准"
			,"地下储罐容量（立方米）","补给频率（次/月）","是否安装油气回收装置","油气回收率（%）"};	
	//农业委-畜禽养殖信息
	public final static String[] NYWXuQinYangZhiColConfighead = {"区县","乡镇","养殖场名称"
			,"畜禽类型","养殖方式","平均饲养周期（天）","总饲养量（万只）","总出栏数（万只）"
			,"年末存栏量（万只）"};	
	//工商局-餐饮企业工商登记信息
	public final static String[] GSJCanYinColConfighead = {"餐企名称","法定代表人/联系人","联系电话"
			,"行政区","详细地址","经营范围"};	
	//工商局-汽修企业工商登记信息
	public final static String[] GSJQiXiuColConfighead = {"汽修企业名称","法定代表人/联系人","联系电话"
			,"行政区","详细地址","油漆使用量/吨"};	
	//工商局-干洗企业工商登记信息
	public final static String[] GSJGanxiColConfighead = {"干洗企业名称","法定代表人/联系人","联系电话"
			,"行政区","详细地址","干洗剂用量/吨"};	
	//经信委-工业企业名单
	public final static String[] JXWGongYeColConfighead = {"工业企业名称","联系人","联系电话"
			,"行政区","详细地址","用电量信息","备注"};	
	//供电局-企业用电信息
	public final static String[] GDJQiYeYongDianColConfighead = {"企业名称"
			,"行政区","详细地址","用电户号","年份","年用电量（万千瓦时）"};	
	//供电局-企业用电信息
	public final static String[] GDJFaDianMeiYueColConfighead = {"电厂名称"
			,"一月（千瓦时）","二月（千瓦时）","三月（千瓦时）","四月（千瓦时）","五月（千瓦时）"	
			,"六月（千瓦时）","七月（千瓦时）","八月（千瓦时）","九月（千瓦时）","十月（千瓦时）"	
			,"十一月（千瓦时）","十二月（千瓦时）"};	
	//质监局-锅炉信息表
	public final static String[] ZJJGuoLuColConfighead = {"所在地"
			,"行政区域","企业名称","详细地址","中心经度","中心纬度"	
			,"锅炉型号","燃料类型","蒸吨数","上次检测日期","下次检测日期"};	
	
	//燃气公司-工业企业燃气消耗量信息
	public final static String[] RQGSGongYeXiaoHaoColConfighead = {"工业企业名称"
			,"行政区","详细地址","前两年","前年","去年"};	
	//燃气公司-工业企业燃气消耗2017年分月统计表
	public final static String[] RQGSGongYeXiaoHaoMeiYueColConfighead = {"工业企业名称"
			,"一月（万立方米）","二月（万立方米）","三月（万立方米）","四月（万立方米）","五月（万立方米）"	
			,"六月（万立方米）","七月（万立方米）","八月（万立方米）","九月（万立方米）","十月（万立方米）"	
			,"十一月（万立方米）","十二月（万立方米）"};	
	
	
	/**
	 * 房屋建筑工地信息
	 */
	public static final String  PHOTO_TYPE_ZJWFANGWUJIANZHUGONGDI = "ZJWFangWuJianZhuGongDi"; 
	/**
	 * 房屋建筑施工和竣工面积信息（面）
	 */
	public static final String  PHOTO_TYPE_ZJWFANGWUJIANZHUMIANJI = "ZJWFangWuJianZhuMianJi"; 
	/**
	 * 混凝土搅拌站信息
	 */
	public static final String  PHOTO_TYPE_ZJWJIAOBANZHAN = "ZJWJiaoBanZhan"; 
	/**
	 * 道路施工材料站信息
	 */
	public static final String  PHOTO_TYPE_ZJWCAILIAOZHAN = "ZJWCaiLiaoZhan"; 
	/**
	 * 工程机械信息（面）
	 */
	public static final String  PHOTO_TYPE_ZJWGONGCHENGJIXIE = "ZJWGongChengJiXie"; 
	/**
	 * 市政项目建筑工地信息
	 */
	public static final String  PHOTO_TYPE_ZJWSHIZHENGJIANZHUGONGDI = "ZJWShiZhengJianZhuGongDi"; 
	/**
	 * 市政项目建筑施工和竣工面积情况统计（面）
	 */
	public static final String  PHOTO_TYPE_ZJWSHIZHENGJIANZHUMIANJI = "ZJWShiZhengJianZhuMianJi"; 
	/**
	 * 城市道路保洁信息（面）
	 */
	public static final String  PHOTO_TYPE_CGJDAOLUBAOJIE = "CGJDaoLuBaoJie"; 
	/**
	 * 城市道路保洁信息机械化（面）
	 */
	public static final String  PHOTO_TYPE_CGJDAOLUBAOJIEJIXIE = "CGJDaoLuBaoJieJiXie"; 
	/**
	 * 渣土车信息（面）
	 */
	public static final String  PHOTO_TYPE_CGJZHATUCHE = "CGJZhaTuChe"; 
	/**
	 * 餐饮行业信息
	 */
	public static final String  PHOTO_TYPE_CGJCANYIN = "CGJCanYin"; 
	/**
	 * 烧烤店统计信息
	 */
	public static final String  PHOTO_TYPE_CGJSHAOKAODIAN = "CGJShaoKaoDian"; 
	/**
	 * 道路类型及数量（面）
	 */
	public static final String  PHOTO_TYPE_JTJDAOLULEIXING = "JTJDaoLuLeiXing"; 
	/**
	 * 客运站/货运站中心信息
	 */
	public static final String  PHOTO_TYPE_JTJKEYUNZHAN = "JTJKeYunZhan"; 
	/**
	 * 公交车保有量（面）
	 */
	public static final String  PHOTO_TYPE_JTJGONGJIAOCHEBAOYOULIANG = "JTJGongJiaoCheBaoYouLiang"; 
	/**
	 * 公交车重信息（面）
	 */
	public static final String  PHOTO_TYPE_JTJGONGJIAOCHECHEZHONG = "JTJGongJiaoCheCheZhong"; 
	/**
	 * 餐饮行业信息
	 */
	public static final String  PHOTO_TYPE_SYJJCANYIN = "SYJJCanYin"; 
	/**
	 * 储油库信息
	 */
	public static final String  PHOTO_TYPE_SWJCHUYOUKU = "SWJChuYouKu"; 
	/**
	 * 加油站信息
	 */
	public static final String  PHOTO_TYPE_SWJJIAYOUZHAN = "SWJJiaYouZhan"; 
	/**
	 * 居民能源统计（面）
	 */
	public static final String  PHOTO_TYPE_FGWJUMINNENGYUAN = "FGWJuMinNengYuan"; 
	/**
	 * 机动车保有量（面）
	 */
	public static final String  PHOTO_TYPE_JJZDBAOYOULIANG = "JJZDBaoYouLiang"; 
	/**
	 * 车流量信息（面）
	 */
	public static final String  PHOTO_TYPE_JJZDCHELIULIANG = "JJZDCheLiuLiang"; 
	/**
	 * 车重信息（面）
	 */
	public static final String  PHOTO_TYPE_JJZDCHEZHONG = "JJZDCheZhong"; 
	/**
	 * 畜禽养殖信息
	 */
	public static final String  PHOTO_TYPE_NYWXUQINYANGZHI = "NYWXuQinYangZhi"; 
	/**
	 * 农业机械信息（面）
	 */
	public static final String  PHOTO_TYPE_NYWNONGYEJIXIE = "NYWNongYeJiXie"; 
	/**
	 * 化肥与农药施用信息（面）
	 */
	public static final String  PHOTO_TYPE_NYWHUAFEIYUNONGYAO = "NYWHuaFeiYuNongYao"; 
	/**
	 * 耕地信息（面）
	 */
	public static final String  PHOTO_TYPE_NYWGENGDI = "NYWGengDi"; 
	/**
	 * 农作物产量与秸秆利用信息（面）
	 */
	public static final String  PHOTO_TYPE_NYWJIEGAN = "NYWJieGan"; 
	/**
	 * 全市渔船信息（面）
	 */
	public static final String  PHOTO_TYPE_NYWYUCHUAN = "NYWYuChuan"; 
	/**
	 * 渔船每月活动水平信息（面）
	 */
	public static final String  PHOTO_TYPE_NYWYUCHUANMEIYUE = "NYWYuChuanMeiYue"; 
	/**
	 * 港口信息
	 */
	public static final String  PHOTO_TYPE_HSJGANGKOU = "HSJGangKou"; 
	/**
	 * 客货运输船舶活动信息
	 */
	public static final String  PHOTO_TYPE_HSJKEHUOCHUANPO = "HSJKeHuoChuanPo"; 
	/**
	 * 客货运输船舶每月活动水平信息
	 */
	public static final String  PHOTO_TYPE_HSJKEHUOCHUANPOMEIYUE = "HSJKeHuoChuanPoMeiYue"; 
	/**
	 * 港口机械信息
	 */
	public static final String  PHOTO_TYPE_HSJGANGKOUJIXIE = "HSJGangKouJiXie"; 
	/**
	 * 餐饮企业工商登记信息
	 */
	public static final String  PHOTO_TYPE_GSJCANYIN = "GSJCanYin"; 
	/**
	 * 汽修企业工商登记信息
	 */
	public static final String  PHOTO_TYPE_GSJQIXIU = "GSJQiXiu"; 
	/**
	 * 干洗企业工商登记信息
	 */
	public static final String  PHOTO_TYPE_GSJGANXI = "GSJGanxi"; 
	/**
	 * 工业企业名单
	 */
	public static final String  PHOTO_TYPE_JXWGONGYE = "JXWGongYe"; 
	/**
	 * 企业用电信息
	 */
	public static final String  PHOTO_TYPE_GDJQIYEYONGDIAN = "GDJQiYeYongDian"; 
	/**
	 * 电厂发电分月统计信息
	 */
	public static final String  PHOTO_TYPE_GDJFADIANMEIYUE = "GDJFaDianMeiYue"; 
	/**
	 * 飞机起降架次
	 */
	public static final String  PHOTO_TYPE_MHJQILUO = "MHJQiLuo"; 
	/**
	 * 机场车辆信息
	 */
	public static final String  PHOTO_TYPE_MHJJICHANGCHELIANG = "MHJJiChangCheLiang"; 
	/**
	 * 锅炉信息表
	 */
	public static final String  PHOTO_TYPE_ZJJGUOLU = "ZJJGuoLu"; 
	/**
	 * 工业企业燃气消耗量信息
	 */
	public static final String  PHOTO_TYPE_RQGSGONGYEXIAOHAO = "RQGSGongYeXiaoHao"; 
	/**
	 * 工业企业燃气消耗年分月统计表
	 */
	public static final String  PHOTO_TYPE_RQGSGONGYEXIAOHAOMEIYUE = "RQGSGongYeXiaoHaoMeiYue"; 
	/**
	 * 民用燃气消耗量信息（面）
	 */
	public static final String  PHOTO_TYPE_RQGSMINYONGXIAOHAO = "RQGSMinYongXiaoHao"; 
	/**
	 * 民用燃气消耗年分月统计表（面）
	 */
	public static final String  PHOTO_TYPE_RQGSMINYONGXIAOHAOMEIYUE = "RQGSMinYongXiaoHaoMeiYue"; 
	
}

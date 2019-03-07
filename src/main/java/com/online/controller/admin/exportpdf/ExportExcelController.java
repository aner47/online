package com.online.controller.admin.exportpdf;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.validator.internal.util.privilegedactions.GetResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.pdf.PdfPTable;
import com.online.controller.base.BaseController;
import com.online.entity.Area;
import com.online.entity.online.Enterprise;
import com.online.entity.online.Project;
import com.online.entity.online.SingleBoilerFuel;
import com.online.entity.online.SingleBoilerInformation;
import com.online.entity.online.embeddable.Address;
import com.online.exportpdf.DataBean;
import com.online.exportpdf.PDFTempletExportService;
import com.online.exportpdf.PDFTempletTicket;
import com.online.service.EnterpriseService;
import com.online.util.Principal;
import com.online.util.SpringUtils;

/** 
 * @author 作者名 
 * @email  邮箱名
 * @time   2017年4月26日 下午2:15:47 
 */
@Controller("exportpdfController")
@RequestMapping("/admin/exportpdf")
public class ExportExcelController extends BaseController {
	
	@Autowired
	protected EnterpriseService enterpriseService;

	@Autowired
	private PDFTempletExportService	pdfTempletExportService;
	
	@RequestMapping("/exportexcellist")
	public String list(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		return "/admin/exportpdf/exportexcelList";
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
			 String filename  = new String(("测试").getBytes("GB2312"),"iso8859-1") +"_"+System.currentTimeMillis()+".zip";;
			 	
			 response.addHeader("Content-Disposition", "attachment;filename="+filename);

			 
			 List<Enterprise> enterprises = enterpriseService.findALLByProjectid(5);
			 
			 pdfTempletExportService.exprotAllEnterprise(response.getOutputStream(),5,"123465","123456",GetResource.class.getClassLoader().getResource("test.png").getPath(),"测试水印",enterprises,true);
			 
			
		} catch (Exception e) {
			e.printStackTrace();
			// return Message.error("导出错误", "");
		}

	}
	
	public void exportpdf(OutputStream out) throws Exception{
		/*PDFTempletTicket pdfTT = new PDFTempletTicket();

		// 模板路径
		String templatePath = "D:\\template3.pdf";
		// 生成的新文件路径
		

		pdfTT.setTemplatePath(templatePath);

		// 获取企业信息
		Enterprise enterprise = new Enterprise();
		enterprise.setName("郑有权企业名称");
		enterprise.setContacts("123456aaaa联系人呢");
		enterprise.setContactNumber("12345456");
		Address address = new Address();
		Area area = new Area();
		area.setName("北京");
		address.setProvinces(area);
		enterprise.setAddress(address);

		//获取模板对应字段
		Map<String, String> fieldnames = new LinkedHashMap<>();
		
		List<String> names = pdfTT.getFieldName(enterprise);
		for(String name:names){
			fieldnames.put(name, pdfTT.getFieldValue(name, enterprise));
		}
		
//		fieldnames.put("name", enterprise.getName());
//		fieldnames.put("contacts", enterprise.getContacts());
//		fieldnames.put("contactNumber", enterprise.getContactNumber());
		fieldnames.put("city", enterprise.getAddress().getProvinces().getName());

		// 表格信息
		Map<String, String> headers = new LinkedHashMap<>();
		headers.put("no", "锅炉编号");
		headers.put("boilerType", "锅炉类型");
		headers.put("boilerModel", "锅炉型号");
		headers.put("steamTon", "蒸吨数（吨/小时）");
		headers.put("operation", "投运时间（年/月）");
		headers.put("stopData", "关停时间（年/月）");
		headers.put("purpose", "锅炉用途");
		headers.put("fuelType", "燃料类型");
		headers.put("governanceMeasures1", "脱硫");
		headers.put("governanceMeasures2", "脱硝");
		headers.put("governanceMeasures3", "除尘");
		headers.put("height", "排放口高度（米）");
		headers.put("isSurveyCover", "锅炉调查是否覆盖（是/否）");

		List<DataBean> lists = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			SingleBoilerInformation singleBoilerInformation = new SingleBoilerInformation();
			singleBoilerInformation.setNo(i + "");
			singleBoilerInformation.setBoilerType("锅炉类型" + i);
			lists.add(singleBoilerInformation);
		}

		Map<String, String> headers1 = new LinkedHashMap<>();
		headers1.put("no", "锅炉编号");
		headers1.put("fuelType", "燃料类型");
		headers1.put("annualConsumption", "年消耗量");
		headers1.put("unit", "单位");
		headers1.put("january", "一月");
		headers1.put("february", "二月");
		headers1.put("march", "三月");

		List<DataBean> lists1 = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			SingleBoilerFuel singleBoilerFuel = new SingleBoilerFuel();
			singleBoilerFuel.setNo(i + "");
			singleBoilerFuel.setFuelType("燃料类型" + i);
			singleBoilerFuel.setAnnualConsumption(i + 12.1);
			lists1.add(singleBoilerFuel);
		}

		List<PdfPTable> tables =new ArrayList<>();
		tables.add(pdfTT.setTable("锅炉信息",headers,lists));
		tables.add(pdfTT.setTable("燃料消耗信息",headers1,lists1));
		ByteArrayOutputStream outList = pdfTT.createTable(tables);

		pdfTT.fillTemplate(fieldnames, outList,out);*/
	}
	
	
	
}

	package com.online.exportpdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.online.DataAccessException;
import com.online.excelexport.BeanUtils;
import com.online.util.Constants;

/**
 * @author 作者 E-mail: 郑有权
 * @date 创建时间：2017年8月21日 下午1:52:22
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class PDFTempletTicket {

	private String templatePath;
	private String newPDFPath;

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public String getNewPDFPath() {
		return newPDFPath;
	}

	public void setNewPDFPath(String newPDFPath) {
		this.newPDFPath = newPDFPath;
	}

	/**
	 * 利用模板生成pdf
	 * 
	 * @author 郑有权
	 * @date 创建时间：2017年8月25日 下午3:36:48
	 * @param fields
	 *            模板文件数据
	 * @param outList
	 *            自定义表格流
	 * @param out
	 *            最终输出流
	 * @throws IOException
	 * @throws DocumentException
	 * @throws DataAccessException
	 */
	public void fillTemplate(Map<String, String> fields, ByteArrayOutputStream outList, OutputStream out)
			throws IOException, DocumentException, DataAccessException {

		PdfReader reader = null;
		ByteArrayOutputStream bos;
		PdfStamper stamper;
		// try {
		// out = new FileOutputStream(newPDFPath);// 输出流
		if (templatePath == null) {
			throw new DataAccessException("模板不存在");
		}
		try {
			// 读取pdf模板
			reader = new PdfReader(templatePath);
		} catch (IOException e1) {
			throw new DataAccessException("读取不到模板"+templatePath);
		}
		bos = new ByteArrayOutputStream();
		stamper = new PdfStamper(reader, bos);
		/* 使用中文字体 */
		BaseFont bf = BaseFont.createFont("simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
		fontList.add(bf);

		AcroFields form = stamper.getAcroFields();
		form.setSubstitutionFonts(fontList);
		// 模板赋值
		for (Map.Entry<String, String> field : fields.entrySet()) {
			form.setField(field.getKey(), field.getValue());
		}

		stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
		stamper.close();

		List<ByteArrayOutputStream> osList = new ArrayList<>();
		osList.add(bos);
		if (outList != null) {
			osList.add(outList);
		}

		mergePdfFiles(osList, out);

		// } catch (IOException e) {
		// System.out.println("读写文件错误");
		// e.printStackTrace();
		// } catch (DocumentException e) {
		// System.out.println("document错误");
		// e.printStackTrace();
		// }finally{
		try {
			out.close();
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// }

	}

	/**
	 * 创建表格
	 * 
	 * @author 郑有权
	 * @date 创建时间：2017年8月24日 下午2:27:03
	 * @param headers
	 * @param lists
	 * @return
	 */
	public ByteArrayOutputStream createTable(List<PdfPTable> tables) {
		ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
		Document document = new Document(PageSize.A4.rotate(), 30, 30, 10, 10); // 设置横向
		try {

			if (tables != null && tables.size() > 0) {
				PdfWriter.getInstance(document, bos1);
				document.open();
				for (PdfPTable table : tables) {
					document.add(table);
				}
			} else {
				return null;
			}

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (document != null) {
				document.close();
			}
		}

		return bos1;
	}

	/**
	 * 表格填充数据
	 * 
	 * @author 郑有权
	 * @date 创建时间：2017年8月24日 下午2:28:16
	 * @param bean
	 *            表数据
	 * @param headers
	 *            表头
	 * @return
	 * @throws BadElementException
	 */
	public PdfPTable setTable(String tableName, Map<String, String> headers,Map<String, List<String>> mergeHeaders, List<DataBean> bean)
			throws BadElementException {
		
		if("污染治理措施信息".equals(tableName)){
			return governancemeasuresTable(tableName, headers, mergeHeaders, bean);
		}else if("炉窑信息".equals(tableName)){
			return kilnTable(tableName, headers, mergeHeaders, bean);
		}
		else{
			// 创建一个有多少列的表格
			PdfPTable table = new PdfPTable(headers.size());
			// 表格的宽度百分比  
			table.setWidthPercentage(100);  
			//第一行空白
			PdfPCell cell0 = null;
			cell0 = new PdfPCell(new Paragraph("", setFont(12)));
//			cell0.setBackgroundColor(BaseColor.WHITE); // 背景颜色
			cell0.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
			cell0.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
			cell0.setFixedHeight(50); // 设置高度
			cell0.setColspan(headers.size()); // 设置合并列
			cell0.setBorder(0);
			table.addCell(cell0);
			
			//第二行，表格概要
			PdfPCell cell = null;
			cell = new PdfPCell(new Paragraph(tableName, setFont(12)));
			cell.setBackgroundColor(new BaseColor(0,191 ,255)); // 背景颜色
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
			cell.setFixedHeight(25); // 设置高度
			cell.setColspan(headers.size()); // 设置合并列
			table.addCell(cell);
			//第三行，表头
			Paragraph p;
			List<String> listsHead = new ArrayList<>();
			for(Map.Entry<String, List<String>> entry : mergeHeaders.entrySet()){
				String key = entry.getKey();
				List<String> lists = entry.getValue();
				p = new Paragraph(key, setFont(12));
				PdfPCell cell_7_1_1 = new PdfPCell(p);
				if(lists != null){
					listsHead.addAll(lists);
					cell_7_1_1.setRowspan(1);
		            cell_7_1_1.setColspan(lists.size());
				}else{
					cell_7_1_1.setRowspan(2);
		            cell_7_1_1.setColspan(1);
				}
	            cell_7_1_1.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
	            cell_7_1_1.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
	            table.addCell(cell_7_1_1);
			}
			
			if(listsHead.size() >0 ){
				for(String head:listsHead){
					p = new Paragraph(head, setFont(12));
		            PdfPCell cell_7_9_5 = new PdfPCell(p);
		            cell_7_9_5.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		            table.addCell(cell_7_9_5);
				}
			}
				
			
			//第四行，数据
			if (bean != null && bean.size() > 0) {
				for (int i = 0; i < bean.size(); i++) {
					for (Map.Entry<String, String> entry : headers.entrySet()) {
						PdfPCell celli1 = setTableHeader(Constants.convert(entry.getValue(),BeanUtils.getPropertyByName(bean.get(i), entry.getKey())), 8);
						table.addCell(celli1);
					}
				}
			}
			return table;
		}
		
		

	}
	
	
	
	
	public  PdfPTable governancemeasuresTable(String tableName, Map<String, String> headers,Map<String, List<String>> mergeHeaders, List<DataBean> bean) throws BadElementException{
		
		List<String> headerList = new ArrayList<>();
		String headName = "脱硫/脱硝/吸附剂名称";
		String headDosage = "脱硫/脱硝/吸附剂用量（吨）";
		String headFrequency = "脱硫/脱硝/吸附剂/布袋清洗频率（次/年）";
		String headRate = "污染物去除效率（%）";
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			if(entry.getKey().equals("desulfurationType") 	//脱硫剂名称
					|| entry.getKey().equals("denitrationType") //脱硝剂名称
					||entry.getKey().equals("adsorbentName")){	//吸附剂名称
				
			}else if(entry.getKey().equals("desulfurationDosage") //脱硫剂使用量
					|| entry.getKey().equals("denitrationDosage") //脱硝剂使用量
					||entry.getKey().equals("adsorbentDosage")){	//吸附剂用量
				
			}else if(entry.getKey().equals("desulfurationfrequency") //脱硫剂更换频率
					|| entry.getKey().equals("denitrationfrequency") //脱硝剂更换频率
					||entry.getKey().equals("adsorbentfrequency") //吸附剂更换频率
					||entry.getKey().equals("dedustingFrequency") //布袋清洗频率（次/年）
					){ 
				
			}else if(entry.getKey().equals("desulfurationRate") //脱硫率
					|| entry.getKey().equals("denitrationRate") //脱硝率
					||entry.getKey().equals("voc")	//voc去除率
					||entry.getKey().equals("dedustingRate") //除尘率
					){ 
				
			}
			else{
				headerList.add(entry.getValue());
			}
			
		}
		
		headerList.add(headName);
		headerList.add(headDosage);
		headerList.add(headFrequency);
		headerList.add(headRate);
		
		
		// 创建一个有多少列的表格
		PdfPTable table = new PdfPTable(headerList.size());
		// 表格的宽度百分比  
		table.setWidthPercentage(100);  
		//第一行空白
		PdfPCell cell0 = null;
		cell0 = new PdfPCell(new Paragraph("", setFont(12)));
		//cell0.setBackgroundColor(BaseColor.WHITE); // 背景颜色
		cell0.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell0.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell0.setFixedHeight(50); // 设置高度
		cell0.setColspan(headers.size()); // 设置合并列
		cell0.setBorder(0);
		table.addCell(cell0);
		
		//第二行，表格概要
		PdfPCell cell = null;
		cell = new PdfPCell(new Paragraph(tableName, setFont(12)));
		cell.setBackgroundColor(new BaseColor(0,191 ,255)); // 背景颜色
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setFixedHeight(25); // 设置高度
		cell.setColspan(headers.size()); // 设置合并列
		table.addCell(cell);
					
		
		//第三行，表头
		for (String entry:headerList) {
			PdfPCell cell1 = setTableHeader(entry, 10);
			cell1.setPadding(5);
			table.addCell(cell1);
		}
		
		//第四行，数据
		
		
		if (bean != null && bean.size() > 0) {
			for (int i = 0; i < bean.size(); i++) {
				List<String> valueList = new ArrayList<>();
				
				String valueName = "";
				String valueDosage = "";
				String valueFrequency = "";
				String valueRate = "";
				
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					if(entry.getKey().equals("desulfurationType") 	//脱硫剂名称
							|| entry.getKey().equals("denitrationType") //脱硝剂名称
							||entry.getKey().equals("adsorbentName")){	//吸附剂名称
						valueName += Constants.convert(entry.getValue(),BeanUtils.getPropertyByName(bean.get(i), entry.getKey()));
					}else if(entry.getKey().equals("desulfurationDosage") //脱硫剂使用量
							|| entry.getKey().equals("denitrationDosage") //脱硝剂使用量
							||entry.getKey().equals("adsorbentDosage")){	//吸附剂用量
						valueDosage += Constants.convert(entry.getValue(),BeanUtils.getPropertyByName(bean.get(i), entry.getKey()));
						
					}else if(entry.getKey().equals("desulfurationfrequency") //脱硫剂更换频率
							|| entry.getKey().equals("denitrationfrequency") //脱硝剂更换频率
							||entry.getKey().equals("adsorbentfrequency") //吸附剂更换频率
							||entry.getKey().equals("dedustingFrequency") //布袋清洗频率（次/年）
							){ 
						valueFrequency += Constants.convert(entry.getValue(),BeanUtils.getPropertyByName(bean.get(i), entry.getKey()));
						
					}else if(entry.getKey().equals("desulfurationRate") //脱硫率
							|| entry.getKey().equals("denitrationRate") //脱硝率
							||entry.getKey().equals("voc")	//voc去除率
							||entry.getKey().equals("dedustingRate") //除尘率
							){ 
						valueRate += Constants.convert(entry.getValue(),BeanUtils.getPropertyByName(bean.get(i), entry.getKey()));
						
					}
					else{
						valueList.add(Constants.convert(entry.getValue(),BeanUtils.getPropertyByName(bean.get(i), entry.getKey())));
					}
					
					
					
				}
				valueList.add(valueName);
				valueList.add(valueDosage);
				valueList.add(valueFrequency);
				valueList.add(valueRate);
				
				
				
				for(String value:valueList){
					PdfPCell celli1 = setTableHeader(value, 8);
					table.addCell(celli1);
				}
			}
		}
		
		return table;
	}
	public  PdfPTable kilnTable(String tableName, Map<String, String> headers,Map<String, List<String>> mergeHeaders, List<DataBean> bean) throws BadElementException{
		
		List<String> headerList = new ArrayList<>();
		String rawMaterials1 = "原辅料1";
		String rawMaterials2 = "原辅料2";
		String rawMaterials3 = "原辅料3";
		List<String> rawMaterialsheaderList = new ArrayList<>();
		rawMaterialsheaderList.add(rawMaterials1);
		rawMaterialsheaderList.add(rawMaterials2);
		rawMaterialsheaderList.add(rawMaterials3);
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			if(entry.getKey().equals("rawMaterials1.name")){
				headerList.add(rawMaterials1);
			}else if(entry.getKey().equals("rawMaterials1.consumption") 
					||entry.getKey().equals("rawMaterials1.unit")){
				
			}else if(entry.getKey().equals("rawMaterials2.name")){
				headerList.add(rawMaterials2);
			}else if(entry.getKey().equals("rawMaterials2.consumption") 
					||entry.getKey().equals("rawMaterials2.unit")){
				
			}else if(entry.getKey().equals("rawMaterials3.name")){
				headerList.add(rawMaterials3);
			}else if(entry.getKey().equals("rawMaterials3.consumption") 
					||entry.getKey().equals("rawMaterials3.unit")){ 
			}
			else{
				headerList.add(entry.getValue());
			}
			
		}
		
		mergeHeaders.put("主要原料信息", rawMaterialsheaderList);
		
		
		// 创建一个有多少列的表格
		PdfPTable table = new PdfPTable(headerList.size());
		// 表格的宽度百分比  
		table.setWidthPercentage(100);  
		//第一行空白
		PdfPCell cell0 = null;
		cell0 = new PdfPCell(new Paragraph("", setFont(12)));
		//cell0.setBackgroundColor(BaseColor.WHITE); // 背景颜色
		cell0.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell0.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell0.setFixedHeight(50); // 设置高度
		cell0.setColspan(headers.size()); // 设置合并列
		cell0.setBorder(0);
		table.addCell(cell0);
		
		//第二行，表格概要
		PdfPCell cell = null;
		cell = new PdfPCell(new Paragraph(tableName, setFont(12)));
		cell.setBackgroundColor(new BaseColor(0,191 ,255)); // 背景颜色
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cell.setFixedHeight(25); // 设置高度
		cell.setColspan(headers.size()); // 设置合并列
		table.addCell(cell);
		
		
		//第三行，表头
		Paragraph p;
		List<String> listsHead = new ArrayList<>();
		for(Map.Entry<String, List<String>> entry : mergeHeaders.entrySet()){
			String key = entry.getKey();
			List<String> lists = entry.getValue();
			p = new Paragraph(key, setFont(12));
			PdfPCell cell_7_1_1 = new PdfPCell(p);
			if(lists != null){
				listsHead.addAll(lists);
				cell_7_1_1.setRowspan(1);
	            cell_7_1_1.setColspan(lists.size());
			}else{
				cell_7_1_1.setRowspan(2);
	            cell_7_1_1.setColspan(1);
			}
            cell_7_1_1.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
            cell_7_1_1.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
            table.addCell(cell_7_1_1);
		}
		
		if(listsHead.size() >0 ){
			for(String head:listsHead){
				p = new Paragraph(head, setFont(12));
	            PdfPCell cell_7_9_5 = new PdfPCell(p);
	            cell_7_9_5.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
	            table.addCell(cell_7_9_5);
			}
		}
		
		//第四行，数据
		if (bean != null && bean.size() > 0) {
			for (int i = 0; i < bean.size(); i++) {
				List<String> valueList = new ArrayList<>();
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					if(entry.getKey().equals("rawMaterials1.name")){
						valueList.add(
								Constants.convert(headers.get("rawMaterials1.name"),BeanUtils.getPropertyByName(bean.get(i), "rawMaterials1.name"))
								+Constants.convert(headers.get("rawMaterials1.consumption"),BeanUtils.getPropertyByName(bean.get(i), "rawMaterials1.consumption"))
								+Constants.convert(headers.get("rawMaterials1.unit"),BeanUtils.getPropertyByName(bean.get(i), "rawMaterials1.unit"))
								);
					}else if(entry.getKey().equals("rawMaterials1.consumption") 
							||entry.getKey().equals("rawMaterials1.unit")){
						
					}else if(entry.getKey().equals("rawMaterials2.name")){
						valueList.add(
								Constants.convert(headers.get("rawMaterials2.name"),BeanUtils.getPropertyByName(bean.get(i), "rawMaterials2.name"))
								+Constants.convert(headers.get("rawMaterials2.consumption"),BeanUtils.getPropertyByName(bean.get(i), "rawMaterials2.consumption"))
								+Constants.convert(headers.get("rawMaterials2.unit"),BeanUtils.getPropertyByName(bean.get(i), "rawMaterials2.unit"))
								);
					}else if(entry.getKey().equals("rawMaterials2.consumption") 
							||entry.getKey().equals("rawMaterials2.unit")){
						
					}else if(entry.getKey().equals("rawMaterials3.name")){
						valueList.add(
								Constants.convert(headers.get("rawMaterials3.name"),BeanUtils.getPropertyByName(bean.get(i), "rawMaterials3.name"))
								+Constants.convert(headers.get("rawMaterials3.consumption"),BeanUtils.getPropertyByName(bean.get(i), "rawMaterials3.consumption"))
								+Constants.convert(headers.get("rawMaterials3.unit"),BeanUtils.getPropertyByName(bean.get(i), "rawMaterials3.unit"))
								);
					}else if(entry.getKey().equals("rawMaterials3.consumption") 
							||entry.getKey().equals("rawMaterials3.unit")){
						
					}
					else{
						valueList.add(Constants.convert(entry.getValue(),BeanUtils.getPropertyByName(bean.get(i), entry.getKey())));
					}
				}
				
				for(String value:valueList){
					PdfPCell celli1 = setTableHeader(value, 8);
					table.addCell(celli1);
				}
			}
		}
		
		return table;
	}
	
	

	/**
	 * 设置字体编码格式
	 * 
	 * @return
	 */
	public static Font setFont(int size) {
		BaseFont baseFont = null;
		try {
			baseFont = BaseFont.createFont("simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Font font = new Font(baseFont, size, Font.NORMAL);
		return font;
	}

	/**
	 * 设置字体编码格式
	 * 
	 * @return
	 */
	public static BaseFont getBaseFont() {
		BaseFont baseFont = null;
		try {
			baseFont = BaseFont.createFont("simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baseFont;
	}

	/**
	 * 设置cell
	 * 
	 * @param name		文字内容
	 * @param size		文字大小
	 * @return
	 * @throws BadElementException
	 */
	public static PdfPCell setTableHeader(String name, int size) throws BadElementException {

		PdfPCell cell = new PdfPCell(new Phrase(name, setFont(size)));
		// 单元格水平对齐方式
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		// 单元格垂直对齐方式
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		return cell;
	}

	/**
	 * 多个PDF合并功能
	 *
	 * @paramosList
	 * @paramos
	 */
	public void mergePdfFiles(List<ByteArrayOutputStream> osList, OutputStream os) {
		try {
			Document document = new Document(new PdfReader(osList.get(0).toByteArray()).getPageSize(1));
			PdfCopy copy = new PdfCopy(document, os);
			document.open();
			for (int i = 0; i < osList.size(); i++) {
				PdfReader reader = new PdfReader(osList.get(i).toByteArray());
				int n = reader.getNumberOfPages();
				for (int j = 1; j <= n; j++) {
					document.newPage();
					PdfImportedPage page = copy.getImportedPage(reader, j);
					copy.addPage(page);
				}
			}
			document.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static byte[] addBytes(byte[] data1, byte[] data2) {
		byte[] data3 = new byte[data1.length + data2.length];
		System.arraycopy(data1, 0, data3, 0, data1.length);
		System.arraycopy(data2, 0, data3, data1.length, data2.length);
		return data3;

	}

	/**
	 * 根据数据bean得到pdf中要写入的textfield的名字
	 * 
	 * @paramdb
	 * @return
	 */
	public List<String> getFieldName(DataBean db) {
		List<String> fieldnames = new ArrayList<String>();

		Field[] fields = db.getClass().getDeclaredFields();
		Field[] superfields = db.getClass().getSuperclass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			String tmpname = fields[i].getName();
			fieldnames.add(tmpname);
		}
		for (int i = 0; i < superfields.length; i++) {
			String tmpname = superfields[i].getName();
			fieldnames.add(tmpname);
		}
		return fieldnames;

	}

	/**
	 * 获取字段value值
	 * 
	 * @author 郑有权
	 * @date 创建时间：2017年8月24日 下午2:28:41
	 * @param fieldname
	 * @param db
	 * @return
	 */
	public String getFieldValue(String fieldname, DataBean db) {
		/*
		 * String value = ""; Method[] methods =
		 * db.getClass().getDeclaredMethods(); for (int i = 0; i <
		 * methods.length; i++) { String methodname = methods[i].getName(); if
		 * (methodname.substring(0, 3).toUpperCase().equals("GET") &&
		 * methodname.substring(3).toUpperCase().equals(fieldname.toUpperCase())
		 * ) { Method method = methods[i]; try { if (method.invoke(db, new
		 * Object[] {}) != null) { value = (String) method.invoke(db, new
		 * Object[] {}).toString(); }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } } } Method[]
		 * supermethods = db.getClass().getSuperclass().getDeclaredMethods();
		 * for (int i = 0; i < supermethods.length; i++) { String methodname =
		 * supermethods[i].getName(); if (methodname.substring(0,
		 * 3).toUpperCase().equals("GET") &&
		 * methodname.substring(3).toUpperCase().equals(fieldname.toUpperCase())
		 * ) { Method method = supermethods[i]; try { if (method.invoke(db, new
		 * Object[] {}) != null) { value = (String) method.invoke(db, new
		 * Object[] {}).toString(); }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } } } return value;
		 */
		return BeanUtils.getPropertyByName(db, fieldname);

	}

	/**
	 * 添加水印
	 * @author 郑有权
	 * @date 创建时间：2017年9月12日 下午6:38:11 
	 * @param userPassword
	 * @param ownerPassword
	 * @param inputFile
	 * @param imageFile
	 * @param outputFile
	 * @param waterMarkName
	 * @param permission
	 * @throws IOException 
	 * @throws DocumentException 
	 * @throws DataAccessException 
	 */
	public synchronized static void waterMark(byte[] userPassword, byte[] ownerPassword, String inputFile, String imageFile,
			OutputStream os, String waterMarkName, int permission) throws IOException, DocumentException, DataAccessException {
		
			PdfReader reader = null;
			PdfStamper stamper = null;

			try {
				reader = new PdfReader(inputFile);
				stamper = new PdfStamper(reader, os);
			
			
			
			// 设置密码
			stamper.setEncryption(userPassword, ownerPassword, permission, false);

			BaseFont base = getBaseFont();

			int total = reader.getNumberOfPages() + 1;
			// 获取图片对象实例
			Image image =null;
			try{
				image = Image.getInstance(imageFile);
			}catch (Exception e) {
				throw new DataAccessException("找不到水印图片");
			}
			// 图片位置

			image.setRotation(30);// 旋转 弧度
			image.setRotationDegrees(0);// 旋转 角度
			// image.scaleAbsolute(200,100);//自定义大小

			PdfContentByte under;

			int j = waterMarkName.length();
			char c = 0;
			int rise = 0;
			for (int i = 1; i < total; i++) {
				rise = 400;
				under = stamper.getUnderContent(i); // 内容之下
				// under = stamper.getOverContent(i); //内容之上
				float leftMargin = under.getPdfDocument().leftMargin();
				float rightMargin = under.getPdfDocument().rightMargin();
				float topMargin = under.getPdfDocument().topMargin();
				float bottomMargin = under.getPdfDocument().bottomMargin();
				float width, height;
				// under.getPdfDocument().setPageSize(PageSize.A4.rotate());
				// //如果是横向
				// if(under.getPdfDocument().getPageSize() ==
				// PageSize.A4.rotate()){
				width = under.getPdfDocument().getPageSize().getHeight();
				height = under.getPdfDocument().getPageSize().getWidth();
				// }else{
				// width = under.getPdfDocument().getPageSize().getWidth();
				// height = under.getPdfDocument().getPageSize().getHeight();
				// }

				under.beginText();
				under.setFontAndSize(base, 30); // 字体和大小

				PdfGState gs = new PdfGState();
				gs.setFillOpacity(0.2f);// 设置透明度为0.3
				under.setGState(gs);

				under.setTextMatrix(width / 4, -150);
				for (int k = 0; k < j; k++) {
					under.setTextRise(rise);
					c = waterMarkName.charAt(k);
					under.showText(c + "");
					rise += 20;

				}

				// 添加水印文字
				under.endText();

				float percentage = 1f; // 初始缩放比例

				// 这里都是图片最原始的宽度与高度
				float resizedWidht = image.getWidth();
				float resizedHeight = image.getHeight();

				// 这时判断图片宽度是否大于页面宽度减去也边距，如果是，那么缩小，如果还大，继续缩小，
				// 这样这个缩小的百分比percentage会越来越小
				while (resizedWidht > (width - leftMargin - rightMargin) * 0.5) {
					percentage = percentage * 0.9f;
					resizedHeight = image.getHeight() * percentage;
					resizedWidht = image.getWidth() * percentage;
				}
				// There is a 0.8 here. If the height of the image is too close
				// to the page size height,
				// the image will seem so big
				while (resizedHeight > (height - topMargin - bottomMargin) * 0.5) {
					percentage = percentage * 0.9f;
					resizedHeight = image.getHeight() * percentage;
					resizedWidht = image.getWidth() * percentage;
				}

				// 这里用计算出来的百分比来缩小图片
				image.scalePercent(percentage * 100);
				// 让图片的中心点与页面的中心店进行重合
				image.setAbsolutePosition(width / 2 - resizedWidht / 2, height / 2 - resizedHeight / 2);

				// image.scalePercent(50);//依照比例缩放
				// image.setAbsolutePosition(width, height);
				// 添加水印图片
				under.addImage(image);

				// 画个圈
				// under.ellipse(250, 450, 350, 550);
				// under.setLineWidth(1f);
				// under.stroke();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				if(stamper != null){
					try {
						stamper.close();
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
				
				if(reader != null){
					reader.close();
				}
			}
			
			
		
	}
}

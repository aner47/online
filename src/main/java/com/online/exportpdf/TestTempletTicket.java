package com.online.exportpdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.online.DataAccessException;

/**
 * @author 作者 E-mail: 郑有权
 * @date 创建时间：2017年8月21日 下午2:01:48
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class TestTempletTicket {

	//先建立Document对象  
    Document document = new Document(PageSize.A4.rotate());    
  
    String filenameuuid = UUID.randomUUID().toString();  
  
    public  void getPdfIo() throws DocumentException, IOException{    
  
  
        //字体的定义  
  
        BaseFont fontChinesecontent = BaseFont.createFont("simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);  
  
  
        Font chinese = new Font(fontChinesecontent, 10, Font.NORMAL);     
  
        //保存本地指定路径    
        saveLocal();  
  
  
        //打开文件  
        document.open();    
  
        //生成八列表格  
        PdfPTable table = new PdfPTable(8);  
  
        //设置表格具体宽度  100为整张宽度  
        table.setTotalWidth(100);  
  
        //设置每一列所占的长度  
        table.setWidths(new float[]{6,6,13,12,12,15,24,12});  
  
        //设置表头  
        String titleName[] = {"序号","上下午","会议时间","会议主题","地  点","主办处/召集人","参会范围","备注"};  
  
        for(int i=0;i<titleName.length;i++){  
            PdfPCell celltitle = new PdfPCell();  
            celltitle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);  
            celltitle.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);  
            Paragraph  paratitle  = new Paragraph( titleName[i] ,chinese);  
            paratitle.setAlignment(1);  
            celltitle.addElement(paratitle);  
            table.addCell(celltitle);  
        }  
  
        List list = new ArrayList();  
        Vector vector = new Vector();   
        vector.add("1");  
        vector.add("上午");  
        vector.add("2011-04-11 14:30—2011-04-11 17:00");  
        vector.add("会议");  
        vector.add("会议室");  
        vector.add("书记");  
        vector.add("所有人");  
        vector.add("空");  
  
        for(int i=0; i<50; i++){  
            list.add(vector);  
        }  
  
        //获取要写入的内容  
        Vector info = new Vector();   
  
        for(int i=0; i<list.size(); i++){  
            info = (Vector)list.get(i);  
            for(int j=0; j<8; j++){  
                PdfPCell cellcon = new PdfPCell();  
                cellcon.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);  
                cellcon.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);  
                Paragraph  paracon  = new Paragraph( (String)info.get( j ) ,chinese);  
                paracon.setAlignment(1);  
                cellcon.addElement(paracon);  
                table.addCell(cellcon);  
            }  
        }  
  
        //将表格的第一行设置为表头 让它在每一页都显示出来  
//        table.setHeaderRows(1);  
  
        //将表格添加到文档中  
        document.add(table);  
  
        //关闭文档    
        document.close();    
          
        System.out.println("ok");  
  
    }    
  
  
    //指定一个文件进行保存 这里吧文件保存到D盘的text.pdf    
    public void saveLocal() throws IOException, DocumentException{    
  
        //直接生成PDF 制定生成到D盘test.pdf    
        File file = new File("D:\\test.pdf");    
        file.createNewFile();    
        PdfWriter.getInstance(document, new FileOutputStream(file));    
  
    }  
      
      
    //返回路径  
    public String returnFileName(){  
        return filenameuuid;  
    }  
  
  
    public static void main(String[] args) {  
  
    	TestTempletTicket exportPdf = new TestTempletTicket();  
  
        try {  
            exportPdf.getPdfIo();  
            try {
				PDFTempletTicket.waterMark("123456".getBytes(),"123456".getBytes()
						,"D:\\test.pdf", "D:\\test.png", new FileOutputStream("D:\\test1.pdf"), "测试水印文字测试水印文字", PdfWriter.ALLOW_COPY);
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (DocumentException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
    }  
  
  

}

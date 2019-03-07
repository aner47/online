package com.online.controller.base;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.online.Message;
import com.online.service.SystemConfigService;

/**
 * Controller - 文件
 * 
 */
@Controller
@RequestMapping("/common/file")
public class FileController extends BaseController {
	private DateFormat simpleDataFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private SystemConfigService systemConfigService;
	
	String[] imgs = {"JPG","BMP","PNG","GIF"};
	public List<String> imgSuffix = Arrays.asList(imgs);
	
	public List<String> pdfSuffix = Arrays.asList("PDF");
	
	
	
	/**
	 * 上传
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> upload(
			MultipartFile file,HttpServletRequest request,String postfixType) {
		Map<String, Object> data = new HashMap<String, Object>();
		String name = file.getOriginalFilename();
		
		
		//如果是上传图片
		if("img".equals(postfixType)){
			String postfix = name.substring(name.lastIndexOf(".")+1);
			if(!imgSuffix.contains(postfix.toUpperCase())){
				data.put("message", Message.error("请上传正确的图片格式"));
				data.put("state", "ERROR");
				return data;
			}
		}
		if("pdf".equals(postfixType)){
			String postfix = name.substring(name.lastIndexOf(".")+1);
			if(!pdfSuffix.contains(postfix.toUpperCase())){
				data.put("message", Message.error("请上传正确的PDF格式"));
				data.put("state", "ERROR");
				return data;
			}
		}
		
		String path = systemConfigService.findBySysKey("save_path").getSysValue()+"/"+simpleDataFormat.format(new Date())+File.separator+UUID.randomUUID().toString()+name.substring(name.indexOf("."),name.length());
//		System.out.println("path===="+path);
		String realPath = request.getSession().getServletContext().getRealPath("/")+path;
//		String realPath = path;
		String url = systemConfigService.findBySysKey("uplaod_path").getSysValue()+ "/"+path;
		try {
//			System.out.println("realPath :::::"+ realPath);
			InputStream inputStream = file.getInputStream();
			FileUtils.copyInputStreamToFile(inputStream, new File(realPath));
			
		} catch (IOException e) {
			e.printStackTrace();
			data.put("message", Message.error(ExceptionUtils.getStackTrace(e)));
			data.put("state", "ERROR");
			return data;
		}finally {
		}
		data.put("message", Message.success());
		data.put("state", "SUCCESS");
		data.put("url", url);
		data.put("realPath", realPath);
		return data;
	}




	/**
	 * 获取子文件路径
	 * @param url
	 * @return
	 */
	public String getChildDirectory(String url) {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String str = df.format(now);

		File file = new File(url, str);
		if (!file.exists()) {
			file.mkdirs();
		}
		return str;
	}

	// 获取唯一标示符
	public String genGuid() {
		return new BigInteger(165, new Random()).toString(36).toUpperCase();
	}
	
	
}
package com.online.controller.admin.photofile;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.online.Filter.Operator;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.online.PhotoFile;
import com.online.service.PhotoFileService;
import com.online.util.Constants;
import com.online.util.ExtractZip;
import com.online.util.Principal;
import com.online.util.SpringUtils;

@Controller
@RequestMapping("/admin/photofile")
public class PhotoFileController extends BaseController {

static List<String> imgSuffix = Arrays.asList("JPG","PNG","GIF","JPEG");
	
	@Autowired
	private PhotoFileService  photoFileService ;
	
	@RequestMapping("/list")
	public String list(ModelMap model,Integer enterpriseId){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		model.put("enterpriseId", enterpriseId);
		return "/admin/photofile/adminlist";
	}
	
	/**
	 * 增加照片页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/photofile/add";
	}

	/**
	 * 修改照片页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("photoFile", photoFileService.find(id));
		return "/admin/photofile/update";
	}
	
	/**
	 * 查看照片页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("photoFile", photoFileService.find(id));
		return "/admin/photofile/view";
	}
	
	/**
	 * 查询照片
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<PhotoFile> query(Pageable pageable,PhotoFile photoFile,Integer enterpriseId) {
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		pageable.addFilter("enterprise.id", Operator.eq, enterpriseId);
		return photoFileService.findPage(pageable);
		
	}
	

	/**
	 * 保存照片
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	@Transactional
	public Message save(HttpServletRequest request,String url,String filename) {
		String suffix = filename.substring(filename.indexOf(".")+1);
		if(suffix.equalsIgnoreCase("zip")){
			detailZipFile(request, url, filename);
		}else if(imgSuffix.contains(suffix.toUpperCase())){
			PhotoFile pFile = new PhotoFile();
			pFile.setFilename(filename);
			pFile.setUrl(url);
			pFile.setProject(SpringUtils.getCurrentProject());
			pFile.setEnterprise(SpringUtils.getCurrentEnterprise());
			photoFileService.save(pFile);
		}else{
			return Message.error("上传图片格式错误！");
		}
		return Message.success();
    }

	
	private void detailZipFile(HttpServletRequest request,String url,String filename) {
	    String sparator = File.separator;
	    
	    String serviceContainPath = request.getSession().getServletContext().getRealPath(sparator);//服务器地址
	    String contextPath = request.getContextPath();
	    
	    String zipFile = serviceContainPath + sparator  + url.substring(contextPath.length()+1);//zipfile
	  
	    Map<String,String> unZip = null;
		try {
			unZip = ExtractZip.unZip(zipFile, null);
		} catch (Exception e) {
			throw new RuntimeException("解压文件失败！");
		}
	    for (Entry<String,String> entry : unZip.entrySet()) {
			PhotoFile pFile = new PhotoFile();
	        pFile.setFilename(entry.getKey());
	        pFile.setUrl(url.substring(0, url.indexOf("."))+sparator+entry.getValue());
	        pFile.setProject(SpringUtils.getCurrentProject());
	        pFile.setEnterprise(SpringUtils.getCurrentEnterprise());
	        photoFileService.save(pFile);
		}
	
		
	}
	/**
	 * 更新照片
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(PhotoFile photoFile) {
		photoFileService.update(photoFile,"project","enterprise","url","description");
		return Message.success();
	}

	/**
	 * 删除照片
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 photoFileService.delete(ids[i]);
		}
		return Message.success();
	}

}

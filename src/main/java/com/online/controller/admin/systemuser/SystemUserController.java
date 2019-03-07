package com.online.controller.admin.systemuser;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.SystemUser;
import com.online.entity.SystemUser.UserType;
import com.online.entity.online.Project;
import com.online.excelexport.Export;
import com.online.service.ProjectService;
import com.online.service.SystemUserService;
/**
 * @author dev3
 *
 */
@Controller
@RequestMapping("/admin/systemuser")
public class SystemUserController extends BaseController{
	
	@Autowired
	private SystemUserService  systemUserService ;
	
	
	@Autowired
	private ProjectService  projectService ;
	
	private Export export = new Export();
	
	@RequestMapping("/list")
	public String list(){
		return "/admin/systemuser/list";
	}
	
	/**
	 * 注册页面
	 * @return
	 */
	@RequestMapping(value = "/registeredPage")
	public String registeredPage(){
		return "/register";
	}
	
	/**
	 * 增加用户页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/systemuser/add";
	}

	/**
	 * 修改用户页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,int id) {
		model.put("systemUser", systemUserService.find(id));
		return "/admin/systemuser/update";
	}
	
	/**
	 * 查看用户页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,int id) {
		model.put("systemUser", systemUserService.find(id));
		return "/admin/systemuser/view";
	}
	
	/**
	 * 查询用户
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<SystemUser> query(Pageable pageable,SystemUser systemUser,Integer projectId) {
	    String username = systemUser.getUsername();
        String phone = systemUser.getPhone();
        UserType userType = systemUser.getUserType();
        if (StringUtils.isNotEmpty(username)) {
            pageable.addFilter("username", Operator.like, "%"+username.trim()+"%");
        }
        if (StringUtils.isNotEmpty(phone)) {
            pageable.addFilter("phone", Operator.like, "%"+phone.trim()+"%");
        }
        if (projectId != null) {
        	pageable.addFilter("project.id", Operator.eq, projectId);
        }
        if (userType != null) {
        	pageable.addFilter("userType", Operator.eq, userType);
        }
		return systemUserService.findPage(pageable);
		
	}
	
	/**
	 * 查询所有用户
	 */
	@RequestMapping(value = "/queryAll")
	@ResponseBody
	public List<SystemUser> query(String param) {
		if(StringUtils.isEmpty(param)){
			return systemUserService.findAll();
		}else{
			return systemUserService.queryProjectInvitationCode(param);
		}
		
		
	}
	/**
	 * 按条件查询
	 */
	@RequestMapping(value="/queryForm")
	@ResponseBody
	public List<SystemUser> queryForm(String username,String phone){
		return systemUserService.queryForms(username,phone);
	}
	/**
	 * 保存用户
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(SystemUser  systemUser,String selectRole,String selectDepartment,Integer projectId) {
		String  password="123456";
		//systemUser.setUserType(UserType.system);
		systemUser.setPassword(DigestUtils.md5Hex(password));
		Project project = projectService.find(projectId);
		systemUser.setProject(project);
		try {
			systemUserService.saveUserEntity(systemUser,selectRole, selectDepartment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error("用户已存在",null);
		}
		return Message.success();
	}
	
	/**
	 * 更新用户
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(SystemUser systemUser,String selectRole,Integer projectId,String selectDepartment) {
		systemUserService.updateUserEntity(systemUser,selectRole,projectId,selectDepartment);
		return Message.success();
	}
	/**
	 *==>修改用户密码
	 */
	@RequestMapping(value = "modifiyPassword")
	@ResponseBody
	public Message modifiyPassword(ModelMap model,HttpServletRequest request,String ids) {
		
		String[] idStr = ids.split(",");
		for (String id:idStr) {
			SystemUser systemUser = systemUserService.find(Integer.parseInt(id));
			
			systemUser.setPassword(DigestUtils.md5Hex("123456"));
			systemUser.setPasswordVisible("123456");
			systemUserService.update(systemUser);
		}
		
		return Message.success();
	}
	/**
	 * 更新用户
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public Message update(String password,String newPassword,Integer id) {
		
		SystemUser systemUser = systemUserService.find(id);
		if(DigestUtils.md5Hex(password).equals(systemUser.getPassword())){
			systemUser.setPassword(DigestUtils.md5Hex(newPassword));
		}else{
			return Message.error("原始密码错误！");
		}
		systemUserService.update(systemUser);
		return Message.success();
	}
	

	/**
	 * 删除用户
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(String  ids) {
		try {
			String[] id = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				 systemUserService.delete(Integer.parseInt(id[i]));
			}
			return Message.success();
		} catch (DataIntegrityViolationException e) {
			return Message.error("删除失败:用户有企业关联", null);
		} catch (Exception e) {
			return Message.error("删除失败", null);
		}
		
		
	}
	
	/**
	 * 导出用户
	 */
	@RequestMapping(value = "/exportUser", method = RequestMethod.GET)
	public @ResponseBody Message exportUser(HttpServletRequest request,
            HttpServletResponse response,String username,Integer projectId) {
		
		//web浏览通过MIME类型判断文件是excel类型
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        String systemtime = df.format(System.currentTimeMillis());   
        
        // 对文件名进行处理。防止文件名乱码
        String filename;
		try {
			filename = new String(("企业用户").getBytes("GB2312"),"iso8859-1") +"_"+systemtime+".xls";
			response.setHeader("Content-disposition", "attachment;filename=" + filename);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        // Content-disposition属性设置成以附件方式进行下载
        
        ServletOutputStream os;
		
        try {
			os = response.getOutputStream();
			List<Filter> filters = new ArrayList<>();
			filters.add(new Filter("userType", Operator.eq, SystemUser.UserType.enterprise));
			if(StringUtils.isNotBlank(username)){
				filters.add(new Filter("username", Operator.eq, username));
			}
			if(projectId != null){
				filters.add(new Filter("project.id", Operator.eq, projectId));
			}
			List<SystemUser> users = systemUserService.findList(null, filters, null);
			export.exportUser(os, users);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		return Message.success();
	}
	
}

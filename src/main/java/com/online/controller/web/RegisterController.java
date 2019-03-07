package com.online.controller.web;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.Message;
import com.online.controller.base.BaseController;
import com.online.entity.SystemMenu;
import com.online.entity.SystemUser;
import com.online.entity.SystemUser.UserType;
import com.online.entity.online.Enterprise;
import com.online.entity.online.Project;
import com.online.service.AreaService;
import com.online.service.EnterpriseService;
import com.online.service.ProjectService;
import com.online.service.RSAService;
import com.online.service.SystemUserService;
import com.online.util.Constants;
import com.online.util.JsonUtil;
import com.online.util.Principal;
import com.online.util.ShiroUtils;
import com.online.util.SpringUtils;
import com.online.util.VerifyCodeUtils;

@Controller("registerController")
@RequestMapping("/web")
public class RegisterController extends BaseController {

	@Autowired
	private SystemUserService systemUserService;

	@Autowired
	private RSAService rsaService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	protected AreaService areaService;

	/**
	 * 注册页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "registerPage", method = RequestMethod.GET)
	public String registerPage(ModelMap model) {
		model.addAttribute("captchaId", UUID.randomUUID().toString());
		return "/online/register";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public @ResponseBody Message register() {
		return Message.success();
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(ModelMap model, HttpServletRequest request) {
		return "/online/login";
	}

	// 到登陆页面
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody Message login(HttpServletRequest request, String username, String password,
			String invitationCode) {

		// 使用SecurityUtils获取subject
		Subject subject = SecurityUtils.getSubject();
		// 解密
		password = rsaService.decryptParameter("password", request);
		// md5加密
		password = DigestUtils.md5Hex(password);

		// 创建用户名和密码的令牌
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		SystemUser systemUser = systemUserService.findByUsername(username);
		if (systemUser == null || systemUser.getUserType() == UserType.system) {// 系统账号不允许在此登录
			return Message.error("账号不存在");
		}
		Project project = projectService.findProjectByInvitationCode(invitationCode);
		if (project == null) {
			return Message.error("认证码无效");
		}
		try {
			subject.login(token);
			System.out.println("access OK");
		} catch (UnknownAccountException e) {// 用户名没有找到。
			return Message.error(e.getMessage());
		} catch (IncorrectCredentialsException e) {// 用户名密码不匹配。
			return Message.error(e.getMessage());
		} catch (LockedAccountException e) {
			return Message.error(e.getMessage());
		} catch (AuthenticationException e) {// 其他的登录错误
			return Message.error("账户验证失败");
		}
		Integer enterpriseId = null;
		if (systemUser.getUserType() == UserType.enterprise) {// 企业用户直接缓存企业ID
			Enterprise enterprise = enterpriseService.findByAccount(systemUser.getId());
			if (enterprise != null)
				enterpriseId = enterprise.getId();
		}
		ShiroUtils.setSessionAttribute(Constants.SESSION_USERNAME,JsonUtil.toJSON(new Principal(systemUser.getId(), username,
				"username", systemUser.getUserType().toString(), project.getId(), enterpriseId)) );

		return Message.success();
	}
	
	@RequestMapping(value = "logout")
	public @ResponseBody Message logout(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return Message.success();
	}

	@RequestMapping("main")
	public String main(ModelMap model, HttpServletRequest request) throws JsonProcessingException {
		Principal principal = SpringUtils.getPrincipal();
		ObjectMapper mapper = new ObjectMapper();
		model.put("project", mapper.writeValueAsString(SpringUtils.getCurrentProject()));
		List<SystemMenu> menus = systemUserService.queryAllPerm(Long.valueOf(principal.getId()).intValue());
		model.put("menus", menus);
		/*if (principal.getUserType().equals(UserType.enterprise.toString())) {
			model.put("menusRoot", 19);
		} else {
			model.put("menusRoot", 2);
		}*/
		
		model.put("principal", principal);
		
		if("部门".equals(SpringUtils.getCurrentProject().getProjectType())){
			SystemMenu systemMenu = (SystemMenu)menus.stream().filter(o1->o1.getPid()==2).findFirst().orElse(null);
			if(systemMenu != null){
				model.put("menusRoot", systemMenu.getId());
			}else{
				model.put("menusRoot", 2);
			}
				
			return "/online/departmentmain";
		}else{
			model.put("menusRoot", 2);
			return "/online/main";
		}
		
	}

	/**
	 * 注册保存(用户默认企业用户权限)
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @param captcha
	 * @param name
	 * @param code
	 * @param house_number
	 * @param longitude
	 * @param latitude
	 * @param contacts
	 * @param contact_number
	 * @return
	 */
	@RequestMapping(value = "/registered", method = RequestMethod.POST)
	@ResponseBody
	public Message saveUserAndEnterprise(String username, String password, String email, String captcha, String name,
			@RequestParam("industry_category_code") String sicCode,
			@RequestParam("industry_category_name") String sicName,
			@RequestParam("industry_category_code_middle") String sicCodeMiddle,
			@RequestParam("industry_category_name_middle") String sicNameMiddle,
			String code, String provinces, String city,
			String county, String street, String house_number, String longitude, String latitude, String contacts,
			String contact_number,String captchaCode,String captchaKey) {
		
		if(!VerifyCodeUtils.valid(captchaKey, captchaCode)){
			return Message.error("验证码错误！");
		}
		
		try {
			systemUserService.saveUserAndEnterPrise(username,password,email,captcha,name,sicCode,sicName,sicCodeMiddle,sicNameMiddle,
					code,provinces,city,county,street,house_number,longitude,latitude,contacts,contact_number,captchaCode,captchaKey,"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Message.success();
	}

	/**
	 *==>修改用户密码
	 */
	@RequestMapping(value = "modifiyPassword")
	public String modifiyPassword(ModelMap model,HttpServletRequest request ) {
		model.put("User", request.getSession().getAttribute(Constants.SESSION_USERNAME));
		return "/online/modifiyPassword";
	}	
	/**
	 * 更新用户
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public Message update(String password,String newPassword) {
		
		SystemUser systemUser = systemUserService.find(SpringUtils.getUserId());
		if(DigestUtils.md5Hex(password).equals(systemUser.getPassword())){
			systemUser.setPassword(DigestUtils.md5Hex(newPassword));
		}else{
			return Message.error("原始密码错误！");
		}
		systemUserService.update(systemUser);
		return Message.success();
	}
	
	@RequestMapping(value = "latlongQuery")
	public String latlongQuery(ModelMap model, HttpServletRequest request) {
		return "/online/latlongQuery";
	}
	
	/**
	 * 省市县
	 */
	@RequestMapping(value = "getAreaCode",produces = "application/json;charset=utf-8")
	public @ResponseBody String getAreaCode(String lngs, String lats) {
		
		try {
			return areaService.getAreaCode(lngs, lats);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 省市县
	 */
	@RequestMapping(value = "getGeocoderLatitude")
	public @ResponseBody Map<String, String> getGeocoderLatitude(String address) {
		
		try {
			return areaService.getGeocoderLatitude(address);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}

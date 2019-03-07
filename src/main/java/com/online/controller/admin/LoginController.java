package com.online.controller.admin;

import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.online.Message;
import com.online.controller.base.BaseController;
import com.online.entity.SystemUser;
import com.online.entity.SystemUser.UserType;
import com.online.service.AreaService;
import com.online.service.RSAService;
import com.online.service.SystemUserService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.ShiroUtils;
import com.online.util.SpringUtils;

@Controller
@RequestMapping("/admin/")
public class LoginController extends BaseController {
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private RSAService rsaService;
	
	@Autowired
	protected AreaService areaService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(ModelMap model, HttpServletRequest request) {
		return "/admin/login";
	}

	// 到登陆页面
	@RequestMapping(value = "login", method = RequestMethod.POST)

	public @ResponseBody Message login(HttpServletRequest request, String username, String password,String invitationCode) {
		System.out.println("登录开始==================");
		SystemUser systemUser = systemUserService.findByUsername(username);
		if (systemUser!=null) {
		    if(systemUser.getUserType() == UserType.enterprise){
		        return Message.error("企业用户不能通过后台登录");
		    }
		    else if(systemUser.getUserType() == UserType.investigator){
		        return Message.error("调查员不能通过后台登录");
		    }
       
		// 使用SecurityUtils获取subject
		Subject subject = SecurityUtils.getSubject();
		// 解密
		password = rsaService.decryptParameter("password", request);
		// md5加密
		password = DigestUtils.md5Hex(password);

		// 创建用户名和密码的令牌
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		
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
//		request.getSession().setAttribute(Constants.SESSION_USERNAME,
//				new Principal(systemUser.getId(), username, "username", UserType.system.toString()));
		
		ShiroUtils.setSessionAttribute(Constants.SESSION_USERNAME,
				JSONObject.toJSON(new Principal(systemUser.getId(), username, "username", UserType.system.toString())));
		 }else{
			 //没有此用户
			 return Message.error("用户不存在");
		 }
		return Message.success();
	}

	@RequestMapping(value = "logout")
	public @ResponseBody Message logout(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return Message.success();

	}

	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String main(ModelMap model, HttpServletRequest request) {
		Principal attribute = JSONObject.parseObject(request.getSession().getAttribute(Constants.SESSION_USERNAME).toString(), Principal.class);
		
		model.put("principal", attribute);
		
		if (attribute == null) {
			return "/admin/login";
		} else {
			model.put("menus", systemUserService.queryAllPerm(Long.valueOf(attribute.getId()).intValue()));
		}
		return "/admin/main";
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

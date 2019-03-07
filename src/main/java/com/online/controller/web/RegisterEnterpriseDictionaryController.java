package com.online.controller.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.Message;
import com.online.controller.base.BaseController;
import com.online.entity.EnterpriseDictionary;
import com.online.entity.SystemRole;
import com.online.entity.SystemUser;
import com.online.entity.SystemUser.UserType;
import com.online.service.AreaService;
import com.online.service.EnterpriseDictionaryService;
import com.online.service.SystemRoleService;
import com.online.service.SystemUserService;
import com.online.util.Constants;
import com.online.util.VerifyCodeUtils;

@Controller("registerEnterpriseDictionaryController")
@RequestMapping("/web/registerenterprisedictionary")
public class RegisterEnterpriseDictionaryController extends BaseController {

	@Autowired
	private SystemUserService systemUserService;

	@Autowired
	private SystemRoleService systemRoleService;
	
	@Autowired
	protected AreaService areaService;
	@Autowired
	protected EnterpriseDictionaryService enterpriseDictionaryService;

	/**
	 * 注册页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "registerPage", method = RequestMethod.GET)
	public String registerPage(ModelMap model) {
		model.addAttribute("captchaId", UUID.randomUUID().toString());
		return "/online/registerEnterpriseDictionary";
	}

	

	

	/**
	 * 注册名录库
	 * @author 郑有权
	 * @date 创建时间：2018年2月28日 下午2:09:35 
	 * @param username
	 * @param password
	 * @param captcha
	 * @param name
	 * @param inputIndustry
	 * @param code
	 * @param countyName
	 * @param contacts
	 * @param corporation
	 * @param captchaCode
	 * @param captchaKey
	 * @return
	 */
	@RequestMapping(value = "/registered", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public Message saveUserAndEnterprise(String username, String password, String captcha,
			String name,String inputIndustry,
			String code, String countyName,String detailAddress,String contacts,String contactsPhone,
			String corporation,String captchaCode,String captchaKey) {
		
		if(!VerifyCodeUtils.valid(captchaKey, captchaCode)){
			return Message.error("验证码错误！");
		}
		
		try {
			SystemUser entity = new SystemUser();
			entity.setUsername(username);
			entity.setPasswordVisible(password);
			entity.setPassword(DigestUtils.md5Hex(password));
			entity.setUserType(UserType.enterprise);
			Set<SystemRole> roles = new HashSet<>();
			List<SystemRole> systemRoles = systemRoleService.findByFilter(new Filter("name", Operator.eq, "企业用户"));
			if(systemRoles != null && systemRoles.size()>0){
				roles.add(systemRoles.get(0));
				entity.setRoles(roles);
			}
			systemUserService.save(entity);
			
			EnterpriseDictionary enterpriseDictionary = new EnterpriseDictionary();
			enterpriseDictionary.setEnterpriseName(name);
			enterpriseDictionary.setInputIndustry(inputIndustry);
			enterpriseDictionary.setCode(code);
			enterpriseDictionary.setCountyName(countyName);
			enterpriseDictionary.setDetailAddress(detailAddress);
			enterpriseDictionary.setContacts(contacts);
			enterpriseDictionary.setContactsPhone(contactsPhone);
			enterpriseDictionary.setCorporation(corporation);
			enterpriseDictionary.setSource(username);
			enterpriseDictionary.setStatus(Constants.ENTERPRISE_DICTIONARY_STATUS_CHECKPENDING);
			enterpriseDictionaryService.save(enterpriseDictionary);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Message.success();
	}

	
	
}

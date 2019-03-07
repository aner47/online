package com.online.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.controller.base.BaseController;
import com.online.entity.SystemUser;
import com.online.entity.online.Enterprise;
import com.online.service.EnterpriseService;
import com.online.service.ProjectService;
import com.online.service.SystemUserService;

/** 
 * @author 作者名 
 * @email  邮箱名
 * @time   2017年4月25日 下午8:40:25 
 */
@Controller("examineController")
@RequestMapping("/common")
public class ExamineController extends BaseController {
	
	@Autowired
	protected ProjectService projectService;
	@Autowired
	protected EnterpriseService enterpriseService;
	@Autowired
	protected SystemUserService systemUserService;
	
	/**
	 * 检查用户名字是否重复
	 * @param enterpriseName
	 * @return
	 */
	@RequestMapping(value = "/checkUserNameIsRepeat", method = RequestMethod.POST)
	@ResponseBody
	public Boolean checkUserNameIsRepeat(String userName){
		SystemUser user = systemUserService.findByUsername(userName);
		if (user != null) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 检查企业名字是否重复
	 * @param enterpriseName
	 * @return
	 */
	@RequestMapping(value = "/checkEnterpriseNameIsRepeat", method = RequestMethod.POST)
	@ResponseBody
	public Boolean checkEnterpriseNameIsRepeat(String enterpriseName){
		Enterprise enterprise = enterpriseService.findByEnterpriseName(enterpriseName);
		if (enterprise != null) {
			return false;
		} else {
			return true;
		}
	}
}

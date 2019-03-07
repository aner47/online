/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.online.util;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import com.online.entity.SystemUser;
import com.online.entity.online.Enterprise;
import com.online.entity.online.Project;
import com.online.service.EnterpriseService;
import com.online.service.ProjectService;
import com.online.service.SystemUserService;

/**
 * 
 */
@Lazy(false)
@Component("springUtils")
public final class SpringUtils implements ApplicationContextAware, DisposableBean {

	/** ApplicationContext */
	private static ApplicationContext applicationContext;

	/**
	 * 不可实例化
	 */
	private SpringUtils() {
	}

	/**
	 * 设置ApplicationContext
	 * 
	 * @param applicationContext
	 *            ApplicationContext
	 */
	@Override
	public  void  setApplicationContext(ApplicationContext applicationContext) {
		SpringUtils.applicationContext = applicationContext;
	}

	/**
	 * 获取applicationContext
	 * 
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取实例
	 * 
	 * @param name
	 *            Bean名称
	 * @return 实例
	 */
	public static Object getBean(String name) {
		Assert.hasText(name);

		return applicationContext.getBean(name);
	}

	public static HttpServletRequest getRequest() {  
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
    }  
	
	/**
	 * 活动用户当前登录项目ID
	 * @return
	 */
	public static Integer getProjectId(){
		return getPrincipal().getProejctId();
	}
	
	/**
	 * 活动用户当前登录项目ID
	 * @return
	 */
	public static Integer getUserId(){
		return  getPrincipal().getId();
	}
	
	/**
	 * 活动用户登录信息
	 * @return
	 */
	public static Principal getPrincipal(){
		Principal principal = JsonUtil.fromJSON(getRequest().getSession().getAttribute(Constants.SESSION_USERNAME).toString(), Principal.class);
		return principal;
	}
	
	
    /**获取ip地址
     * @return
     */
    public static String getIpAddr() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
	
	
	/**
	 * 获取实例
	 * 
	 * @param type
	 *            Bean类型
	 * @return 实例
	 */
	public static <T> T getBean(Class<T> type) {
		Assert.notNull(type);
		return applicationContext.getBean(type);
	}

	/**
	 * 获取实例
	 * 
	 * @param name
	 *            Bean名称
	 * @param type
	 *            Bean类型
	 * @return 实例
	 */
	public static <T> T getBean(String name, Class<T> type) {
		Assert.hasText(name);
		Assert.notNull(type);

		return applicationContext.getBean(name, type);
	}

	/**
	 * 获取国际化消息
	 * 
	 * @param code
	 *            代码
	 * @param args
	 *            参数
	 * @return 国际化消息
	 */
	public static String getMessage(String code, Object... args) {
		Assert.hasText(code);
		LocaleResolver localeResolver = getBean("localeResolver", LocaleResolver.class);
		Locale locale = localeResolver.resolveLocale(null);
		return applicationContext.getMessage(code, args, locale);
	}

	/**
	 * 销毁
	 */
	@Override
	public void destroy() throws Exception {
		applicationContext = null;
	}

	/**
	 * 读取配置文件类
	 */
	public static Properties loadProperties (String configFile){
		Resource resource= new ClassPathResource(configFile);
		try{
			Properties property = PropertiesLoaderUtils.loadProperties(resource);
			return property;
		}catch(IOException e1){
			
		}
		return null; 
	}
	
	/**
	 * 活动当前用户登陆项目信息
	 * @return
	 */
	public static Project getCurrentProject(){
		Integer projectId = getPrincipal().getProejctId();
		/*Project project = projects.get(projectId);
		if(project == null){
			project = getBean("projectServiceImpl",ProjectService.class).find(getProjectId());
			projects.put(projectId, project);
		}*/
		return getBean("projectServiceImpl",ProjectService.class).find(getProjectId());
	}
	/**
	 * 获得当前用户企业信息（只针对企业用户）
	 * @return
	 */
	public static Enterprise getCurrentEnterprise(){
/*		Integer enterpriseId = getPrincipal().getEnterpriseId();
		Enterprise enetprise = enterprises.get(enterpriseId);
		if(enetprise == null){
 			enetprise = getBean("enterpriseServiceImpl",EnterpriseService.class).find(enterpriseId);
			enterprises.put(enterpriseId, enetprise);
		}
		return enetprise;*/
		Integer enterpriseId = getPrincipal().getEnterpriseId();
		return getBean("enterpriseServiceImpl",EnterpriseService.class).find(enterpriseId);
	}
	
	
	private static Map<Integer,Enterprise> enterprises = new ConcurrentHashMap<>();
	private static Map<Integer,Project> projects = new ConcurrentHashMap<>();
	/**
	 * 获得当前用户信息
	 * @return
	 */
	public static SystemUser getCurrentSystemUser(){
		return getBean("systemUserServiceImpl",SystemUserService.class).find(getUserId());
	}
	
	
	
	
}
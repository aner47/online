package com.online.controller.base;

import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.entity.Area;
import com.online.entity.DictionaryData;
import com.online.entity.online.Category;
import com.online.service.AreaService;
import com.online.service.CategoryService;
import com.online.service.DictionaryService;
import com.online.service.RSAService;
import com.online.util.VerifyCodeUtils;


/**
 * Controller - 共用
 * 
 * 
 * 
 */
@Controller
@RequestMapping("/common")
public class CommonController {

	@Resource(name = "rsaServiceImpl")
	private RSAService rsaService;
	
	

	@Resource(name = "areaServiceImpl")
	private AreaService areaService;
	
	@Resource(name = "categoryServiceImpl")
	private CategoryService  categoryService;


	@Autowired
	private DictionaryService dictionaryService;

	/**
	 * 查询
	 * 
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/findCodeDataByName")
	public @ResponseBody List<DictionaryData> query(String name,String param) {
		List<DictionaryData> data = dictionaryService.findByname(name,param);
		return data;
	}
	/**
	 * 公钥
	 */
	@RequestMapping(value = "/public_key", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, String> publicKey(HttpServletRequest request) {
		RSAPublicKey publicKey = rsaService.generateKey(request);
		Map<String, String> data = new HashMap<String, String>();
		data.put("modulus", Base64.encodeBase64String(publicKey.getModulus().toByteArray()));
		data.put("exponent", Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray()));
		return data;
	}

	/**
	 * 验证码
	 */
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public void captcha(String captchaId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerifyCodeUtils.generalPic(captchaId, response.getOutputStream());
	}
	
	@RequestMapping("/changeCaptcha")
	@ResponseBody
	public String changeCaptcha(){
		return UUID.randomUUID().toString();
	}
	
	
	/**
	 * 验证码
	 */
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String map(ModelMap model,String address) throws IOException {
		model.put("address", address);
		return "/online/map";
	}
	
	/**
	 * 验证码
	 */
	@RequestMapping(value = "/captchaValid", method = RequestMethod.GET)
	public boolean captchaValid(String captchaId,String code) throws IOException {
        return VerifyCodeUtils.valid(captchaId, code);
	}
	

	/**
	 * 错误提示
	 */
	@RequestMapping("/error")
	public String error() {
		return null;
//		return "/shop/${theme}/common/error";
	}

	/**
	 * 资源不存在
	 */
	@RequestMapping("/resource_not_found")
	public String resourceNotFound() {
		return "/404";
	}

	/**
	 * 商业许可异常
	 */
	@RequestMapping("/illegal_license")
	public String illegalLicense() {
		return "";
	}
	
	/**
	 * 查询所有区域数据
	 */
	@RequestMapping(value = "/allArea")
	@ResponseBody
	public List<Area> allArea() {
		return areaService.findAll();
	}
	
	/**
	 * 查询所有行业分类
	 */
	@RequestMapping(value = "/allCategory")
	@ResponseBody
	public List<Category> allCategory() {
		return categoryService.findAll();
	}
	
}
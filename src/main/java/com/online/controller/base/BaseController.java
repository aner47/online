package com.online.controller.base;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.online.Message;
import com.online.controller.DateEditor;
import com.online.entity.Log;
import com.online.service.LogService;
import com.online.util.JsonUtil;
import com.online.util.SpringUtils;



/**
 * Controller - 基类
 *
 *
 *
 */
public class BaseController {

	/** Logger */
	public final Logger logger = Logger.getLogger(getClass());

	/** 错误视图 */
	protected static final String ERROR_VIEW = "/error";

	/** 错误消息 */
	protected static final Message ERROR_MESSAGE = Message.error("shop.message.error");

	/** 成功消息 */
	protected static final Message SUCCESS_MESSAGE = Message.success("shop.message.success");

//	/** "验证结果"属性名称 */
//	private static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";

//	@Resource(name = "validator")
//	private Validator validator;

	@Autowired
	private LogService logService;
	
	/**
	 * 数据绑定
	 *
	 * @param binder
	 *            WebDataBinder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
//		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Date.class, new DateEditor(true));
//		binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
//		binder.registerCustomEditor(Float.class, new CustomNumberEditor(Float.class, true));
//		binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
//		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
//		binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor(true));
		
//		binder.registerCustomEditor(String.class, "password", new StringEditor(true));
	}

	/**
	 * 异常处理
	 *
	 * @param exception
	 *            异常
	 * @param response
	 *            HttpServletResponse
	 * @return 数据视图
	 */
	@ExceptionHandler
	public String exceptionHandler(Exception exception, HttpServletResponse response) {
		exception.printStackTrace();
		Log log = new Log();
		log.setOperation("系统异常信息");
		log.setOperator(SpringUtils.getPrincipal().getUsername());
		log.setContent(toString_02(exception));
		log.setIp(SpringUtils.getIpAddr());
		log.setParameter(JsonUtil.toJSON(SpringUtils.getRequest().getParameterMap()));
		logService.save(log);
		logger.warn("Handler execution resulted in exception", exception);
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return "/404";
	}

    private static String toString_02(Throwable e){   
        StringWriter sw = new StringWriter();   
        PrintWriter pw = new PrintWriter(sw, true);   
        e.printStackTrace(pw);   
        pw.flush();   
        sw.flush();   
        return sw.toString();   
} 
	
	
	/**
	 * 数据验证
	 *
	 * @param target
	 *            验证对象
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(Object target, Class<?>... groups) {
/*		Assert.notNull(target);

		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target, groups);
		if (constraintViolations.isEmpty()) {
			return true;
		}
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
		return false;*/
		return true;
	}

	/**
	 * 数据验证
	 *
	 * @param targets
	 *            验证对象
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(Collection<Object> targets, Class<?>... groups) {
		Assert.notEmpty(targets);

		for (Object target : targets) {
			if (!isValid(target, groups)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 数据验证
	 *
	 * @param type
	 *            类型
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(Class<?> type, String property, Object value, Class<?>... groups) {
//		Assert.notNull(type);
//		Assert.hasText(property);
//
//		Set<?> constraintViolations = validator.validateValue(type, property, value, groups);
//		if (constraintViolations.isEmpty()) {
//			return true;
//		}
//		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
//		requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
//		return false;
		return true;
	}

	/**
	 * 数据验证
	 *
	 * @param type
	 *            类型
	 * @param properties
	 *            属性
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(Class<?> type, Map<String, Object> properties, Class<?>... groups) {
		Assert.notNull(type);
		Assert.notEmpty(properties);

		for (Map.Entry<String, Object> entry : properties.entrySet()) {
			if (!isValid(type, entry.getKey(), entry.getValue(), groups)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 货币格式化
	 *
	 * @param amount
	 *            金额
	 * @param showSign
	 *            显示标志
	 * @param showUnit
	 *            显示单位
	 * @return 货币格式化
	 */
//	protected String currency(BigDecimal amount, boolean showSign, boolean showUnit) {
//		Setting setting = SystemUtils.getSetting();
//		String price = setting.setScale(amount).toString();
//		if (showSign) {
//			price = setting.getCurrencySign() + price;
//		}
//		if (showUnit) {
//			price += setting.getCurrencyUnit();
//		}
//		return price;
//	}

	/**
	 * 获取国际化消息
	 *
	 * @param code
	 *            代码
	 * @param args
	 *            参数
	 * @return 国际化消息
	 */
	protected String message(String code, Object... args) {
//		return SpringUtils.getMessage(code, args);
		return code;
	}

//	/**
//	 * 添加瞬时消息
//	 *
//	 * @param redirectAttributes
//	 *            RedirectAttributes
//	 * @param message
//	 *            消息
//	 */
//	protected void addFlashMessage(RedirectAttributes redirectAttributes, Message message) {
//		if (redirectAttributes != null && message != null) {
//			redirectAttributes.addFlashAttribute(FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME, message);
//		}
//	}

//	protected User getUserInfo(HttpServletRequest request){
//		// 检查参数格式
//		HttpSession session = request.getSession(true);
//		User user = null;
//		Object obj = session.getAttribute(CommonUtils.SESSION_LOGIN_USER_NAME);
//		if(obj!=null){
//			user = (User) obj;
//		}
//		if(user == null){
//			throw new RuntimeException("无法根据接口请求获取用户信息，请检查userkey是否正确！");
//		}
//		return user;
//	}
	
}
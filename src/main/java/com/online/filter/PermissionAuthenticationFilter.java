package com.online.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.Message;
import com.online.entity.SystemUser.UserType;
import com.online.util.Constants;
import com.online.util.JsonUtil;
import com.online.util.Principal;

public class PermissionAuthenticationFilter extends PathMatchingFilter {

 
	
	@Override
	protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
//		System.out.println(String.valueOf(request.getSession().getAttribute(Constants.SESSION_USERNAME)));
//		Principal principal = JSONObject.parseObject(request.getSession().getAttribute(Constants.SESSION_USERNAME).toString(),Principal.class);
		Principal principal = JsonUtil.fromJSON(String.valueOf(request.getSession().getAttribute(Constants.SESSION_USERNAME)), Principal.class);
		
		if(principal == null || principal.getUserType() == null ||  principal.getUserType().equals(UserType.system.toString())){
			if (StringUtils.isNotEmpty(request.getHeader("X-Requested-With"))&&StringUtils.equalsIgnoreCase(request.getHeader("X-Requested-With"), "XMLHttpRequest")) {
//				System.out.println("ajax请求被阻止!");
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				ObjectMapper m = new ObjectMapper();  
				String value = m.writeValueAsString(Message.error(Message.NO_SESSION,"权限不足"));
				response.getWriter().write(value);
				return false;
			}else{
//				System.out.println("普通请求被阻止");
			    java.io.PrintWriter out = response.getWriter();  
			    out.println("<html>");  
			    out.println("<script>");  
			    out.println("window.open ('/online/web/login.jhtml','_top')");  
			    out.println("</script>");  
			    out.println("</html>");  
				return false;
			}
		}
		return super.preHandle(request, response);

	}
	

}

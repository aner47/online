package com.online.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.Message;
import com.online.entity.SystemUser.UserType;
import com.online.util.Constants;
import com.online.util.JsonUtil;
import com.online.util.Principal;

public class AuthFilter  extends FormAuthenticationFilter {

	@Override  
    protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
		super.postHandle(request, response);
	}
	
	@Override
	protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String string = String.valueOf(request.getSession().getAttribute(Constants.SESSION_USERNAME));
//		System.out.println("session=========="+string);
		Principal principal = JsonUtil.fromJSON(string, Principal.class);
		if(principal == null || !principal.getUserType().equals(UserType.system.toString())){
		    if (StringUtils.equalsIgnoreCase(request.getHeader("X-Requested-With"), "XMLHttpRequest")) {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				ObjectMapper m = new ObjectMapper();  
				String value = m.writeValueAsString(Message.error(HttpServletResponse.SC_FORBIDDEN,"权限不足"));
				response.getWriter().write(value);
				return false;
			}else{
			    java.io.PrintWriter out = response.getWriter();  
			    out.println("<html>");  
			    out.println("<script>");  
			    out.println("window.open ('/online/admin/login.jhtml','_top')");  
			    out.println("</script>");  
			    out.println("</html>");  
				return false;
			}
		}
		return super.preHandle(request, response);
		
		
		
	}
	@Override
	protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse,Object mapvalue) throws Exception {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		if (StringUtils.equalsIgnoreCase(request.getHeader("X-Requested-With"), "XMLHttpRequest")) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			ObjectMapper m = new ObjectMapper();  
			String value = m.writeValueAsString(Message.error(HttpServletResponse.SC_FORBIDDEN,"权限不足"));
			response.getWriter().write(value);
			return false;
		}else{
		    java.io.PrintWriter out = response.getWriter();  
		    out.println("<html>");  
		    out.println("<script>");  
		    out.println("window.open ('/online/admin/login.jhtml','_top')");  
		    out.println("</script>");  
		    out.println("</html>");  
			return false;
		}
		
//		return super.onAccessDenied(request, response);
	}

}

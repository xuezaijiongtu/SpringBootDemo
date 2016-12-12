package poi.cloud.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import poi.cloud.service.AdminService;
import poi.cloud.service.CustomerService;
import poi.cloud.serviceImpl.AdminServiceImpl;
import poi.cloud.serviceImpl.CustomerServiceImpl;
import util.ValidateUtil;
public class AuthFilter implements Filter{
	//无需做任何拦截操作的白名单列表
	private List whiteList = Arrays.asList("/VarifyImg/code", "/login");

	private AdminService adminService = new AdminServiceImpl();
	 
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/*ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    	HttpServletRequest req = attributes.getRequest();
    	String path = req.getRequestURI();
    	if(!whiteList.contains(path)){
    		//不在白名单内的Request执行逻辑
    		if(!adminService.checkLogin()){
    			//未登录
    			response.setContentType("application/json; charset=utf-8");
    			response.getWriter().write("{\"code\":-1,\"msg\":\"请求非法,请登陆\"}");  
                return;
    		}
    	}*/
		chain.doFilter(request, response);
	}
 
	@Override
	public void destroy() {
		
	}
}

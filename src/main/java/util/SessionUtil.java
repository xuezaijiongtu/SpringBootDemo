package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Description Session 工具类
 * @Author xing.liu
 * @Date 2016-11-26
 * */
public class SessionUtil {
	private static HttpSession session = null;
	
	static {
		// 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        session = request.getSession();
	}
	/**
	 * Set Session
	 * @param String key
	 * @param String value
	 * @return void
	 * */
	public static void setSession(String key, String value){
		session.setAttribute(key, value);
	}
	
	/**
	 * Get Session
	 * @param String key
	 * @return String
	 * */
	public static String getSession(String key){
		return session.getAttribute(key) == null ? "" : session.getAttribute(key).toString();
	}
	
	/**
	 * Delete Session
	 * @param String key
	 * @return void
	 * */
	public static void deleteSession(String key){
		session.removeAttribute(key);
	}
}

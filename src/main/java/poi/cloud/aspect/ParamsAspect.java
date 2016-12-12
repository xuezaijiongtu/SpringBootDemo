package poi.cloud.aspect;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import poi.cloud.service.CustomerService;
import util.ValidateUtil;
/**
 * @Description Controller 参数预处理
 * @Author xing.liu
 * @Date 2016-11-22
 * */
@Aspect
@Order(5)
@Component
public class ParamsAspect {
	//需要做token验证的url列表
	private List tokenUrlList = Arrays.asList("/Register/register", "/Register/getTask", "/Register/getDCA", "/Register/getCustomerIdByCode");
	@Autowired
	private CustomerService customerService;
	/** 
     * 定义拦截规则：拦截poi.cloud.controller包下面的所有类。 
     */  
    @Pointcut("execution(public * poi.cloud.controller..*.*(..))")  
    public void params(){
    }
    
    @Before("params()")
    public void paramsValidate(JoinPoint joinPoint) throws IOException{
    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    	HttpServletRequest req = attributes.getRequest();
    	String path = req.getRequestURI();
    	if(tokenUrlList.contains(path)){
    		//在token列表中，进行token校验
    		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
			Map params = getParams(req);
			Map res = checkToken(params);
			if(!res.get("code").equals(90)){
				response.setContentType("application/json; charset=utf-8");
				ServletOutputStream out = response.getOutputStream();
				out.write(new String("{\"code\":98,\"msg\":\"token validate fail\",\"status\":false}").getBytes());
				out.flush();
				out.close();
				return;
			}
    	}
    }
    
    @AfterReturning(returning = "ret", pointcut = "params()")
    public void doAfterReturning(Object ret) throws Throwable {
    	HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
    	response.setHeader("Access-Control-Allow-Origin", "*");  
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");  
        response.setHeader("Access-Control-Max-Age", "3600");  
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        // 处理完请求，返回内容
    	System.out.println("RESPONSE : " + ret);
    	//System.out.println("code: " + SessionUtil.getSession(Constants.KAPTCHA_SESSION_KEY));
    }
    
	
	/**
	 * 获取请求参数
	 * */
	public static Map getParams(HttpServletRequest request){
		Map<String, String[]> map = request.getParameterMap();
		Map<String, String> response = new HashMap<String, String>();
        Set<Entry<String, String[]>> set = map.entrySet();  
        Iterator<Entry<String, String[]>> it = set.iterator();  
        while (it.hasNext()) {  
            Entry<String, String[]> entry = it.next();  
            for (String i : entry.getValue()) {  
                response.put(entry.getKey(), i);
            }  
        }
        return response;
	}
	
	/**
	 * token校验
	 * */
	public Map checkToken(Map params){
		Map<String, Object> rsp = new HashMap<String, Object>();
		String token = params.get("token") == null ? "" : params.get("token").toString();
		String timestamp = params.get("timestamp") == null ? "" : params.get("timestamp").toString();
		String dcaOwnerCode = params.get("dca_owner_code") == null ? "" : params.get("dca_owner_code").toString();
		String dcaName = params.get("dca_name") == null ? "" : params.get("dca_name").toString();
		String ip = params.get("ip") == null ? "" : params.get("ip").toString();
		String mac = params.get("mac") == null ? "" : params.get("mac").toString();
		
		if(token.equals("") || timestamp.equals("") || dcaOwnerCode.equals("")
				 || ip.equals("") || mac.equals("") || dcaName.equals("")){
			rsp.put("status", false);
    		rsp.put("code", 99);
    		rsp.put("msg", "参数缺少或不合法");
    		return rsp;
		}
		
		Map customer =  customerService.getCustomer("customer_code", dcaOwnerCode);
		if(customer == null){
			rsp.put("status", false);
    		rsp.put("code", 97);
    		rsp.put("msg", "dca_owner_code不存在");
    		return rsp;
		}
		
		Map paramters = new HashMap<String, String>();
		paramters.put("timestamp", timestamp);
		paramters.put("dca_owner_code", dcaOwnerCode);
		paramters.put("dca_name", dcaName);
		paramters.put("ip", ip);
		paramters.put("mac", mac);
		boolean isAllow = ValidateUtil.checkValidation(paramters, customer.get("customer_key").toString(), token);
		if(!isAllow){
			rsp.put("status", false);
    		rsp.put("code", 98);
    		rsp.put("msg", "token校验失败");
		}else{
			rsp.put("status", true);
    		rsp.put("code", 90);
    		rsp.put("msg", "token校验成功");
		}
		return rsp;
	}
}

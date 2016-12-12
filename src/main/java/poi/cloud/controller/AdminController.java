package poi.cloud.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import poi.cloud.service.AdminService;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	/**
	 * 用户登陆
	 * */
	@ResponseBody
    @RequestMapping(value="/login", method = RequestMethod.GET)
	public Map login(@RequestParam(value = "username", required = false) String username,
					 @RequestParam(value = "password", required = false) String password,
					 @RequestParam(value = "code", required = false) String code
			){
		Map<String, Object> rsp = new HashMap<String, Object>();
		if(username == null || password == null || code == null){
    		rsp.put("status", false);
    		rsp.put("code", 99);
    		rsp.put("msg", "参数缺少或不合法");
    		return rsp;
    	}
		
		//检查用户名是否存在
		Map admin = adminService.getAdminInfo(username);
		if(admin == null){
			rsp.put("status", false);
    		rsp.put("code", 98);
    		rsp.put("msg", "用户名或密码错误");
    		return rsp;
		}
		
		//检查验证码是否正确
		if(!adminService.checkVerifyCode(code)){
			rsp.put("status", false);
    		rsp.put("code", 97);
    		rsp.put("msg", "验证码错误");
    		return rsp;
		}
		
		//检查密码是否正确
		if(!adminService.checkPassword(password, admin.get("password").toString())){
			rsp.put("status", false);
    		rsp.put("code", 96);
    		rsp.put("msg", "用户名或密码错误");
    		return rsp;
		}
		
		//登陆写入
		if(adminService.login(admin.get("id").toString(), username)){
			rsp.put("status", true);
    		rsp.put("code", 1);
    		rsp.put("msg", "登陆成功");
		}else{
			rsp.put("status", false);
    		rsp.put("code", -1);
    		rsp.put("msg", "登陆失败");
		}
		return rsp;
	}
	
}

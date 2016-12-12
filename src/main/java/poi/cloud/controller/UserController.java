package poi.cloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import poi.cloud.service.UserService;

/**
 * @Description User Controller
 * @Author xing.liu
 * @Date 2016-11-23
 * */
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * 获取用户列表数据
	 * @param Integer page
	 * @return Map
	 * */
	@ResponseBody
	@RequestMapping(value="/User/getUserList", method = RequestMethod.GET)
	public Map getUserList(@RequestParam(value = "page", required = false) Integer page){
		Map<String, Object> rsp = new HashMap<String, Object>();
		if(page == null){
    		rsp.put("status", false);
    		rsp.put("code", 99);
    		rsp.put("msg", "参数缺少或不合法");
    		return rsp;
    	}
		page = page - 1;
		int total = userService.getUserCount(1);
		
		int pagePerNum = 10;
		int pageNum = new Double(Math.ceil((double)total / pagePerNum)).intValue();
		int offset = pagePerNum * page;
		List<Map> data = userService.getUserList(offset, pagePerNum);
		rsp.put("total", total);
		rsp.put("data", data);
		rsp.put("page", pageNum);
		rsp.put("page_per_num", pagePerNum);
		return rsp;
	}
	
	/**
	 * 获取某个用户信息
	 * @param int index
	 * @param int limit
	 * @return Map
	 * */
	@ResponseBody
    @RequestMapping(value="/User/getUser", method = RequestMethod.GET)
	public Map getUser(@RequestParam(value = "user_id", required = false) Integer userId){
		Map<String, Object> rsp = new HashMap<String, Object>();
    	if(userId == null){
    		rsp.put("status", false);
    		rsp.put("code", 99);
    		rsp.put("msg", "参数缺少或不合法");
    		rsp.put("data", null);
    		return rsp;
    	}
		rsp.put("data", userService.getUser(userId));
		rsp.put("status", true);
		rsp.put("code", 1);
		rsp.put("msg", "获取User数据成功");
		return rsp;
	}
}

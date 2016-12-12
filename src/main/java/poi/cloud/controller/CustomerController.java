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

import poi.cloud.service.CustomerService;

@Controller
public class CustomerController{
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 获取用户列表
	 * @param int page
	 * @return Map<String, Object>
	 * */
    @ResponseBody
    @RequestMapping(value = "/Customer/getCustomerList", method = RequestMethod.GET)
    public Map getUserList(@RequestParam(value = "page", required = false) Integer page) throws Exception{
    	Map<String, Object> rsp = new HashMap<String, Object>();
    	if(page == null){
    		rsp.put("status", false);
    		rsp.put("code", 99);
    		rsp.put("msg", "参数缺少或不合法");
    		return rsp;
    	}
    	
    	page = page - 1;
		int total = customerService.getCustomerCount(1);
		
		int pagePerNum = 10;
		int pageNum = new Double(Math.ceil((double)total / pagePerNum)).intValue();
		int offset = pagePerNum * page;
		List<Map> data = customerService.getCustomerList(offset, pagePerNum);
		rsp.put("total", total);
		rsp.put("data", data);
		rsp.put("page", pageNum);
		rsp.put("page_per_num", pagePerNum);
		return rsp;
    }
    
    /**
     * 检查customer code是否存在
     * @param code
     * @return Map
     * */
    @ResponseBody
    @RequestMapping(value="/Customer/checkCodeExist", method = RequestMethod.GET)
    public Map checkCustomerCodeExist(@RequestParam(value = "code", required = false) String code){
    	Map<String, Object> rsp = new HashMap<String, Object>();
    	if(code == null){
    		rsp.put("status", false);
    		rsp.put("code", 99);
    		rsp.put("msg", "参数缺少或不合法");
    		return rsp;
    	}
    	boolean status = customerService.checkCustomerCodeExist(code);
    	if(status){
    		rsp.put("status", false);
    		rsp.put("code", 0);
    		rsp.put("msg", "code已被使用，不可用");
    	}else{
    		rsp.put("status", true);
    		rsp.put("code", 1);
    		rsp.put("msg", "code未被使用，可用");
    	}
    	return rsp;
    }
    
    
    /**
     * 新增企业
     * @param String name
     * @param String code
     * @param String mac
     * @return Map
     * */
    @ResponseBody
    @RequestMapping(value="/Customer/addCustomer", method = RequestMethod.GET)
    public Map addCustomer(@RequestParam(value = "name", required = false) String name,
    					   @RequestParam(value = "code", required = false) String code,
    					   @RequestParam(value = "mac", required = false) String mac
    		){
    	Map<String, Object> rsp = new HashMap<String, Object>();
    	if(name == null || code == null || mac == null){
    		rsp.put("status", false);
    		rsp.put("code", 99);
    		rsp.put("msg", "参数缺少或不合法");
    		return rsp;
    	}
    	
    	boolean status = customerService.addCustomer(name, code, mac);
    	if(status){
    		rsp.put("status", true);
    		rsp.put("code", 1);
    		rsp.put("msg", "企业添加成功");
    	}else{
    		rsp.put("status", false);
    		rsp.put("code", 0);
    		rsp.put("msg", "企业添加失败");
    	}
    	return rsp;
    }
    
    /**
     * 更新企业信息
     * @param String mac
     * @param Integer id
     * @return Map
     * */
    @ResponseBody
    @RequestMapping(value="/Customer/editCustomer", method = RequestMethod.GET)
    public Map editCustomer(@RequestParam(value = "mac", required = false) String mac,
    						@RequestParam(value = "id", required = false) Integer id
    		){
    	Map<String, Object> rsp = new HashMap<String, Object>();
		if(mac == null || id == null){
    		rsp.put("status", false);
    		rsp.put("code", 99);
    		rsp.put("msg", "参数缺少或不合法");
    		return rsp;
    	}
		
		Map data = new HashMap<String, Object>();
		data.put("customer_mac", mac);
		data.put("id", id);
		boolean status = customerService.editCustomer(data);
		if(status){
			rsp.put("code", 1);
			rsp.put("msg", "企业信息更新成功");
			rsp.put("status", true);
		}else{
			rsp.put("code", 0);
			rsp.put("msg", "企业信息更新失败");
			rsp.put("status", false);
		}
		return rsp;
    }
    
    /**
     * 根据ID获取企业信息
     * @param Integer id
     * @return Map
     * */
    @ResponseBody
    @RequestMapping(value="/Customer/getCustomer", method = RequestMethod.GET)
    public Map getCustomer(@RequestParam(value = "id", required = false) String id){
    	Map<String, Object> rsp = new HashMap<String, Object>();
		if(id == null){
    		rsp.put("status", false);
    		rsp.put("code", 99);
    		rsp.put("msg", "参数缺少或不合法");
    		return rsp;
    	}
		return customerService.getCustomer("id", id);
    }
    
}

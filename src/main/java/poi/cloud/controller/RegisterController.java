package poi.cloud.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import poi.cloud.service.CustomerService;
import poi.cloud.service.DCAService;
import util.TimeUtil;
import util.ValidateUtil;

/**
 * @Description DCA服务注册控制器
 * @Author xing.liu
 * @Date 2016-11-15
 * */
@Controller
public class RegisterController {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private DCAService dcaService;
	/**
	 * DCA注册接口
	 * */
	@ResponseBody
    @RequestMapping(value="/Register/register", method = RequestMethod.GET)
	public Map<String, Object> register(@RequestParam(value = "token", required = false) String token,
								 @RequestParam(value = "healthy_check_interval", required = false) String healthyCheckInterval,
								 @RequestParam(value = "sync_data_interval", required = false) String syncDataInterval,
								 @RequestParam(value = "is_active", required = false) String isActive,
								 @RequestParam(value = "get_task_interval", required = false) String getTaskInterval,
								 @RequestParam(value = "log_path", required = false) String logPath,
								 @RequestParam(value = "timestamp", required = false) String timestamp,
								 @RequestParam(value = "dca_owner_code", required = false) String dcaOwnerCode,
								 @RequestParam(value = "mac", required = false) String mac,
								 @RequestParam(value = "ip", required = false) String ip,
								 @RequestParam(value = "dca_name", required = false) String dcaName
									){
		Map<String, Object> rsp = new HashMap<String, Object>();
		if(token == null || healthyCheckInterval == null || isActive == null || getTaskInterval == null
				|| logPath == null || timestamp == null || dcaOwnerCode == null || ip == null
				|| mac == null || dcaName == null || syncDataInterval == null){
			rsp.put("status", false);
    		rsp.put("code", 99);
    		rsp.put("msg", "参数缺少或不合法");
    		return rsp;
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("healthy_check_interval", healthyCheckInterval);
		params.put("sync_data_interval", syncDataInterval);
		params.put("is_active", isActive);
		params.put("get_task_interval", getTaskInterval);
		params.put("log_path", logPath);
		params.put("timestamp", timestamp);
		params.put("dca_owner_code", dcaOwnerCode);
		params.put("mac", mac);
		params.put("ip", ip);
		params.put("dca_name", dcaName);
		
		Map DCA = dcaService.checkDCAExist(dcaName);
		Map customer = customerService.getCustomer("customer_code", dcaOwnerCode);
		//检查是否第一次激活DCA
		if(DCA == null || DCA.isEmpty()){
			//写入新DCA数据
			params.remove("dca_owner_code");
			params.put("customer_id", customer.get("id").toString());
			params.put("register_time", TimeUtil.getDateTime("yyyy-MM-dd HH:mm:ss"));
			params.put("last_request_time", TimeUtil.getDateTime("yyyy-MM-dd HH:mm:ss"));
			params.put("status", "1");
			boolean st = dcaService.addDCA(params);
			params.put("dca_owner_code", dcaOwnerCode);
			if(st){
				rsp.put("status", true);
	    		rsp.put("code", 11);
	    		rsp.put("data", params);
	    		rsp.put("msg", "新DCA节点注册成功");
			}else{
				rsp.put("status", false);
	    		rsp.put("code", 12);
	    		rsp.put("data", params);
	    		rsp.put("msg", "新DCA节点注册失败");
			}
			return rsp;
		}else{
			//更新DCA状态数据,及最新数据响应
			boolean st = dcaService.updateStatus(DCA.get("dca_id").toString(), "1");
			dcaService.updateLastRequestTime(DCA.get("dca_id").toString());
			if(st){
				rsp.put("status", true);
	    		rsp.put("code", 13);
	    		rsp.put("data", DCA);
	    		rsp.put("msg", "DCA节点登记在线状态成功");
			}else{
				rsp.put("status", false);
	    		rsp.put("code", 14);
	    		rsp.put("data", DCA);
	    		rsp.put("msg", "DCA节点登记在线状态失败");
			}
			return rsp;
		}
	}
	
	
	/**
	 * DCA获取指令接口
	 * */
	@ResponseBody
    @RequestMapping(value="/Register/getTask", method = RequestMethod.GET)
	public Map getTask(@RequestParam(value = "token", required = false) String token,
					   @RequestParam(value = "timestamp", required = false) String timestamp,
					   @RequestParam(value = "ip", required = false) String ip,
					   @RequestParam(value = "mac", required = false) String mac,
					   @RequestParam(value = "dca_owner_code", required = false) String dcaOwnerCode,
					   @RequestParam(value = "dca_name", required = false) String dcaName
		){
			Map<String, Object> rsp = new HashMap<String, Object>();
			if(token == null || dcaOwnerCode == null || ip == null
				|| mac == null || timestamp == null || dcaName == null){
				rsp.put("status", false);
				rsp.put("code", 99);
				rsp.put("msg", "参数缺少或不合法");
				return rsp;
			}
			Map<String, String> params = new HashMap<String, String>();
			params.put("timestamp", timestamp);
			params.put("ip", ip);
			params.put("mac", mac);
			params.put("dca_owner_code", dcaOwnerCode);
			params.put("dca_name", dcaName);
			
			Map DCA = dcaService.checkDCAExist(dcaName);
			if(DCA == null || DCA.isEmpty()){
				rsp.put("status", false);
				rsp.put("code", 96);
				rsp.put("msg", "dca_name不存在");
				return rsp;
			}
			//更新DCA状态数据,及最新数据响应
			boolean st = dcaService.updateStatus(DCA.get("dca_id").toString(), "1");
			dcaService.updateLastRequestTime(DCA.get("dca_id").toString());
			rsp.put("data", DCA);
			rsp.put("status", true);
			rsp.put("code", 1);
			rsp.put("msg", "customer_id获取成功");
			return rsp;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/Register/getCustomerIdByCode", method = RequestMethod.GET)
	public Map getCustomerIdByCode(@RequestParam(value = "token", required = false) String token,
								   @RequestParam(value = "timestamp", required = false) String timestamp,
								   @RequestParam(value = "ip", required = false) String ip,
								   @RequestParam(value = "mac", required = false) String mac,
								   @RequestParam(value = "dca_owner_code", required = false) String dcaOwnerCode,
								   @RequestParam(value = "dca_name", required = false) String dcaName
			){
		Map<String, Object> rsp = new HashMap<String, Object>();
		if(token == null || dcaOwnerCode == null || ip == null
				|| mac == null || timestamp == null || dcaName == null){
			rsp.put("status", false);
    		rsp.put("code", 99);
    		rsp.put("msg", "参数缺少或不合法");
    		return rsp;
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("timestamp", timestamp);
		params.put("ip", ip);
		params.put("mac", mac);
		params.put("dca_owner_code", dcaOwnerCode);
		
		Map customer = customerService.getCustomer("customer_code", dcaOwnerCode);
		Map data = new HashMap<String, String>();
		data.put("dca_owner_code", dcaOwnerCode);
		data.put("customer_id", customer.get("id"));
		rsp.put("data", data);
		rsp.put("status", true);
		rsp.put("code", 1);
		rsp.put("msg", "customer_id获取成功");
		return rsp;	
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/Register/getDCA", method = RequestMethod.GET)
	public Map getDCA(@RequestParam(value = "token", required = false) String token,
								   @RequestParam(value = "timestamp", required = false) String timestamp,
								   @RequestParam(value = "ip", required = false) String ip,
								   @RequestParam(value = "mac", required = false) String mac,
								   @RequestParam(value = "dca_owner_code", required = false) String dcaOwnerCode,
								   @RequestParam(value = "dca_name", required = false) String dcaName
			){
		Map<String, Object> rsp = new HashMap<String, Object>();
		if(token == null || dcaOwnerCode == null || ip == null
				|| mac == null || timestamp == null || dcaName == null){
			rsp.put("status", false);
    		rsp.put("code", 99);
    		rsp.put("msg", "参数缺少或不合法");
    		return rsp;
		}
		Map DCA = dcaService.checkDCAExist(dcaName);
		rsp.put("data", DCA);
		rsp.put("status", true);
		rsp.put("code", 1);
		rsp.put("msg", "DCA信息获取成功");
		return rsp;	
	}
}

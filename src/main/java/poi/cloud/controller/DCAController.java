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

import poi.cloud.service.DCAService;

/**
 * @Description DCA Controller
 * @Author xing.liu
 * @Date 2016-11-18
 * */
@Controller
public class DCAController{
	@Autowired
	private DCAService dcaService;
	
	/**
	 * 获取某个DCA信息
	 * @param int index
	 * @param int limit
	 * @return Map
	 * */
	@ResponseBody
    @RequestMapping(value="/DCA/getDCA", method = RequestMethod.GET)
	public Map getDCA(@RequestParam(value = "dca_id", required = false) Integer dcaId){
		Map<String, Object> rsp = new HashMap<String, Object>();
    	if(dcaId == null){
    		rsp.put("status", false);
    		rsp.put("code", 99);
    		rsp.put("msg", "参数缺少或不合法");
    		rsp.put("data", null);
    		return rsp;
    	}
		rsp.put("data", dcaService.getDCA(dcaId));
		rsp.put("status", true);
		rsp.put("code", 1);
		rsp.put("msg", "获取DCA数据成功");
		return rsp;
	}
	
	/**
	 * 获取DCA列表
	 * @param int page
	 * @return List<Map>
	 * */
	@ResponseBody
	@RequestMapping(value="/DCA/getDCAList", method = RequestMethod.GET)
	public Map getDCAList(@RequestParam(value = "page", required = false) Integer page){
		Map<String, Object> rsp = new HashMap<String, Object>();
		if(page == null){
    		rsp.put("status", false);
    		rsp.put("code", 99);
    		rsp.put("msg", "参数缺少或不合法");
    		return rsp;
    	}
		page = page - 1;
		int total = dcaService.getDCACount(1);
		
		int pagePerNum = 10;
		int pageNum = new Double(Math.ceil((double)total / pagePerNum)).intValue();
		int offset = pagePerNum * page;
		System.out.println(offset);
		List<Map> data = dcaService.getDCAList(offset, pagePerNum);
		rsp.put("total", total);
		rsp.put("data", data);
		rsp.put("page", pageNum);
		rsp.put("page_per_num", pagePerNum);
		return rsp;
	}
	
	/**
	 * DCA信息修改
	 * @param int healthyCheckInterval
	 * @param int syncDataInterval
	 * @param int isActive
	 * @param int getTaskInterval
	 * @return Map
	 * */
	@ResponseBody
	@RequestMapping(value="/DCA/editDCA", method = RequestMethod.GET)
	public Map editDCA(@RequestParam(value = "healthy_check_interval", required = false) Integer healthyCheckInterval,
					   @RequestParam(value = "sync_data_interval", required = false) Integer syncDataInterval,
					   @RequestParam(value = "is_active", required = false) String isActive,
					   @RequestParam(value = "get_task_interval", required = false) Integer getTaskInterval,
					   @RequestParam(value = "dca_id", required = false) Integer dcaId
					){
		Map<String, Object> rsp = new HashMap<String, Object>();
		if(healthyCheckInterval == null || syncDataInterval == null || isActive == null || getTaskInterval == null || dcaId == null){
    		rsp.put("status", false);
    		rsp.put("code", 99);
    		rsp.put("msg", "参数缺少或不合法");
    		return rsp;
    	}
		
		Map data = new HashMap<String, Object>();
		data.put("healthy_check_interval", healthyCheckInterval);
		data.put("sync_data_interval", syncDataInterval);
		data.put("is_active", isActive);
		data.put("get_task_interval", getTaskInterval);
		data.put("dca_id", dcaId);
		boolean status = dcaService.updateDCA(data);
		if(status){
			rsp.put("code", 1);
			rsp.put("msg", "DCA信息更新成功");
			rsp.put("status", true);
		}else{
			rsp.put("code", 0);
			rsp.put("msg", "DCA信息更新失败");
			rsp.put("status", false);
		}
		return rsp;
	}
}

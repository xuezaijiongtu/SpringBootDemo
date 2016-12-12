package poi.cloud.service;

import java.util.List;
import java.util.Map;

/**
 * @Description DCA Service
 * @Author xing.liu
 * @Date 2016-11-18
 * */
public interface DCAService {
	/**
	 * @param int offset
	 * @param int limit
	 * @return List<DCABean>
	 * */
	public List<Map> getDCAList(int offset, int limit);
	
	/**
	 * @param int dcaId
	 * @return DCABean
	 * */
	public Map getDCA(int dcaId);
	
	/**
	 * @param Map<String, Object> data
	 * @param Map<String, Object> condition
	 * @return void
	 * */
	public boolean updateDCA(Map<String, Object> data);
	
	/**
	 * 根据条件获取DCA个数
	 * @param int offset
	 * @return int
	 * */
	public int getDCACount(int offset);
	
	/**
	 * 检查DCA是否存在，如果存在返回该DCA数据
	 * @param String dcaName
	 * @return Map
	 * */
	public Map checkDCAExist(String dcaName); 
	
	/**
	 * 添加DCA
	 * @param Map data
	 * @return boolean
	 * */
	public boolean addDCA(Map data);
	
	/**
	 * 更新状态
	 * @param String dcaId
	 * @param String status
	 * @return boolean
	 * */
	public boolean updateStatus(String dcaId, String status);
	
	/**
	 * 更新DCA last_request_time
	 * @return void
	 * */
	public void updateLastRequestTime(String dcaId);
}

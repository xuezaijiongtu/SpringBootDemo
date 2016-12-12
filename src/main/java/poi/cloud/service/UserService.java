package poi.cloud.service;

import java.util.List;
import java.util.Map;

/**
 * @Description UserService Interface
 * @Author xing.liu
 * @Date 2016-11-23
 * */
public interface UserService {
	/**
	 * @param int offset
	 * @param int limit
	 * @return List<Map>
	 * */
	public List<Map> getUserList(int offset, int limit);
	
	/**
	 * 根据条件获取User数
	 * @param int offset
	 * @return int
	 * */
	public int getUserCount(int offset);
	
	/**
	 * @param int userId
	 * @return Map
	 * */
	public Map getUser(int userId);
}

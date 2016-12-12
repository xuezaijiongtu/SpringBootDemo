package poi.cloud.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poi.cloud.dao.UserDao;
import poi.cloud.service.UserService;

/**
 * @Description UserService Impl
 * @Author xing.liu
 * @Date 2016-11-23
 * */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	/**
	 * @param int offset
	 * @param int limit
	 * @return List<Map>
	 * */
	public List<Map> getUserList(int offset, int limit){
		return userDao.getUserList(offset, limit);
	}
	
	/**
	 * 根据条件获取User数
	 * @param int offset
	 * @return int
	 * */
	public int getUserCount(int offset){
		return userDao.getUserCount(offset);
	}
	
	/**
	 * @param int userId
	 * @return Map
	 * */
	public Map getUser(int userId){
		if(userId == 0){
			return null;
		}
		return userDao.getUser(userId);
	}
}

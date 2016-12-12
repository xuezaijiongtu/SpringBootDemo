package poi.cloud.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import poi.cloud.mapper.UserMapper;

@Repository
public class UserDao {
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * @param int offset
	 * @param int limit
	 * @return List<Map>
	 * */
	public List<Map> getUserList(int offset, int limit){
		return userMapper.getUserList(offset, limit);
	}
	
	/**
	 * @param offset
	 * @return int
	 * */
	public int getUserCount(int offset){
		return userMapper.getUserCount(offset);
	}
	
	/**
	 * @param int userId
	 * @return Map
	 * */
	public Map getUser(int userId){
		return userMapper.getUser(userId);
	}
}

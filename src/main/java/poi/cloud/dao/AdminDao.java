package poi.cloud.dao;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import poi.cloud.mapper.AdminMapper;

/**
 * @Description Admin Dao
 * @Author xing.liu
 * @Date 2016-11-28
 * */
@Repository
public class AdminDao {
	@Autowired
	private AdminMapper adminMapper;
	
	/**
	 * 根据name获取管理员信息，name唯一
	 * */
	public Map getAdminInfo(String name){
		return adminMapper.getAdminInfo(name);
	}
	
	/**
	 * 更新上次登录时间
	 * */
	public boolean updateLastLoginTime(Map data){
		return adminMapper.updateLastLoginTime(data);
	}
}

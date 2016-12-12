package poi.cloud.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AdminMapper {
	@Select("SELECT id, name AS username, pwd AS password FROM poi_admin WHERE name = #{name}")
	public Map getAdminInfo(@Param("name") String name);
	
	/**
	 * @param Map<String, Object> data
	 * @return boolean
	 * */
	@Update("UPDATE poi_admin SET last_login_time = #{last_login_time} WHERE id = #{id}")
	public boolean updateLastLoginTime(Map data);
}

package poi.cloud.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
	@Select("SELECT user.id AS id,"
			    + " user.name AS name,"
			    + " user.email AS email,"
			    + " user.nickname AS nickname,"
			    + " user.pwd AS pwd,"
			    + " user.age AS age,"
			    + " user.status AS status,"
			    + " user.last_login_time AS last_login_time,"
			    + " poi_customer.customer_name"
			    + " FROM user"
			    + " LEFT JOIN poi_customer"
			    + " ON user.customer_id = poi_customer.id"
			    + " ORDER BY id DESC"
			    + " LIMIT #{offset}, #{limit}")
	public List<Map> getUserList(@Param("offset") int offset, @Param("limit") int limit);
	
	/**
	 * @param offset
	 * @return int
	 * */
	@Select("SELECT count(id) AS num FROM user WHERE id >= #{offset}")
	public int getUserCount(@Param("offset") int offset);
	
	@Select("SELECT user.id AS id,"
		    + " user.name AS name,"
		    + " user.email AS email,"
		    + " user.nickname AS nickname,"
		    + " user.pwd AS pwd,"
		    + " user.age AS age,"
		    + " user.status AS status,"
		    + " user.last_login_time AS last_login_time,"
		    + " poi_customer.customer_name"
		    + " FROM user"
		    + " LEFT JOIN poi_customer"
		    + " ON user.customer_id = poi_customer.id"
		    + " WHERE user.id = #{id}")
public Map getUser(@Param("id") int id);
}

package poi.cloud.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
public interface CustomerMapper {
	@Select("SELECT * FROM poi_customer ORDER BY id DESC LIMIT #{offset}, #{limit}")
	public List<Map> getCustomerList(@Param("offset") int offset, @Param("limit") int limit);
	
	@Select("SELECT count(id) AS num FROM poi_customer WHERE id >= #{offset}")
	public int getCustomerCount(@Param("offset") int offset);
	
	@Select("SELECT count(id) AS num FROM poi_customer WHERE customer_code = #{code}")
	public int checkCustomerCodeExist(@Param("code") String code);
	
	@Insert("INSERT INTO poi_customer(customer_name, customer_code, customer_key, customer_mac) VALUES(#{customer_name}, #{customer_code}, #{customer_key}, #{customer_mac})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public boolean addCustomer(Map data);
	
	/**
	 * @param Map<String, Object> data
	 * @return boolean
	 * */
	@Update("UPDATE poi_customer SET customer_mac = #{customer_mac} WHERE id = #{id}")
	public boolean updateCustomer(Map data);
	
	@Select("SELECT * FROM poi_customer WHERE ${key} = #{value}")
	public Map getCustomer(@Param("key") String key, @Param("value") String value);
}

package poi.cloud.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DCAMapper {

	/**
	 * @param int offset
	 * @param int limit
	 * @return List<Map>
	 * */
	@Select("SELECT poi_dca.dca_id AS id,"
			+ " poi_dca.mac AS mac,"
			+ " poi_dca.is_active AS is_active,"
			+ " poi_dca.log_path AS log_path,"
			+ " poi_dca.healthy_check_interval AS healthy_check_interval,"
			+ " poi_dca.ip AS ip,"
			+ " poi_dca.get_task_interval AS get_task_interval,"
			+ " poi_dca.sync_data_interval AS sync_data_interval,"
			+ " poi_dca.dca_name AS dca_name,"
			+ " poi_dca.last_request_time AS last_request_time,"
			+ " poi_dca.register_time AS register_time,"
			+ " poi_dca.status AS status,"
			+ " poi_customer.customer_name AS customer_name"
			+ " FROM poi_dca LEFT JOIN poi_customer"
			+ " ON poi_dca.customer_id = poi_customer.id"
			+ " ORDER BY poi_dca.dca_id DESC"
			+ " LIMIT #{offset}, #{limit}")
	public List<Map> getDCAList(@Param("offset") int offset, @Param("limit") int limit);
	
	/**
	 * @param offset
	 * @return int
	 * */
	@Select("SELECT count(dca_id) AS num FROM poi_dca WHERE dca_id >= #{offset}")
	public int getDCACount(@Param("offset") int offset);
	
	/**
	 * @param int dcaId
	 * @return Map
	 * */
	@Select("SELECT poi_dca.dca_id AS id,"
			+ " poi_dca.mac AS mac,"
			+ " poi_dca.is_active AS is_active,"
			+ " poi_dca.log_path AS log_path,"
			+ " poi_dca.get_task_interval AS get_task_interval,"
			+ " poi_dca.healthy_check_interval AS healthy_check_interval,"
			+ " poi_dca.ip AS ip,"
			+ " poi_dca.sync_data_interval AS sync_data_interval,"
			+ " poi_dca.dca_name AS dca_name,"
			+ " poi_dca.last_request_time AS last_request_time,"
			+ " poi_dca.register_time AS register_time,"
			+ " poi_dca.status AS status,"
			+ " poi_customer.customer_name AS customer_name"
			+ " FROM poi_dca LEFT JOIN poi_customer"
			+ " ON poi_dca.customer_id = poi_customer.id"
			+ " WHERE dca_id = #{dcaId}")
	public Map getDCA(int dcaId);
	
	/**
	 * @param Map<String, Object> data
	 * @param Map<String, Object> condition
	 * @return void
	 * */
	@Update("UPDATE poi_dca SET healthy_check_interval = #{healthy_check_interval},"
			+ "sync_data_interval = #{sync_data_interval},"
			+ "is_active = #{is_active},"
			+ "get_task_interval = #{get_task_interval} "
			+ " WHERE dca_id = #{dca_id}")
	public boolean updateDCA(Map<String, Object> data);
	
	@Select("SELECT * FROM poi_dca WHERE dca_name = #{dcaName} LIMIT 1")
	public Map checkDCAExist(String dcaName);

	@Insert("INSERT INTO poi_dca(dca_name, healthy_check_interval,"
			+ " sync_data_interval, is_active, get_task_interval,"
			+ " log_path, ip, mac, status, customer_id, register_time)"
			+ " VALUES(#{dca_name}, #{healthy_check_interval}, #{sync_data_interval},"
			+ " #{is_active}, #{get_task_interval}, #{log_path}, #{ip}, #{mac},"
			+ " #{status}, #{customer_id}, #{register_time})")
	public boolean addDCA(Map data);
	
	/**
	 * @param Map<String, Object> data
	 * @param Map<String, Object> condition
	 * @return void
	 * */
	@Update("UPDATE poi_dca SET status = #{status}, last_request_time = NOW() WHERE dca_id = #{dca_id}")
	public boolean updateStatus(@Param("dca_id") String dcaId, @Param("status") String status);
	
	@Update("UPDATE poi_dca SET last_request_time = NOW() WHERE dca_id = #{dca_id}")
	public void updateLastRequestTime(@Param("dca_id") String dcaId);
}

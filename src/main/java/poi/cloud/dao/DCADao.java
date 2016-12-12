package poi.cloud.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import poi.cloud.mapper.DCAMapper;

@Repository
public class DCADao {
	@Autowired
	private DCAMapper dcaMapper;
	
	/**
	 * @param int offset
	 * @param int limit
	 * @return List<Map>
	 * */
	public List<Map> getDCAList(int offset, int limit){
		return dcaMapper.getDCAList(offset, limit);
	}
	
	/**
	 * @param int dcaId
	 * @return Map
	 * */
	public Map getDCA(int dcaId){
		return dcaMapper.getDCA(dcaId);
	}
	
	/**
	 * @param Map<String, Object> data
	 * @return void
	 * */
	public boolean updateDCA(Map<String, Object> data){
		return dcaMapper.updateDCA(data);
	}
	
	/**
	 * 根据条件获取DCA个数
	 * @param int offset
	 * @return int
	 * */
	public int getDCACount(int offset){
		return dcaMapper.getDCACount(offset);
	}
	
	/**
	 * 检查DCA是否存在，如果存在返回该DCA数据
	 * @param String dcaName
	 * @return Map
	 * */
	public Map checkDCAExist(String dcaName){
		return dcaMapper.checkDCAExist(dcaName);
	}
	
	/**
	 * 添加DCA
	 * @param Map data
	 * @return boolean
	 * */
	public boolean addDCA(Map data){
		return dcaMapper.addDCA(data);
	}
	
	/**
	 * 更新DCA在线状态
	 * @param String dcaId
	 * @param String status
	 * @return boolean
	 * */
	public boolean updateStatus(String dcaId, String status){
		return dcaMapper.updateStatus(dcaId, status);
	}
	
	/**
	 * 更新DCA last_request_time
	 * @return void
	 * */
	public void updateLastRequestTime(String dcaId){
		dcaMapper.updateLastRequestTime(dcaId);
	}
	
}

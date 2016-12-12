package poi.cloud.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import poi.cloud.dao.DCADao;
import poi.cloud.service.DCAService;
import util.TimeUtil;

/**
 * @Description DCA ServiceImpl实现
 * @Author xing.liu
 * @Date 2016-11-18
 * */
@Service
public class DCAServiceImpl implements DCAService{
	@Autowired
	private DCADao dcaDao;
	
	/**
	 * @param int offset
	 * @param int limit
	 * @return List<DCABean>
	 * */
	public List<Map> getDCAList(int offset, int limit){
		if(limit == 0){
			return null;
		}
		return dcaDao.getDCAList(offset, limit);
	}
	
	/**
	 * @param int dcaId
	 * @return DCABean
	 * */
	public Map getDCA(int dcaId){
		if(dcaId == 0){
			return null;
		}
		return dcaDao.getDCA(dcaId);
	}
	
	/**
	 * @param Map<String, Object> data
	 * @return void
	 * */
	public boolean updateDCA(Map<String, Object> data){
		if(data.isEmpty()){
			return false;
		}
		return dcaDao.updateDCA(data);
	}
	
	/**
	 * 根据条件获取DCA个数
	 * @param int offset
	 * @return int
	 * */
	public int getDCACount(int offset){
		return dcaDao.getDCACount(offset);
	}
	
	/**
	 * 检查DCA是否存在，如果存在返回该DCA数据
	 * @param String dcaName
	 * @return Map
	 * */
	public Map checkDCAExist(String dcaName){
		return dcaDao.checkDCAExist(dcaName);
	}
	
	/**
	 * 添加DCA
	 * @param Map data
	 * @return boolean
	 * */
	public boolean addDCA(Map data){
		return dcaDao.addDCA(data);
	}
	
	/**
	 * 更新DCA在线状态
	 * @param String dcaId
	 * @param String status
	 * @return boolean
	 * */
	public boolean updateStatus(String dcaId, String status){
		return dcaDao.updateStatus(dcaId, status);
	}
	
	/**
	 * 下线长时间不更新的DCA节点
	 * 30s检查一次节点
	 * */
	@Scheduled(fixedRate = 30000)
	public void setDCAOffline(){
		System.out.println("DCA在线检测启动...");
		List<Map> dcaList = getDCAList(0, 100000);
		if(dcaList != null && dcaList.size() > 0){
			for(Map dca : dcaList){
				String lastUpdateTime = TimeUtil.formatDate(dca.get("last_request_time").toString());
				int lut = TimeUtil.strToTimestamp(lastUpdateTime);
				int nowTime = (int)(TimeUtil.getTimestamp() / 1000);
				int getTaskInterval = Integer.parseInt(dca.get("get_task_interval").toString());
				if(((nowTime - lut) > (getTaskInterval + 5)) && dca.get("status").toString().equals("1")){
					dcaDao.updateStatus(dca.get("id").toString(), "0");
					System.out.println("下线DCA节点:"+dca.get("dca_name"));
				}
			}
		}
		System.out.println("DCA在线检测完成...");
	}
	
	/**
	 * 更新DCA last_request_time
	 * @return void
	 * */
	public void updateLastRequestTime(String dcaId){
		dcaDao.updateLastRequestTime(dcaId);
	}
}

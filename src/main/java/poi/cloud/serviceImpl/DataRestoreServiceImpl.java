package poi.cloud.serviceImpl;

import org.springframework.scheduling.annotation.Scheduled;

import poi.cloud.service.DataRestoreService;

public class DataRestoreServiceImpl implements DataRestoreService{
	/**
	 * 检查数据完整性
	 * */
	@Scheduled(fixedRate = 60000)
	public void runDataRestore(){
		
	}
}

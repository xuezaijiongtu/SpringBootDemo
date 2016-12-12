package poi.cloud.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poi.cloud.dao.CustomerDao;
import poi.cloud.service.CustomerService;
import util.EncryptService;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerDao customerDao;
	
	/**
	 * @param int offset
	 * @param int limit
	 * @return List<Map>
	 * */
	@Override
	public List<Map> getCustomerList(int offset, int limit){
		List<Map> customerList = customerDao.getCustomerList(offset, limit);
		return customerList;
	}
	
	/**
	 * @param int offset
	 * @return int
	 * */
	@Override
	public int getCustomerCount(int offset){
		return customerDao.getCustomerCount(offset);
	}
	
	@Override
	public boolean addCustomer(String name, String code, String mac){
		Map data = new HashMap<String, Object>();
		data.put("customer_name", name);
		data.put("customer_code", code);
		data.put("customer_key", createCustomerKey(code));
		data.put("customer_mac", mac);
		return customerDao.addCustomer(data);
	}
	
	
	/**
	 * 获取customer的Key
	 * @param String salt
	 * @return String
	 * */
	@Override
	public String createCustomerKey(String salt){
		long timestamp = System.currentTimeMillis();
		salt = timestamp + salt;
		return EncryptService.MD5(salt);
	}
	
	/**
	 * 检查customer的code是否已经被使用
	 * @param String code
	 * @return boolean
	 * */
	@Override
	public boolean checkCustomerCodeExist(String code){
		return customerDao.checkCustomerCodeExist(code);
	}
	
	
	/**
	 * 修改Customer信息
	 * @param Map data
	 * @return boolean
	 * */
	@Override
	public boolean editCustomer(Map data){
		return customerDao.editCustomer(data);
	}
	
	/**
	 * 根据条件获取企业信息
	 * @param String key
	 * @param String value
	 * @return Map
	 * */
	public Map getCustomer(String key, String value){
		return customerDao.getCustomer(key, value);
	}
}

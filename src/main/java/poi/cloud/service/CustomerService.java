package poi.cloud.service;

import java.util.List;
import java.util.Map;

public interface CustomerService {
	/**
	 * @param int offset
	 * @param int limit
	 * @return List<Map>
	 * */
	public List<Map> getCustomerList(int offset, int limit);
	
	/**
	 * @param int offset
	 * @return int
	 * */
	public int getCustomerCount(int offset);
	
	public boolean addCustomer(String name, String code, String mac);
	
	/**
	 * @param String salt
	 * @return String
	 * */
	public String createCustomerKey(String salt);
	
	/**
	 * 检查customer的code是否已经被使用
	 * @param String code
	 * @return boolean
	 * */
	public boolean checkCustomerCodeExist(String code);
	
	/**
	 * 修改Customer信息
	 * @param Map data
	 * @return boolean
	 * */
	public boolean editCustomer(Map data);
	
	/**
	 * 根据条件获取企业信息
	 * @param String key
	 * @param String value
	 * @return Map
	 * */
	public Map getCustomer(String key, String value);
}

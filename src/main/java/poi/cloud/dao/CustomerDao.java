package poi.cloud.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import poi.cloud.mapper.CustomerMapper;
import poi.cloud.mapper.DCAMapper;

@Repository
public class CustomerDao {
	@Autowired
	private CustomerMapper customerMapper;
	
	/**
	 * @param int offset
	 * @param int limit
	 * @return List<Map>
	 * */
	public List<Map> getCustomerList(int offset, int limit) {
		List<Map> customerList = customerMapper.getCustomerList(offset, limit);
		return customerList;
	}
	
	/**
	 * @param int offset
	 * @return int
	 * */
	public int getCustomerCount(int offset){
		return customerMapper.getCustomerCount(offset);
	}

	
	/**
	 * 
	 * */
	public boolean addCustomer(Map data) {
		return customerMapper.addCustomer(data);
	}
	
	/**
	 * @param String code
	 * @return boolean
	 * */
	public boolean checkCustomerCodeExist(String code){
		int num = customerMapper.checkCustomerCodeExist(code);
		if(num == 0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 修改Customer信息
	 * @param Map data
	 * @return boolean
	 * */
	public boolean editCustomer(Map data){
		return customerMapper.updateCustomer(data);
	}
	
	
	/**
	 * 根据条件获取企业信息
	 * @param String condition
	 * @return Map
	 * */
	public Map getCustomer(String key, String value){
		return customerMapper.getCustomer(key, value);
	}
}

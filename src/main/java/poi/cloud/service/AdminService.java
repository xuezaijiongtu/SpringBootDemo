package poi.cloud.service;

import java.util.Map;

/**
 * @Description Admin Service Interface
 * @Author xing.liu
 * @Date 2016-11-25
 * */
public interface AdminService {
	/**
	 * 检查是否登陆
	 * @return boolean
	 * */
	public boolean checkLogin();
	
	/**
	 * 获取Admin信息
	 * @param String name
	 * @return Map
	 * */
	public Map getAdminInfo(String username);
	
	/**
	 * 检查密码是否正确
	 * @param String password 传入密码
	 * @param String dbSavePwd 数据库存储密码
	 * @return boolean
	 * */
	public boolean checkPassword(String password, String dbSavePwd);
	
	/**
	 * 检查验证码是否正确
	 * @param String code
	 * @return boolean
	 * */
	public boolean checkVerifyCode(String code);
	
	/**
	 * 登陆操作，写入相关信息
	 * @param String id
	 * @param String name
	 * @return boolean
	 * */
	public boolean login(String id, String name);
}

package poi.cloud.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.kaptcha.Constants;

import poi.cloud.dao.AdminDao;
import poi.cloud.service.AdminService;
import util.EncryptService;
import util.SessionUtil;

/**
 * @Description Admin Service Impl
 * @Author xing.liu
 * @Date 2016-11-25
 * */
@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDao adminDao;
	
	/**
	 * 检查是否登陆
	 * @return boolean
	 * */
	public boolean checkLogin(){
		//简易方式
		String name = SessionUtil.getSession("username");
		String time = SessionUtil.getSession("last_login_time");
		String id = SessionUtil.getSession("id");
		if(name == null || time == null || id == null){
			return false;
		}
		
		if(name.isEmpty() || time.isEmpty() || id.isEmpty()){
			return false;
		}
		
		//登陆时效判断
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date;
		try {
			date = simpleDateFormat.parse(time);
			long ti = date.getTime();
			Date nt = new Date();
			long timestamp = nt.getTime() - ti;
			long ts = 60 * 60 * 24;
			if(timestamp > ts){
				SessionUtil.deleteSession("username");
				SessionUtil.deleteSession("last_login_time");
				SessionUtil.deleteSession("id");
				return false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * 获取Admin信息
	 * @param String name
	 * @return Map
	 * */
	public Map getAdminInfo(String name){
		return adminDao.getAdminInfo(name);
	}
	
	/**
	 * 检查密码是否正确
	 * @param String password 传入密码
	 * @param String dbSavePwd 数据库存储密码
	 * @return boolean
	 * */
	public boolean checkPassword(String password, String dbSavePwd){
		String pwd = EncryptService.MD5(password);
		if(pwd.equals(dbSavePwd)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 检查验证码是否正确
	 * @param String code
	 * @return boolean
	 * */
	public boolean checkVerifyCode(String code){
		String varifyCode = SessionUtil.getSession(Constants.KAPTCHA_SESSION_KEY);
		if(varifyCode != null && varifyCode.equals(code)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 登陆操作，写入相关信息
	 * @param String id
	 * @param String name
	 * @return boolean
	 * */
	public boolean login(String id, String name){
		Date nt = new Date();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dm = fm.format(nt);
		SessionUtil.setSession("id", id);
		SessionUtil.setSession("username", name);
		SessionUtil.setSession("last_login_time", dm);
		
		//更新用户登录时间
		Map data = new HashMap<String, Object>();
		data.put("last_login_time", dm);
		data.put("id", id);
		boolean status = adminDao.updateLastLoginTime(data);
		return status;
	}
}

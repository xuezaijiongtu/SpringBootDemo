package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * @Description 时间工具类
 * @Author xing.liu
 * @Date 2016-11-03
 * */
public class TimeUtil {
	/**
	 * 获取当前时间的时间戳
	 * */
	public static long getTimestamp(){
		return System.currentTimeMillis();
	}
	
	/**
	 * 获取某种时间格式的时间
	 * */
	public static String getDateTime(String format){
		Date date = new Date();
		SimpleDateFormat fm = new SimpleDateFormat(format);
		String dm = fm.format(date);
		return dm;
	}
	
	/** 
	  * 格式化字符串，解决从MySql中查出来的数据后面多了0的问题 
	  * @param date 
	  * @return 
	  */  
	 public static String formatDate(String date){
		Date dt = null;
		try {
			SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dt = dtf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dt);  
	 }
	 
	 /**
	  * 字符串转成时间戳
	  * */
	 public static int strToTimestamp(String datetime){
		 Date dt = null;
		 int time = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dt = sdf.parse(datetime);
			long times = dt.getTime();
			time = (int)(times / 1000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	 }
}

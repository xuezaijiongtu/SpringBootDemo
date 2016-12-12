package util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
/**
 * @Description 验证token工具类
 * @Author xing.liu
 * @Date 2016-11-16
 * */
public class ValidateUtil {
	/**
	 * 对map按照key进行排序
	 * @param Map<String, String> map
	 * @return SortedMap<String, String>
	 * */
	public static SortedMap<String, String> sortMapByKey(Map<String, String> map)
	{
		if (map == null || map.isEmpty()) {
			return null;
		}
		SortedMap<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparatorService());
		sortMap.putAll(map);
		return sortMap;
	}
	
	/**
	 * 校验token是否正确
	 * @param Map<String, String> parameters
	 * @param String key 
	 * @param String token
	 * @return boolean
	 * */
	public static boolean checkValidation(Map<String,String> parameters, String key, String token){
		if(parameters.isEmpty() || token.isEmpty() || key.isEmpty()){
			return false;
		}
		
		String tk = ValidateUtil.createSign(parameters, key);
		if(tk.equals(token)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 生成token
	 * token = sha1(md5(ksort(params)+"&key=")+key)
	 * @param Map<String, String> parameters
	 * @param String key
	 * @return String
	 * */
	public static String createSign(Map<String,String> parameters, String key)
	{
		parameters = sortMapByKey(parameters);
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			Object v = entry.getValue();
			if(null != v) {
				sb.append(k + "=" + v + "&");
			}
		}
		String sign = sb.toString();
		//System.out.println(sign);
		sign = EncryptService.SHA1(EncryptService.MD5(sign.substring(0, sign.length()-1) + "&key=") + key);
		return sign;
	}
}

class MapKeyComparatorService implements Comparator<String>{
	/**
	 * 比较字符串
	 * @param String str1
	 * @param String str2
	 * @return int
	 * */
    public int compare(String str1, String str2) {  
        return str1.compareTo(str2);  
    }  
}

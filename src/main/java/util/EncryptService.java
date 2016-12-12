package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * @Description 加密工具类
 * @Author xing.liu
 * @Date 2016-11-03
 * */
public class EncryptService {
	/**
	 * SHA1加密算法
	 * */
	public static String SHA1(String decript)
	{
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * MD5加密算法
	 * */
	public static String MD5(String input)
	{
		try {
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(input.getBytes());
			byte[] md = mdInst.digest();
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < md.length; i++) {
				String shaHex = Integer.toHexString(md[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}

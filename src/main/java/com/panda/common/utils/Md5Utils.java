package com.panda.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

/**
 * 
 * @ClassName: Md5Utils    
 * @Description: Md5工具类    
 * @author zsk 471035438@qq.com   
 * @date 2016年7月25日 下午7:37:55
 */
public class Md5Utils {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}
	/**
	 *  
	 * @Title: sign 
	 * @Description: 签名
	 * @author zsk 471035438@qq.com
	 * @param text 签名参数
	 * @param key 密匙 
	 * @param input_charset 编码
	 * @return    
	 * String   
	 * @throws
	 */
	public static String sign(String text, String key, String input_charset) {
	    	text = text + key;
	        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
	  }
	
	

public static String signRequest(Map<String, Object> params) throws IOException {
    // 第一步：对参数进行ASCII排序
    String[] keys = params.keySet().toArray(new String[0]);
    Arrays.sort(keys);
    // 第二步：把所有参数名和参数值串在一起
    StringBuilder query = new StringBuilder();
    for (String key : keys) {
        Object value = params.get(key);
        query.append(key).append(value);
    }
    // 第三步：md5加密
    return DigestUtils.md5Hex(query.toString());
}

	 /**
	  * 
	  * @Title: getContentBytes 
	  * @Description: 获取前面字节数组
	  * @author zsk 471035438@qq.com
	  * @param content
	  * @param charset
	  * @return    
	  * byte[]   
	  * @throws
	  */
	 private static byte[] getContentBytes(String content, String charset) {
	        if (charset == null || "".equals(charset)) {
	            return content.getBytes();
	        }
	        try {
	            return content.getBytes(charset);
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
	        }
	    }
	/**
	 * 
	 * @Title: byteToHexString 
	 * @Description: 将字节数组加密
	 * @author zsk 471035438@qq.com
	 * @param @param b
	 * @param @return    
	 * @return String   
	 * @throws
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	/**
	 * 
	 * @Title: MD5Encode 
	 * @Description: 将字符串加密
	 * @author zsk 471035438@qq.com
	 * @param @param origin
	 * @param @return    
	 * @return String   
	 * @throws
	 */
	public static String encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex) {

		}
		return resultString;
	}

} 
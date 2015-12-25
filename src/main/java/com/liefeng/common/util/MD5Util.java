package com.liefeng.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MD5工具类
 * @author Huangama
 * @date 2015-12-22
 */
public class MD5Util {

	private static Logger logger = LoggerFactory.getLogger(MD5Util.class);
	
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	private static MessageDigest messagedigest = null;
	
	static {
        try { 
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
        	logger.error("Get MD5 instance failed: ", e);
        }
    }
	
	public static String eccrypt(String info) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] srcBytes = info.getBytes();
			md5.update(srcBytes);
			byte[] resultBytes = md5.digest();
			return toHex(resultBytes).toUpperCase();
		} catch (Exception e) {
			logger.error("MD5 eccrypt failed: ", e);
		}
		return null;
	}

	private static String toHex(byte buffer[]) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 15, 16));
		}
		return sb.toString();
	}
	
	
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}
	
	/**
	 * 小写32位加密
	 * @param origin
	 * @return
	 */
	public static String encode32L(String origin) {
		String resultString = null;
		byte[] originBytes = origin.getBytes();
		try {
			resultString = new String(origin);
			resultString = byteArrayToHexString(messagedigest.digest(originBytes));
		} catch (Exception ex) {
		}
		return resultString;
	}

	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}
	
}

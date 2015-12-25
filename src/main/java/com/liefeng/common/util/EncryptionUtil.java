package com.liefeng.common.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 加解密工具类
 * @author Huangama
 * @date 2015-12-18
 */
public class EncryptionUtil {

	private static Logger logger = LoggerFactory.getLogger(EncryptionUtil.class);
	
	/**
	 * 客户身份证号码加密口令
	 */
	private static final String CUST_ID_NUM_PASSWORD = "!iD_nUm.*89";
	
	/**
	 * JAVA6支持以下任意一种算法:1)PBEWITHMD5ANDDES;2)PBEWITHMD5ANDTRIPLEDES;
	 * 3)PBEWITHSHAANDDESEDE;4)PBEWITHSHA1ANDRC2_40;5)PBKDF2WITHHMACSHA1
	 * 本类使用的算法为基于口令加密的:PBEWITHMD5andDES(Password Base Encryption)。
	 * 其特点是使用口令代替了密钥，用口令和盐生成密钥，再使用对称加密DES算法生成密文。
	 */
	public static final String ALGORITHM = "PBEWithMD5AndDES";
	
	/**
	 * 静态盐(长度必须为8位)
	 */
	public static final String STATIC_SALT = "Lie_Feng";

	/**
	 * 定义迭代次数为1000次
	 */
	private static final int ITERATIONCOUNT = 1000;
	
	/**
	 * 加密客户身份证号码
	 * @param idNum 身份证号码明文
	 * @return 密文
	 */
	public static String encryptCustIdNum(String idNum) {
		return encrypt(idNum, CUST_ID_NUM_PASSWORD, getStaticSalt());
	}
	
	/**
	 * 解密客户身份证号码
	 * @param encryptIdNum 身份证号码密文
	 * @return 明文
	 */
	public static String decryptCustIdNum(String encryptIdNum) {
		return decrypt(encryptIdNum, CUST_ID_NUM_PASSWORD, getStaticSalt());
	}
	
	/**
	 * 加密
	 * @param plaintext 明文
	 * @param password 生成密钥的口令
	 * @return 密文
	 */
	public static String encrypt(String plaintext, String password) {
		return encrypt(plaintext, password, getStaticSalt());
	}

	/**
	 * 加密
	 * @param plaintext 明文
	 * @param password 生成密钥的口令
	 * @param salt 盐值(长度必须为8位)
	 * @return 密文
	 */
	public static String encrypt(String plaintext, String password, byte[] salt) {

		Key key = getPBEKey(password);
		byte[] encipheredData = null;
		PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, ITERATIONCOUNT);
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
			encipheredData = cipher.doFinal(plaintext.getBytes());
		} catch (Exception e) {
			logger.error("encrypt error:", e);
		}
		return bytesToHexString(encipheredData);
	}
	
	/**
	 * 解密
	 * @param ciphertext 密文
	 * @param password 生成密钥的口令(需要与加密时使用的一致)
	 * @return 明文
	 */
	public static String decrypt(String ciphertext, String password) {
		return decrypt(ciphertext, password, getStaticSalt());
	}

	/**
	 * 解密
	 * @param ciphertext 密文
	 * @param password 生成密钥的口令(需要与加密时使用的一致)
	 * @param salt 盐值(与加密时使用的一致，长度必须为8位)
	 * @return 明文
	 */
	public static String decrypt(String ciphertext, String password, byte[] salt) {

		Key key = getPBEKey(password);
		byte[] passDec = null;
		PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, ITERATIONCOUNT);
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
			passDec = cipher.doFinal(hexStringToBytes(ciphertext));
		}
		catch (Exception e) {
			logger.error("decrypt error:", e);
		}
		return new String(passDec);
	}
	
	/**
	 * 获取加密算法中使用的盐值,解密中使用的盐值必须与加密中使用的相同才能完成操作. 盐长度必须为8字节
	 * @return byte[] 盐值
	 */
	public static byte[] getSalt() throws Exception {
		SecureRandom random = new SecureRandom(); //实例化安全随机数
		return random.generateSeed(8); //产出盐
	}

	public static byte[] getStaticSalt() {
		return STATIC_SALT.getBytes(); //产出盐
	}

	/**
	 * 根据PBE密码生成一把密钥
	 * @param password 生成密钥时所使用的口令
	 * @return Key PBE算法密钥
	 */
	private static Key getPBEKey(String password) {
		// 实例化使用的算法
		SecretKeyFactory keyFactory;
		SecretKey secretKey = null;
		try {
			keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
			// 设置PBE密钥参数
			PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
			// 生成密钥
			secretKey = keyFactory.generateSecret(keySpec);
		} catch (Exception e) {
			logger.error("getPBEKey error:", e);
		}

		return secretKey;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * @param src 字节数组
	 * @return
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 将十六进制字符串转换为字节数组
	 * @param hexString 十六进制字符串
	 * @return
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	public static void main(String[] args) {
		String encryptedStr = EncryptionUtil.encryptCustIdNum("421481199211298871");
		System.out.println(encryptedStr);
		String dencryptedStr = EncryptionUtil.decryptCustIdNum("ed19fcdeb425d00a22498fb0f13f9051ef7b97a7492714ad");
		System.out.println(dencryptedStr);
	}
}
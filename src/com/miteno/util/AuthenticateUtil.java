package com.miteno.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


/**
 * 检验signature
 * @author Stapler
 * @date 2013年12月27日
 *
 */
public class AuthenticateUtil {
	
	
	
	/**
	 * 检查signature
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return 是否校验通过
	 */
	public static boolean check_Signature(String signature, String timestamp, String nonce,String token){
		String[] arr = new String[]{token, timestamp, nonce};
		
		Arrays.sort(arr);		//按照字典顺序排序
		
		StringBuilder content = new StringBuilder();
		for (int i=0; i<arr.length; i++) {
			content.append(arr[i]);
		}
		
		MessageDigest md = null;
		String tempStr = null;
		
		try {
			md = MessageDigest.getInstance("SHA1");						//获取SHA1加密的MessageDigest对象
			byte[] digest = md.digest(content.toString().getBytes());	//将三个参数字符串拼接成的字符串进行sha1加密
			tempStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		content = null;
		System.out.println("tempStr"+tempStr.toString());
		System.out.println("signature"+signature.toUpperCase());
		return tempStr != null ? tempStr.equals(signature.toUpperCase()):false;
	}
	
	
	
	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}
}

package com.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static String getMD5String(String s){
		char[] hex = "0123456789abcdef".toCharArray();
		char[] rs = new char[hex.length*2];
		try {
			MessageDigest md5 = MessageDigest.getInstance("md5");
			md5.update(s.getBytes());
			byte[] data = md5.digest();
			int k = 0;
			for(int i=0;i<hex.length;i++){
				rs[k++] =  hex[data[i]>>>4&0xf];
				rs[k++] =  hex[data[i]&0xf];
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(rs);
	}
}

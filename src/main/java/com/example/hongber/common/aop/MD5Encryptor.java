package com.example.hongber.common.aop;

import com.example.hongber.common.exception.ErrorMsg;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

@Slf4j
public class MD5Encryptor {

	public static String encrypt(String str) {
		return encrypt(str, "None");
	}
	
	public static String encrypt(String str, String fieldNm) {
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes());
			byte[] md5Bytes = md5.digest();
			for (byte md5Byte : md5Bytes) {
				sb.append(Integer.toString((md5Byte & 0xFF) + 0x100, 16).substring(1));
			}
		} catch (Exception e) {
			log.error("MD5Encryptor : encrypt error!! : Field=["+ fieldNm +"]", e);
			throw new RuntimeException(ErrorMsg.ENCRYPT_FAIL.getMsg());
		}
		return sb.toString();
	}
}
package br.com.usjt.partiu.web.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

	public static boolean checkPassword(String senha, String senhaHash) throws NoSuchAlgorithmException {

		if(getMD5(senha).equals(senhaHash)){
			return true;
		}
		return false;
	}
	
//	public static String getMD5(String senha) throws NoSuchAlgorithmException{
//		MessageDigest m = MessageDigest.getInstance("MD5");
//		m.update(senha.getBytes(),0,senha.length());
//		return new BigInteger(1,m.digest()).toString(16);
//	}
	
	public static String getMD5(String senha) {
		StringBuffer encryption = new StringBuffer();
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(senha.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		for (int i = 0; i < hash.length; i++) {		
			encryption.append((((0xff & hash[i]) < 0x10) ? "0" : "") + Integer.toHexString((0xFF & hash[i])));
		}
		return encryption.toString();
	}

}

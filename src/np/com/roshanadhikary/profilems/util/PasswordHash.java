package np.com.roshanadhikary.profilems.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

public class PasswordHash {
	
	public static byte[] getHash(String password) {
		byte[] hashedPassword = null;
		try {
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt);
			
			hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashedPassword;
	}
}

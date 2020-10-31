package np.com.roshanadhikary.profilems.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class PasswordHash {
	
	public static String getHash(String password) {
		byte[] hashedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Base64.getEncoder().encodeToString(hashedPassword);
	}
}

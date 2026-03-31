package ra.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class BCryptUtil {

    private static final int COST = 12; // 🔥 tăng độ mạnh (10 → 12)

    public static String hash(String plainPassword) {
        return BCrypt.withDefaults()
                .hashToString(COST, plainPassword.toCharArray());
    }

    public static boolean verify(String plainPassword, String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer()
                .verify(plainPassword.toCharArray(), hashedPassword);

        return result.verified;
    }
}
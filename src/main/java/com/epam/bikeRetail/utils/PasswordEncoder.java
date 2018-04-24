package com.epam.bikeRetail.utils;
import org.apache.commons.codec.digest.DigestUtils;
import com.epam.bikeRetail.entity.User;
/**
 * Util class for encoding user password.
 *
 * @author Stepan Menchytsky
 * @see User
 * @see DigestUtils
 */

public class PasswordEncoder {

    private static final String PASSWORD_PREFIX = "MD5_";

    /**
     * Encode password using sha256 algorithm.
     *
     * @param password the user password.
     * @return the encoded user password.
     */
    public static String encode(String password) {
        password = PASSWORD_PREFIX + password;
        password = DigestUtils.sha256Hex(password);

        return password;
    }

}

package cn.com.megalith.auth;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description:
 * @author: zhoum
 * @Date: 2018-11-22
 * @Time: 17:36
 */
public class EncodePassword implements PasswordEncoder {

    /**
     * 暂时瞎比写
     */
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence , String s) {
        return charSequence.toString().equals(s);
    }
}

package cn.com.megalith.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

/**
 * @Description:MD5加密
 * @author: zhoum
 * @Date: 2018-11-22
 * @Time: 17:36
 */
@Component
public class EncodePassword implements PasswordEncoder {

    /**
     * md5加密
     */
    @Override
    public String encode(CharSequence charSequence) {

        return MD5(charSequence.toString());
    }

    /**
     * 匹配规则
     */
    @Override
    public boolean matches(CharSequence charSequence , String s) {
        return MD5(charSequence.toString()).equals(s);
    }

    /**
     * md5加密过程
     */
    public static String MD5(String key) {
        char hexDigits[] = {
                '0' , '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9' , 'a' , 'b' , 'c' , 'd' , 'e' , 'f'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[ j * 2 ];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[ i ];
                str[ k++ ] = hexDigits[ byte0 >>> 4 & 0xf ];
                str[ k++ ] = hexDigits[ byte0 & 0xf ];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}

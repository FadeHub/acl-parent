package com.sl.security.security;

import com.sl.util.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author shuliangzhao
 * @Title: DefaultPasswordEncoder
 * @ProjectName acl-parent
 * @Description: TODO
 * @date 2021/5/22 20:55
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder() {
        this(-1);
    }

    public DefaultPasswordEncoder(int strength) {
        
    }

    @Override
    public String encode(CharSequence charSequence) {
        return MD5.encrypt(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String encodePassword) {
        return encodePassword.equals(MD5.encrypt(charSequence.toString()));
    }
}

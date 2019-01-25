package com.ypj.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @ClassName UserUtil
 * @Author pain
 * @Date 2019/1/6 18:00
 * @Version 1.0
 **/
public class UserUtil {

    /**
     * 将用户的明文密码加密
     *
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 获取MessageDigest实例
        MessageDigest messageDigest = MessageDigest.getInstance("md5");
        // 获取password的字节数组
        byte[] passwordBytes = password.getBytes("utf-8");
        // 生成散列码
        final byte[] digest = messageDigest.digest(passwordBytes);
        // 获取Base64.Encoder
        Base64.Encoder encoder = Base64.getEncoder();
        // 返回加密后的字符串
        String encryptStr = encoder.encodeToString(digest);
        return encryptStr;
    }

}

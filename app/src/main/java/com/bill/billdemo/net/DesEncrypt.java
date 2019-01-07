package com.bill.billdemo.net;

import com.bill.billdemo.App;
import com.bill.billdemo.R;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DesEncrypt {


//    /* 设置秘钥 */
//    public static void setKey(String strKey) {
//        CommentConstant.k = strKey;
//    }

    /**
     * 加密
     *
     * @param message
     * @return
     * @throws Exception
     */
    public static String getEncString(String message) throws Exception {
        message = java.net.URLEncoder.encode(message, "utf-8");
        byte[] byteMi = null;
        byte[] byteMing = null;
        String strMi = "";
        byteMing = message.getBytes("UTF8");

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        DESKeySpec desKeySpec = new DESKeySpec(App.Companion.getInstance().getString(R.string.KEY).getBytes("UTF-8"));

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(App.Companion.getInstance().getString(R.string.KEY).getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        byteMi = cipher.doFinal(byteMing);
        strMi = Base64.encode(byteMi);
        strMi = filter(strMi);
        return strMi;
    }

    /**
     * 解密
     *
     * @param message
     * @return
     * @throws Exception
     */
    public static String getDesString(String message) throws Exception {

        byte[] byteMing = null;
        byte[] byteMi = null;
        String strMing = "";
        byteMi = Base64.decode(message);
//        byteMi = base64De.decodeBuffer(message);

        strMing = new String(byteMi, "UTF8");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(App.Companion.getInstance().getString(R.string.KEY).getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(App.Companion.getInstance().getString(R.string.KEY).getBytes("UTF-8"));

        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byteMing = cipher.doFinal(byteMi);
        strMing = new String(byteMing, "UTF8");
        strMing = java.net.URLDecoder.decode(strMing, "utf-8");
        return strMing;
    }

    public static String filter(String str) {
        String output = null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            int asc = str.charAt(i);
            if (asc != 10 && asc != 13)
                sb.append(str.subSequence(i, i + 1));
        }
        output = new String(sb);
        return output;
    }

}

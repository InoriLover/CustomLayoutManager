package com.customlayoutmanager.util;

import java.nio.charset.Charset;

/**
 * Created by Fishy on 2017/4/10.
 * 基本的类型之间的转换器
 */

public class BaseTypeConverter {
    /**
     * UTF-8编码
     */
    public static final String UTF_8 = "UTF-8";
    /**
     * ISO-8859-1编码
     */
    public static final String ISO_8859_1 = "ISO-8859-1";
    /**
     * GBK编码
     */
    public static final String GBK = "GBK";
    /**
     * US-ASCII编码
     */
    public static final String ASCII = "US-ASCII";

    /**
     * 基本的int转String
     *
     * @param value
     * @return
     */
    public static String intToString(int value) {
        return intToString(value, -1);
    }

    /**
     * int转String时，自动补全成固定位数
     * <p>如 <strong>04月09日</strong> 这种情况</p>
     *
     * @param value
     * @param formatLength
     * @return
     */
    public static String intToString(int value, int formatLength) {
        String origin = value + "";
        int length = origin.length();
        if (length < formatLength) {
            for (int i = length; i < formatLength; i++) {
                origin = "0" + origin;
            }
        }
        return origin;
    }

    /**
     * String转int，传入的参数必须合法
     * @param value
     * @return
     */
    public static int stringToInt(String value){
        return Integer.valueOf(value);
    }
    /**
     * byte数组转String
     * <p>使用默认的编码集</p>
     *
     * @param value
     * @return
     */
    public static String bytesToString(byte[] value) {
        return bytesToString(value, Charset.defaultCharset());
    }

    /**
     * byte数组转String
     * <p>使用编码集的名字，该类封装了几个</p>
     * {@link BaseTypeConverter#ISO_8859_1}<br/>
     * {@link BaseTypeConverter#UTF_8}<br/>
     * {@link BaseTypeConverter#ASCII}<br/>
     * {@link BaseTypeConverter#GBK}<br/>
     *
     * @param value
     * @param charsetName
     * @return
     */
    public static String bytesToString(byte[] value, String charsetName) {
        return bytesToString(value, Charset.forName(charsetName));
    }

    /**
     * byte数组转String
     * <p>使用自己封装好的charset</p>
     *
     * @param value
     * @param charset
     * @return
     */
    public static String bytesToString(byte[] value, Charset charset) {
        String result = new String(value, charset);
        return result;
    }

    /**
     * String转byte数组
     * <p>使用默认的编码集</p>
     *
     * @param value
     * @return
     */
    public static byte[] stringToBytes(String value) {
        return stringToBytes(value, Charset.defaultCharset());
    }

    /**
     * String转byte数组
     * <p>使用编码集的名字，该类封装了几个</p>
     * {@link BaseTypeConverter#ISO_8859_1}<br/>
     * {@link BaseTypeConverter#UTF_8}<br/>
     * {@link BaseTypeConverter#ASCII}<br/>
     * {@link BaseTypeConverter#GBK}<br/>
     *
     * @param value
     * @param charsetName
     * @return
     */
    public static byte[] stringToBytes(String value, String charsetName) {
        return stringToBytes(value, Charset.forName(charsetName));
    }

    /**
     * String转byte数组
     * <p>使用自己封装好的charset</p>
     *
     * @param value
     * @param charset
     * @return
     */
    public static byte[] stringToBytes(String value, Charset charset) {
        byte[] result = value.getBytes(charset);
        return result;
    }

    /**
     * byte数组转16进制表示的String
     *
     * @param value
     * @return
     */
    public static String bytesToHexString(byte[] value) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < value.length; i++) {
            int data = value[i];
            String dataStr = Integer.toHexString(data);
            stringBuilder.append(dataStr);
        }
        return stringBuilder.toString();
    }

    /**
     * byte数组转16进制表示的String数组
     * @param value
     * @return
     */
    public static String[] bytesToHexStringArray(byte[] value) {
        String[] result=new String[value.length];
        for (int i = 0; i < value.length; i++) {
            int data=value[i];
            result[i]=Integer.toHexString(data);
        }
        return result;
    }
}

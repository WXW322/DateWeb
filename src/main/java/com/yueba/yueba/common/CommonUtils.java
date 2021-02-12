package com.yueba.yueba.common;

import com.google.common.base.Objects;
import lombok.val;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author 徐塬峰 2019/3/31
 */
public class CommonUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() < 1;
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Map map) {
        return map == null || map.size() < 1;
    }

    //检查字符串长度，字符串为null返回true
    public static boolean isLengthEnough(String str, int length) {
        if (str == null) {
            return false;
        }
        return str.length() >= length;
    }

    public static boolean isEmail(String email) {
        if (email == null) {
            return false;
        }
        return Pattern.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", email);
    }

    public static boolean isPhone(String phoneNum) {
        if (phoneNum == null) {
            return false;
        }
        return Pattern.matches("^1(\\d{10})$", phoneNum);
    }

    //计算一个字符串的MD5值
    public final static String calculateMD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //计算两个日期相差天数的绝对值
    public static long calculateApartDays(Date date1, Date date2) {
        //一天对应的毫秒值
        long day = 1000L * 60 * 60 * 24;
        return Math.abs(date1.getTime() / day - date2.getTime() / day);
    }


    /**
     * 计算两个数相除
     *
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    public static double division(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 返回两个字符串是否相等
     * 如果为空则返回null
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean ObjectsEqual(String str1, String str2) {
        return Objects.equal(str1, str2);
    }


    public static String sha1(String src) {

        try {
            MessageDigest md = MessageDigest.getInstance("sha1");
            byte[] digest = md.digest(src.getBytes());
            char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(chars[(b >> 4) & 15]);
                sb.append(chars[b & 15]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 该方法主要是将值上下浮动百分之10  0.90 - 1.10
     *
     * @return
     */
    public static Double updateValue(Double oldValue) {
        //如果传入的参数为0 则不需要进行改变。
        if (oldValue == null || oldValue == 0) {
            return oldValue;
        }
        //生产90-110的随机数字
        Random random = new Random();
        BigDecimal field = BigDecimal.valueOf(random.nextInt(20) + 90).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
        val newValue = BigDecimal.valueOf(oldValue).multiply(field).setScale(2, BigDecimal.ROUND_HALF_UP);

        System.out.println("==========field=======" + field);
        System.out.println("==========newValue=======" + newValue);
        return newValue.doubleValue();
    }

    /**
     * 生成40-60随机数据
     *
     * @return
     */
    public static double generateRateData(Random random) {
        int number = random.nextInt(20) + 40;
        return BigDecimal.valueOf(number).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP).setScale(2).doubleValue();
    }

    public static String costAndPercentGenerate(Random random) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 24; i++) {
            sb.append(random.nextInt(100));
            sb.append(",");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }


    /**
     * @return
     */
    public static BigDecimal generateMoneyData(Random random) {
        return BigDecimal.valueOf(random.nextInt(20) + 20).divide(BigDecimal.valueOf(10), 2, BigDecimal.ROUND_HALF_UP).setScale(2);
    }


    public static String createRandom(int number) {
        String randomcode = "";
        // 用字符数组的方式随机
        String model = "0123456789abcdefghijklmnopqrstuvwxyz";
        char[] m = model.toCharArray();
        for (int j = 0; j < number; j++) {
            char c = m[(int) (Math.random() * 36)];
            if (randomcode.contains(String.valueOf(c))) {
                j--;
                continue;
            }
            randomcode = randomcode + c;
        }
        return randomcode;
    }


}

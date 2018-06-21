package com.xiaomi.editor.utils;

import java.util.Random;

/**
 * Description:生成验证码的工具类
 * User: dell - XiaomiLi
 * Date: 2018-06-08
 * Time: 15:28
 * UpdateDescription：
 */
public class RandomUtils {
    private final static String numberStr = "0123456789";
    private final static String letterLittleStr = "abcdefghigklmnopqrstuvwxyz";
    private final static String letterBigStr = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";

    public final static int NUMBER = 0x1;
    public final static int LETTER_LITTLE = 0x2;
    public final static int LETTER_BIG = 0x3;
    public final static int NUMBER_LETTER = 0x4;
    public final static int LETTER = 0x5;

    public static String getRandom(int length, int randomType) {
        String randomString = getRandomString(randomType);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int index = new Random().nextInt(randomString.length());
            stringBuffer.append(randomString.charAt(index));
        }
        return stringBuffer.toString();
    }


    private static String getRandomString(int randomType) {
        if (randomType != NUMBER && randomType != LETTER_LITTLE && randomType != LETTER_BIG
                && randomType != NUMBER_LETTER && randomType != LETTER) {
            throw new IllegalArgumentException("randomType is error !");
        }
        String str = "";
        switch (randomType) {
            case NUMBER:
                str = numberStr;
                break;
            case LETTER_LITTLE:
                str = letterLittleStr;
                break;
            case LETTER_BIG:
                str = letterBigStr;
                break;
            case NUMBER_LETTER:
                str = numberStr + letterLittleStr + letterBigStr;
                break;
            case LETTER:
                str = letterLittleStr + letterBigStr;
                break;
        }
        return str;
    }
}

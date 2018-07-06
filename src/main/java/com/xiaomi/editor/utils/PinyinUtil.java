package com.xiaomi.editor.utils;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

/**
 * Description:汉字转化成拼音 <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-28<br>
 * Time: 13:18<br>
 * UpdateDescription：<br>
 */
public class PinyinUtil {

    /**
     * 汉字转化拼音
     *
     * @param str
     * @return
     * @throws PinyinException
     */
    public static String getPinyin(String str) throws PinyinException {
        if (CheckStringEmptyUtils.IsEmpty(str)) {
            return "";
        }
        return PinyinHelper.convertToPinyinString(str, "", PinyinFormat.WITHOUT_TONE);// ni,hao,shi,jie
    }


    public static void main(String[] args) {
        String str = "你好世界";
        try {
//            PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITH_TONE_MARK); // nǐ,hǎo,shì,jiè
//            PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITH_TONE_NUMBER); // ni3,hao3,shi4,jie4
            String string = PinyinHelper.convertToPinyinString(str, "", PinyinFormat.WITHOUT_TONE);// ni,hao,shi,jie
            System.out.println("string:" + string);
//            PinyinHelper.getShortPinyin(str); // nhsj
        } catch (PinyinException e) {
            System.out.println("e:" + e.toString());
            e.printStackTrace();
        }
    }

}

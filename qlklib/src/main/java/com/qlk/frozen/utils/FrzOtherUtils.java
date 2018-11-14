package com.qlk.frozen.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/8/14 14:09
 */
public class FrzOtherUtils {
    /**
     * 名称是否合法
     */
    @SuppressWarnings("uncheck")
    public static boolean isNameValid(String name) {
        final String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\r\n\f\b\t]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(name);
        if (!m.find()) {
            if (containsEmoji(name)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检测是否有emoji表情
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >=
                0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >=
                0x10000) && (codePoint <= 0x10FFFF));
    }
}

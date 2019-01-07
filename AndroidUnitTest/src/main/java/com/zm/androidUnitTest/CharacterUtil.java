package com.zm.androidUnitTest;

/**
 * 文本工具
 * Author: zhangmiao
 * Date: 2019/1/7
 */
public class CharacterUtil {

    public static final String TAG = CharacterUtil.class.getSimpleName();

    /**
     * 将字符前端和后端的空格、制表符、回车符删除掉
     *
     * @param str
     * @return
     */
    public static String removeStartEndSpace(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        int length = str.length();
        int startIndex = 0;
        int endIndex = length;

        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (c == ' ' || c == '\t' || c == '\n') {
                startIndex++;
            } else {
                break;
            }
        }

        for (int i = length - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (c == ' ' || c == '\t' || c == '\n') {
                endIndex--;
            } else {
                break;
            }
        }

        if (startIndex == 0 && endIndex == length) {
            return str;
        } else {
            return str.substring(startIndex, endIndex);
        }
    }

    /**
     * 判断大于小于等于符号的正确答案与错误答案的比较
     *
     * @param userAnswer
     * @param correctAnswer
     * @return
     */
    public static boolean isJudgeSymbol(String userAnswer, String correctAnswer) {
        if ((userAnswer == null || "".equals(userAnswer)) || (correctAnswer == null || "".equals(correctAnswer))) {
            return false;
        }
        if ("<".equals(userAnswer) || "＜".equals(userAnswer)) {
            //用户输入为小于号
            if ("<".equals(correctAnswer) || "＜".equals(correctAnswer) || "&lt;".equals(correctAnswer)) {
                return true;
            } else {
                return false;
            }
        } else if (">".equals(userAnswer) || "＞".equals(userAnswer)) {
            //用户输入为大于号
            if (">".equals(correctAnswer) || "＞".equals(correctAnswer) || "&gt;".equals(correctAnswer)) {
                return true;
            } else {
                return false;
            }
        } else if ("=".equals(userAnswer) || "＝".equals(userAnswer)) {
            //用户输入等于号
            if ("=".equals(correctAnswer) || "＝".equals(correctAnswer)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}

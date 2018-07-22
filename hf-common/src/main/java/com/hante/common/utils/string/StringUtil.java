package com.hante.common.utils.string;


import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


public class StringUtil extends StringUtils {
    public StringUtil() {
    }

    public static int lengthForChinese(String text) {
        int length = 0;
        for (int i = 0; i < text.length(); ++i) {
            if ((String.valueOf(text.charAt(i))).getBytes().length > 1) {
                length += 2;
            } else {
                ++length;
            }
        }
        return length;

    }

    public static String convertCtrlNAndBlankToHtml(String text) {
        if (isNotBlank(text)) {
            text = text.replace("\n", "<br/>");
        }

        return text;
    }

    public static String subStr(String text, int len) {
        int length = text.length();
        return length < len ? text.substring(0, length) : text.substring(0, len) + "...";
    }

    public static String fTOL(String text, int len) {
        int length = text.length();
        if (length <= len) {
            return text;
        } else {
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < length - len; ++i) {
                if (i % 4 == 0) {
                    sb.append(" ");
                }

                sb.append("*");
            }

            return sb.toString() + text.substring(length - len);
        }
    }

    public static String showMobile(String mobile) {
        return mobile == null ? null : mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    public static String showLater(String text, int len) {
        int length = text.length();
        return length > len ? text.substring(length - len) : text;
    }

    public static String fTOL(String text, int f, int l) {
        int length = text.length();
        if (length > f + l) {
            int i = 0;
            StringBuffer sb = new StringBuffer(text.substring(0, f));
            sb.append(" ");

            while (i <= length - (f + l)) {
                sb.append("*");
                ++i;
                if (i % l == 0) {
                    sb.append(" ");
                }
            }

            sb.append(" ");
            sb.append(text.substring(length - (l + 1)));
            return sb.toString();
        } else {
            return text;
        }
    }

    public static String cardNumberStarConvert(String str){
        int i = str.length() - 4;
        String star = "";
        for (int j = 0; j<7; j++){
            star +="*";
        }
        return str.replaceAll("(\\d{"+i+"})(\\d{4})", star +"$2");
    }
    /**
     * 身份证脱敏处理
     * 显示前6后4
     * */
    public static String idCardStarConvert(String str){
        if (StringUtils.isBlank(str)) {
            return "";
        }
        String right = StringUtils.right(str, 4);
        String left = StringUtils.left(str,6);
        return left+"********"+right;
    }

    /**
     * 公司名称脱敏处理
     * 显示第一位
     * */
    public static String companyStarConvert(String str){
        if (StringUtils.isBlank(str)) {
            return "";
        }
        String left = StringUtils.left(str,1);
        return left+"********";
    }

    /**
     * map转字符串参数拼接
     */
    public static String sortAndToString(Map<String, String> paramsMap) {
        SortedMap<String, String> sortedMap = new TreeMap<>();
        sortedMap.putAll(paramsMap);
        StringBuffer stringBuffer = new StringBuffer();
        sortedMap.forEach((key, value) -> stringBuffer.append(key).append("=").append(value).append("&"));
        if (stringBuffer.toString().length() > 0) {
            return stringBuffer.toString().substring(0, stringBuffer.length() - 1);
        }
        return "";
    }

    /**
     * 身份证脱敏处理
     * 显示前10后4
     * */
    public static String idCardStarConvertNew(String str){
        if (StringUtils.isBlank(str)) {
            return "";
        }
        String right = StringUtils.right(str, 4);
        String left = StringUtils.left(str,10);
        return left+"****"+right;
    }

    /**
     * 转成Integer
     * */
    public static Integer parseInteger(String str){
        if(StringUtils.isBlank(str)){
            return 0;
        }
        return Integer.parseInt(str);

    }


}
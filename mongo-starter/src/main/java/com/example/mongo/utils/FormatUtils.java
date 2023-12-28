package com.example.mongo.utils;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-08 11:17
 * @Version V1.0
 */
public class FormatUtils {

    public static final String EMPTY = "";


    public static Pattern regex = Pattern.compile("\\$\\{([^}]*)\\}");

    public static String bson(String json) {
        json = transString(json);


        String blank = "    ";
        String indent = "";// 缩进
        StringBuilder sb = new StringBuilder();

        for (char c : json.toCharArray()) {
            switch (c) {
                case '{':
                    indent += blank;
                    sb.append("{\n").append(indent);
                    break;
                case '}':
                    indent = indent.substring(0, indent.length() - blank.length());
                    sb.append("\n").append(indent).append("}");
                    break;
                case '[':
                    indent += blank;
                    sb.append("[\n").append(indent);
                    break;
                case ']':
                    indent = indent.substring(0, indent.length() - blank.length());
                    sb.append("\n").append(indent).append("]");
                    break;
                case ',':
                    sb.append(",\n").append(indent);
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 转换$oid为ObjectId()
     *
     * @param str
     * @return
     */
    private static String transString(String str) {
        str = str.replace(", ", ",").replace("{\"$oid\":", "${");

        List<String> temp = getContentInfo(str);
        for (String tp : temp) {
            str = str.replace("${" + tp + "}", "ObjectId(" + tp.trim() + ")");
        }

        return str;
    }

    /**
     * 获取表达式中${}中的值
     *
     * @param content
     * @return
     */
    private static List<String> getContentInfo(String content) {

        Matcher matcher = regex.matcher(content);
        List<String> list = new ArrayList<String>();
        while (matcher.find()) {
            list.add(matcher.group(1));
        }

        return list;

    }

    /**
     * 小写首字母<br>
     * 例如：str = Name, return name
     *
     * @param str 字符串
     * @return 字符串
     */
    public static String lowerFirst(CharSequence str) {
        if (null == str) {
            return null;
        }
        if (str.length() > 0) {
            char firstChar = str.charAt(0);
            if (Character.isUpperCase(firstChar)) {
                return Character.toLowerCase(firstChar) + subSuf(str, 1);
            }
        }
        return str.toString();
    }

    /**
     * 切割指定位置之后部分的字符串
     *
     * @param string    字符串
     * @param fromIndex 切割开始的位置（包括）
     * @return 切割后后剩余的后半部分字符串
     */
    public static String subSuf(CharSequence string, int fromIndex) {
        if (!StringUtils.hasText(string)) {
            return null;
        }
        return sub(string, fromIndex, string.length());
    }

    /**
     * 改进JDK subString<br>
     * index从0开始计算，最后一个字符为-1<br>
     * 如果from和to位置一样，返回 "" <br>
     * 如果from或to为负数，则按照length从后向前数位置，如果绝对值大于字符串长度，则from归到0，to归到length<br>
     * 如果经过修正的index中from大于to，则互换from和to example: <br>
     * abcdefgh 2 3 =》 c <br>
     * abcdefgh 2 -3 =》 cde <br>
     *
     * @param str       String
     * @param fromIndex 开始的index（包括）
     * @param toIndex   结束的index（不包括）
     * @return 字串
     */
    public static String sub(CharSequence str, int fromIndex, int toIndex) {
        if (!StringUtils.hasText(str)) {
            return EMPTY;
        }
        int len = str.length();

        if (fromIndex < 0) {
            fromIndex = len + fromIndex;
            if (fromIndex < 0) {
                fromIndex = 0;
            }
        } else if (fromIndex > len) {
            fromIndex = len;
        }

        if (toIndex < 0) {
            toIndex = len + toIndex;
            if (toIndex < 0) {
                toIndex = len;
            }
        } else if (toIndex > len) {
            toIndex = len;
        }

        if (toIndex < fromIndex) {
            int tmp = fromIndex;
            fromIndex = toIndex;
            toIndex = tmp;
        }

        if (fromIndex == toIndex) {
            return EMPTY;
        }

        return str.toString().substring(fromIndex, toIndex);
    }


}

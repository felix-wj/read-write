package cn.wangjie.learn.util;

import org.apache.commons.lang3.ArrayUtils;

import java.io.UnsupportedEncodingException;
import java.util.stream.IntStream;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2020-03-31 11:40
 **/
public class StringUtil {

    /**
     * @param beginIndex 起始位置
     * @param characterNum 字符个数
     * @param str        原始文本
     * @Author WangJie
     * @Description 截取n个字符，可以处理表情符号的字符串
     * @Date 2019/11/22 10:29
     */
    public static String subPointString(Integer beginIndex, Integer characterNum, String str) {
        if (str == null || "".equals(str)) {
            return str;
        }
        IntStream intStream = str.codePoints();
        if (beginIndex != null && beginIndex > 0) {
            intStream = intStream.skip(beginIndex);
        }
        if (characterNum != null && characterNum > 0) {
            intStream = intStream.limit(characterNum);
        }
        return intStream.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    /**
     * @Author WangJie
     * @Description 按字节截取字符串
     * @param str 待截取字符串
     * @param bytes 期望长度
     * @param charSetName 字符串编码
     * @Date  2020/3/31 11:24
     */
    public static String subStringSafe(String str, int bytes, String charSetName)
            throws UnsupportedEncodingException {
        if (str ==null||str.getBytes(charSetName).length<=bytes){
            return str;
        }
        byte[] subAfter = ArrayUtils.subarray(str.getBytes(charSetName),0,bytes);
        int temp = bytes;
        // 直到截取的字符串的字节数 和 需要的 截取的字节数相等位为止
        while (bytes < subAfter.length) {
            subAfter = ArrayUtils.subarray(str.getBytes(charSetName),0,--temp);
        }
        return new String(subAfter,charSetName);
    }
}

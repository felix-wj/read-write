package cn.wangjie.learn;

import cn.wangjie.learn.entity.Node;
import cn.wangjie.learn.entity.Room;
import cn.wangjie.learn.entity.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.RoundingMode;
import java.text.CharacterIterator;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.awt.*;
import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.regex.Pattern.compile;

/**
 * @program: read-write
 * @description:
 * @author: WangJie
 * @create: 2019-01-10 21:45
 **/
@Slf4j
public class SampleTest {

    @Test
    public void testEnum() {
        String str = "abc";
        StringBuffer stringBuffer = new StringBuffer(str);
        stringBuffer.reverse();
        System.out.println(stringBuffer);
        Suit.values();
    }

    enum Suit {
        a,
        b,
        c,
    }
    @Test
    public void shift() {
        int i = 0;
        while (true) {
            if ((i >>> 16) > 0) {
                System.out.println(i);
                break;
            }
            i++;
        }
        System.out.println(65535 >>> 16);

    }



    @Test
    public void test4() {
        String str = "\uD83D\uDC4D\uD83D\uDCAA\uD83C\uDFFB\uD83D\uDE02\uD83D\uDE07";
        System.out.println(str.substring(0, 1));
        str.codePoints().forEach(System.out::println);
        String intStreamToString = str.codePoints().skip(0).limit(100).collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(intStreamToString);
        System.out.println(subPointString(1, -1, str));
    }

    public static String subPointString(Integer beginIndex, Integer length, String str) {
        if (str == null || "".equals(str)) {
            return str;
        }
        IntStream intStream = str.codePoints();
        if (beginIndex != null && beginIndex > 0) {
            intStream = intStream.skip(beginIndex);
        }
        if (length != null && length > 0) {
            intStream = intStream.limit(length);
        }
        return intStream.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    @Test
    public void bitSet() {
        BitSet bitSet = new BitSet(2);
        bitSet.set(18);
    }

    @Test
    public void classTest() {
        List<Class<?>> primitiveTypes = new ArrayList<>(32);

        Collections.addAll(primitiveTypes, boolean[].class, byte[].class, char[].class,
                double[].class, float[].class, int[].class, long[].class, short[].class, Boolean.class, boolean.class, Boolean[].class, Node[].class);
        primitiveTypes.add(void.class);
        for (Class<?> primitiveType : primitiveTypes) {
            System.out.println(primitiveType.getName() + "  " + primitiveType);
        }
    }

    @Test
    public void testSplit() {
        String st = "，美丽，好看，1，，";
        String[] split = st.split("，");
        System.out.println(split.length);
        for (String s : split) {
            System.out.println(s);
        }

        st = "，美丽，好看，1，，1";
        split = st.split("，");
        System.out.println(split.length);
        for (String s : split) {
            System.out.println(s);
        }

    }







}

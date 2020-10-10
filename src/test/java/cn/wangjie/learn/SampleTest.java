package cn.wangjie.learn;

import cn.wangjie.learn.entity.Node;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.regex.Pattern.compile;
import static java.util.regex.Pattern.matches;

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
        st = "，美丽，好看，1，，1";
        split = st.split("，");
        System.out.println(split.length);
    }

    class HashCode {
        private Integer i;

        public Integer getI() {
            return i;
        }

        public void setI(Integer i) {
            this.i = i;
        }

        HashCode() {

        }

        public HashCode(Integer i) {
            this.i = i;
        }
    }

    @Test
    public void testHashCode() {
        for (int i = 0; i < 10; i++) {
            HashCode hashCode = new HashCode();
//            hashCode.setI(1);
            System.out.println(hashCode.hashCode());
        }
        B b = new B();
        b.a();
    }

    abstract class A {
        public void a() {
        }
    }

    class B extends A {

    }

    @Test
    public void testboolean(){
        Random random = new Random();
        for (int i = 0; i <10000000 ; i++) {
            int num = random.nextInt(100)+50;
            int day = random.nextInt(30)+10;
            List<Integer> randomNum = getRandom(num, day);
            int total = 0;
            for (Integer integer : randomNum) {
                List<Integer> integers = perVoteNum(integer, 5);
                total+=sum(integers);
            }
            if (num!=total){
                log.info("num:{},day:{}",num,day);
                log.info("randomNum:{}",randomNum.toString());
            }
        }
    }

    private int sum(List<Integer> list){
        return list.stream().mapToInt(i->i).sum();
    }
    private List<Integer> getRandom(int num, int day) {
        if (day==1){
            return Arrays.asList(num);
        }
        Random random = new Random();
        int[] a = new int[day - 1];
        for (int i = 0; i < day - 1; i++) {
            a[i] = random.nextInt(num);
        }
        Arrays.sort(a);
        List<Integer> result = new LinkedList<>();
        int temp = 0;
        for (int i = 0; i < day - 1; i++) {
            result.add( a[i] - temp);
            temp = a[i];
        }
        result.add( num - a[day - 2]);
        return result;
    }
    private List<Integer> perVoteNum(int num,int maxVoteNum){
        if (num==0){
            return new ArrayList<>();
        }
        List<Integer> randoms = getRandom(num, 4);
        List<Integer> perVoteNum = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>(randoms);

        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            if (poll>maxVoteNum){
                randoms = getRandom(poll, 2);
                queue.addAll(randoms);
            }else if (poll>0){
                perVoteNum.add(poll);
            }
        }
        return perVoteNum;
    }
    @Test
    public void testArray() {
        Set<String> s = new HashSet<>();
        s.add(null);
        s.add("s");
        Set<String> t = new HashSet<>();
        //t.add(null);
        t.add("s");
        t.retainAll(s);
        System.out.println(t.size());
    }

    @Test
    public void name() throws IOException {


        FileInputStream inputStream = new FileInputStream("txt.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String str = null;
        int i = 1;
        while ((str = bufferedReader.readLine()) != null) {
            sb.append("\"" + str + "\",");
            i++;
        }
        System.out.println(i);
        //close
        inputStream.close();
        bufferedReader.close();
        sb.deleteCharAt(sb.lastIndexOf(","));
        System.out.println(sb.toString());

    }

    //布隆过滤器
    @Test
    public void na() {
        int total = 100000; // 总数量
        BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total,0.000001);
        // 初始化 1000000 条数据到过滤器中
        for (int i = 0; i < total; i++) {
            bf.put("" + i);
        }
        // 判断值是否存在过滤器中
        int count = 0;
        int t = total * 1000;
        for (int i = 0; i < t; i++) {
            if (bf.mightContain("" + i)) {
                count++;
            }
        }

        System.out.println("已匹配数量 " + count);
    }

    @Test
    public void switchTest() {
        String name = null;
        switch (name){
            case "ss":
                System.out.println("ss");
                break;
            default:
                System.out.println("nothing");
        }
    }
}

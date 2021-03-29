package cn.wangjie.learn;

import cn.wangjie.learn.entity.Node;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.awt.*;
import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        String s = "587591880726,589077400486,597348669638,592519328987,607699694687,587948407045,587518908799,587470408836,587652981552,594992201495,589701087522,588891235001,588054143414,588346176222,592440092700,588860939232,588268961946,625594830189,628780403698,588106026565,626168222970,588718478764,588588021933,588689224077,615455011268,587969297300,587846834517,587736801800,608054884033,607835754737,593395003750,587377025345,587496860199,593163538285,588725866999,603392314517,628751503599,587499572972,593884863984,587705855042,587551610385,597903889974,607389640477,588715502430,596615586742,601216413201,601609059683,628854287394,627934732628,627850268489,587756128136,587757712033,587757940270,587974565525,595065766574,628485882299,594166653974,592063098620,611307733978\t";
        String a = "587591880726";
        String[] split = s.split(",");

        List<String> strings = Arrays.asList(split);
        log.info(strings.toString());
        log.info("set:{}",new HashSet<>(strings));
        System.out.println(String.format("%s",null));
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
    static Class<?> comparableClassFor(Object x) {
        if (x instanceof Comparable) {
            Class<?> c; Type[] ts, as; Type t; ParameterizedType p;
            c = x.getClass();
        /*    if ((c = x.getClass()) == String.class) // bypass checks
                return c;*/
            if ((ts = c.getGenericInterfaces()) != null) {
                for (int i = 0; i < ts.length; ++i) {
                    if (((t = ts[i]) instanceof ParameterizedType) &&
                            ((p = (ParameterizedType)t).getRawType() ==
                                    Comparable.class) &&
                            (as = p.getActualTypeArguments()) != null &&
                            as.length == 1 && as[0] == c) // type arg is c
                        return c;
                }
            }
        }
        return null;
    }

    @Test
    public void main() {
      /*  Map<String, String> map = new HashMap<>();
        map.put("1",null);
        System.out.println(map.size());
        System.out.println(map.keySet());
        System.out.println(map.values());
        map.entrySet().forEach(e-> System.out.println(e.getKey()+e.getValue())
        );
        String s = map.get(null);
        System.out.println(s);*/
        /*PayStatus ss = PayStatus.
        System.out.println(ss);*/
/*
        int[]a=new int[1];
        for(int i = 0; i < 200000; i++) {
            try {
                System.out.println(a[1]);
            } catch (Exception e) {
                System.out.println(i + ":" + e.getStackTrace().length);
                if (e.getStackTrace().length == 0) {
                    System.out.println("stackTrace omit after " + i + " times");
                    throw e;
                }
            }
        }
*/
        String str = "{\"x\":\"10\"}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println(jsonObject.getInteger("x"));
    }
}

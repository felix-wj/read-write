package cn.wangjie.learn;

import cn.wangjie.learn.entity.Node;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
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
    public void testArray(){
        int array[] = new int[10];
        String[] strs = new String[10];
        System.out.println(array[1]);
    }
    @Test
    public void testa(){
        Map<String,String> map = new HashMap<>();
        map.put("1","hah");
        map.get(null);
    }
}

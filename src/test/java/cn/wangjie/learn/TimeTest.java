package cn.wangjie.learn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.web.util.HtmlUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @program: read-write
 * @description: java8 新的日期时间API
 * @author: WangJie
 * @create: 2019-01-31 15:08
 **/
@Slf4j
public class TimeTest {
    //获取时区
    @Test
    public void getZone(){
        //默认时区
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(zoneId.toString());
        zoneId = ZoneId.of("Asia/Shanghai");
        System.out.println(zoneId.toString());
        zoneId = TimeZone.getTimeZone("CTT").toZoneId();
        System.out.println(zoneId.toString());
    }
    //字符转时间
    @Test
    public void strToDate(){
        LocalDate date = LocalDate.parse("20190522", DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println(date);
    }
    //时间格式化输出
    @Test
    public void dateToStr(){
        LocalDate today = LocalDate.now();
        System.out.println(today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        LocalTime time = LocalTime.now();
        //24小时制
        System.out.println(time.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        //12小时制
        System.out.println(time.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        LocalDateTime now = LocalDateTime.of(today,time);
        //yyyyMMdd
        System.out.println(now.format(DateTimeFormatter.BASIC_ISO_DATE));
        //yyyy-MM-dd
        System.out.println(now.format(DateTimeFormatter.ISO_DATE));
        //2019-05-28T15:30:21.047
        System.out.println(now.format(DateTimeFormatter.ISO_DATE_TIME));
        //local date
        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE));
        //local time 带毫秒 eg:15:33:00.043
        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_TIME));
        //local dateTime eg:2019-05-28T15:33:00.043
        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        //2019-W22-2
        System.out.println(now.format(DateTimeFormatter.ISO_WEEK_DATE));
    }

    //与Date的转换
    @Test
    public void transformWithDate(){
        //date转 localdatetime
        Date date = new Date();
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        //localdatetime转date
        Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    //时间戳与localdatetime转换
    @Test
    public void transformWithTimestamp(){
        //秒级时间戳
        long timeStamp = LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8));
        System.out.println(timeStamp);
        timeStamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
        System.out.println(timeStamp);
        System.out.println(Instant.ofEpochSecond(timeStamp).atZone(ZoneId.systemDefault()).toLocalDateTime());
        //毫秒级时间戳
        timeStamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println(timeStamp);
        System.out.println(Instant.ofEpochMilli(timeStamp).atZone(ZoneId.systemDefault()).toLocalDateTime());
    }
    //时间调整到特定某天
    @Test
    public void adjust(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        //同月的第一天
        LocalDateTime firstDayInSameMonthOfNow = now.withDayOfMonth(1);
        System.out.println(firstDayInSameMonthOfNow);
        //同年的第一天
        LocalDateTime firstDayInSameYearOfNow = now.withDayOfYear(1);
        System.out.println(firstDayInSameYearOfNow);
        //同年的第2月第10天
        LocalDateTime time = now.withMonth(2).withDayOfMonth(10);
        System.out.println(time);
        //当天的6点整
        time = now.withHour(6).withMinute(0).withSecond(0).withNano(0);
        System.out.println(time);
    }
    //时间间隔
    @Test
    public void duration(){
        String durationStr;
        //秒级时间戳
        long time = 1L;
        LocalDateTime date = LocalDateTime.ofEpochSecond(time,0, ZoneOffset.ofHours(8));
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(date,now);
        TemporalAdjusters.firstDayOfYear();
        LocalDateTime firstDayInThisYear =now.withDayOfYear(1);
        if (date.isBefore(firstDayInThisYear)){
            durationStr = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }else if (duration.compareTo(Duration.ofDays(3))>=0){
            durationStr = date.format(DateTimeFormatter.ofPattern("MM-dd HH:mm"));
        }else if (duration.compareTo(Duration.ofDays(1))>=0){
            durationStr = duration.toDays()+"天前";
        }else if (duration.compareTo(Duration.ofHours(1))>=0){
            durationStr = duration.toHours()+"小时前";
        }else if (duration.compareTo(Duration.ofMinutes(10))>=0){
            durationStr = duration.toMinutes()+"分钟前";
        }else {
            durationStr = "刚刚";
        }
        System.out.println(durationStr);
    }


}


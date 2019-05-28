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
    @Test
    public void localDateTest(){

        //距1970-01-01的n天的日期
        LocalDate date = LocalDate.ofEpochDay(1);
        log.info("1970-01-01后1天的日期:{}",date);
        //获取某年某月某日的日期
        date = LocalDate.of(2019,Month.MAY,2);
        log.info("2019年5月2日的日期:{}",date);
        //获取某年的第n天的日期
        date = LocalDate.ofYearDay(2019,2);
        log.info("2019年第2天的日期:{}",date);
        date = LocalDate.parse("2019-01-31");
        log.info("字符串转时间:{}",date);
        //默认时区的当前日期
        LocalDate today = LocalDate.now();
        log.info("默认时区(Asia/Shanghai)下的当前日期:{}",today);
        //某个指定时区的当前日期
        LocalDate todayAtAmericaAnchorage = LocalDate.now(ZoneId.of("America/Anchorage"));
        log.info("America/Anchorage时区下的当前日期:{}",todayAtAmericaAnchorage);
        log.info("LocalDate.MAX:{}",LocalDate.MAX);
        log.info("LocalDate.MIN:{}",LocalDate.MIN);

        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Shanghai"));
        log.info("格式化输出:{}",localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
    @Test
    public void localTimeTest(){
        log.info("中午:{}",LocalTime.NOON);
        log.info("午夜:{}",LocalTime.MIDNIGHT);
        LocalDateTime today= LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
        System.out.println(today);
        LocalDateTime tomorrow =today.plusDays(1);
        System.out.println(tomorrow);
    }
    @Test
    public void durationTest(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime date = LocalDateTime.of(2019,1,20,17,0);
        Duration duration = Duration.between(now,date);
        System.out.println(duration.toDays());
        Instant nowMilli = Instant.now();
        Instant dateMilli = Instant.ofEpochMilli(1543834532474L);
        duration = Duration.between(dateMilli,nowMilli);
        System.out.println(duration.toDays());
        Period oneWeek = Period.ofWeeks(1);
        System.out.println(oneWeek.getDays());
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
        System.out.println(twoYearsSixMonthsOneDay.getDays());
        System.out.println(duration.compareTo(Duration.ofDays(7)));
        System.out.println(duration.compareTo(Duration.ofHours(59*24)));
    }
    @Test
    public void temporalAdjusterTest(){
        LocalDate now = LocalDate.now();
        LocalDate date = now.with(TemporalAdjusters.dayOfWeekInMonth(1,DayOfWeek.MONDAY));
        System.out.println(date);
        LocalDateTime todayNoon = LocalDateTime.of(LocalDate.now(),LocalTime.NOON);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = todayNoon.atZone(zone).toInstant();
        long todayNoonMillis = instant.toEpochMilli();
        long yesterdayNoonMillis = instant.plus(-1,ChronoUnit.DAYS).toEpochMilli();
        System.out.println(yesterdayNoonMillis);
    }

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
    public void transform(){
        //date转 localdatetime
        Date date = new Date();
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        //localdatetime转date
        Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }
}


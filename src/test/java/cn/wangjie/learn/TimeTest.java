package cn.wangjie.learn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.ref.SoftReference;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.TimeZone;

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
    }
    @Test
    public void localTimeTest(){
        log.info("中午:{}",LocalTime.NOON);
        log.info("午夜:{}",LocalTime.MIDNIGHT);
    }
    @Test
    public void localDateTimeTest(){
        LocalDateTime todayNoon = LocalDateTime.of(LocalDate.now(),LocalTime.NOON);
        log.info("今天中午:{}",todayNoon);
        
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
    }

    @Test
    public void testZone(){
        LocalTime noonTime =LocalTime.of(12,0,0,0);
        LocalDateTime todayNoon = LocalDateTime.of(LocalDate.now(),noonTime);
        LocalDateTime yesterdayNoon = todayNoon.plus(-1,ChronoUnit.DAYS);
        System.out.println(yesterdayNoon);
        ZoneId zoneCST = TimeZone.getTimeZone("CST").toZoneId();
        Instant instant = todayNoon.atZone(zoneCST).toInstant();
        System.out.println(todayNoon.atZone(zoneCST).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(TimeZone.getDefault().getID());
        System.out.println(TimeZone.getDefault().getDisplayName());
        System.out.println(TimeZone.getTimeZone("CTT").getDisplayName());
        System.out.println(TimeZone.getTimeZone("CST").getDisplayName());
    }

}

package J_API;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


public class J_Date {
    //时间相关类
    public static void main(String[] args) throws ParseException {
        //java.util.Date------------------------------------------------------
        //file操作
        //空参构造Date(),调用System.currentTimeMillis()获取当前时间再把结果传入有参构造赋值给fastTime
        Date date = new Date();
        System.out.println(date);//Tue Aug 08 14:32:28 CST 2023
        //有参构造Date(long date)创建指定时间对象
        Date date1 = new Date(0L);//时间原点
        System.out.println(date1);//Thu Jan 01 08:00:00 CST 1970
        //long getTime()获取时间毫秒值
        long time = date1.getTime();//(可用于两个date对象的时间比较

        //setTime(long time)
        time = time + 1000L * 60 * 60 * 24 * 365;//设置时间原点后一年的时间
        date1.setTime(time);
        //SimpleDateFormat------------------------------------------------------
        //格式化时间展示,解析时间字符串
        //空参构造SimpleDateFormat()默认格式
        SimpleDateFormat sdf = new SimpleDateFormat();
        //有参构造SimpleDateFormat(String pattern)指定格式
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        // y年 M月 d日 H时 m分 s秒 S毫秒
        // 其他的字母可在API文档中查询
        //final String format(Date date)格式化日期对象为字符串
        String str = sdf.format(date1);
        System.out.println(str);//1971/1/1 上午8:00
        String str1 = sdf1.format(date1);
        System.out.println(str1);//1971年01月01日 08:00:00
        //Date parse(String source)解析字符串为日期对象
        String str_date = "2023-8-8 13:14";
        SimpleDateFormat sdf2 = new SimpleDateFormat("y-M-d HH:mm");//位数不写全也是可以的
        Date date2 = sdf2.parse(str_date);
        System.out.println(date2);//Tue Aug 08 13:14:00 CST 2023

        //格式转换:2000-11-1转为2000年11月01日
        String s1 = "2000-11-1";
        SimpleDateFormat sd1 = new SimpleDateFormat("y-M-d");
        Date d1 = sd1.parse(s1);
        SimpleDateFormat sd2 = new SimpleDateFormat("y年M月dd日");//位数自行把控
        String s2 = sd2.format(d1);
        System.out.println(s2);//2000年11月01日

        //Calendar抽象类----------------------------------------------------
        //简化时间计算问题
        //Calendar .getInstance()获取当前时间的日历对象
        Calendar cal = Calendar.getInstance();//根据系统时区获取日历对象(月份为0~11,星期日为一周的第一天)
        //Date getTime()获取日期对象
        //setTime()给日历设置日期对象
        cal.setTime(date1);
        //long getTimeInMillis()获取时间毫秒值
        //setTimeInMillis(long millis)给日历设置时间毫秒值
        //int get(int field)获取日历的某个字段信息
        //set(int field,int value)设置日历的某个字段信息
        int year = cal.get(Calendar.YEAR);//0:纪元  1:年  2:月  3:一年中的第几周
        int day = cal.get(Calendar.DATE);
        //add(int field,int amount)对某个字段增加/减少指定值
        cal.add(Calendar.MONTH, 1);
        //对于set,add等方法,加太多会自动进位
        System.out.println(cal);

        //JDK8时间相关类----------------------------------------
        //时间对象都是不可变的
        //一、Date类:ZoneId时区,Instant时间戳,ZoneDateTime带时区的时间
        //二、日期格式化类:DateTimeFormatter
        //三、日历类:LocalDate年月日,LocalTime时分秒,LocalDateTime年月日时分秒
        //四、工具类:时间间隔Duration秒 纳秒 ,Period年月日 ,ChronUnit所有时间单位

        //------------
        //ZoneId
        //Set<String> ZoneId.getAvailableZoneIds()获取所有时区名称
        //ZoneId ZoneId.systemDefault()获取系统默认时区
        //ZoneId ZoneId.of(String 州+地区)获取指定时区

        //Instant
        //Instant now()获取当前时间的时间戳对象(标准时间)
        //ZonedDateTime atZone(ZoneId)指定时区
        //Instant ofXXX(long)
        //boolean isXXX(Instant)判断
        //Instant minusXXX(long)减少
        //Instant plusXXX(long)增加

        //ZoneDateTime
        //ZoneDateTime now()获取当前时间
        //ZoneDateTime ofXXX(...)获取指定时间
        //ZoneDateTime withXXX(时间)修改时间
        //ZoneDateTime minusXXX(时间)减少时间
        //ZoneDateTime plusXXX(时间)增加时间

        //------------
        //DateTimeFormatter
        //DateTimeFormatter ofPattern(格式)获取格式对象
        //String format(时间)按指定方法格式化

        //------------
        //LocalDate年月日,LocalTime时分秒,LocalDateTime年月日时分秒
        //XXX now()获取当前时间对象
        //XXX of(...)获取指定时间对象
        //get获取字段
        //isBefore,isAfter 比较
        //withXXX,修
        // minusXXX,减
        // piusXXX,加

        //------------
        //时间间隔Duration秒 纳秒 ,Period年月日 ,ChronUnit所有时间单位
        //duration.toXXX计算差值并转换
        //chronUnit.between计算
    }
}

class 一年的第几天 {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        LocalDate date = LocalDate.parse(str);
        System.out.println(date.getDayOfYear());
    }
}
class 日期之差 {
    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 1, 10);

        long days = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println("相差天数：" + days);

        long months = ChronoUnit.MONTHS.between(startDate, endDate);
        System.out.println("相差月数：" + months);

        long years = ChronoUnit.YEARS.between(startDate, endDate);
        System.out.println("相差年数：" + years);
    }
}

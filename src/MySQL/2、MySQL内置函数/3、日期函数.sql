/*
curdate()           当前日期
curtime()           当前时间
now()               当前日期和时间

year(date)          获取指定date的年份
mouth(date)         获取指定date的月份
day(date)           获取指定date的日期

monthname(date),dayname(date) 获取月份名和星期名
weekday(date)       获取指定date的星期几(0-6)
week(date)          获取指定date的星期数(0-53)
dayofyear(date)      获取指定date是本年的第几天
dayofweek(date)      获取指定date是本周的第几天(1-7)
dayofmonth(date)     获取指定date是本月的第几天(1-31)
date_format(date,format) 格式化日期

date_add(date,INTERVAL expr type)    date加上expr时间间隔(type 单位)的时间
datediff(d1,d2)   天数差d1-d2

例:
select date_add(now(),INTERVAL 70 DAY);#从现在往后推70天

select name,datediff(curdate(),entrydate) as 'entrydays' from user order by 'entrydays' desc; # 根据进入天数倒序排序
*/

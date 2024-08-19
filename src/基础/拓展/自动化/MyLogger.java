package 基础.拓展.自动化;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 日志
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/5/8 <br> */
public class MyLogger {
    static Logger logger = Logger.getLogger("MyLog");
    static String logFileName = "myLog.log";
    static long MaxSize = 10 * 1024 * 1024;// 10MB
    static long remove = 2 * 1024 * 1024; // 2MB

    static {
        try {
            FileHandler fh = new FileHandler(logFileName, true);
            fh.setFormatter(new SimpleFormatter() {
                @Override
                public String format(LogRecord record) {
                    return String.format("%s : %s%n", record.getLevel(), record.getMessage());
                }
            });
            logger.addHandler(fh);
            check();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private MyLogger() {
    }

    static Pattern pattern = Pattern.compile("\\.([^.$]*)\\$");

    /**
     获取当前运行的方法签名

     @param o 在方法中 new Object(){} 将其传入
     @return 返回 类名#方法名(参数列表)
     */
    public static String getCurrentMethodSignature(Object o) {
        Class<?> clazz = o.getClass();
        Matcher matcher = pattern.matcher(clazz.toString());
        matcher.find();
        String className = matcher.group(1);

        Method enclosingMethod = clazz.getEnclosingMethod();
        String methodName = enclosingMethod.getName();
        Class<?>[] parameterTypes = enclosingMethod.getParameterTypes();

        StringBuilder signature = new StringBuilder(className + "#" + methodName + "(");
        for (int i = 0; i < parameterTypes.length; i++) {
            if (i > 0) signature.append(", ");
            signature.append(parameterTypes[i].getSimpleName());
        }
        signature.append(")");
        return signature.toString();
    }

    /**
     检查文件的大小,超出最大限制则移除指定大小
     */
    private static void check() {
        File file = new File(logFileName);
        long fileSize = file.length();
        if (fileSize < MaxSize) return;
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            byte[] buffer = new byte[1024];
            long remainingBytes = fileSize - remove;
            while (remainingBytes > 0) {
                int bytesRead = raf.read(buffer);
                raf.seek(raf.getFilePointer() - bytesRead);
                raf.write(buffer, 0, bytesRead);
                remainingBytes -= bytesRead;
            }
            raf.setLength(fileSize - remove);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     写入信息到log,自定义级别<br>
     OFF: 关闭日志<br>
     WARNING: 警告<br>
     INFO: 信息<br>
     SEVERE: 严重<br>
     CONFIG: 配置<br>
     FINE: 详细信息<br>
     FINER: 更详细的信息<br>
     FINEST: 极其详细的信息<br>
     ALL: 所有信息<br>
     */
    public static void log(Level level, String info) {
        // 格式日期: 年-月-日 时:分:秒
        logger.log(level, info + "\n" + getNowDate_Format());
    }

    @NotNull
    private static String getNowDate_Format() {
        return LocalDateTime.now().format(dateTimeFormatter);
    }

    public static void log(Level level, Callable<String> info) {
        try {
            logger.log(level, info.call() + "\n" + getNowDate_Format());
        } catch (Exception e) {
            logger.log(level, "call - Error\n" + getNowDate_Format());
        }
    }

    /**
     日志输出时附带类和方法签名

     @param o     new Object(){}
     @param level 日志级别
     @param info  日志信息
     */
    public static void log(Level level, String info, Object o) {
        logger.log(level, getCurrentMethodSignature(o) + " : " + info + "\n" + getNowDate_Format());
    }


    /**
     写入信息到log, 级别OFF
     */
    public static void off(String info) {
        log(Level.OFF, info);
    }

    /**
     写入信息到log, 级别WARNING
     */
    public static void warning(String info) {
        log(Level.WARNING, info);
    }



    /**
     写入信息到log, 级别INFO
     */
    public static void info(String info) {
        log(Level.INFO, info);
    }

    /**
     写入信息到log, 级别SEVERE
     */
    public static void severe(String info) {
        log(Level.SEVERE, info);
    }

    /**
     写入信息到log, 级别CONFIG
     */
    public static void config(String info) {
        log(Level.CONFIG, info);
    }

    /**
     写入信息到log, 级别FINE
     */
    public static void fine(String info) {
        log(Level.FINE, info);
    }

    /**
     写入信息到log, 级别FINER
     */
    public static void finer(String info) {
        log(Level.FINER, info);
    }

    /**
     写入信息到log, 级别FINEST
     */
    public static void finest(String info) {
        log(Level.FINEST, info);
    }

    /**
     写入信息到log, 级别OFF
     */
    public static void off(String info, Object o) {
        log(Level.OFF, info, o);
    }

    /**
     写入信息到log, 级别WARNING
     */
    public static void warning(String info, Object o) {
        log(Level.WARNING, info, o);
    }

    /**
     写入信息到log, 级别INFO
     */
    public static void info(String info, Object o) {
        log(Level.INFO, info, o);
    }

    /**
     写入信息到log, 级别SEVERE
     */
    public static void severe(String info, Object o) {
        log(Level.SEVERE, info, o);
    }

    /**
     写入信息到log, 级别CONFIG
     */
    public static void config(String info, Object o) {
        log(Level.CONFIG, info, o);
    }

    /**
     写入信息到log, 级别FINE
     */
    public static void fine(String info, Object o) {
        log(Level.FINE, info, o);
    }

    /**
     写入信息到log, 级别FINER
     */
    public static void finer(String info, Object o) {
        log(Level.FINER, info, o);
    }

    /**
     写入信息到log, 级别FINEST
     */
    public static void finest(String info, Object o) {
        log(Level.FINEST, info, o);
    }

    /**
     写入信息到log, 级别ALL
     */
    public static void all(String info, Object o) {
        log(Level.ALL, info, o);
    }

    /**
     写入信息到log, 级别ALL
     */
    public static void all(String info) {
        log(Level.ALL, info);
    }

}

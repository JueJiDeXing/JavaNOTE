package 基础.拓展.自动化;

import java.awt.*;
import java.time.LocalDateTime;


/**
 自动执行op.txt操作(可选,在config.yaml中修改)<br>
 运行时长到达后会停止程序,如果是0点~2点,会自动关机
 鼠标移动到右上角,程序会自动退出
 */
public class 鼠标键盘控制 {

    public static void main(String[] args) throws AWTException {
        MyLogger.all("-----------------------\n" + "$$开始运行");
        int runTime = YamlReader.getInt("runTime", 120);
        MyLogger.info("应运行时长:" + runTime + "分钟");
        boolean shutdown = isShutdown();
        MyLogger.info("是否需要关机:" + shutdown);
        Controller.robot = new Robot();
        Controller.robot.delay(1000);

        new Listener(code -> {
            if (Listener.getKeyText(code).equals("S")) {
                Voice.play("E:\\ideaProject\\NOTE\\src\\基础\\拓展\\自动化\\sounds\\1.wav");
                MyLogger.info("初始化完毕,开始运行...");
                new ShutdownOrExit(runTime, shutdown).start();
                new ExecThread(Controller.robot).start();
            }
        }, null);
    }

    private static boolean isShutdown() {
        int hour = LocalDateTime.now().getHour();
        int minute = LocalDateTime.now().getMinute();
        String shutdownTime = YamlReader.getString("shutdownTime", "23:00~2:00");

        String[] split = shutdownTime.split("~");
        String[] start = split[0].split(":");
        String[] end = split[1].split(":");

        int startHour = Integer.parseInt(start[0]), startMinute = Integer.parseInt(start[1]);
        int endHour = Integer.parseInt(end[0]), endMinute = Integer.parseInt(end[1]);

        if (startHour <= endHour) {//一天内
            return (startHour <= hour && hour <= endHour) &&
                    (startMinute <= minute && minute <= endMinute);
        } else {// 跨晚上
            if (hour == endHour) return minute <= endMinute;
            if (hour == startHour) return startMinute <= minute;
            return (startHour <= hour || hour <= endHour);
        }

    }
}


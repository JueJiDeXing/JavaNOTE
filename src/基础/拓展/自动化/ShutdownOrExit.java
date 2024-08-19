package 基础.拓展.自动化;

/**
 停机线程
 */
public class ShutdownOrExit extends Thread {
    int runTime;// 停机时间
    boolean shutdown;

    public ShutdownOrExit(int runTime, boolean shutdown) {
        this.runTime = runTime;
        this.shutdown = shutdown;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (!checkTime(startTime, shutdown)) ;
        MyLogger.info("由于时长到达结束,-停机");
        System.exit(0);
    }

    private boolean checkTime(long startTime, boolean shutdown) {
        long minute = (System.currentTimeMillis() - startTime) / 1000 / 60;
        if (minute < runTime) return false;
        MyLogger.info("运行时长到达");
        try {
            if (shutdown) {
                MyLogger.info("准备关机");
                Runtime.getRuntime().exec("shutdown -s -t 1");
                Sleep.sleep(10000);
                MyLogger.warning("休眠10s仍未关机,error-停机");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }


}

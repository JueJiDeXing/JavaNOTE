package 基础.拓展.自动化;

/**
 睡!
 */
public class Sleep {
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

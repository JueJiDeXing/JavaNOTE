package MyNote;

public class 异常处理 {
    //异常体系
    //      Java.lang.Throwable
    //    ┌──────────┴─────────┐
    // Error(错误)         Exception(异常)
    //              ┌───────────┴────────────┐
    //   RuntimeException(运行时异常)     其他异常(编译时异常)
    //            └────...
    //Error
    //系统级别错误,属于严重问题

    //Exception
    //异常,表示程序可能出现的问题,一般用Exception及其子类封装

    //RuntimeException及其子类
    //运行时异常,编译阶段通常不会给出提醒(如:数组越界异常)
    //例:
    //int[] arr = {1, 2, 3};
    //System.out.println(arr[5]);//数组越界

    //其他异常
    //编译时异常,在编译阶段就会给出提醒(如:日期解析异常)
    //例:
    // String time = "2023年1月1日";
    // SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");
    // Date date = sdf.parse(time);//ParseException

    public static void main(String[] args) {
        //异常处理方式
        //一、JVM默认:停止程序,并把异常信息输出在控制台
        //System.out.println(24/0);

        //二、异常捕获
        /*
         * try{
         * 可能出现异常的代码;
         * }catch(异常类名 变量名){
         * 异常处理代码;
         * }finally{
         * 最终执行
         * }
         * */
        int[] arr = {1, 2, 3, 4, 5};
        try {
            //只能捕获一个异常,捕获到异常则终止try的执行,并与catch异常对比
            //如果没有catch异常与之匹配,则交给JVM处理
            System.out.println(arr[10]);
            System.out.println(12 / 0);
            String s = null;
            System.out.println(s);
        } catch (ArrayIndexOutOfBoundsException e) {//捕获数组越界异常
            System.out.println("索引越界");
            //异常成员方法
            //String getMessage()返回此throwable的详细消息字符串
            //String toString()返回此可抛出的简短描述
            //void printStackTrace()输出错误信息到控制台(包含信息最多,toString次之)
            System.out.println(e.getMessage());//Index 10 out of bounds for length 5
            System.out.println(e.toString());//java.lang.ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 5
            e.printStackTrace();//打印红字的报错信息,但不终止程序

            System.err.println();//用于打印错误信息的红字,运行在另一个线程

        } catch (ArithmeticException | NullPointerException e) {
            System.out.println("计算异常或空指针异常");
        } catch (Exception e) {//如果要捕获多个异常,父类异常要写在下面
            System.out.println("未知异常");
        }finally {
            //除非JVM退出,否则一定执行
            System.out.println("一定执行finally");//IO流释放资源之类的代码适合写在finally
        }


    }

    //三、抛出异常
    //一般写在方法里面,然后在调用处进行捕获
    public static int method(int num) throws ArithmeticException, NullPointerException {
        //throws写在方法后面,告诉调用者方法可能会有哪些异常
        //编译时异常必须要写,运行时异常可以省略
        if (num > 50) {
            //throw写在方法里面,结束方法,手动抛出异常交给调用者
            throw new RuntimeException("数字太大");
        } else if (num < 0) {
            throw new RuntimeException();
        } else {
            return num * 2;
        }

    }
}

//自定义异常
class MinusException extends RuntimeException {
    //编译时异常继承Exception即可
    public MinusException() {
        super();
    }

    public MinusException(String message) {
        super(message);
    }
}

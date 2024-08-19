package 课程.数值计算;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class 三体 {
    static int m1, m2, m3;
    static BigFraction dt = new BigFraction(1, 10);
    static BigFraction a1x, a1y, a2x, a2y, a3x, a3y;
    static BigFraction x1, y1, x2, y2, x3, y3;
    static BigFraction vx1, vy1, vx2, vy2, vx3, vy3;

    public static void main(String[] args) throws Exception {
        BigFraction.isLazy = true;
        sc();
        for (int i = 0; i < 20; i++) {
            IO.print(String.format("%.4f %.4f %.4f %.4f %.4f %.4f\n",
                    x1.getValue(), y1.getValue(), x2.getValue(), y2.getValue(), x3.getValue(), y3.getValue()));
            for (int j = 0; j < 100; j++) {
                runge_kutta();
                IO.print(String.format("%.4f %.4f %.4f %.4f %.4f %.4f\n",
                        x1.getValue(), y1.getValue(), x2.getValue(), y2.getValue(), x3.getValue(), y3.getValue()));
                IO.flush();
            }
        }
    }

    private static void sc() throws IOException {
        m1 = IO.I();
        m2 = IO.I();
        m3 = IO.I();
        x1 = new BigFraction(IO.I());
        y1 = new BigFraction(IO.I());
        x2 = new BigFraction(IO.I());
        y2 = new BigFraction(IO.I());
        x3 = new BigFraction(IO.I());
        y3 = new BigFraction(IO.I());
        vx1 = new BigFraction(IO.I());
        vy1 = new BigFraction(IO.I());
        vx2 = new BigFraction(IO.I());
        vy2 = new BigFraction(IO.I());
        vx3 = new BigFraction(IO.I());
        vy3 = new BigFraction(IO.I());
    }


    static void runge_kutta() {
        acceleration();
        BigFraction k1_vx1 = a1x.mul(dt), k1_vy1 = a1y.mul(dt),
                k1_vx2 = a2x.mul(dt), k1_vy2 = a2y.mul(dt),
                k1_vx3 = a3x.mul(dt), k1_vy3 = a3y.mul(dt);
        BigFraction k2_vx1 = a1x.add(k1_vx1.div(2)).mul(dt), k2_vy1 = a1y.add(k1_vy1.div(2)).mul(dt),
                k2_vx2 = a2x.add(k1_vx2.div(2)).mul(dt), k2_vy2 = a2y.add(k1_vy2.div(2)).mul(dt),
                k2_vx3 = a3x.add(k1_vx3.div(2)).mul(dt), k2_vy3 = a3y.add(k1_vy3.div(2)).mul(dt);
        BigFraction k3_vx1 = a1x.add(k2_vx1.div(2)).mul(dt), k3_vy1 = a1y.add(k2_vy1.div(2)).mul(dt),
                k3_vx2 = a2x.add(k2_vx2.div(2)).mul(dt), k3_vy2 = a2y.add(k2_vy2.div(2)).mul(dt),
                k3_vx3 = a3x.add(k2_vx3.div(2)).mul(dt), k3_vy3 = a3y.add(k2_vy3.div(2)).mul(dt);
        BigFraction k4_vx1 = a1x.add(k3_vx1).mul(dt), k4_vy1 = a1y.add(k3_vy1).mul(dt),
                k4_vx2 = a2x.add(k3_vx2).mul(dt), k4_vy2 = a2y.add(k3_vy2).mul(dt),
                k4_vx3 = a3x.add(k3_vx3).mul(dt), k4_vy3 = a3y.add(k3_vy3).mul(dt);
        x1.setValue(x1.add(dt.mul((vx1.add(k1_vx1.div(2)).add(k2_vx1.div(2)).add(k3_vx1)))));
        x2.setValue(x2.add(dt.mul((vx2.add(k1_vx2.div(2)).add(k2_vx2.div(2)).add(k3_vx2)))));
        x3.setValue(x3.add(dt.mul((vx3.add(k1_vx3.div(2)).add(k2_vx3.div(2)).add(k3_vx3)))));
        y1.setValue(y1.add(dt.mul((vy1.add(k1_vy1.div(2)).add(k2_vy1.div(2)).add(k3_vy1)))));
        y2.setValue(y2.add(dt.mul((vy2.add(k1_vy2.div(2)).add(k2_vy2.div(2)).add(k3_vy2)))));
        y3.setValue(y3.add(dt.mul((vy3.add(k1_vy3.div(2)).add(k2_vy3.div(2)).add(k3_vy3)))));
        vx1.setValue(vx1.add(k1_vx1.add(k2_vx1.mul(2)).add(k3_vx1.mul(2)).add(k4_vx1)).div(6));
        vx2.setValue(vx2.add(k1_vx2.add(k2_vx2.mul(2)).add(k3_vx2.mul(2)).add(k4_vx2)).div(6));
        vx3.setValue(vx3.add(k1_vx3.add(k2_vx3.mul(2)).add(k3_vx3.mul(2)).add(k4_vx3)).div(6));
        vy1.setValue(vy1.add(k1_vy1.add(k2_vy1.mul(2)).add(k3_vy1.mul(2)).add(k4_vy1)).div(6));
        vy2.setValue(vy2.add(k1_vy2.add(k2_vy2.mul(2)).add(k3_vy2.mul(2)).add(k4_vy2)).div(6));
        vy3.setValue(vy3.add(k1_vy3.add(k2_vy3.mul(2)).add(k3_vy3.mul(2)).add(k4_vy3)).div(6));
    }

    static void acceleration() {
        // int G=1
        BigFraction dx12 = x1.sub(x2), dy12 = y1.sub(y2),
                dx13 = x1.sub(x3), dy13 = y1.sub(y3),
                dx23 = x2.sub(x3), dy23 = y2.sub(y3);
        BigFraction r12_3 = dx12.pow(2).add(dy12.pow(2)).pow(3).sqrt(),
                r13_3 = dx13.pow(2).add(dy13.pow(2)).pow(3).sqrt(),
                r23_3 = dx23.pow(2).add(dy23.pow(2)).pow(3).sqrt();
        a1x = dx12.div(r12_3).mul(-m2).sub(dx13.div(r13_3).mul(-m3));
        a1y = dy12.div(r12_3).mul(-m2).sub(dy13.div(r13_3).mul(-m3));
        a2x = dx12.div(r12_3).mul(m1).sub(dx23.div(r23_3).mul(-m3));
        a2y = dy12.div(r12_3).mul(m1).sub(dy23.div(r23_3).mul(-m3));
        a3x = dx13.div(r13_3).mul(m1).add(dx23.div(r23_3).mul(m2));
        a3y = dy13.div(r13_3).mul(m1).add(dy23.div(r23_3).mul(m2));
    }

    @SuppressWarnings("all")
    static class IO {
        static InputStream in = System.in;
        static PrintStream out = System.out;

        static {
            try {
                File inFile = new File("E:\\ideaProject\\NOTE\\src\\课程\\数值计算\\in.txt");
                if (inFile.exists()) in = new FileInputStream(inFile);
                File outFile = new File("E:\\ideaProject\\NOTE\\src\\课程\\数值计算\\out.txt");
                if (outFile.exists()) out = new PrintStream(new FileOutputStream(outFile));
            } catch (Exception ignored) {
            }
        }

        static BufferedReader bf = new BufferedReader(new InputStreamReader(in), 65535);
        static StreamTokenizer st = new StreamTokenizer(bf);
        static Scanner sc = new Scanner(in);
        static PrintWriter pw = new PrintWriter(out);

        static int I() throws IOException {
            st.nextToken();
            return (int) st.nval;
        }

        static long L() throws IOException {
            return sc.nextLong();
        }

        static double D() throws IOException {
            return sc.nextDouble();
        }

        static int[] List(int n) throws IOException {
            int[] res = new int[n];
            for (int i = 0; i < n; i++) res[i] = I();
            return res;
        }

        static String Line() throws IOException {
            String res = bf.readLine();
            while (res.isEmpty()) res = bf.readLine();
            return res;
        }

        static <T> void print(T s) {
            pw.print(s);
        }

        static void println(String s) {
            pw.println(s);
        }

        static <T> void println(T[] s) {
            pw.println(Arrays.toString(s));
        }

        static <T> void println(T[][] s) {
            pw.println(Arrays.deepToString(s));
        }

        static void flush() {
            pw.flush();
        }
    }
}

package 课程;

public class 均绩点计算 {
    static class Pair {
        double xueFen, score;
        String name = "";

        Pair(double xueFen, double score) {
            this.xueFen = xueFen;
            this.score = score;
        }

        Pair(double xueFen, double score, String name) {
            this.xueFen = xueFen;
            this.score = score;
            this.name = name;
        }

        static Pair of(double xueFen, double score) {
            return new Pair(xueFen, score);
        }

        static Pair[] of(double... data) {
            int n = data.length / 2;
            Pair[] ans = new Pair[n];
            for (int i = 0; i < 2 * n; i += 2) {
                ans[i / 2] = new Pair(data[i], data[i + 1]);
            }
            return ans;
        }

        static Pair of(double xueFen, double score, String name) {
            return new Pair(xueFen, score);
        }

        double getJiDian() {
            return toJiDian(score);
        }

        static double toJiDian(double score) {
            if (score >= 90) return 4.0;
            if (score >= 85) return 3.7;
            if (score >= 82) return 3.3;
            if (score >= 78) return 3.0;
            if (score >= 75) return 2.7;
            if (score >= 71) return 2.3;
            if (score >= 66) return 2.0;
            if (score >= 62) return 1.7;
            if (score >= 60) return 1.3;
            return 0.0;
        }
    }


    public static void main(String[] args) throws Exception {
        Pair[] data_大二上 = new Pair[]{
                Pair.of(1, 72),
                Pair.of(0.5, 75),
                Pair.of(1, 94),
                Pair.of(2, 68),
                Pair.of(3, 91),
                Pair.of(2, 87),
                Pair.of(3, 85),
                Pair.of(0.5, 97),
                Pair.of(1, 90),
                Pair.of(3, 87),
                Pair.of(3, 98),
                Pair.of(4, 94),
                Pair.of(0.5, 84),
                Pair.of(3, 77),
                Pair.of(0.5, 81),
                Pair.of(3, 80),
                Pair.of(2, 90),
        };
        Pair[] data_大二下 = new Pair[]{
                Pair.of(2, 74),
                Pair.of(2, 91),
                Pair.of(2.5, 94),
                Pair.of(3, 87),
                Pair.of(1, 98),
                Pair.of(1, 83),
                Pair.of(2, 90),
                Pair.of(0.5, 87),
                Pair.of(4, 86),
                Pair.of(3.5, 95),
                Pair.of(2, 95),
                Pair.of(1, 86),
                Pair.of(0.5, 91),
                Pair.of(0.5, 84),
                Pair.of(3, 73),
        };
        System.out.printf("大二上: %.2f\n", getAns(data_大二上));
        System.out.printf("大二下: %.2f\n", getAns(data_大二下));
    }

    private static double getAns(Pair[] data) {
        double sumXueFen = 0, sumJiDian = 0;
        for (Pair p : data) {
            sumXueFen += p.xueFen;
            sumJiDian += p.getJiDian() * p.xueFen;
        }
        return sumJiDian / sumXueFen;
    }
}

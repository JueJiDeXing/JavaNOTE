package MyNote.Temp;

import java.util.Arrays;
import java.util.*;
public class chinese {
    static class Data {
        int n = 0;
        double avg = 0;
        double S1 = 0;
        double S2 = 0;
    }

    public static void main(String[] args) {
        Data data = getDataBySample(new double[]{4.9, 4.8, 5.0, 5.1, 4.9, 4.9, 5.0, 5.2, 5.2});
        System.out.println(data.avg);
        System.out.println(data.S1);
        System.out.println(data.S2);
        double t=2.306;
        System.out.println(Arrays.toString(calConfidenceInterval(data, t)));
    }

    private static double[] calConfidenceInterval(Data data, double t) {
        double len2 = t * data.S1 / Math.sqrt(data.n);
        System.out.println(len2);
        return new double[]{data.avg - len2, data.avg + len2};
    }

    /**
     通过样本计算数据:均值和方差

     @param arr 状态分布取样Xi
     */
    private static Data getDataBySample(double[] arr) {
        Data data = new Data();
        data.n = arr.length;
        for (double xi : arr) {
            data.avg += xi;
        }
        data.avg /= data.n;
        for (double xi : arr) {
            data.S2 += Math.pow(xi - data.avg, 2);
        }
        data.S2 /= (data.n - 1);
        data.S1 = Math.sqrt(data.S2);
        return data;
    }

    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int[] p : items1) m.merge(p[0], p[1], Integer::sum);
        for (int[] p : items2) m.merge(p[0], p[1], Integer::sum);
        List<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : m.entrySet()){
            ans.add(List.of(e.getKey(), e.getValue()));
        }
        ans.sort(Comparator.comparingInt(a -> a.get(0)));
        return ans;
    }
}

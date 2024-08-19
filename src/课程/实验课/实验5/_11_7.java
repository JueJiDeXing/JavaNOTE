package 课程.实验课.实验5;

        import java.util.ArrayList;
        import java.util.Random;

public class _11_7 {
    // 打乱ArrayList
    public static void shuffle(ArrayList<Integer> list) {
        Random random = new Random();
        int n = list.size();
        for (int i = 0; i < n; i++) {// 对于list[i],随机选择后面的一个元素与它交换
            int r = i + random.nextInt(n - i);
            swap(list, i, r);
        }
    }

    public static void swap(ArrayList<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     测试
     */
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 15; i++) list.add(i);

        System.out.println("初始数组: " + list);
        shuffle(list);
        System.out.println(list);
        shuffle(list);
        System.out.println(list);
        shuffle(list);
        System.out.println(list);
    }

}

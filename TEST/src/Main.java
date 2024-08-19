import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.Queue;

class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }
/*
60 100 70  ->  100 100
50 60 100 70 -> 60 100 100
60 50 100 70 -> 60 100 100
60 100 50 70 -> 100 100 70
60 100 70 50 -> 100 100 70
 */
    static int n, k, A;
    static int[] a;

    public static void main(String[] args) {
        n = nextInt();
        k = nextInt();
        A = nextInt();
        a = new int[n + 1];
        for (int i = 1; i <= n; i++) a[i] = nextInt();
        a[0] = A;

        long ans = cal();
        for (int i = 0; i < n; i++) {
            swap(i, i + 1);
            ans = Math.max(ans, cal());
        }
        System.out.println(ans);
    }

    static void swap(int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    static long cal() {
        Queue<Integer> queue = new LinkedList<>();// 单减队列, 容量k
        long ans = 0;
        for (int i = 0; i <= n; i++) {
            while (!queue.isEmpty() && (queue.peek() < i - k + 1 || a[queue.peek()] <= a[i])) {
                queue.poll();
            }
            queue.add(i);
            if (i >= k - 1) {
                ans += a[queue.peek()];
            }
        }
        return ans;
    }

}

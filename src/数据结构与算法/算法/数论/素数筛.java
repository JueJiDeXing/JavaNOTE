package 数据结构与算法.算法.数论;

public class 素数筛 {
    //求[0,N]的质数数量、对[0,N]的质数求和、求[0,N]的第k个质数

    /**
     <h1>传统做法</h1>
     */
    public int count1(int N) {
        int sum = 0;
        for (int i = 2; i <= N; i++) {
            if (isPrime1(i)) {
                sum++;
            }
        }
        return sum;
    }

    public boolean isPrime1(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isPrime2(int n) {
        int m = (int) Math.sqrt(n);//优化: 假如a<b,a*b=n,那么a<=sqrt(n)<=b
        for (int i = 2; i <= m; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     <h1>埃氏筛法</h1>
     */
    public int count2(int N) {
        if (N < 2) return 0;
        boolean[] isComposite = new boolean[N + 1];//是否为合数
        for (int i = 2; i <= N / i; i++) {
            if (!isComposite[i]) {
                //是质数,把后面的合数筛掉
                for (int j = i * i; j <= N; j += i) {
                    //从i^2开始是因为:假设前面质数是ai,这次质数是b,则筛ai的时候把ai*b都已经筛掉了,所以从b^2开始即可
                    isComposite[j] = true;
                }
            }
        }
        int sum = 0;
        for (int i = 2; i <= N; i++) {
            if (!isComposite[i]) sum++;
        }
        return sum;
    }

    /**
     <h1>欧拉筛法</h1>
     埃氏筛的改进
     */
    public int count3(int N) {
        if (N < 2) return 0;
        boolean[] isComposite = new boolean[N + 1];//是否为合数
        isComposite[0] = isComposite[1] = true;
        int[] prime = new int[N + 1];//存储素数
        int sum = 0;
        for (int i = 2; i <= N; i++) {
            if (!isComposite[i]) {
                prime[sum++] = i;//是素数
            }
            //把后面的合数( i与现有质数的倍数(含自身) )筛掉
            for (int j = 0; prime[j] * i <= N && j <= sum; j++) {
                isComposite[prime[j] * i] = true;
                if (i % prime[j] == 0) break;//防止重复筛
            }
            // for (int p : prime) {
            //     if (p * i > N) break;
            //     isComposite[p * i] = true;
            //     if (i % p == 0) break;//防止重复筛
            // }
        }
        return sum;
    }
}

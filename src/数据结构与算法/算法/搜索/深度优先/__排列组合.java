package 数据结构与算法.算法.搜索.深度优先;

import java.util.*;

public class __排列组合 {
}

class 全排列 {
    //求一个序列的全部排列
    public static void main(String[] args) {
        System.out.println(permute(new int[]{2, 2, 1, 1}));
    }

    /**
     @param nums 不含重复元素的序列
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        dfs(nums, 0, lists, new ArrayList<>(), new boolean[nums.length]);
        return lists;//传入数组有重复元素时需要对排列去重
    }

    /**
     深度优先搜索,多路递归

     @param nums
     @param depth  当前深度(已排列元素个数)
     @param lists  所有排列
     @param list   当前排列
     @param isUsed 记录当前排列中已使用的元素
     */
    public static void dfs(int[] nums, int depth, List<List<Integer>> lists, List<Integer> list, boolean[] isUsed) {
        if (depth == nums.length) {
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {//多路递归,枚举所有情况
            if (isUsed[i]) {//数组记录是否已使用过
                continue;
            }
            isUsed[i] = true;//未使用过则可以加入,进行递归
            list.add(nums[i]);
            int index = list.size() - 1;
            dfs(nums, depth + 1, lists, list, isUsed);//递归下一层搜索
            //list.remove((Integer) nums[i]);有重复元素时不可使用此方法
            list.remove(index);//出栈
            isUsed[i] = false;
        }
    }

    /**
     对重复元素的处理

     @param nums
     @return
     */
    public static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);//对数组排序,使重复元素相邻
        dfs2(nums, 0, lists, new ArrayList<>(), new boolean[nums.length]);
        return lists;
    }

    public static void dfs2(int[] nums, int depth, List<List<Integer>> lists, List<Integer> list, boolean[] isUsed) {
        if (depth == nums.length) {
            lists.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {//多路递归,枚举所有情况
            if (i > 0 && nums[i] == nums[i - 1] && !isUsed[i - 1]) {//重复元素排列时必须先排列前面的
                continue;
            }
            if (isUsed[i]) {//数组记录是否已使用过
                continue;
            }
            isUsed[i] = true;//未使用过则可以加入,进行递归
            list.add(nums[i]);
            int index = list.size() - 1;
            dfs2(nums, depth + 1, lists, list, isUsed);//递归下一层搜索
            //list.remove((Integer) nums[i]);有重复元素时不可使用此方法
            list.remove(index);//出栈
            isUsed[i] = false;
        }
    }

}

class 全子集 {
    //不含重复元素的集合,求它的所有子集
    public static void main(String[] args) {
        List<List<Integer>> subsets = subsets(new int[]{1, 2, 3});
        System.out.println(subsets);
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        doSub(0, new ArrayList<>(), nums, res);
        return res;
    }

    public static void doSub(int start, ArrayList<Integer> path, int[] nums, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            doSub(i + 1, path, nums, res);//递归start从i+1开始
            //path.remove((Integer) nums[i]);
            path.remove(path.size() - 1);
        }
    }
    /*回溯算法
            /--2--3
        /--1 --3
       /
    null --2 --3
       \
        \--3
     */
}

class 重复集合的子集 {
    //含重复元素的集合,求它的所有子集
    /*
    示例 ：
    输入：nums = [1,2,2]
    输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, res, new ArrayList<>());
        return new ArrayList<>(new HashSet<>(res));
    }

    public void dfs(int[] nums, int start, List<List<Integer>> res, List<Integer> list) {
        res.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;//已经添加过了,再遇到重复元素不添加
            list.add(nums[i]);
            dfs(nums, i + 1, res, list);
            list.remove(list.size() - 1);
        }
    }
}

class k组合 {
    //1~n,任取k个数的组合
    public static void main(String[] args) {
        System.out.println(combine(5, 3));
    }

    static List<List<Integer>> lists = new ArrayList<>();

    public static List<List<Integer>> combine(int n, int k) {
        dfs(n, k, 1, new LinkedList<>());
        return lists;
    }

    /**
     @param n     1~n的数
     @param k     目标个数
     @param start 当前索引,从start开始与后面进行组合
     @param stack 当前存储的组合
     */
    public static void dfs(int n, int k, int start, LinkedList<Integer> stack) {
        if (stack.size() == k) {//凑够k个
            lists.add(new ArrayList<>(stack));
            return;
        }
        for (int i = start; i <= n; i++) {
            if (k - stack.size() > n - i + 1) {//剪枝
                break; // 缺少的个数 > 剩余可用元素数量,不需要再尝试组合
            }
            stack.push(i);
            dfs(n, k, i + 1, stack);//将start改为1,即为k排列
            stack.pop();
        }
    }
}

class 不重复非空子集数 {
    //每个字符只能使用一次,重复字符可用次数为它的个数
    public int numTilePossibilities(String tiles) {
        Map<Character, Integer> count = new HashMap<>();
        for (char t : tiles.toCharArray()) {
            count.put(t, count.getOrDefault(t, 0) + 1);//统计各类字符个数
        }
        Set<Character> tile = new HashSet<>(count.keySet());//可用字符
        return dfs(tiles.length(), count, tile) - 1;
    }

    /**
     @param i     当前可用字符种数
     @param count 字符::剩余数量
     @param tile  字符种类
     */
    public int dfs(int i, Map<Character, Integer> count, Set<Character> tile) {
        if (i == 0) {//已无字符
            return 1;
        }
        int res = 1;
        for (char t : tile) {
            if (count.get(t) > 0) {//还有字符t可用
                count.put(t, count.get(t) - 1);
                res += dfs(i - 1, count, tile);

                count.put(t, count.get(t) + 1);
            }
        }
        return res;
    }
    /* 示例:AABC
             /--B
         /--A--C
        |
     /--A --B--A--C
     |  |    \--C--A
     |  |
     |   \--C--A--B
     |       \--B--A
    null--B...
     \--C...

    */
}

class _39组数总和 {
    /*
    给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
    找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
    candidates 中的 同一个 数字可以 无限制重复被选取 。
    如果至少一个数字的被选数量不同，则两种组合是不同的。
    对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     */
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, target, 0, new LinkedList<>());
        return res;
    }

    /**
     @param candidates 全集
     @param remainder  剩余量
     @param start      起始组合索引
     @param stack      栈
     */
    public void dfs(int[] candidates, int remainder, int start, LinkedList<Integer> stack) {
        if (remainder < 0) {//无解
            return;
        }
        if (remainder == 0) {//找到解
            res.add(new ArrayList<>());
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];
            if (remainder < candidate) {//剪枝
                continue;
            }
            //搜索
            stack.push(candidate);
            dfs(candidates, remainder - candidate, i, stack);
            stack.pop();
        }
    }
}

class _40组数总和2 {
    // 每个数字只能使用1次
    // 给出的集合可能存在重复元素,结果不能出现重复的组合
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);//排序,使重复元素相邻
        dfs(candidates, target, 0, new LinkedList<>(), new boolean[candidates.length]);
        return res;
    }

    /**
     @param candidates 全集
     @param remainder  剩余量
     @param start      起始组合索引
     @param stack      栈
     @param isUsed     记录是否使用
     */
    public void dfs(int[] candidates, int remainder, int start, LinkedList<Integer> stack, boolean[] isUsed) {
        if (remainder < 0) {//无解
            return;
        }
        if (remainder == 0) {//找到解
            res.add(new ArrayList<>());
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];
            if (remainder < candidate) {//剪枝
                continue;
            }
            if (i > 0 && candidate == candidates[i - 1] && !isUsed[i - 1]) {
                //遇到重复元素,必须先使用前面的才能使用后面重复的
                continue;
            }
            //搜索
            stack.push(candidate);
            isUsed[i] = true;
            dfs(candidates, remainder - candidate, i + 1, stack, isUsed);//start从i+1开始,一个数字不能重复使用
            isUsed[i] = false;
            stack.pop();
        }
    }
}

class _216组数总和3 {
    //数字1~9,选择k个,相加为target的组合
    //每个数字只能使用一次
    //1~n,任取k个数的组合
    public static void main(String[] args) {
        System.out.println(combineSum(8, 3));
    }

    static List<List<Integer>> lists = new ArrayList<>();

    public static List<List<Integer>> combineSum(int target, int k) {
        dfs(k, 1, target, new LinkedList<>());
        return lists;
    }

    /**
     @param k      目标个数
     @param start  当前索引,从start开始与后面进行组合
     @param target 还需要凑的大小
     @param stack  当前存储的组合
     */
    public static void dfs(int k, int start, int target, LinkedList<Integer> stack) {
        if (target < 0) {
            return;
        }
        if (stack.size() == k) {//凑够k个
            if (target == 0) {
                lists.add(new ArrayList<>(stack));
            }
            return;
        }
        for (int i = start; i <= 9; i++) {//1~9
            if (target < i || k - stack.size() > 10 - i) {//剪枝
                // 1.当前想放的i比target大
                // 2.缺少的个数 > 剩余可用元素数量,不需要再尝试组合
                break;
            }
            stack.push(i);
            dfs(k, i + 1, target - i, stack);
            stack.pop();
        }
    }
}

class _15三数之和 {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> stack = new LinkedList<>();

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        dfs(nums, 3, 0, 0, nums.length - 1);
        return res;
    }

    public void dfs(int[] nums, int n, int target, int start, int end) {
        if (n == 2) {//两数之和
            twoSum(nums, start, end, target);
            return;
        }
        for (int k = start; k < end; k++) {
            if (k > start && nums[k] == nums[k - 1]) continue;
            stack.push(nums[k]);
            dfs(nums, n - 1, target - nums[k], k + 1, end);
            stack.pop();
        }
    }

    public void twoSum(int[] nums, int i, int j, int target) {
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {//找到解
                List<Integer> result = new ArrayList(stack);
                result.add(nums[i]);
                result.add(nums[j]);
                res.add(result);
                i++;//继续搜索
                j--;
                while (i < j && nums[i] == nums[i - 1]) i++;//去重
                while (i < j && nums[j] == nums[j + 1]) j--;
            }
        }
    }

}

class four {
    public List<List<Integer>> threeSum(int[] nums) {
        return nSum(nums, 3, 0);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        return nSum(nums, 4, target);
    }

    List<List<Integer>> nSum(int[] a, int n, long target) {
        return new AbstractList<List<Integer>>() {
            final List<List<Integer>> res = new ArrayList<>();
            final List<Integer> path = new ArrayList<>();

            @Override
            public int size() {
                init();
                return res.size();
            }

            @Override
            public List<Integer> get(int index) {
                init();
                return res.get(index);
            }

            void init() {
                if (res.isEmpty()) {
                    Arrays.sort(a);
                    dfs(a, 0, a.length - 1, n, target);
                }
            }

            void dfs(int[] a, int i, int j, int n, long target) {
                if (n == 2) {
                    two(a, i, j, target);
                } else if (n > 2) {
                    hit(a, i, j, n, target);
                }
            }

            void two(int[] a, int i, int j, long target) {
                if (i >= j) {
                    return;
                }
                long max = 0;
                long min = 0;
                for (int k = 0; k < 2; k++) {
                    min += a[i + k];
                    max += a[j - k];
                }
                if (target < min || target > max) {
                    return;
                }
                while (j > i) {
                    long sum = a[i] + a[j];
                    if (sum < target) {
                        i++;
                    } else if (sum > target) {
                        j--;
                    } else {
                        path.add(a[i]);
                        path.add(a[j]);
                        res.add(new ArrayList<>(path));
                        path.remove(path.size() - 1);
                        path.remove(path.size() - 1);
                        while (j > i && a[i] == a[i + 1]) {
                            i++;
                        }
                        while (j > i && a[i] == a[j - 1]) {
                            j--;
                        }
                        i++;
                        j--;
                    }
                }
            }

            void hit(int[] a, int i, int j, int n, long target) {
                int begin = i;
                int end = j;
                if (i + n - 2 >= j) {
                    return;
                }
                long max = 0;
                long min = 0;
                for (int k = 0; k < n; k++) {
                    min += a[i + k];
                    max += a[j - k];
                }
                if (target < min || target > max) {
                    return;
                }
                while (j > i + n - 2) {
                    long sufMax = 0;
                    long preMin = 0;
                    for (int k = 0; k < n - 1; k++) {
                        preMin += a[i + k];
                        sufMax += a[j - k];
                    }
                    preMin += a[j];
                    sufMax += a[i];
                    if (sufMax < target) {
                        i++;
                    } else if (preMin > target) {
                        j--;
                    } else {
                        while (i != begin && j > i + n - 2 && a[i] == a[i - 1]) {
                            i++;
                        }
                        while (j != end && j > i + n - 2 && a[j] == a[j + 1]) {
                            j--;
                        }
                        path.add(a[i]);
                        dfs(a, i + 1, j, n - 1, target - a[i]);
                        path.remove(path.size() - 1);
                        i++;
                    }
                }
            }
        };
    }

}

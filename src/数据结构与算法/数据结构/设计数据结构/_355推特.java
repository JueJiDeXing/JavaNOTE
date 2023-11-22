package 数据结构与算法.数据结构.设计数据结构;

import lombok.Data;

import java.util.*;

public class _355推特 {
    /*
    设计一个简化版的推特(Twitter)，
    可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近 10 条推文。

    实现 Twitter 类：

    Twitter()
         初始化简易版推特对象
    void postTweet(int userId, int tweetId)
         根据给定的 tweetId 和 userId 创建一条新推文。
         每次调用此函数都会使用一个不同的 tweetId 。
    List<Integer> getNewsFeed(int userId)
         检索当前用户新闻推送中最近 10 条推文的 ID 。
         新闻推送中的每一项都必须是由用户关注的人或者是用户自己发布的推文。
         推文必须 按照时间顺序由最近到最远排序 。
    void follow(int followerId, int followeeId)
         ID 为 followerId 的用户开始关注 ID 为 followeeId 的用户。
    void unfollow(int followerId, int followeeId)
         ID 为 followerId 的用户不再关注 ID 为 followeeId 的用户。
     */
}

class Twitter {

    @Data
    static class Tweet {//文章
        int id;
        int time;
        Tweet next;

        public Tweet(int id, int time, Tweet next) {
            this.id = id;
            this.time = time;
            this.next = next;
        }

        public Tweet() {
        }
    }

    static class User {//用户
        int id;

        public User(int id) {
            this.id = id;
        }

        Set<Integer> followees = new HashSet<>();
        Tweet head = new Tweet();
    }

    Map<Integer, User> userMap = new HashMap<>();
    private static int time;

    public Twitter() {

    }

    public void postTweet(int userId, int tweetId) {
        User user = userMap.computeIfAbsent(userId, User::new);
        user.head.next = new Tweet(tweetId, time++, user.head.next);
    }

    public List<Integer> getNewsFeed(int userId) {
        User user = userMap.get(userId);
        if (user == null) return List.of();
        PriorityQueue<Tweet> queue = new PriorityQueue<>(
                Comparator.comparingInt(Tweet::getTime).reversed()
        );
        if (user.head.next != null) {
            queue.offer(user.head.next);
        }
        for (Integer id : user.followees) {
            User followee = userMap.get(id);
            if (followee.head.next != null) {
                queue.offer(followee.head.next);
            }
        }
        //合并k个有序链表
        List<Integer> res = new ArrayList<>();
        int count = 0;
        while (!queue.isEmpty() && count < 10) {
            Tweet max = queue.poll();
            res.add(max.id);
            if (max.next != null) {
                queue.offer(max.next);
            }
            count++;
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        User user = userMap.get(followerId);
        user.followees.add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        User user = userMap.get(followerId);
        user.followees.remove(followeeId);
    }
}

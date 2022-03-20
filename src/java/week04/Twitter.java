package week04;


import java.util.*;

/**
 * 第四周作业二
 * 355. 设计推特
 * 模仿Twitter
 *
 * 解题思路
 *  最核心的功能难点应该是 getNewsFeed，因为返回的结果必须在时间上有序，但问题是用户的关注是动态变化的
 *  如果我们把每个用户各自的推文存储在链表里，每个链表节点存储文章 id 和一个时间戳 time（记录发帖时间以便比较），
 *  而且这个链表是按 time 有序的，那么如果某个用户关注了 k 个用户，
 *  我们就可以用leetcode23题合并 k 个有序链表的算法合并出有序的推文列表，正确地 getNewsFeed 了！
 *
 *  还需要一个 User 类，储存 user 信息，还需要一个 Tweet 类，储存推文信息，并且要作为链表的节点
 *  Tweet类设计  每个 Tweet 实例需要记录自己的 tweetId 和发表时间 time，而且作为链表节点，要有一个指向下一个节点的 next 指针。
 *  User 类的实现 一个用户需要存储的信息有 userId，关注列表，以及该用户发过的推文列表。
 *  其中关注列表应该用集合（Hash Set）这种数据结构来存，因为不能重复，而且需要快速查找
 *  推文列表应该由链表这种数据结构储存，以便于进行有序合并的操作
 */
public class Twitter {
    // 我们需要一个映射将 userId 和 User 对象对应起来
    private HashMap<Integer, User> userMap = new HashMap<>();
    private static int timestamp = 0;
    private static class Tweet {
        /**
         * 帖子id
         */
        private int id;
        /**
         * 发帖时间
         */
        private int time;
        /**
         * 指向下一个帖子
         */
        private Tweet next;

        // 需要传入推文内容（id）和发文时间
        public Tweet(int id, int time) {
            this.id = id;
            this.time = time;
            this.next = null;
        }
    }
    private static class User {
        /**
         * 用户id
         */
        private int id;
        /**
         * 关注列表
         */
        public Set<Integer> followed;
        /**
         * 用户发表的推文链表头结点
         */
        public Tweet head;

        public User(int userId) {
            followed = new HashSet<>();
            this.id = userId;
            this.head = null;
            // 关注一下自己
            follow(id);
        }

        public void follow(int userId) {
            followed.add(userId);
        }

        public void unfollow(int userId) {
            // 不可以取关自己
            if (userId != this.id)
                followed.remove(userId);
        }

        public void post(int tweetId) {
            Tweet twt = new Tweet(tweetId, timestamp);
            timestamp++;
            // 将新建的推文插入链表头
            // 越靠前的推文 time 值越大
            twt.next = head;
            head = twt;
        }
    }

    /** user 发表一条 tweet 动态 */
    public void postTweet(int userId, int tweetId) {
        // 若 userId 不存在，则新建
        if (!userMap.containsKey(userId))
            userMap.put(userId, new User(userId));
        User u = userMap.get(userId);
        u.post(tweetId);
    }

    /** 返回该 user 关注的人（包括他自己）最近的动态 id，
     最多 10 条，而且这些动态必须按从新到旧的时间线顺序排列。*/
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        if (!userMap.containsKey(userId)) return res;
        // 关注列表的用户 Id
        Set<Integer> users = userMap.get(userId).followed;
        // 自动通过 time 属性从大到小排序，容量为 users 的大小 借助优先队列 合并k个有序链表
        PriorityQueue<Tweet> pq = new PriorityQueue<>(users.size(), (a, b)->(b.time - a.time));

        // 先将所有链表头节点插入优先级队列
        for (int id : users) {
            Tweet twt = userMap.get(id).head;
            if (twt == null) continue;
            pq.add(twt);
        }

        while (!pq.isEmpty()) {
            // 最多返回 10 条就够了
            if (res.size() == 10) break;
            // 弹出 time 值最大的（最近发表的）
            Tweet twt = pq.poll();
            res.add(twt.id);
            // 将下一篇 Tweet 插入进行排序
            if (twt.next != null)
                pq.add(twt.next);
        }
        return res;
    }

    /** follower 关注 followee，如果 Id 不存在则新建 */
    public void follow(int followerId, int followeeId) {
        // 若 follower 不存在，则新建
        if(!userMap.containsKey(followerId)){
            User u = new User(followerId);
            userMap.put(followerId, u);
        }
        // 若 followee 不存在，则新建
        if(!userMap.containsKey(followeeId)){
            User u = new User(followeeId);
            userMap.put(followeeId, u);
        }
        userMap.get(followerId).follow(followeeId);
    }

    /** follower 取关 followee，如果 Id 不存在则什么都不做 */
    public void unfollow(int followerId, int followeeId) {
        if (userMap.containsKey(followerId)) {
            User flwer = userMap.get(followerId);
            flwer.unfollow(followeeId);
        }
    }
}

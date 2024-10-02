package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Twitter {

    private static class TweetWithTimePoint {
        int tweetId;
        int timePoint;

        TweetWithTimePoint(int tweetId, int timePoint){
            this.tweetId = tweetId;
            this.timePoint = timePoint;
        }

        int getTweetId(){
            return tweetId;
        }

        int getTimePoint(){
            return timePoint;
        }
    }

    private static int EPOCH = 1;
    private final Map<Integer, Set<TweetWithTimePoint>> tweetsOfUsers;
    private final Map<Integer, Set<Integer>> follows;

    public Twitter() {
        tweetsOfUsers = new HashMap<>();
        follows = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        TweetWithTimePoint tweet = new TweetWithTimePoint(tweetId, EPOCH++);
        if(tweetsOfUsers.containsKey(userId)){
            tweetsOfUsers.get(userId).add(tweet);
        } else {
            tweetsOfUsers.put(userId, new HashSet<>(Collections.singleton(tweet)));
        }
    }

    public List<Integer> getNewsFeed(int userId) {

        List<TweetWithTimePoint> newsFeed = new ArrayList<>();

        // Store tweets of user, if any
        Set<TweetWithTimePoint> userTweets = tweetsOfUsers.get(userId);
        if(userTweets != null){
            newsFeed.addAll(userTweets);
        }

        // Get all tweets of any users the user follows
        if(follows.containsKey(userId)){
            List<Integer> followees = new ArrayList<>(follows.get(userId));
            for(Integer followee: followees){
                Set<TweetWithTimePoint> tweetsOfFollowee = tweetsOfUsers.get(followee);
                if(tweetsOfFollowee != null){
                    newsFeed.addAll(tweetsOfUsers.get(followee));
                }
            }
        }

        // Sort news feed by timepoint DESC
        newsFeed.sort((tweet1, tweet2) -> tweet2.getTimePoint() - tweet1.getTimePoint());

        // Return maximum 10 tweet IDs of result.
        return newsFeed.stream().limit(10).map(TweetWithTimePoint::getTweetId).collect(Collectors.toList());
    }

    public void follow(int followerId, int followeeId) {
        if(follows.containsKey(followerId)){
            follows.get(followerId).add(followeeId);
        } else {
            follows.put(followerId, new HashSet<>(Collections.singleton(followeeId)));
        }
    }

    public void unfollow(int followerId, int followeeId) {
        if(follows.containsKey(followerId)){
            Set<Integer> followees = follows.get(followerId);
            followees.remove(followeeId);
            follows.put(followerId, followees);
        }
        // Nothing to do if following relationship is not there.
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        System.out.println(twitter.getNewsFeed(1));
        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        System.out.println(twitter.getNewsFeed(1));
        twitter.unfollow(1, 2);
        System.out.println(twitter.getNewsFeed(1));
    }
}
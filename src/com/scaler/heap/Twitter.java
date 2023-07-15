package com.scaler.heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 
 * @author u1069438
 * 
 * 
 	Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, 
 	and is able to see the 10 most recent tweets in the user's news feed.

	Implement the Twitter class:
	
		- Twitter() Initializes your twitter object.
		
		- void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. 
		Each call to this function will be made with a unique tweetId.
		
		- List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. 
		Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
		
		-void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
		
		-void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.

 */

public class Twitter {
	
	int feedMax;
	static int timeStamp;
	Map<Integer, Set<Integer>> followees;
	Map<Integer, List<Tweet>> tweets;
	
	public class Tweet{
		int tweetId;
		int timestamp;
		public Tweet(int tweetId, int timestamp) {
			this.tweetId = tweetId;
			this.timestamp = timestamp;
		}
	}
	
	public Twitter() {
		timeStamp = 0;
		feedMax = 10;
		followees = new HashMap<>();
		tweets = new HashMap<>();
	}
	
	public void postTweet(int userId, int tweetId) {
		if(!tweets.containsKey(userId)) {
			tweets.put(userId, new LinkedList<Tweet>());
		}
		tweets.get(userId).add(0, new Tweet(tweetId, timeStamp++));
	}
	
	public void follow(int followerId, int followeeId) {
		if(!followees.containsKey(followerId)) followees.put(followerId, new HashSet<Integer>());
		followees.get(followerId).add(followeeId);
	}
	
	public void unfollow(int followerId, int followeeId) {
		if(!followees.containsKey(followerId) || followerId == followeeId) return;
		followees.get(followerId).remove(followeeId);
	}
	
	public List<Integer> getNewsFeed(int userId){
		PriorityQueue<Tweet> pq = new PriorityQueue<>(new Comparator<Tweet>() {
			public int compare(Tweet t1, Tweet t2) {
				return t1.timestamp - t2.timestamp;
			}
		});
		Set<Integer> myFollowees = followees.get(userId);
		if(myFollowees != null) {
			for(int followeeId : myFollowees) {
				List<Tweet> followeeTweets = tweets.get(followeeId);
				if (followeeTweets == null) continue;
				for(Tweet tweet : followeeTweets) {
					if(pq.size() < feedMax) {
						pq.add(tweet);
					}else if(tweet.timestamp <= pq.peek().timestamp) {
						break;
					}else {
						pq.add(tweet);
						pq.poll();
					}
				}
			}
		}
		List<Integer> tweetList = new LinkedList<>();
		while(!pq.isEmpty()) {
			tweetList.add(pq.poll().tweetId);
		}
		return tweetList;
	}

}

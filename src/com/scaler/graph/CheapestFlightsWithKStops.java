package com.scaler.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
There are n cities connected by some number of flights. You are given an array
flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst
with at most k stops. If there is no such route, return -1.
 */
public class CheapestFlightsWithKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();

        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];

            graph.putIfAbsent(from, new HashMap<>());
            graph.get(from).put(to, price);
        }

        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[src] = 0;

        for (int i = 0; i <= k; i++) {
            int[] newMinCost = Arrays.copyOf(minCost, n);

            for (int from : graph.keySet()) {
                Map<Integer, Integer> neighbors = graph.get(from);
                for (int to : neighbors.keySet()) {
                    if (minCost[from] != Integer.MAX_VALUE) {
                        newMinCost[to] = Math.min(newMinCost[to], minCost[from] + neighbors.get(to));
                    }
                }
            }

            minCost = newMinCost;
        }

        return (minCost[dst] == Integer.MAX_VALUE) ? -1 : minCost[dst];
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] flights = {
                {0, 1, 10},
                {1, 2, 5},
                {0, 3, 15},
                {3, 1, 12},
                {2, 4, 10},
                {4, 0, 10},
                {2, 3, 7},
                {3, 4, 5}
        };
        int src = 0;
        int dst = 4;
        int k = 2;

        int result = new CheapestFlightsWithKStops().findCheapestPrice(n, flights, src, dst, k);
        System.out.println("Minimum cost from " + src + " to " + dst + " with at most " + k + " stops: " + result);
    }
}

/*
here is an undirected graph with n nodes, numbered from 0 to n - 1.

You are given a 0-indexed integer array scores of length n where scores[i] denotes the score of node i. You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.

A node sequence is valid if it meets the following conditions:

    There is an edge connecting every pair of adjacent nodes in the sequence.
    No node appears more than once in the sequence.

The score of a node sequence is defined as the sum of the scores of the nodes in the sequence.

Return the maximum score of a valid node sequence with a length of 4. If no such sequence exists, return -1.

 

Example 1:

Input: scores = [5,2,9,8,4], edges = [[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]
Output: 24
Explanation: The figure above shows the graph and the chosen node sequence [0,1,2,3].
The score of the node sequence is 5 + 2 + 9 + 8 = 24.
It can be shown that no other node sequence has a score of more than 24.
Note that the sequences [3,1,2,0] and [1,0,2,3] are also valid and have a score of 24.
The sequence [0,3,2,4] is not valid since no edge connects nodes 0 and 3.

Example 2:

Input: scores = [9,20,6,4,11,12], edges = [[0,3],[5,3],[2,4],[1,3]]
Output: -1
Explanation: The figure above shows the graph.
There are no valid node sequences of length 4, so we return -1.

*/


class Solution {
    public int maximumScore(int[] A, int[][] edges) {
        int n = A.length;
        PriorityQueue<Integer>[] q = new PriorityQueue[n];
        for (int i = 0; i < n; i++)
            q[i] = new PriorityQueue<>((a, b) -> A[a] - A[b]);
        for (int[] e : edges) {
            q[e[0]].offer(e[1]);
            q[e[1]].offer(e[0]);
            if (q[e[0]].size() > 3) q[e[0]].poll();
            if (q[e[1]].size() > 3) q[e[1]].poll();
        }
        int res = -1;
        for (int[] edge : edges)
            for (int i : q[edge[0]])
                for (int j : q[edge[1]])
                    if (i != j && i != edge[1] && j != edge[0])
                        res = Math.max(res, A[i] + A[j] + A[edge[0]] + A[edge[1]]);  
        return res;
    }
}

/*

Approach:

1. form a adjListGraph using priorityQueue based on the score (minHeap - store only top 3) (cause removing from minheap returns min value)
2. go over each edges 
    for each edge pick top 3 elements on the either side and calculate the total score.

*/
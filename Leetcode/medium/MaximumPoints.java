package Leetcode.medium;

/*
 * You are given an m x n integer matrix points (0-indexed). Starting with 0 points, you want to maximize the number of points you can get from the matrix.

To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c) will add points[r][c] to your score.

However, you will lose points if you pick a cell too far from the cell that you picked in the previous row. For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.

Return the maximum number of points you can achieve.

abs(x) is defined as:

    x for x >= 0.
    -x for x < 0.

 

Example 1:

Input: points = [[1,2,3],[1,5,1],[3,1,1]]
Output: 9
Explanation:
The blue cells denote the optimal cells to pick, which have coordinates (0, 2), (1, 1), and (2, 0).
You add 3 + 5 + 3 = 11 to your score.
However, you must subtract abs(2 - 1) + abs(1 - 0) = 2 from your score.
Your final score is 11 - 2 = 9.

Example 2:

Input: points = [[1,5],[2,3],[4,2]]
Output: 11
Explanation:
The blue cells denote the optimal cells to pick, which have coordinates (0, 1), (1, 1), and (2, 0).
You add 5 + 3 + 4 = 12 to your score.
However, you must subtract abs(1 - 1) + abs(1 - 0) = 1 from your score.
Your final score is 12 - 1 = 11.

 

Constraints:

    m == points.length
    n == points[r].length
    1 <= m, n <= 105
    1 <= m * n <= 105
    0 <= points[r][c] <= 105


 */


//Bottom Up DP solution 
//TC : M*N*N
//SC : M*N


class Solution {
    
    Long[][] dp;
    public long maxPoints(int[][] points) {
        
        int R = points.length;
        int C = points[0].length;
        
        dp = new Long[R][C];
        
        for(int i=0; i<C; i++) {
            dp[R-1][i] = Long.valueOf(new Long(points[R-1][i]));
        }
        
        for(int i = R-2; i >= 0; i--){
            for(int j = 0; j < C; j++) {
                Long max = Long.MIN_VALUE;
                for(int k=0; k< C; k++) {
                    max = Math.max(max, ((points[i][j]+dp[i+1][k])-Math.abs(j-k)));
                }
                dp[i][j] = max;
            }
        }
        Long maxPoints = Long.MIN_VALUE;
        for(int i=0; i<C;i++) {
            maxPoints = Math.max(maxPoints, dp[0][i]);
        }
        return maxPoints;
    }
}



//Top Down DP Solution

class Solution {
    Long[] prev;
    
    public long maxPoints(int[][] points) {
        
        int R = points.length;
        int C = points[0].length;
        
        prev = new Long[C];
        
        //array to store the maximum seen so far at each index
        for(int i=0; i<C; i++) {
            prev[i] = new Long(points[0][i]);
        }
        
        for(int i=1; i<R; i++) {
            
            Long[] curr = new Long[C];
            Long[] leftMax = new Long[C];
            Long[] rightMax = new Long[C];
            
            //Comparing topleft and top and updating maximum at the index
            leftMax[0] = prev[0];
            for(int j = 1; j < C; j++) {
                leftMax[j] = Math.max(prev[j], leftMax[j-1]-1);
            }
            
            
            //comparing top right and top and updating maximum at the index
            rightMax[C-1] = prev[C-1];
            for(int j=C-2; j>=0;j--) {
                rightMax[j] = Math.max(prev[j], rightMax[j+1]-1);
            }
            
            //Checking which sum contributes more to the maximum at that index
            for(int j=0;j<C;j++) {
                curr[j] = points[i][j]+ Math.max(leftMax[j], rightMax[j]);
            }

            //updating curr as prev
            prev = curr;
        }
        
        Long max = Long.MIN_VALUE;
        for(int i=0; i<C; i++) {
            max = Math.max(max, prev[i]);
        }
        
        return max;
    }
}

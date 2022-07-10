/*
You are given an m x n binary matrix grid where each cell is either 0 (empty) or 1 (occupied).

You are then given stamps of size stampHeight x stampWidth. We want to fit the stamps such that they follow the given restrictions and requirements:

    Cover all the empty cells.
    Do not cover any of the occupied cells.
    We can put as many stamps as we want.
    Stamps can overlap with each other.
    Stamps are not allowed to be rotated.
    Stamps must stay completely inside the grid.

Return true if it is possible to fit the stamps while following the given restrictions and requirements. Otherwise, return false.

 

Example 1:

Input: grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
Output: true
Explanation: We have two overlapping stamps (labeled 1 and 2 in the image) that are able to cover all the empty cells.

Example 2:

Input: grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2 
Output: false 
Explanation: There is no way to fit the stamps onto all the empty cells without the stamps going outside the grid.


*/

class Solution {

    public boolean possibleToStamp(int[][] matrix, int stampHeight, int stampWidth) {
        int[][] dp = prefixArray(matrix);
        int [][] stamped = new int [matrix.length][matrix[0].length];

        for (int i = stampHeight - 1; i < matrix.length; i++)
            for (int j = stampWidth - 1; j < matrix[i].length; j++)
               if (sumRegion(dp, i - stampHeight + 1, j - stampWidth + 1, i, j) == 0) stamped[i][j] = 1;
        dp = prefixArray(stamped);
        
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                if (matrix[i][j] == 0 &&
                    sumRegion(dp, i, j,
                              Math.min(i + stampHeight - 1, matrix.length - 1),
                              Math.min(j + stampWidth - 1, matrix[0].length - 1)) == 0) return false;
                
        return true;
    }
    
    public int[][] prefixArray(int[][] matrix) {
       int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
        for (int r = 0; r < matrix.length; r++)
            for (int c = 0; c < matrix[0].length; c++) 
                dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] + matrix[r][c] - dp[r][c];
        return dp;
    } 
    
    public int sumRegion(int[][] dp, int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }
}
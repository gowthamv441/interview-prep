package Leetcode.medium;
/*
 * 

 Remove All Ones With Row and Column Flips

You are given an m x n binary matrix grid.

In one operation, you can choose any row or column and flip each value in that row or column (i.e., changing all 0's to 1's, and all 1's to 0's).

Return true if it is possible to remove all 1's from grid using any number of operations or false otherwise.

 

Example 1:

Input: grid = [[0,1,0],[1,0,1],[0,1,0]]
Output: true
Explanation: One possible way to remove all 1's from grid is to:
- Flip the middle row
- Flip the middle column

Example 2:

Input: grid = [[1,1,0],[0,0,0],[0,0,0]]
Output: false
Explanation: It is impossible to remove all 1's from grid.

Example 3:

Input: grid = [[0]]
Output: true
Explanation: There are no 1's in grid.

 

Constraints:

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 300
    grid[i][j] is either 0 or 1.


 */
class Solution {
    
    private void invert(int[][] grid, int row) {
        for(int i = 0; i < grid[row].length; i++) {
            grid[row][i] = (grid[row][i] == 0) ? 1 : 0;
        }
    }
    
    private boolean isSame(int[] src, int[] target) {
        if(src.length != target.length) return false;
        
        for(int i=0;i<src.length; i++) {
            if(src[i] != target[i])
                return false;
        }
        return true;
    }
    
    public boolean removeOnes(int[][] grid) {
        for(int i=1; i< grid.length; i++) {
            if(isSame(grid[i-1], grid[i])) {
                continue;
            } 
            invert(grid, i);
            if (isSame(grid[i-1], grid[i])) {
             continue;   
            }
            return false;
        }
        return true;
    }
}

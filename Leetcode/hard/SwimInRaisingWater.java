/*
 * 
 
You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).

The rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.

Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).

 

Example 1:

Input: grid = [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.

Example 2:

Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
Output: 16
Explanation: The final route is shown.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.

 

Constraints:

    n == grid.length
    n == grid[i].length
    1 <= n <= 50
    0 <= grid[i][j] < n2
    Each value grid[i][j] is unique.


 */


 /*
  * 

Binary Search and DFS [Accepted]

Intuition and Algorithm

Whether the swim is possible is a monotone function with respect to time, so we can binary search this function for the correct time: the smallest T for which the swim is possible.

Say we guess that the correct time is T. To check whether it is possible, we perform a simple depth-first search where we can only walk in squares that are at most T.

*/

class Solution {
    
    private int maximumInGrid(int[][] grid) {
        int max = grid[0][0];
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                max = Math.max(max, grid[i][j]);
            }
        }
        return max;
    }
    
    public int swimInWater(int[][] grid) {
        
        int N = grid.length;
        
        int low = grid[0][0];
        int high = maximumInGrid(grid);
        
        while(low<high) {
            int mid = low+ (high-low)/2;
            if(isPossible(grid, mid)) {
                high = mid;
            } else {
                low = mid+1;
            }
        }
        return low;
    }
    
    private boolean isPossible(int[][] grid, int T) {
        
        Stack<Pair<Integer, Integer>> stack = new Stack<Pair<Integer, Integer>>();
        Set<Pair<Integer, Integer>> visited = new HashSet<Pair<Integer, Integer>>();
        
        int R = grid.length;
        int C = grid[0].length;
        
        stack.push(new Pair(0,0));
        visited.add(new Pair(0,0));
        
        int dir[][] = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        
        while(!stack.isEmpty()) {
            Pair<Integer, Integer> cell = stack.pop();
            int curr_r = cell.getKey();
            int curr_c = cell.getValue();
            
            if(curr_r == R-1 && curr_c == C-1) {
                return true;
            }
                
            for(int i=0; i<4; i++) {
                int new_r = curr_r+dir[i][0];
                int new_c = curr_c+dir[i][1];
                if(new_r >= 0 && new_r < R && new_c >= 0 && new_c < C && !visited.contains(new Pair(new_r, new_c)) && grid[new_r][new_c] <= T) {
                    stack.push(new Pair(new_r, new_c));
                    visited.add(new Pair(new_r, new_c));
                }
            }
        }
        return false;
    }
}



Time Complexity: O(N2log⁡N)O(N^2 \log N)O(N2logN). Our depth-first search during a call to possible is O(N2)O(N^2)O(N2), and we make up to O(log⁡N)O(\log N)O(logN) of them.

Space Complexity: O(N2)O(N^2)O(N2), the maximum size of the stack.

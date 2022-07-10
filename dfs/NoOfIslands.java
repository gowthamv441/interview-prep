package dfs;

/*
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3

 

Constraints:

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 300
    grid[i][j] is '0' or '1'.


 */

class Solution {
    boolean[][] visited;
    
    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    private boolean isInMatrix(int i, int j, int R, int C) {
        return i>=0 && i<R && j>=0 && j<C;
    }
    
    private void dfs(char[][] grid, int i, int j, int R, int C) {
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        
        stack.push(new Pair(i,j));
        visited[i][j] = true;
        
        while(!stack.isEmpty()) {
            Pair<Integer, Integer> index = stack.pop();
            int curr_i = index.getKey();
            int curr_j = index.getValue();
            grid[curr_i][curr_j] = '0';
            for(int k=0; k<4; k++) {
                int x = curr_i+dir[k][0];
                int y = curr_j+dir[k][1];
                if(isInMatrix(x,y,R,C) && grid[x][y] == '1' && !visited[x][y]) {
                    stack.push(new Pair(x,y));
                }    
            }
        }
    }
    
    public int numIslands(char[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        
        visited = new boolean[R][C];
        
        int count = 0;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j, R, C);
                }
            }
        }
        return count++;
        
    }
}

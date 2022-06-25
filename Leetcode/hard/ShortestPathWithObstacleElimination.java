package Leetcode.hard;

/*
 * 

 You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.

 

Example 1:

Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
Output: 6
Explanation: 
The shortest path without eliminating any obstacle is 10.
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).

Example 2:

Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
Output: -1
Explanation: We need to eliminate at least two obstacles to find such a walk.

 

Constraints:

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 40
    1 <= k <= m * n
    grid[i][j] is either 0 or 1.
    grid[0][0] == grid[m - 1][n - 1] == 0


 */


class StepState {
    int steps;
    int row;
    int col;
    int remainingElimination;
    
    public StepState(int steps, int row, int col, int remainingElimination) {
        this.steps = steps;
        this.row = row;
        this.col = col;
        this.remainingElimination = remainingElimination;
    }
    
    @Override
    public int hashCode() {
        // needed when we put objects into any container class
        return (this.row + 1) * (this.col + 1) * this.remainingElimination;
    }

    @Override
    public boolean equals(Object other) {
        /**
         * only (row, col, k) matters as the state info
         */
        if (!(other instanceof StepState)) {
            return false;
        }
        StepState newState = (StepState) other;
        return (this.row == newState.row) && (this.col == newState.col) && (this.remainingElimination == newState.remainingElimination);
    }

    @Override
    public String toString() {
        return String.format("%d %d %d", this.row, this.col, this.remainingElimination);
    }
}

class Solution {
    
    public int shortestPath(int[][] grid, int k) {
        int R = grid.length;
        int C = grid[0].length;
        
        int[] target = {R-1, C-1};
        
        int manhattanDistance = R + C - 2;
        
        if(k>=manhattanDistance)
            return manhattanDistance;
        
        Queue<StepState> q = new LinkedList<StepState>();
        Set<StepState> visited = new HashSet<StepState>();
        
        StepState start = new StepState(0,0,0,k);
        q.add(start);
        visited.add(start);
        
        while(!q.isEmpty()) {
            StepState curr = q.poll();
            
            if(target[0] == curr.row && target[1] == curr.col) {
                return curr.steps;
            }
            
            int[] nextSteps= {
                curr.row, curr.col+1,
                curr.row+1, curr.col,
                curr.row, curr.col-1,
                curr.row-1, curr.col
            };
            
            for(int i=0; i<nextSteps.length;i+=2) {
                
                int nextRow = nextSteps[i];
                int nextCol = nextSteps[i+1];
                
                if(nextRow < 0 || nextRow > R-1 || nextCol < 0 || nextCol > C-1)
                    continue;
                
                int nextElimination = curr.remainingElimination - (grid[nextRow][nextCol] == 1 ? 1 : 0);
                
                StepState nextState = new StepState(curr.steps+1, nextRow, nextCol, nextElimination);
                
                if(nextElimination >= 0 && !(visited.contains(nextState))) {
                    q.add(nextState);
                    visited.add(nextState);
                }
                
            }
            
        }
        return -1;
        
    }
}

/*
 Complexity Analysis

Let N be the number of cells in the grid, and K be the quota to eliminate obstacles.

    Time Complexity: O(N⋅K)

        We conduct a BFS traversal in the grid. In the worst case, we will visit each cell in the grid. And for each cell, at most, it will be visited K times, with different quotas of obstacle elimination.

        Thus, the overall time complexity of the algorithm is O(N⋅K)

    Space Complexity: O(N⋅K)

        We used a queue to maintain the order of visited states. In the worst case, the queue will contain the majority of the possible states that we need to visit, which in total is N⋅K as we discussed in the time complexity analysis. Thus, the space complexity of the queue is O(N⋅K)

        Other than the queue, we also used a set variable (named seen) to keep track of all the visited states along the way. Same as the queue, the space complexity of this set is also O(N⋅K)

        To sum up, the overall space complexity of the algorithm is O(N⋅K)

 */
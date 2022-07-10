package Leetcode.hard;

/*
 * 
 * 

 Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbors of it if they exist (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighbors if they share one edge.

Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.

A binary matrix is a matrix with all cells equal to 0 or 1 only.

A zero matrix is a matrix with all cells equal to 0.

 

Example 1:

Input: mat = [[0,0],[0,1]]
Output: 3
Explanation: One possible solution is to flip (1, 0) then (0, 1) and finally (1, 1) as shown.

Example 2:

Input: mat = [[0]]
Output: 0
Explanation: Given matrix is a zero matrix. We do not need to change it.

Example 3:

Input: mat = [[1,0,0],[1,0,0]]
Output: -1
Explanation: Given matrix cannot be a zero matrix.

 

Constraints:

    m == mat.length
    n == mat[i].length
    1 <= m, n <= 3
    mat[i][j] is either 0 or 1.


 */

class Solution {
    public int minFlips(int[][] mat) {
        
        Set<String> visitedSet = new HashSet<>();
        Queue<int[][]> matrixQueue = new LinkedList<>();
        
        // HashMap<String, Integer> dpSet = new HashMap<>();
        int steps = 0;
        
        if(isMatrixZero(mat)){
            return steps;
        }
        
        /* Add the current representation to the queue. */
        matrixQueue.offer(mat);
        String serial = Arrays.deepToString(mat);
        visitedSet.add(serial);
        
         
        while(!matrixQueue.isEmpty()){
            int size = matrixQueue.size();
            steps++;
            while(size > 0){
                //doing it in level order fashion. where level mention the flipped version of matrix
                int[][] currentMatrix = matrixQueue.poll();
                System.out.println("**********");
                /* Check all 4 flipped versions. */
                // for each matrix in queue get the flipped version and add it to queue if not visited and return step if picked matrix is zero. 
                 for(int[][] flippedMatrix : getAllFlippedVersions(currentMatrix)){
                     printMatrix(flippedMatrix);
                     System.out.println("----");
                     serial = Arrays.deepToString(flippedMatrix);
                     if(visitedSet.contains(serial))
                         continue;
                    if(isMatrixZero(flippedMatrix)){
                        return steps;
                    }
                    matrixQueue.add(flippedMatrix);
                    visitedSet.add(serial);
                }
                size--;
            }
        }     
        return -1;
    }
    
    void printMatrix(int[][] matrix) {
        
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    
    //for each (i,j) do the flip and add the flipped version to list
    List<int[][]> getAllFlippedVersions(int[][] matrix){
        List<int[][]> result = new ArrayList<>();
        for(int row = 0; row < matrix.length; row++){
            for(int col = 0; col < matrix[0].length; col++){
                int[][] flipped = flipMatrix(matrix, row, col);
                result.add(flipped);
            }
        }
        return result;
    }
    
    //flip that particular matrix[i][j] and its neighbours
    int[][] flipMatrix(int[][] matrix, int rowF, int colF){
        int[][] flippedMatrix = new int[matrix.length][matrix[0].length];
        for(int row = 0; row < matrix.length; row++){
            for(int col = 0; col < matrix[0].length; col++){
                if(row == rowF && col == colF){
                    flippedMatrix[row][col] = 1 - matrix[row][col];
                } else if(row == rowF && col == colF -1){
                    flippedMatrix[row][col] = 1 - matrix[row][col];
                } else if (row == rowF && col == colF +1){
                    flippedMatrix[row][col] = 1 - matrix[row][col];
                } else if(row == rowF -1 && col == colF){
                    flippedMatrix[row][col] = 1 - matrix[row][col];
                } else if (row == rowF +1 && col == colF){
                    flippedMatrix[row][col] = 1 - matrix[row][col];
                } else {
                    flippedMatrix[row][col] = matrix[row][col];
                }
            }            
        }
        return flippedMatrix;
    }
    
    boolean isMatrixZero(int[][] mat){
        int sum = 0;
        for(int[] arr : mat){
            for(int val : arr){
                if(val != 0)
                    return false;
            }
        }
        return true;
    }
}

//TC: 
//2^(N*N) possible states 
//bfs will take O(N*2) 
// So total will be O(N*2 (2^(N*N)))
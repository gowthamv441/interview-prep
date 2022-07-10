/*
Given an m x n matrix, return a new matrix answer where answer[row][col] is the rank of matrix[row][col].

The rank is an integer that represents how large an element is compared to other elements. It is calculated using the following rules:

    The rank is an integer starting from 1.
    If two elements p and q are in the same row or column, then:
        If p < q then rank(p) < rank(q)
        If p == q then rank(p) == rank(q)
        If p > q then rank(p) > rank(q)
    The rank should be as small as possible.

The test cases are generated so that answer is unique under the given rules.

 

Example 1:

Input: matrix = [[1,2],[3,4]]
Output: [[1,2],[2,3]]
Explanation:
The rank of matrix[0][0] is 1 because it is the smallest integer in its row and column.
The rank of matrix[0][1] is 2 because matrix[0][1] > matrix[0][0] and matrix[0][0] is rank 1.
The rank of matrix[1][0] is 2 because matrix[1][0] > matrix[0][0] and matrix[0][0] is rank 1.
The rank of matrix[1][1] is 3 because matrix[1][1] > matrix[0][1], matrix[1][1] > matrix[1][0], and both matrix[0][1] and matrix[1][0] are rank 2.

Example 2:

Input: matrix = [[7,7],[7,7]]
Output: [[1,1],[1,1]]

Example 3:

Input: matrix = [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
Output: [[4,2,3],[1,3,4],[5,1,6],[1,3,4]]

 

Constraints:

    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 500
    -109 <= matrix[row][col] <= 109

*/


class Solution {
    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // link row to col, and link col to row
        Map<Integer, Map<Integer, List<Integer>>> graphs = new HashMap<>();
        // graphs.get(v): the connection graph of value v
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int v = matrix[i][j];
                // if not initialized, initial it
                if (!graphs.containsKey(v)) {
                    graphs.put(v, new HashMap<Integer, List<Integer>>());
                }
                Map<Integer, List<Integer>> graph = graphs.get(v);
                if (!graph.containsKey(i)) {
                    graph.put(i, new ArrayList<Integer>());
                }
                if (!graph.containsKey(~j)) {
                    graph.put(~j, new ArrayList<Integer>());
                }
                // link i to j, and link j to i
                graph.get(i).add(~j);
                graph.get(~j).add(i);
            }
        }

        // put points into `value2index` dict, grouped by connection
        // use TreeMap to help us sort the key automatically
        Map<Integer, List<List<int[]>>> value2index = new TreeMap<>();
        int[][] seen = new int[m][n]; // mark whether put into `value2index` or not
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (seen[i][j] == 1) {
                    continue;
                }
                seen[i][j] = 1;
                int v = matrix[i][j];
                Map<Integer, List<Integer>> graph = graphs.get(v);
                // store visited row and col
                Set<Integer> rowcols = new HashSet<Integer>();
                rowcols.add(i);
                rowcols.add(~j);
                // start bfs
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                q.offer(~j);
                while (!q.isEmpty()) {
                    int node = q.poll();
                    for (int rowcol : graph.get(node)) {
                        if (!rowcols.contains(rowcol)) {
                            rowcols.add(rowcol);
                            q.offer(rowcol);
                        }
                    }
                }
                // transform rowcols into points
                List<int[]> points = new ArrayList<>();
                for (int rowcol : rowcols) {
                    for (int k : graph.get(rowcol)) {
                        if (k >= 0) {
                            points.add(new int[] { k, ~rowcol });
                            seen[k][~rowcol] = 1;
                        } else {
                            points.add(new int[] { rowcol, ~k });
                            seen[rowcol][~k] = 1;
                        }
                    }
                }
                if (!value2index.containsKey(v)) {
                    value2index.put(v, new ArrayList<List<int[]>>());
                }
                value2index.get(v).add(points);
            }
        }
        int[][] answer = new int[m][n]; // the required rank matrix
        int[] rowMax = new int[m]; // rowMax[i]: the max rank in i row
        int[] colMax = new int[n]; // colMax[j]: the max rank in j col
        for (int v : value2index.keySet()) {
            // update by connected points with same value
            for (List<int[]> points : value2index.get(v)) {
                int rank = 1;
                for (int[] point : points) {
                    rank = Math.max(rank, Math.max(rowMax[point[0]], colMax[point[1]]) + 1);
                }
                for (int[] point : points) {
                    answer[point[0]][point[1]] = rank;
                    // update rowMax and colMax
                    rowMax[point[0]] = Math.max(rowMax[point[0]], rank);
                    colMax[point[1]] = Math.max(colMax[point[1]], rank);
                }
            }
        }
        return answer;
    }
}


/**
My Approach:

Input :
20  -21  14
-19  4   19
22  -47  29
-19  4   19 

1) Calculate Rank in Row wise

3 1 2
1 2 3
2 1 3
1 2 3

2) Calculate Rank in column wise

2 2 1
1 3 2
3 1 1
1 3 2

3) Now we know what is the Rank in both row and column. Now add it to find the total rank

5 3 3
2 5 5
5 2 6
2 5 5


4) Now create a final matrix with INT_MIN and push all the rank value and corresponding position in sorted map

2 -> (2,1) (1,0)
3 -> (0,1) (0,3)
5 -> (0,0) (1,1) (1,2) (2,0) (3,1) (3,2)
6 -> (2,2)

5) Now iterate over the map keySet and fill up the rank accordingly

maxRankRow = index + max value in row (getting from hash map precomputation)
maxRankColumn = index + max value in Col (getting from hash map precomputation)
if(maxRankCol> maxRankRow) {
    if(mat[i][j]== mat[maxRankCol_i][maxRankCol_j])
        rank i,j = maxRankCol_rank;
    else 
        rank i,j = maxRankCol_rank + 1; 
} else {
    if(mat[i][j]== mat[maxRankRow_i][maxRankRow_j])
        rank i,j = maxRankRow_rank;
    else 
        rank i,j = maxRankRow_rank + 1; 
}

Time Complexity :
Sorting in Col - O(N* MLogM)
Sorting in Row - O(M * NLogN)

Combine - O(M*N)

update rank for all nodes O(M*N) - O(1) for fetching max in row and col using precomputation

Total Time Complexity = O(N* MLogM) +  O(M * NLogN) + O(M*N) + O(M*N)

Consolidated = O(N2 log N)
 */
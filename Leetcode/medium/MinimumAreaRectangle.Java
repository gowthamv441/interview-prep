
/*
 * You are given an array of points in the X-Y plane points where points[i] = [xi, yi].

Return the minimum area of a rectangle formed from these points, with sides parallel to the X and Y axes. If there is not any such rectangle, return 0.

 

Example 1:

Input: points = [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4

Example 2:

Input: points = [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2

 

Constraints:

    1 <= points.length <= 500
    points[i].length == 2
    0 <= xi, yi <= 4 * 104
    All the given points are unique.


 */

class Solution {
    Map<Integer, Set<Integer>> mp;
    public int minAreaRect(int[][] points) {
        mp = new HashMap<>();
        for(int i=0; i<points.length; i++) {
            if(!mp.containsKey(points[i][0])) {
                mp.put(points[i][0],new HashSet<Integer>());
            }
            mp.get(points[i][0]).add(points[i][1]);
        }
        
        int minArea = Integer.MAX_VALUE;
        
        for(int i=0;i<points.length;i++) {
            for(int j = i+1; j< points.length; j++) {
                if(points[i][0] == points[j][0] || points[i][1] == points[j][1])
                    continue;
               
                int currArea = Math.abs(((points[j][0] - points[i][0]) * (points[j][1] - points[i][1])));
                if(currArea < minArea &&
                  mp.get(points[i][0]).contains(points[j][1]) &&
                  mp.get(points[j][0]).contains(points[i][1])) {
                    minArea = currArea;
                }
            }
        }
        
        return minArea == Integer.MAX_VALUE ? 0 : minArea;
        
        
    }
}
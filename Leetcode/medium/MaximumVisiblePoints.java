/*
You are given an array points, an integer angle, and your location, where location = [posx, posy] and points[i] = [xi, yi] both denote integral coordinates on the X-Y plane.

Initially, you are facing directly east from your position. You cannot move from your position, but you can rotate. In other words, posx and posy cannot be changed. Your field of view in degrees is represented by angle, determining how wide you can see from any given view direction. Let d be the amount in degrees that you rotate counterclockwise. Then, your field of view is the inclusive range of angles [d - angle/2, d + angle/2].

Your browser does not support the video tag or this video format.

You can see some set of points if, for each point, the angle formed by the point, your position, and the immediate east direction from your position is in your field of view.

There can be multiple points at one coordinate. There may be points at your location, and you can always see these points regardless of your rotation. Points do not obstruct your vision to other points.

Return the maximum number of points you can see.

 

Example 1:

Input: points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]
Output: 3
Explanation: The shaded region represents your field of view. All points can be made visible in your field of view, including [3,3] even though [2,2] is in front and in the same line of sight.

Example 2:

Input: points = [[2,1],[2,2],[3,4],[1,1]], angle = 90, location = [1,1]
Output: 4
Explanation: All points can be made visible in your field of view, including the one at your location.

Example 3:

Input: points = [[1,0],[2,1]], angle = 13, location = [1,1]
Output: 1
Explanation: You can only see one of the two points, as shown above.

*/

class Solution {
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        // Use ArrayList here in case of TLE
        List<Double> memo = new ArrayList<>(); 
        
        int same = 0;
        // Compute angle between "straight-east" and the point in degree
        for (List<Integer> point : points) {
            int dx = point.get(0) - location.get(0);
            int dy = point.get(1) - location.get(1);
            if (dx == 0 && dy == 0) {
                same++;
                continue;
            }
            double degree = Math.toDegrees(Math.atan2(dy, dx));
            memo.add(degree);
        }
        
        // sort degree to loop in a "sliding window" style
        Collections.sort(memo);
        int n = memo.size();
        // add a "whole loop" for negative degree to cover cases, like end of memo list and begin
        // of memo also could form a valid angle
        for (int i = 0; i < n; i++) {
            if (memo.get(i) < 0) {
                memo.add(memo.get(i) + 360);
            }
        }
        
        int count = 0;
        // sliding window here, end keeps moving
        // move left if current formed angle > limited
        for (int start = 0, end = 0; end < memo.size(); end++) {
            while (memo.get(end) - memo.get(start) > angle) {
                start++;
            }
            // filter the max possible 
            count = Math.max(count, end - start + 1);
        }
        
        // not forget to add points same as "location"
        return count + same;
    }
}
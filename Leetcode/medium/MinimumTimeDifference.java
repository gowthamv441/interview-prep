package Leetcode.medium;

/*
 * Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.

 

Example 1:

Input: timePoints = ["23:59","00:00"]
Output: 1

Example 2:

Input: timePoints = ["00:00","23:59","00:00"]
Output: 0

 

Constraints:

    2 <= timePoints.length <= 2 * 104
    timePoints[i] is in the format "HH:MM".

 */


//TC: O(N)
//SC: O(1)
 
class Solution {
    
    private int convertToMins(String s) {
        return (((s.charAt(0)-'0')*10+(s.charAt(1)-'0'))*60) + (s.charAt(3)-'0')*10 + (s.charAt(4)-'0');
    }
    
    public int findMinDifference(List<String> timePoints) {
        
        boolean mins[] = new boolean[1440];
        
        int minTime = Integer.MAX_VALUE;
        int maxTime = Integer.MIN_VALUE;
        
        for(String time : timePoints) {
            int currTimeMins = convertToMins(time); 
            if(mins[currTimeMins]) return 0;
            
            mins[currTimeMins] = true;
            minTime = Math.min(minTime, currTimeMins);
            maxTime = Math.max(maxTime, currTimeMins);
        }
        
        int prev = maxTime;
        int ans = Integer.MAX_VALUE;
        for(int curr=minTime; curr<=maxTime; curr++) {
            if(mins[curr]) {
                int diff = Math.abs(prev-curr);
                System.out.println(diff);
                //Think of it as a whole circle with 1440 units. So from min a to b 
                // find which segment is minimum in that circle
                ans = Math.min(ans, Math.min(diff, 1440-diff));
                prev = curr;
            }
        }
        return ans;
    }
}


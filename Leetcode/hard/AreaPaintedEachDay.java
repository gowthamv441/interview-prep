package Leetcode.hard;

/*
 * 
 * 

 There is a long and thin painting that can be represented by a number line. You are given a 0-indexed 2D integer array paint of length n, where paint[i] = [starti, endi]. This means that on the ith day you need to paint the area between starti and endi.

Painting the same area multiple times will create an uneven painting so you only want to paint each area of the painting at most once.

Return an integer array worklog of length n, where worklog[i] is the amount of new area that you painted on the ith day.

 

Example 1:

Input: paint = [[1,4],[4,7],[5,8]]
Output: [3,3,1]
Explanation:
On day 0, paint everything between 1 and 4.
The amount of new area painted on day 0 is 4 - 1 = 3.
On day 1, paint everything between 4 and 7.
The amount of new area painted on day 1 is 7 - 4 = 3.
On day 2, paint everything between 7 and 8.
Everything between 5 and 7 was already painted on day 1.
The amount of new area painted on day 2 is 8 - 7 = 1. 

Example 2:

Input: paint = [[1,4],[5,8],[4,7]]
Output: [3,3,1]
Explanation:
On day 0, paint everything between 1 and 4.
The amount of new area painted on day 0 is 4 - 1 = 3.
On day 1, paint everything between 5 and 8.
The amount of new area painted on day 1 is 8 - 5 = 3.
On day 2, paint everything between 4 and 5.
Everything between 5 and 7 was already painted on day 1.
The amount of new area painted on day 2 is 5 - 4 = 1. 

Example 3:

Input: paint = [[1,5],[2,4]]
Output: [4,0]
Explanation:
On day 0, paint everything between 1 and 5.
The amount of new area painted on day 0 is 5 - 1 = 4.
On day 1, paint nothing because everything between 2 and 4 was already painted on day 0.
The amount of new area painted on day 1 is 0.

 

Constraints:

    1 <= paint.length <= 105
    paint[i].length == 2
    0 <= starti < endi <= 5 * 104


 */

class Solution {
    public int[] amountPainted(int[][] paint) {
        
        
        // Key is the start index -> value is the end index
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        
        int[] totalAreaToPaint = new int[paint.length];
        
        
        for(int i = 0; i < paint.length; i++){
            int[] area = paint[i];
            
            int start = area[0];
            int end   = area[1];
            
            int areaToPaint = end-start;
            
            // Check the lower part // Can be just 1
            Map.Entry<Integer, Integer> lowerArea = treeMap.floorEntry(area[0]);
            
            // A lower area exists -> check for overlap
            if(lowerArea != null) {
                // Is the lower area completely covering?
                if(lowerArea.getValue() >= end){
                    /* Area is completely covered. */
                    continue;
                } else if (lowerArea.getValue() <= start){
                    /* Nothing needed. */
                }else {
                    /* Area is partly covered. We must intersect the 2 areas. */
                    treeMap.remove(lowerArea.getKey()); // Remove the old area
                    // Update the area to paint
                    areaToPaint = end - lowerArea.getValue();
                    // Update the start for the new internal
                    start = lowerArea.getKey();
                }
            }
            
            // Check if a higher Area exist -> There can be multiple
                // There may be multiple fully overlapping and 1 partially overlapping
            Map.Entry<Integer, Integer> higher = treeMap.ceilingEntry(area[0]);
            
            while(higher != null && higher.getKey() < end){
                int alreadyPainted = Math.min(higher.getValue(), end) - higher.getKey();
                areaToPaint -= alreadyPainted;
                // Remove the area
                treeMap.remove(higher.getKey());
                end = Math.max(end, higher.getValue());
                // Check the next one
                higher = treeMap.ceilingEntry(start);
            }
            
            // Add the final Area
            treeMap.put(start, end);    
            totalAreaToPaint[i] = areaToPaint;
        }
        return totalAreaToPaint;
        
    }
}

//TC: O(NLogN)
//SC : O(N)
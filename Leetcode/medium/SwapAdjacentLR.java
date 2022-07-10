package Leetcode.medium;

/*
 * 

 In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.

 

Example 1:

Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
Output: true
Explanation: We can transform start to end following these steps:
RXXLRXRXL ->
XRXLRXRXL ->
XRLXRXRXL ->
XRLXXRRXL ->
XRLXXRRLX

Example 2:

Input: start = "X", end = "L"
Output: false

 

Constraints:

    1 <= start.length <= 104
    start.length == end.length
    Both start and end will only consist of characters in 'L', 'R', and 'X'.


 */

class Solution {
    public boolean canTransform(String start, String end) {
        
        if(start.length() != end.length())
            return false;
        
        int i = 0;
        int j = 0;
        
        int n = start.length();
        
        while(i<n && j<n) {
            
            // Get past all the X since these help move L or R
            while(i<n && start.charAt(i) == 'X')
                ++i;
            
            while(j<n && end.charAt(j) == 'X')
                ++j;
            
            // traversal finished, so it must be possible
            if(i == n && j == n)
                return true;

            //i didnt match with j, and i or j crossed the word length
            if(i>=n || j>=n)
                return false;
            
            // if the curr char is not same, then there is no way to match 
            if(start.charAt(i) != end.charAt(j))
                return false;
            
            // If both point to R, then start's ptr should be <= end's ptr since
            // R can move towards right
            if(start.charAt(i) == 'L' && i < j)
                return false;
            
            // If both point to L, then start's ptr should be >= end's ptr since
            // L can move towards left
            if(start.charAt(i) == 'R' && i > j)
                return false;
            
            ++i;
            ++j;
        }
        
        // We might still have some chars left for one of the strings
        // Eg start = XXXXL, end = XLXXX 
        // 'i' will end first. So let the indices get past X and then check if they both finished processing
        while(i<n && start.charAt(i) == 'X')
            i++;
        
        while(j<n && end.charAt(j) == 'X')
            j++;
        
        return i == j;
        
    }
}

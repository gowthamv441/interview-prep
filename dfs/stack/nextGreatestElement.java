package dfs.stack;

/*
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

 

Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]

Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]

Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]

 

Constraints:

    1 <= temperatures.length <= 105
    30 <= temperatures[i] <= 100


 */
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        
        Stack<Integer> stack = new Stack<Integer>();
        for(int i=0; i<n; i++) {
            int curr_val = temperatures[i];
            
            while(!stack.isEmpty() && temperatures[stack.peek()] < curr_val) {
                int prevIndex = stack.peek();
                int diff = i - prevIndex;
                answer[prevIndex] = diff;
                stack.pop();
            }
            stack.push(i);
        }
        return answer;
    }
}
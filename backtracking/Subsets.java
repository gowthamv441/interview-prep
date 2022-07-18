/*

Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

 

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

Example 2:

Input: nums = [0]
Output: [[],[0]]

 */
class Solution {
    List<List<Integer>> ans;
    
    public List<List<Integer>> subsets(int[] nums) {
        ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        helper(0, nums, list);
        return ans;
    }
    
    public void helper(int start, int[] nums, List<Integer> list) {
        if (start>=nums.length) {
            ans.add(new ArrayList<>(list)); //In java, we will have to add like this otherwise it'll give null as it'll just have the reference instead of actual values.
            return;
        }
        
        //add the element and start the  recursive call
        list.add(nums[start]);
        helper(start+1, nums, list);
        //remove the element and do the backtracking call.
        list.remove(list.size()-1);
        helper(start+1, nums, list);
    }
}

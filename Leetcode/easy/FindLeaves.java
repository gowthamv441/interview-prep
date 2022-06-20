package Leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**

Given the root of a binary tree, collect a tree's nodes as if you were doing this:

    Collect all the leaf nodes.
    Remove all the leaf nodes.
    Repeat until the tree is empty.

 

Example 1:

Input: root = [1,2,3,4,5]
Output: [[4,5,3],[2],[1]]
Explanation:
[[3,5,4],[2],[1]] and [[3,4,5],[2],[1]] are also considered correct answers since per each level it does not matter the order on which elements are returned.

Example 2:

Input: root = [1]
Output: [[1]]

 

Constraints:

    The number of nodes in the tree is in the range [1, 100].
    -100 <= Node.val <= 100


  
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    
    List<List<Integer>> ans = new ArrayList<>();
    
    private int assignHeights(TreeNode root) {
        
        if(root == null) return -1;
        
        int leftHeight = assignHeights(root.left);
        int rightHeight = assignHeights(root.right);
        
        int curr_height = Math.max(leftHeight, rightHeight) + 1;
        
        if(ans.size()==curr_height)
            ans.add(curr_height, new ArrayList<Integer>());
        
        ans.get(curr_height).add(root.val);
        
        return curr_height;
       
    }
    
    public List<List<Integer>> findLeaves(TreeNode root) {
        assignHeights(root);
        return ans;
    }
}

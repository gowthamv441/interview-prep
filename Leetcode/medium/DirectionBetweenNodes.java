package Leetcode.medium;

/*
 * You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.

Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:

    'L' means to go from a node to its left child node.
    'R' means to go from a node to its right child node.
    'U' means to go from a node to its parent node.

Return the step-by-step directions of the shortest path from node s to node t.

 

Example 1:

Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
Output: "UURL"
Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.

Example 2:

Input: root = [2,1], startValue = 2, destValue = 1
Output: "L"
Explanation: The shortest path is: 2 → 1.

 

Constraints:

    The number of nodes in the tree is n.
    2 <= n <= 105
    1 <= Node.val <= n
    All the values in the tree are unique.
    1 <= startValue, destValue <= n
    startValue != destValue


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
    
    
    
    private String findDirectionFromLCA(TreeNode root, int start, int des) {
        Stack<TreeNode> stack = new Stack<>();
        Map<Integer, Pair<TreeNode,String>> parentMapWithDirection = new HashMap<>();    
        
        stack.push(root);
        parentMapWithDirection.put(root.val, new Pair(null, null));
        
        while(!parentMapWithDirection.containsKey(start) || !parentMapWithDirection.containsKey(des)) {
            
            TreeNode node = stack.pop();
            
            if(node.left != null) {
                stack.push(node.left);
                parentMapWithDirection.put(node.left.val, new Pair(node, "L"));
            }
            if(node.right != null) {
                stack.push(node.right);
                parentMapWithDirection.put(node.right.val, new Pair(node, "R"));
            }
        }
        
        Set<Integer> ancestor = new HashSet<>();
        int st = start;
        while(st != -1) {
            ancestor.add(st);
            st = parentMapWithDirection.get(st).getKey() != null ? parentMapWithDirection.get(st).getKey().val : -1;
        }
        
        int dt = des; 
        String pathToDestFromLCA = "";
        StringBuilder sb = new StringBuilder(pathToDestFromLCA);
        while(!ancestor.contains(dt)) {
            sb.append(parentMapWithDirection.get(dt).getValue());
            dt = parentMapWithDirection.get(dt).getKey().val;
        }
        pathToDestFromLCA = sb.reverse().toString();
        Integer lcaNode = dt;
        
        String pathToLCAFromSource = "";
        sb = new StringBuilder(pathToLCAFromSource);
        while(start != lcaNode) {
            sb.append("U");
            start = parentMapWithDirection.get(start).getKey().val;
        }
        pathToLCAFromSource = sb.toString();
        return pathToLCAFromSource+pathToDestFromLCA;
    }
    
    
    public String getDirections(TreeNode root, int startValue, int destValue) {
        
        return findDirectionFromLCA(root, startValue, destValue);
        
    }
}

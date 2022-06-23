package Leetcode.medium;

/*
 * Given the root of a binary tree, return all duplicate subtrees.

For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with the same node values.

 

Example 1:

Input: root = [1,2,3,4,null,2,4,null,null,4]
Output: [[2,4],[4]]

Example 2:

Input: root = [2,1,1]
Output: [[1]]

Example 3:

Input: root = [2,2,2,3,null,3,null]
Output: [[2,3],[3]]

 

Constraints:

    The number of the nodes in the tree will be in the range [1, 10^4]
    -200 <= Node.val <= 200


 */

class Solution {

	// String for not tree
	final String NT = "X";
	// hashset to add vsisted nodes
	Set<String> visited = new HashSet<>();
	// hashmap to keep track of duplicate subtrees
	Map<String, TreeNode> res = new HashMap<>(); 

	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		// we will do Post Order Traversal to solve this problem in O(n) TC
		// we need to serialize each node from bottom to top
		// during this process we will create serialized SubTree and add each TreeNode's serialized value in HashSet
		// if we find the same serialized value that means we found the same subtree, in that case we will insert that TreeNode in map
		postOrderTraversal(root);
		return new LinkedList<>(res.values());
	}

	private String postOrderTraversal(TreeNode node){
		StringBuilder sb = new StringBuilder();

		// initially consider them as null and set String value to X for serialization
		String leftSubTree = NT, rightSubTree = NT;

		// traverse left SubTree and seralize it
		if(node.left != null){
			leftSubTree = postOrderTraversal(node.left);
		}
		// traverse right SubTree and seralize it
		if(node.right != null){
			rightSubTree = postOrderTraversal(node.right);
		}

		// use current node, it's leftSubTree and it's rightSubTree 
		// to build serialized value of curSubTree
		sb.append(node.val);
		sb.append(",");
		sb.append(leftSubTree);
		sb.append(",");
		sb.append(rightSubTree);
		String curSubTree = sb.toString();

		// add curSubTree in list 
		addDuplicateSubtree(node, curSubTree);
		// set visited for curSubTree
		visited.add(curSubTree);

		return curSubTree;
	}

	private void addDuplicateSubtree(TreeNode node, String curSubTree){

		// if visited already has curSubTree that means curSubTree is duplicate so add it in result
		if(visited.contains(curSubTree)){
			res.put(curSubTree, node);
		}
	}
}

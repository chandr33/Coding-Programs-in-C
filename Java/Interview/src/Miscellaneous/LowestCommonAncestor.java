package Miscellaneous;

import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestor {
	private static class TreeNode {
		int data;
		TreeNode left;
		TreeNode right;
		TreeNode(int data) { this.data = data; this.left = null; this.right = null; }
	}
	TreeNode root;
	
	/*
	 * Approach 1 - Dynamically returns the LCA without tracing each node's root separately
	 */
	public static TreeNode calcAncestor(TreeNode root, int val1, int val2) {
		if (root == null)
			return null;
		
		if (root.data == val1 || root.data == val2) {
			return root;
		}
		
		TreeNode left_tree = calcAncestor(root.left, val1, val2);
		TreeNode right_tree = calcAncestor(root.right, val1, val2);

		return (left_tree == null) ? ((right_tree == null) ? null : right_tree) : ((right_tree != null) ? root : left_tree);
	}
	
	/*
	 * Approach 2 - Trace each Node's path separately and then calculate the LCA
	 */
	
	public static boolean getPath(TreeNode root, int data, List<Integer> path) {
		
		if (root == null)
			return false;
		
		path.add(root.data);

		if (root.data == data) {
			return true;
		}
		
		boolean found_left = getPath(root.left, data, path);
		boolean found_right = getPath(root.right, data, path);
		
		if (found_left || found_right) 
			return true;
		
		path.remove(path.size()-1);
		
		return false;
	}
	
	public static int getAncestor(List<Integer> path1, List<Integer> path2) {
		int i = 0;
		int j = 0;
		while (i < path1.size() && j < path2.size()) {
			if (path1.get(i) != path2.get(j))
				break;
			i++;
			j++;
		}
		return (i < path1.size()) ? path1.get(i-1) : path2.get(j-1);
	}
	
	public static void printPath(List<Integer> path) {
		for (int i : path) {
			System.out.print(i+ " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		LowestCommonAncestor lca = new LowestCommonAncestor();
		
		lca.root = new TreeNode(3); 
		lca.root.left = new TreeNode(6);
		lca.root.left.left = new TreeNode(2);
		lca.root.left.right = new TreeNode(11);
		lca.root.left.right.left = new TreeNode(9);
		lca.root.left.right.right = new TreeNode(5);
		
		lca.root.right = new TreeNode(8);
		lca.root.right.right = new TreeNode(13);
		lca.root.right.right.left = new TreeNode(7);
		
		List<Integer> path1 = new ArrayList<>();
		List<Integer> path2 = new ArrayList<>();	
		
		getPath(lca.root,9,path1);
		getPath(lca.root,7,path2);
		
		System.out.print("Path1 ");
		printPath(path1);
		System.out.print("Path2 ");
		printPath(path2);
		
		System.out.println(getAncestor(path1,path2));

		
	}
}

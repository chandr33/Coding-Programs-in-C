package DailyCodingProblems;

public class LockedBinaryTree {
	private static class TreeNode {
		TreeNode left;
		TreeNode right;
		TreeNode parent;
		boolean locked;
		int count_locked;
		TreeNode(int data, TreeNode parent, int count) 
		{ 
			left = null; right = null; this.parent = parent; locked = false; this.count_locked = count;
		}
	}
	
	TreeNode root;
	
	private static boolean is_locked(TreeNode node) {
		return node.locked;
	}
	
	private static boolean isEnabled(TreeNode node) {
		TreeNode node_ref = node;
		if (node_ref.count_locked > 0) //Check if the descendants are locked
			return false;
		
		node_ref = node_ref.parent;
		while (node_ref != null) { //Check if the ancestors are locked
			if (is_locked(node_ref)) {
				return false;
			}
			node_ref = node_ref.parent;
		}
		return true;
	}
	private static boolean lock(TreeNode node) {
		if (node == null)
			return false;
		
		TreeNode node_ref = node;
		
		if (isEnabled(node_ref)) { //Increment the lock count for all the ancestors, indicating that at least one descendant is locked
			if (!node.locked) { //Only lock if node was unlocked previously
				node.locked = true; //Lock the node
				node_ref = node_ref.parent;
				while (node_ref != null) {
					node_ref.count_locked++;
					node_ref = node_ref.parent;
				}
			}
			return true;
		}
		
		return false;
	}
	
	private static boolean unlock(TreeNode node) {
		if (node == null)
			return false;
		
		TreeNode node_ref = node;
		
		if (isEnabled(node_ref)) {
			if (node.locked) { //Only unlock if node was locked previously
				node.locked = false;
				node_ref = node_ref.parent;
				while (node_ref != null) {
					node_ref.count_locked--;
					node_ref = node_ref.parent;
				}
			}
			return true;
		}
		
		return false;
	}
	
	private static int height_of_tree(TreeNode root) {
		if (root == null)
			return 0;
		
		int height_left = 1 + height_of_tree(root.left);
		int height_right = 1 + height_of_tree(root.right);
		
		return Math.max(height_left, height_right);
	}
	
	public static void main(String[] args) {
		LockedBinaryTree tree = new LockedBinaryTree();
		tree.root = new TreeNode(3, null,0);
		
		tree.root.left = new TreeNode(6,tree.root,0);
		tree.root.left.left = new TreeNode(2,tree.root.left,0);
		tree.root.left.right = new TreeNode(11,tree.root.left,0);
		tree.root.left.right.left = new TreeNode(9,tree.root.left.right,0);
		tree.root.left.right.right = new TreeNode(5,tree.root.left.right,0);
		
		tree.root.right = new TreeNode(8,tree.root,0);
		tree.root.right.right = new TreeNode(13,tree.root.right,0);
		tree.root.right.right.left = new TreeNode(7,tree.root.right.right,0);
		
		System.out.println(height_of_tree(tree.root));
		
		System.out.println(lock(tree.root.left.left));
		System.out.println(lock(tree.root.left.right.right));
		System.out.println(unlock(tree.root.left.right));
	}
}

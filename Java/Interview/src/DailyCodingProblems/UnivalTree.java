package DailyCodingProblems;

class Tree {
	int data;
	Tree left,right;
	Tree(int data) {this.left = null; this.right = null; this.data = data;}
}
public class UnivalTree {
	static boolean isSame(Tree root) {
		/*
		 * Approach 1 - Inefficient
		 */
		if (root == null)
			return true;
		if (root.left != null && root.left.data != root.data)
			return false;
		if (root.right != null && root.right.data != root.data)
			return false;
		return isSame(root.left) && isSame(root.right);
	}
	static int countUnivalTrees(Tree root) {
		int count = 0;
		if (root == null)
			return 0;
		count = countUnivalTrees(root.left) + countUnivalTrees(root.right);
		if (isSame(root))
			count += 1;
		return count;
	}
	public static void main(String[] args) {
		Tree root = new Tree(0);
		root.left = new Tree(1);
		root.right = new Tree(0);
		root.right.left = new Tree(1);
		root.right.right = new Tree(0);
		root.right.left.left = new Tree(1);
		root.right.left.right = new Tree(1);
		System.out.println(countUnivalTrees(root));
	}
}

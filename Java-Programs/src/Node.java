import java.lang.reflect.Array;
import java.util.*;
/* This program serializes and de-serializes a binary tree. The null Node is treated as -1 to serialize and vice-versa.
 * Serialization is done using PreOrder Traversal
 */
class Node {
	int data;
	Node left,right;
	Node (int val) {
		this.data = val;
		this.left = this.right = null;
	}
}

class BinaryTree {
	Node root;
	
	BinaryTree() {
		root = null;
	}
	
	void serialize(Node node, List<Integer> arr) {
		if (node == null) {
			arr.add(-1);
			return;
		}
		arr.add(node.data);
		serialize(node.left, arr);
		serialize(node.right, arr);
	}
	
	void printSerialized(List<Integer> arr) {
		for (int i : arr) {
			System.out.printf("%d ",i);
		}
		System.out.println();
	}
	
	Node deserialize(Node node,ListIterator<Integer> iter) {
		if (iter.next() == -1)
			return node;
		else {
			node = new Node(iter.previous());
			iter.next();
		}
		node.left = deserialize(node.left,iter);
		node.right = deserialize(node.right,iter);
		return node;
	}
	
	void printDeserialized(Node node) {
		if (node == null)
			return;
		System.out.printf("%s ",node.data);
		printDeserialized(node.left);
		printDeserialized(node.right);
	}
	
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(20);
		tree.root.left = new Node(8);
		tree.root.left.left = new Node(10);
		tree.root.left.left.left = new Node(5);
		
		List<Integer> serialize_arr = new ArrayList<>();
		tree.serialize(tree.root, serialize_arr);
		
		ListIterator<Integer> iter = serialize_arr.listIterator();
		BinaryTree deserialized_tree = new BinaryTree();
		deserialized_tree.root = deserialized_tree.deserialize(deserialized_tree.root, iter);
		
		System.out.println("Printing the Serialized array");
		tree.printSerialized(serialize_arr);
		
		System.out.println();
		
		System.out.println("Printing the De-Serialized array");
		deserialized_tree.printDeserialized(deserialized_tree.root);
	}
}

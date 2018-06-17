#include <iostream>
#include <map>

using namespace std;

/*AVL Tree is a self-balancing BST where the difference between the heights 
of Left Subtree and Right Subtree cannot be more than 1 for all nodes 
AVL is more preferred for Search operations as it's height is always O(logn),
whereas for insertion and deletion Red-Black Trees are preferred as AVL involves
too many rotations.*/

typedef struct Treenode {
	int key;
	Treenode * left;
	Treenode * right;
	int height;
}Tree;

Tree * newNode(int data) {
	Tree * new_node = new Tree();
	new_node -> key = data;
	new_node -> height = 1;
	new_node -> left = NULL;
	new_node -> right = NULL;
	return new_node;
}

void PreOrder(Tree * root) {//Prints nodes in Pre-Order Traversal
	if (!root)
		return;
	cout<<root->key<<" ";
	PreOrder(root -> left);
	PreOrder(root -> right);
}

int getHeight(Tree * root) {//Returns the height of the node passed
	if (!root)
		return 0;
	return root -> height;
}

Tree * rotate_right(Tree * x) {//Performs right rotation of parent node with its left child as pivot and updates the
	Tree * y = x -> left;										//height of the parent node and the left child node
	Tree * T2 = y -> right;
	y -> right = x;
	x -> left = T2;
	x -> height = max(getHeight(x -> left), getHeight(x -> right)) + 1;
	y -> height = max(getHeight(y -> left), getHeight(y -> right)) + 1;
	return y;
}

Tree * rotate_left(Tree * x) {//Performs left rotation of parent node with its right child as pivot and updates the 
	Tree * y = x -> right;										//height of the parent node and the right child node
	Tree * T2 = y -> left;
	y -> left = x;
	x -> right = T2;
	x -> height = max(getHeight(x -> left), getHeight(x -> right)) + 1;
	y -> height = max(getHeight(y -> left), getHeight(y -> right)) + 1;
	return y;
}

int balance(Tree * root) {//Gets the balance of the passed node
	if (!root)
		return 0;
	return getHeight(root -> left) - getHeight(root -> right);
}

Tree * insert_AVL(Tree * root, int data) {
	if (!root) {//Insert node here
		Tree * new_node = newNode(data);
		return new_node;
	}
	if (root -> key > data)//Go left
		root -> left = insert_AVL(root -> left, data);
	if (root -> key < data)
		root -> right = insert_AVL(root -> right, data);
	if (root -> key == data)//Same value not allowed
		return root;

	root -> height = max(getHeight(root -> left),getHeight(root -> right)) + 1;//Update height of all the ancestors of the newly inserted node

	if (balance(root) > 1 && (data < root -> left -> key))//Left-Left Case
		return rotate_right(root);

	if (balance(root) > 1 && (data > root -> left -> key)) {//Left-Right Case
		root -> left = rotate_left(root -> left);
		return rotate_right(root);
	}

	if (balance(root) < -1 && (data > root -> right -> key))//Right-Right Case
		return rotate_left(root);

	if (balance(root) < -1 && (data < root -> right -> key)) {//Right-Left Case
		root -> right = rotate_right(root -> right);
		return rotate_left(root);
	}
	return root;
}

Tree * delete_AVL(Tree * root, int data) {
	if (!root)//Tree is empty
		return NULL;
	if (root -> key > data)//Go Left
		root -> left = delete_AVL(root -> left, data);
	if (root -> key < data)
		root -> right = delete_AVL(root -> right, data);
	if (root -> key == data) {//This is the node to be deleted
		if (!root -> left)//One or no child
			return root -> right;
		if (!root -> right)
			return root -> left;
		//If root to be deleted has 2 child nodes
		root -> key = root -> right -> key;//Replace the root to be deleted's value with its inorder successor
		root -> right = delete_AVL(root -> right,root -> right -> key);//Recursively call to delete this inorder successor
	}

	root -> height = max(getHeight(root -> left),getHeight(root -> right)) + 1;//Update all ancestors height

	if ((balance(root) < -1) && (balance(root -> right) <= 0))//Right-Right Case
		return rotate_left(root);

	if ((balance(root) < -1) && (balance(root -> right) > 0)) {//Right-Left Case
		root -> right = rotate_right(root -> right);
		return rotate_left(root);
	}

	if ((balance(root) > 1) && (balance(root -> left) >= 0))//Left-Left Case
		return rotate_right(root);

	if ((balance(root) > 1) && (balance(root -> left) < 0)) {//Left-Right Case
		root -> left = rotate_left(root -> left);
		return rotate_right(root);
	}	
	return root;
}

//Calculates the height of the binary tree
int heightOfTree(Tree * root) {
	int left_height,right_height;

	if (!root)//For an empty tree
		return 0;

	if ((!root->left) && (!root->right))
		return 1;

	left_height = (root -> left) ? heightOfTree(root -> left) + 1 : INT_MIN;//Recursive call to increment the height of the left subtree
	right_height = (root -> right) ? heightOfTree(root -> right) + 1 : INT_MIN;//Recursive call to increment the height of the right subtree

	return max(left_height,right_height);
}

void search_util(Tree * root, int data, char ** result) {
	if (!root)//Tree is empty
		return;

	if (root -> key > data)//Go left
		search_util(root -> left, data, result);

	if (root -> key < data)//Go right
		search_util(root -> right, data, result);

	if (root -> key == data)//Data found 
		strcpy(*result,"Found");//Copy result
}

char * search(Tree * root, int data) {
	//static char result[] = "Not found";
	char * result = new char();//Allocate memory for the result
	strcpy(result,"Not found");//Assign Default Value
	search_util(root, data, &result);//Search
	return result;
}

int main() {
	Tree * root = NULL;
	root = insert_AVL(root, 10);
  	root = insert_AVL(root, 20);
  	root = insert_AVL(root, 30);
  	root = insert_AVL(root, 40);
  	root = insert_AVL(root, 50);
  	root = insert_AVL(root, 25);
  	cout<<search(root,50)<<endl;
  	root = delete_AVL(root,20);
  	cout<<search(root,20)<<endl;
	return 0;
}
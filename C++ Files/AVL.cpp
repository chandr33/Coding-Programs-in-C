#include <iostream>
#include <map>

using namespace std;

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

void InOrder(Tree * root) {
	if (!root)
		return;
	InOrder(root -> left);
	cout<<root->key<<" ";
	InOrder(root -> right);
}

int getHeight(Tree * root) {
	if (!root)
		return 0;
	return root -> height;
}

Tree * rotate_right(Tree * x) {
	Tree * y = x -> left;
	Tree * T1 = y -> right;
	y -> right = x;
	x -> left = T1;
	x -> height = max(getHeight(x -> left), getHeight(x -> right)) + 1;
	y -> height = max(getHeight(y -> left), getHeight(y -> right)) + 1;
	return y;
}

Tree * rotate_left(Tree * x) {
	Tree * y = x -> right;
	Tree * T1 = y -> left;
	y -> left = x;
	x -> right = T1;
	x -> height = max(getHeight(x -> left), getHeight(x -> right)) + 1;
	y -> height = max(getHeight(y -> left), getHeight(y -> right)) + 1;
	return y;
}

int balance(Tree * root) {
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

int main() {
	Tree * root = NULL;
	root = insert_AVL(root, 10);
  	root = insert_AVL(root, 20);
  	root = insert_AVL(root, 30);
  	root = insert_AVL(root, 40);
  	root = insert_AVL(root, 50);
  	root = insert_AVL(root, 25);
  	InOrder(root);
  	cout<<endl;
	return 0;
}
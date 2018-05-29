#include <iostream>
#include <queue>
#include <stack>

using namespace std;

typedef struct TreeNode {
	int key;
	struct TreeNode * right;
	struct TreeNode * left;
}Node;

Node * newNode(int data) {
	Node * new_node = new Node();
	new_node -> key = data;
	new_node -> right = NULL;
	new_node -> left = NULL;
	return new_node;
}

bool isCompleteTree(Node * root) {
	bool left_child,right_child;
	if (root == NULL)
		return true;
	if (root -> left != NULL)
		left_child = isCompleteTree(root -> left);
	else 
		left_child = false;
	if (root -> right != NULL)
		right_child = isCompleteTree(root -> right);
	else
		right_child = false;
	return (left_child == right_child);//XNOR
}

int main() {
	Node * root = NULL;
	root = newNode(10);
    root->left = newNode(20);
    root->right = newNode(30);
 
    root->left->right = newNode(40);
    root->left->left = newNode(50);
    root->right->left = newNode(60);
    root->right->right = newNode(70);
 
    root->left->left->left = newNode(80);
    root->left->left->right = newNode(90);
    root->left->right->left = newNode(80);
    root->left->right->right = newNode(90);
    root->right->left->left = newNode(80);
    root->right->left->right = newNode(90);
    //root->right->right->left = newNode(80);
    //root->right->right->right = newNode(90);
	bool result = (isCompleteTree(root)) ? true : false;
	cout<<result<<endl;
	return 0;
}
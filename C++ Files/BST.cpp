#include <iostream>

using namespace std;

struct BST {
	int data;
	struct BST * left;
	struct BST * right;
};

struct BST * ret_newNode(int data) {
	struct BST * new_node = new BST();
	new_node -> data = data;
	new_node -> right = NULL;
	new_node -> left = NULL;
	return new_node;
}

struct BST * insert_node(struct BST * root_ref, int data) {
	if (root_ref == NULL)//Check if the tree is initially empty
	{
		root_ref = ret_newNode(data);
	}
	else if (root_ref -> data >= data)//Go to the left tree
	{
		root_ref -> left = insert_node(root_ref -> left,data);
	}
	else //Go to the right tree
	{
		root_ref -> right = insert_node(root_ref -> right,data);
	}
	return root_ref;

}

int main() {
	struct BST * root = NULL;//Create a new BST Node
	root = insert_node(root,15);
	root = insert_node(root,10);
	return 0;
}
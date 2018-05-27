#include <iostream>
#include <queue>
#include <stack>

using namespace std;

struct BST {
	int data;
	struct BST * left;
	struct BST * right;
};

struct BST * newNode(int data) {
	struct BST * new_node = new BST();
	new_node -> data = data;
	new_node -> right = NULL;
	new_node -> left = NULL;
	return new_node;
}
void iterative_post_order(struct BST * root) {
	stack<struct BST *> s;//Initialize the stack
	while (s.empty() != true || root != NULL) {//Run the while loop till its not empty or you reached the end of left sub-tree
		if (root != NULL) {
			s.push(root);//Push the root and move to the left child;
			root = root -> left;
		}
		else if (root == NULL) {//If you reached the end of left-subtree
			struct BST * right_node = s.top() -> right;//Store the first node of the right subtree
			if (right_node == NULL) {
				right_node = s.top();
				cout<<right_node->data<<" ";//Print the stack top
				s.pop();//Pop the node
				while ((s.empty()!= true) && (right_node == s.top() -> right)) {//Print the right subtree
					right_node = s.top();
					cout<<right_node->data<<" ";
					s.pop();
				}
			}
			else //If there is a right subtree to visit
				root = right_node;
		}
	}
	cout<<endl;
}
int main()
{
	struct BST * root = NULL;
	root = newNode(1);
    root->left = newNode(2);
    root->right = newNode(3);
    root->left->left = newNode(4);
    root->left->right = newNode(5);
    root->right->left = newNode(6);
    root->right->right = newNode(7);
    root->right->right->right = newNode(8);
    root->right->right->right->right = newNode(9);
	iterative_post_order(root);
	return 0;
}
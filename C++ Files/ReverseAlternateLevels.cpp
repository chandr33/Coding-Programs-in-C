#include <iostream>
#include <stack>
#include <string>
using namespace std;

/* 
This Function reverses the elements on alternate levels starting from Level 1 (root is Level 0)
*/

typedef struct TreeNode {
	char key;
	struct TreeNode * left,*right;
}Node;

Node * newNode(char c) {
	Node * new_node = new Node();
	new_node -> key = c;
	new_node -> left = NULL;
	new_node -> right = NULL;
	return new_node;
}

void Inorder(Node * root) {
	if (root == NULL)
		return;
	Inorder(root -> left);
	cout<<root -> key<<" ";
	Inorder(root -> right);
}

void store_left(Node * root, int odd, int * index, char * arr_left) {
	if (root == NULL)
		return;
	store_left(root -> left,odd+1,index,arr_left);
	store_left(root -> right,odd-1,index,arr_left);
	if ((odd % 2) == 1 || (odd % 2) == -1) {//Nodes whose kids have to be reversed
		arr_left[*index] = root->key;
		*index = *index + 1;
	}

}
void store_right(Node * root, int odd, int * index, char * arr_right) {
	if (root == NULL)
		return;
	store_right(root -> right,odd+1,index,arr_right);
	store_right(root -> left,odd-1,index,arr_right);
	if ((odd % 2) == 1 || (odd % 2) == -1) {//Nodes whose kids have to be reversed
		arr_right[*index] = root->key;
		*index = *index + 1;
	}
}

void modify_left(Node * root, int odd, int * index, char * arr_left) {
	if (root == NULL)
		return ;
	modify_left(root -> left,odd+1,index,arr_left);
	modify_left(root -> right,odd-1,index,arr_left);
	if ((odd % 2) == 1 || (odd % 2) == -1) {//Nodes whose kids have to be reversed
		root->key = arr_left[*index];
		*index = *index + 1;
	}
}

void modify_right(Node * root, int odd, int * index, char * arr_right) {
	if (root == NULL)
		return ;
	modify_right(root -> right,odd+1,index,arr_right);
	modify_right(root -> left,odd-1,index,arr_right);
	if ((odd % 2) == 1 || (odd % 2) == -1) {//Nodes whose kids have to be reversed
		root->key = arr_right[*index];
		*index = *index + 1;
	}
}

void reversal(Node * root) {
	int odd = 1;
	char arr_left[100];
	char arr_right[100];
	int index_to_store = 0, index_to_modify = 0;
	store_left(root->left,odd,&index_to_store,arr_left);
	arr_left[index_to_store] = '\0';
	index_to_store = 0;
	store_right(root->right,odd,&index_to_store,arr_right);
	arr_right[index_to_store] = '\0';

	modify_left(root->left,odd,&index_to_modify,arr_right);
	index_to_modify = 0;
	modify_right(root->right,odd,&index_to_modify,arr_left);
}

int main() {
	Node *root = newNode('a');
    root->left = newNode('b');
    root->right = newNode('c');
    root->left->left = newNode('d');
    root->left->right = newNode('e');
    root->right->left = newNode('f');
    root->right->right = newNode('g');
    root->left->left->left = newNode('h');
    root->left->left->right = newNode('i');
    root->left->right->left = newNode('j');
    root->left->right->right = newNode('k');
    root->right->left->left = newNode('l');
    root->right->left->right = newNode('m');
    root->right->right->left = newNode('n');
    root->right->right->right = newNode('o');
    Inorder(root); 
    reversal(root);
    Inorder(root);
}
#include <iostream>
#include <stack>
#include <string>
using namespace std;

/* In order to construct a General Binary tree, we need two traversals ,i.e. In-Order and Pre-Order.
Let's suppose we have N distinct elements. We can have a possible of N! lists out of those elements.
But for trees we can have 2*N! trees (once with root->left = NULL and then with root->right = NULL).
Therefore accroding to the Pigeonhole Principle, there is one list which can generate atleast 2 trees.
On the other hand, for a BST, an In-Order traversal will give us a sorted list so we can use that to construct
a BST. */

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
//Returns the first occurrence of str2 in str1, otherwise NULL
char * my_strstr(char * str1, char * str2) {
	int i =0,j=0,match=0;
	while (j < strlen(str2)) {
		if (str1[i] == str2[j]) {
			match = 1;
			i++;
			j++;
		}
		else {
			if (match)
				return NULL;
			str1++;
		}
	}
	return str1;
}
//Pre-Order Traversal
void Preorder_Store(Node * tree, char * arr,int * index) {
	if (tree == NULL)
		return;
	arr[*index] = tree -> key;
	*index = *index + 1;
	Preorder_Store(tree->left,arr,index);
	Preorder_Store(tree->right,arr,index);
}

void Inorder_Store(Node * tree, char * arr, int * index) {
	if (tree == NULL)
		return;
	Inorder_Store(tree->left,arr,index);
	arr[*index] = tree -> key;
	*index = *index + 1;
	Inorder_Store(tree -> right,arr,index);
}

bool isSubTree(Node * S, Node * T) {
	char arr_SubInOrder[100];
	char arr_MainInOrder[100];
	int i = 0;
	Inorder_Store(S,arr_SubInOrder,&i);
	arr_SubInOrder[i] = '\0';
	i = 0;
	Inorder_Store(T,arr_MainInOrder,&i);
	arr_MainInOrder[i] = '\0';

	if (!my_strstr(arr_MainInOrder,arr_SubInOrder))
		return false;

	char arr_SubPreOrder[100];
	char arr_MainPreOrder[100];
	int j = 0;
	Preorder_Store(S,arr_SubPreOrder,&j);
	arr_SubPreOrder[j] = '\0';
	j = 0;
	Preorder_Store(T,arr_MainPreOrder,&j);
	arr_MainPreOrder[j] = '\0';

	return strstr(arr_MainPreOrder,arr_SubPreOrder) ? true : false;
}

int main() {
	Node *T = newNode('a');
    T->left = newNode('b');
    T->left->left = newNode('c');
    T->right = newNode('d');
    T->right->right = newNode('e');
 
    Node *S = newNode('a');
    S->left = newNode('b');
    S->left->left = newNode('c');
    S->left->left = newNode('d');
	cout<<isSubTree(S,T)<<endl; 
}
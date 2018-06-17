#include <iostream>

using namespace std;

/*Red-Black Tree Properties - 
1) Every Node has a color, either Red or Black
2) Root of tree is always black
3) There are no two adjacent Red Nodes - # of Black Nodes is at least floor(n/2); n = total nodes
4)Every path from Root to a NULL has the same # of black nodes - There is a root to leaf path with at most
																 Log2(n+1) black nodes with n = Total nodes
Also, n>=2^k - 1, where k is the min. number of nodes from all root to NULL paths

INSERTION - Black Aunt :- Rotation ; Red Aunt :- Color Flip
Note :- NULL is a black
Color Flip :- 1) Node is Red (Grandparent) ; If Node is root then change color ot black
			  2) Children are Black (Aunt and Parent)

Rotation :- 4 cases, After rotation
			1) Current Node is Black
			2) Children are Red
*/ 
typedef struct Treenode {
	int key;
	int color;//Black = 0; Red = 1
	int depth;
	int height;
	Treenode * left;
	Treenode * right;
}Tree;

Tree * newNode(int data) {
	Tree * new_node = new Tree();
	new_node -> key = data;
	new_node -> color = 1;
	new_node -> depth = 1;
	new_node -> height = 1;
	new_node -> left = NULL;
	new_node -> right = NULL;
	return new_node;
}

void PreOrder(Tree * root) {
	if (!root)
		return;
	cout<<root -> key<<" ";
	PreOrder(root -> left);
	PreOrder(root -> right);
}

int getColor(Tree * root) {
	if (!root)
		return 0;//Black
	return root -> color;
}

int getDepth(Tree * root) {
	if (!root)
		return 0;
	return root -> depth;
}

int getHeight(Tree * root) {
	if (!root)
		return 0;
	return root -> height;
}

int getBalance(Tree * root) {
	if (!root)
		return 0;
	return getHeight(root -> left) - getHeight(root -> right);
}

Tree * rotate_left(Tree * x) {
	Tree * y = x -> right;
	Tree * T2 = y -> left;
	y -> left = x;
	x -> right = T2;
	x -> height = max(getHeight(x -> left), getHeight(x -> right)) + 1;
	y -> height = max(getHeight(y -> left), getHeight(y -> right)) + 1;
	return y;
}

Tree * rotate_right(Tree * x) {
	Tree * y = x -> left;
	Tree * T2 = y -> right;
	y -> right = x;
	x -> left = T2;
	x -> height = max(getHeight(x -> left), getHeight(x -> right)) + 1;
	y -> height = max(getHeight(y -> left), getHeight(y -> right)) + 1;
	return y;
}

Tree * color_flip(Tree * x) {
	x -> color = 1;//Parent is red
	x -> left -> color = x -> right -> color = 0;//Children are black
	return x;
}

Tree * color_flip_rotate(Tree * x) {
	x -> color = 0;//Parent is black
	x -> left -> color = x -> right -> color = 1;//Children are Red
	return x;
}

bool sameColor(Tree * root) {
	return getColor(root) && ((getColor(root -> left)) || (getColor(root -> right)));
}

Tree * insert_RBTREE(Tree * root, int data) {
	if (!root) {
		Tree * new_node = newNode(data);
		return new_node;
	}
	if (root -> key > data) {//Go left
		root -> left = insert_RBTREE(root -> left, data);
		root -> left -> depth = getDepth(root) + 1;
		if (getDepth(root) == 1)
			root -> color = 0;
	}

	else if (root -> key < data) {//Go right
		root -> right = insert_RBTREE(root -> right, data);
		root -> right -> depth = getDepth(root) + 1;
		if (getDepth(root) == 1)
			root -> color = 0;
	}

	else//If node is already present, then simply return the root of the present tree
		return root;

	root -> height = max(getHeight(root -> left),getHeight(root -> right)) + 1;//Update height of all ancestors

	if (getBalance(root) != 0) {
		if (sameColor(root -> left)) {
			if(getColor(root->right)) {//If aunt is Red then Color Flip
				root = color_flip(root);
				if (getDepth(root) == 1)//If this is the root
					root -> color = 0;
			}
			else {
				if (data > root -> left -> key) {//Left-Right Case
					root -> left = rotate_left(root -> left);
					root = rotate_right(root);
					root = color_flip_rotate(root);
				}
				else if (data < root -> left -> key) {//Left-Left Case
					root = rotate_right(root);
					root = color_flip_rotate(root);
				}
			}
		}
		else if (sameColor(root -> right)) {
			if(getColor(root->left)) {
				root = color_flip(root);
				if (getDepth(root) == 1)//If this is the root
					root -> color = 0;
			}
			else {
				if (data < root -> right -> key) {//Right-Left Case
					root -> right = rotate_right(root -> right);
					root = rotate_left(root);
					root = color_flip_rotate(root);
				}
				else if (data > root -> right -> key) {//Right-Right Case
					root = rotate_left(root);
					root = color_flip_rotate(root);
				}
			}
		}
	}

	return root;
}

int main() {
	Tree * root = NULL;
	root = insert_RBTREE(root,3);
	root = insert_RBTREE(root,1);
	root = insert_RBTREE(root,5);
	root = insert_RBTREE(root,7);
	root = insert_RBTREE(root,6);
	root = insert_RBTREE(root,8);
	root = insert_RBTREE(root,9);
	root = insert_RBTREE(root,10);
	PreOrder(root);
	cout<<endl;
	return 0;
}
#include <iostream>
#include <map>
#include <utility>

using namespace std;

/*Red-Black Tree Properties - 
1) Every Node has a color, either Red or Black
2) Root of tree is always black
3) There are no two adjacent Red Nodes - # of Black Nodes is at least floor(n/2); n = total nodes
4)Every path from Root to a NULL has the same # of black nodes - There is a root to leaf path with at most Log2(n+1) black nodes with n = Total nodes
Also, n>=2^k - 1, where k is the min. number of nodes from all root to NULL paths

INSERTION - Black Aunt :- Rotation ; Red Aunt :- Color Flip
Note :- NULL is black
Color Flip :- 1) Node is Red (Grandparent) ; If Node is root then change color to black
			  2) Children are Black (Aunt and Parent)

Rotation :- 4 cases, After rotation
			1) Current Node is Black
			2) Children are Red
*/ 
typedef struct Treenode {
	int key;
	int color;//Black = 0; Red = 1
	Treenode * parent;
	Treenode * left;
	Treenode * right;
}Tree;

Tree * root = NULL;//Root of the tree

Tree * newNode(int data) {
	Tree * new_node = new Tree();
	new_node -> key = data;
	new_node -> color = 1;
	new_node -> left = NULL;
	new_node -> right = NULL;
	new_node -> parent = NULL;
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

Tree * rotate_left(Tree * GrandParent) {
	//First save all the links that will be manipulated
	Tree * GreatGrandParent = GrandParent -> parent;
	Tree * Parent = GrandParent -> right;
	Tree * T2 = Parent -> left;

	//Set the GreatGrandParent Link
	if (!GreatGrandParent) //GrandParent is root
		root = Parent;

	else if (GrandParent == GreatGrandParent -> right)//Right-Right Case
		GreatGrandParent -> right = Parent;

	else if (GrandParent == GreatGrandParent -> left)//Left-Right Case
		GreatGrandParent -> left = Parent;

	//Rotate the Links
	Parent -> left = GrandParent;
	GrandParent -> right = T2;

	//Set T2's parent Link
	if (T2)
		T2 -> parent = GrandParent;

	//Set the GrandParent's Parent Link
	GrandParent -> parent = Parent;

	//Set the Parent's parent link
	Parent -> parent = GreatGrandParent;

	//Return the current Parent
	return Parent;
}

Tree * rotate_right(Tree * GrandParent) {
	//First Save all the Links that will be manipulated 
	Tree * GreatGrandParent = GrandParent -> parent;
	Tree * Parent = GrandParent -> left;
	Tree * T2 = Parent -> right;

	//Set the GrandParent Link
	if (!GreatGrandParent) //GreatGrandParent is root
		root = Parent;

	else if (GrandParent == GreatGrandParent -> left)//Left-Left Case
		GreatGrandParent -> left = Parent;

	else if (GrandParent == GreatGrandParent -> right)//Right-Left Case
		GreatGrandParent -> right = Parent;

	//Rotate Links
	Parent -> right = GrandParent;
	GrandParent -> left = T2;

	//Set T2's Parent's Link
	if (T2)
		T2 -> parent = GrandParent;
	
	//Set GrandParent's Parent Link
	GrandParent -> parent = Parent;

	//Set the Parent's parent Link
	Parent -> parent = GreatGrandParent;

	//Return the Parent
	return Parent;
}

Tree * color_flip(Tree * x) {//Flip color when Aunt is Red
	x -> color = 1;//Parent is red
	x -> left -> color = x -> right -> color = 0;//Children are black
	return x;
}

Tree * color_flip_rotate(Tree * x) {//Flip color when Aunt is Black
	x -> color = 0;//Parent is black

	if (x -> left)
		x -> left -> color = 1;//Children are Red

	if (x -> right)
		x -> right -> color = 1;

	return x;
}

Tree * insert_RBTREE_util(Tree * root, Tree * new_node) {
	if (!root)
		return new_node;

	if (root -> key > new_node -> key) {//Go left
		root -> left = insert_RBTREE_util(root -> left, new_node);
		root -> left -> parent = root;//Set the Left Child's Parent Link
	}

	else if (root -> key < new_node -> key) {//Go right
		root -> right = insert_RBTREE_util(root -> right, new_node);
		root -> right -> parent = root;//Set the Right Child's Parent Link
	}
	else//If node is already present, then simply return the root of the present tree
		return root;

	return root;
}

Tree * fix_Violations(Tree * new_node) {
	Tree * Grandparent;
	Tree * Parent;
	while ((new_node != root) && (getColor(new_node) && getColor(new_node -> parent))) {
		Parent = new_node -> parent;
		Grandparent = Parent-> parent;
		//Make two cases per case of aunt, whether she's on left of grandparent or right of grandparent
		if (Grandparent -> right == Parent) {//Aunt is on left
			Tree * aunt = Grandparent -> left;

			if ((aunt) && getColor(aunt))//If Aunt is red then flip color
				new_node = color_flip(Grandparent);

			else {//If aunt is black then rotate, two cases - Right-Right and Right-Left
				if (Parent -> right == new_node) {//Right-Right Case - Rotate Left
					new_node = rotate_left(Grandparent);
					new_node = color_flip_rotate(new_node);
				}
				else {//Right-Left Case - Rotate Right then Left
					new_node = rotate_right(Parent);
					new_node = rotate_left(new_node -> parent);
					new_node = color_flip_rotate(new_node);
				}
			}
		}
		else if (Grandparent -> left == Parent) {//Aunt is on right
			Tree * aunt = Grandparent -> right;

			if ((aunt) && getColor(aunt))//Aunt is red then flip color
				new_node = color_flip(Grandparent);	

			else {//If Aunt is black then rotate, two cases - Left-Left and Left-Right
				if (Parent -> left == new_node) {//Left-Left Case
					new_node = rotate_right(Grandparent);
					new_node = color_flip_rotate(new_node);
				}
				else {//Left-Right Case - Rotate Left then Right
					new_node = rotate_left(Parent);
					new_node = rotate_right(new_node -> parent);
					new_node = color_flip_rotate(new_node);
				}
			}
		}	
	}
	root -> color = 0;
	return root;
}

Tree * insert_RBTREE(int data) {
	Tree * new_node = newNode(data);
	root = insert_RBTREE_util(root, new_node);
	root = fix_Violations(new_node);
	return root;
}

int main() {
	root = insert_RBTREE(3);
	root = insert_RBTREE(1);
	root = insert_RBTREE(5);
	root = insert_RBTREE(7);
	root = insert_RBTREE(6);
	root = insert_RBTREE(8);
	root = insert_RBTREE(9);
	root = insert_RBTREE(0);
	root = insert_RBTREE(2);
	root = insert_RBTREE(10);
	PreOrder(root);
	cout<<endl;
	return 0;
}
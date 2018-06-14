#include <iostream>
#include <queue>
#include <vector>
#include <map>

using namespace std;

typedef struct TreeNode {
	int key;
	TreeNode * left;
	TreeNode * right;
}Tree;

Tree * newTreeNode(int data) {
	Tree * new_node = new Tree();
	new_node -> key = data;
	new_node -> left = NULL;
	new_node -> right = NULL;
	return new_node;
}

void printDLL(Tree * head) {//Prints the DLL from start to end 
	if (!head)
		return;
	cout<<head->key<<" ";
	printDLL(head->right);
}

void printDLLReverse(Tree * head) {//Prints the DLL from end to start
	Tree * head_ref = head;
	while(head_ref -> right != NULL)
		head_ref = head_ref -> right;
	while(head_ref != NULL) {
		cout<<head_ref->key<<" ";
		head_ref = head_ref -> left;
	}
}
void store_inOrder(Tree * root,int hd,map<int,vector<Tree*>> &table) {//Stores the tree nodes in the map in In-Order traversal
	if (!root)
		return;
	store_inOrder(root->left,hd-1,table);
	table[hd].push_back(root);
	store_inOrder(root->right,hd+1,table);
}

void addToList(Tree * head_ref, Tree * node) {//Appends to the DLL
	Tree * head = head_ref;
	while(head -> right != NULL)
		head = head -> right;
	head -> right = node;
	node -> left = head;
	node -> right = NULL;
}
//Function to convert Binary Tree to Doubly Linked List using InOrder Traversal
void BToDLL(Tree * root, Tree ** head_ref) {
	map<int,vector<Tree*>> table;//Map containing key = Horizontal Distance; Value = Vector of Tree Nodes -> This allows us to store multiple tree nodes for the same key value
	int horizontal_distance = 0;
	store_inOrder(root,horizontal_distance,table);
	map<int,vector<Tree*>> :: iterator map_iterator;
	vector<Tree*> :: iterator vec_iterator;
	map_iterator = table.begin();
	vec_iterator = map_iterator->second.begin();

	*head_ref = (*vec_iterator);//Point the head node to the first value stored in map
	(*vec_iterator) -> left = NULL;
	(*vec_iterator) -> right = NULL;
	map_iterator++;

	while (map_iterator != table.end()) {//Populate the DLL by extracting each value stored from the map and adding it to the list
		for (vec_iterator = map_iterator->second.begin(); vec_iterator != map_iterator->second.end(); vec_iterator++) {
			addToList(*head_ref,*vec_iterator);
		}
		map_iterator++;
	}
}

int main() {
	Tree * root = NULL;
	root = newTreeNode(10);
	root -> left = newTreeNode(12);
	root -> right = newTreeNode(15);
	root -> left -> left = newTreeNode(6);
	root -> left -> right = newTreeNode(7);
	root -> right -> left = newTreeNode(8);
	root -> right -> right = newTreeNode(9);
	Tree * head = NULL;
	BToDLL(root,&head);
	printDLL(head);
	cout<<endl;
	printDLLReverse(head);
	cout<<endl;
}
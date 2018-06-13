#include <iostream>
#include <queue>
#include <stack>
#include <map>
#include <utility>
#include <vector>

//Traverse through the tree level by level. Add the first node to the hash map according to each level.

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

void Print_Left_View (Node * root) {
	if (!root){
		cout<<"Tree Empty"<<endl;
		return;
	}
	int level = 1;
	queue<pair<Node *,int>> q;
	map<int, Node*> table;
	map<int, Node*> :: iterator i,j;
	q.push(make_pair(root,level));
	table.insert(pair <int,Node*> (q.front().second,q.front().first));//Insert the root at level 1
	while(!q.empty()) {//Loop till queue is not empty
		if (q.front().first->left)//Push Left Child, if Not NULL
			q.push(make_pair(q.front().first->left,q.front().second+1));
		if (q.front().first->right)//Push right child, if not NULL
			q.push(make_pair(q.front().first->right,q.front().second+1));
		q.pop();//Dequeue the front node
		if (!q.empty()) {//Check that queue is not empty after popping, this is basically an extra check for the last node of the tree
			i = table.find(q.front().second);//Search the table for the node at the current level
			if (i == table.end())//If node doesn't exist already, then add it to the table. In this way we add only the first node of a particular level
				table.insert(pair <int,Node*> (q.front().second,q.front().first));
		}
	}
	for (j = table.begin(); j != table.end(); j++)
		cout<<j->second->key<<" ";
	cout<<endl;
}

void store_in_array(Node * root, int arr[],int & index) {//In order traversal of tree to store tree nodes in a sorted order
	if (!root)
		return;
	store_in_array(root->left,arr,index);
	arr[index++] = root->key;
	store_in_array(root->right,arr,index);
}
//Store the tree nodes in an array using In-Order traversal and then check if the array is sorted
bool isBST(Node * root) {
	if (!root)
		return false;

	int result_array[100];
	int i = 0;
	store_in_array(root,result_array,i);
	for (int j = 0; j < i-1; j++) {//Return true if the InOrder traversal of tree stored in the array is sorted
		if (result_array[j] > result_array[j+1])
			return false;
	}
	return true;
}

void PreOrderStore(Node * root, int horizontal_depth,map<int,vector<Node*>> & table) {//PreOrder Traversal to store tree nodes according to their horizontal distance
	if (!root)
		return;
	table[horizontal_depth].push_back(root);
	PreOrderStore(root->left,horizontal_depth-1,table);//Horizontal Depth of Left Child = Horizontal Depth of Parent - 1
	PreOrderStore(root->right,horizontal_depth+1,table);//Horizontal Depth of Right Child = Horizontal Depth of Parent + 1
}

void Print_Vertical_Order(Node * root) {
	if (!root) {
		cout<<"Empty Tree"<<endl;
		return;
	}
	int hd = 0;
	map<int,vector<Node*>> table;
	PreOrderStore(root,hd,table);//Function to the all the nodes of the tree according to their horizontal distance
	map<int,vector<Node*>> :: iterator table_iterator;//key = horizontal distance, value = Tree Node
	vector<Node *> :: iterator vec_iterator;
	for (table_iterator = table.begin(); table_iterator != table.end(); table_iterator++) {//Print all tree nodes starting in an ascending order
		for (vec_iterator = table_iterator->second.begin(); vec_iterator != table_iterator->second.end(); vec_iterator++)
			cout<<(*vec_iterator)->key<<" ";
		cout<<endl;
	}
}

void Print_Spiral_Order(Node * root) {
	if (!root) {
		cout<<"Empty Tree"<<endl;
		return;
	}
	queue<pair<Node*,int>> q;
	q.push(make_pair(root,0));
	map<int,vector<Node *>> table;
	table[0].insert(table[0].begin(),root);//Add the root to the map at level 0
	while(!q.empty()) {//Performs level order traversal
		Node * current = q.front().first;
		if (current -> left)
			q.push(make_pair(current->left,q.front().second+1));//Add left node of the front node
		if (current -> right)
			q.push(make_pair(current->right,q.front().second+1));//Add the right node of the front node
		q.pop();//Dequeue the fron node
		if (!q.empty()) {
			if (q.front().second % 2 == 1)//Check if the front node is at an odd level
				table[q.front().second].push_back(q.front().first);//Append to the table at index(level of tree) i.e. 1->2->3
			else
				table[q.front().second].insert(table[q.front().second].begin(),q.front().first);//Add to the beginning of table at index(level of tree) i.e. 3->2->1
		}
	}
	map<int,vector<Node*>> :: iterator table_iterator;
	vector<Node*> :: iterator vec_iterator;
	for (table_iterator = table.begin(); table_iterator != table.end(); table_iterator++) {//Print out all the tree nodes in the table
		for (vec_iterator = table_iterator->second.begin(); vec_iterator != table_iterator->second.end(); vec_iterator++)
			cout<<(*vec_iterator)->key<<" ";
		cout<<endl;
	}
}
int main () {
	Node * root = NULL;
	root = newNode(1);
	root -> left = newNode(2);
	root -> right = newNode(3);
	root -> left -> left = newNode(4);
	root -> left -> right = newNode(5);
	root -> right -> left = newNode(6);
	root -> right -> right = newNode(7);
	Print_Left_View(root);
	Print_Vertical_Order(root);
	Print_Spiral_Order(root);
	cout<<isBST(root)<<endl;
}
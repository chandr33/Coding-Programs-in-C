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
	table.insert(pair <int,Node*> (q.front().second,q.front().first));
	while(!q.empty()) {
		if (q.front().first->left)
			q.push(make_pair(q.front().first->left,q.front().second+1));
		if (q.front().first->right)
			q.push(make_pair(q.front().first->right,q.front().second+1));
		q.pop();
		if (!q.empty()) {
			i = table.find(q.front().second);
			if (i == table.end())
				table.insert(pair <int,Node*> (q.front().second,q.front().first));
		}
	}
	for (j = table.begin(); j != table.end(); j++)
		cout<<j->second->key<<endl;
}

void store_in_array(Node * root, int arr[],int & index) {
	if (!root)
		return;
	store_in_array(root->left,arr,index);
	arr[index++] = root->key;
	store_in_array(root->right,arr,index);
}
//Store the tree nodes in an array using In-Order traversal and then check if the array is sorted
bool isBST(Node * root) {
	int result_array[100];
	int i = 0;
	store_in_array(root,result_array,i);
	for (int j = 0; j < i-1; j++) {
		if (result_array[j] > result_array[j+1])
			return false;
	}
	return true;
}

void PreOrderStore(Node * root, int horizontal_depth,map<int,vector<Node*>> & table) {
	if (!root)
		return;
	table[horizontal_depth].push_back(root);
	PreOrderStore(root->left,horizontal_depth-1,table);
	PreOrderStore(root->right,horizontal_depth+1,table);
}

void Print_Vertical_Order(Node * root) {
	int hd = 0;
	map<int,vector<Node*>> table;
	PreOrderStore(root,hd,table);
	map<int,vector<Node*>> :: iterator table_iterator;
	vector<Node *> :: iterator vec_iterator;
	for (table_iterator = table.begin(); table_iterator != table.end(); table_iterator++) {
		for (vec_iterator = table_iterator->second.begin(); vec_iterator != table_iterator->second.end(); vec_iterator++)
			cout<<(*vec_iterator)->key<<" ";
		cout<<endl;
	}
}

void Print_Spiral_Order(Node * root) {
	queue<pair<Node*,int>> q;
	int level = 0;
	q.push(make_pair(root,level));
	map<int,vector<Node *>> table;
	table[level].insert(table[level].begin(),root);
	while(!q.empty()) {
		Node * current = q.front().first;
		if (current -> left)
			q.push(make_pair(current->left,q.front().second+1));
		if (current -> right)
			q.push(make_pair(current->right,q.front().second+1));
		q.pop();
		if (!q.empty()) {
			if (q.front().second % 2 == 1)
				table[q.front().second].push_back(q.front().first);
			else
				table[q.front().second].insert(table[q.front().second].begin(),q.front().first);
		}
	}
	map<int,vector<Node*>> :: iterator table_iterator;
	vector<Node*> :: iterator vec_iterator;
	for (table_iterator = table.begin(); table_iterator != table.end(); table_iterator++) {
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
	Print_Spiral_Order(root);
}
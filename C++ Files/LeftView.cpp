#include <iostream>
#include <queue>
#include <map>
#include <utility>

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

int main () {
	Node * root = NULL;
	root = newNode(1);
	root -> left = newNode(-5);
	root -> right = newNode(5);
	root -> left -> left = newNode(-7);
	root -> left -> right = newNode(-2);
	root -> right -> left = newNode(3);
	root -> right -> right = newNode(7);
	root -> left -> left -> right = newNode(-6);
	cout<<isBST(root)<<endl;
    //Print_Left_View(root);
}
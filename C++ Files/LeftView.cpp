#include <iostream>
#include <queue>
#include <stack>
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

int main () {
	Node * root = NULL;
	root = newNode(1);
	root -> left = newNode(2);
	root -> right = newNode(3);
	root -> left -> left = newNode(4);
	root -> left -> right = newNode(5);
	root -> right -> left = newNode(6);
	root -> right -> right = newNode(7);
	root -> left -> left -> right = newNode(8);

    Print_Left_View(root);
}
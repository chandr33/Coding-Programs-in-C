#include <iostream>
#include <queue>
#include <stack>
#include <map>
#include <utility>

using namespace std;

typedef struct TreeNode {
	int key;
	struct TreeNode * right;
	struct TreeNode * left;
}Node;

struct qnode {
	int distance;
	Node * node;
};

Node * newNode(int data) {
	Node * new_node = new Node();
	new_node -> key = data;
	new_node -> right = NULL;
	new_node -> left = NULL;
	return new_node;
}

bool isCompleteTree(Node * root) {
	bool left_child,right_child;
	if (root == NULL)
		return true;
	if (root -> left != NULL)
		left_child = isCompleteTree(root -> left);
	else 
		left_child = false;
	if (root -> right != NULL)
		right_child = isCompleteTree(root -> right);
	else
		right_child = false;
	return (left_child == right_child);//XNOR
}
//Prints the bottom view of a binary tree
void BottomViewofTree(Node * root) {
	if (root == NULL) {
		cout<<"Tree is empty"<<endl;
		return;
	}
	queue<qnode> q;
	map <int,int> distance_map;
	map <int,int> :: iterator i;
	qnode qitem;
	qitem.distance = 0;
	qitem.node = root;
	q.push(qitem);
	distance_map.insert(pair <int,int> (qitem.distance,(qitem.node)->key));
	while (q.empty() != true) {
		qitem = q.front();
		Node * tree_node = q.front().node;
		q.pop();
		if (tree_node -> left != NULL) {
			qnode left_node;
			left_node.distance = qitem.distance - 1;
			left_node.node = tree_node -> left;
			q.push(left_node);
			i = distance_map.find(left_node.distance);
			if (i != distance_map.end())//If the value is found
				i -> second = left_node.node -> key;
			else
				distance_map.insert(pair <int,int> (left_node.distance,(left_node.node) -> key));
		}
		if (tree_node -> right != NULL) {
			qnode right_node;
			right_node.distance = qitem.distance + 1;
			right_node.node = tree_node -> right;
			q.push(right_node);
			i = distance_map.find(right_node.distance);
			if (i != distance_map.end())//If the value is found
				i -> second = right_node.node -> key;
			else
				distance_map.insert(pair <int,int> (right_node.distance,(right_node.node) -> key));
		}
	}
	for (i = distance_map.begin(); i != distance_map.end(); i++)
		cout<<i -> second<<" ";
	cout<<endl;
}
//Prints the top view of a binary tree
void topView(Node * root) {
	if (root == NULL)
		return;
	queue <qnode> q;
	map <int, int> distance_map;
	qnode qitem;
	qitem.distance = 0;
	qitem.node = root;
	q.push(qitem);//Pushing the root with depth = 0 and horizontal distance = 0
	cout<<(qitem.node)->key<<" ";
	distance_map.insert(pair <int,int> (qitem.distance,(qitem.node)->key));
	while (!q.empty()) {
		qitem = q.front();
		Node * current = qitem.node;
		q.pop();
		if (distance_map.find(qitem.distance) == distance_map.end()) {
			distance_map.insert(pair <int,int> (qitem.distance,(qitem.node)->key));
			cout<<(qitem.node)->key<<" ";
		}
		if (current -> left != NULL) {
			qnode left_node;
			left_node.distance = qitem.distance - 1;
			left_node.node = current -> left;
			q.push(left_node);	
		}
		if (current -> right != NULL) {
			qnode right_node;
			right_node.distance = qitem.distance + 1;
			right_node.node = current -> right;
			q.push(right_node);
		}
	}
	cout<<endl;
}

int main() {
	Node * root = NULL;
	root = newNode(1);
    root->left = newNode(2);
    root->right = newNode(3);
    root->left->left = newNode(4);
    root->left->right = newNode(5);
    root->right->left = newNode(6);
    root->right->right = newNode(7);
	topView(root);
	return 0;
}
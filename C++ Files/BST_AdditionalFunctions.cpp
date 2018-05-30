#include <iostream>
#include <queue>
#include <stack>
#include <map>

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

int main() {
	Node * root = NULL;
	root = newNode(20);
	root->left = newNode(8);
    root->right = newNode(22);
    root->left->left = newNode(5);
    root->left->right = newNode(3);
    root->right->left = newNode(4);
    root->right->right = newNode(25);
    root->left->right->left = newNode(10);
    root->left->right->right = newNode(14);
	BottomViewofTree(root);
	return 0;
}
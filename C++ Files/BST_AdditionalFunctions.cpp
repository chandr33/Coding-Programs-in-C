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
void print_In_Order(Node * root) {
	if (root == NULL)
		return;
	print_In_Order(root -> left);
	cout<<root -> key<<" ";
	print_In_Order(root -> right);
}

Node * Delete_Shortest_Path_util(Node * root, int left_depth, int right_depth,int k) {
	if (root == NULL)
		return root;
	root -> left = Delete_Shortest_Path_util(root -> left,left_depth-1,right_depth,k);
	root -> right = Delete_Shortest_Path_util(root -> right,left_depth,right_depth-1,k);
	if ((root -> left == NULL) && (root -> right == NULL)) {
		if ((left_depth+right_depth) >= k)
			return NULL;
	}
	return root;
}

//Function that deletes the shorter path than k, and returns the root node
Node * Delete_Shortest_Path(Node * root, int k) {
	int left_depth = k-1;
	int right_depth = k-1;
	return Delete_Shortest_Path_util(root,left_depth,right_depth,k);
}

void LowestCommonAncestor_util(Node * root, int num1, int num2, int * result) {
	if (num1 == root -> key)
		return;
	if (num1 < root -> key)
		LowestCommonAncestor_util(root -> left,num1,num2,result);
	else if (num1 > root -> key)
		LowestCommonAncestor_util(root -> right,num1,num2,result);
	
	if (root -> key == num2)
		*result = num2;
	else if (root -> right != NULL){
		if (root -> right -> key == num2)
			*result = root -> key;
	}
	else if (root -> left != NULL){
		if (root -> left -> key == num2)
			*result = root -> key;
	}
}
//Returns the Lowest Common Ancestor of the given nodes
int LowestCommonAncestor(Node * root,int num1,int num2) {
	int lca = 0;
	LowestCommonAncestor_util(root,num1,num2,&lca);
	return lca;
}

int main() {
	Node * root = NULL;
	root = newNode(20);
	root->left = newNode(8);
    root->right = newNode(22);
    root->left->left = newNode(4);
    root->left->right = newNode(12);
    root->left->right->left = newNode(10);
    root->left->right->right = newNode(14);
	return 0;
}
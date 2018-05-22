#include <iostream>
#include <queue>

using namespace std;

struct BST {
	int data;
	struct BST * left;
	struct BST * right;
};

struct qNode {
	int depth;
	struct BST * treeNode;
};

struct BST * ret_newNode(int data) {
	struct BST * new_node = new BST();
	new_node -> data = data;
	new_node -> right = NULL;
	new_node -> left = NULL;
	return new_node;
}

struct BST * insert_node(struct BST * root_ref, int data) {
	if (root_ref == NULL)//Check if the tree is initially empty
	{
		root_ref = ret_newNode(data);
	}
	else if (root_ref -> data >= data)//Go to the left tree
	{
		root_ref -> left = insert_node(root_ref -> left,data);
	}
	else //Go to the right tree
	{
		root_ref -> right = insert_node(root_ref -> right,data);
	}
	return root_ref;

}
void print_preorder(struct BST * root) {
	if (root == NULL)
		return;
	cout<<root->data<<" ";
	print_preorder(root->left);
	print_preorder(root->right);
}

void print_inorder(struct BST * root) {
	if (root == NULL)
		return;
	print_inorder(root -> left);
	cout<<root->data<<" ";
	print_inorder(root -> right);
}

void print_postorder(struct BST * root) {
	if (root == NULL)
		return;
	print_postorder(root->left);
	print_postorder(root->right);
	cout<<root->data<<" ";
}

struct BST * remove(struct BST * root, int key) {
	if (root == NULL)//If no key found
		return NULL;
	if (key < root -> data)
		root -> left = remove(root -> left,key);
	else if (key > root -> data)
		root -> right = remove(root -> right,key);
	else if (key == root -> data)
	{
		if (root -> left == NULL)//With one child or no child
			return root -> right;
		else if (root -> right == NULL)
			return root -> left;
		root -> data = root -> right -> data;
		root -> right = remove(root -> right,root -> data);
	}
	return root;
}

int find_min_depth(struct BST * root) {
	int left_depth,right_depth;
	if (root == NULL)//When the tree is empty
		return 0;
	if (!(root -> left) && !(root -> right))//Leaf node
		return 1;

	if (root -> left != NULL)
		left_depth = find_min_depth(root -> left) + 1;
	else
		left_depth = INT_MAX;

	if (root -> right != NULL)
		right_depth = find_min_depth(root -> right) + 1;
	else
		right_depth = INT_MAX;

	return (left_depth > right_depth) ? right_depth : left_depth;
}

int iterative_min_depth(struct BST * root) {
	if(root == NULL)//If tree is empty
		return 0;
	queue<qNode> q;//Create a queue with each tree node's depth and pointer to next tree node
	qNode node;
	node.depth = 1;
	node.treeNode = root;
	q.push(node);
	while(q.empty() == false) {
		node = q.front();//Save the front node
		q.pop();//Pop
		struct BST * current_node = node.treeNode;
		if ((current_node -> right == NULL) && (current_node -> left == NULL))//If the leaf node has been reached
			return node.depth;

		int depth = node.depth;

		if (current_node -> left != NULL)
		{
			qNode newLeft;
			newLeft.treeNode = current_node -> left;
			newLeft.depth = depth + 1;
			q.push(newLeft);
		}
		if (current_node -> right != NULL)
		{
			qNode newRight;
			newRight.treeNode = current_node -> right;
			newRight.depth = depth + 1;
			q.push(newRight);			
		}
	}
	return 0;

}

int MaxSum_util(struct BST * root,int * maxSum) {
	if (root == NULL)
		return 0;
	int leftPathSum = MaxSum_util(root -> left,maxSum);
	int rightPathSum = MaxSum_util(root -> right,maxSum);
	int temp = max(root -> data,max(leftPathSum,rightPathSum) + root -> data);
	int temp2 = max(temp,root -> data + rightPathSum + leftPathSum);
	*maxSum = max(temp2,*maxSum);
	return temp;
}

int MaxSumPath(struct BST * root) {
	int maxSum = 0;
	MaxSum_util(root,&maxSum);
	return maxSum;
}

void isPreOrder_util(int * arr, int i, int j, bool * val) {
	if (i > j)
		return;
	isPreOrder_util(arr,i+1,j-1,val);
	if (arr[i] > arr[j])
		*val = false;
}

bool isPreOrder(int * arr, int size) {
	int first = arr[0];
	int i = 0;
	int j;
	bool val1 = true,val2 = true;
	for (j = 1; j < size; j++) {//Finds the fist instance where the array element is greater than the first element
		if (arr[j] > first)
			break;
	}
	isPreOrder_util(arr,i,j,&val1);//Checks for the pre-order arrangement for the first half
	if (j != size - 1)//If there is any element in the second half
		isPreOrder_util(arr,j,size-1,&val2);
	return (val1 && val2);
}

int main() {
	int arr1[] = {40,30,35,20,80,100};
	int n = sizeof(arr1)/sizeof(int);
	bool result = isPreOrder(arr1,n);
	cout<<result<<endl;
	return 0;
}
void inorder(struct BST * root) {//Left Root Right
	if(root == NULL)
		return NULL;
	inorder(root -> left);
	cout<<root -> data<<" ";
	inorder(root -> right);
}
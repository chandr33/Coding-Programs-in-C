#include <stdio.h>
#include <stdlib.h>

struct Node {
	int data;
	struct Node * right;
	struct Node * down;
};

struct Node * newNode(int data) {
	struct Node * new = (struct Node *)malloc(sizeof(struct Node));
	new -> data = data;
	new -> right = NULL;
	new -> down = NULL;
	return new;
}
void pushDown(struct Node * head, int data) {
	struct Node * new = newNode(data);
	struct Node * head_ref = head;
	if (head_ref -> down == NULL)//List is empty
	{
		head_ref -> down = new;
	}
	else {
		while(head_ref -> down != NULL) {
			head_ref = head_ref -> down;
		}
		head_ref -> down = new;
	}

}

void print_list(struct Node * head) {
	struct Node * head_ref = head;
	while(head_ref != NULL) {
		printf("%d ", head_ref->data);
		head_ref = head_ref -> down;
	}
	printf("\n");
}

struct Node * flatten_util(struct Node * list1, struct Node * list2) {
	struct Node * result = NULL;
	if (list1 == NULL)
		return list2;
	if (list2 == NULL)
		return list1;
	if (list1 -> data < list2 -> data)
	{
		result = list1;
		result -> down = flatten_util(list1->down,list2);
	}
	else if (list1 -> data > list2 -> data) {
		result = list2;
		result -> down = flatten_util(list1,list2->down);
	}
	return result;
}

struct Node * flatten(struct Node * head) {
	struct Node * result;
	result = flatten_util(head,head -> right);
	if (head -> right != NULL)
	{
		//print_list(result);
		//print_list(head->right);
		print_list(head);
		flatten(head -> right);
	}
	print_list(head);
	return result;
}

int main() {
	struct Node * head1 = NULL;
	struct Node * head2 = NULL;
	struct Node * head3 = NULL;
	struct Node * head4 = NULL;
	head1 = newNode(5);
	pushDown(head1,7);
	pushDown(head1,8);
	pushDown(head1,30);
	head2 = newNode(10);
	pushDown(head2,20);
	head3 = newNode(19);
	pushDown(head3,22);
	pushDown(head3,50);
	head4 = newNode(28);
	pushDown(head4,35);
	pushDown(head4,40);
	pushDown(head4,45);
	head1 -> right = head2;
	head2 -> right = head3;
	head3 -> right = head4;
	head4 -> right = NULL;
	struct Node * result = flatten(head1);
	//print_list(result);
}
#include <stdio.h>
#include <stdlib.h>

struct Node {
	int data;
	struct Node * next;
};

struct Node * newNode(int data) {
	struct Node * result = (struct Node *)malloc(sizeof(struct Node));
	result -> next = NULL;
	result -> data = data;
	return result;
}

void push(struct Node ** top, int data) {
	struct Node * top_ref = *top;
	struct Node * new_node = newNode(data);
	new_node -> next = top_ref;
	top_ref = new_node;
}

int pop(struct Node ** top) {
	struct Node * top_ref = *top;
	struct Node * temp = top_ref;
	if (top_ref == NULL)
	{
		return -1;
	}
	else {
		temp = top_ref;
		top_ref = top_ref -> next;
	}
	return temp -> data;
}

int main() {
	struct Node * top = NULL;
	push(&top,1);
	push(&top,2);
	push(&top,3);
	//int deleted = pop(&top);
	printf("%d\n", top -> data);
	return 0;
}


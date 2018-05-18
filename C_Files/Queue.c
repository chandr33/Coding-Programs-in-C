#include <stdio.h>
#include <stdlib.h>

struct Node {
	int data;
	struct Node * next;
};

struct Queue {
	struct Node * front;
	struct Node * back;
};

struct Node * newNode(int data) {
	struct Node * new_node = (struct Node *)malloc(sizeof(struct Node));
	new_node -> data = data;
	new_node -> next = NULL;
	return new_node;
}

void addToQueue(struct Queue * q, int data) {
	struct Queue * q_ref = q;
	struct Node * new_node = newNode(data);
	if (q_ref -> front == NULL || q_ref -> back == NULL)
	{
		q_ref -> front = new_node;
		q_ref -> back = new_node;
	}
	else {
		q_ref -> back -> next = new_node;
		q_ref -> back = new_node;
	}
}

void printQueue(struct Queue ** q) {
	struct Queue * q_ref = *q;
	while(q_ref -> front != NULL) {
		printf("%d ", q_ref -> front -> data);
		q_ref -> front = q_ref -> front -> next;
	}
	printf("\n");
}

struct Node * deQueue(struct Queue * q) {
	if (q -> front == NULL)//Queue empty
		return NULL;
	struct Node * temp = q -> front;
	q -> front = q -> front -> next;
	if (q -> front == NULL) {//If list becomes empty
		q -> back = NULL;
	}
	return temp;
}

int main() {
	struct Queue * q = (struct Queue *)malloc(sizeof(struct Queue));
	q->front = NULL;
	q->back = NULL;
	addToQueue(q,1);
	addToQueue(q,2);
	addToQueue(q,3);
	addToQueue(q,4);
	printf("%d %d\n", q -> front -> data, q -> back -> data);
	//printQueue(&q);
	struct Node * deletedNode = deQueue(q);
	if (deletedNode != NULL)
		printf("Deleted node is : %d\n",deletedNode -> data);
	printQueue(&q);
	deQueue(q);
	return 0;
}
#include <stdio.h>
#include <stdlib.h>

//All Linked list add functions implemented

struct Node {
	int data;
	struct Node * next;
};
struct Node * head;//Pointer to the first element of the list

void add_front(int val) {//Add to the front of the list
	struct Node * newNode = malloc(sizeof(struct Node));
	newNode -> data = val;
	newNode -> next = head;
	head = newNode;
}

void add_end(int val) {//Append to the list
	struct Node * newNode = malloc(sizeof(struct Node));
	struct Node * ref = head;
	if (ref != NULL) {
		while(ref -> next != NULL){//Go to the penultimate end of the list
			ref = ref -> next;
		}
		newNode -> data = val;
		newNode -> next = ref -> next;
		ref -> next = newNode;
	}
	else
		add_front(val); 
}

int search(int num) {
	struct Node * ref = head;
	int found = 0;
	int count = 0;
	while ((found == 0) && (ref != NULL)){
		if(ref -> data == num) {
			found = 1;
		}
		ref = ref -> next;
		count++;
	}
	if (found)
		return count-1;
	return -1;
}

void insert_after(int num_in_list,int val) {
	int index = search(num_in_list);
	struct Node * ref = head;
	if (index != -1) {
		int i = 0;
		while(i != index){//Move the pointer where new Node has to be inserted
			ref = ref->next;
			i++;
		}
		struct Node * newNode = malloc(sizeof(struct Node));
		newNode -> data = val;
		newNode -> next = ref -> next;
		ref->next = newNode;
	}
	else
		printf("Node not found\n");
}

void delete(int val) {
	int index = search(val);
	struct Node * ref = head;
	if (index != -1) {
		if (index != 0) {
			int i = 0;
			while (i != index - 1) {
				ref = ref -> next;
				i++;
			}
			ref -> next = ref -> next -> next;
		}
		else {//Deleting first element
			ref = ref -> next;
			head = ref;
		}
	}
}

void print_list() {
    struct Node * ref = head;
    while(ref != NULL) {
        printf("%d ", ref -> data);
        ref = ref -> next;
    }
    if (ref == NULL)
    	printf("\n");
}

int main() {
	head = NULL;
	add_end(1);
	add_end(2);
	add_end(3);
	add_end(4);
	add_end(5);
	add_end(6);
	add_end(7);
	insert_after(7,8);
	print_list();
	delete(5);
	delete(1);
	delete(9);
	print_list();
}
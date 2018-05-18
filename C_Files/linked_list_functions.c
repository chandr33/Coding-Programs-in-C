#include <stdio.h>
#include <stdlib.h>

//Linked list add functions implemented
//Linked list delete function also implemented
//Un-optimized bubble sort done
//Recursive functions done

struct Node {
	int data;
	struct Node * next;
};
struct Node * head;//Pointer to the first element of the list

void add_front(int val) {//Add to the front of the list
	struct Node * newNode = (struct Node*)malloc(sizeof(struct Node));
	newNode -> data = val;
	newNode -> next = head;
	head = newNode;
}

void add_end(int val) {//Append to the list
	struct Node * newNode = (struct Node *)malloc(sizeof(struct Node));
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
		struct Node * newNode = (struct Node *)malloc(sizeof(struct Node));
		newNode -> data = val;
		newNode -> next = ref -> next;
		ref->next = newNode;
	}
	else
		printf("Node not found\n");
}

void delete_node(int val) {
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

void delete_greater(int x) {
	struct Node * ref = head;
	while (ref != NULL) {
		if (ref -> data > x) {
			delete_node(ref->data);
		}
		ref = ref -> next;
	}
}

void sort_bubble() {
	struct Node * i;
	struct Node * j;
	int flag = 0;
	for (i=head;i->next!=NULL;i=i->next) {
		for (j=i->next;j!=NULL;j=j->next){
			if(i->data > j->data) {
				int temp = i->data;
				i->data = j->data;
				j->data = temp;
				flag = 1;
			}
		}
		if (flag == 0) {
			break;
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

void print_reverse(struct Node * p) {
	if (p == NULL)
		return;
	print_reverse(p -> next);
	printf("%d ", p->data);
}

void reverse_list(struct Node * p) {
	if (p -> next == NULL) {
		head = p;
		return;
	}
	reverse_list(p -> next);
	struct Node * q = p -> next;
	q -> next = p;
	p -> next = NULL;
	//return;
}

struct Node * delete_ith_node(struct Node * p,int i) {
	struct Node * temp;
	if (i == 1) {
		temp = p->next;
		return temp;
	}
	//printf("%d\n", p -> next -> data);
	p -> next = delete_ith_node(p->next,i-1);
	printf("%d\n", p -> next -> data);
	return p;
}

struct Node * create_new(int data) {
	struct Node * newNode = (struct Node*)malloc(sizeof(struct Node));
	newNode -> data = data;
	newNode -> next = NULL;	
	return newNode;
}

struct Node * recursive_add(struct Node *p,int data) {
	if (p == NULL) {
		return create_new(data);
	}
	else
		p -> next = recursive_add(p -> next,data);
	return p;
}

void delete_recursive(struct Node * p) {
	if (p == NULL)
	{
		head = p;
		return;
	}
	delete_recursive(p->next);
}

void print_alternate(struct Node * p, int flag) {
	if (p == NULL)
		return;
	if (flag % 2 == 0)
		printf("%d ", p -> data);
	flag++;
	print_alternate(p->next,flag);
}

void moveNode(struct Node * m, struct Node * n) {
	if ((m == NULL) || (n == NULL))
	{
		return;
	}
	if (m -> next != NULL)
		m -> next = m->next->next;
	if (n -> next != NULL)
		n -> next = n->next->next;
	moveNode(m->next,n->next);
}

void split_alternative(struct Node * p, struct Node ** a, struct Node ** b) {
	*a = p;
	*b = p->next;
	moveNode(*a,*b);
}

void moveNode_iterative(struct Node * m, struct Node * n) {
	while((m != NULL) && (n != NULL)) {
		if (m -> next != NULL)
			m -> next = m -> next -> next;
		if (n -> next != NULL)
			n -> next = n -> next -> next;
		m = m->next;
		n = n->next;
	}
}

void iterative_split_alternative(struct Node * p, struct Node ** a,struct Node ** b) {
	*a = p;
	*b = p -> next;
	moveNode_iterative(*a,*b);
}

void find_mid(struct Node * head, int * n, struct Node ** result) {
	if (head == NULL){
		*n = (*n)/2;//Using n as pointer to reflect the updated value after the function returns
		return;
	}
	*n = *n + 1;
	find_mid(head->next,n,result);
	*n = *n - 1;
	if(*n == 0) {
		*result = head;
	}
}

struct Node * midpoint(struct Node * p) {
	struct Node * mid = NULL;
	int n = 1;
	find_mid(p,&n,&mid);
	return mid;
}

int main() {
	head = NULL;
	add_end(0);
	add_end(1);
	add_end(2);
	add_end(3);
	add_end(4);
	add_end(5);
	add_end(6);
	add_end(7);
	//add_end(8);
	//insert_after(8,9);
	struct Node * list_head1 = NULL;
	struct Node * list_head2 = NULL;
	struct Node * list_head3 = NULL;
	struct Node * list_head4 = NULL;
	//print_list();
	//delete(5);
	//delete(1);
	//delete(8);
	//print_list();
	//delete_greater(3);
	//sort_bubble();
	//print_list();
	//reverse_list(head);
	delete_ith_node(head,4);
	//recursive_add(head,10);
	//delete_recursive(head);
	print_list();
	//print_alternate(head,0);
	//split_alternative(head,&list_head1,&list_head2);
	//iterative_split_alternative(head,&list_head3,&list_head4);
	//printf("%d %d %d\n", head->data, list_head1->next->data, list_head2->next->data);
	//reverse_list(head);
	//print_reverse(list_head3);
	//struct Node * mid = midpoint(head);
	//printf("%d\n", mid -> data);
	//printf("%d\n", head -> next->next->data);
}
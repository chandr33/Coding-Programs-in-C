#include <stdio.h>
#include <stdlib.h>
#include <time.h>

struct Node {
	int data;
	struct Node * next;
	int key;
};

void insertNode(struct Node ** head_ref, int data) {
	struct Node * newNode = (struct Node *)malloc(sizeof(struct Node));
	newNode -> next = *head_ref;
	newNode -> data = data;
	*head_ref = newNode;
}

struct Node * newNode(int data) {
	struct Node * newNode = (struct Node *)malloc(sizeof(struct Node));
	newNode -> data = data;
	newNode -> next = NULL;
	return newNode;
}
void print_list(struct Node * head) {
	while(head != NULL) {
		printf("%d ", head -> data);
		head = head -> next;
	}
	printf("\n");
}

void insertSorted(struct Node ** head_ref, struct Node * newNode) {
	struct Node * i = * head_ref;
	int found = 0;
	while ((!found) && (i -> next != NULL)) {
		if ((i -> data < newNode -> data) && (i -> next -> data > newNode -> data))
		{
			found = 1;
			break;
		}
		i = i -> next;
	}
	if (found) {
		newNode -> next = i -> next;
		i -> next = newNode;
	}
	else
		i -> next = newNode;
}

void deleteFunction(struct Node * startNode, struct Node * deleteNode) {
	if (startNode -> data == deleteNode -> data)//first node to be deleted
	{
		startNode -> data = startNode -> next -> data;
		startNode -> next = startNode -> next -> next;
	}
	else {
		if (startNode != NULL)// If list not empty
		{
			while (startNode -> next != NULL) {//stop at penultimate node
				if (startNode -> next -> data == deleteNode -> data) {
					startNode -> next = startNode -> next -> next;
					break;
				}
				startNode = startNode -> next;
			}
		}
	}
}

void rotate(struct Node ** head_ref, int k) {
	struct Node * current = *head_ref;
	struct Node * head = *head_ref;
	int flag = 0;
	while (k != 0) {
		while (!flag) {
			if (current -> next -> next == NULL)
			{
				current -> next -> next = head;
				head = current -> next;
				current -> next = NULL;
				flag = 1;
			}
			if (!flag) {
				current = current -> next;
			}
		}
		flag = 0;
		current = head;
		k--;
	}
	*head_ref = head;
}

void list_reverse_util(struct Node ** p, struct Node * head) {
	if (head -> next == NULL)
	{
		*p = head;
		return;
	}
	list_reverse_util(p,head -> next);
	struct Node * q = head -> next;
	q -> next = head;
	head -> next = NULL;
}

struct Node * list_reverse(struct Node * head) {
	struct Node * p = head;
	list_reverse_util(&p,head);
	return p;
}
//Function to fund the length of linked list
int size(struct Node * list) {
	int result = 0;
	while (list != NULL) {
		result = result + 1;
		list = list -> next;
	}
	return result;
}
//Utility function for sum_list
struct Node * addSameSize(struct Node * list1, struct Node * list2, int * carry) {
	if (list1 == NULL)
	{
		return NULL;
	}
	addSameSize(list1->next,list2->next,carry);
	list1 -> data = list1 -> data + list2 -> data + *carry;
	if (list1 -> data >= 10)
	{
		*carry = 1;
		list1 -> data = list1 -> data % 10;
	}
	return list1;

}
//Utility function for sum_list
void addCarryToRemaining(struct Node ** result,struct Node * list,struct Node * current, int * carry) {
	if (list == current)
	{
		return;
	}
	addCarryToRemaining(result,list->next,current,carry);
	int sum = list -> data + *carry;
	if (sum >= 10)
	{
		insertNode(result,sum%10);
		*carry = sum / 10;
	}
	else {
		insertNode(result,sum);
		*carry = 0;
	}
	return;
}
//Function to add two given numbers
struct Node * sum_list(struct Node * list1, struct Node * list2) {
	int len1 = size(list1);
	int len2 = size(list2);
	struct Node * head1 = list1;
	struct Node * head2 = list2;

	int difference;
	struct Node * result = NULL;

	int carry  = 0;

	if (len1 == len2)
	{
		result = addSameSize(head1,head2,&carry);
	}
	else {
		if (len1 > len2) {
			difference = len1 - len2;
			while(difference != 0) {
				head1 = head1 -> next;
				difference = difference - 1;
			}
			result = addSameSize(head1,head2, &carry); // TODO - Add the carry to the remaining nodes
			addCarryToRemaining(&result,list1,head1,&carry);
		}
		else {
			difference = len2 - len1;
			while(difference != 0) {
				head2 = head2 -> next;
				difference = difference - 1;
			}
			result = addSameSize(head1,head2, &carry); 
			addCarryToRemaining(&result,list2,head2,&carry);
		}
	}
	if (carry)
	{
		insertNode(&result,1);
	}

	return result;

}
//Function to merge two lists at alternate positions 
void merge(struct Node * head1, struct Node ** head2) {
	struct Node * head2_copy = *head2;
	struct Node * head1_copy = head1;
	int flag = 0;
	while((head1_copy != NULL) && (head2_copy != NULL)) {
		if (flag % 2 == 0)
		{
			head1_copy = head1_copy -> next;
			head1 -> next = head2_copy;
			head1 = head1 -> next;
			flag = flag + 1;
		}
		else {
			head2_copy = head2_copy -> next;
			head1 -> next = head1_copy;
			head1 = head1 -> next;
			flag = flag + 1;
		}
	}
	*head2 = head1 -> next;
	head1 -> next = NULL;

}
//Function to reverse list in k groups
struct Node * group_reverse(struct Node * list,int k) {
	struct Node * current = list;
	struct Node * next = NULL;
	struct Node * prev = NULL;
	int count = 0;
	while((current != NULL) && (count < k)) {
		next = current -> next;
		current -> next = prev;
		prev = current;
		current = next;
		count = count + 1;
	}
	if (next != NULL)
	{
		list -> next = group_reverse(next,k);
		printf("%d \n", list -> data);
	}
	printf("%d \n", prev -> data);
	return prev;
}

void util_NthFromLast(struct Node * head,int * n, int * result) {
	if (head == NULL)
	{
		return ;
	}
	util_NthFromLast(head->next,n,result);
	*n = *n - 1;
	if (*n == 0)
	{
		*result = head -> data;
	}
}


//Function to get the Nth node from End
int getNthFromLast(struct Node * list, int n) {
	int result = 0;
	util_NthFromLast(list,&n,&result);
	return result;
}
void swap(struct Node ** head1, struct Node ** head2) {
	struct Node * temp = NULL;
	temp = *head1;
	*head1 = *head2;
	*head2 = temp;
}

int intersectionPoint(struct Node * head1,struct Node * head2) {
	int len1 = size(head1);
	int len2 = size(head2);
	int set_swap = 0;
	int found = 0;
	if (len2 > len1)
	{
		swap(&head1,&head2);
		set_swap = 1;
	}
	while(len1 != len2) {
		head1 = head1 -> next;
		if(set_swap)
			len2 = len2 - 1;
		else
			len1 = len1 - 1;
	}
	if(head1 == head2)
		found = 1;
	while((head1 != head2) && (head1 != NULL)) {
		if((!found) &&(head1 -> next != NULL)){
			head1 = head1 -> next;
			head2 = head2 -> next;
		}
		if (head1 == head2)
		{
			found = 1;
			break;
		}
		if ((!found) && (head1 -> next == NULL))
		{
			head1 = head1 -> next;
			head2 = head2 -> next;
		}
	}
	if(found)
		return head1 -> data;
	return -1;
}

void pairWiseSwap(struct Node * head) {
	struct Node * current = head;
	struct Node * next = current -> next;
	int count = 0;
	while(next != NULL) {
		if (count % 2 == 0)
		{
			int temp = current->data;
			current -> data = next -> data;
			next -> data = temp;
			current = next;
			next = next -> next;
		}
		else {
			current = next;
			next = next -> next;
		}
		count = count + 1;

	}

}
void reverse_util(struct Node ** result,struct Node * head) {
	if (head -> next == NULL)
	{
		*result = head;
		return;	
	}
	reverse_util(result,head -> next);
	struct Node * q = head -> next;
	q -> next = head;
	head -> next = NULL;
}

bool isPalindrome(struct Node * head) {
	struct Node * current = head;
	struct Node * reverse = NULL;
	reverse_util(&reverse,head);
	while(current != NULL) {
		if (current -> data == reverse -> data)
		{
			current = current -> next;
			reverse = reverse -> next;
		}
		else
			return false;
	}
	return true;
}
int search(struct Node * head,int data) {
	int found = 0;
	while (head != NULL) {
		if (head -> data == data)
		{
			found = 1;
			break;
		}
		head = head -> next;
	}
	return found;
}

struct Node * unionList(struct Node * head1, struct Node * head2) {
	struct Node * result = NULL;
	while(head1 != NULL) {
		insertNode(&result,head1 -> data);
		head1 = head1 -> next;
	}
	while(head2 != NULL) {
		if(search(result,head2->data)) {
			head2 = head2 -> next;
		}
		else {
			insertNode(&result,head2->data);
			head2 = head2 -> next;
		}
	}
	return result;
}

struct Node * intersection(struct Node * list1, struct Node * list2) {
	struct Node * result = NULL;
	while((list1 != NULL) && (list2 != NULL)) {
		if (search(list2,list1 -> data))
		{
			insertNode(&result,list1->data);
		}
		list1 = list1 -> next;
	}
		
	return result;
}

//Function to remove loop using Floyd's Detection Algorithm
void detectAndremoveLoop(struct Node * head) {
	struct Node * slowPtr = head;
	struct Node * fastPtr = head;
	struct Node * prev = NULL;
	prev = slowPtr;
	slowPtr = slowPtr -> next;
	fastPtr = fastPtr -> next -> next;
	while(fastPtr != NULL && fastPtr -> next != NULL) {//Accounting for both odd and even length lists without loops
		if (slowPtr == fastPtr)
			break;
		prev = slowPtr;
		slowPtr = slowPtr -> next;
		fastPtr = fastPtr -> next -> next;
	}
	if ((slowPtr == fastPtr) && (slowPtr != head))
	{
		slowPtr = head;
		while(slowPtr -> next != fastPtr -> next) {
			slowPtr = slowPtr -> next;
			fastPtr = fastPtr -> next;
		}
		fastPtr -> next = NULL;
	}
	else
		prev -> next = NULL;
}
void find_mid(struct Node * head, int * n, struct Node ** result) {
	if (head == NULL)
	{
		*n = *n / 2;
		return;
	}
	*n = *n + 1;
	find_mid(head -> next,n,result);
	*n = *n - 1;
	if (*n == 0)
	{
		*result = head;
	}
}
struct Node * sortMerge_util(struct Node * a, struct Node * b) {
	struct Node * result = NULL;
	if (a == NULL)
		return b;
	if (b == NULL)
		return a;
	if (a -> data < b -> data)
	{
		result = a;
		result -> next = sortMerge_util(a -> next,b);
	}
	else if (a -> data > b -> data)
	{
		result = b;
		result -> next = sortMerge_util(a,b -> next);
	}
	return result;
}

void split(struct Node * head,struct Node ** a,struct Node ** b) {
	struct Node * current = head;
	struct Node * mid = NULL;
	int n = 0;
	find_mid(head,&n,&mid);
	while(current -> next != mid) {
		current = current -> next;
	}
	current -> next = NULL;
	*a = head;
	*b = mid;
}

void sortMerge(struct Node ** head_ref) {
	struct Node * head = *head_ref;
	if (head -> next == NULL) // When there is only one element in the list
	{
		return;
	}
	struct Node * a = NULL;
	struct Node * b = NULL;
	split(head,&a,&b);
	sortMerge(&a);
	sortMerge(&b);
	*head_ref = sortMerge_util(a,b);
}

void printRandom(struct Node * head) {
	struct Node * current = head;
	int i;
	time_t t;
	srand((unsigned) time(&t));
	for (int i = 1; current != NULL ; i++)
	{
		if (rand()%i == current -> key)
			printf("%d\n", current -> data);
		current = current -> next;
	}
}

int main() {
	struct Node * head = newNode(50);
	head -> key = 1;
	head -> next = newNode(20);
	head -> next -> key = 2;
	head -> next -> next = newNode(15);
	head -> next -> next -> key = 3;
	head -> next -> next -> next = newNode(9);
	head -> next -> next -> next -> key = 4;
	head -> next -> next -> next -> next = newNode(10);
	head -> next -> next -> next -> next -> key = 5;
	head -> next -> next -> next -> next -> next = newNode(11);
	head -> next -> next -> next -> next -> next -> key = 6;
	head -> next -> next -> next -> next -> next -> next = NULL;
	sortMerge(&head);
	//print_list(head);
	printRandom(head);
	return 0;
}
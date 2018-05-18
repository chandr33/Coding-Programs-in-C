#include <stdio.h>
#include <stdlib.h>

struct Node {
	char c;
	struct Node * next;
};

struct Node * newNode(char c){
	struct Node * newNode = (struct Node *)malloc(sizeof(struct Node));
	newNode -> next = NULL;
	newNode -> c = c;
	return newNode; 
}

int compare(struct Node * list1, struct Node * list2) {
	int result;
	while((list1 != NULL) && (list2 != NULL)) {
		if (list1 -> c == list2 -> c)
		{
			result = 0;
		}
		else if (list1 -> c < list2 -> c)
		{
			result = -1;
		}
		else if (list1 -> c > list2 -> c)
		{
			result = 1;
		}
		list1 = list1 -> next;
		list2 = list2 -> next;
	}
	if (list1 != NULL)
	{
		result = 1;
	}

	if (list2 != NULL)
	{
		result = -1;
	}
	return result;
}

int main() {
    struct Node *list1 = newNode('g');
    list1->next = newNode('e');
    list1->next->next = newNode('e');
    list1->next->next->next = newNode('k');
    list1->next->next->next->next = newNode('s');
    list1->next->next->next->next->next = newNode('a');

    struct Node *list2 = newNode('g');
    list2->next = newNode('e');
    list2->next->next = newNode('e');
    list2->next->next->next = newNode('k');
    list2->next->next->next->next = newNode('s');
    list2->next->next->next->next->next = newNode('b');

    int output = compare(list1,list2);
    printf("%d\n", output);

}
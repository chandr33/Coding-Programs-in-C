#include <iostream>

using namespace std;

typedef struct ListNode
{
	int data;
	ListNode * both; //Pointer to XOR of previous and next nodes
	ListNode(int data) : data(data), both(NULL) {}
}ListNode;

ListNode * head = NULL; //Pointer to the head Node : Initially empty

void addNode(ListNode * new_node);
ListNode * getNode(int index);

//Returns the XORed address of both the pointers
ListNode * XOR_Helper(ListNode * l1, ListNode * l2) {
	return (ListNode *)((uintptr_t)l1 ^ (uintptr_t)l2);
}

//Adds Node to the end of list
void addNode(ListNode * new_node) {
	//If List is empty
	if (!head) {
		new_node -> both = XOR_Helper(head,NULL);
		head = new_node;
	}
	else //If list is not empty
	{
		ListNode * current = head;
		ListNode * previous = NULL;
		ListNode * next;
		while (current->both) {
			
			next = XOR_Helper(previous,current->both);

			//cout<<"Next Data: "<<next->data<<endl;
			previous = current;
			//cout<<"Previous Data: "<<previous->data<<endl;
			current = next;
			//cout<<"Current Data: "<<current->data<<endl;
			cout<<"Entered"<<endl;
		}
		current->both = XOR_Helper(previous,new_node);
		new_node->both = XOR_Helper(current,NULL);
	}
}

ListNode * getNode(int index) {
	int i = 0;
	ListNode * current = head;
	ListNode * previous = NULL;
	ListNode * next;
	while (i < index && current) {
		next = XOR_Helper(previous,current->both);
		previous = current;
		current = next;
		i++;
	}
	return current;
}

int main(int argc, char const *argv[])
{
	addNode(new ListNode(1));
	addNode(new ListNode(2));
	addNode(new ListNode(3));
	/*addNode(new ListNode(4));*/
	//cout<<getNode(1)->data<<endl;
	return 0;
}
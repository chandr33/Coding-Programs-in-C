#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* 
This is an implementation of Hash Tables using an array of size specified by the user. 
Each array index will contain the pointer to the head of the linked list. 
Each linked list will in turn contain the key:value pair and the pointer 
to the next node of the linked list. Typically the hashing function will 
take in a string and compute the hash code by summing up the ASCII values 
of the characters and then returning the ramainder(Hash Code) by taking the
modulo sum%ArraySize. This concept implements chaining algorithm to take care
od potential collisions.
*/


typedef struct ListNode {//This is a Node of the linked list containing the key value pair
	char * key;
	long value;
	struct ListNode * next;
} Node;

Node * newNode(char * key, long value) {
	Node * new_node = (Node *)malloc(sizeof(Node));
	new_node -> value = value;
	new_node -> key = (char*)malloc(sizeof(char)*(strlen(key)+1));
	strcpy(new_node -> key,key);
	new_node -> next = NULL;
	return new_node;
}

int hashFunction(char * key) { //This function will return the hash code (ASCII Sum of the literals)
	int sum = 0;
	char c;
	int i = 0;
	while (i < sizeof(key)) {
		c = key[i];			
		sum = sum + c;
		i++;
	}
	return sum;
}

void insertNode(Node ** arr, Node * node, int index) {
	if (arr[index] == NULL)//If the array element doesn't have any struct stored
		arr[index] = node; //Store the new struct
	else {//If there are structs from before then implement chaining and add the new struct to the end of list
		while (arr[index] -> next != NULL)
			arr[index] = arr[index] -> next;
		arr[index] -> next = node;
	}
}

int Table_Lookup(Node ** arr, char * key, int index, long * value) {//Function which searches for the corresponding value
	int found = 0;
	while ((!found) && (arr[index] != NULL)) {
		if (strcmp(arr[index] -> key,key) == 0) {
			*value = arr[index] -> value;
			found = 1;
		}
		arr[index] = arr[index] -> next;
	}
	return found;
}

int main(int argc, char ** argv) {
	int size;
	printf("Enter the size of the Hash Table : ");
	scanf("%d",&size);
	Node * array[size];//Array of Node pointers
	for (int i = 0; i < size; i++) {//Initialize all the array indices to NULL
		array[i] = NULL;
	}
	char key[50];//key
	long value;//Corresponding value
	int hashCode,index;
	Node * new_node;
	char option[10];

	int count = 0;
	while (count < size) {
		hashCode = 0;
		index = 0;
		printf("Enter the %d key : ", count+1);
		scanf("%s",key);
		printf("Enter the Corresponding Value(DOB) : ");
		scanf("%ld",&value);
		new_node = newNode(key,value);
		hashCode = hashFunction(key);
		index = hashCode%size;
		insertNode(array,new_node,index);
		count = count + 1;
	}
	printf("---------------------------DONE INSERTING IN THE TABLE---------------------------\n");
	printf("Do you want to search ? [Y/N] : ");
	scanf("%s",option);
	if (strcmp(option,"Y") == 0) {//Re-use all the variables from above and search for the key
		memset(key,0,size*sizeof(key[0]));
		value = 0;
		hashCode = 0;
		index = 0;
		printf("Enter the key : ");
		scanf("%s",key);
		hashCode = hashFunction(key);
		index = hashCode%size;
		if (Table_Lookup(array,key,index,&value))
			printf("The Value to Your Key is : %ld\n", value);
		else
			printf("The key that you entered could not be located in the table\n");
	}
	else
		exit(1);

 return(0);

}
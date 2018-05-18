#include <stdio.h>
#include <stdlib.h>

//Struct Node which has the data and the pointer to the node
struct list_node{
    int data;
    struct list_node * next;
};
struct list_node * head; //Pointer to the first element of the list

void print() {
    struct list_node * ref = head;
    while(ref != NULL) {
        printf("%d  ", ref -> data);
        ref = ref -> next;
    }
}
int main() {
    struct list_node * first = malloc(sizeof(struct list_node)); //Allocating memory to all the nodes of the list
    struct list_node * second = malloc(sizeof(struct list_node));
    struct list_node * third = malloc(sizeof(struct list_node));
    first -> data = 1;
    second -> data = 2;
    third -> data = 3;
    first -> next = second;
    second -> next = third;
    third -> next = NULL;
    head = first;
    print();
}
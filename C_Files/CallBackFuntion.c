#include <stdio.h>
#include <stdlib.h>

void A() {
	printf("Hello World\n");
}

void B(void (*ptr) ()) {//Function pointer as argument
	//(*ptr) ();
	ptr();//Call-back the function that ptr points to
}

//In this function the two elements to be compared are being passed through reference: Their addresses are being passed
int compare(const void * a, const void * b) {//This function will compare two integers
	int A = *((int*) a);//This will first typecast 'a' (void ptr) to int ptr and then de-reference it to get the value
	int B = *((int*) b);//Typecasting b to int* and getting the value
	return A-B;//Rank an integer value with higher rank as high (to sort in an ascending order)
}

int main() {
	void (*p) () = A;//P points to a function(function pointer) that returns a void and takes no arguments
	B(p);														  //p is initialized with the address of A
	B(A);//Name of a function also returns a pointer to that function; Here A itself is a pointer to A
		//Here A is the callBack Function as it can be called back by B
	int i,arr[] = {-31,2,3,99,0,11};
	qsort(&arr[0],6,sizeof(int),compare);//Here last argument is a function pointer ,i.e. This is a callBack function(qsort)
	for(i=0;i<6;i++) printf("%d ", arr[i]);

}

//Note :- int (*p) (int,int) - Here p is a function pointer that points to a function which returns an int and takes in two int arguments
//Whereas int *p (int,int) - Here p is a function which returns an int and takes in two int arguments 
#include <stdio.h>
#include <stdlib.h>

int missingNumber(int * array,int size) {
	int num;
	int i = 0;
	int found = 0;
	for (int i = 0; i < size-1; i++)
	{
		if (array[0] != 1) {
			num = 1;
			found = 1;
			break;
		}
		else {
			if ((array[i+1] - array[i]) == 2)
			{
				num = array[i]+1;
				found = 1;
				break;
			}
		}
	}
	if ((array[i+1] != size + 1) && (!found))
	{
		num = size + 1;
	}
	return num;
}

int main() {
	int arr[5] = {1,2,3,4,6};
	int size = sizeof(arr)/sizeof(int);
	int result = missingNumber(arr,size);
	printf("%d\n", result);
	return 0;
}
package ArraysAndStrings;

public class PancakeSort {
	
	static int[] sort(int arr[]) {
		int startIndex = 0;
		int endIndex = 0;
		while (startIndex != arr.length-2) {
			if (arr[startIndex] > arr[startIndex+1]) {
				endIndex = startIndex+1;
				while (endIndex <= arr.length-1) {
					if (arr[endIndex] > arr[startIndex]) {
						break;
					}
					endIndex++;
				}
				flip(arr,startIndex,endIndex-1);			
				startIndex = 0;
			}
			else
				startIndex++;
		}
		return arr;
	}
	
	static void flip(int arr[], int startIndex, int endIndex)  {
		while (startIndex < endIndex) {
			int temp = arr[startIndex];
			arr[startIndex] = arr[endIndex];
			arr[endIndex] = temp;
			startIndex++;
			endIndex--;
		}
	}
	
	static void printArr(int arr[]) {
		for (int i : arr)
			System.out.print(i+" ");
		System.out.println();
	}
	public static void main(String[] args) {
		int arr[] = {4,3,2,1};
		printArr(sort(arr));
	}
}

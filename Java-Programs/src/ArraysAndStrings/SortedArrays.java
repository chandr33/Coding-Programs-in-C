package ArraysAndStrings;
import java.util.*;
public class SortedArrays {
	static void ListSorted (int arr1[], int arr2[]) {
		int even = 0;
		int startIndexArr1 = 0;
		int startIndexArr2 = 0;
		int index = 0;
		List<Integer> current = new ArrayList<>();
		ListSorted_util(arr1, arr2, even, startIndexArr1, startIndexArr2, current, index);
	}
	
	static void ListSorted_util(int arr1[], int arr2[], int even, int index1, int index2, List<Integer> current, int lastIndex) {
		
		if (!current.isEmpty() && lastIndex % 2 == 0) {
			printList(current, lastIndex);
		}
		
		if (even % 2 == 0) {//Choose from the first sorted array			
			for (int i = index1; i < arr1.length; i++) { //Loop through all the elements in the first array
				if (lastIndex == 0) {
					current.add(lastIndex,arr1[i]);
					ListSorted_util(arr1, arr2, even+1, i+1, index2, current, lastIndex+1);
				}
				else {
					if (arr1[i] > current.get(lastIndex-1)) {
						current.add(lastIndex,arr1[i]);
						ListSorted_util(arr1, arr2, even+1, i+1, index2, current, lastIndex+1);
					}
				}
			}
		}
		else {//Choose from the second sorted array
			for (int j = index2; j < arr2.length; j++) {
				if (arr2[j] > current.get(lastIndex-1)) {
					current.add(lastIndex,arr2[j]);
					ListSorted_util(arr1, arr2, even+1, index1, j+1, current, lastIndex+1);
				}
			}
		}
	}

	static void printList(List<Integer> current, int lastIndex) {
		for (int i = 0; i < lastIndex; i++) {
			System.out.print(current.get(i) + " ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		int arr1[] = {10,15,25};
		int arr2[] = {1,5,20,30};
		ListSorted(arr1, arr2);
	}
}

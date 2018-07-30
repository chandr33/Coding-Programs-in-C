package DynamicProgramming;
import java.util.*;
public class LongestIncreasingSubsequence {
	
	public static int lengthSequence(int arr[]) {
		int index = arr.length - 1;
		int current_num = arr[index];
		HashMap<String,Integer> table = new HashMap<>();
		int count = lengthSequence_util(arr, index, current_num, table);
		return count;
	}
	
	public static int lengthSequence_util(int arr[], int index, int current_num, HashMap<String,Integer> table) {
		String key = Integer.toString(current_num) + ":" + Integer.toString(arr[index]);
		if (table.containsKey(key))
			return table.get(key);
		if (index ==  0)
			return 1;
		if (current_num > arr[index - 1]) {
			table.put(key,lengthSequence_util(arr, index - 1, arr[index - 1], table));
			return 1 + table.get(key);
		}
		table.put(key,Math.max(lengthSequence_util(arr, index - 1, arr[index - 1], table), lengthSequence_util(arr, index - 1, arr[index], table)));
		return table.get(key);
	}
	
	public static int lengthSequenceTable(int arr[]) {
		int longestSequenceArr[] = new int[arr.length];
		Arrays.fill(longestSequenceArr, 1);//Initially fill all the values with the least length of a subsequence ,i.e. 1
		if (arr.length == 1)
			return 1;
		for (int i = 1; i < longestSequenceArr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j])
					longestSequenceArr[i] = Math.max(longestSequenceArr[i], longestSequenceArr[j] + 1);
			}
		}
		int max = 0;
		for (int i = 1; i < longestSequenceArr.length; i++) {
			if (longestSequenceArr[i] > longestSequenceArr[max])
				max = i;
		}
		
		return longestSequenceArr[max];
	}
	public static void main(String[] args) {
		int arr[] = new int[] {10,22,9,33,21,50,41,60};
		System.out.println(lengthSequenceTable(arr));
	}
}

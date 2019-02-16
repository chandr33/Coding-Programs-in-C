package DynamicProgramming;
import java.util.*;
public class MatrixChainMultiply {
	
	static class Pair {
		int numOperations;
		int resultDimensions;
	}
	
	static int matrixMultiply_Recursive(int arr[]) {
		int i = 1;
		int j = arr.length-1;
		HashMap<String,Integer> table = new HashMap<String,Integer>();
		return matrixMultiply_util(arr, i, j, table);
	}
	
	static int matrixMultiply_util(int arr[], int i, int j, HashMap<String,Integer> table) {
		String key = Integer.toString(i) + ":" + Integer.toString(j);
		
		if (table.containsKey(key))
			return table.get(key);
		
		if (i == j)
			return 0;
		
		int minCountOperations = Integer.MAX_VALUE;
		int countOperations = 0;
		
		for (int k = i; k < j; k++) {//This runs j-i times
			countOperations = matrixMultiply_util(arr, i, k, table) + matrixMultiply_util(arr, k+1, j, table) + arr[i-1]*arr[k]*arr[j];
			minCountOperations = Math.min(minCountOperations, countOperations);
			table.put(key, minCountOperations);
		}
		return table.get(key);
	}
	
	static int matrixMultiply_table(int arr[]) {
		int table[][] = new int[arr.length-1][arr.length-1];
		for (int i = 0; i < table.length; i++) {
			table[i][i] = 0;
		}
		int countNumber = 0;
		for (int i = 2; i <= table.length; i++) {
			for (int j = 0; j <= table.length-i; j++) {
				int gap = i+j-1;
				table[j][gap] = Integer.MAX_VALUE;
				for (int k = j; k < gap; k++) {
					countNumber = table[j][k] + table[k+1][gap] + arr[gap+1]*arr[j]*arr[k+1];
					if (countNumber < table[j][gap])
						table[j][gap] = countNumber;
				}
			}
		}

		return table[0][table.length-1];
	}
	
	public static void main(String[] args) {
		int arr[] = {40,20,30,10,30};
		//System.out.println(matrixMultiply_table(arr));
		System.out.println(matrixMultiply_Recursive(arr));
	}
}

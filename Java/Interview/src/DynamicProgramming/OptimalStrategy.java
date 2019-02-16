package DynamicProgramming;

import java.util.*;

public class OptimalStrategy {
	
	static class Pair<U, V> {
		private U u;
		private V v;
		U getFirst() { return u; }
		V getSecond() { return v; }
		void setFirst(U u) { this.u = u; }
		void setSecond(V v) { this.v = v; }
	}
	
	/*static int optimalSolution(int arr[]) {
		int even = 0;
		int player1 = 0;
		int player2 = 0;
		int maxTotal = optimalSolution_util(player1, player2, even, arr);
		return maxTotal;
	}
	
	static int optimalSolution_util(int player1, int player2, int even, int arr[]) {
		int arr2[] = Arrays.copyOf(arr, arr.length);
		
		if (arr.length == 0)//Return Player1 score after all the players have chosen their respective numbers
			return player1;
		
		if (even % 2 == 1) {//Player 2's turn
			if (arr.length > 1) {
				if (arr[0] >= arr[arr.length-1]) {
					return optimalSolution_util(player1 , player2+arr[0], even+1, arr=Arrays.copyOfRange(arr, 1, arr.length));
				}
				else {
					return optimalSolution_util(player1, player2+arr[arr.length-1], even+1, arr=Arrays.copyOfRange(arr, 0, arr.length-1));
				}
			}
			else
				return optimalSolution_util(player1 , player2+arr[0], even+1, arr=Arrays.copyOfRange(arr, 1, arr.length));
		}
		
		return Math.max(optimalSolution_util(player1+arr[0], player2, even+1, arr=Arrays.copyOfRange(arr, 1, arr.length)), 
				optimalSolution_util(player1+arr2[arr2.length-1], player2, even+1, arr2=Arrays.copyOfRange(arr2, 0, arr2.length-1)));
	}*/
	
	static int optimalStrategy_Recursive(int arr[]) {
		int i = 0;
		int j = arr.length-1;
		HashMap<String,Integer> table = new HashMap<>();
		return optimalStrategy_util(arr, i, j, table);
	}
	
	static int optimalStrategy_util(int arr[], int i, int j, HashMap<String,Integer> table) {
		String key = Integer.toString(i) + ":" + Integer.toString(j);
		
		if(table.containsKey(key))
			return table.get(key);
		
		if (i == j) {//When there's only one item to pick 
			return arr[i];
		}
		
		if (i == j-1) {//When there are two items to pick
			return Math.max(arr[i], arr[j]);
		}
		
		int max1 = arr[i] + Math.min(optimalStrategy_util(arr, i+2, j, table), optimalStrategy_util(arr, i+1, j-1, table));
		int max2 = arr[j] + Math.min(optimalStrategy_util(arr, i+1, j-1, table), optimalStrategy_util(arr, i, j-2, table));
		
		table.put(key, Math.max(max1, max2));
		
		return table.get(key);
	}
	
	static int optimalSolution_table(int arr[]) {
		Pair<Integer,Integer> matrix[][] = new Pair[arr.length][arr.length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = new Pair<Integer,Integer>();
			}
		}
		
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][i].setFirst(arr[i]);
			matrix[i][i].setSecond(0);
		}
		
		for (int i = 2; i <= matrix.length; i++) {
			for (int j = 0; j <= matrix.length - i; j++) {
				int gap = i+j-1;
				if ((arr[j] + matrix[j+1][gap].getSecond()) >= (arr[gap] + matrix[j][gap-1].getSecond())) {
					matrix[j][gap].setFirst(arr[j] + matrix[j+1][gap].getSecond());
					matrix[j][gap].setSecond(matrix[j+1][gap].getFirst());
				}
				else {
					matrix[j][gap].setFirst(arr[gap] + matrix[j][gap-1].getSecond());
					matrix[j][gap].setSecond(matrix[j][gap-1].getFirst());
				}
			}
		}
		return matrix[0][arr.length-1].getFirst();
	}
	
	public static void main(String[] args) {
		int arr1[] = {20,30,2,2,2,10};
		System.out.println(optimalStrategy_Recursive(arr1));
		System.out.println(optimalSolution_table(arr1));
	}
}

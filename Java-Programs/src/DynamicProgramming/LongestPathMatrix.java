package DynamicProgramming;

import java.util.Arrays;

public class LongestPathMatrix {
	static int compute(int mat[][]) {
		int numRows = mat.length;
		int numCols = mat[0].length;
		int table[][] = new int[numRows][numCols];
		for (int row[] : table)
			Arrays.fill(row, -1);
		int longestSumPath = 1;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				if (table[i][j] == -1)
					compute_util(mat, i, j, numRows - 1, table);
				if (longestSumPath < table[i][j])
					longestSumPath = table[i][j];
			}			
		}

		return longestSumPath;
	}
	
	static int compute_util(int mat[][], int row, int col, int length, int table[][]) {

		if (row < 0 || col < 0)
			return 0;
		
		if (row > length || col > length)
			return 0;
		
		if (table[row][col] != -1)
			return table[row][col];
		
		if (row - 1 >= 0) {
			if (mat[row-1][col] - mat[row][col] == 1)
				return table[row][col] = 1 + compute_util(mat, row-1, col, length, table);
		}
		if (row + 1 <= length) {
			if (mat[row+1][col] - mat[row][col] == 1)
				return table[row][col] = 1 + compute_util(mat, row+1, col, length, table);
		}
		if (col + 1 <= length) {
			if (mat[row][col+1] - mat[row][col] == 1)
				return table[row][col] = 1 + compute_util(mat, row, col+1, length, table);
		}
		if (col - 1 >= 0) {
			if (mat[row][col-1] - mat[row][col] == 1)
				return table[row][col] = 1 + compute_util(mat, row, col-1, length, table);
		}
		
		return table[row][col] = 1;
	}
	
	public static void main(String[] args) {
		int arr[][] = new int[][]{{1,2,9},
							  	  {5,3,8},
							  	  {2,6,7}};
		
		System.out.println(compute(arr));
	}
}

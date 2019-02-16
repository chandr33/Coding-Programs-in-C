package DailyCodingProblems;

public class PaintHouse {
	static int min_index = -1;
	static int second_min_index = -1;
	
	static void calcMin(int[] row) {
		min_index = 0;
		if (row.length >= 2) {
			second_min_index = 1;
			if (row[0] < row[1]) {
				min_index = 0;
				second_min_index = 1;
			}
			else {
				min_index = 1;
				second_min_index = 0;
			}
			int i = 2;
			while (i < row.length) {
				if (row[i] < row[min_index]) {
					second_min_index = min_index;
					min_index = i;
				}
				else if (row[i] < row[second_min_index] && row[i] >= row[min_index]) {
					second_min_index = i;
				}
				i++;
			}
		}
	}
	public static int calcCost(int[][] costs) {
		if (costs.length == 0)
            return 0;
		int min_cost = Integer.MAX_VALUE;
		if (costs[0].length >= 2) {
			int[][] result_matrix = new int[costs.length][costs[0].length];
			for (int j = 0; j < costs[0].length; j++) {
				result_matrix[0][j] = costs[0][j];
			}
			calcMin(result_matrix[0]);
			for (int i = 1; i < costs.length; i++) {
				for (int j = 0; j < costs[i].length;j++) {
					if (j == min_index) {
						result_matrix[i][j] = costs[i][j]+result_matrix[i-1][second_min_index];
					}
					else {
						result_matrix[i][j] = costs[i][j] + result_matrix[i-1][min_index];
					}
				}
				calcMin(result_matrix[i]);
			}
			for (int j = 0; j < costs[0].length; j++) {
				if (result_matrix[costs.length-1][j] < min_cost)
					min_cost = result_matrix[costs.length-1][j];
			}
			//printMatrix(result_matrix);
		}
		else {
			return costs[0][0];
		}
		return min_cost;
	}
	static void printMatrix(int[][] cost) {
		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost[i].length;j++) {
				System.out.print(cost[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		int [][] cost_matrix = {{6,9,12,7},{7,8,3,4},{10,10,7,12}};
		System.out.println(calcCost(cost_matrix));
	}
}

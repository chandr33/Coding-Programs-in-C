package DynamicProgramming;
import java.util.*;
public class KnapSack {
	
	static int compute(int val[], int weight[], int maxWeight) {
		int index = val.length - 1;
		HashMap<String,Integer> table = new HashMap<>();
		return maxVal(val,weight,maxWeight, index, table);
	}
	
	static int maxVal(int val[], int weight[], int maxWeight, int index, HashMap<String, Integer> table) {
		String key = Integer.toString(maxWeight) + ":" + Integer.toString(index);
		if (table.containsKey(key))
			return table.get(key);
		if (index < 0 || maxWeight < 0)
			return 0;
		if (weight[index] > maxWeight)
			return maxVal(val, weight, maxWeight, index-1, table);
		table.put(key, Math.max(val[index] + maxVal(val, weight, maxWeight-weight[index], index-1, table), maxVal(val, weight, maxWeight, index-1, table)));
		return table.get(key);
	}
	
	static int KnapSack_table(int val[], int weight[], int maxWeight) {
		int matrix[][] = new int[val.length][maxWeight+1];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (j == 0)
					matrix[i][j] = 0;
				else if (i == 0) {
					if (weight[i] <= j)
						matrix[i][j] = val[i];
					else
						matrix[i][j] = 0;
				}
				else {
					if (j < weight[i])
						matrix[i][j] = matrix[i-1][j];
					else
						matrix[i][j] = Math.max(matrix[i-1][j], val[i] + matrix[i-1][j-weight[i]]);
				}
			}
		}
		return matrix[val.length-1][maxWeight];
	}

	public static void main(String[] args) {
		int value[] = {60,100,120};
		int weight[] = {2,2,1};
		int maxWeight = 4;
		System.out.println(KnapSack_table(value,weight,maxWeight));
		System.out.println(compute(value,weight,maxWeight));
	}
}

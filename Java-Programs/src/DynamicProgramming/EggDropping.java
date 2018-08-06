package DynamicProgramming;
import java.util.*;
public class EggDropping {
	
	static int EggDropping_table(int n, int k) {
		int table[][] = new int[k][n];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (i == 0) //If currently there is only one egg
					table[i][j] = j+1;
				else if (i > j) //If number of eggs is greater than the number of floors
					table[i][j] = table[i-1][j];
				else {
					table[i][j] = Integer.MAX_VALUE;
					for (int m = 0; m <= j; m++) {
						if (m == 0) {//Dropping from the first floor
							if (table[i][j] > 1+Math.max(0,table[i][j-1]))
								table[i][j] = 1+Math.max(0,table[i][j-1]);
						}
						else if (m > 0 && m < j) {
							if (table[i][j] > 1+Math.max(table[i-1][m-1], table[i][j-m-1]))
								table[i][j] = 1+Math.max(table[i-1][m-1], table[i][j-m-1]);
						}
						else {//Dropping from the current last floor
							if (table[i][j] > 1+Math.max(table[i-1][j-1], 0))
								table[i][j] = 1+Math.max(table[i-1][j-1], 0);
						}
					}
				}
			}
		}
		return table[table.length-1][table[0].length-1];
	}
	
	static int EggDropping_recursive(int n, int k, HashMap<String,Integer> table) {
		String key = Integer.toString(n) + ":" + Integer.toString(k);
		
		if (table.containsKey(key))
			return table.get(key);
		
		if (k == 1)
			return n;
		
		if (n <= 0)
			return 0;
		
		int min = Integer.MAX_VALUE;
		
		for (int i = 1; i <= n; i++) {
			int worstCase = 1 + Math.max(EggDropping_recursive(i-1, k-1, table), EggDropping_recursive(n-i, k, table));
			if (min > worstCase)
				min = worstCase;			
		}
		table.put(key, min);	
		return table.get(key);
	}

	public static void main(String[] args) {
		int n = 100;
		int k = 3;
		System.out.println(EggDropping_table(n, k));
		HashMap<String,Integer> table = new HashMap<>();
		System.out.println(EggDropping_recursive(n, k, table));
	}
}

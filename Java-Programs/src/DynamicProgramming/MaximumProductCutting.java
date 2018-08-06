package DynamicProgramming;
import java.util.*;
public class MaximumProductCutting {
	static int MaxProduct(int n) {
		HashMap<String, Integer> table = new HashMap<>();
		return MaxProduct_util(n,table);
	}
	static int MaxProduct_util(int n, HashMap<String, Integer> table) {
		String key = Integer.toString(n);
		int max = Integer.MIN_VALUE;
		
		if (table.containsKey(key))
			return table.get(key);
		
		if (n<=1)
			return 1;
		
		for (int i = 1; i <= n; i++) {
			max  = Math.max(max, i*MaxProduct_util(n-i, table));
		}
		
		table.put(key, max);
		return table.get(key);
	}
	
	public static void main(String[] args) {
		int input = 150;
		System.out.println(MaxProduct(input));
	}
}

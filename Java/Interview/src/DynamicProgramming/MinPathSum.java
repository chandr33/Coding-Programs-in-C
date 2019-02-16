package DynamicProgramming;
import java.util.*;
public class MinPathSum {
	static int recursive_minPath(int grid[][]) {
		int index1 = 0, index2 = 0;
		HashMap<String,Integer> table = new HashMap<>();
		return recursive_minPath_util(grid, index1, index2, table);
	}
	
	static int recursive_minPath_util(int grid[][], int index1, int index2, HashMap<String,Integer> table) {
		
		String key = Integer.toString(index1) + ":" + Integer.toString(index2);
		
		if(table.containsKey(key))
			return table.get(key);
		
		if (index1 >= grid.length || index2 >= grid[0].length) {
			return Integer.MAX_VALUE;
		}
		
		if (index1 == grid.length-1 && index2 == grid[0].length-1) {			
			return grid[index1][index2];
		}
		
		table.put(key, grid[index1][index2] + Math.min(recursive_minPath_util(grid, index1+1, index2, table), recursive_minPath_util(grid, index1, index2+1, table)));
		
		return table.get(key);
	}
	public static void main(String[] args) {
		int grid[][] = {{1,3,1},
						{1,5,1},
						{4,2,1}};
		System.out.println(recursive_minPath(grid));
	}
}

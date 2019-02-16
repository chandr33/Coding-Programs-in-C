package DynamicProgramming;
import java.util.*;
public class UniquePaths {
	static int recursive_path(int obstacleGrid[][]) {
		int index1 = 0;
		int index2 = 0;
		HashMap<String,Integer> table = new HashMap<>();
		return recursive_path_util(obstacleGrid, index1, index2, table);
	}
	
	static int recursive_path_util(int obstacleGrid[][], int index1, int index2, HashMap<String, Integer> table) {
		String key = Integer.toString(index1) + ":" + Integer.toString(index2);
		
		if(table.containsKey(key))
			return table.get(key);
		
		if (index1 >= obstacleGrid.length || index2 >= obstacleGrid[index1].length) {
			return 0;
		}
		
		if (obstacleGrid[index1][index2] == 1)
			return 0;
		
		if (index1 == obstacleGrid.length-1 && index2 == obstacleGrid[index1].length-1) {
			if (obstacleGrid[index1][index2] == 1)
				return 0;
			return 1;
		}
			
		table.put(key, recursive_path_util(obstacleGrid, index1+1, index2, table) + recursive_path_util(obstacleGrid, index1, index2+1, table));
		
		return table.get(key);
	}
	public static void main(String[] args) {
		int obstacleGrid[][] = {{1}};
		System.out.println(recursive_path(obstacleGrid));
	}
}

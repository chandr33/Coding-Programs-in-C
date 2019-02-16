package ArraysAndStrings;

import java.util.*;
public class ThreeSum {
	static String convertToString(int num1, int num2, int num3) {
		String ret_string = Integer.toString(num1) + ":" + Integer.toString(num2) + ":" + Integer.toString(num3);
		return ret_string;
	}
	static List<List<Integer>> computeList(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
		HashMap<String,Integer> setTable = new HashMap<>();
		HashMap<Integer,Boolean> visited = new HashMap<>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0)
				break;
			if (!visited.containsKey(nums[i]))
				visited.put(nums[i], true);
			else
				continue;
			HashMap<Integer,Integer> possible_complements = new HashMap<>();
			int j = i + 1;
			while (j < nums.length) {
				if (!possible_complements.containsKey(nums[j])) {
						possible_complements.put(-(nums[i] + nums[j]),nums[j]);
				}
				else {
					String set = Integer.toString(possible_complements.get(nums[j])) + ":" + Integer.toString(nums[i]) + ":" +Integer.toString(nums[j]);
					if (!setTable.containsKey(set)) {
						setTable.put(set, 1);
						List<Integer> adjList = new ArrayList<>();
						adjList.add(nums[i]);
						adjList.add(nums[j]);
						adjList.add(possible_complements.get(nums[j]));
						resultList.add(adjList);
					}
				}
				j++;
			}
		}
		return resultList;
	}
	public static void main(String[] args) {
		//int array[] = new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
		//int array[] = new int[]{-4,-2,-2,-2,0,1,2,2,2,4,4,6};
		//int array[] = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int array[] = new int[]{-1,-1,0,1,2};
		System.out.println(computeList(array));
	}
}

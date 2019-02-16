package ArraysAndStrings;
import java.util.*;

public class SumToANumber {
	public static boolean isSum_Dynamic(int[] arr, int target) {
		HashMap<String, Boolean> table = new HashMap<>();
		boolean result = isSum_util(arr, target, 0, 0, table);
		return result;
	}
	public static boolean isSum_util(int[] arr, int target, int index, int sum, HashMap<String, Boolean> table) {
		String key = Integer.toString(index)+" "+Integer.toString(sum);
		if (table.containsKey(key))
			return table.get(key);
		if (sum == target)
			return true;
		if (sum > target)
			return false;
		if (index >= arr.length)
			return false;
		table.put(key, isSum_util(arr, target, index+1, sum, table) || isSum_util(arr, target, index+1, sum+arr[index], table));
		return table.get(key);
	}
	public static void main(String[] args) {
		int[] arr = {10,15,3,7};
		int target = 17;
		System.out.println(isSum_Dynamic(arr, target));
	}
}

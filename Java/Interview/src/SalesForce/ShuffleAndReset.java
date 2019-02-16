package SalesForce;
import java.util.*;
public class ShuffleAndReset {
	int arr[];
	List<int[]> permute_list = new ArrayList<>();
	public ShuffleAndReset(int nums[]) {
		arr = nums.clone();
	}
	
	public int[] shuffle() {
		shuffle_helper(0, arr);
		int len = permute_list.size();
		Random rand = new Random();
		int val = rand.nextInt(len-1); //Random value between 0 and length of the permutation list
		return permute_list.get(val);
	}
	
	public int[] reset() {
		return arr;
	}
	
	public void shuffle_helper(int i, int arr[]) {
		if (i == arr.length) {
			permute_list.add(NewArr(arr));
		}
		for (int k = i; k < arr.length; k++) {
			swap(arr,k,i);
			shuffle_helper(i+1, arr);
			swap(arr, k, i);
		}
	}
	
	public void swap(int arr[], int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public int[] NewArr(int arr[]) {
		int new_arr[] = new int[arr.length];
		for(int i = 0;i < arr.length; i++) {
			new_arr[i] = arr[i];
		}
		return new_arr;
	}

	public static void main(String[] args) {
		int nums[] = {1,2,3,4,5,6,7};
		ShuffleAndReset sr = new ShuffleAndReset(nums);
		System.out.println(Arrays.toString(sr.shuffle()));
	}
}

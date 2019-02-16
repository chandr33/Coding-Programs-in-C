package ArraysAndStrings;
public class StringAndArrayPermutation {
	static void permuteString(String str) {
		int i = 0;
		int j = str.length();
		permuteString_util(str, i, j);
	}
	
	static void permuteString_util(String string_to_permute, int i, int j) {
		if (i == j)
			printPermutations(string_to_permute);
			
		for (int k = i; k < j; k++) {
			string_to_permute = swap(string_to_permute,i,k);
			permuteString_util(string_to_permute, i+1, j);
			string_to_permute = swap(string_to_permute,i,k);
		}
	}
	
	static String swap(String str, int i, int j) {
		char string_to_permute[] = str.toCharArray();
		char temp = string_to_permute[i];
		string_to_permute[i] = string_to_permute[j];
		string_to_permute[j] = temp;
		return String.valueOf(string_to_permute);
	}
	
	static void printPermutations(String permutation) {
		for (int i = 0; i < permutation.length(); i++) {
			System.out.print(permutation.charAt(i));
		}
		System.out.println();
	}
	
	static void permutations2(String str) {
		String prefix = "";
		permutations_util(prefix, str);
	}
	
	static void permutations_util(String prefix, String suffix) {
		if (suffix.length() == 0)
			System.out.println(prefix);
		else {
			for (int i = 0; i < suffix.length(); i++) {
				permutations_util(prefix + suffix.charAt(i), suffix.substring(0, i) + suffix.substring(i+1, suffix.length()));
			}
		}
	}
	
	static void permuteArrays(int arr[]) {
		int index = 0;
		permuteArrays_util(arr, index);
	}
	
	static void permuteArrays_util(int arr[], int index) {
		if (index >= arr.length)
			printArrays(arr);
		for (int i = index; i < arr.length; i++) {
			swap(arr,i,index);
			permuteArrays_util(arr, index+1);
			swap(arr,i,index);
		}
	}
	
	static void swap(int arr[], int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	static void printArrays(int arr[]) {
		for (int i : arr)
			System.out.print(i+" ");
		System.out.println();
	}

	public static void main(String[] args) {
		String str = "bab";
		int arr[] = {1,2,3};
		permuteString(str);
		permuteArrays(arr);
		//permutations2(str);
	}
}

package DynamicProgramming;

public class RodCutting {
	static int RodCutting_table(int arr[]) {
		int table[][] = new int[arr.length][arr.length+1];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (i == 0)
					table[i][j] = arr[i]*j;
				else if (j == 0)
					table[i][j] = 0;
				else {
					if (i+1 > j)
						table[i][j] = table[i-1][j];
					else {
						table[i][j] = Math.max(table[i-1][j], arr[i] + table[i][j-(i+1)]);
					}
				}
			}
		}
		return table[arr.length-1][arr.length];
	}
	
	static int RodCutting_Recursive(int arr[]) {
		int index = arr.length-1;
		return RodCutting_util(arr, index);
	}
	
	static int RodCutting_util(int arr[], int index) {
		if (index < 0)
			return 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i <= index; i++) {
			max = Math.max(max, arr[i] + RodCutting_util(arr, index-i-1));
		}
		return max;
	}

	public static void main(String[] args) {
		int arr[] = {2,5,7,8};
		System.out.println(RodCutting_table(arr));
		System.out.println(RodCutting_Recursive(arr));
	}
}

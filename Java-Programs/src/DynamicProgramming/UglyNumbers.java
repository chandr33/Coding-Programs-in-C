package DynamicProgramming;

public class UglyNumbers {
	static int getNthNumber(int n) {
		int arr[] = new int [n];
		arr[0] = 1;
		int i2 = 0;
		int i3 = 0;
		int i5 = 0;
		int next_num = 1;
		int i;
		for (i = 1; i < arr.length; i++) {
			int next_multiple_2 = arr[i2]*2;
			int next_multiple_3 = arr[i3]*3;
			int next_multiple_5 = arr[i5]*5;
			next_num = Math.min(next_multiple_2, Math.min(next_multiple_3, next_multiple_5));
			arr[i] = next_num;
			if (next_num == next_multiple_2) {
				next_multiple_2 = next_multiple_2 * 2;
				i2++;
			}
			if (next_num == next_multiple_3) {
				next_multiple_3 = next_multiple_3 * 3;
				i3++;
			}
			if (next_num == next_multiple_5) {
				next_multiple_5 = next_multiple_5 * 5;
				i5++;
			}
		}
		return next_num;
	}
	

	public static void main(String[] args) {
		System.out.println(getNthNumber(150));
	}
}

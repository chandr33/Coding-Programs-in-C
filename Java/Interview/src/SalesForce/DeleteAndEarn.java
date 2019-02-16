package SalesForce;

public class DeleteAndEarn {
	static int maximize(int arr[]) {
		int count[] = new int[10001];
		for (int i = 0; i < arr.length; i++) {
			count[arr[i]]++;
		}
		int pick = 0, not_pick = 0, previous = -1;
		for (int i = 0; i < count.length; i++) {
			if (count[i] > 0) {
				int prevMax = Math.max(pick, not_pick);
				if (i-previous == 1) {
					pick = i*count[i]+not_pick;
					not_pick = prevMax;
				}
				else {
					pick = i*count[i]+prevMax;
					not_pick = prevMax;
				}
				previous = i;
			}
		}
		return Math.max(pick, not_pick);
	}
	public static void main(String[] args) {
		int arr[] = {2, 2, 3, 3, 3, 4};
		System.out.println(maximize(arr));
	}
}

package ArraysAndStrings;

public class PalindromicPartitions {
	static void partitionString(String input) {
		int i = 0;
		int j = input.length();
		String util = "";
		partitionString_util(input, util, i, j);
	}
	
	static void partitionString_util(String input, String util, int i, int j) {
		if (i == j) {
			System.out.println(util);
			return;
		}
		for (int k = i; k < j; k++) {
			if (isPalindrome(input, i, k)) {
				partitionString_util(input, util+substr(input,i, k-i+1)+" ", k+1, j);
			}
		}
			
	}
	static boolean isPalindrome(String str, int i, int j) {
		int startindex = i;
		int endIndex = j;
		while (startindex < endIndex) {
			if (str.charAt(startindex) != str.charAt(endIndex)) {
				return false;
			}
			startindex++;
			endIndex--;
		}
		return true;
	}
	
	static String substr(String input, int startIndex, int endIndex) {
		String result = "";
		int count = 0;
		while (count != endIndex && startIndex < input.length()) {
			result += input.charAt(startIndex++);
			count++;
		}
		return result;
	}
	
	public static void main(String[] args) {
		String input = "aaa";
		partitionString(input);
	}
}

package ArraysAndStrings;

public class LongestPalindromicSubstring {
	public static boolean isPalindrome(String s) {
		if (s.length() == 0)
			return true;
		int i = 0;
		int j = s.length()-1;
		while (i <= j) {
			if (s.charAt(i++) != (s.charAt(j--)))
				return false;
		}
		return true;
	}
	public static String longestPalindrome(String s) {
		int max = 1;
		String result = "";
		boolean found = false;
		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j < s.length()+1; j++) {
				String temp = s.substring(i, j);
				if(isPalindrome(temp))
				{
					if (max < temp.length()) {
						max = temp.length();
						result = temp;
						found = true;
					}
				}
					
			}
		}
		if (!found) {
			result = (s.length() <= 1) ? s : s.substring(0,1);
        }
		return result;  
    }
	public static void main(String[] args) {
		System.out.println(longestPalindrome("cbbd"));
	}
}

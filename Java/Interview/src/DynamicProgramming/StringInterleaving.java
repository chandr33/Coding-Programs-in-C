package DynamicProgramming;
public class StringInterleaving {
	static boolean isInterleave(String s1, String s2, String s3) {
		return isInterleave_util(s1, s2, s3, 0, 0, 0);
	}
	
	static boolean isInterleave_util(String s1, String s2, String s3, int i1, int i2, int i3) {
		if (i3 >= s3.length())
			return  (i1 >= s1.length()) && (i2 >= s2.length());
		boolean b1 = (i2 < s2.length() && i3 < s3.length()) && (s2.charAt(i2) == s3.charAt(i3)) && isInterleave_util(s1, s2, s3, i1, i2+1, i3+1);
		boolean b2 = (i1 < s1.length() && i3 < s3.length()) && (s1.charAt(i1) == s3.charAt(i3)) && isInterleave_util(s1, s2, s3, i1+1, i2, i3+1);
		return b1 || b2;
	}
	public static void main(String[] args) {
		String s1 = "aabcc";
		String s2 = "dbbca";
		String s3 = "aadbbcbcac";
		String s4 = "aadbbbaccc";
		System.out.println(isInterleave(s1, s2, s4));
	}

}

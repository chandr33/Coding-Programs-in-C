/*
 * This program computes the Shortest Common SuperSequence of the given strings
 * str1 = "AGGTAB" str2 = "GXTXAYB"  result = "AGXGTXAYB"
 */
package DynamicProgramming;

public class SCS {
	
	static int CommonSupersequence_Recursive(String str1, String str2) {
		int indexStr1 = 0;
		int indexStr2 = 0;
		return CommonSupersequence_util(str1, str2, indexStr1, indexStr2);
	}
	
	static int CommonSupersequence_util(String str1, String str2, int indexStr1, int indexStr2) {
		if (indexStr1 == str1.length()) {
			return str2.length() - indexStr2;
		}
		
		if (indexStr2 == str2.length()) {
			return str1.length() - indexStr1;
		}
		
		if (str1.charAt(indexStr1) == str2.charAt(indexStr2)) {
			return 1 + CommonSupersequence_util(str1, str2, indexStr1+1, indexStr2+1);
		}
		
		return 1 + Math.min(CommonSupersequence_util(str1, str2, indexStr1+1, indexStr2), CommonSupersequence_util(str1, str2, indexStr1, indexStr2+1));
	}
	
	static int CommonSupersequence_table(String str1, String str2) {
		int table[][] = new int[str1.length()+1][str2.length()+1];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (i == 0) {
					table[i][j] = j;
				}
				else if (j == 0) {
					table[i][j] = i;
				}
				else {
					if (str1.charAt(i-1) == str2.charAt(j-1)) {
						table[i][j] = 1 + table[i-1][j-1];
					}
					else {
						table[i][j] = 1 + Math.min(table[i][j-1], table[i-1][j]);
					}
				}
			}
		}
		return table[table.length-1][table[0].length-1];
	}
	
	public static void main(String[] args) {
		String str1 = "AGGTAB";
		String str2 = "GXTXAYB";
		System.out.println(CommonSupersequence_Recursive(str1, str2));
		System.out.println(CommonSupersequence_table(str1, str2));
	}
}

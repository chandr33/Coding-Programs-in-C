package DynamicProgramming;

public class EditDistance {
	public static int computeNumEdits(String str1, String str2) {
		int index1 = str1.length() - 1;
		int index2 = str2.length() - 1;
		int count = computeNumEdits_util(str1, str2, index1, index2);
		return count;
	}

	
	public static int computeNumEdits_util(String str1, String str2, int index1, int index2) {
		if (index1 == 0) {
			return index2;
		}
	
		if (index2 == 0) {
			return index1;
		}
		
		if (str1.charAt(index1) == str2.charAt(index2)) {
			return computeNumEdits_util(str1, str2, index1 - 1, index2 - 1);
		}
		
		return 1 + Math.min(Math.min(computeNumEdits_util(str1, str2, index1 - 1, index2),//Remove 
									computeNumEdits_util(str1, str2, index1, index2 - 1)), //Insert
									computeNumEdits_util(str1, str2, index1 - 1, index2 - 1));//Replace
	}
	
	public static int computeNumEditsTable(String str1, String str2) {
		int table[][] = new int[str1.length()+1][str2.length()+1];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (i == 0 || j == 0) {
					if (i == 0)
						table[i][j] = j;
					if (j == 0)
						table[i][j] = i;
				}
				else {
					if (str1.charAt(i - 1) == str2.charAt(j - 1))
						table[i][j] = table[i - 1][j - 1];
					else {
						table[i][j] = Math.min(Math.min(table[i - 1][j], table[i][j - 1]), table[i - 1][j - 1]) + 1;
					}
				}
			}
		}
		return table[str1.length()][str2.length()];
	}
	public static void main(String[] args) {
		String str1 = "saturday";
		String str2 = "sunday";
		System.out.println(computeNumEditsTable(str1, str2));
	}
}

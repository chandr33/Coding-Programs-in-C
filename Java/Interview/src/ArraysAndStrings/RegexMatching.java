package ArraysAndStrings;

public class RegexMatching {
	private static boolean isMatch(String text, String pattern) {
		if (pattern.equals(".*"))
			return true;
		boolean [][] table = new boolean[text.length()+1][pattern.length()+1];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (i == 0) {
					if (j == 0) 
						table[i][j] = true;
					else {
						if (pattern.charAt(j-1) == '*') {
							table[i][j] = table[i][j-2];
						}
						else {
							table[i][j] = false;
						}
					}
				}
				else if (j == 0) {
					if (i != 0) {
						table[i][j] = false;
					}
				}
				else {
					if (pattern.charAt(j-1) == '*') {
						table[i][j] = table[i][j-2];
						if (pattern.charAt(j-2) == text.charAt(i-1) || pattern.charAt(j-2) == '.') {
							table[i][j] = table[i][j] || table[i-1][j];
						}
					}
					else if (text.charAt(i-1) == pattern.charAt(j-1) || pattern.charAt(j-1) == '.') {
						table[i][j] = table[i-1][j-1];
					}
					else {
						table[i][j] = false;
					}
				}
			}
		}
		printTable(table);
		return table[table.length-1][table[0].length-1];
	}
	
	private static void printTable(boolean[][] table) {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (table[i][j])
					System.out.print("T ");
				else
					System.out.print("F ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		String text = "xaabyc";
		String pattern = "xa*b.c";
		System.out.println(isMatch(text, pattern));
	}
}

package DynamicProgramming;
import java.util.*;
public class WildCardMatching {
	
	static boolean isMatch(String text, String pattern) {
		HashMap<String,Boolean> table = new HashMap<>();
		return isMatch_util(text, pattern, table);
	}
	
	static boolean isMatch_util(String text, String pattern, HashMap<String,Boolean> table) {
		String key = text+" "+pattern;
		
		if(table.containsKey(key))
			return table.get(key);
		
		if (pattern.isEmpty())
			return text.isEmpty();
		
		if (text.isEmpty()) {
			if (!pattern.isEmpty()) {
				if (pattern.charAt(0) == '*') {
					table.put(key, isMatch_util(text, pattern.substring(1), table));
					return table.get(key);
				}
				else {
					table.put(key, false);
					return table.get(key);
				}
			}
			else {
				table.put(key, pattern.isEmpty());
				return table.get(key);
			}
		}
		
		if (pattern.charAt(0) == '*') {
			if (pattern.length() > 1) {
				if (pattern.charAt(1) == text.charAt(0) || pattern.charAt(1) == '?') {
					table.put(key, isMatch_util(text.substring(1), pattern.substring(2), table) || isMatch_util(text.substring(1), pattern, table));
					return table.get(key);
				}
				else {
					if (pattern.charAt(1) == '*') {
						table.put(key, isMatch_util(text.substring(1), pattern.substring(1), table) || isMatch_util(text, pattern.substring(2), table));
						return table.get(key);
					}
					else {
						table.put(key, isMatch_util(text.substring(1), pattern, table));
						return table.get(key);
					}
				}
			}
			else {
				table.put(key, isMatch_util(text.substring(text.length()), pattern.substring(1), table));
				return table.get(key);
			}
		}
		else if (text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '?') {
			table.put(key, isMatch_util(text.substring(1), pattern.substring(1), table));
			return table.get(key);
		}
		else {
			table.put(key, false);
			return table.get(key);
		}
	}
	
	static boolean isMatch_table(String text, String pattern) {
		if (pattern.isEmpty())
			return text.isEmpty();
		boolean table[][] = new boolean[text.length()+1][pattern.length()+1];
		table[0][0] = true;
		table[0][1] = (pattern.charAt(0) == '*') ? true : false;
		for (int i = 2; i < table[0].length; i++) {
			if (pattern.charAt(i-1) == '*') {
				table[0][i] = table[0][i-1];
			}
			else
				table[0][i] = false;
		}
		
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (j == 0 && i > 0) 
					table[i][j] = false;
				else if(j >= 1 && i >= 1) {
					if (text.charAt(i-1) == pattern.charAt(j-1) || pattern.charAt(j-1) == '?')
						table[i][j] = table[i-1][j-1];
					else if(pattern.charAt(j-1) == '*')
						table[i][j] = table[i][j-1] | table[i-1][j];
					else
						table[i][j] = false;
				}
			}
		}
		return table[text.length()][pattern.length()];
	}
	
	static void printTable(boolean table[][]) {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (table[i][j] == true) {
					System.out.print("T  ");
				}
				else {
					System.out.print("F  ");
				}
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		String text = "ho";
		String pattern = "**ho";
		System.out.println(isMatch_table(text, pattern));
		//System.out.println(isMatch(text, pattern));
	}
}

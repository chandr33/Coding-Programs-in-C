package DynamicProgramming;

public class WordBreak {
	static String[] dict = {"i","like","sam","sung","samsung","mobile",
		 "ice","cream","icecream","and","man","go","mango"};
	
	static boolean findInDictionary(String word) {
		for (String inDict : dict) {
			if (inDict.equals(word))
				return true;
		}
		return false;
	}
	
	static boolean search_table(String input) {
		boolean table[][] = new boolean[input.length()][input.length()];
		for (int i = 0; i < table.length; i++) {
			String str = Character.toString(input.charAt(i));
			table[i][i] = (findInDictionary(str)) ? true : false;
		}
		
		for (int i = 2; i <= table.length; i++) {
			for (int j = 0; j <= table.length - i; j++) {
				int gap = i+j-1;
				String head_str = input.substring(j, gap+1);
				if (findInDictionary(head_str)) {
					table[j][gap] = true;
				}
				else {
					for (int k = j; k < gap; k++) {
						if (table[j][k] && table[k+1][gap]) {
							table[j][gap] = true;
							break;
						}
						else {
							table[j][gap] = false;
						}
					}
				}
			}
		}
		if (input.length() >= 1)
			return table[0][input.length()-1];
		return true;
	}
	
	public static void main(String[] args) {
		String input = "";
		System.out.println(search_table(input));
	}
}

package DailyCodingProblems;

import java.util.Arrays;

public class Dictionary {
	static final String[] word_list = {"bed","bath","bedbath","and","beyond"};
	static final String[] word_list2 = {"the","quick","brown","fox"};
	public static boolean isPresent(String word) {
		for (String s : word_list) {
			if (s.equals(word))
				return true;
		}
		return false;
	}
	public static String[] getWords(String sentence) {
		String[] result = new String[word_list.length];
		int index = 0;
		for (int i = 2; i < sentence.length(); i++) {
			for (int j = 0; j <= sentence.length()-i; j++) {
				int gap = i+j-1;
				boolean found = false;
				String word = sentence.substring(j,gap+1);
				for (int k = 0; k < word.length(); k++) {
					String split1 = word.substring(0,k);
					String split2 = word.substring(k,word.length());
					if (Arrays.asList(result).contains(split1) && Arrays.asList(result).contains(split2)) {
						found = true;
						break;
					}
				}
				if (isPresent(word) && !found)
					result[index++] = word;
				found = false;
			}
		}
		return result;
	}
	public static void print(String[] arr) {
		String result = "";
		for (int i = 0; i < arr.length && arr[i] != null; i++) {
			result += arr[i]+" ";
		}
		System.out.println(result);
	}
	public static void main(String[] args) {
		String sentence = "bedbathandbeyond";
		print(getWords(sentence));
	}
}

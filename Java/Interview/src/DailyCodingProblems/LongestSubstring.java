package DailyCodingProblems;
import java.util.*;
public class LongestSubstring {
	public static boolean isOverFlow(String s, int k) {
		int count_unique = 0;
		boolean overflow = false;
		HashMap<Character,Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (!map.containsKey(ch)) {
				map.put(ch, 1);
				count_unique++;
				if (count_unique > k) {
					overflow = true;
					break;
				}
			}
			else {
				int val = map.get(ch);
				val++;
				map.put(ch, val);
			}
		}
		return overflow;
	}
	public static String Solution(String s, int k) {
		String word = "";
		String max_word = "";
		int j = 0;
		for (int i = 1; i <= s.length(); i++) {
			word = s.substring(j,i);
			if (!isOverFlow(word, k)) {
				if (word.length() > max_word.length())
					max_word = word;
			}
			else {
				j++;
				i--;
			}
		}
		return max_word;
	}

	public static void main(String[] args) {
		String s = "abcdbbaabcc";
		int k = 3;
		System.out.println(Solution(s, k));
	}
}

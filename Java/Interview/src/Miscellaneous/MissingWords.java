package Miscellaneous;
import java.util.*;

public class MissingWords {
	public static void print(List<String> result) {
		for (String s : result) {
			System.out.println(s);
		}
	}
	public static List<String> Solution(String s, String t) {
		List<String> map_s = new ArrayList<>();
		List<String> map_t = new ArrayList<>();
		List<String> result = new ArrayList<>();
		String temp = "";
		String temp2 = "";
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isWhitespace(s.charAt(i)) && i != s.length()) {
				temp += s.charAt(i);
			}
			else {
				map_s.add(temp);
				temp = "";
			}
		}
		map_s.add(temp);
		for (int i = 0; i < t.length(); i++) {
			if (!Character.isWhitespace(t.charAt(i)) && i != t.length()) {
				temp2 += t.charAt(i);
			}	
			else {
				map_t.add(temp2);
				temp2 = "";
			}
		}
		map_t.add(temp2);
		for (String str : map_s) {
			if (map_t.indexOf(str) == -1) {
				result.add(str);
			}
		}
		print(result);
		return result;
	}
	public static void main(String[] args) {
		String s = "I am using hackerrank to improve programming";
		String t = "am hackerrank to improve";
		Solution(s, t);
	}
}

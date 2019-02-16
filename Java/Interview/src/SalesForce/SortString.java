package SalesForce;
import java.util.*;
public class SortString {
	
	static class SortByLength implements Comparable<SortByLength> {
		String name;
		public SortByLength(String str) {
			this.name = str;
		}		
		public String getName() {
			return this.name;
		}
		@Override
		public int compareTo(SortByLength o) {
			// TODO Auto-generated method stub
			return this.name.length() - o.name.length();
		}
	}
	
	static String arrange(String sentence) {
		List<SortByLength> list_string = new ArrayList<SortByLength>();
		String temp = "";
		for (int i = 0; i < sentence.length(); i++) {
			if (!Character.isWhitespace(sentence.charAt(i)) && i != sentence.length()-1) {
				temp += sentence.charAt(i);
			}
			else {
				list_string.add(new SortByLength(temp));
				temp = "";
			}
		}
		Collections.sort(list_string);
		String result = "";
		for (int i = 0; i < list_string.size(); i++) {
			String temp2 = list_string.get(i).getName();
			temp2 = temp2.substring(0, 1).toUpperCase() + temp2.substring(1);
			if (i < list_string.size()-1)
				result += temp2 + " ";
			else
				result += temp2;
		}
		result += ".";
		return result;
	}
	
	public static void main(String[] args) {
		String sentence = "I love to code.";
		System.out.println(arrange(sentence));
	}
}

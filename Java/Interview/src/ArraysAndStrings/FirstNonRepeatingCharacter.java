package ArraysAndStrings;
import java.util.*;
public class FirstNonRepeatingCharacter {
	public static String FirstCharater(String s) {//This function will use two data structures
		Set<String> repeating_set = new HashSet<String>();
		List<String> non_repeating_set = new ArrayList<String>();
		for (int i = 0; i < s.length(); i++) {
			char current = s.charAt(i);
			if (non_repeating_set.contains(current)) {
				non_repeating_set.remove((Character)current);
				repeating_set.add(Character.toString(current));
			}
			else {
				non_repeating_set.add(Character.toString(current));
			}
		}
		return non_repeating_set.get(0);
	}
	public static void main(String[] args) {
		System.out.println(FirstCharater("morningm"));
	}

}

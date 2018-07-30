package DynamicProgramming;
import java.util.*;
public class Trie_WordSearch {
	
	static String dictionary[] = {"i","like","sam","sung","samsung","mobile",
		 "ice","cream","icecream","and","man","go","mango"};
	
	static class TrieNode {
		HashMap<Character,TrieNode> children = new HashMap<>();
		boolean endOfWord;
	}	
	
	static boolean search(String word, TrieNode root_ref) {
		TrieNode root = root_ref;
		char c;
		if (root.children.isEmpty())
			return false;
		else {
			for (int i = 0; i < word.length(); i++) {
				c = word.charAt(i);
				if (!root.children.containsKey(c))
					return false;
				
				root = root.children.get(c);
			}
		}
		if (root.endOfWord)
			return true;
		return false;
	}
	
	static void insertNode(String word, TrieNode root_ref) {
		TrieNode root = root_ref;
		char c;
		for (int i = 0; i < word.length(); i++) {
			c = word.charAt(i);
			if (!root.children.containsKey(c)) {
				TrieNode newNode = new TrieNode();
				root.children.put(c, newNode);
			}
			root = root.children.get(c);
		}
		
		root.endOfWord = true;
	}
	
	static boolean compute(String input) {
		TrieNode root = new TrieNode();
		for (int i = 0; i < dictionary.length; i++) {
			insertNode(dictionary[i], root);
		}
		if (search(input, root) || input.length() == 0)
			return true;
		else {
			int i = 0;
			String str = "";
			while (i < input.length()) {
				str += Character.toString(input.charAt(i));
				if (search(str, root)) {
					if (i == input.length()-1)
						return true;
					str = "";
				}
				i++;
			}
		}
		
		return false;
	}
	public static void main(String[] args) {
		System.out.println(compute("manandgo"));
	}
}

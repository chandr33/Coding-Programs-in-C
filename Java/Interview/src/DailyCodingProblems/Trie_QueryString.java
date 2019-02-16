package DailyCodingProblems;

import java.util.*;


/*
 * Functions to insert, search and delete a trie node are implemented
 */

class TrieNode {
	Map<Character,TrieNode> node_char_set;
	boolean endOfWord;
	TrieNode() {
		node_char_set = new HashMap<>();
		endOfWord = false;
	}
}

public class Trie_QueryString {
	private final TrieNode root;
	
	public Trie_QueryString() {
		root = new TrieNode();
	}
	
	public void insertNode(String word) {
		int i = 0;
		TrieNode current = root;
		while (i < word.length()) {
			char ch = word.charAt(i);
			TrieNode node = current.node_char_set.get(ch);
			if (node == null) {
				node = new TrieNode();
				current.node_char_set.put(ch, node);
			}
			current = node;
			i++;
		}
		current.endOfWord = true;
	}
	
	public List<String> auto_complete(String query) {
		int i = 0;
		List<String> word_set = new ArrayList<>();
		String word = "";
		TrieNode current = root;
		while (i < query.length()) {
			char ch = query.charAt(i);
			TrieNode node = current.node_char_set.get(ch);
			if (node != null) {
				if (i < query.length()-1)
					word += ch;
				current = node;
			}
			else
				break;
			i++;
			
		}
		if (i < query.length())
			return null;
		Search(current, query.charAt(i-1), word_set, word);
		return word_set;
		
	}
	public void Search(TrieNode current_node, char ch, List<String> word_set, String word) {
		word += ch;
		if (current_node.endOfWord) {
			word_set.add(word);
		}
		Iterator it = current_node.node_char_set.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			char next_char = (char) pair.getKey();
			TrieNode next_node = (TrieNode) pair.getValue();
			Search(next_node, next_char, word_set, word);
		}
	}
	
	public static void printList(List<String> list) {
		if (list == null) {
			System.out.println("NULL");
			return;
		}
		for (String s : list)
			System.out.println(s);
	}
	
	public static void main(String[] args) {
		Trie_QueryString trie = new Trie_QueryString();
		trie.insertNode("diamond");
		trie.insertNode("diaspora");
		trie.insertNode("devil");
		trie.insertNode("dialect");
		List<String> word_list = new ArrayList<String>();
		word_list = trie.auto_complete("diaspora");
		printList(word_list);
		
	}
}

package DailyCodingProblems;

import java.util.ArrayList;
import java.util.Stack;

class Directory{
	String dir_name;
	int level;
	Directory(String name, int n) { dir_name = name; level = n; }
}

public class MaxPathFinder {
	public static String form_path(Stack<Directory> stack, String path_name, String word) {
		path_name += "/";
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < stack.size(); i++)
			list.add(stack.get(i).dir_name);
		for (int i = 0; i < list.size(); i++) {
			path_name += list.get(i) + "/";
		}
		path_name += word;
		return path_name;
	}
	public static int LongestPath(String input) {
		boolean file_found = false;
		int max_word_len = 0;
		String file_path = "";
		Stack<Directory> stack = new Stack<>();
		int curr_level = 0;
		int i = 0;
		while (i < input.length()) {
			if (input.charAt(i) == '\n')
				i++;
			else if (input.charAt(i) == '\t') {
				for (int j = i; input.charAt(j) == '\t'; j++)
					curr_level++;
				int end_index = (input.indexOf('\n',i+curr_level) == -1) ? input.length() : input.indexOf('\n',i+curr_level);  
				String word = input.substring(i+curr_level,end_index);
				if (word.contains(".")) { //File Found
					file_found = true;
					String temp = form_path(stack, file_path, word);
					max_word_len = Math.max(max_word_len, temp.length());
				}
				else {
					while (!stack.isEmpty() && stack.peek().level >= curr_level)
						stack.pop();
					stack.push(new Directory(word,curr_level));
				}
				i += word.length()+curr_level;
				curr_level = 0;
			}
			else {
				file_path += input.charAt(i);
				i++;
			}
		}
		if (file_path.contains(".")) {
			if (input.indexOf('\n') == -1) {
				return file_path.length();
			}
			else {
				return file_path.length()+1;
			}
		}
		if (!file_found)
			return 0;
		return max_word_len;
	}

	public static void main(String[] args) {
		String str = "dir\n    file.txt"; //12
		System.out.println(LongestPath(str));
	}
}

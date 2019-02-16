package DynamicProgramming;
import java.util.*;
public class LongestValidParentheses {
	static int compute(String s) {
		int max = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		int index = 0;
		while (index < s.length()) {
			if (s.charAt(index) == '(')
				stack.push(index);
			else {
				stack.pop();
				if (!stack.isEmpty()) {
					max = Math.max(max, index - stack.peek());
				}
				else
					stack.push(index);
			}
			index++;
		}
		return max;
	}
	
	static int compute_table(String s) {
		int max = 0;
		int table[] = new int[s.length()];
		Arrays.fill(table, 0);
		for (int i = 1; i < table.length; i++) {
			if (s.charAt(i) == ')') {
				if (s.charAt(i-1) == '(') {
					if (i >= 2)
						table[i] = table[i-2] + 2;
					else
						table[i] += 2;
				}
				else {
					if (i-table[i-1] >= 1 && s.charAt(i-table[i-1]-1) == '(') {
						if (i-table[i-1] <= 1)
							table[i] = table[i-1] + 2;
						else
							table[i] = table[i-1] + table[i-table[i-1]-2] + 2;
					}
				}
				max = Math.max(max, table[i]);
			}
		}
		return max;
	}
	

	public static void main(String[] args) {
		String s = "))))";
		System.out.println(compute_table(s));
	}
}

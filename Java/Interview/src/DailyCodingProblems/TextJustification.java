package DailyCodingProblems;

import java.util.Arrays;

public class TextJustification {
	private static int charCount(String[] words, int i, int j, int maxLen) {
		int count = 0;
		for (int k = i; k <= j; k++) {
			count += words[k].length();
		}
		if (j-i >= 1) {
			count += (j-i);
		}
		return (count > maxLen) ? Integer.MAX_VALUE : (int)Math.pow((maxLen-count), 2);
	}
	private static String justify(String[] words, int maxLen) {
		int[][] cost_matrix = new int[words.length][words.length];
		for (int i = 0; i < cost_matrix.length; i++) {
			for (int j = 0; j < cost_matrix[i].length; j++) {
				if (i <= j) {
					cost_matrix[i][j] = charCount(words, i, j, maxLen);
				}
			}
		}
		int[] cost_array = new int[words.length];
		int[] split_index = new int[words.length];
		Arrays.fill(cost_array, Integer.MAX_VALUE);
		int j = words.length-1;
		for (int i = words.length-1; i >= 0; i--) {
			for (int k = j; k >= i; k--) {
				if (cost_matrix[i][k] != Integer.MAX_VALUE) {
					if (k == j) {
						cost_array[i] = cost_matrix[i][k];
						split_index[i] = k+1;
					}
					else {
						if (cost_array[i] > cost_matrix[i][k]+cost_array[k+1]) {
							cost_array[i] = cost_matrix[i][k]+cost_array[k+1];
							split_index[i] = k+1;
						}
					}
				}
			}
		}
		String result = "";
		for (int k = 0; k < split_index.length; k = split_index[k]) {
			int l;
			for (l = k; l <= split_index[k]-1; l++) {
				result += words[l]+" ";
			}
			result += "\n";
		}
		return result;
	}
	public static void main(String[] args) {
		String[] words = {"The","quick","brown","fox","jumps","over","the","lazy","dog"};
		System.out.println(justify(words,16));
	}
}
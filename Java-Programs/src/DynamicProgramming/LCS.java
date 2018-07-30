package DynamicProgramming;

public class LCS {
	static int count(char seq1[], char seq2[]) {
		int index1 = seq1.length, index2 = seq2.length;
		int count = count_util(seq1, seq2, index1, index2);
		return count;
	}
	/*Recursive solution*/
	static int count_util(char seq1[], char seq2[], int index1, int index2) {
		if (index1 == 0 || index2 == 0)
			return 0;
		else if (seq1[index1 - 1] == seq2[index2 - 1]) {
			return (1 + count_util(seq1, seq2, index1 - 1, index2 - 1));
		}
		return Math.max(count_util(seq1, seq2, index1, index2 - 1), count_util(seq1, seq2, index1 - 1, index2));
		
	}
	
	static int count_Table(char seq1[], char seq2[]) {
		int table[][] = new int[seq1.length + 1][seq2.length + 1];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (i == 0 || j == 0)
					table[i][j] = 0;
				else if(seq1[i - 1] != seq2[j - 1]) {
					table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
				}
				else if(seq1[i - 1] == seq2[j - 1]) {
					table[i][j] = 1 + table[i-1][j-1];
				}
			}
		}
		return table[seq1.length][seq2.length];
	}
	public static void main(String[] args) {
		char sequence1[] = new char[]{'A','G','G','T','A','B'};
		char sequence2[] = new char[]{'G','X','T','X','A','Y','B'};
		System.out.println(count_Table(sequence1,sequence2));
	}
}

package Graphs;
import java.util.*;

public class BoggleSearch {
	char boggleArray[][];
	String dictionary[];
	char node_list[];
	public static final int ROWS = 3;
	public static final int COLUMNS = 3;
	
	BoggleSearch(char boggleArray[][]) {
		this.boggleArray = new char[boggleArray.length][boggleArray[0].length];
		node_list = new char[(boggleArray.length)*(boggleArray[0].length)];
		this.dictionary = new String[5];
		for (int i = 0; i < boggleArray.length; i++) {
			for (int j = 0; j < boggleArray[i].length; j++) {
				this.boggleArray[i][j] = boggleArray[i][j];
			}
		}
		dictionary = new String[]{"GEEKS","FOR","QUIZ","GO","SEEK"};
	}
	
	private void compute() {
		for (int i = 0; i < boggleArray.length; i++) {
			for (int j = 0; j < boggleArray[i].length; j++) {
				node_list[(i*boggleArray[i].length)+j] = boggleArray[i][j];
			}
		}
		search();
	}
	
	public void search() {
		int i = 0;
		while (i < dictionary.length) {
			int j;
			for (j = 0; j < dictionary[i].length(); j++) {
				if (!search_util(dictionary[i].charAt(j))) {
					break;
				}
			}
			if (j == dictionary[i].length()) {
				System.out.println(dictionary[i]);
			}
			i++;
		}
	}
	
	public boolean search_util(char letter) {
		boolean found = false;
		int i = 0;
		while ((!found) && (i != node_list.length)) {
			if (node_list[i] == letter) {
				found = true;
				i++;
			}
			else
				i++;
		}
		return found;
	}
	//visit neighboring cells through DFS and form all potential words starting with the passed character
	void traverseArray(char test[][], boolean visited[][], int i, int j, String str) {
		str += test[i][j];
		System.out.println(str);
		visited[i][j] = true;
		for (int row = i - 1; row <= i+1 && row < ROWS; row++) {
			for (int col = j - 1; col <= j+1 && col < COLUMNS; col++) {
				if (row >= 0 && col >= 0 && !visited[row][col]) {
					traverseArray(test, visited, row, col, str);
				}
			}
		}
		visited[i][j] = false;
	}
	
	public static void main(String[] args) {
		char boggleArray[][] = new char[][] {{'G','I','Z'},
											 {'U','E','K'},
											 {'Q','S','E'}};
		BoggleSearch word = new BoggleSearch(boggleArray);
		//word.compute();
		
		boolean visited[][] = new boolean[BoggleSearch.ROWS][BoggleSearch.COLUMNS];
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[i].length; j++) {
				visited[i][j] = false;
			}
		}
		String str = "";
		word.traverseArray(boggleArray, visited, 0, 0, str);
	}
}

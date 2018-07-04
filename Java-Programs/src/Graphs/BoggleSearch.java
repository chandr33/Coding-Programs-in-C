package Graphs;

public class BoggleSearch {
	char boggleArray[][];
	String dictionary[];
	char node_list[];
	
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
	
	public static void main(String[] args) {
		char boggleArray[][] = new char[][] {{'G','I','Z'},{'U','E','K'},{'Q','S','E'}};
		BoggleSearch word = new BoggleSearch(boggleArray);
		word.compute();
	}
}

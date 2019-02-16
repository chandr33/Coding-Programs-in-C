package DynamicProgramming;
import java.util.*;
public class BooleanParenthesization {
	
	static class Pair {
		int trueCount = 0;
		int falseCount = 0;
	}
	
	static int countWays(Character symbols[],Character operators[]) {
		Character expression[] = new Character[symbols.length+operators.length];
		int even = 0;
		int charIndex = 0, opIndex = 0;
		for (int i = 0; i < expression.length; i++) {
			expression[i] = (even % 2 == 0) ? symbols[charIndex++] : operators[opIndex++];
			even++;
		}
		HashMap<String,Pair> table = new HashMap<>();
		int i = 0, j = expression.length-1;
		Pair newPair = new Pair();
		newPair.trueCount = newPair.falseCount = 0;
		count(expression,i,j,newPair,table);
		return newPair.trueCount;
	}
	
	static void count(Character expression[], int i, int j, Pair newPair, HashMap<String,Pair>table) {
		String key = Integer.toString(i) + Integer.toString(j);
		
		if (table.containsKey(key)) {
			newPair.trueCount = table.get(key).trueCount;
			newPair.falseCount = table.get(key).falseCount;
			return;
		}
		
		if (i == j) {
			if (expression[i] == 'T') {
				newPair.trueCount = 1;
			}
			else {
				newPair.falseCount = 1;
			}
			return;
		}
		
		for (int k = i+1; k <= j-1; k++) {
			Pair newPair1 = new Pair();
			Pair newPair2 = new Pair();
			if (expression[k] == '&') {
				count(expression, i, k-1, newPair1, table);
				count(expression, k+1, j, newPair2, table);
				newPair.trueCount += newPair1.trueCount*newPair2.trueCount;
				newPair.falseCount += (newPair1.trueCount*newPair2.falseCount) + (newPair2.trueCount*newPair1.falseCount) + (newPair1.falseCount*newPair2.falseCount);
			}
			else if(expression[k] == '|') {
				count(expression, i, k-1, newPair1, table);
				count(expression, k+1, j, newPair2, table);
				newPair.trueCount += (newPair1.trueCount*newPair2.falseCount) + (newPair2.trueCount*newPair1.falseCount) + (newPair1.trueCount*newPair2.trueCount);
				newPair.falseCount += (newPair1.falseCount*newPair2.falseCount);
			}
			else if(expression[k] == '^') {
				count(expression, i, k-1, newPair1, table);
				count(expression, k+1, j, newPair2, table);
				newPair.trueCount += (newPair1.trueCount*newPair2.falseCount) + (newPair2.trueCount*newPair1.falseCount);
				newPair.falseCount += (newPair1.trueCount*newPair2.trueCount) + (newPair1.falseCount*newPair2.falseCount);
			}
		}
		table.put(key, newPair);
		return;
	}
	
	static int ParenthesizationTable(Character symbols[], Character operators[]) {
		int falseTable[][] = new int[symbols.length][symbols.length];
		int trueTable[][] = new int[symbols.length][symbols.length];
		
		for (int i = 0; i < symbols.length; i++) {
			trueTable[i][i] = (symbols[i] == 'T') ? 1 : 0;
			falseTable[i][i] = (symbols[i] == 'F') ? 1 : 0;
		}
		
		for (int i = 2; i <= symbols.length; i++) {
			for (int j = 0; j <= symbols.length-i; j++) {
				int gap = i+j-1;
				trueTable[j][gap] = falseTable[j][gap] = 0;//Assign default value of 0
				for (int k = 0; k < gap; k++) {//Evaluate all the oprators in the current gap
					int total1 = trueTable[j][k] + falseTable[j][k];//Total of true and false counts from startIndex to k
					int total2 = trueTable[k+1][gap] + falseTable[k+1][gap];//Total of true and false counts from k to endIndex
					
					if(operators[k] == '|') {
						trueTable[j][gap] += ((total1*total2) - (falseTable[j][k]*falseTable[k+1][gap]));
						falseTable[j][gap] += falseTable[j][k]*falseTable[k+1][gap];
					}
					
					else if(operators[k] == '&') {
						trueTable[j][gap] += trueTable[j][k]*trueTable[k+1][gap];
						falseTable[j][gap] += ((total1*total2) - (trueTable[j][k]*trueTable[k+1][gap]));
					}
					
					else if(operators[k] == '^') {
						trueTable[j][gap] += (trueTable[j][k]*falseTable[k+1][gap] + falseTable[j][k]*trueTable[k+1][gap]);
						falseTable[j][gap] += (trueTable[j][k]*trueTable[k+1][gap] + falseTable[j][k]*falseTable[k+1][gap]);
					}
				}
			}
		}
		return trueTable[0][symbols.length-1];
	}
	
	public static void main(String[] args) {
		Character symbols[] = {'T','T','F','T','T','F'};
		Character operators[] = {'|','&','^','&','&'};
		System.out.println(countWays(symbols, operators));
		System.out.println(ParenthesizationTable(symbols, operators));
	}
}

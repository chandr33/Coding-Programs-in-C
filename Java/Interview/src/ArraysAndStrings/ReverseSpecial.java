package ArraysAndStrings;

public class ReverseSpecial {
	
	static Character[] reverse(Character[] StrToRev) {
		Character result[] = new Character[StrToRev.length];
		int startIndex = 0;
		int endIndex = result.length - 1;
		while (startIndex != endIndex) {
			if (isLetter(StrToRev[startIndex]) && isLetter(StrToRev[endIndex])) {
				result[startIndex] = StrToRev[endIndex];
				result[endIndex] = StrToRev[startIndex];
				startIndex++;
				endIndex--;
			}
			else if (!isLetter(StrToRev[startIndex])){
				result[startIndex] = StrToRev[startIndex];
				startIndex++;
			}
			else if(!isLetter(StrToRev[endIndex])) {
				result[endIndex] = StrToRev[endIndex];
				endIndex--;
			}
		}
		result[startIndex] = StrToRev[startIndex];
		return result;
	}
	
	static boolean isLetter(Character c) {
		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
			return true;
		return false;
	}
	public static void main(String[] args) {
		Character StrToRev[] = {'A','b',',','c',',','d','e','!','$'};
		Character result[] = reverse(StrToRev);
		for (Character c : result)
			System.out.print(c + " ");
	}
}

package DailyCodingProblems;

public class RunlengthEncoding {
	private static boolean isNum(char c) {
		if (c >= '0' && c <= '9')
			return true;
		return false;
	}
	private static String encode(String str) {
		String result = "";
		int count = 1;
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i) == str.charAt(i-1)) {
				count++;
			}
			else {
				result += Integer.toString(count)+Character.toString(str.charAt(i-1));
				count = 1;
			}
		}
		result += Integer.toString(count)+Character.toString(str.charAt(str.length()-1));
		return result;
	}
	private static String decode(String str) {
		String result = "";
		int num_index = 0;
		for (int i = 1; i < str.length(); i++) {
			if (!isNum(str.charAt(i))) {
				String number = str.substring(num_index,i);
				int num = Integer.parseInt(number);
				for (int j = 0; j < num; j++) {
					result += Character.toString(str.charAt(i));
				}
				num_index = i+1;
			}
		}
		return result;
	}
	public static void main(String[] args) {
		String str = "FFFFFFFFFFA";
		System.out.println(decode(encode(str)));
	}
}

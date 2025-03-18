

public class Tools {

	public static String trim(String x) {
		String s = "";
		for (int i = 0; i < x.length(); i++) {
			char c = x.charAt(i);
			if (c != ' ') {
				s = s + c;
			}
		}
		return s;
	}
	
}





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
	
	public static String virgolaPunto(String s) {
		String nuova = "";

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '.') {
				nuova = nuova + ',';
			} else {
				nuova = nuova + s.charAt(i);
			}
		}

		return nuova;
	}
	
	public static double arrotondaSecondaCifra(double d) {
		d = Math.round(d * 100);
		d = d / 100;
		return d;
	}
	
}

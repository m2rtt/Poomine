package application;

class ValeTaheErind extends Exception {
	ValeTaheErind() {
		super();
	}

	ValeTaheErind(String s) {
		super(s);
	}
}

public class Kontroll extends Main {
	private static String t�hestik = "abcdefghijklmnopqrsztuvw����xy";
	static public boolean KasT�htOnS�nas(String s�na, String pakutudt�ht, int errors) {
		// kui t�ht ei leidu s�nas siis errors +1
		if (s�na.indexOf(pakutudt�ht) == -1) {

			return false;
		} else {
			return true;
		}
	}

	static public String Asendakriipsud(String s�na, String s�nakriipsudena, String pakutudt�ht) throws ValeTaheErind {
		// asendab '_' t�htedega kui need s�nas leiduvad
		// char pakutudt�ht2 = pakutudt�ht.charAt(0);
		StringBuilder uus1 = new StringBuilder(s�na);
		StringBuilder uus2 = new StringBuilder(s�nakriipsudena);
		/*
		 * for (int i = 0; i < s�nakriipsudena.length(); i++) { StringBuilder
		 * str = new StringBuilder(s�nakriipsudena); if
		 * (s�nakriipsudena.charAt(i) == pakutudt�ht2) { str.setCharAt(i,
		 * pakutudt�ht2); } s�nakriipsudena = str.toString(); }
		 */
		if(pakutudt�ht.isEmpty()) {
			throw new ValeTaheErind("Seda t�hte ei ole t�hestikus");
		}
		char t = pakutudt�ht.charAt(0);
		if(t�hestik.indexOf(t) == -1) {
			throw new ValeTaheErind("Seda t�hte ei ole t�hestikus");
		}
		while (uus1.indexOf(pakutudt�ht) != -1) {
			
			int t�heindeks = uus1.indexOf(pakutudt�ht);
			uus1.setCharAt(t�heindeks, '0');
			uus2.setCharAt(t�heindeks, pakutudt�ht.charAt(0));

		}
		return uus2.toString();
	}

	public static boolean KasArvatud(String s�na) {
		// kui k�ik t�hed on arvatud
		String t = new String(s�na);
		if (t.indexOf('_') == -1) {
			// v�itsid ja teeb midagi
			return true;
		}
		return false;
	}
}

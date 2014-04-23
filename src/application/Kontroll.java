package application;

class ValeTaheErind extends Exception {
	/**
	 * sai v�hemalt h��um�rgist lahti
	 */
	private static final long serialVersionUID = 1L;

	ValeTaheErind() {
		super();
	}

	ValeTaheErind(String s) {
		super(s);
	}
}

public class Kontroll extends Main {
	private static String t�hestik = "abcdefghijklmnopqrsztuvw����xy";
	static public boolean KasT�htOnS�nas(String s�na, String pakutudt�ht, int errors) throws ValeTaheErind {
		// kui t�ht ei leidu s�nas siis errors +1
		char t = pakutudt�ht.charAt(0);
		if(t�hestik.indexOf(t) == -1 || pakutudt�ht.isEmpty()) {
			throw new ValeTaheErind("Seda t�hte ei ole t�hestikus");
		}
		if (s�na.indexOf(pakutudt�ht) == -1) {

			return false;
		} else {
			return true;
		}
	}

	static public String Asendakriipsud(String s�na, String s�nakriipsudena, String pakutudt�ht){
		// asendab '_' t�htedega kui need s�nas leiduvad
		StringBuilder uus1 = new StringBuilder(s�na);
		StringBuilder uus2 = new StringBuilder(s�nakriipsudena);
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

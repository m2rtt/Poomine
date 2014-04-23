package application;

class ValeTaheErind extends Exception {
	/**
	 * sai vähemalt hüüumärgist lahti
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
	private static String tähestik = "abcdefghijklmnopqrsztuvwõäöüxy";
	static public boolean KasTähtOnSõnas(String sõna, String pakutudtäht, int errors) throws ValeTaheErind {
		// kui täht ei leidu sõnas siis errors +1
		char t = pakutudtäht.charAt(0);
		if(tähestik.indexOf(t) == -1 || pakutudtäht.isEmpty()) {
			throw new ValeTaheErind("Seda tähte ei ole tähestikus");
		}
		if (sõna.indexOf(pakutudtäht) == -1) {

			return false;
		} else {
			return true;
		}
	}

	static public String Asendakriipsud(String sõna, String sõnakriipsudena, String pakutudtäht){
		// asendab '_' tähtedega kui need sõnas leiduvad
		StringBuilder uus1 = new StringBuilder(sõna);
		StringBuilder uus2 = new StringBuilder(sõnakriipsudena);
		while (uus1.indexOf(pakutudtäht) != -1) {
			
			int täheindeks = uus1.indexOf(pakutudtäht);
			uus1.setCharAt(täheindeks, '0');
			uus2.setCharAt(täheindeks, pakutudtäht.charAt(0));

		}
		return uus2.toString();
	}

	public static boolean KasArvatud(String sõna) {
		// kui kõik tähed on arvatud
		String t = new String(sõna);
		if (t.indexOf('_') == -1) {
			// võitsid ja teeb midagi
			return true;
		}
		return false;
	}
}

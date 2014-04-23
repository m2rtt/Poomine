package application;

public class Kontroll extends Main {
	
	static public boolean KasTähtOnSõnas(String sõna, String pakutudtäht, int errors){
    //kui täht ei leidu sõnas siis errors +1
    if (sõna.indexOf(pakutudtäht) == -1){
            errors++;
                      
            if (errors == 7){
            	//mängu lõpp kuna erroreid liiga palju (errors = 10)
            	//return midagi;
            }  
            return false;
    }
    else {
    	return true;
    }
	}
    static public String Asendakriipsud(String sõnakriipsudena, String pakutudtäht){
    //asendab '_' tähtedega kui need sõnas leiduvad
    char pakutudtäht2 = pakutudtäht.charAt(0);
    for (int i=0; i < sõnakriipsudena.length(); i++){
            if (sõnakriipsudena.charAt(i) == pakutudtäht2){
            	StringBuilder str = new StringBuilder(sõnakriipsudena);
                str.setCharAt(i, pakutudtäht2);
            	sõnakriipsudena = str.toString();
            }
    }
    return sõnakriipsudena;
    }
    
    public static boolean KasArvatud(String sõna) {
    // kui kõik tähed on arvatud
    String t = new String(sõna);
    if (t.indexOf('_') == -1){
    	//võitsid ja teeb midagi
    	return true;
    }
    return false;
    }
    
    public boolean kasTäht(char t) {
    	/*
    	 * kui vajutatud täht eksisteerib sõnas
    	 * siis returnib 'true', else 'false'
    	 */
    	return true;
    }

}

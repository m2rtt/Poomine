package application;

public class Kontroll extends Main {
	
	static public boolean KasT�htOnS�nas(String s�na, String pakutudt�ht, int errors){
    //kui t�ht ei leidu s�nas siis errors +1
    if (s�na.indexOf(pakutudt�ht) == -1){
            errors++;
                      
            if (errors == 7){
            	//m�ngu l�pp kuna erroreid liiga palju (errors = 10)
            	//return midagi;
            }  
            return false;
    }
    else {
    	return true;
    }
	}
    static public String Asendakriipsud(String s�na, String pakutudt�ht){
    //asendab '_' t�htedega kui need s�nas leiduvad
    char pakutudt�ht2 = pakutudt�ht.charAt(0);
    for (int i=0; i < s�na.length(); i++){
            if (s�na.charAt(i) == pakutudt�ht2){
            	StringBuilder str = new StringBuilder(s�na);
                str.setCharAt(i, pakutudt�ht2);
            	s�na = str.toString();
            }
    }
    return s�na;
    }
    
    public static boolean KasArvatud(String s�na) {
    // kui k�ik t�hed on arvatud
    String t = new String(s�na);
    if (t.indexOf('_') == -1){
    	//v�itsid ja teeb midagi
    	return true;
    }
    return false;
    }
    
    public boolean kasT�ht(char t) {
    	/*
    	 * kui vajutatud t�ht eksisteerib s�nas
    	 * siis returnib 'true', else 'false'
    	 */
    	return true;
    }

}

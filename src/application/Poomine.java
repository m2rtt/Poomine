package application;


import java.io.FileNotFoundException;

public class Poomine {
	static final int lõpp = 10;   
    private int errors;
    String sõna;
    private static String[] sõnalist;
    
    public static void faililugeja() throws FileNotFoundException{
		
		java.io.File fail = new java.io.File("sõnad.txt"); 
		java.util.Scanner sc = new java.util.Scanner(fail);
		int counter=0;
		while (sc.hasNextLine()) {
		    String rida = sc.nextLine();
		    sõnalist[counter] = rida;
		    counter+=1;
		}
	}
    
    public void mängi() {
    	
    	int arv = (int) Math.random()*(sõnalist.length)+0;
    	sõna = sõnalist[arv];
    	
    	char tähed[] = new char[sõna.length()];
        for (int i=0; i<sõna.length(); i++) {
                tähed[i] = '_';
        }
    }
    
    public void kontrolli() {

        char pakutudtäht = 0;
        
        //   
        if (sõna.indexOf(pakutudtäht) == -1){

                errors++;
                if (errors == lõpp){
                	//mängu lõpp kuna erroreid liiga palju

                }
                
                return;
        }

        //asendab '_' tähtedega

        for (int i=0; i < sõna.length(); i++){
                if (sõna.charAt(i) == pakutudtäht){
                	StringBuilder str = new StringBuilder(sõna);
                    str.setCharAt(i, pakutudtäht);
                	sõna = str.toString();

                }
        }

        // kui kõik tähed on arvatud
        String t = new String(sõna);
        if (t.indexOf('_') == -1){
        	//võitsid
        }
        
    }
    public void joonista() {
    	//joonistab hangmani
    }

}

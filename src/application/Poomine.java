package application;


import java.io.FileNotFoundException;

public class Poomine {
	static final int l�pp = 10;   
    private int errors;
    String s�na;
    private static String[] s�nalist;
    
    public static void faililugeja() throws FileNotFoundException{
		
		java.io.File fail = new java.io.File("s�nad.txt"); 
		java.util.Scanner sc = new java.util.Scanner(fail);
		int counter=0;
		while (sc.hasNextLine()) {
		    String rida = sc.nextLine();
		    s�nalist[counter] = rida;
		    counter+=1;
		}
	}
    
    public void m�ngi() {
    	
    	int arv = (int) Math.random()*(s�nalist.length)+0;
    	s�na = s�nalist[arv];
    	
    	char t�hed[] = new char[s�na.length()];
        for (int i=0; i<s�na.length(); i++) {
                t�hed[i] = '_';
        }
    }
    
    public void kontrolli() {

        char pakutudt�ht = 0;
        
        //   
        if (s�na.indexOf(pakutudt�ht) == -1){

                errors++;
                if (errors == l�pp){
                	//m�ngu l�pp kuna erroreid liiga palju

                }
                
                return;
        }

        //asendab '_' t�htedega

        for (int i=0; i < s�na.length(); i++){
                if (s�na.charAt(i) == pakutudt�ht){
                	StringBuilder str = new StringBuilder(s�na);
                    str.setCharAt(i, pakutudt�ht);
                	s�na = str.toString();

                }
        }

        // kui k�ik t�hed on arvatud
        String t = new String(s�na);
        if (t.indexOf('_') == -1){
        	//v�itsid
        }
        
    }
    public void joonista() {
    	//joonistab hangmani
    }

}

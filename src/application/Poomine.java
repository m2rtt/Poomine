package application;


import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Poomine extends Main {
	static final int lõpp = 10;   
    private int errors;
    public String sõna;
    private static String[] sõnalist;
    public static String pakutudtäht;
    
    
    //loeb failist sõnad listi
    public static void faililugeja() throws FileNotFoundException {
		
		java.io.File fail = new java.io.File("sõnad.txt"); 
		java.util.Scanner sc = new java.util.Scanner(fail);
		int counter=0;
		while (sc.hasNextLine()) {
		    String rida = sc.nextLine();
		    sõnalist[counter] = rida;
		    counter+=1;
		}
		sc.close();
	}
    
    public void mängi() {
    	//genereerib random sõna listist
    	int arv = (int) Math.random()*(sõnalist.length)+0;
    	sõna = sõnalist[arv];
    	//loob sõnale vastava _ _ _ _ _ _ _ järjendi mille saab canvasele trükkida, hiljem muudab neid -> _ tähtedeks
    	char tähed[] = new char[sõna.length()];
        for (int i=0; i<sõna.length(); i++) {
                tähed[i] = '_';
        }

    }
    
    
    public static void joonista(int errors) {
    	//joonistab hangmani errorite kaupa
    	//errors=1 siis teeb esimese asja
    	//errors =2 siis teeb teise
    	//jne
    	Circle pea = new Circle();
    	if(errors >= 1) {

             pea.setCenterX(250);
             pea.setCenterY(290);
             pea.setRadius(10);
             pea.setStroke(Color.BLACK);
             root.getChildren().add(pea); //vaja parem asi välja mõelda kuhu ta neid addima hakkab, canvasele ei saa

    	}

    	if(errors >= 2) {
    		Line keha = new Line(250, 300, 250, 350);
            keha.setStrokeWidth(3);
            keha.setStroke(Color.BLACK);
            root.getChildren().add(keha);
            

    	}
    	if(errors >= 3) {
    		Line vasakkäsi = new Line(250, 320, 220, 290);
    		vasakkäsi.setStrokeWidth(5);
    		vasakkäsi.setStroke(Color.BLACK);
            root.getChildren().add(vasakkäsi);
            

    	}
    	if(errors >= 4) {
    		 Line paremkäsi = new Line(250, 320, 280, 290);
    		 paremkäsi.setStrokeWidth(5);
    		 paremkäsi.setStroke(Color.BLACK);
             root.getChildren().add(paremkäsi);
            

    	}
    	if(errors >= 5) {
            Line vasakjalg = new Line(250, 350, 220, 380);
            vasakjalg.setStrokeWidth(5);
            vasakjalg.setStroke(Color.BLACK);
            root.getChildren().add(vasakjalg);

    	}

    	if(errors >= 6) {
    		Line paremjalg = new Line(250, 350, 280, 380);
    		paremjalg.setStrokeWidth(5);
    		paremjalg.setStroke(Color.BLACK);
            root.getChildren().add(paremjalg);
            

    	}
    	if(errors >= 7) {
    		pea.setFill(Color.RED);
            pea.setStroke(Color.ORANGE);
            pea.setStrokeWidth(2);
            

    	}	
    }


    
    public boolean kasTäht(char t) {
    	/*
    	 * kui vajutatud täht eksisteerib sõnas
    	 * siis returnib 'true', else 'false'
    	 */
    	return true;
    }

}

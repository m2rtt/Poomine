package application;


import java.io.FileNotFoundException;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Poomine extends Main{
    
    public static void joonista(int errors) {
    	//joonistab hangmani errorite kaupa
    	//errors=1 siis teeb esimese asja
    	//errors =2 siis teeb teise
    	//jne
    	Circle pea = new Circle();
    	if(errors >= 1) {

             pea.setCenterX(230);
             pea.setCenterY(50);
             pea.setRadius(40);
             pea.setStroke(Color.BLACK);
             joonis.getChildren().add(pea); 

    	}

    	if(errors >= 2) {
    		Line keha = new Line(230, 80, 230, 350);
            keha.setStrokeWidth(3);
            keha.setStroke(Color.BLACK);
            joonis.getChildren().add(keha);
            

    	}
    	if(errors >= 3) {
    		Line vasakk�si = new Line(230, 120, 70, 200);
    		vasakk�si.setStrokeWidth(5);
    		vasakk�si.setStroke(Color.BLACK);
            joonis.getChildren().add(vasakk�si);
            

    	}
    	if(errors >= 4) {
    		 Line paremk�si = new Line(230, 120, 380, 200);
    		 paremk�si.setStrokeWidth(5);
    		 paremk�si.setStroke(Color.BLACK);
             joonis.getChildren().add(paremk�si);
            

    	}
    	if(errors >= 5) {
            Line vasakjalg = new Line(230, 350, 70, 450);
            vasakjalg.setStrokeWidth(5);
            vasakjalg.setStroke(Color.BLACK);
            joonis.getChildren().add(vasakjalg);

    	}

    	if(errors >= 6) {
    		Line paremjalg = new Line(230, 350, 380, 450);
    		paremjalg.setStrokeWidth(5);
    		paremjalg.setStroke(Color.BLACK);
            joonis.getChildren().add(paremjalg);
            

    	}
    	if(errors >= 7) {
    		pea.setFill(Color.RED);
            pea.setStroke(Color.ORANGE);
            pea.setStrokeWidth(2);
            

    	}	
    }

}
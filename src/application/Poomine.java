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
    		
            pea.setCenterX(330);
            pea.setCenterY(140);
            pea.setRadius(30);
            pea.setFill(Color.BLACK);
            joonis.getChildren().add(pea); 
            Line n��r = new Line(318, 80, 318, 168);
    		 n��r.setStrokeWidth(3);
            n��r.setStroke(Color.GREEN);
            joonis.getChildren().add(n��r);

   	}

   	if(errors >= 2) {
   		Line keha = new Line(318, 167, 318, 350);
           keha.setStrokeWidth(5);
           keha.setStroke(Color.BLACK);
           joonis.getChildren().add(keha);
           

   	}
   	if(errors >= 3) {
   		Line vasakk�si = new Line(318, 190, 270, 260);
   		vasakk�si.setStrokeWidth(4);
   		vasakk�si.setStroke(Color.BLACK);
           joonis.getChildren().add(vasakk�si);
           

   	}
   	if(errors >= 4) {
   		 Line paremk�si = new Line(318, 190, 366, 260);
   		 paremk�si.setStrokeWidth(4);
   		 paremk�si.setStroke(Color.BLACK);
            joonis.getChildren().add(paremk�si);
           

   	}
   	if(errors >= 5) {
           Line vasakjalg = new Line(318, 350, 280, 450);
           vasakjalg.setStrokeWidth(5);
           vasakjalg.setStroke(Color.BLACK);
           joonis.getChildren().add(vasakjalg);

   	}

   	if(errors >= 6) {
   		Line paremjalg = new Line(318, 350, 356, 450);
   		paremjalg.setStrokeWidth(5);
   		paremjalg.setStroke(Color.BLACK);
           joonis.getChildren().add(paremjalg);
           

   	}
    }

}
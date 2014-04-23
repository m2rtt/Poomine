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
            Line nöör = new Line(318, 80, 318, 168);
    		 nöör.setStrokeWidth(3);
            nöör.setStroke(Color.GREEN);
            joonis.getChildren().add(nöör);

   	}

   	if(errors >= 2) {
   		Line keha = new Line(318, 167, 318, 350);
           keha.setStrokeWidth(5);
           keha.setStroke(Color.BLACK);
           joonis.getChildren().add(keha);
           

   	}
   	if(errors >= 3) {
   		Line vasakkäsi = new Line(318, 190, 270, 260);
   		vasakkäsi.setStrokeWidth(4);
   		vasakkäsi.setStroke(Color.BLACK);
           joonis.getChildren().add(vasakkäsi);
           

   	}
   	if(errors >= 4) {
   		 Line paremkäsi = new Line(318, 190, 366, 260);
   		 paremkäsi.setStrokeWidth(4);
   		 paremkäsi.setStroke(Color.BLACK);
            joonis.getChildren().add(paremkäsi);
           

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
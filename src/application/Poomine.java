package application;


import java.io.FileNotFoundException;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Poomine extends Main{
	static Circle pea = new Circle();
	static Line keha = new Line();
	static Line n��r = new Line();
	static Line vasakk�si = new Line();
	static Line paremk�si = new Line();
	static Line vasakjalg = new Line();
	static Line paremjalg = new Line();
	
	public static void resetJoonista() {
		joonis.getChildren().removeAll(pea, n��r, keha, vasakk�si, paremk�si, vasakjalg, paremjalg);
	}
    
    public static void joonista(int errors) {
    	//joonistab hangmani errorite kaupa
    	//errors=1 siis teeb esimese asja
    	//errors =2 siis teeb teise
    	//jne
    	//joonis.getChildren().remove(pea, n��r, keha, vasakk�si, paremk�si, vasakjalg, paremjalg);
    	/*Circle pea = new Circle();
    	Line keha = new Line();
    	Line n��r = new Line();
    	Line vasakk�si = new Line();
   	    Line paremk�si = new Line();
   	    Line vasakjalg = new Line();
   	    Line paremjalg = new Line();
   	    */
   
   	 
    	if(errors >= 1) {
    		
            pea.setCenterX(330);
            pea.setCenterY(140);
            pea.setRadius(30);
            pea.setFill(Color.BLACK);
            joonis.getChildren().add(pea); 
            n��r.setStartX(318);
       		n��r.setStartY(80);
       		n��r.setEndX(318);
       		n��r.setEndY(168);
    		n��r.setStrokeWidth(3);
            n��r.setStroke(Color.GREEN);
            joonis.getChildren().add(n��r);

   	}

   	if(errors >= 2) {
   		keha.setStartX(318);
   		keha.setStartY(167);
   		keha.setEndX(318);
   		keha.setEndY(350);
        keha.setStrokeWidth(5);
        keha.setStroke(Color.BLACK);
        joonis.getChildren().add(keha);
           

   	}
   	if(errors >= 3) {
   		vasakk�si.setStartX(318);
   		vasakk�si.setStartY(190);
   		vasakk�si.setEndX(270);
   		vasakk�si.setEndY(260);
   		vasakk�si.setStrokeWidth(4);
   		vasakk�si.setStroke(Color.BLACK);
           joonis.getChildren().add(vasakk�si);
           

   	}
   	if(errors >= 4) {
   		paremk�si.setStartX(318);
   		paremk�si.setStartY(190);
   		paremk�si.setEndX(366);
   		paremk�si.setEndY(260);
   		 paremk�si.setStrokeWidth(4);
   		 paremk�si.setStroke(Color.BLACK);
            joonis.getChildren().add(paremk�si);
           

   	}
   	if(errors >= 5) {
           vasakjalg.setStartX(318);
      		vasakjalg.setStartY(350);
      		vasakjalg.setEndX(280);
      		vasakjalg.setEndY(450);
           vasakjalg.setStrokeWidth(5);
           vasakjalg.setStroke(Color.BLACK);
           joonis.getChildren().add(vasakjalg);

   	}

   	if(errors >= 6) {
   		paremjalg.setStartX(318);
  		paremjalg.setStartY(350);
  		paremjalg.setEndX(356);
  		paremjalg.setEndY(450);
   		paremjalg.setStrokeWidth(5);
   		paremjalg.setStroke(Color.BLACK);
        joonis.getChildren().add(paremjalg);
           

   	}
    }


}
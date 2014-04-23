package application;
	

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	 private int errors;
	 public String sõna;
	 private static String[] sõnalist;
	 public static String pakutudtäht;
	 public String sõnakriipsudena = "";

	
	Canvas canvas = new Canvas(400, 400);
	private Mangija playa;
	private Taimer taimer;
	protected static FlowPane root;
	
	// see asi töötab isegi jou
	public void runn() {
			root.setOnKeyPressed(new EventHandler<KeyEvent>() {
                public void handle(final KeyEvent keyEvent) {
                	pakutudtäht = keyEvent.getText();
                    System.out.println(keyEvent.getText());
                    keyEvent.consume();
                    
                }
            });
	}
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
	/*private VBox getNupud() {
		VBox nupud = new VBox();
		Button start = new Button("Alusta");
		start.setLayoutX(0); 
        start.setLayoutY(0);
		Button abi = new Button("Abi");
		abi.setLayoutX(100); 
        abi.setLayoutY(0);
		nupud.getChildren().addAll(start, abi);
		nupud.setSpacing(10);
		
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				/*
				 * alustab mängu
				 
			}
		});
		
		return nupud;
	}
	*/
	
	
    
	@Override
	public void start(Stage lava) {	
	
		
		lava.setTitle("Poomine");
		Label pealkiri = new Label("Hangman");
		pealkiri.setFont(Font.font ("Verdana", 40));
		
		root = new FlowPane();
		root.setLayoutX(20);
		root.setLayoutY(20);
		
		Button start = new Button("Alusta");
		Button abi = new Button("Abi");
		
		FlowPane nupud = new FlowPane(50, 50);
	    nupud.setAlignment(Pos.CENTER);
	    nupud.getChildren().addAll(start, abi);
	    
	    
	    //tekst mänguseisu jaoks(arvatav sõna, valesid jne)
	    Label tekst = new Label();
	    
	    
	    
	    VBox kokku = new VBox(10);
	    kokku.setAlignment(Pos.CENTER);
	    kokku.getChildren().addAll(pealkiri, nupud);

		root.getChildren().add(kokku);
	
		
		Scene scene = new Scene(root, 500, 600, Color.SNOW);
		
		//kui start vajutad siis mis juhtub
		start.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//genereerib random sõna listist
		    	int arv = (int) Math.random()*(sõnalist.length)+0;
		    	sõna = sõnalist[arv];
		    	//loob sõnale vastava _ _ _ _ _ _ _ järjendi mille saab canvasele trükkida, hiljem muudab neid -> _ tähtedeks
				for (int i = 0; i < sõna.length(); i++){
					sõnakriipsudena += "_";
				}
		    
			}
		}
				);
		
		
		//kui tähti vajutatakse siis mis teeb
		root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent keyEvent) {
            	pakutudtäht = keyEvent.getText();
                System.out.println(keyEvent.getText());
                keyEvent.consume();
                
                if(Kontroll.KasTähtOnSõnas(sõna, pakutudtäht, errors) == true) {
                	sõnakriipsudena = Kontroll.Asendakriipsud(sõna, pakutudtäht);
                }
                
                
                if(Kontroll.KasArvatud(sõnakriipsudena) == true) {
                	//YOU WIN ehk teeb midagi
                }
                
                
                
            }
        });
		
		
		lava.setResizable(false);
		lava.setScene(scene);
		lava.show();

	}

	
	public static void main(String[] args) {
		launch(args);
	}
}

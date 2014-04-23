package application;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

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
import javafx.scene.layout.AnchorPane;
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
	public String s�na;
	private static ArrayList<String> s�nalistt = new ArrayList<String>();
	//private static String[] s�nalist;
	public static String pakutudt�ht;
	public String s�nakriipsudena = "";

	static Pane joonis = new Pane();
	private Mangija playa;
	private Taimer taimer;
	protected static AnchorPane root;

	// see asi t��tab isegi jou
	public void runn() {
		root.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent keyEvent) {
				pakutudt�ht = keyEvent.getText();
				System.out.println(keyEvent.getText());
				keyEvent.consume();

			}
		});
	}

	// loeb failist s�nad listi
	public static void faililugeja() throws FileNotFoundException {

		java.io.File fail = new java.io.File("src\\application\\s�nad.txt");
		java.util.Scanner sc = new java.util.Scanner(fail);
		int counter = 0;
		s�nalistt.removeAll(s�nalistt); // enne teeme s�nalisti t�hjaks
		while (sc.hasNextLine()) {
			String rida = sc.nextLine();
			s�nalistt.add(rida);
			System.out.println(s�nalistt.get(counter));
			counter += 1;
			
		}
		sc.close();
	}


	@Override
	public void start(Stage lava) {

        lava.setTitle("Poomine");
		
		//luuakse juur
		AnchorPane anchorpane = new AnchorPane();
		
		//m�ngu nimi
		Label pealkiri = new Label("Hangman");
		pealkiri.setFont(Font.font ("Verdana", 40));
		AnchorPane.setTopAnchor(pealkiri, 10.0);
        AnchorPane.setLeftAnchor(pealkiri, 10.0);
		
        //koht kuhu joonistatakse kriipsujuku
        joonis.setStyle("-fx-background-color: yellow;");
        AnchorPane.setTopAnchor(joonis, 65.0);
        AnchorPane.setLeftAnchor(joonis, 10.0);
        AnchorPane.setRightAnchor(joonis, 150.0);
        AnchorPane.setBottomAnchor(joonis, 10.0);
        
        //nupud alusta ja abi
        Button start = new Button("Alusta");
        AnchorPane.setTopAnchor(start, 10.0);
        AnchorPane.setRightAnchor(start, 10.0);
        Button abi = new Button("Abi");
        AnchorPane.setTopAnchor(abi, 40.0);
        AnchorPane.setRightAnchor(abi, 10.0);
        
        //tekst m�nguseisu jaoks(arvatav s�na, valesid jne)
	    Label tekst = new Label("Arvatav s�na: "+s�nakriipsudena+"\nVihje: lamp \nT�hed: "+pakutudt�ht);
	    AnchorPane.setTopAnchor(tekst, 70.0);
        AnchorPane.setRightAnchor(tekst, 10.0);
	    
        //addime k�ik eelneva childreniteks
        anchorpane.getChildren().addAll(joonis, start, abi, pealkiri, tekst);
	   	    
        Poomine.joonista(7);
	   
	
		
		Scene scene = new Scene(anchorpane, 600, 600, Color.SNOW);

		// kui start vajutad siis mis juhtub
		start.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// genereerib random s�na listist
				try {
					faililugeja();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				System.out.println(s�nalistt.size());
				Random rand = new Random();
				int arv = rand.nextInt(s�nalistt.size());
				s�na = s�nalistt.get(arv);
				System.out.println(s�na);
				// loob s�nale vastava _ _ _ _ _ _ _ j�rjendi mille saab
				// canvasele tr�kkida, hiljem muudab neid -> _ t�htedeks
				for (int i = 0; i < s�na.length(); i++) {
					s�nakriipsudena += "_";
				}

			}
		});

		// kui t�hti vajutatakse siis mis teeb
		anchorpane.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent keyEvent) {
				pakutudt�ht = keyEvent.getText();
				System.out.println(keyEvent.getText());
				keyEvent.consume();

				if (Kontroll.KasT�htOnS�nas(s�na, pakutudt�ht, errors) == true) {
					s�nakriipsudena = Kontroll.Asendakriipsud(s�na, pakutudt�ht);
				}

				if (Kontroll.KasArvatud(s�nakriipsudena) == true) {
					// YOU WIN ehk teeb midagi
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

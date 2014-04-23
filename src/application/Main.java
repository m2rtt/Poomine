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
	public String sõna;
	private static ArrayList<String> sõnalistt = new ArrayList<String>();
	//private static String[] sõnalist;
	public static String pakutudtäht;
	public String sõnakriipsudena = "";

	static Pane joonis = new Pane();
	private Mangija playa;
	private Taimer taimer;
	protected static AnchorPane root;

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

	// loeb failist sõnad listi
	public static void faililugeja() throws FileNotFoundException {

		java.io.File fail = new java.io.File("src\\application\\sõnad.txt");
		java.util.Scanner sc = new java.util.Scanner(fail);
		int counter = 0;
		sõnalistt.removeAll(sõnalistt); // enne teeme sõnalisti tühjaks
		while (sc.hasNextLine()) {
			String rida = sc.nextLine();
			sõnalistt.add(rida);
			System.out.println(sõnalistt.get(counter));
			counter += 1;
			
		}
		sc.close();
	}


	@Override
	public void start(Stage lava) {

        lava.setTitle("Poomine");
		
		//luuakse juur
		AnchorPane anchorpane = new AnchorPane();
		
		//mängu nimi
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
        
        //tekst mänguseisu jaoks(arvatav sõna, valesid jne)
	    Label tekst = new Label("Arvatav sõna: "+sõnakriipsudena+"\nVihje: lamp \nTähed: "+pakutudtäht);
	    AnchorPane.setTopAnchor(tekst, 70.0);
        AnchorPane.setRightAnchor(tekst, 10.0);
	    
        //addime kõik eelneva childreniteks
        anchorpane.getChildren().addAll(joonis, start, abi, pealkiri, tekst);
	   	    
        Poomine.joonista(7);
	   
	
		
		Scene scene = new Scene(anchorpane, 600, 600, Color.SNOW);

		// kui start vajutad siis mis juhtub
		start.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// genereerib random sõna listist
				try {
					faililugeja();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				System.out.println(sõnalistt.size());
				Random rand = new Random();
				int arv = rand.nextInt(sõnalistt.size());
				sõna = sõnalistt.get(arv);
				System.out.println(sõna);
				// loob sõnale vastava _ _ _ _ _ _ _ järjendi mille saab
				// canvasele trükkida, hiljem muudab neid -> _ tähtedeks
				for (int i = 0; i < sõna.length(); i++) {
					sõnakriipsudena += "_";
				}

			}
		});

		// kui tähti vajutatakse siis mis teeb
		anchorpane.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent keyEvent) {
				pakutudtäht = keyEvent.getText();
				System.out.println(keyEvent.getText());
				keyEvent.consume();

				if (Kontroll.KasTähtOnSõnas(sõna, pakutudtäht, errors) == true) {
					sõnakriipsudena = Kontroll.Asendakriipsud(sõna, pakutudtäht);
				}

				if (Kontroll.KasArvatud(sõnakriipsudena) == true) {
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

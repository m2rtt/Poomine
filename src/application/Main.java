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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {
	private int errors;
	public String vihje = "";
	public String sõna = "";
	private boolean kasTöötab = false;
	protected static ArrayList<String> vihjelist = new ArrayList<String>();
	protected static ArrayList<String> sõnalistt = new ArrayList<String>();
	//private static String[] sõnalist;
	public static String pakutudtäht;
	public String sõnakriipsudena = "";

	static Pane joonis = new Pane();
	private Mangija playa;
	private Taimer taimer;
	protected static AnchorPane root;

	// algväärtustamine
	public void resetGame() {
		vihje = "";
		sõna = "";
		kasTöötab = false;
		sõnakriipsudena = "";
		pakutudtäht = "";
		errors = 0;
	}

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
        AnchorPane.setRightAnchor(joonis, 200.0);
        AnchorPane.setBottomAnchor(joonis, 10.0);
        
        //nupud alusta ja abi
        final Button start = new Button("Alusta");
        AnchorPane.setTopAnchor(start, 10.0);
        AnchorPane.setRightAnchor(start, 10.0);
        final Button vihjenupp = new Button("Vihje");
        AnchorPane.setTopAnchor(vihjenupp, 40.0);
        AnchorPane.setRightAnchor(vihjenupp, 10.0);
        

        //tekst mänguseisu jaoks(arvatav sõna, valesid jne)
	    final Label tekst = new Label("Arvatav sõna: \nVihje: \nTähed: ");
	    tekst.setFont(Font.font ("Verdana", 20));
	    AnchorPane.setTopAnchor(tekst, 70.0);
        AnchorPane.setRightAnchor(tekst, 10.0);
	    
        //addime kõik eelneva childreniteks
        anchorpane.getChildren().addAll(joonis, start, vihjenupp, pealkiri, tekst);
        
        //poomispost
        Rectangle alus = new Rectangle(10, 500, 120, 30);
    	alus.setFill(Color.BROWN);
    	joonis.getChildren().add(alus);
    	Rectangle post = new Rectangle(55, 20, 25, 480);
    	post.setFill(Color.BROWN);
    	joonis.getChildren().add(post);
    	Rectangle ots = new Rectangle(55, 20, 250, 25);
    	ots.setFill(Color.BROWN);
    	joonis.getChildren().add(ots);
    	Line osa = new Line(65, 130, 150, 30);
        osa.setStrokeWidth(10);
        osa.setStroke(Color.BROWN);
        joonis.getChildren().add(osa);
        Rectangle otsalla = new Rectangle(305, 20, 25, 60);
    	otsalla.setFill(Color.BROWN);
    	joonis.getChildren().add(otsalla);
	   	     
	
		
		Scene scene = new Scene(anchorpane, 600, 600, Color.SNOW);
		vihjenupp.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(kasTöötab == true) {
					vihje=vihjelist.get(sõnalistt.indexOf(sõna));
					tekst.setText("Arvatav sõna: "+sõnakriipsudena+"\nVihje: "+vihje+"\nValitud täht: "+pakutudtäht+"\nErrors: "+errors);
					System.out.println(vihje);
				}
			}
		});
		// kui start vajutad siis mis juhtub
		start.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// genereerib random sõna listist
				start.setDisable(true);
				resetGame(); // algväärtustame muutujad
				kasTöötab = true; // määrame töötamise tõeseks
				//sõnakriipsudena = "";
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
				errors = 0;
				pakutudtäht = "";
				tekst.setText("Arvatav sõna: "+sõnakriipsudena+"\nVihje: "+vihje+"\nValitud täht: "+pakutudtäht+"\nErrors: "+errors);

			}
		});

		// kui tähti vajutatakse siis mis teeb
		anchorpane.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent keyEvent) {
				pakutudtäht = keyEvent.getText();
				System.out.println(keyEvent.getText());
				keyEvent.consume();

				if (Kontroll.KasTähtOnSõnas(sõna, pakutudtäht, errors) == true) {
					try {
						sõnakriipsudena = Kontroll.Asendakriipsud(sõna, sõnakriipsudena, pakutudtäht);
					} catch (ValeTaheErind e) {
						System.out.println("Tekkis erind: Seda tähte pole tähestikus");
					}
				}
				else {
					errors++;
				}
				
				tekst.setText("Arvatav sõna: "+sõnakriipsudena+"\nVihje: "+vihje+"\nValitud täht: "+pakutudtäht+"\nErrors: "+errors);
				
				if (Kontroll.KasArvatud(sõnakriipsudena) == true) {
					// YOU WIN ehk teeb midagi
					tekst.setText("Arvatav sõna: "+sõnakriipsudena+"\nVihje: "+vihje+"\nValitud täht: "+pakutudtäht+"\nErrors: "+errors+"\nSINA VÕITSID!");
					start.setDisable(false);
					resetGame();
				}
				Poomine.joonista(errors);
				if (errors == 7) {
					tekst.setText("KAOTASID!\nLiiga palju erroreid!");
					start.setDisable(false);
					resetGame();
				}

			}
		});

		//lava.setResizable(false);
		lava.setScene(scene);
		lava.show();

	}

	public static void main(String[] args) {
		try {
			Faililugeja.faililugeja();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		launch(args);
	}
}

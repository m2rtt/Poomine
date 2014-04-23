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
	public String s�na = "";
	private boolean kasT��tab = false;
	protected static ArrayList<String> vihjelist = new ArrayList<String>();
	protected static ArrayList<String> s�nalistt = new ArrayList<String>();
	//private static String[] s�nalist;
	public static String pakutudt�ht;
	public String s�nakriipsudena = "";

	static Pane joonis = new Pane();
	private Mangija playa;
	private Taimer taimer;
	protected static AnchorPane root;

	// algv��rtustamine
	public void resetGame() {
		vihje = "";
		s�na = "";
		kasT��tab = false;
		s�nakriipsudena = "";
		pakutudt�ht = "";
		errors = 0;
	}

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
        AnchorPane.setRightAnchor(joonis, 200.0);
        AnchorPane.setBottomAnchor(joonis, 10.0);
        
        //nupud alusta ja abi
        final Button start = new Button("Alusta");
        AnchorPane.setTopAnchor(start, 10.0);
        AnchorPane.setRightAnchor(start, 10.0);
        final Button vihjenupp = new Button("Vihje");
        AnchorPane.setTopAnchor(vihjenupp, 40.0);
        AnchorPane.setRightAnchor(vihjenupp, 10.0);
        

        //tekst m�nguseisu jaoks(arvatav s�na, valesid jne)
	    final Label tekst = new Label("Arvatav s�na: \nVihje: \nT�hed: ");
	    tekst.setFont(Font.font ("Verdana", 20));
	    AnchorPane.setTopAnchor(tekst, 70.0);
        AnchorPane.setRightAnchor(tekst, 10.0);
	    
        //addime k�ik eelneva childreniteks
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
				if(kasT��tab == true) {
					vihje=vihjelist.get(s�nalistt.indexOf(s�na));
					tekst.setText("Arvatav s�na: "+s�nakriipsudena+"\nVihje: "+vihje+"\nValitud t�ht: "+pakutudt�ht+"\nErrors: "+errors);
					System.out.println(vihje);
				}
			}
		});
		// kui start vajutad siis mis juhtub
		start.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// genereerib random s�na listist
				start.setDisable(true);
				resetGame(); // algv��rtustame muutujad
				kasT��tab = true; // m��rame t��tamise t�eseks
				//s�nakriipsudena = "";
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
				errors = 0;
				pakutudt�ht = "";
				tekst.setText("Arvatav s�na: "+s�nakriipsudena+"\nVihje: "+vihje+"\nValitud t�ht: "+pakutudt�ht+"\nErrors: "+errors);

			}
		});

		// kui t�hti vajutatakse siis mis teeb
		anchorpane.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent keyEvent) {
				pakutudt�ht = keyEvent.getText();
				System.out.println(keyEvent.getText());
				keyEvent.consume();

				if (Kontroll.KasT�htOnS�nas(s�na, pakutudt�ht, errors) == true) {
					try {
						s�nakriipsudena = Kontroll.Asendakriipsud(s�na, s�nakriipsudena, pakutudt�ht);
					} catch (ValeTaheErind e) {
						System.out.println("Tekkis erind: Seda t�hte pole t�hestikus");
					}
				}
				else {
					errors++;
				}
				
				tekst.setText("Arvatav s�na: "+s�nakriipsudena+"\nVihje: "+vihje+"\nValitud t�ht: "+pakutudt�ht+"\nErrors: "+errors);
				
				if (Kontroll.KasArvatud(s�nakriipsudena) == true) {
					// YOU WIN ehk teeb midagi
					tekst.setText("Arvatav s�na: "+s�nakriipsudena+"\nVihje: "+vihje+"\nValitud t�ht: "+pakutudt�ht+"\nErrors: "+errors+"\nSINA V�ITSID!");
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

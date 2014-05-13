package application;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Main extends Application {

	//hunnik arvumuutujaid
	static int TAIMERIAEG = 120; // m�ngu alustades 120sek
	int OIGESONA = 100; // lisab nii palju punkte
	int OIGETAHT = 10; // -::-
	int VIHJEMIINUS = 10; // v�tab nii palju punkte maha
	int VIHJEMIINUSAEG = 5; // v�tab selle v�rra aega maha
	int VALETAHT = 5; // sama analoogia eelmise kahega
	int VALETAHTAEG = 10; // -::-
	
	//Taimer ajaloendur = new Taimer();
	static Label pealkiri = new Label("Poomism�ng");
	private static int errors;
	public static String vihje = "";
	public static String s�na = "";
	private static boolean kasT��tab = false;
	protected static ArrayList<String> vihjelist = new ArrayList<String>();
	protected static ArrayList<String> s�nalist = new ArrayList<String>();
	// private static String[] s�nalist;
	public static String pakutudt�ht;
	public static String s�nakriipsudena = "";
	public static int punktid = 0;
	public static Set<String> pakutudt�hed = new HashSet<String>();
	double y = 1;
	double x = 1;
	
	final Label tekst = new Label("Arvatav s�na: \nVihje: \nT�hed: ");
	final static Button start = new Button("Alusta");
	final static Button vihjenupp = new Button("Vihje");


	static Pane joonis = new Pane();
	//private Mangija playa; // veel ei kasuta
	//private Taimer taimer; // veel ei kasuta
	protected static AnchorPane root;
	
	// http://java-buddy.blogspot.com/2012/06/update-ui-in-javafx-application-thread.html
	public static void muudaTekst(final String tekst) {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                pealkiri.setText(tekst);
            }
        });
	}
	// algv��rtustamine	
	public static void resetGame() {
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	Poomine.resetJoonista();	        
	        }
	   });
		Taimer.stopp();
		Taimer.setAeg(TAIMERIAEG);
		resetSona();
		start.setDisable(false);
		vihjenupp.setDisable(true);
		kasT��tab = false;
		errors = 0;
		punktid = 0;
		
	}
	public static void resetSona() {
		vihje = "";
		s�na = "";
		s�nakriipsudena = "";
		pakutudt�ht = "";
		pakutudt�hed.clear();
	}
	protected void uusSona() {
		resetSona();
		vihjenupp.setDisable(false);
		System.out.println(s�nalist.size());
		Random rand = new Random();
		int arv = rand.nextInt(s�nalist.size());
		s�na = s�nalist.get(arv);
		//System.out.println(s�na); // n�itab, palju neid s�nu �ldse listis on
		// loob s�nale vastava _ _ _ _ _ _ _ j�rjendi mille saab
		for (int i = 0; i < s�na.length(); i++) {
			s�nakriipsudena += "_";
		}
		//errors = 0;
		pakutudt�ht = "";
		tekst.setText("Arvatav s�na: \n" + s�nakriipsudena
				+ "\nVihje: \n" + vihje + "\nValitud t�ht: "
				+ pakutudt�ht + "\nVigu: " + errors + "\nPunkte: " + punktid);
		
	}

	@Override
	public void start(Stage lava) {

		// luuakse juur
		AnchorPane anchorpane = new AnchorPane();
		Scene scene = new Scene(anchorpane, 600, 570, Color.WHEAT);
		// m�ngu nimi
		//Label pealkiri = new Label("Poomism�ng");
		pealkiri.setText("Poomine");
		//muudaTekst("ogy");
		pealkiri.setFont(Font.font("Arial Bold", 40));
		;
		AnchorPane.setTopAnchor(pealkiri, 10.0);
		AnchorPane.setLeftAnchor(pealkiri, 10.0);

		// koht kuhu joonistatakse kriipsujuku
		joonis.setStyle("-fx-background-color: azure;");
		AnchorPane.setTopAnchor(joonis, 65.0);
		AnchorPane.setLeftAnchor(joonis, 10.0);
		AnchorPane.setRightAnchor(joonis, 200.0);
		AnchorPane.setBottomAnchor(joonis, 10.0);

		// nupud alusta ja abi
		AnchorPane.setTopAnchor(start, 10.0);
		AnchorPane.setRightAnchor(start, 10.0);
		AnchorPane.setTopAnchor(vihjenupp, 40.0);
		AnchorPane.setRightAnchor(vihjenupp, 10.0);

		// tekst m�nguseisu jaoks(arvatav s�na, valesid jne)
		tekst.setFont(Font.font("Century Gothic", 20));
		AnchorPane.setTopAnchor(tekst, 70.0);
		AnchorPane.setRightAnchor(tekst, 10.0);

		// addime k�ik eelneva childreniteks
		anchorpane.getChildren().addAll(joonis, start, vihjenupp, pealkiri, tekst);

		// poomispost
		Rectangle alus = new Rectangle(10, 460, 140, 20);
		alus.setFill(Color.BROWN);
		
		Rectangle post = new Rectangle(55, 20, 20, 450);
		post.setFill(Color.BROWN);
		
		Rectangle ots = new Rectangle(55, 20, 200, 20);
		ots.setFill(Color.BROWN);
		
		Line osa = new Line(65, 130, 150, 30);
		osa.setStrokeWidth(10);
		osa.setStroke(Color.BROWN);
		
		Rectangle otsalla = new Rectangle(250, 20, 20, 60);
		otsalla.setFill(Color.BROWN);
		
		Group poomispost = new Group();
		poomispost.getChildren().addAll(alus, post, ots, osa, otsalla);
		
		joonis.getChildren().add(poomispost);

		/* Hetkel ei oska midagi m�istlikku peale hakata sellega
		joonis.heightProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(final ObservableValue<?> o, final Object vana1, final Object uus1) {
				final double y1 = (double) uus1;
				y = y1 / 600.0;
			}
		});
		joonis.widthProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(final ObservableValue<?> o, final Object vana, final Object uus2) {
				final double x1 = (double) uus2;
				x = x1 / 600.0;
			}
		});
		*/


		vihjenupp.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (kasT��tab == true) {
					vihjenupp.setDisable(true);
					punktid -= VIHJEMIINUS;
					vihje = vihjelist.get(s�nalist.indexOf(s�na));
					tekst.setText("Arvatav s�na: \n" + s�nakriipsudena
							+ "\nVihje: \n" + vihje + "\nValitud t�ht: "
							+ pakutudt�ht + "\nVigu: " + errors + "\nPunkte: " + punktid);
					System.out.println(vihje);
					Taimer.setAeg(Taimer.getAeg() - VIHJEMIINUSAEG);
					
				}
			}
		});
		// kui start vajutad siis mis juhtub
		start.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// genereerib random s�na listist
				resetGame(); // algv��rtustame muutujad
				kasT��tab = true; // m��rame t��tamise t�eseks
				// s�nakriipsudena = "";
				Taimer.alusta();
				start.setDisable(true);
				uusSona();
			}
		});

		// kui t�hti vajutatakse siis mis teeb
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(final KeyEvent keyEvent) {
				if (kasT��tab) {
					pakutudt�ht = keyEvent.getText();
					System.out.println(keyEvent.getText());
					keyEvent.consume();

					try {
						if(pakutudt�hed.contains(pakutudt�ht)) {
							System.out.println("T�hte "+pakutudt�ht+" on juba pakutud");
						}
						else {
							pakutudt�hed.add(pakutudt�ht);
							if (Kontroll.KasT�htOnS�nas(s�na, pakutudt�ht, errors) == true) {
								s�nakriipsudena = Kontroll.Asendakriipsud(s�na,
									s�nakriipsudena, pakutudt�ht);
								punktid += OIGETAHT;
							} else {
								errors++;
								punktid -= VALETAHT;
								Taimer.setAeg(Taimer.getAeg()-VALETAHTAEG);
							}
						}
					} catch (ValeTaheErind e) {
						System.out
								.println("Tekkis erind: seda t�hte pole t�hestikus v�i vajutasid shift/tab/alt etc");
					}

					tekst.setText("Arvatav s�na: \n" + s�nakriipsudena
							+ "\nVihje: \n" + vihje + "\nValitud t�ht: "
							+ pakutudt�ht + "\nvigu: " + errors + "\nPunkte: " + punktid);

					if (Kontroll.KasArvatud(s�nakriipsudena) == true) {
						// YOU WIN ehk teeb midagi
						punktid += OIGESONA;
						tekst.setText("Arvatav s�na: \n" + s�nakriipsudena
								+ "\nVihje: \n" + vihje + "\nValitud t�ht: "
								+ pakutudt�ht + "\nvigu: " + errors
								+ "\n�ige!" + "\nPunkte: " + punktid);
						//start.setDisable(false);
						//resetGame();
						Taimer.setAeg(Taimer.getAeg()+30);
						uusSona();
						
					}
					Poomine.joonista(errors);
					if (errors == 7) {
						tekst.setText("KAOTASID!\nLiiga palju erroreid!\nL�ppskoor: "+ punktid);
						resetGame();
					}
				}
			}
		});
		
		lava.setTitle("Poomine");
		lava.setScene(scene);
		lava.setMinHeight(600); lava.setMaxHeight(700);
		lava.setMinWidth(550); lava.setMaxWidth(700);
		lava.show();
		lava.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	              System.out.println("Programm sulgub");
	              Taimer.stopp();
	          }
	      });        
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

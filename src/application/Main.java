package application;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
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

	//Taimer ajaloendur = new Taimer();
	static Label pealkiri = new Label("Poomismäng");
	private int errors;
	public String vihje = "";
	public String sõna = "";
	private boolean kasTöötab = false;
	protected static ArrayList<String> vihjelist = new ArrayList<String>();
	protected static ArrayList<String> sõnalist = new ArrayList<String>();
	// private static String[] sõnalist;
	public static String pakutudtäht;
	public String sõnakriipsudena = "";
	double y = 1;
	double x = 1;

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
	// algväärtustamine
	public void resetGame() {
		vihje = "";
		sõna = "";
		kasTöötab = false;
		sõnakriipsudena = "";
		pakutudtäht = "";
		errors = 0;
		Poomine.resetJoonista();

	}

	@Override
	public void start(Stage lava) {

		// luuakse juur
		AnchorPane anchorpane = new AnchorPane();

		// mängu nimi
		//Label pealkiri = new Label("Poomismäng");
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
		final Button start = new Button("Alusta");
		AnchorPane.setTopAnchor(start, 10.0);
		AnchorPane.setRightAnchor(start, 10.0);
		final Button vihjenupp = new Button("Vihje");
		AnchorPane.setTopAnchor(vihjenupp, 40.0);
		AnchorPane.setRightAnchor(vihjenupp, 10.0);

		// tekst mänguseisu jaoks(arvatav sõna, valesid jne)
		final Label tekst = new Label("Arvatav sõna: \nVihje: \nTähed: ");
		tekst.setFont(Font.font("Century Gothic", 20));
		AnchorPane.setTopAnchor(tekst, 70.0);
		AnchorPane.setRightAnchor(tekst, 10.0);

		// addime kõik eelneva childreniteks
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

		/* Hetkel ei oska midagi mõistlikku peale hakata sellega
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
				if (kasTöötab == true) {
					vihje = vihjelist.get(sõnalist.indexOf(sõna));
					tekst.setText("Arvatav sõna: \n" + sõnakriipsudena
							+ "\nVihje: \n" + vihje + "\nValitud täht: "
							+ pakutudtäht + "\nF: " + errors);
					System.out.println(vihje);
					System.out.println("xd");
					Taimer.setAeg(10);
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
				// sõnakriipsudena = "";
				Taimer.alusta();
				System.out.println(sõnalist.size());
				Random rand = new Random();
				int arv = rand.nextInt(sõnalist.size());
				sõna = sõnalist.get(arv);
				//System.out.println(sõna); // näitab, palju neid sõnu üldse listis on
				// loob sõnale vastava _ _ _ _ _ _ _ järjendi mille saab
				for (int i = 0; i < sõna.length(); i++) {
					sõnakriipsudena += "_";
				}
				errors = 0;
				pakutudtäht = "";
				tekst.setText("Arvatav sõna: \n" + sõnakriipsudena
						+ "\nVihje: \n" + vihje + "\nValitud täht: "
						+ pakutudtäht + "\nVigu: " + errors);

			}
		});

		// kui tähti vajutatakse siis mis teeb
		anchorpane.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(final KeyEvent keyEvent) {
				if (kasTöötab) {
					pakutudtäht = keyEvent.getText();
					System.out.println(keyEvent.getText());
					keyEvent.consume();

					try {
						if (Kontroll.KasTähtOnSõnas(sõna, pakutudtäht, errors) == true) {
							sõnakriipsudena = Kontroll.Asendakriipsud(sõna,
									sõnakriipsudena, pakutudtäht);

						} else {
							errors++;
						}
					} catch (ValeTaheErind e) {
						System.out
								.println("Tekkis erind: seda tähte pole tähestikus või vajutasid shift/tab/alt etc");
					}

					tekst.setText("Arvatav sõna: \n" + sõnakriipsudena
							+ "\nVihje: \n" + vihje + "\nValitud täht: "
							+ pakutudtäht + "\nvigu: " + errors);

					if (Kontroll.KasArvatud(sõnakriipsudena) == true) {
						// YOU WIN ehk teeb midagi
						tekst.setText("Arvatav sõna: \n" + sõnakriipsudena
								+ "\nVihje: \n" + vihje + "\nValitud täht: "
								+ pakutudtäht + "\nvigu: " + errors
								+ "\nSINA VÕITSID!");
						start.setDisable(false);
						resetGame();
					}
					Poomine.joonista(errors);
					if (errors == 7) {
						tekst.setText("KAOTASID!\nLiiga palju erroreid!");
						resetGame();
						start.setDisable(false);
					}
				}
			}
		});
		
		lava.setTitle("Poomine");
		Scene scene = new Scene(anchorpane, 600, 570, Color.WHEAT);
		lava.setScene(scene);
		lava.setMinHeight(600); lava.setMaxHeight(700);
		lava.setMinWidth(550); lava.setMaxWidth(700);
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

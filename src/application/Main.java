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
	static Label pealkiri = new Label("Poomism�ng");
	private int errors;
	public String vihje = "";
	public String s�na = "";
	private boolean kasT��tab = false;
	protected static ArrayList<String> vihjelist = new ArrayList<String>();
	protected static ArrayList<String> s�nalist = new ArrayList<String>();
	// private static String[] s�nalist;
	public static String pakutudt�ht;
	public String s�nakriipsudena = "";
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
	// algv��rtustamine
	public void resetGame() {
		vihje = "";
		s�na = "";
		kasT��tab = false;
		s�nakriipsudena = "";
		pakutudt�ht = "";
		errors = 0;
		Poomine.resetJoonista();

	}

	@Override
	public void start(Stage lava) {

		// luuakse juur
		AnchorPane anchorpane = new AnchorPane();

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
		final Button start = new Button("Alusta");
		AnchorPane.setTopAnchor(start, 10.0);
		AnchorPane.setRightAnchor(start, 10.0);
		final Button vihjenupp = new Button("Vihje");
		AnchorPane.setTopAnchor(vihjenupp, 40.0);
		AnchorPane.setRightAnchor(vihjenupp, 10.0);

		// tekst m�nguseisu jaoks(arvatav s�na, valesid jne)
		final Label tekst = new Label("Arvatav s�na: \nVihje: \nT�hed: ");
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
					vihje = vihjelist.get(s�nalist.indexOf(s�na));
					tekst.setText("Arvatav s�na: \n" + s�nakriipsudena
							+ "\nVihje: \n" + vihje + "\nValitud t�ht: "
							+ pakutudt�ht + "\nF: " + errors);
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
				// genereerib random s�na listist
				start.setDisable(true);
				resetGame(); // algv��rtustame muutujad
				kasT��tab = true; // m��rame t��tamise t�eseks
				// s�nakriipsudena = "";
				Taimer.alusta();
				System.out.println(s�nalist.size());
				Random rand = new Random();
				int arv = rand.nextInt(s�nalist.size());
				s�na = s�nalist.get(arv);
				//System.out.println(s�na); // n�itab, palju neid s�nu �ldse listis on
				// loob s�nale vastava _ _ _ _ _ _ _ j�rjendi mille saab
				for (int i = 0; i < s�na.length(); i++) {
					s�nakriipsudena += "_";
				}
				errors = 0;
				pakutudt�ht = "";
				tekst.setText("Arvatav s�na: \n" + s�nakriipsudena
						+ "\nVihje: \n" + vihje + "\nValitud t�ht: "
						+ pakutudt�ht + "\nVigu: " + errors);

			}
		});

		// kui t�hti vajutatakse siis mis teeb
		anchorpane.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(final KeyEvent keyEvent) {
				if (kasT��tab) {
					pakutudt�ht = keyEvent.getText();
					System.out.println(keyEvent.getText());
					keyEvent.consume();

					try {
						if (Kontroll.KasT�htOnS�nas(s�na, pakutudt�ht, errors) == true) {
							s�nakriipsudena = Kontroll.Asendakriipsud(s�na,
									s�nakriipsudena, pakutudt�ht);

						} else {
							errors++;
						}
					} catch (ValeTaheErind e) {
						System.out
								.println("Tekkis erind: seda t�hte pole t�hestikus v�i vajutasid shift/tab/alt etc");
					}

					tekst.setText("Arvatav s�na: \n" + s�nakriipsudena
							+ "\nVihje: \n" + vihje + "\nValitud t�ht: "
							+ pakutudt�ht + "\nvigu: " + errors);

					if (Kontroll.KasArvatud(s�nakriipsudena) == true) {
						// YOU WIN ehk teeb midagi
						tekst.setText("Arvatav s�na: \n" + s�nakriipsudena
								+ "\nVihje: \n" + vihje + "\nValitud t�ht: "
								+ pakutudt�ht + "\nvigu: " + errors
								+ "\nSINA V�ITSID!");
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

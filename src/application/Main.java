package application;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Main extends Application {

	//hunnik arvumuutujaid
	static int TAIMERIAEG = 120; // m�ngu alustades 120sek
	int OIGESONA = 100; // lisab nii palju punkte
	int OIGETAHT = 10; // -::-
	int OIGESONAAEG = 20; // mitu sekundit lisatakse �ige s�na pakkumisel
	int VIHJEMIINUS = 10; // v�tab nii palju punkte maha
	int VIHJEMIINUSAEG = 5; // v�tab selle v�rra aega maha
	int VALETAHT = 5; // sama analoogia eelmise kahega
	int VALETAHTAEG = 10; // -::-
	static int s�nadearv = 0;
	
	//Taimer ajaloendur = new Taimer();
	static Label pealkiri = new Label("Poomism�ng");
	protected static int errors;
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
	 // loome uue m�ngija
	static Mangija uusMangija = new Mangija(); // nimi, skoor, �igesti arvatud s�nu
	
	public String tekstikast() {
		return "Arvatav s�na: \n" + s�nakriipsudena
			+ "\nVihje: \n" + vihje + "\nValitud t�ht: "
			+ pakutudt�ht + "\nVigu: " + errors + "\nPunkte: " + uusMangija.getSkoor();
	}
	
	final static Label tekst = new Label("Vilkommen!");
	final static Label ajalabel = new Label("");
	final static Button start = new Button("Alusta");
	final static Button vihjenupp = new Button("Vihje");
	final static Button edetabel = new Button("Edetabel");
	

	static Pane joonis = new Pane();
	//private Mangija playa; // veel ei kasuta
	AnchorPane anchorpane = new AnchorPane();
	
	// http://java-buddy.blogspot.com/2012/06/update-ui-in-javafx-application-thread.html
	public static void muudaTekst(final String aeg) {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                ajalabel.setText("Aeg: "+ aeg);
            }
        });
	}
	
	// algv��rtustamine	
	public static void resetGame() {
		Poomine.resetJoonista();
		Taimer.stopp();
		Taimer.setAeg(TAIMERIAEG);
		resetSona();
		start.setDisable(false);
		vihjenupp.setDisable(true);
		kasT��tab = false;
		errors = 0;
		uusMangija.mangijaReset();
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
		tekst.setText(tekstikast());
		
	}
	
	protected void t�heVajutus(String pakutudt�ht) {
		try {
			if(pakutudt�hed.contains(pakutudt�ht)) {
				System.out.println("T�hte "+pakutudt�ht+" on juba pakutud");
			}
			else {
				pakutudt�hed.add(pakutudt�ht);
				if (Kontroll.KasT�htOnS�nas(s�na, pakutudt�ht, errors) == true) {
					s�nakriipsudena = Kontroll.Asendakriipsud(s�na,
						s�nakriipsudena, pakutudt�ht);
					uusMangija.setSkoor(uusMangija.getSkoor() + OIGETAHT);
				} else {
					errors++;
					uusMangija.setSkoor(uusMangija.getSkoor() - VALETAHT);
					
					Taimer.setAeg(Taimer.getAeg()-VALETAHTAEG);
				}
			}
		} catch (ValeTaheErind e) {
			System.out.println("Tekkis erind: seda t�hte pole t�hestikus v�i vajutasid shift/tab/alt etc");
		}

		tekst.setText(tekstikast());

		if (Kontroll.KasArvatud(s�nakriipsudena) == true) {
			uusMangija.setSkoor(uusMangija.getSkoor() + OIGESONA);
			uusMangija.setArvatud(uusMangija.getArvatud() + 1);
			tekst.setText(tekstikast());
			//start.setDisable(false);
			//resetGame();
			Taimer.setAeg(Taimer.getAeg() + OIGESONAAEG);
			uusSona();
			
		}
		Poomine.joonista(errors);
		if (errors == 7) {
			tekst.setText("KAOTASID!\nTegid liigselt vigu!\nL�ppskoor: "+ uusMangija.getSkoor());
			Taimer.stopp();
			final String temp = ". KOKKUV�TE: "+uusMangija.getSkoor()+" punkti. Arvasid �ra "+uusMangija.getArvatud()+" s�na. Tegid "+errors+" viga.";
			
			//loome nime sisestamiseks uue akna
			final Stage lopp = new Stage();
            Label label = new Label("Sisesta enda nimi: ");
            final TextField nimekoht = new TextField ();
            Button ok = new Button("OK");
            Button cancel = new Button("Cancel");
            
            ok.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    //failikirjutus nimega
                	try {
                		uusMangija.setNimi(nimekoht.getText());
                		System.out.println(uusMangija);
						Edetabel.kirjutaTabelisse(uusMangija);
                	} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	resetGame();
                    lopp.hide();
               
                    
                }
            });
            cancel.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                	resetGame();
                    lopp.hide();
                    
                }
            });
            FlowPane pane = new FlowPane(10, 10);
            pane.setAlignment(Pos.CENTER);
            pane.getChildren().addAll(ok, cancel);
            
            VBox vBox = new VBox(10);
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().addAll(label, nimekoht, pane);
            nimekoht.setMaxWidth(150);
            
            Scene stseen2 = new Scene(vBox);
            lopp.setScene(stseen2);
            lopp.setHeight(180);
            lopp.setWidth(250);
            lopp.setResizable(false);
            lopp.show();
		}
	}
	
	protected void joonistaInterface() {

		pealkiri.setText("Poomine");
		pealkiri.setFont(Font.font("Century Gothic", 45));
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
		AnchorPane.setBottomAnchor(edetabel, 10.0);
		AnchorPane.setRightAnchor(edetabel, 10.0);

		// tekst m�nguseisu jaoks(arvatav s�na, valesid jne)
		tekst.setFont(Font.font("Century Gothic", 20));
		AnchorPane.setTopAnchor(tekst, 70.0);
		AnchorPane.setRightAnchor(tekst, 10.0);
		
		// ajakuva kast ka siia teksti alla
		ajalabel.setFont(Font.font("Century Gothic", 25));
		AnchorPane.setTopAnchor(ajalabel, 250.0);
		AnchorPane.setRightAnchor(ajalabel, 45.0);

		// addime k�ik eelneva childreniteks
		anchorpane.getChildren().addAll(joonis, start, vihjenupp, pealkiri, tekst, ajalabel, edetabel);
	}

	@Override
	public void start(Stage lava) {

		// luuakse juur
		joonistaInterface();
		Scene scene = new Scene(anchorpane, 600, 570, Color.LIGHTSTEELBLUE);

		// panin posti joonistamise teise klassi �ldse
		Poomine.joonistaPoomispost();

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
					uusMangija.setSkoor(uusMangija.getSkoor() - VIHJEMIINUS);
					vihje = vihjelist.get(s�nalist.indexOf(s�na));
					tekst.setText(tekstikast());
					System.out.println(vihje);
					Taimer.setAeg(Taimer.getAeg() - VIHJEMIINUSAEG);
					
				}
			}
		});
		// kui start vajutad siis mis juhtub
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				resetGame(); // algv��rtustame muutujad
				kasT��tab = true; // m��rame t��tamise t�eseks
				Taimer.alusta();
				start.setDisable(true);
				uusSona();
			}
		});
		edetabel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//loome nime sisestamiseks uue akna
				final Stage edetab = new Stage();
				ArrayList<Mangija> edetabel = new ArrayList<Mangija>();

				try {
					edetabel = Edetabel.loeTabelist();
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				Collections.sort(edetabel);
				ObservableList<Mangija> obsEdetabel = FXCollections.observableArrayList(edetabel);
				ListView<Mangija> listView = new ListView<Mangija>(obsEdetabel);
				
				final Label edetabelTitle = new Label("Edetabel");
	            Button kinni = new Button("Sulge");
	            
	            kinni.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                    edetab.hide();
	                    
	                }
	            });
	            
	            FlowPane pane = new FlowPane(10, 10);
	            pane.setAlignment(Pos.CENTER);
	            pane.getChildren().addAll(kinni);
	            
	            VBox vBox = new VBox(10);
	            vBox.setAlignment(Pos.CENTER);
	            vBox.getChildren().addAll(edetabelTitle, listView, pane);
	            
	            Scene stseen3 = new Scene(vBox);
	            edetab.setScene(stseen3);
	            edetab.setHeight(500);
	            edetab.setWidth(350);
	            edetab.setResizable(false);
	            edetab.show();
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
					t�heVajutus(pakutudt�ht);
					
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

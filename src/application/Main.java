package application;
	
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
	
	Canvas canvas = new Canvas(400, 400);
	private Mangija playa;
	private Taimer taimer;
	protected FlowPane root;
	
	// see asi töötab isegi jou
	public void runn() {
			root.setOnKeyPressed(new EventHandler<KeyEvent>() {
                public void handle(final KeyEvent keyEvent) {
                	Poomine.pakutudtäht = keyEvent.getText();
                    System.out.println(keyEvent.getText());
                    keyEvent.consume();
                    
                }
            });
	}
	private VBox getNupud() {
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
				 */
			}
		});
		
		return nupud;
	}
	
	
    
	@Override
	public void start(Stage lava) {		
		lava.setTitle("Poomine");
		root = new FlowPane();
		root.setLayoutX(20);
		root.setLayoutY(20);
		Text tekst = new Text("Hangman");
        tekst.setFont(Font.font ("Verdana", 40));
		root.getChildren().add(getNupud());
		
		root.getChildren().add(tekst);
		
		root.getChildren().add(canvas);
		
		
		Scene scene = new Scene(root, 500, 500, Color.SNOW);
		runn();
		lava.setResizable(false);
		lava.setScene(scene);
		lava.show();

	}

	
	public static void main(String[] args) {
		launch(args);
	}
}

package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class Main extends Application {
	
	private Canvas canvas = new Canvas(300, 300);
	private Mangija playa;
	private Taimer taimer;
	
	private VBox getNupud() {
		VBox nupud = new VBox();
		Button start = new Button("Alusta");
		Button abi = new Button("Abi");
		nupud.getChildren().addAll(start, abi);
		nupud.setSpacing(10);
		
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
			}
		});
		
		return nupud;
	}
	@Override
	public void start(Stage lava) {		
		lava.setTitle("Poomine");
		FlowPane root = new FlowPane();
		root.setLayoutX(100);
		root.setLayoutY(150);
		root.getChildren().add(getNupud());
		Scene scene = new Scene(root, 400, 400, Color.SNOW);
		lava.setResizable(false);
		lava.setScene(scene);
		lava.show();

	}
	
	///sgssgsg
	public static void main(String[] args) {
		launch(args);
	}
}

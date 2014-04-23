package application;


import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Poomine extends Main {
	static Circle pea = new Circle();
	static Line keha = new Line();
	static Line nöör = new Line();
	static Line vasakkäsi = new Line();
	static Line paremkäsi = new Line();
	static Line vasakjalg = new Line();
	static Line paremjalg = new Line();

	public static void resetJoonista() {
		joonis.getChildren().removeAll(pea, nöör, keha, vasakkäsi, paremkäsi,
				vasakjalg, paremjalg);
	}

	public static void joonista(int errors) {
		// joonistab hangmani errorite kaupa
		// errors=1 siis teeb esimese asja
		// errors =2 siis teeb teise
		// jne


		if (errors == 1) {

			pea.setCenterX(280);
			pea.setCenterY(140);
			pea.setRadius(30);
			pea.setFill(Color.BLACK);
			kasSisaldab(pea);
			nöör.setStartX(260);
			nöör.setStartY(80);
			nöör.setEndX(260);
			nöör.setEndY(168);
			nöör.setStrokeWidth(3);
			nöör.setStroke(Color.GREEN);
			kasSisaldab(nöör);
		}

		if (errors == 2) {
			keha.setStartX(260);
			keha.setStartY(167);
			keha.setEndX(260);
			keha.setEndY(350);
			keha.setStrokeWidth(5);
			keha.setStroke(Color.BLACK);
			kasSisaldab(keha);
		}
		if (errors == 3) {
			vasakkäsi.setStartX(260);
			vasakkäsi.setStartY(190);
			vasakkäsi.setEndX(240);
			vasakkäsi.setEndY(260);
			vasakkäsi.setStrokeWidth(4);
			vasakkäsi.setStroke(Color.BLACK);
			kasSisaldab(vasakkäsi);

		}
		if (errors == 4) {
			paremkäsi.setStartX(260);
			paremkäsi.setStartY(190);
			paremkäsi.setEndX(310);
			paremkäsi.setEndY(260);
			paremkäsi.setStrokeWidth(4);
			paremkäsi.setStroke(Color.BLACK);
			kasSisaldab(paremkäsi);

		}
		if (errors == 5) {
			vasakjalg.setStartX(260);
			vasakjalg.setStartY(350);
			vasakjalg.setEndX(230);
			vasakjalg.setEndY(450);
			vasakjalg.setStrokeWidth(5);
			vasakjalg.setStroke(Color.BLACK);
			kasSisaldab(vasakjalg);

		}

		if (errors == 6) {
			paremjalg.setStartX(260);
			paremjalg.setStartY(350);
			paremjalg.setEndX(290);
			paremjalg.setEndY(450);
			paremjalg.setStrokeWidth(5);
			paremjalg.setStroke(Color.BLACK);
			kasSisaldab(paremjalg);

		}
	}
	private static void kasSisaldab(Node o) {
		if(!joonis.getChildren().contains(o)) {
			joonis.getChildren().add(o);
		}
	}

}
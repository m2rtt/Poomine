package application;


import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Poomine extends Main {
	static Circle pea = new Circle();
	static Line keha = new Line();
	static Line n��r = new Line();
	static Line vasakk�si = new Line();
	static Line paremk�si = new Line();
	static Line vasakjalg = new Line();
	static Line paremjalg = new Line();

	public static void resetJoonista() {
		joonis.getChildren().removeAll(pea, n��r, keha, vasakk�si, paremk�si,
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
			n��r.setStartX(260);
			n��r.setStartY(80);
			n��r.setEndX(260);
			n��r.setEndY(168);
			n��r.setStrokeWidth(3);
			n��r.setStroke(Color.GREEN);
			kasSisaldab(n��r);
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
			vasakk�si.setStartX(260);
			vasakk�si.setStartY(190);
			vasakk�si.setEndX(240);
			vasakk�si.setEndY(260);
			vasakk�si.setStrokeWidth(4);
			vasakk�si.setStroke(Color.BLACK);
			kasSisaldab(vasakk�si);

		}
		if (errors == 4) {
			paremk�si.setStartX(260);
			paremk�si.setStartY(190);
			paremk�si.setEndX(310);
			paremk�si.setEndY(260);
			paremk�si.setStrokeWidth(4);
			paremk�si.setStroke(Color.BLACK);
			kasSisaldab(paremk�si);

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
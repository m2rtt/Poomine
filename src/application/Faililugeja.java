package application;

import java.io.FileNotFoundException;

public class Faililugeja extends Main {

	public static void faililugeja() throws FileNotFoundException {

		java.io.File fail = new java.io.File("src\\application\\s�nad.txt");
		java.util.Scanner sc = new java.util.Scanner(fail);
		int counter = 0;
		s�nalistt.removeAll(s�nalistt); // enne teeme s�nalisti t�hjaks
		while (sc.hasNextLine()) {
			String rida = sc.nextLine();
			//System.out.println(rida);
			String[] s�navihje = rida.split(";"); // teeme pooleks s�na ja vihje
			s�nalistt.add(s�navihje[0]);
			vihjelist.add(s�navihje[1]);
			System.out.println(s�nalistt.get(counter) + " | " + vihjelist.get(counter)); // prindib konsooli s�nad ja nende vihjed
			counter += 1;
			
		}
		
		sc.close();
	}
}

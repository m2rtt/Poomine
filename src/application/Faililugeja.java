package application;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Faililugeja extends Main {

	public static void faililugeja() throws FileNotFoundException {

		InputStream fail = Faililugeja.class.getResourceAsStream("s�nad.txt");
		java.util.Scanner sc = new java.util.Scanner(fail);
		//int counter = 0;
		s�nalist.removeAll(s�nalist); // enne teeme s�nalisti t�hjaks
		while (sc.hasNextLine()) {
			String rida = sc.nextLine();
			//System.out.println(rida);
			String[] s�navihje = rida.split(";"); // teeme pooleks s�na ja vihje
			s�nalist.add(s�navihje[0]);
			vihjelist.add(s�navihje[1]);
			//System.out.println(s�nalistt.get(counter) + " | " + vihjelist.get(counter)); // prindib konsooli s�nad ja nende vihjed
			//counter += 1;
			
		}
		
		sc.close();
	}
}

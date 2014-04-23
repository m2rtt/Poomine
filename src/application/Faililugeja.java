package application;

import java.io.FileNotFoundException;

public class Faililugeja extends Main {

	public static void faililugeja() throws FileNotFoundException {

		java.io.File fail = new java.io.File("src\\application\\sõnad.txt");
		java.util.Scanner sc = new java.util.Scanner(fail);
		int counter = 0;
		sõnalistt.removeAll(sõnalistt); // enne teeme sõnalisti tühjaks
		while (sc.hasNextLine()) {
			String rida = sc.nextLine();
			//System.out.println(rida);
			String[] sõnavihje = rida.split(";"); // teeme pooleks sõna ja vihje
			sõnalistt.add(sõnavihje[0]);
			vihjelist.add(sõnavihje[1]);
			System.out.println(sõnalistt.get(counter) + " | " + vihjelist.get(counter)); // prindib konsooli sõnad ja nende vihjed
			counter += 1;
			
		}
		
		sc.close();
	}
}

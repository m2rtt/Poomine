package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Edetabel extends Main{

	private static String failinimi = "edetabel.txt";
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	
	
	static void kirjutaTabelisse(Mangija m) throws FileNotFoundException, IOException, ClassNotFoundException {
		File fail = new File(failinimi);
		ArrayList<Mangija> edetabel = new ArrayList<Mangija>();
		
		if(!fail.exists()) {
			System.out.println("aa");
		    edetabel.add(m);
		    oos = new ObjectOutputStream(new FileOutputStream(failinimi));
		    oos.writeObject(edetabel);
		}
		else {	
			System.out.println(loeTabelist().isEmpty());
			edetabel.addAll(loeTabelist());
			edetabel.add(m);
			oos = new ObjectOutputStream(new FileOutputStream(failinimi));
			oos.writeObject(edetabel);
		}
	    oos.flush();
	    oos.close();
	}

	static ArrayList<Mangija> loeTabelist() throws FileNotFoundException, IOException, ClassNotFoundException {
		ois = new ObjectInputStream(new FileInputStream(failinimi));
		Object obj = ois.readObject();
		if(obj instanceof ArrayList) {
			ArrayList<Mangija> m = (ArrayList<Mangija>) obj;
			ois.close();
			return m;
		}
		else {
			ois.close();
			return null;
		}		
	}	
}

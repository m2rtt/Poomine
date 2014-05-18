package application;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Failikirjutaja extends Main {
	
	
	
	public static void failikirjutaja() throws IOException  {
		
		DataOutputStream dos = new DataOutputStream (new FileOutputStream ("statistika.dat", true));
		
	
		dos.writeUTF(sõna+";"+punktid+";"+errors+"\n");
		
		System.out.println("kirjutasin faili");
		
		
		dos.close();
	}

}

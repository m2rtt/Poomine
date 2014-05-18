package application;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Failikirjutaja extends Main {
	
	
	
	public static void failikirjutaja() throws IOException  {
		
		DataOutputStream dos = new DataOutputStream (new FileOutputStream ("statistika.dat", true));
		
	
		dos.writeBytes(sõna+"; "+punktid+"; "+errors+"\n");
		
		System.out.println("kirjutasin faili2");
		
		
		dos.close();
	}
	
	
	public static void uusmängija() throws IOException {
		
		DataOutputStream dos = new DataOutputStream (new FileOutputStream ("statistika.dat", true));
		
		
		dos.writeBytes("Uus mängija: \n");
		
		System.out.println("kirjutasin faili1");
		
		
		dos.close();
		
	}
	
	public static void kokkuvõte() throws IOException {
		
		DataOutputStream dos = new DataOutputStream (new FileOutputStream ("statistika.dat", true));
		
		
		dos.writeBytes("Kokkuvõte: "+punktid+" punkti\n");
		
		System.out.println("kirjutasin faili3");
		
		
		dos.close();
		
	}

}

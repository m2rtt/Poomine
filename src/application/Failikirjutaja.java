package application;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Failikirjutaja extends Main {
	
	
	
	public static void failikirjutaja() throws IOException  {
		
		DataOutputStream dos = new DataOutputStream (new FileOutputStream ("statistika.dat", true));
		
	
		dos.writeBytes(s�na+"; "+punktid+"; "+errors+"\n");
		
		System.out.println("kirjutasin faili2");
		
		
		dos.close();
	}
	
	
	public static void uusm�ngija() throws IOException {
		
		DataOutputStream dos = new DataOutputStream (new FileOutputStream ("statistika.dat", true));
		
		
		dos.writeBytes("Uus m�ngija: \n");
		
		System.out.println("kirjutasin faili1");
		
		
		dos.close();
		
	}
	
	public static void kokkuv�te() throws IOException {
		
		DataOutputStream dos = new DataOutputStream (new FileOutputStream ("statistika.dat", true));
		
		
		dos.writeBytes("Kokkuv�te: "+punktid+" punkti\n");
		
		System.out.println("kirjutasin faili3");
		
		
		dos.close();
		
	}

}

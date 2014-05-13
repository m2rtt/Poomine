package application;

/*
 * Esialgu j�tab selle k�rvale ja lisab kolmandaks projektiks
 */
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Taimer extends Main{
	//static int intervall;
	static Timer taimer;
	static int aeg = 10;
	/*
	public static void main(String[] args) {
		alusta();
	}
	*/
	public void stopp() {
		taimer.cancel();
	}
	public static void alusta() {
		int delay = 1000;
		int period = 1000;
		taimer = new Timer();
		//intervall = getAeg();
		// google'i abiga loodud taimer
		taimer.schedule(new TimerTask() {

			public void run() {
				System.out.println(aeg);
				muudaTekst(Integer.toString(aeg));
				aeg = setIntervall();				

			}
		}, delay, period);
	}

	private static final int setIntervall() {
		if (aeg <= 0) {
			taimer.cancel();
			muudaTekst("Aeg l�bi!");
			System.out.println("Aeg l�bi");
			aeg = 10;
			
		}
		return --aeg;
	}
	public static void setAeg(int x) {
		aeg = x;
	}
	public static int getAeg() {
		return aeg;
	}
}

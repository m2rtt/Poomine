package application;

/*
 * Esialgu jätab selle kõrvale ja lisab kolmandaks projektiks
 */
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Taimer {
	static int intervall;
	static Timer taimer;
	static int aeg = 10;
	
	public static void main(String[] args) {
		alusta();
	}
	public void stopp() {
		taimer.cancel();
	}
	public static void alusta() {
		int delay = 1000;
		int period = 1000;
		taimer = new Timer();
		intervall = getAeg();
		// google'i abiga loodud taimer
		taimer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				System.out.println(setIntervall());
				aeg = setIntervall();

			}
		}, delay, period);
	}

	private static final int setIntervall() {
		if (intervall == 1) {
			taimer.cancel();
		}
		return --intervall;
	}
	public void setAeg(int aeg) {
		this.aeg = aeg;
	}
	public static int getAeg() {
		return aeg;
	}
}

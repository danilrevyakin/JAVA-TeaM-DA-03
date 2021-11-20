package controller;

import java.util.Random;

public class Probability {
	static private Random rand = new Random();
	
	public static boolean event_Probability(int percent) {
		float probability = 1.0f/(percent * 0.01f);
		if(0 == rand.nextInt() % probability)
			return true;
		else
			return false;
	}
}

package controller;

import java.util.Random;

public class Probability {
	static private final Random rand = new Random();
	
	public static boolean eventProbability(int percent) {
		float probability = 1.0f/(percent * 0.01f);
		if(0 == rand.nextInt() % probability)
			return true;
		else
			return false;
	}
	
	static public Random getRand() {
		return rand;
	}
}

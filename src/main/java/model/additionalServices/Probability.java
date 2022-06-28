package model.additionalServices;

import java.util.Random;

public class Probability {
	static private final Random rand = new Random();
	
	public static boolean eventProbability(int percent) {
		float probability = percent * 0.01f;
		return (rand.nextFloat() <= probability);
	}
}

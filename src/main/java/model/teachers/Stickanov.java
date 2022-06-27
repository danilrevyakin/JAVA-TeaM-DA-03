package model.teachers;

import java.io.Serializable;
import java.util.List;

import model.Question;
import model.Teacher;
import model.modes.VeryHard;

public final class Stickanov extends Teacher implements Serializable {
	public Stickanov(String name, String sex, List<Question> questions, int id, int correctSkillProbability, int wrongSkillProbability) {
		super(name, sex, questions, id, 50, 100);
		mode = new VeryHard();
	}
	public String StikanovSkillCorrectMessage(){
		return ("You are unsure of the answer?");
	}
	public String StikanovSkillWrongMessage(){
		return ("Professionals don't answer that way\nReduce your health and points by 15%");
	}


}

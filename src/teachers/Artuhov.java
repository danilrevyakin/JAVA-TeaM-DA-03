package teachers;

import java.io.Serializable;
import java.util.ArrayList;

import model.Question;
import model.Teacher;

public final class Artuhov extends Teacher implements Serializable {

	public Artuhov(String name, boolean sex, ArrayList<Question> questions) {
		super(name, sex, questions);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void correctStudentAnswerSkill() {

	}

	@Override
	protected void wrongStudentAnswerSkill() {

	}

	public void skill() {
    	
    }
}

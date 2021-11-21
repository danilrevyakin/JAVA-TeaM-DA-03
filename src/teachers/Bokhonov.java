package teachers;

import java.io.Serializable;
import java.util.ArrayList;

import model.Question;
import model.Teacher;

public class Bokhonov extends Teacher implements Serializable {

	public Bokhonov(String name, boolean sex, ArrayList<Question> questions) {
		super(name, sex, questions);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void correctStudentAnswerSkill() {

	}

	@Override
	protected void wrongStudentAnswerSkill() {

	}
}

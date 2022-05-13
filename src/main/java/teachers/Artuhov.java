package teachers;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.skill.ArtuhovSkill;
import model.skill.Skill;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Artuhov extends Teacher implements Serializable {
	private final int skillProbability = 50;
	Skill skill = new ArtuhovSkill();
	public Artuhov(String name, String sex, List<Question> questions, int id) {
		super(name, sex, questions, id);
	}

	@Override
	protected void correctStudentAnswerSkill() {
		if(Probability.eventProbability(skillProbability))
			skill.studentAnswerCorrect(super.getStudent());
	}

	@Override
	protected void wrongStudentAnswerSkill() {
		if(Probability.eventProbability(skillProbability))
			skill.studentAnswerFalse(super.getStudent());
	}


}

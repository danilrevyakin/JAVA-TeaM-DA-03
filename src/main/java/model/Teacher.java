package model;

import model.additionalServices.Probability;
import model.modes.Easy;
import model.modes.Mode;
import view.ConsoleView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public abstract class Teacher extends Person implements Serializable {
    private int id;
    protected final ConsoleView consoleView = new ConsoleView();
    protected String message;

    protected int manaPrice = 45;
    protected int manaChangePrice = 20;
    protected int minLevelForUsingMana = 2;
    private final List<Question> questions;

    private Student student;
    protected Mode mode = new Easy();

    protected Random rand = new Random();
    ListIterator<Question> iterator;

    protected final int correctSkillProbability;
    protected final int wrongSkillProbability;

    public Teacher(String name, String sex, List<Question> questions, int id, int correctSkillProbability, int wrongSkillProbability) {
        super(name, sex, 50);
        this.questions = questions;
        iterator = questions.listIterator();
        this.id = id;
        this.correctSkillProbability = correctSkillProbability;
        this.wrongSkillProbability = wrongSkillProbability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Question giveNextQuestion() {
        if (!iterator.hasNext()) {
            return null;
        }
        return iterator.next();
    }

    public Question givePreviousQuestion() {
        if (!iterator.hasPrevious()) {
            return null;
        }
        return iterator.previous();
    }

    public Question givePreviousQuestionWithoutMovingIterator() {
        if (!iterator.hasPrevious()) {
            return null;
        }
        Question q = iterator.previous();
        iterator.next();
        return q;
    }

    public void correctStudentAnswer() {
        setHealth(getHealth() - 25);
        if (getHealth() <= 0) {
            message = consoleView.teacherDefeat();
            return;
        }
        if (getHealth() > 0) consoleView.correctAnswerOutput(this);
        String reaction = correctStudentReaction();
        if (reaction.length() >= 1) {
            message = reaction;
        }
    }

    public void wrongStudentAnswer() {
        setHealth(getHealth() + 5);
        message = wrongStudentReaction();
    }

    public void addNextQuestion(Question question) {
        iterator.add(question);
        iterator.previous();
    }

    protected String correctStudentReaction() {
        if (Probability.eventProbability(correctSkillProbability))
            return mode.studentAnswerCorrect(student);
        return ConsoleView.correctMessage();
    }

    protected String wrongStudentReaction() {
        if (Probability.eventProbability(wrongSkillProbability))
            return mode.studentAnswerFalse(student);
        return ConsoleView.wrongMessage();
    }


    public Student getStudent() {
        return student;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + getName() + '\'' +
                ", health=" + getHealth() +
                ", sex='" + getSex() + '\'' +
                ", questions=" + questions +
                '}';
    }

    public boolean hasQuestions() {
        return iterator.hasNext();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Mode getMode() {
        return mode;
    }

    public String getModeName() {
        StringBuilder stringBuilder = new StringBuilder(mode.getClass().getName());
        return stringBuilder.substring(stringBuilder.lastIndexOf(".") + 1);
    }

    public String say() {
        if (!hasQuestions()) {
            message += "\nI have no questions for you(";
        }
        String currentMessage = message;
        message = "";
        return currentMessage;
    }

    public int getManaPrice() {
        return this.manaPrice;
    }

    public enum TransferManaStatus {
        SUCCESSFUL_USED_MANA,

        FAILED_USED_MANA
    }

    public TransferManaStatus tryUseMana() {
        if (student.getLevel() < minLevelForUsingMana) {
            message = "Your level is less then " + minLevelForUsingMana + "" +
                    ". You can't use mana now";
            return TransferManaStatus.FAILED_USED_MANA;
        } else if (student.getMana() < manaPrice) {
            message = "You have less than " + manaPrice + " mana." +
                    "You can't use mana now";
            return TransferManaStatus.FAILED_USED_MANA;
        } else if (givePreviousQuestionWithoutMovingIterator().getChoices().size() == 1) {
            message = "You are very stupid. There is only correct answer";
            return TransferManaStatus.FAILED_USED_MANA;
        }
        student.increaseManaOn(-manaPrice);
        message = "You have received my special offer";
        makeWorkForMana();
        changeManaPrice();
        return TransferManaStatus.SUCCESSFUL_USED_MANA;
    }

    protected void changeManaPrice() {
        manaPrice += manaChangePrice;
    }

    protected void makeWorkForMana() {
        message += "\nI have erased one wrong choice";
        Question currentQuestion = givePreviousQuestion();
        ArrayList<String> choices = currentQuestion.getChoices();
        for (String choice : choices) {
            if (!choice.equals(currentQuestion.getAnswer())) {
                choices.remove(choice);
                break;
            }
        }
        currentQuestion.setChoices(choices);
    }
}
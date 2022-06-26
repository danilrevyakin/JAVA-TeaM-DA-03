package model;

import controller.Probability;
import model.modes.Easy;
import model.modes.Mode;
import view.ConsoleView;

import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public abstract class Teacher extends Person implements Serializable {
    private int id;
    protected final ConsoleView consoleView = new ConsoleView();
    protected String message;
    private final List<Question> questions;

    private Student student;
    protected Mode mode = new Easy();

    public ConsoleView getConsoleView() {
        return consoleView;
    }
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
        if (!iterator.hasNext()){
            return null;
        }
        message = "";//message is new because of teacher give another question
        return iterator.next();
    }
    public Question givePreviousQuestion() {
        if (!iterator.hasPrevious()){
            return null;
        }
        message = "";//message is new because of teacher give another question
        return iterator.previous();
    }

    public Question giveNextQuestionWithoutDeletingOldMessage() {
        if (!iterator.hasNext()){
            return null;
        }
        return iterator.next();
    }
    public Question givePreviousQuestionWithoutDeletingOldMessage() {
        if (!iterator.hasPrevious()){
            return null;
        }
        return iterator.previous();
    }

    public void correctStudentAnswer() {
        setHealth(getHealth() - 25);
        if (getHealth() <= 0) {
            message += consoleView.teacherDefeat();
            return;
        }
        if (getHealth() > 0) consoleView.correctAnswerOutput(this);
        String reaction = correctStudentReaction();
        if(reaction.length() >= 1){
            message += "\n" + reaction;
        }
    }

    public void wrongStudentAnswer() {
        setHealth(getHealth() + 5);
        String reaction = wrongStudentReaction();
        if(reaction.length() >= 1){
            message += reaction;
        }
    }

    public void addNextQuestion(Question question){
        iterator.add(question);
        iterator.previous();
    }

    protected String correctStudentReaction(){
        if(Probability.eventProbability(correctSkillProbability))
            return mode.studentAnswerCorrect(student);
        return ConsoleView.correctMessage();
    }

    protected String wrongStudentReaction(){
        if(Probability.eventProbability(wrongSkillProbability))
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

    public boolean hasQuestions(){
        return iterator.hasNext();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Mode getMode() {
        return mode;
    }

    public String getModeName(){
        StringBuilder stringBuilder = new StringBuilder(mode.getClass().getName());
        return stringBuilder.substring(stringBuilder.lastIndexOf(".") + 1);
    }

    public String say(){
        if(!hasQuestions()){
            message += "\nI have no questions for you(";
        }
        return message;
    }

}
public class Teacher extends Person{
    private String subject;
    public Teacher(String name, boolean sex, int level, String subject){
        setMaxMana(500);
        setMana(500);
        setLevel(level);
        setName(name);
        setSex(sex);
        setSubject(subject);
    }

    public String getSubject(){
        return subject;
    }
    public void setSubject(String subject){
        this.subject = subject;
    }
}

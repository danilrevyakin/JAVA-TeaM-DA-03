public class Student extends Person{

    private Mission currentMission;

    public Student(String name, boolean sex){
        setMaxMana(100);
        setMana(50);
        setLevel(1);
        setName(name);
        setSex(sex);
    }

    public boolean enterMission(Mission mission){
        return mission.open();
    }
}

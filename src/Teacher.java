public class Teacher extends Person{
    private Mission currentMission;
    private final int MAX_MANA = 500;

    public Teacher(String name){
        setMaxMana(MAX_MANA);
        setMana(getMaxMana());
        setName(name);
    }

    //getters & setters
    public Mission getCurrentMission(){
        return currentMission;
    }

    public  void setCurrentMission(Mission currentMission){
        this.currentMission = currentMission;
    }


}

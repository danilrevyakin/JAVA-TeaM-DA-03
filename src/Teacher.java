public class Teacher extends Person{
    private Mission currentMission;
    public Teacher(String name){
        setMaxMana(500);
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

public class Main {
    public static void main(String[] args){
        Student vlad = new Student("Vlad", true);
        Teacher boha = new Teacher("Bokhonov",true,9, "Matan");
        Mission firstMission = new Mission(vlad, boha, 1);
        if(vlad.enterMission(firstMission));
    }
}

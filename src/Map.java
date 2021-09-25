import java.util.Scanner;

public class Map {
    public Mission[] missions;

    public void generateMissions(Student student){
        missions = new Mission[student.getMaxLevel()];
        for(int i = 0; i < student.getMaxLevel(); i++){
            missions[i] = Game.createMissions(student);
        }
    }

    public void openMission(){
        Scanner in = new Scanner(System.in);
        int missinNumber = 0;

        while (missinNumber <= 0 || missinNumber > missions.length){
            System.out.print("Choose mission(1 - " + missions.length + "): ");
            missinNumber = in.nextInt();
        }

        missions[missinNumber - 1].startMission();

    }

}

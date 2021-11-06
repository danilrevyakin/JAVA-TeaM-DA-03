import java.util.Scanner;

//Раскидать по другим классам
public class Map {
    public Mission[] missions;

    public void generateMissions(Student student){
        missions = new Mission[student.getMaxLevel()];
        for(int i = 0; i < student.getMaxLevel(); i++){
            missions[i] = Game.createMission(student, i+1);
        }
    }

    public void openMission(){
        Scanner in = new Scanner(System.in);
        int missinNumber = 0;

        while (missinNumber <= 0 || missinNumber > missions.length){
            System.out.print("\nChoose mission(1 - " + missions.length + "): ");
            missinNumber = in.nextInt();
        }

        missions[missinNumber - 1].startMission();

    }

}

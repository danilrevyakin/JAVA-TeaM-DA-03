package controller.factory;

import model.Mission;
import model.Student;
import model.Teacher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class MissionFactory {

    private final TeachersFactory teacherManager = new TeachersFactory();
    public static final int MAX_NUMBER_OF_MISSIONS = TeachersFactory.NUMBER_OF_TEACHERS;  // Teachers are 10

    private Mission createMission(Teacher teacher, Student student){
        return new Mission(teacher, student);
    }

    public void generateMissions(Student student){
        student.availableMissions = new HashMap<>();
        List<Teacher> teachers = teacherManager.getTeachers();
        List<String> completed;
        if (student.getPlayer().getCompletedMissions() != null) {
            completed = Arrays.stream(student.getPlayer().getCompletedMissions().split(",")).toList();
            IntStream.range(0, MAX_NUMBER_OF_MISSIONS)
                    .forEach(index -> {
                                student.availableMissions.put(index + 1, createMission(teachers.get(index), student));
                                if (completed.contains(String.valueOf(index + 1))) {
                                    student.availableMissions.get(index + 1).setCompleted();
                                }
                            }
                    );
        }else{
            IntStream.range(0, MAX_NUMBER_OF_MISSIONS)
                    .forEach(index -> student.availableMissions.put(index + 1, createMission(teachers.get(index), student)));
        }
    }
}

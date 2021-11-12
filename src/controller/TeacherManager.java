package controller;
import model.*;


import java.io.IOException;
import java.util.Vector;

public class TeacherManager {
    private final FileManager fileManager = new FileManager();

    public  Teacher createTeacher() throws IOException {
        Vector<String> teacherSet = fileManager.initTeachers();
        int nN = (int) (Math.random() * teacherSet.size());
        return new Teacher(teacherSet.get(nN));
    }
}

package controller.factory;

import model.Teacher;


public interface TeacherFactory {
	Teacher getTeacher(String name);
}

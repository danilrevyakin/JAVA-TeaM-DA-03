package controller.factory;

import model.Teacher;

import java.lang.reflect.InvocationTargetException;


public interface TeacherFactory {
	Teacher getTeacher(String name) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException;
}

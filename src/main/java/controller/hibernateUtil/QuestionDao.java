package controller.hibernateUtil;

import model.Question;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionDao implements DaoImpl<Question>{

    @Override
    public Question findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Question.class, id);
    }

    public List<Question> getTeacherQuestions(int teacherID){
        return findAll().stream()
                .filter(x -> x.getTeacherID() == teacherID)
                .collect(Collectors.toList());
    }

    @Deprecated
    @Override
    public void save(Question question) {}

    @Deprecated
    @Override
    public void update(Question question) {}

    @Deprecated
    @Override
    public void delete(Question question) {}

    @Override
    public List<Question> findAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Question").list();
    }

}

package controller;

import model.additionalServices.Pair;

import java.sql.*;
import java.util.ArrayList;

public class SQLiteJDBC{
    Connection connection;

    public SQLiteJDBC() {
       connection = getConnection();
    }

    public Connection getConnection() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:database_java_team");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return c;
    }

    public ArrayList<String> getTeachersName(){
        Statement statement;
        ArrayList<String> names = new ArrayList<>();
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT main.teachers.name FROM main.teachers");
            while ( rs.next() ) {
                names.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }

    public Pair<Integer, String> teacherInfo(String name){
        Statement statement;
        ResultSet rs;
        int id = 0;
        String sex = null;
        try {
            statement = connection.createStatement();

            rs = statement.executeQuery(
                    "SELECT main.teachers.id, main.teachers.sex FROM main.teachers WHERE main.teachers.name='"  + name + "'");
            while ( rs.next() ) {
                id = rs.getInt("id");
                sex = rs.getString("sex");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Pair<Integer, String> pair = new Pair(id,sex);
        return pair;
    }
}

public class Mission {
    private int buildingNumber;
    private Teacher teacher;
    private Student student;
    private boolean isOpen;

    public Mission(Student student, Teacher teacher, int buildingNumber){
        this.buildingNumber = buildingNumber;
        this.teacher = teacher;
        this.student = student;
    }

    public boolean open(){
        if(student.getLevel() >= buildingNumber){
            System.out.println("Welcome to the " + buildingNumber + " mission 8).\n" +
                    "Subject " + teacher.getSubject() + ": your teacher is " + teacher.getName());
            isOpen = true;
        }
        else{
            System.out.println("Your level is too low for this mission 8(");
            isOpen = false;
        }
        return isOpen;
    }
}

package njvproject;
import java.io.Serializable;

public class Student implements Serializable {
    private String id;
    private String name;
    private String fatherName;
    private String gender;
    private String className;
    private String dob;
    private int attendance;

    public Student(String id, String name, String fatherName, String gender, String className, String dob) {
        this.id = id;
        this.name = name;
        this.fatherName = fatherName;
        this.gender = gender;
        this.className = className;
        this.dob = dob;
        this.attendance = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getGender() {
        return gender;
    }

    public String getClassName() {
        return className;
    }

    public String getDob() {
        return dob;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}

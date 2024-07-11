package njvproject;
import java.io.Serializable;
public class Teacher implements Serializable {
    private String name;
    private String gender;

    public Teacher(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }
}
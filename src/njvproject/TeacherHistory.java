package njvproject;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class TeacherHistory extends JFrame {

    public TeacherHistory() {
        setTitle("Teachers History");
        setSize(500, 400);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        ArrayList<Teacher> teachers = readTeachersFromFile();
        StringBuilder history = new StringBuilder();

        for (Teacher teacher : teachers) {
            history.append("Name: ").append(teacher.getName())
                    .append(", Gender: ").append(teacher.getGender())
                    .append("\n");
        }

        textArea.setText(history.toString());
        add(new JScrollPane(textArea));

        setVisible(true);
    }

    private ArrayList<Teacher> readTeachersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("teachers.dat"))) {
            return (ArrayList<Teacher>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}

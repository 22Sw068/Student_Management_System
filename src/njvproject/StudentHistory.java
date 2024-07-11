package njvproject;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class StudentHistory extends JFrame {

    public StudentHistory() {
        setTitle("Students History");
        setSize(500, 400);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        ArrayList<Student> students = readStudentsFromFile();
        StringBuilder std_history = new StringBuilder();

        for (Student student : students) {
            std_history.append("Std ID: ").append(student.getId())
                    .append(", Name: ").append(student.getName())
                    .append(", Father's Name: ").append(student.getFatherName())
                    .append(", Gender: ").append(student.getGender())
                    .append(", Class: ").append(student.getClassName())
                    .append(", DOB: ").append(student.getDob())
                    .append("\n");
        }

        textArea.setText(std_history.toString());
        add(new JScrollPane(textArea));

        setVisible(true);
    }

    private ArrayList<Student> readStudentsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"))) {
            return (ArrayList<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}

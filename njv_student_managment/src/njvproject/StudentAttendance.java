package njvproject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class StudentAttendance extends JFrame {
    private JList<String> studentList;
    private DefaultListModel<String> listModel;
    private JTextField txtStudentId;
    private JTextField txtAttendance;

    public StudentAttendance() {
        setTitle("Student Attendance");
        setSize(400, 300);
        setLocationRelativeTo(null);

        listModel = new DefaultListModel<>();
        studentList = new JList<>(listModel);
        loadStudents();

        JScrollPane scrollPane = new JScrollPane(studentList);

        JLabel lblStudentId = new JLabel("Student ID:");
        txtStudentId = new JTextField();

        JLabel lblAttendance = new JLabel("Attendance (%):");
        txtAttendance = new JTextField();

        JButton btnMarkAttendance = new JButton("Mark Attendance");

        btnMarkAttendance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markAttendance();
            }
        });

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(lblStudentId);
        panel.add(txtStudentId);
        panel.add(lblAttendance);
        panel.add(txtAttendance);
        panel.add(new JLabel()); // Empty label for alignment
        panel.add(btnMarkAttendance);

        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadStudents() {
        ArrayList<Student> students = readStudentsFromFile();
        for (Student student : students) {
            listModel.addElement(student.getId() + " - " + student.getName());
        }
    }

    private void markAttendance() {
        String studentId = txtStudentId.getText();
        String attendanceStr = txtAttendance.getText();
        int attendance;

        try {
            attendance = Integer.parseInt(attendanceStr);
            if (attendance < 0 || attendance > 100) {
                JOptionPane.showMessageDialog(this, "Attendance must be between 0 and 100.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for attendance.");
            return;
        }

        ArrayList<Student> students = readStudentsFromFile();
        boolean found = false;

        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                student.setAttendance(attendance);
                found = true;
                break;
            }
        }

        if (found) {
            writeStudentsToFile(students);
            JOptionPane.showMessageDialog(this, "Attendance marked successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Student not found!");
        }
    }

    private ArrayList<Student> readStudentsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"))) {
            return (ArrayList<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void writeStudentsToFile(ArrayList<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


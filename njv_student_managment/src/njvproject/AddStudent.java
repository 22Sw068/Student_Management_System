package njvproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class AddStudent extends JFrame {

    private JTextField txtName, txtFatherName, txtGender, txtClass, txtDOB;

    public AddStudent() {
        setTitle("Add Student");
        setSize(500, 350);  // Increased the size of the frame
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Labels
        JLabel lblName = new JLabel("Student Name:");
        JLabel lblFatherName = new JLabel("Father's Name:");
        JLabel lblGender = new JLabel("Gender:");
        JLabel lblClass = new JLabel("Class:");
        JLabel lblDOB = new JLabel("Date of Birth:");

        // Text fields with increased size
        txtName = new JTextField(20);
        txtFatherName = new JTextField(20);
        txtGender = new JTextField(20);
        txtClass = new JTextField(20);
        txtDOB = new JTextField(20);

        // Save button
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveStudent();
            }
        });

        // Panel for form fields with increased padding
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblName, gbc);
        gbc.gridx = 1;
        formPanel.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblFatherName, gbc);
        gbc.gridx = 1;
        formPanel.add(txtFatherName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblGender, gbc);
        gbc.gridx = 1;
        formPanel.add(txtGender, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lblClass, gbc);
        gbc.gridx = 1;
        formPanel.add(txtClass, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(lblDOB, gbc);
        gbc.gridx = 1;
        formPanel.add(txtDOB, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(btnSave, gbc);

        // Adding padding around the form panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private void saveStudent() {
        String name = txtName.getText();
        String fatherName = txtFatherName.getText();
        String gender = txtGender.getText();
        String className = txtClass.getText();
        String dob = txtDOB.getText();

        Student student = new Student(generateStudentId(className), name, fatherName, gender, className, dob);

        ArrayList<Student> students = readStudentsFromFile();
        students.add(student);
        writeStudentsToFile(students);

        JOptionPane.showMessageDialog(this, "Student added successfully!");
    }

    private String generateStudentId(String className) {
        ArrayList<Student> students = readStudentsFromFile();
        long count = students.stream().filter(s -> s.getClassName().equals(className)).count();
        return className + (count + 1);
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

    public static void main(String[] args) {
        new AddStudent();
    }
}


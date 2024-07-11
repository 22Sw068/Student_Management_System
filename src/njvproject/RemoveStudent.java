package njvproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class RemoveStudent extends JFrame {

    private JTextField txtName;

    public RemoveStudent() {
        setTitle("Remove Student");
        setSize(300, 150); // Adjusted size
        setLocationRelativeTo(null);

        JLabel lblName = new JLabel("Student Name:");
        txtName = new JTextField(20); // Set preferred width of the text field

        JButton btnRemove = new JButton("Remove");

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });

        // Use GridBagLayout for more control over component placement
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding

        // Add components using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblName, gbc);

        gbc.gridx = 1;
        add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center align button
        add(btnRemove, gbc);

        setVisible(true);
    }

    private void removeStudent() {
        String name = txtName.getText();

        ArrayList<Student> students = readStudentsFromFile();
        boolean removed = students.removeIf(student -> student.getName().equalsIgnoreCase(name));
        writeStudentsToFile(students);

        if (removed) {
            JOptionPane.showMessageDialog(this, "Student removed successfully!");
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

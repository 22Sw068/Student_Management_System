package njvproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class RemoveTeacher extends JFrame {

    private JTextField txtName;

    public RemoveTeacher() {
        setTitle("Remove Teacher");
        setSize(400, 200); // Adjust the frame size to be medium-sized
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Labels
        JLabel lblName = new JLabel("Teacher Name:");

        // Text fields with preferred sizes
        txtName = new JTextField(15);

        // Remove button
        JButton btnRemove = new JButton("Remove");
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTeacher();
            }
        });

        // Panel for form fields
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
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(btnRemove, gbc);

        // Adding padding around the form panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private void removeTeacher() {
        String name = txtName.getText();

        ArrayList<Teacher> teachers = readTeachersFromFile();
        boolean removed = teachers.removeIf(teacher -> teacher.getName().equalsIgnoreCase(name));
        writeTeachersToFile(teachers);

        if (removed) {
            JOptionPane.showMessageDialog(this, "Teacher removed successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Teacher not found!");
        }
    }

    private ArrayList<Teacher> readTeachersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("teachers.dat"))) {
            return (ArrayList<Teacher>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void writeTeachersToFile(ArrayList<Teacher> teachers) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teachers.dat"))) {
            oos.writeObject(teachers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RemoveTeacher();
    }
}

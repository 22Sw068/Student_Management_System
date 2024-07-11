package njvproject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentMenu extends JFrame {

    public StudentMenu() {
        setTitle("Students");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JButton student_Add_btn = new JButton("Add Student");
        JButton rem_student_btn = new JButton("Remove Student");
        JButton student_attend_btn = new JButton("Student Attendance");

        // Action listeners for each button
        student_Add_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStudent();
            }
        });

        rem_student_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveStudent();
            }
        });

        student_attend_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentAttendance();
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5); // Add padding

        g.gridx = 0;  g.gridy = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        add(student_Add_btn, g);

        g.gridy = 1;
        add(rem_student_btn, g);

        g.gridy = 2;
        add(student_attend_btn, g);

        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentMenu();
            }
        });
    }
}

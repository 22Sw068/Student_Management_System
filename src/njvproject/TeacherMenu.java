package njvproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherMenu extends JFrame {

    public TeacherMenu() {
        setTitle("Teachers");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JButton btnAddTeacher = new JButton("Add Teacher");
        JButton btnRemoveTeacher = new JButton("Remove Teacher");

        // Set medium size for buttons
        Dimension buttonSize = new Dimension(150, 30);
        btnAddTeacher.setPreferredSize(buttonSize);
        btnRemoveTeacher.setPreferredSize(buttonSize);

        // Action listeners for each button
        btnAddTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTeacher();
            }
        });

        btnRemoveTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveTeacher();
            }
        });

        // Use GridBagLayout for more control over component placement
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding

        // Center align buttons horizontally and vertically within their cells
        gbc.anchor = GridBagConstraints.CENTER;

        // Add buttons using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(btnAddTeacher, gbc);

        gbc.gridy = 1;
        add(btnRemoveTeacher, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        // Run the teacher menu
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TeacherMenu();
            }
        });
    }
}

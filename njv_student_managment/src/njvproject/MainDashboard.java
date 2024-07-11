package njvproject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainDashboard extends JFrame {

    public MainDashboard() {
          setTitle("NJV SCHOOL KARACHI STUDENT MANAGEMENT DASHBOARD");
          setSize(500,350);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setLocationRelativeTo(null);
          JLabel titleLabel = new JLabel("NJV SCHOOL KARACHI", SwingConstants.CENTER);
          titleLabel.setFont(new Font("Serif", Font.BOLD, 18));

          JLabel dashboardLabel = new JLabel("STUDENT MANAGEMENT DASHBOARD", SwingConstants.CENTER);
          dashboardLabel.setFont(new Font("Serif", Font.PLAIN, 16));
          JButton teach_btn = createStyledButton("TEACHERS");
          JButton Std_btn= createStyledButton("STUDENTS");
          JButton Std_btn_his = createStyledButton("Students History");
          JButton teach_btn_his = createStyledButton("Teachers History");
        teach_btn.addActionListener(e -> new TeacherMenu());

        Std_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentMenu();
            }
        });

        Std_btn_his.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentHistory();
            }
        });

        teach_btn_his.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TeacherHistory();
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        buttonPanel.add(teach_btn);
        buttonPanel.add(Std_btn);
        buttonPanel.add(Std_btn_his);
        buttonPanel.add(teach_btn_his);

            JPanel buttonPanelWrapper = new JPanel(new GridBagLayout());
            buttonPanelWrapper.add(buttonPanel);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(dashboardLabel, BorderLayout.NORTH);
        centerPanel.add(buttonPanelWrapper, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    public static void main(String[] args) {
        new MainDashboard();
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class secondWindow {
    public secondWindow() {
        JFrame frame = new JFrame();
        frame.setTitle("Add new Student");
        frame.setSize(420, 160);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel jpanel1 = new JPanel();
        JPanel jpanel2 = new JPanel();

        jpanel1.setBackground(Color.GRAY);
        jpanel2.setBackground(Color.GRAY);

        frame.add(jpanel1, BorderLayout.CENTER);
        frame.add(jpanel2, BorderLayout.SOUTH);

        JLabel text1 = new JLabel("Student ID: ");
        jpanel1.add(text1);

        JTextField studentID = new JTextField();
        studentID.setPreferredSize(new Dimension(280, 24));
        jpanel1.add(studentID);

        JLabel text2 = new JLabel("Student name: ");
        jpanel1.add(text2);

        JTextField studentName = new JTextField();
        studentName.setPreferredSize(new Dimension(280, 24));
        jpanel1.add(studentName);

        JButton button1 = new JButton("Accept");
        button1.addActionListener(e -> System.out.println("Accept"));
        button1.setFocusable(false);
        jpanel2.add(button1);

        JButton button2 = new JButton("Cancel");
        button2.addActionListener(e -> {
            firstWindow.isSecondWindowOpen = false;
            frame.dispose();
        });
        button2.setFocusable(false);
        jpanel2.add(button2);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                firstWindow.isSecondWindowOpen = false;
            }
        });

        frame.setVisible(true);
    }
}

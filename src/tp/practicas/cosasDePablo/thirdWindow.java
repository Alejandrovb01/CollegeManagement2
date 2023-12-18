package tp.practicas.cosasDePablo;

import javax.swing.*;
import java.awt.*;

public class thirdWindow {
    public thirdWindow() {
        JFrame frame = new JFrame();
        frame.setTitle("Enroll Student in course");
        frame.setSize(420, 160);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel jpanel1 = new JPanel();
        JPanel jpanel2 = new JPanel();

        jpanel1.setBackground(Color.GRAY);
        jpanel2.setBackground(Color.GRAY);

        frame.add(jpanel1, BorderLayout.CENTER);
        frame.add(jpanel2, BorderLayout.SOUTH);

        JLabel text1 = new JLabel("Student: ");
        jpanel1.add(text1);

        String[] example1 = {"Pablo", "Joel", "Alejandro"};
        JComboBox<String> studentSelect = new JComboBox<>(example1);
        jpanel1.add(studentSelect);

        JLabel text2 = new JLabel("Course: ");
        jpanel1.add(text2);

        String[] example2 = {"Lengua", "Mates", "Historia"};
        JComboBox<String> courseSelect = new JComboBox<>(example2);
        jpanel1.add(courseSelect);

        JButton button1 = new JButton("Accept");
        button1.addActionListener(e -> System.out.println("Accept"));
        button1.setFocusable(false);
        jpanel2.add(button1);

        JButton button2 = new JButton("Cancel");
        button2.addActionListener(e -> {
            firstWindow.isThirdWindowOpen = false;
            frame.dispose();
        });
        button2.setFocusable(false);
        jpanel2.add(button2);

        frame.setVisible(true);
    }
}

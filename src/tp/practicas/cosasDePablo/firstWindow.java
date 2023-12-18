package tp.practicas.cosasDePablo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class firstWindow {
    public static boolean isSecondWindowOpen = false;
    public static boolean isThirdWindowOpen = false;

    public firstWindow() {
        JFrame frame = new JFrame();
        frame.setTitle("School App");
        frame.setSize(480, 320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel jpanel1 = new JPanel();
        JPanel jpanel2 = new JPanel();

        jpanel1.setBackground(Color.white);
        jpanel2.setBackground(Color.GRAY);

        jpanel2.setPreferredSize(new Dimension(200, 320));

        frame.add(jpanel1, BorderLayout.CENTER);
        frame.add(jpanel2, BorderLayout.EAST);

        JMenuBar jmenuBar = new JMenuBar();
        JMenu menuOption = new JMenu("Menu");
        jmenuBar.add(menuOption);
        frame.setJMenuBar(jmenuBar);

        JMenuItem addItem = new JMenuItem("Add new Student");
        JMenuItem enrollItem = new JMenuItem("Enroll Student in course");
        JMenuItem exit = new JMenuItem("Exit");
        menuOption.add(addItem);
        menuOption.add(enrollItem);
        menuOption.add(exit);

        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isSecondWindowOpen) {
                    isSecondWindowOpen = true;
                    new secondWindow();
                }
            }
        });

        enrollItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isThirdWindowOpen) {
                    isThirdWindowOpen = true;
                    new thirdWindow();
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    JRadioButton orderName = new JRadioButton("Order by student's name");
        orderName.setBackground(Color.GRAY);
        orderName.setFocusable(false);
        orderName.addActionListener(e -> System.out.println("ORDER ME BY NAME"));

        JRadioButton orderId = new JRadioButton("Order by student's id");
        orderId.setBackground(Color.GRAY);
        orderId.setFocusable(false);
        orderId.addActionListener(e -> System.out.println("ORDER ME BY ID"));

        ButtonGroup orderOptions = new ButtonGroup();
        orderOptions.add(orderName);
        orderOptions.add(orderId);
        jpanel2.add(orderName);
        jpanel2.add(orderId);

        JButton button1 = new JButton("Add new student");
        button1.addActionListener(e -> {
            if (!isSecondWindowOpen) {
                isSecondWindowOpen = true;
                new secondWindow();
            }
        });
        button1.setFocusable(false);
        jpanel2.add(button1);

        JButton button2 = new JButton("Enroll student in course");
        button2.addActionListener(e -> {
            if (!isThirdWindowOpen) {
                isThirdWindowOpen = true;
                new thirdWindow();
            }
        });
        button2.setFocusable(false);
        jpanel2.add(button2);

        frame.setVisible(true);

    }
}

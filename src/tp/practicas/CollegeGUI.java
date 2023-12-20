package tp.practicas;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.regex.*;

import tp.practicas.CollegeManagement.*;


public class CollegeGUI extends JFrame {
    static private OfferedCourses offeredCourses= new OfferedCourses();
    static private EnrolledStudents enrolledStudents = new EnrolledStudents();;

    private JTextArea data;
    private JRadioButton orderName;
    private JRadioButton orderId;

    private boolean selectedRadioButton;
    private JButton addStudent;
    private JButton enrollStudent;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem itemAddStudent, itemEnrollStudent, itemExit;

    private void constructMenu() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menu = new JMenu("Menu");
        itemAddStudent = new JMenuItem("Add new student");
        itemAddStudent.addActionListener(e -> addStudentWindow());
        itemEnrollStudent = new JMenuItem("Enroll student in course");
        itemEnrollStudent.addActionListener(e -> enrollStudentWindow());
        itemExit = new JMenuItem("Exit");
        itemExit.addActionListener(e -> System.exit(0));
        menu.add(itemAddStudent);
        menu.add(itemEnrollStudent);
        menu.add(itemExit);
        menuBar.add(menu);
    }
    private Component constructControls() {
        orderName = new JRadioButton("Order by student's name", true);
        orderName.addActionListener(e -> {
            selectedRadioButton = false;
            updateTextArea();
        });
        orderId = new JRadioButton("Order by student's id", false);
        orderId.addActionListener(e -> {
            selectedRadioButton = true;
            updateTextArea();
        });
        ButtonGroup bg = new ButtonGroup();
        bg.add(orderName);
        bg.add(orderId);
        addStudent = new JButton("Add new student");
        addStudent.addActionListener(e -> addStudentWindow());
        enrollStudent = new JButton("Enroll student in course");
        enrollStudent.addActionListener(e -> enrollStudentWindow());
        JPanel east = new JPanel();
        east.setLayout(new BoxLayout(east, BoxLayout.PAGE_AXIS));
        JPanel order = new JPanel();
        order.setLayout(new BoxLayout(order, BoxLayout.PAGE_AXIS));
        order.add(orderName);
        order.add(orderId);
        order.setBorder(BorderFactory.createTitledBorder("List order"));
        east.add(order);
        east.add(addStudent);
        east.add(enrollStudent);
        return east;
    }

    private Component constructInfo() {
        data = new JTextArea();
        data.setEditable(false);
        return new JScrollPane(data);
    }

    public CollegeGUI(){
        super("College Management");
        InitialData.init(offeredCourses, enrolledStudents);
        constructMenu();
        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());
        pane.add(constructInfo(), BorderLayout.CENTER);
        pane.add(constructControls(), BorderLayout.EAST);
        setContentPane(pane);
        setLocation(50,50);
        setSize(700,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        updateTextArea();
    }

    private void updateTextArea() {
        List<Student> students;
        if (selectedRadioButton) {
            students = enrolledStudents.getStudentsOrderById();
        } else {
            students = enrolledStudents.getStudentsOrderByName();
        }
        StringBuilder sb = new StringBuilder();
        for (Student student : students) {
            sb.append(student.toString());
            sb.append("\n");
        }
        data.setText(sb.toString());
    }

    private void addStudentWindow() {
        JTextField textFieldName = new JTextField(20); 
        JTextField textFieldID = new JTextField(20); 
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        panel.add(textFieldName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("ID:"), gbc);

        gbc.gridx = 1;
        panel.add(textFieldID, gbc);

        int option = JOptionPane.showConfirmDialog(this, panel, "Add new student", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(textFieldID.getText());
                String name = textFieldName.getText();
                if(!enrolledStudents.addStudent(new Student(id, name))) {
                    JOptionPane.showMessageDialog(this, "A student already has that ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                updateTextArea();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid ID (numeric).", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void enrollStudentWindow() {
        List<Student> students = enrolledStudents.getStudentsOrderById();
        String[] studentNames = new String[students.size()];
        for(int i=0; i< students.size(); i++) {
            studentNames[i] = students.get(i).getId() + "-" + students.get(i).getName();
        }
        List<Course> courses = offeredCourses.getCourses();
        String[] coursesNames = new String[courses.size()];
        for(int i=0; i< courses.size(); i++) {
            coursesNames[i] = courses.get(i).toString();
        }

        JComboBox<String> comboStudents = new JComboBox<>(studentNames);
        JComboBox<String> comboCourses = new JComboBox<>(coursesNames);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(new JLabel("Student: "), gbc);

        gbc.gridx++;
        panel.add(comboStudents, gbc);

        gbc.gridx++;
        panel.add(new JLabel("Course: "), gbc);

        gbc.gridx++;
        panel.add(comboCourses, gbc);

        int option = JOptionPane.showConfirmDialog(this, panel, "Enroll student in course", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            Course selectedCourse = null;
            Student selectedStudent = null;
            Pattern patternCourse = Pattern.compile("\\((\\d+)\\)");
            Pattern patternStudent = Pattern.compile("(\\d+)-.*");
            Matcher matcherCourse = patternCourse.matcher((CharSequence) Objects.requireNonNull(comboCourses.getSelectedItem()));
            Matcher matcherStudent = patternStudent.matcher((CharSequence) Objects.requireNonNull(comboStudents.getSelectedItem()));
            if(matcherCourse.find()) {selectedCourse = offeredCourses.getCourse(Integer.parseInt(matcherCourse.group(1)));}
            if(matcherStudent.find()) {selectedStudent = enrolledStudents.getStudent(Integer.parseInt(matcherStudent.group(1)));}

            assert selectedStudent != null;
            if (!selectedStudent.enrollCourse(selectedCourse)) {
                JOptionPane.showMessageDialog(this, "Student already in course.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            updateTextArea();
        }
    }

    public static void main (String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception ignored) {}
        new CollegeGUI();

    }
}

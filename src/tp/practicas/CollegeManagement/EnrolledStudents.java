package tp.practicas.CollegeManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.util.Comparator;

public class EnrolledStudents {

    private final Set<Student> container;

    /**
     * Creates a new empty list of students.
     * */
    public EnrolledStudents() {
        container = new HashSet<>();
    }

    /**
     * Adds a new student to the list.
     *
     * @param student to add.
     * @return True if the student wasn't on the list.
     * */
    public boolean addStudent(Student student) {
        return container.add(student);
    }

    /**
     * Removes a student from the list
     *
     * @param id to find the student.
     * @return True if the Student was on the list and was removed.
     * */
    public boolean removeStudent(int id) {
        for (Student student : container) {
            if (student.getId() == id) {
                container.remove(student);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a student from the list that matches the id.
     *
     * @param id to find the student.
     * @return student.
     * */
    public Student getStudent(int id) {
        for (Student student : container) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    /**
     * Returns a list of students that are in the same course.
     *
     * @param id of the course.
     * @return List of students sorted by name and if they are the same by ID.
     * */
    public List<Student> getStudentsByCourse(int id) {
        final List<Student> studentsByCourse = new ArrayList<>();
        for (Student student : container) {
            for (Course course : student.getEnrolledCourses()){
                if (course.getCode() == id) {
                    studentsByCourse.add(student);
                }
            }
        }
        Collections.sort(studentsByCourse, new StudentNameIdComparator());
        return studentsByCourse;
    }

    /**
     * Returns a list of all students sorted by name.
     *
     * @return List of students.
     * */
    public List<Student> getStudentsOrderByName() {
        List<Student> sortedStudents = new ArrayList<>(container);
        Collections.sort(sortedStudents, new StudentNameIdComparator());
        return sortedStudents;
    }

    /**
     * Returns a list of all students sorted by name.
     *
     * @return List of students.
     * */
    public List<Student> getStudentsOrderById() {
        List<Student> sortedStudents = new ArrayList<>(container);
        Collections.sort(sortedStudents, new StudentIdComparator());
        return sortedStudents;
    }

    /**
     * A comparator to sort students by ID.
     * */
    private static class StudentIdComparator implements Comparator<Student> {
        @Override
        public int compare(Student student1, Student student2) {
            return Integer.compare(student1.getId(), student2.getId());
        }
    }

    /**
     * A comparator to sort students by name and if they are the same then by ID.
     * */
    private static class StudentNameIdComparator implements Comparator<Student> {
        @Override
        public int compare(Student student1, Student student2) {
            int nameComparison = student1.getName().compareTo(student2.getName());
            if (nameComparison == 0) {
                return Integer.compare(student1.getId(), student2.getId());
            }
            return nameComparison;
        }
    }
}

package tp.practicas.CollegeManagement;

import java.util.*;

public class Student {

    private final int id;
    private final String name;
    private Set<Course> enrolledCourses = new TreeSet<>();

    /**
     * Creates a new student.
     *
     * @param id The identification number of the student.
     * @param name The name of the student.
     */
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return The id number of the student.
     */
    public int getId() {
        return id;
    }

    /**
     * @return The name of the student.
     */
    public String getName() {
        return name;
    }

    /**
     * Allows a student to enroll in a Course.
     *
     * @return True if the student wasn't enrolled.
     */
    public boolean enrollCourse(Course course) {
        return enrolledCourses.add(course);
    }

    /**
     * Allows a student to unenroll from a Course.
     *
     * @return True if the student was enrolled in the course.
     */
    public boolean unenrollCourse(Course course) {
        return enrolledCourses.remove(course);
    }

    /**
     * @return TreeSet of all the enrolled courses.
     */
    public Collection<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    /**
     * @return Student information in this format: "id-name[(course id)course name, ...]".
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getId() + "-" + getName());
        result.append("[");
        int index = 1;
        int sizeCourses = getEnrolledCourses().size();
        for (Course course : getEnrolledCourses()) {
            if (sizeCourses != index) {
                result.append(course.toString()).append(", ");
                ++index;
            }else {
                result.append(course.toString());
            }
        }
        result.append("]");
        return result.toString();
    }

    /**
     * @return True if they are from the same class and their hash codes are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.hashCode() == o.hashCode();
    }

    /**
     * @return hash code generated from id and name.
     */
    @Override
    public int hashCode() {
        return id;
    }
}

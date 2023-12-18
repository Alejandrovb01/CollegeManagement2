package tp.practicas.CollegeManagement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class OfferedCourses {

    private Set<Course> subjectsOffered;

    /**
     * Creates a new empty list of courses.
     * */
    public OfferedCourses() {
        this.subjectsOffered  = new HashSet<>(0);
    }

    /**
     * Adds a new course to the list.
     *
     * @param newCourse to add.
     * @return true if newCourse wasn't already on the list.
     * */
    public boolean addCourse(Course newCourse) {
        return subjectsOffered.add(newCourse);
    }

    /**
     * Removes a course from the list.
     *
     * @param id of the course to remove.
     * @return true if a course with an id was on the list and was removed.
     * */
    public boolean removeCourse(int id) {
        for (Course course : this.subjectsOffered) {
            if (course.getCode() == id) {
                this.subjectsOffered.remove(course);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets a course from the list.
     *
     * @param id of the course to get.
     * @return Course that matches the ID, if none are found it returns null.
     * */
    public Course getCourse(int id) {
        for (Course course : this.subjectsOffered) {
            if (course.getCode() == id) {
                return course;
            }
        }
        return null;
    }
    /**
     * Returns the list of Courses.
     *
     * @return ArrayList of all courses on list.
     * */
    public List<Course> getCourses() {
        return new ArrayList<>(subjectsOffered);
    }
}

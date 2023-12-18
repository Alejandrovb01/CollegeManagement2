package tp.practicas.CollegeManagement;

public class Course implements Comparable<Course> {
    private final int id;
    private final String subject;

    /**
     * creates a new Course.
     *
     * @param id the identification number of the subject.
     * @param subject the name of the subject.
     * */
    public Course(int id, String subject) {
        this.id = id;
        this.subject = subject;
    }

    /**
     * @return id of the course.
     * */
    public int getCode() {
        return id;
    }

    /**
     * @return name of the course.
     * */
    public String getName() {
        return subject;
    }

    /**
     * @return information of the course on the format: "(id)subject".
     * */
    @Override
    public String toString() {
        return String.format("(%s)%s", id, subject);
    }

    /**
     *  creates a hash code by adding together subject's hashCode and id number
     *
     * @return hash code.
     * */
    @Override
    public int hashCode() {
        return subject.hashCode() + id;
    }

    /**
     * @return true if they are from the same class and their hash codes are the same.
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.hashCode() == o.hashCode();
    }

    @Override
    public int compareTo(Course otherCourse) {
        return this.id - otherCourse.id;
    }
}

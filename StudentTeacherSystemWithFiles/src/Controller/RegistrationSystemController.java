import java.util.List;

/**
 * controller for the RegistrationSystem repository
 */
public class RegistrationSystemController {
    private RegistrationSystem r;

    public RegistrationSystemController(RegistrationSystem r) {
        this.r = r;
    }

    public boolean register(Course course, Student student){
        return this.r.register(course,student);
    }

    public List<Course> retrieveCoursesWithFreePlaces(CourseInMemoryRepo cr){
        return this.r.retrieveCoursesWithFreePlaces(cr);
    }

    public List<Student> retrieveStudentsEnrolledForACourse(Course course){
        return this.r.retrieveStudentsEnrolledForACourse(course);
    }

    public List<Course> getAllCourses(CourseInMemoryRepo cr){
        return this.r.getAllCourses(cr);
    }

    public void changeCredit(Course course, int credit, StudentInMemoryRepo sr){
        this.r.changeCredit(course,credit,sr);
    }

    public void deleteCourse(Course course, TeacherInMemoryRepo tr, StudentInMemoryRepo sr, CourseInMemoryRepo cr){
        this.r.deleteCourse(course,tr,sr,cr);
    }

    public List<Student> sortStudents(StudentInMemoryRepo s){
        return this.r.sortStudents(s);
    }

    public List<Course> sortCourses(CourseInMemoryRepo c){
        return this.r.sortCourses(c);
    }

    public List<Student> filterStudents(StudentInMemoryRepo s){
        return this.r.filterStudents(s);
    }

    public List<Course> filterCourses(CourseInMemoryRepo c){
        return this.r.filterCourses(c);
    }

}

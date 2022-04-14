import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

public class RegistrationSystemControllerTest {
    @Test
    void register() {
        Student s1 = new Student("A","B",101,9, Collections.emptyList());
        Person p1 = new Person("O","P");
        Course c1 = new Course("X",p1,4,Collections.emptyList(),6);

        RegistrationSystem r = new RegistrationSystem();
        RegistrationSystemController c = new RegistrationSystemController(r);


        assertEquals(c.register(c1,s1), true);
    }

    @Test
    void retrieveCoursesWithFreePlaces() {
        Person p1 = new Person("O","P");
        Student s1 = new Student("A","B",101,9, Collections.emptyList());
        Course c1 = new Course("X",p1,1,Collections.emptyList(),6);
        Course c2 = new Course("Y",p1,7,Collections.emptyList(),3);

        CourseInMemoryRepo cr = new CourseInMemoryRepo();
        RegistrationSystem r = new RegistrationSystem();
        RegistrationSystemController c = new RegistrationSystemController(r);

        cr.save(c1);
        cr.save(c2);

        c.register(c1,s1);

        List<Course> courses = c.retrieveCoursesWithFreePlaces(cr);

        assertEquals(courses, List.of(c2));
    }

    @Test
    void retrieveStudentsEnrolledForACourse() {
        Person p1 = new Person("O","P");
        Student s1 = new Student("A","B",101,9, Collections.emptyList());
        Student s2 = new Student("C","D",102,10, Collections.emptyList());
        Student s3 = new Student("E","F",103,20, Collections.emptyList());
        Course c1 = new Course("X",p1,5,Collections.emptyList(),6);

        RegistrationSystem r = new RegistrationSystem();
        RegistrationSystemController c = new RegistrationSystemController(r);

        c.register(c1,s1);
        c.register(c1,s2);
        c.register(c1,s3);

        List<Student> students = c.retrieveStudentsEnrolledForACourse(c1);

        assertEquals(students, List.of(s1,s2,s3));
    }

    @Test
    void getAllCourses() {
        Person p1 = new Person("O","P");
        Course c1 = new Course("X",p1,1,Collections.emptyList(),6);
        Course c2 = new Course("Y",p1,7,Collections.emptyList(),3);
        Course c3 = new Course("Z",p1,4,Collections.emptyList(),4);

        CourseInMemoryRepo cr = new CourseInMemoryRepo();
        RegistrationSystem r = new RegistrationSystem();
        RegistrationSystemController c = new RegistrationSystemController(r);

        cr.save(c1);
        cr.save(c2);
        cr.save(c3);

        List<Course> courses = c.getAllCourses(cr);

        assertEquals(courses,List.of(c1,c2,c3));
    }

    @Test
    void changeCredit() {
        Person p1 = new Person("O","P");
        Course c1 = new Course("X",p1,1,Collections.emptyList(),6);

        StudentInMemoryRepo sr = new StudentInMemoryRepo();
        RegistrationSystem r = new RegistrationSystem();
        RegistrationSystemController c = new RegistrationSystemController(r);

        c.changeCredit(c1,1,sr);

        assertEquals(c1.getCredits(), 1);
    }

    @Test
    void sortStudents() {
        Student s1 = new Student("E","F",103,20, Collections.emptyList());
        Student s2 = new Student("C","D",102,10, Collections.emptyList());
        Student s3 = new Student("A","B",101,9, Collections.emptyList());

        StudentInMemoryRepo sr = new StudentInMemoryRepo();
        RegistrationSystem r = new RegistrationSystem();
        RegistrationSystemController c = new RegistrationSystemController(r);

        sr.save(s1);
        sr.save(s2);
        sr.save(s3);

        List<Student> students = c.sortStudents(sr);

        assertEquals(students.get(0), s3);

    }

    @Test
    void sortCourses() {
        Person p1 = new Person("O","P");
        Course c1 = new Course("X",p1,1,Collections.emptyList(),6);
        Course c2 = new Course("Y",p1,7,Collections.emptyList(),3);
        Course c3 = new Course("Z",p1,4,Collections.emptyList(),4);

        CourseInMemoryRepo cr = new CourseInMemoryRepo();
        RegistrationSystem r = new RegistrationSystem();
        RegistrationSystemController c = new RegistrationSystemController(r);

        cr.save(c1);
        cr.save(c2);
        cr.save(c3);

        List<Course> courses = c.sortCourses(cr);

        assertEquals(courses.get(0),c2);
    }

    @Test
    void filterStudents() {
        Student s1 = new Student("E","F",103,31, Collections.emptyList());
        Student s2 = new Student("C","D",102,30, Collections.emptyList());
        Student s3 = new Student("A","B",101,12, Collections.emptyList());

        StudentInMemoryRepo sr = new StudentInMemoryRepo();
        RegistrationSystem r = new RegistrationSystem();
        RegistrationSystemController c = new RegistrationSystemController(r);

        sr.save(s1);
        sr.save(s2);
        sr.save(s3);

        List<Student> students = c.filterStudents(sr);

        assertEquals(students.get(0), s3);
    }

    @Test
    void filterCourses() {
        Person p1 = new Person("O","P");
        Course c1 = new Course("X",p1,0,Collections.emptyList(),6);
        Course c2 = new Course("Y",p1,7,Collections.emptyList(),3);
        Course c3 = new Course("Z",p1,4,Collections.emptyList(),4);

        CourseInMemoryRepo cr = new CourseInMemoryRepo();
        RegistrationSystem r = new RegistrationSystem();
        RegistrationSystemController c = new RegistrationSystemController(r);

        cr.save(c1);
        cr.save(c2);
        cr.save(c3);

        List<Course> courses = c.filterCourses(cr);

        assertEquals(courses.get(0),c2);
        assertEquals(courses, List.of(c2,c3));
    }
}

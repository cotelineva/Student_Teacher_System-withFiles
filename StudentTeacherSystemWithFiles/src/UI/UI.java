import java.util.*;

public class UI {
    public static void main(String[] args){
        StudentInMemoryRepo sr = new StudentInMemoryRepo();
        TeacherInMemoryRepo tr = new TeacherInMemoryRepo();
        CourseInMemoryRepo cr = new CourseInMemoryRepo();

        RegistrationSystem r = new RegistrationSystem();

        RegistrationSystemController c = new RegistrationSystemController(r);

        Person p1 = new Person("Ion","Ionescu");
        Person p2 = new Person("Ana","Enescu");
        Person p3 = new Person("Radu","Albu");
        Person p4 = new Person("Maria","Zaharia");

        Teacher t1 = new Teacher("Ion","Ionescu",Collections.<Course>emptyList());
        Teacher t2 = new Teacher("Ana","Enescu",Collections.<Course>emptyList());
        Teacher t3 = new Teacher("Radu","Albu",Collections.<Course>emptyList());
        Teacher t4 = new Teacher("Maria","Zaharia",Collections.<Course>emptyList());

        Student s1 = new Student("Paul","Popescu",1000,0,Collections.<Course>emptyList());
        Student s2 = new Student("Andrei", "Popovici", 1020, 0, Collections.<Course>emptyList());
        Student s3 = new Student("Maria","Lovinescu",1050,0,Collections.<Course>emptyList());
        Student s4 = new Student("Ana","Avram",1210,0,Collections.<Course>emptyList());
        Student s5 = new Student("Raluca", "Ion", 1320, 0, Collections.<Course>emptyList());
        Student s6 = new Student("Mirela","Dumitru",1650,0,Collections.<Course>emptyList());

        Course c1 = new Course("BD", p1, 1, Collections.<Student>emptyList(), 5);
        Course c2 = new Course("MAP", p2,3,Collections.<Student>emptyList(),6);
        Course c3 = new Course("FP", p2, 2,Collections.<Student>emptyList(),13);
        Course c4 = new Course("ASC",p3,5,Collections.<Student>emptyList(),7);
        Course c5 = new Course("SO",p4,4,Collections.<Student>emptyList(),3);

        t1.setCourses(Arrays.asList(c1));
        t2.setCourses(Arrays.asList(c2,c3));
        t3.setCourses(Arrays.asList(c4));
        t4.setCourses(Arrays.asList(c5));

        tr.save(t1);
        tr.save(t2);
        tr.save(t3);
        tr.save(t4);

        sr.save(s1);
        sr.save(s2);
        sr.save(s3);
        sr.save(s4);
        sr.save(s5);
        sr.save(s6);

        cr.save(c1);
        cr.save(c2);
        cr.save(c3);
        cr.save(c4);
        cr.save(c5);

        c.register(c1,s1);
        c.register(c2,s2);
        c.register(c2,s3);



        System.out.println("Are you a student or a teacher?\n" +
                            "Press 1 for Teacher\n" +
                            "\tOR\n" +
                            "Press 2 for Student\n" +
                            "\tOR\n" +
                            "Press 0 to EXIT\n ");

        Scanner scanner = new Scanner(System.in);
        int n, x;
        n = scanner.nextInt();

        if(n == 0){
            System.out.println("Goodbye!");
        }
        while(n != 0) {
            if (n == 1) {
                System.out.println("Welcome Teacher!");
                System.out.println("Welcome to the Registration System! \n" +
                        "\tPress 0: Exit \n" +
                        "\tPress 1: To see which students are enrolled for a course\n" +
                        "\tPress 2: To see all available courses\n" +
                        "\tPress 3: To change the credits of a course\n" +
                        "\tPress 4: To see students sorted by last name\n" +
                        "\tPress 5: To see students filtered by total credits\n" +
                        "\tPress 6: To add a course\n" +
                        "\tPress 7: To add a teacher\n" +
                        "\n" +
                        "Your key: ");
                n = scanner.nextInt();
                if (n == 0) {
                    System.out.println("Bye!");
                } else {
                    x = n;
                    while (x != 0) {
                        if (x == 1) {
                            System.out.println("\nEnter the course name:");
                            Scanner sc = new Scanner(System.in);
                            String course;
                            course = sc.nextLine();

                            List<Student> students = new ArrayList<>();
                            for (Course C : cr.findAll()) {
                                if (course.equals(C.getName())) {
                                    students = c.retrieveStudentsEnrolledForACourse(C);
                                }
                            }
                            for (Student s : students) {
                                System.out.println(s.getFirstName() + " " + s.getLastName() + " " + s.getStudentId());
                            }
                            System.out.println();
                        }
                        if (x == 2) {
                            List<Course> list = new ArrayList<>();
                            list = c.getAllCourses(cr);

                            for (Course C : list) {
                                System.out.println("Name: " + C.getName() + " Credits: " + C.getCredits() + " Max Enrollment: " + C.getMaxEnrollment());
                            }
                            System.out.println();
                        }
                        if (x == 3) {
                            System.out.println("\nEnter the course name:");
                            Scanner sc = new Scanner(System.in);
                            String course;
                            course = sc.nextLine();

                            System.out.println("\nEnter the new credits:");
                            int credits;
                            credits = sc.nextInt();

                            for (Course C : cr.findAll()) {
                                if (course.equals(C.getName())) {
                                    c.changeCredit(C, credits, sr);
                                    System.out.println("Credits changed to " + credits + "!\n\n");
                                }
                            }
                        }
                        if (x == 4) {
                            List<Student> list = new ArrayList<>();
                            list = c.sortStudents(sr);

                            for (Student s : list) {
                                System.out.println(s.getFirstName() + " " + s.getLastName() + " ID:" + s.getStudentId());
                            }
                        }
                        if (x == 5) {
                            List<Student> list = new ArrayList<>();
                            list = c.filterStudents(sr);

                            for (Student s : list) {
                                System.out.println(s.getFirstName() + " " + s.getLastName() + " ID:" + s.getStudentId() + " Total Credits:" + s.getTotalCredits());
                            }
                        }
                        if (x == 6) {
                            System.out.println("\nEnter the course name:");
                            Scanner sc = new Scanner(System.in);
                            String name;
                            name = sc.nextLine();

                            System.out.println("\nEnter the first name of the teacher:");
                            String firstName;
                            firstName = sc.nextLine();

                            System.out.println("\nEnter the last name of the teacher:");
                            String lastName;
                            lastName = sc.nextLine();
                            Person p = new Person(firstName, lastName);

                            System.out.println("\nEnter the maximum enrollment number:");
                            int maxEnrollment;
                            maxEnrollment = sc.nextInt();

                            System.out.println("\nEnter the credits:");
                            int credits;
                            credits = sc.nextInt();

                            Course course = new Course(name, p, maxEnrollment, Collections.<Student>emptyList(), credits);

                            cr.save(course);

                            System.out.println("Course was added!");
                        }
                        if (x == 7) {
                            Scanner sc = new Scanner(System.in);

                            System.out.println("\nEnter the first name of the teacher:");
                            String firstName;
                            firstName = sc.nextLine();

                            System.out.println("\nEnter the last name of the teacher:");
                            String lastName;
                            lastName = sc.nextLine();

                            Teacher teacher = new Teacher(firstName, lastName, Collections.emptyList());
                            tr.save(teacher);

                            System.out.println("Teacher was added!");

                        }

                        System.out.println(
                                "\tPress 0: Exit \n" +
                                        "\tPress 1: To see which students are enrolled for a course\n" +
                                        "\tPress 2: To see all available courses\n" +
                                        "\tPress 3: To change the credits of a course\n" +
                                        "\tPress 4: To see students sorted by last name\n" +
                                        "\tPress 5: To see students filtered by total credits\n" +
                                        "\tPress 6: To add a course\n" +
                                        "\tPress 7: To add a teacher\n" +
                                        "\n" +
                                        "Your key: ");
                        n = scanner.nextInt();
                        x = n;
                        if (x == 0) {
                            System.out.println("Bye!");
                        }
                    }
                }
            }
            else if (n == 2) {
                System.out.println("Welcome Student!");
                System.out.println("Welcome to the Registration System! \n" +
                        "\tPress 0: Exit \n" +
                        "\tPress 1: To register to a course\n" +
                        "\tPress 2: To see courses with available places\n" +
                        "\tPress 3: To see courses sorted by credits\n" +
                        "\tPress 4: To see courses filtered by available places\n" +
                        "\tPress 5: To add a new student\n" +
                        "\n" +
                        "Your key: ");
                n = scanner.nextInt();
                if (n == 0) {
                    System.out.println("Bye!");
                } else {
                    x = n;
                    while (x != 0) {
                        if (x == 1) {
                            Scanner sc = new Scanner(System.in);

                            System.out.println("\nEnter the name of the course:");
                            String name;
                            name = sc.nextLine();

                            System.out.println("\nEnter the id of the student:");
                            Long id;
                            id = sc.nextLong();


                            Student student = new Student("", "", 0, 0, Collections.emptyList());
                            Course course = new Course("", p1, 0, Collections.emptyList(), 0);


                            for (Student s : sr.findAll()) {
                                if (s.getStudentId() == id) {
                                    student = s;
                                }
                            }

                            for (Course C : cr.findAll()) {
                                if (name.equals(C.getName())) {
                                    course = C;
                                }
                            }

                            if (course.getName() != "" && student.getFirstName() != "") {
                                c.register(course, student);
                                System.out.println("Student was registered to the course!");
                            } else {
                                System.out.println("The input was false, could not register!");
                            }
                        }
                        if (x == 2) {
                            c.retrieveCoursesWithFreePlaces(cr);
                        }
                        if (x == 3) {
                            List<Course> courses = c.sortCourses(cr);

                            for(Course C:courses){
                                System.out.println("Name: "+C.getName()+"; Max Enrollment: "+C.getMaxEnrollment()+"; Credits: "+C.getCredits());
                            }
                        }
                        if (x == 4) {
                            List<Course> courses = c.filterCourses(cr);

                            for(Course C:courses){
                                System.out.println("Name: "+C.getName()+"; Credits: "+C.getCredits()+"; available places: "+(C.getMaxEnrollment() - C.getStudentsEnrolled().size()));
                            }
                        }
                        if (x == 5) {
                            Scanner sc = new Scanner(System.in);

                            System.out.println("\nEnter the first name of the student:");
                            String firstName;
                            firstName = sc.nextLine();

                            System.out.println("\nEnter the last name of the student:");
                            String lastName;
                            lastName = sc.nextLine();

                            System.out.println("\nEnter the id of the student:");
                            Long id;
                            id = sc.nextLong();

                            Student student = new Student(firstName,lastName,id,0,Collections.emptyList());

                            sr.save(student);

                            System.out.println("Student was added!\n");

                        }

                        System.out.println(
                                "\tPress 0: Exit \n" +
                                        "\tPress 1: To register to a course\n" +
                                        "\tPress 2: To see courses with available places\n" +
                                        "\tPress 3: To see courses sorted by credits\n" +
                                        "\tPress 4: To see courses filtered by available places\n" +
                                        "\tPress 5: To add a new student\n" +
                                        "\n" +
                                        "Your key: ");

                        n = scanner.nextInt();
                        x = n;
                        if (x == 0) {
                            System.out.println("Bye!");
                        }
                    }
                }

            }
            System.out.println("Are you a student or a teacher?\n" +
                    "Press 1 for Teacher\n" +
                    "\tOR\n" +
                    "Press 2 for Student\n" +
                    "\tOR\n" +
                    "Press 0 to EXIT\n ");

            n = scanner.nextInt();
            if (n == 0) {
                System.out.println("Goodbye!");
            }
        }
    }
}

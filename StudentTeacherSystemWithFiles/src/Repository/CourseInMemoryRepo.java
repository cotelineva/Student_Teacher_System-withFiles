import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseInMemoryRepo implements CRUD_Repo<Course>{
    private List<Course> courses = new ArrayList<>();

    @Override
    public Course findOne(int id) {
        if(courses.contains(courses.get(id)))
            return courses.get(id);
        return null;
    }

    @Override
    public Iterable<Course> findAll() {
        return courses;
    }

    @Override
    public Course save(Course entity) {
        for(Course c:courses){
            if(c.equals(entity)){
                return c;
            }
        }
        courses.add(entity);
        try{

            FileWriter fw = new FileWriter("courses.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(entity.getName() + " " + entity.getCredits());
            bw.newLine();
            bw.close();
        }
        catch(IOException e){
        }
        return null;
    }

    @Override
    public Course delete(int id) {
        if(courses.contains(courses.get(id))){
            Course c = courses.get(id);
            courses.remove(courses.get(id));
            return c;
        }
        return null;
    }

    @Override
    public Course update(Course entity) {
        if(courses.contains(entity)){
            return null;
        }
        return entity;
    }

    @Override
    public String toString() {
        return "CourseInMemoryRepo{" +
                "courses=" + courses +
                '}';
    }
}

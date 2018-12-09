import com.ibatis.mysql.entity.Student;
import com.ibatis.mysql.dao.impl.StudentDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.List;


/**
 * Created by Administrator on 2018/12/8 0008.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:applicationContext.xml")
public class TestStudentDAO {

    @Autowired
    private StudentDaoImpl studentDao;

    @Test
    public void test_query_by_id(){
        System.out.println(studentDao.selectStudentById(13));
    }

    @Test
    public void test_add(){
        Student student = new Student();
        student.setName("张三");
        student.setScore(80);
        student.setBirth(Date.valueOf("2018-12-08"));
        System.out.println(studentDao.addStudent(student));
    }


    @Test
    public void test_delete(){
        System.out.println(studentDao.deleteStudentById(2));
    }

    @Test
    public void test_update(){
        Student student = new Student();
        student.setId(13);
        student.setName("张三1");
        student.setScore(80);
        student.setBirth(Date.valueOf("2018-12-08"));
        System.out.println(studentDao.updateStudent(student));
    }

    @Test
    public void test_select_by_name(){
        List<Student> list = studentDao.selectStudentByName("张三");
        for(Student student : list){
            System.out.println(student);
        }
    }

    @Test
    public void test_select_all(){
        List<Student> students = studentDao.selectAllStudent();
        for(Student student: students){
            System.out.println(student);
        }
    }
}

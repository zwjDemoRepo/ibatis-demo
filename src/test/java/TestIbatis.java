import com.ibatis.mysql.entity.Student;
import com.ibatis.mysql.dao.impl.StudentDaoImpl;

import java.sql.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/12/8 0008.
 */
public class TestIbatis {
    public static void main(String[] args) {
        StudentDaoImpl studentDaoImpl= new StudentDaoImpl();

        //测试插入
        Student addStudent=new Student();
        addStudent.setName("李四");
        addStudent.setBirth(Date.valueOf("2011-09-02"));
        addStudent.setScore(88);
        System.out.println(studentDaoImpl.addStudent(addStudent));

        addStudent.setName("李四2");
        addStudent.setBirth(Date.valueOf("1990-09-02"));
        addStudent.setScore(98);
        System.out.println(studentDaoImpl.addStudent(addStudent));
        //根据Id查询
        System.out.println(studentDaoImpl.selectStudentById(2));

        //根据姓名查询
        List<Student> list=studentDaoImpl.selectStudentByName("四");
        for(Student student:list){
            System.out.println(student);
        }

        //查询所有
        list=studentDaoImpl.selectAllStudent();
        for(Student student:list){
            System.out.println(student);
        }

        //更新信息
        Student updateStudent=new Student();
        updateStudent.setId(1);
        updateStudent.setName("李四1+");
        updateStudent.setBirth(Date.valueOf("1990-09-07"));
        updateStudent.setScore(24);
        System.out.println(studentDaoImpl.updateStudent(updateStudent));

        //删除数据
        Boolean b=studentDaoImpl.deleteStudentById(1);
        System.out.println("删除结果:"+b);

    }
}

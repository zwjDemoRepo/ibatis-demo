package com.ibatis.mysql;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2018/12/8 0008.
 */
@Component
public class StudentDaoImpl extends SqlMapClientDaoSupport implements IStudentDao {

    @Resource(name = "sqlMapClient")
    private SqlMapClient sqlMapClient;

    @PostConstruct
    public void initSqlMapClient(){
        super.setSqlMapClient(sqlMapClient);
    }

    public boolean addStudent(Student student) {
        Object object = null;
        boolean flag = false;
        try {
            object = sqlMapClient.insert("addStudent", student);
            System.out.println("添加学生信息的返回值:" + object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (object != null) {
            flag = true;
        }
        return flag;
    }

    public boolean deleteStudentById(int id) {
        boolean flag = false;
        Object object = null;
        try {
            object = sqlMapClient.delete("deleteStudentById", id);
            System.out.println("删除学生信息的返回值:" + object + ",这里返回的是影响的函数");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (object != null) {
            flag = true;
        }
        return flag;
    }

    public boolean updateStudent(Student student) {
        boolean flag = false;
        Object object = false;
        try {
            object = sqlMapClient.update("updateStudent", student);
            System.out.println("更新学生信息的返回值:" + object + ",返回影响的行数");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (object != null) {
            flag = true;
        }
        return flag;
    }

    public List<Student> selectAllStudent() {
        List<Student> students = null;
        try {
            students = sqlMapClient.queryForList("selectAllStudent");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> selectStudentByName(String name) {
        List<Student> students = null;
        try {
            students = sqlMapClient.queryForList("selectStudentByName", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student selectStudentById(int id) {
        Student student = null;
        try {
            student = (Student) sqlMapClient.queryForObject("selectStudentById", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
}

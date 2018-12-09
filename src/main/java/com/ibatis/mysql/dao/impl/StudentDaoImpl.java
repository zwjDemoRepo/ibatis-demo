package com.ibatis.mysql.dao.impl;

import com.ibatis.mysql.dao.AbstractDAO;
import com.ibatis.mysql.dao.IStudentDao;
import com.ibatis.mysql.entity.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/12/8 0008.
 */
@Component
public class StudentDaoImpl extends AbstractDAO<Student> implements IStudentDao {

    @Transactional(rollbackFor = Exception.class)
    public boolean addStudent(Student student) {
        Object object = null;
        boolean flag = false;
        try {
            object = super.insert("addStudent", student);
            System.out.println("添加学生信息的返回值:" + object);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        if (object != null) {
            flag = true;
        }
        return flag;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteStudentById(int id) {
        boolean flag = false;
        Object object = null;
        try {
            object = super.delete("deleteStudentById", id);
            System.out.println("删除学生信息的返回值:" + object + ",这里返回的是影响的行");
        } catch (DataAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (object != null) {
            flag = true;
        }
        return flag;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateStudent(Student student) {
        boolean flag = false;
        Object object = false;
        try {
            object = super.update("updateStudent", student);
            System.out.println("更新学生信息的返回值:" + object + ",返回影响的行数");
        } catch (DataAccessException e) {
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
            students = super.queryForList("selectAllStudent",null);
        } catch (DataAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> selectStudentByName(String name) {
        List<Student> students = null;
        try {
            students = super.queryForList("selectStudentByName", name);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student selectStudentById(int id) {
        Student student = null;
        try {
            student = super.queryById("selectStudentById", id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return student;
    }

    protected String namespace() {
        return "student";
    }
}

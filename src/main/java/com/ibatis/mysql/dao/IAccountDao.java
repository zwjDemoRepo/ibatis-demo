package com.ibatis.mysql.dao;

import com.ibatis.mysql.entity.Account;

import java.util.List;

/**
 * Created by Administrator on 2018/12/8 0008.
 */
public interface IAccountDao {

     Integer addAcc(Account account);

     boolean deleteAccById(int id);

     boolean updateStudent(Account account);

     List<Account> selectAllAcc();


     List<Account> selectAccByName(String name);

     Account selectAccById(int id);
}

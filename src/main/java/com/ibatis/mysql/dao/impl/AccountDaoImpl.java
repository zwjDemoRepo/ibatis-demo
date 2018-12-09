package com.ibatis.mysql.dao.impl;

import com.ibatis.mysql.dao.AbstractDao;
import com.ibatis.mysql.dao.IAccountDao;
import com.ibatis.mysql.entity.Account;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2018/12/9 0009.
 */
@Component
public class AccountDaoImpl extends AbstractDao<Account> implements IAccountDao {
    public Integer addAcc(Account account) {
        return super.insert("addAccount",account);
    }

    public boolean deleteAccById(int id) {
        return false;
    }

    public boolean updateStudent(Account account) {
        return false;
    }

    public List<Account> selectAllAcc() {
        return null;
    }

    public List<Account> selectAccByName(String name) {
        return null;
    }

    public Account selectAccById(int id) {
        return super.queryById("selectAccountById",id);
    }

    protected String namespace() {
        return "account";
    }
}

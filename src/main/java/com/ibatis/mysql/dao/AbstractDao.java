package com.ibatis.mysql.dao;

import com.ibatis.mysql.entity.BaseEntity;
import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/12/9 0009.
 */
public abstract class AbstractDao<T extends BaseEntity> extends SqlMapClientDaoSupport{
    @Resource(name = "sqlMapClient")
    private SqlMapClient sqlMapClient;

    @PostConstruct
    public void initSqlMapClient(){
        super.setSqlMapClient(sqlMapClient);
    }

    protected Integer insert(String sqlId,T t){
        return (Integer)getSqlMapClientTemplate().insert(getFullSql(sqlId),t);
    }

    protected int delete(String sqlId, int id){
        return getSqlMapClientTemplate().delete(getFullSql(sqlId),id);
    }

    protected int update(String sqlId,T t){
        return getSqlMapClientTemplate().update(getFullSql(sqlId),t);
    }

    protected T queryById(String sqlId,int id){
        return (T)getSqlMapClientTemplate().queryForObject(getFullSql(sqlId),id);
    }
    protected List<T> queryForList(String sqlId, String name){
        return (List<T>) getSqlMapClientTemplate().queryForList(getFullSql(sqlId),name);
    }

    private String getFullSql(String sqlId){
        return String.format("%s.%s",namespace(),sqlId);
    }

    protected abstract String namespace();
}

package com.ibatis.mysql.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Administrator on 2018/12/9 0009.
 */
public class BaseEntity{

    private Timestamp createTime;

//    private Date updateTime;
//    private DateTime updateTime;
      private Date updateTime;
    /**
     * @return create_time
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return this.toString();
    }
}

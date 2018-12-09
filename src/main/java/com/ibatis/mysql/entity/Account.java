package com.ibatis.mysql.entity;

public class Account extends BaseEntity{

    private Integer id;

    private String name;

    private String enabled;

    private String pid;

    private String ptype;

    private String source;

    private Integer partionKey;

    private Integer version;

    public Account() {
    }

    public Account(String name, String pid, String ptype, String source, Integer partionKey) {
        this.name = name;
        this.pid = pid;
        this.ptype = ptype;
        this.source = source;
        this.partionKey = partionKey;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }



    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return enabled
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    /**
     * @return pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * @return ptype
     */
    public String getPtype() {
        return ptype;
    }

    /**
     * @param ptype
     */
    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    /**
     * @return source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }

    public Integer getPartionKey() {
        return partionKey;
    }

    public void setPartionKey(Integer partionKey) {
        this.partionKey = partionKey;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
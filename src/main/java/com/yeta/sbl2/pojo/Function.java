package com.yeta.sbl2.pojo;

import javax.persistence.*;

public class Function {
    @Id
    private Integer id;

    private String name;

    @Column(name = "parentId")
    private Integer parentid;

    private String url;

    @Column(name = "serialNum")
    private Integer serialnum;

    private Integer accordion;

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
     * @return parentId
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * @param parentid
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return serialNum
     */
    public Integer getSerialnum() {
        return serialnum;
    }

    /**
     * @param serialnum
     */
    public void setSerialnum(Integer serialnum) {
        this.serialnum = serialnum;
    }

    /**
     * @return accordion
     */
    public Integer getAccordion() {
        return accordion;
    }

    /**
     * @param accordion
     */
    public void setAccordion(Integer accordion) {
        this.accordion = accordion;
    }

    @Override
    public String toString() {
        return "Function{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentid=" + parentid +
                ", url='" + url + '\'' +
                ", serialnum=" + serialnum +
                ", accordion=" + accordion +
                '}';
    }
}
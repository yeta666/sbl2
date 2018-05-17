package com.yeta.sbl2.pojo;

import javax.persistence.*;

@Table(name = "role_function")
public class RoleFunction {
    @Id
    private Integer id;

    @Column(name = "roleId")
    private Integer roleid;

    @Column(name = "functionId")
    private Integer functionid;

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
     * @return roleId
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * @param roleid
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * @return functionId
     */
    public Integer getFunctionid() {
        return functionid;
    }

    /**
     * @param functionid
     */
    public void setFunctionid(Integer functionid) {
        this.functionid = functionid;
    }

    @Override
    public String toString() {
        return "RoleFunction{" +
                "id=" + id +
                ", roleid=" + roleid +
                ", functionid=" + functionid +
                '}';
    }
}
package com.yeta.sbl2.pojo;

import javax.persistence.*;

@Table(name = "user_role")
public class UserRole {
    @Id
    private Integer id;

    @Column(name = "userId")
    private Integer userid;

    @Column(name = "roleId")
    private Integer roleid;

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
     * @return userId
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
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

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userid=" + userid +
                ", roleid=" + roleid +
                '}';
    }
}
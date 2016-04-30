package zsl.college.web.dbproxy.entity;

import java.io.Serializable;

/**
 * Created by zhangshenglan on 16/4/30.
 */
public class User implements Serializable {
    private String id;
    private String userName;
    private String password;
    private String roleName;



    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRoleName() {
        return roleName;
    }

    public User(String id, String userName, String password, String roleName) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roleName = roleName;
    }

    public User() {
    }
}

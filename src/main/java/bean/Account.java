package bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by wangxue on 2018/6/9.
 */
@Entity
public class Account {
    private int uid;
    private String pwd;
    private String uname;
    private String tel;
    private Date logDate;
    private int type;

    @Id
    @Column(name = "uid", nullable = false)
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "pwd", nullable = false, length = -1)
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Basic
    @Column(name = "uname", nullable = true, length = -1)
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Basic
    @Column(name = "tel", nullable = true, length = 11)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "log_date", nullable = false)
    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (uid != account.uid) return false;
        if (type != account.type) return false;
        if (pwd != null ? !pwd.equals(account.pwd) : account.pwd != null) return false;
        if (uname != null ? !uname.equals(account.uname) : account.uname != null) return false;
        if (tel != null ? !tel.equals(account.tel) : account.tel != null) return false;
        if (logDate != null ? !logDate.equals(account.logDate) : account.logDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + (uname != null ? uname.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (logDate != null ? logDate.hashCode() : 0);
        result = 31 * result + type;
        return result;
    }
}

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
public class Hotel {
    private int hid;
    private String pwd;
    private Date createDate;
    private String hname;
    private String city;

    @Id
    @Column(name = "hid", nullable = false)
    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
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
    @Column(name = "create_date", nullable = false)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "hname", nullable = false, length = -1)
    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    @Basic
    @Column(name = "city", nullable = true, length = -1)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (hid != hotel.hid) return false;
        if (pwd != null ? !pwd.equals(hotel.pwd) : hotel.pwd != null) return false;
        if (createDate != null ? !createDate.equals(hotel.createDate) : hotel.createDate != null) return false;
        if (hname != null ? !hname.equals(hotel.hname) : hotel.hname != null) return false;
        if (city != null ? !city.equals(hotel.city) : hotel.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hid;
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (hname != null ? hname.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}

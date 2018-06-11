package bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by wangxue on 2018/6/10.
 */
@Entity
public class Orders {
    private int oid;
    private int uid;
    private int hid;
    private int rid;
    private int rnum;
    private Integer price;
    private int money;
    private Date ctime;
    private Date stime;
    private Date etime;
    private Integer state;
    private Double pingjia;

    @Id
    @Column(name = "oid", nullable = false)
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    @Basic
    @Column(name = "uid", nullable = false)
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "hid", nullable = false)
    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    @Basic
    @Column(name = "rid", nullable = false)
    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    @Basic
    @Column(name = "rnum", nullable = false)
    public int getRnum() {
        return rnum;
    }

    public void setRnum(int rnum) {
        this.rnum = rnum;
    }

    @Basic
    @Column(name = "price", nullable = true)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "money", nullable = false)
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Basic
    @Column(name = "ctime", nullable = false)
    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Basic
    @Column(name = "stime", nullable = false)
    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    @Basic
    @Column(name = "etime", nullable = true)
    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    @Basic
    @Column(name = "state", nullable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "pingjia", nullable = true, precision = 0)
    public Double getPingjia() {
        return pingjia;
    }

    public void setPingjia(Double pingjia) {
        this.pingjia = pingjia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;

        if (oid != orders.oid) return false;
        if (uid != orders.uid) return false;
        if (hid != orders.hid) return false;
        if (rid != orders.rid) return false;
        if (rnum != orders.rnum) return false;
        if (money != orders.money) return false;
        if (price != null ? !price.equals(orders.price) : orders.price != null) return false;
        if (ctime != null ? !ctime.equals(orders.ctime) : orders.ctime != null) return false;
        if (stime != null ? !stime.equals(orders.stime) : orders.stime != null) return false;
        if (etime != null ? !etime.equals(orders.etime) : orders.etime != null) return false;
        if (state != null ? !state.equals(orders.state) : orders.state != null) return false;
        if (pingjia != null ? !pingjia.equals(orders.pingjia) : orders.pingjia != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = oid;
        result = 31 * result + uid;
        result = 31 * result + hid;
        result = 31 * result + rid;
        result = 31 * result + rnum;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + money;
        result = 31 * result + (ctime != null ? ctime.hashCode() : 0);
        result = 31 * result + (stime != null ? stime.hashCode() : 0);
        result = 31 * result + (etime != null ? etime.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (pingjia != null ? pingjia.hashCode() : 0);
        return result;
    }
}

package bean;

import javax.persistence.*;

/**
 * Created by wangxue on 2018/6/10.
 */
@Entity
@IdClass(RoomPK.class)
public class Room {
    private int hid;
    private int rid;
    private int num;
    private int price;
    private String rname;

    @Id
    @Column(name = "hid", nullable = false)
    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    @Id
    @Column(name = "rid", nullable = false)
    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    @Basic
    @Column(name = "num", nullable = false)
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "rname", nullable = true, length = -1)
    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (hid != room.hid) return false;
        if (rid != room.rid) return false;
        if (num != room.num) return false;
        if (price != room.price) return false;
        if (rname != null ? !rname.equals(room.rname) : room.rname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hid;
        result = 31 * result + rid;
        result = 31 * result + num;
        result = 31 * result + price;
        result = 31 * result + (rname != null ? rname.hashCode() : 0);
        return result;
    }
}

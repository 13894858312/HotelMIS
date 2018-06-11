package bean;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by wangxue on 2018/6/10.
 */
public class RoomPK implements Serializable {
    private int hid;
    private int rid;

    @Column(name = "hid", nullable = false)
    @Id
    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    @Column(name = "rid", nullable = false)
    @Id
    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomPK roomPK = (RoomPK) o;

        if (hid != roomPK.hid) return false;
        if (rid != roomPK.rid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hid;
        result = 31 * result + rid;
        return result;
    }
}

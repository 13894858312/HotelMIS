package data;

import DAO.RoomDAO;
import bean.Room;
import bean.RoomPK;
import org.hibernate.Session;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wangxue on 2018/6/6.
 */
public class RoomData extends Data implements RoomDAO {
    @Override
    public boolean addRoom(Room room) {
        Session session = sessionFactory.openSession();
        try {
            session.save(room);
            return true;
        }catch (Exception e){
            System.out.println("房间 "+room.getRname()+" 插入错误");
        }
        return false;
    }

    @Override
    public boolean modifyRoom(Room room) {
        Session session = sessionFactory.openSession();
        try {
            session.update(room);
            return true;
        }catch (Exception e){
            System.out.println("房间 "+room.getRname()+" 插入错误");
        }
        return false;
    }

    @Override
    public Room getRoomInfo(int hid, int rid) {
        RoomPK pk = new RoomPK();
        pk.setHid(hid);
        pk.setRid(rid);
        return (Room)sessionFactory.openSession().get(Room.class, pk);
    }

    @Override
    public HashMap<String, Long> getHotelRoomInfo(int hid) {
        Session session = sessionFactory.openSession();
        String sql = "select r.rname, r.num from Room r where r.hid=?";
        List<Object[]> queryResult = session.createQuery(sql).setInteger(0,hid).list();
        HashMap<String, Long> result = new HashMap<>();
        for(Object[] o : queryResult){
            result.put((String)o[0],(Long)o[1]);
        }
        return result;
    }

    @Override
    public HashMap<String, Long> getRoomTurnover(int hid) {
        Session session = sessionFactory.openSession();
        String sql = "select o.hid, o.rid, r.rname, sum(o.money) from Orders o join Room r on o.hid=r.hid and r.rid=o.rid where o.state<>3 and o.hid=? group by o.hid,o.rid ";
        List<Object[]> queryResult = session.createQuery(sql).setInteger(0,hid).list();
        HashMap<String, Long> result = new HashMap<>();
        for(Object[] o : queryResult){
            result.put((String) o[2],(Long)o[3]);
        }
        return result;
    }

    @Override
    public HashMap<String, Long> getRoomOrder(int hid) {
        Session session = sessionFactory.openSession();
        String sql = "select o.hid, o.rid, r.rname, count(o.oid) from Orders o join Room r on o.hid=r.hid and r.rid=o.rid where o.state<>3 and o.hid=? group by o.hid,o.rid ";
        List<Object[]> queryResult = session.createQuery(sql).setInteger(0,hid).list();
        HashMap<String, Long> result = new HashMap<>();
        for(Object[] o : queryResult){
            result.put((String) o[2],(Long)o[3]);
        }
        return result;
    }

    @Override
    public HashMap<String, Long> getReservedRoomInfo(int year, int month, int day, int hid) {
        Session session = sessionFactory.openSession();
        Date date = new Date(year,month-1,day);
        String sql = "select o.hid, o.rid, r.rname, count(o.rnum) from Orders o join Room r on o.hid=r.hid and o.rid=r.rid where o.state<>3 and o.stime<=? and o.etime>=? and o.hid=? group by o.hid,r.rid";
        List<Object[]> queryResult = session.createQuery(sql).setDate(0,date).setDate(1,date).setInteger(2,hid).list();
        HashMap<String, Long> result = new HashMap<>();
        for (Object[] o : queryResult){
            result.put((String)o[2],(Long)o[3]);
        }
        return result;
    }
}

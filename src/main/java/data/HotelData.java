package data;

import DAO.HotelDAO;
import bean.Hotel;
import logic.TYPE;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wangxue on 2018/6/6.
 */
public class HotelData extends Data implements HotelDAO {

    @Override
    public boolean addHotel(Hotel hotel) {
        Session session = sessionFactory.openSession();
        try {
            session.save(hotel);
            return true;
        }catch (Exception e){
            System.out.println("账号 "+hotel.getHid()+" 插入错误");
        }
        return false;
    }

    @Override
    public boolean modifyHotel(Hotel hotel) {
        Session session = sessionFactory.openSession();
        try {
            session.update(session);
            return true;
        }catch (Exception e){
            System.out.println("账号 "+hotel.getHid()+" 插入错误");
        }
        return false;
    }

    @Override
    public boolean checkHotel(Hotel hotel) {

        Session session = sessionFactory.openSession();
        int uid = hotel.getHid();
        Hotel a_in_db = (Hotel) session.get(Hotel.class, uid);

        if(a_in_db == null) {
            return false;
        }

        return a_in_db.getPwd().equals(hotel.getPwd());
    }

    @Override
    public Hotel getHotelInfo(int hid) {
        return (Hotel)sessionFactory.openSession().get(Hotel.class, hid);
    }

    @Override
    public long getHotelTotalTurnover(int hid) {
        return getTotalTurnoverHelper(TYPE.HOTEL, hid);
    }

    @Override
    public HashMap<String, Long> getHotelPeriodTurnOver(int hid) {
        return periodTurnoverQueryHelper(TYPE.HOTEL, hid);
    }

    @Override
    public HashMap<String, Long> getHotelPeriodTurnOver(int year, int hid) {
        return periodTurnoverQueryHelper(year, TYPE.HOTEL, hid);
    }

    @Override
    public HashMap<String, Long> getHotelPeriodTurnOver(int year, int month, int hid) {
        return periodTurnoverQueryHelper(year, month, TYPE.HOTEL, hid);
    }

    @Override
    public long getHotelOrder(int hid) {
        return getTotalOrderHelper(TYPE.HOTEL, hid);
    }

    @Override
    public HashMap<String, Long> getHotelPeriodOrder(int hid) {
        return periodOrderQueryHelper(TYPE.HOTEL,hid);
    }

    @Override
    public HashMap<String, Long> getHotelPeriodOrder(int year, int hid) {
        return periodOrderQueryHelper(year, TYPE.HOTEL, hid);
    }

    @Override
    public HashMap<String, Long> getHotelPeriodOrder(int year, int month, int hid) {
        return periodOrderQueryHelper(year,month,TYPE.HOTEL,hid);
    }

    @Override
    public long getHotelTurnover(int year, int month, int day, int hid) {
        return getTurnoverQueryHelper(year,month,day,TYPE.HOTEL,hid);
    }

    @Override
    public long getHotelOrder(int year, int month, int day, int hid) {
        return getOrderQueryHelper(year, month, day, TYPE.HOTEL, hid);
    }

    @Override
    public long getHotelTurnover(int year, int month, int hid) {
        return getTurnoverQueryHelper(year, month, TYPE.HOTEL, hid);
    }

    @Override
    public long getHotelOrder(int year, int month, int hid) {
        return getOrderQueryHelper(year, month, TYPE.HOTEL, hid);
    }

    @Override
    public long[] getTypeOrder(int hid) {
        Session session = sessionFactory.openSession();
        long not = (long)session.createQuery("select count(o.oid) from Orders o where o.hid=? and o.state=0").setInteger(0,hid).list().get(0);
        long yep = (long)session.createQuery("select count(o.oid) from Orders o where o.hid=? and o.state=1").setInteger(0,hid).list().get(0);
        long cancel = (long)session.createQuery("select count(o.oid) from Orders o where o.hid=? and o.state=2").setInteger(0,hid).list().get(0);
        long pjed = (long)session.createQuery("select count(o.oid) from Orders o where o.uid=? and o.state=3").setInteger(0,hid).list().get(0);
        return new long[]{not,yep,cancel,pjed};
    }

    @Override
    public HashMap<String, Double> getComment(int hid) {
        Session session = sessionFactory.openSession();
        HashMap<String, Double> result = new HashMap<>();
        String sql = "select year(o.etime) as year, month(o.etime) as month, avg(o.pingjia) from Orders o where o.hid=? and o.state=3 group by year, month";
        List<Object[]> queryResult = session.createQuery(sql).setInteger(0, hid).list();
        for(Object[] o:queryResult){
            String m = o[0]+"-"+o[1];
            result.put(m, (Double)o[2]);
        }
        return result;
    }


}

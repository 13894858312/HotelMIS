package data;

import DAO.OrderDAO;
import bean.Orders;
import logic.STATE;
import logic.TYPE;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangxue on 2018/6/6.
 */
public class OrderData extends Data implements OrderDAO {
    @Override
    public boolean addOrder(Orders orders) {
        Session session = sessionFactory.openSession();
        try {
            session.save(orders);
            return true;
        }catch (Exception e){
            System.out.println("订单 "+orders.getOid()+" 插入错误");
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean modifyOrder(Orders orders) {
        Session session = sessionFactory.openSession();
        try {
            session.update(orders);
            return true;
        }catch (Exception e){
            System.out.println("订单 "+orders.getOid()+" 修改错误");
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public Orders getOrderInfo(int oid) {
        return (Orders)sessionFactory.openSession().get(Orders.class, oid);
    }

    @Override
    public Iterator<Orders> getHotelOrderList(int hid) {
        return getOrderList(TYPE.HOTEL, hid);
    }

    @Override
    public Iterator<Orders> getHotelOrderList(STATE state, int hid) {
        return getOrderList(TYPE.HOTEL, state, hid);
    }

    @Override
    public Iterator<Orders> getUserOrderList(int uid) {
        return getOrderList(TYPE.USER, uid);
    }

    @Override
    public Iterator<Orders> getUserOrderList(STATE state, int uid) {
        return getOrderList(TYPE.USER, state, uid);
    }

    @Override
    public long getTotalTurnover() {
        return getTotalTurnoverHelper(TYPE.WEB, 0);
    }

    @Override
    public HashMap<String, Long> getPeriodTurnOver() {
        return periodTurnoverQueryHelper(TYPE.WEB, 0);
    }

    @Override
    public HashMap<String, Long> getPeriodTurnOver(int year) {
        return periodTurnoverQueryHelper(year, TYPE.WEB, 0);
    }

    @Override
    public HashMap<String, Long> getHotelPeriodTurnOver(int year, int month) {
        return periodTurnoverQueryHelper(year, month, TYPE.WEB, 0);
    }

    @Override
    public long getTotalOrder() {
        return getTotalOrderHelper(TYPE.WEB, 0);
    }

    @Override
    public HashMap<String, Long> getPeriodOrder() {
        return periodOrderQueryHelper(TYPE.WEB, 0);
    }

    @Override
    public HashMap<String, Long> getPeriodOrder(int year) {
        return periodOrderQueryHelper(year, TYPE.WEB, 0);
    }

    @Override
    public HashMap<String, Long> getPeriodOrder(int year, int month) {
        return periodOrderQueryHelper(year ,month, TYPE.WEB, 0);
    }

    @Override
    public long getTurnover(int year, int month, int day) {
        return getTurnoverQueryHelper(year, month, day, TYPE.WEB, 0);
    }

    @Override
    public long getOrder(int year, int month, int day) {
        return getOrderQueryHelper(year, month, day, TYPE.WEB, 0);
    }

    @Override
    public long getTurnover(int year, int month) {
        return getTurnoverQueryHelper(year, month, TYPE.WEB, 0);
    }

    @Override
    public long getOrder(int year, int month) {
        return getOrderQueryHelper(year, month, TYPE.WEB, 0);
    }

    @Override
    public HashMap<String, Long> getAreaTurnover() {
        Session session = sessionFactory.openSession();
        HashMap<String, Long> result = new HashMap<>();
        String sql = "select h.city, sum(o.money) from Hotel h join Orders o on h.hid=o.hid group by h.city";
        List<Object[]> list = session.createQuery(sql).list();
        for(Object[] objects :list){
            result.put((String) objects[0], (Long)objects[1]);
        }
        return result;
    }

    @Override
    public HashMap<String, Long> getAreaOrder() {
        Session session = sessionFactory.openSession();
        HashMap<String, Long> result = new HashMap<>();
        String sql = "select h.city, count (o.oid) from Hotel h join Orders o on h.hid=o.hid group by h.city";
        List<Object[]> list = session.createQuery(sql).list();
        for(Object[] objects :list){
            result.put((String) objects[0], (Long)objects[1]);
        }
        return result;
    }

    @Override
    public HashMap<String, Long> getAreaTurnoverRanking(String area, int n) {
        Session session = sessionFactory.openSession();
        HashMap<String, Long> result = new HashMap<>();
        String sql = "select h.hname, sum(o.money) from Hotel h join Orders o on h.hid=o.hid where h.city=? group by h.hname ";
        List<Object[]> list = session.createQuery(sql).setString(0, area).list();
        for(Object[] objects :list){
            result.put((String) objects[0], (Long)objects[1]);
        }
        return result;
    }

    @Override
    public HashMap<String, Long> getAreaOrderRanking(String area, int n) {
        Session session = sessionFactory.openSession();
        HashMap<String, Long> result = new HashMap<>();
        String sql = "select h.hname, count(o.oid) from Hotel h join Orders o on h.hid=o.hid where h.city=? group by h.hname ";
        List<Object[]> list = session.createQuery(sql).setString(0, area).list();
        for(Object[] objects :list){
            result.put((String) objects[0], (Long)objects[1]);
        }
        return result;
    }
}

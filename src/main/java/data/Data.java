package data;

import bean.Orders;
import logic.STATE;
import logic.TYPE;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangxue on 2018/6/9.
 */
public abstract class Data {

    Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
    SessionFactory sessionFactory = configuration.buildSessionFactory();

    private String getTypeString(TYPE type){
        switch (type){
            case USER:
                return " and uid = ? ";
            case HOTEL:
                return " and hid = ? ";
            case WEB:
                return " ";
            default:
                return null;
        }
    }

    /**
     * 获取的id对应的一段时期内的订单数helper方法
     * @param year
     * @param month
     * @param type
     * @param id
     * @return
     */
    public HashMap<String, Long> periodOrderQueryHelper(int year, int month, TYPE type, int id){
        Session session = sessionFactory.openSession();
        String sql = "select year(o.ctime) as year, month(o.ctime) as month, day(o.ctime) as day, count(o.oid) from Orders o where o.state<>3 and year(o.ctime)=? and month(o.ctime)=? ";
        HashMap<String,Long> result = new HashMap<>();
        List<Object[]> queryResult;
        String typestr = getTypeString(type);
        sql = sql+typestr+"group by year, month, day";
        if(type == TYPE.WEB){
            queryResult = session.createQuery(sql).setInteger(0,year).setInteger(1,month).list();
        }else {
            queryResult = session.createQuery(sql).setInteger(0,year).setInteger(1,month).setInteger(2,id).list();
        }
        for(Object[] object : queryResult){
            String date = object[0]+"-"+object[1]+"-"+object[2];
            result.put(date, (Long)object[3]);
        }
        return result;
    }

    /**
     * 获取的id对应的一段时期内的订单数helper方法
     * @param year
     * @param type
     * @param id
     * @return
     */
    public HashMap<String, Long> periodOrderQueryHelper(int year, TYPE type, int id){
        Session session = sessionFactory.openSession();
        String sql = "select year(o.ctime) as year, month(o.ctime) as month, count(o.oid) from Orders o where o.state<>3 and year(o.ctime)=? ";
        HashMap<String,Long> result = new HashMap<>();
        List<Object[]> queryResult;
        String typeStr = getTypeString(type);
        sql = sql+typeStr+"group by year, month";
        if(type == TYPE.WEB){
            queryResult = session.createQuery(sql).setInteger(0,year).list();
        }else {
            queryResult = session.createQuery(sql).setInteger(0,year).setInteger(1,id).list();
        }
        for(Object[] object : queryResult){
            String date = object[0]+"-"+object[1];
            result.put(date, (Long)object[2]);
        }
        return result;
    }

    /**
     * 获取的id对应的一段时期内订单数的helper方法
     * @param type
     * @param id
     * @return
     */
    public HashMap<String, Long> periodOrderQueryHelper(TYPE type, int id){
        Session session = sessionFactory.openSession();
        String sql = "select year(o.ctime) as year, count(o.ctime) from Orders o where  o.state<>3 ";
        HashMap<String,Long> result = new HashMap<>();
        List<Object[]> queryResult;
        String typeStr = getTypeString(type);
        sql = sql+typeStr+"group by year";
        if(type == TYPE.WEB){
            queryResult = session.createQuery(sql).list();
        }else {
            queryResult = session.createQuery(sql).setInteger(0,id).list();
        }
        for(Object[] object : queryResult){
            String date = (String)object[0];
            result.put(date, (Long)object[1]);
        }
        return result;
    }

    /**
     * 获取的id对应的一段时期内的销售额helper方法
     * @param year
     * @param month
     * @param type
     * @param id
     * @return
     */
    public HashMap<String, Long> periodTurnoverQueryHelper(int year, int month, TYPE type, int id){
        Session session = sessionFactory.openSession();
        String sql = "select year(o.ctime) as year, month(o.ctime) as month, day(o.ctime) as day, sum(o.money) from Orders o where o.state<>3 and year(o.ctime)=? and month(o.ctime)=? ";
        HashMap<String,Long> result = new HashMap<>();
        List<Object[]> queryResult;
        String typestr = getTypeString(type);
        sql = sql+typestr+"group by year, month, day";
        if(type == TYPE.WEB){
            queryResult = session.createQuery(sql).setInteger(0,year).setInteger(1,month).list();
        }else {
            queryResult = session.createQuery(sql).setInteger(0,year).setInteger(1,month).setInteger(2,id).list();
        }
        for(Object[] object : queryResult){
            String date = object[0]+"-"+object[1]+"-"+object[2];
            result.put(date, (Long)object[3]);
        }
        return result;
    }

    /**
     * 获取的id对应的一段时期内的销售额helper方法
     * @param year
     * @param type
     * @param id
     * @return
     */
    public HashMap<String, Long> periodTurnoverQueryHelper(int year, TYPE type, int id){
        Session session = sessionFactory.openSession();
        String sql = "select year(o.ctime) as year, month(o.ctime) as month , sum(o.money) from Orders o where o.state<>3 and year(o.ctime)=? ";
        HashMap<String,Long> result = new HashMap<>();
        List<Object[]> queryResult;
        String typeStr = getTypeString(type);
        sql = sql+typeStr+"group by year, month";
        if(type == TYPE.WEB){
            queryResult = session.createQuery(sql).setInteger(0,year).list();
        }else {
            queryResult = session.createQuery(sql).setInteger(0,year).setInteger(1,id).list();
        }
        for(Object[] object : queryResult){
            String date = object[0]+"-"+object[1];
            result.put(date, (Long)object[2]);
        }
        return result;
    }

    /**
     * 获取的id对应的一段时期内的销售额helper方法
     * @param type
     * @param id
     * @return
     */
    public HashMap<String, Long> periodTurnoverQueryHelper(TYPE type, int id){
        Session session = sessionFactory.openSession();
        String sql = "select year(o.ctime) as year, sum(o.money) from Orders o where o.state<>3 ";
        HashMap<String,Long> result = new HashMap<>();
        List<Object[]> queryResult;
        String typeStr = getTypeString(type);
        sql = sql+typeStr+"group by year";
        if(type == TYPE.WEB){
            queryResult = session.createQuery(sql).list();
        }else {
            queryResult = session.createQuery(sql).setInteger(1,id).list();
        }
        for(Object[] object : queryResult){
            String date = (String)object[0];
            result.put(date, (Long)object[1]);
        }
        return result;
    }

    /**
     * 获取指定时间订单数
     * @param year
     * @param month
     * @param day
     * @param type
     * @param id
     * @return
     */
    public long getOrderQueryHelper(int year, int month, int day, TYPE type, int id){
        Session session = sessionFactory.openSession();
        String sql = "select count(o.oid) from Orders o where o.state<>3 and year(o.ctime)=? and month(o.ctime)=? and day(o.ctime)=? ";
        List<Long> queryResult;
        String typeStr = getTypeString(type);
        sql = sql+typeStr;
        if(type == TYPE.WEB){
            queryResult = session.createQuery(sql).setInteger(0,year).list();
        }else {
            queryResult = session.createQuery(sql).setInteger(0,year).setInteger(1,month).setInteger(2,day).setInteger(3,id).list();
        }
        return queryResult.get(0);
    }

    /**
     * 获取指定时间订单数
     * @param year
     * @param month
     * @param type
     * @param id
     * @return
     */
    public long getOrderQueryHelper(int year, int month, TYPE type, int id){
        Session session = sessionFactory.openSession();
        String sql = "select count(o.oid) from Orders o where o.state<>3 and year(o.ctime)=? and month(o.ctime)=? ";
        List<Long> queryResult;
        String typeStr = getTypeString(type);
        sql = sql+typeStr;
        if(type == TYPE.WEB){
            queryResult = session.createQuery(sql).setInteger(0,year).list();
        }else {
            queryResult = session.createQuery(sql).setInteger(0,year).setInteger(1,month).setInteger(2,id).list();
        }
        return queryResult.get(0);
    }

    /**
     * 获取指定时间订单数
     * @param year
     * @param type
     * @param id
     * @return
     */
    public long getOrderQueryHelper(int year, TYPE type, int id){
        Session session = sessionFactory.openSession();
        String sql = "select count(o.oid) from Orders o where o.state<>3 and year(o.ctime)=? ";
        List<Long> queryResult;
        String typeStr = getTypeString(type);
        sql = sql+typeStr;
        if(type == TYPE.WEB){
            queryResult = session.createQuery(sql).setInteger(0,year).list();
        }else {
            queryResult = session.createQuery(sql).setInteger(0,year).setInteger(1,id).list();
        }
        return queryResult.get(0);
    }

    /**
     * 获取指定时间销售额
     * @param year
     * @param month
     * @param day
     * @param type
     * @param id
     * @return
     */
    public long getTurnoverQueryHelper(int year, int month, int day, TYPE type, int id){
        Session session = sessionFactory.openSession();
        String sql = "select sum(o.money) from Orders o where o.state<>3 and year(o.ctime)=? and month(o.ctime)=? and day(o.ctime)=? ";
        List<Long> queryResult;
        String typeStr = getTypeString(type);
        sql = sql+typeStr;
        if(type == TYPE.WEB){
            queryResult = session.createQuery(sql).setInteger(0,year).list();
        }else {
            queryResult = session.createQuery(sql).setInteger(0,year).setInteger(1,month).setInteger(2,day).setInteger(3,id).list();
        }
        return queryResult.get(0);
    }

    /**
     * 获取指定时间销售额
     * @param year
     * @param month
     * @param type
     * @param id
     * @return
     */
    public long getTurnoverQueryHelper(int year, int month, TYPE type, int id){
        Session session = sessionFactory.openSession();
        String sql = "select sum(o.money) from Orders o where o.state<>3 and year(o.ctime)=? and month(o.ctime)=? ";
        List<Long> queryResult;
        String typeStr = getTypeString(type);
        sql = sql+typeStr;
        if(type == TYPE.WEB){
            queryResult = session.createQuery(sql).setInteger(0,year).list();
        }else {
            queryResult = session.createQuery(sql).setInteger(0,year).setInteger(1,month).setInteger(2,id).list();
        }
        return queryResult.get(0);
    }

    /**
     * 获取指定时间销售额
     * @param year
     * @param type
     * @param id
     * @return
     */
    public long getTurnoverQueryHelper(int year, TYPE type, int id){
        Session session = sessionFactory.openSession();
        String sql = "select sum(o.money) from Orders o where o.state<>3 and year(o.ctime)=? ";
        List<Long> queryResult;
        String typeStr = getTypeString(type);
        sql = sql+typeStr;
        if(type == TYPE.WEB){
            queryResult = session.createQuery(sql).setInteger(0,year).list();
        }else {
            queryResult = session.createQuery(sql).setInteger(0,year).setInteger(1,id).list();
        }
        return queryResult.get(0);
    }

    /**
     * 获取id全部订单数
     * @param type
     * @param id
     * @return
     */
    public long getTotalOrderHelper(TYPE type, int id){
        Session session = sessionFactory.openSession();
        String sql = "select count(o.oid) from Orders o where o.state<>3";
        List<Long> queryResult;
        String typeStr = getTypeString(type);
        sql = sql+typeStr;
        if(type == TYPE.WEB){
            queryResult = session.createQuery(sql).list();
        }else {
            queryResult = session.createQuery(sql).setInteger(1,id).list();
        }
        return queryResult.get(0);
    }
    /**
     * 获取id全部销售额
     * @param type
     * @param id
     * @return
     */
    public long getTotalTurnoverHelper(TYPE type, int id){
        Session session = sessionFactory.openSession();
        String sql = "select sum(o.money) from Orders o where o.state<>3 ";
        List<Long> queryResult;
        String typeStr = getTypeString(type);
        sql = sql+typeStr;
        if(type == TYPE.WEB){
            queryResult = session.createQuery(sql).list();
        }else {
            queryResult = session.createQuery(sql).setInteger(1,id).list();
        }
        return queryResult.get(0);
    }

    /**
     * 获取id全部订单
     * @param type
     * @param id
     * @return
     */
    public Iterator<Orders> getOrderList(TYPE type, int id){
        Session session = sessionFactory.openSession();
        String sql = "from Orders o where o.oid>0 ";
        String type_str = getTypeString(type);
        sql = sql+type_str;
        Iterator<Orders> ordersIterator = session.createQuery(sql).setInteger(0, id).iterate();
        return ordersIterator;
    }

    /**
     * 获取id指定类型订单
     * @param type
     * @param state
     * @param id
     * @return
     */
    public Iterator<Orders> getOrderList(TYPE type, STATE state, int id){
        Session session = sessionFactory.openSession();
        String sql = "from Orders o where o.state=? ";
        String type_str = getTypeString(type);
        sql = sql+type_str;
        Iterator<Orders> ordersIterator = session.createQuery(sql).setInteger(0, state.ordinal()).setInteger(1,id).iterate();
        return ordersIterator;
    }
}

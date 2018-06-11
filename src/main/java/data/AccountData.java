package data;

import DAO.AccountDAO;
import bean.Account;
import logic.TYPE;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wangxue on 2018/6/6.
 */
public class AccountData extends Data implements AccountDAO {

    @Override
    public boolean addAccount(Account account) {
        Session session = sessionFactory.openSession();

        try {
            session.save(account);
        }catch (Exception e){
            System.out.println("账号 "+account.getUid()+" 插入错误");
            return false;
        }finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean modifyAccount(Account account) {
        Session session = sessionFactory.openSession();

        try {
            session.update(account);
        }catch (Exception e){
            System.out.println("账号 "+account.getUid()+" 修改错误");
            return false;
        }finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean checkAccount(Account account) {
        Session session = sessionFactory.openSession();
        int uid = account.getUid();
        Account a_in_db = (Account)session.get(Account.class, uid);

        if(a_in_db == null) {
            return false;
        }

        return a_in_db.getPwd().equals(account.getPwd()) && (a_in_db.getType()==account.getType());
    }

    @Override
    public Account getInfo(int uid) {
        Session session = sessionFactory.openSession();
        return (Account)session.get(Account.class, uid);
    }

    @Override
    public HashMap<String, Long> getMonthlyAnalysis() {
        Session session = sessionFactory.openSession();
        String hql = "select year(a.logDate) as year, month(a.logDate) as month, count(uid) as num from Account a group by year(a.logDate), month(a.logDate)";
        List<Object[]> query = session.createQuery(hql).list();
        HashMap<String, Long> result = new HashMap<>();
        for(Object[] r : query){
            String s = r[0]+"-"+r[1];
            result.put(s, (long)r[2]);
        }
        return result;
    }

    @Override
    public long getUserNum() {
        return (long)sessionFactory.openSession().createQuery("select count(*) from Account a where a.type=0").list().get(0);
    }

    @Override
    public long getActiveUserNum(int year, int month) {
        Session session = sessionFactory.openSession();
        String hql = "select count(distinct o.uid) from Orders o where o.state<>3 and year(o.ctime)=? and month(o.ctime)=?";
        return (long)session.createQuery(hql).setInteger(0,year).setInteger(1,month).list().get(0);
    }

    @Override
    public HashMap<String, Long> getUserAreaOrder(int uid) {
        Session session = sessionFactory.openSession();
        String sql = "select h.city, count(o.oid) from Hotel h right join Orders o ON h.hid=o.hid where o.state<>3 group by h.city";
        List<Object[]> list = session.createQuery(sql).list();
        HashMap<String, Long> result = new HashMap<>();
        for(Object[] l :list){
            result.put((String)l[0],(long)l[2]);
        }
        return result;
    }

    @Override
    public HashMap<String, Long> getUserAreaTurnover(int uid) {
        Session session = sessionFactory.openSession();
        String sql = "select h.city, sum(o.money) from Hotel h right join Orders o ON h.hid=o.hid where o.state<>3 group by h.city";
        List<Object[]> list = session.createQuery(sql).list();
        HashMap<String, Long> result = new HashMap<>();
        for(Object[] l :list){
            result.put((String)l[0],(long)l[2]);
        }
        return result;
    }

    @Override
    public HashMap<String, Long> getUserPeriodTurnover(int uid) {
        return periodTurnoverQueryHelper(TYPE.USER, uid);
    }

    @Override
    public HashMap<String, Long> getUserPeriodTurnover(int year, int uid) {
        return periodTurnoverQueryHelper(year,TYPE.USER,uid);
    }

    @Override
    public HashMap<String, Long> getUserPeriodOrder(int uid) {
        return periodOrderQueryHelper(TYPE.USER, uid);
    }

    @Override
    public HashMap<String, Long> getUserPeriodOrder(int year, int uid) {
        return periodOrderQueryHelper(year,TYPE.USER,uid);
    }

    @Override
    public long getUserTurnover(int year, int month, int uid) {
        return getTurnoverQueryHelper(year, month, TYPE.USER, uid);
    }

    @Override
    public long getUserTotalOrder(int uid) {
        return getTotalOrderHelper(TYPE.USER, uid);
    }

    @Override
    public long getUserTotalTurnover(int uid) {
        return getTotalTurnoverHelper(TYPE.USER, uid);
    }

    @Override
    public long[] getUserTypeOrder(int uid) {
        Session session = sessionFactory.openSession();
        long not = (long)session.createQuery("select count(o.oid) from Orders o where o.uid=? and o.state=0").setInteger(0,uid).list().get(0);
        long yep = (long)session.createQuery("select count(o.oid) from Orders o where o.uid=? and o.state=1").setInteger(0,uid).list().get(0);
        long cancel = (long)session.createQuery("select count(o.oid) from Orders o where o.uid=? and o.state=2").setInteger(0,uid).list().get(0);
        long pjed = (long)session.createQuery("select count(o.oid) from Orders o where o.uid=? and o.state=3").setInteger(0,uid).list().get(0);
        return new long[]{not,yep,cancel,pjed};
    }

}

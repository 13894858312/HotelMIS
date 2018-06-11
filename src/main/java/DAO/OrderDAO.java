package DAO;

import bean.Orders;
import logic.STATE;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by wangxue on 2018/6/9.
 */
public interface OrderDAO {

    /**
     * 添加订单
     * @return
     */
    public boolean addOrder(Orders orders);

    /**
     * 修改订单
     * @return
     */
    public boolean modifyOrder(Orders orders);

    /**
     * 获取订单信息
     * @param oid
     * @return
     */
    public Orders getOrderInfo(int oid);

    /**
     * 获取酒店全部订单信息
     * @param hid
     * @return
     */
    public Iterator<Orders> getHotelOrderList(int hid);

    /**
     * 获取酒店指定类型订单信息
     * @param state
     * @param hid
     * @return
     */
    public Iterator<Orders> getHotelOrderList(STATE state, int hid);

    /**
     * 获取用户全部订单信息
     * @param uid
     * @return
     */
    public Iterator<Orders> getUserOrderList(int uid);

    /**
     * 获取用户指定类型订单信息
     * @param state
     * @param uid
     * @return
     */
    public Iterator<Orders> getUserOrderList(STATE state, int uid);

    /**
     * 获取平台总销售额
     * @return
     */
    public long getTotalTurnover();

    /**
     * 获得各年总营业额
     * @return 年，营业额
     */
    public HashMap<String,Long> getPeriodTurnOver();

    /**
     * 获得各月总营业额
     * @param year 指定年份
     * @return 月份，营业额
     */
    public HashMap<String, Long> getPeriodTurnOver(int year);

    /**
     * 获得各年总营业额
     * @param year
     * @param month
     * @return 日，营业额
     */
    public HashMap<String, Long> getHotelPeriodTurnOver(int year, int month);

    /**
     * 获取平台总订单数
     * @return
     */
    public long getTotalOrder();

    /**
     * 获得各年总订单数
     * @return 年，订单数
     */
    public HashMap<String,Long> getPeriodOrder();

    /**
     * 获得各月总订单数
     * @param year 指定年份
     * @return 月份，订单数
     */
    public HashMap<String, Long> getPeriodOrder(int year);

    /**
     * 获得各年总订单数
     * @param year
     * @param month
     * @return 日，订单数
     */
    public HashMap<String, Long> getPeriodOrder(int year, int month);

    /**
     * 获得指定日期销售额
     * @param year
     * @param month
     * @param day
     * @return
     */
    public long getTurnover(int year, int month, int day);

    /**
     * 获得指定日期订单数
     * @param year
     * @param month
     * @param day
     * @return
     */
    public long getOrder(int year, int month, int day);

    /**
     * 获得指定月份销售额
     * @param year
     * @param month
     * @return
     */
    public long getTurnover(int year, int month);

    /**
     * 获得指定月份订单数
     * @param year
     * @param month
     * @return
     */
    public long getOrder(int year, int month);

    /**
     * 获取个地区总销售额
     * @return 地区，销售额
     */
    public HashMap<String, Long> getAreaTurnover();

    /**
     * 获取个地区总订单数
     * @return 地区，订单数
     */
    public HashMap<String, Long> getAreaOrder();

    /**
     * 获得指定地区销售额排行前n名
     * @param area
     * @param n
     * @return 酒店名称,销售额
     */
    public HashMap<String,Long> getAreaTurnoverRanking(String area, int n);

    /**
     * 获得指定地区订单数排行前n名
     * @param area
     * @param n
     * @return 酒店名称,订单数
     */
    public HashMap<String,Long> getAreaOrderRanking(String area, int n);


}

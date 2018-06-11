package DAO;

import bean.Hotel;

import java.util.HashMap;

/**
 * Created by wangxue on 2018/6/9.
 */
public interface HotelDAO {

    /**
     * 添加酒店
     * @param hotel
     * @return
     */
    public boolean addHotel(Hotel hotel);

    /**
     * 修改酒店
     * @param hotel
     * @return
     */
    public boolean modifyHotel(Hotel hotel);

    /**
     * 检测酒店登录信息
     * @param hotel
     * @return
     */
    public boolean checkHotel(Hotel hotel);

    /**
     * 根据ID得到酒店信息
     * @param hid
     * @return
     */
    public Hotel getHotelInfo(int hid);

    /**
     * 获得酒店总营业额
     * @param hid
     * @return
     */
    public long getHotelTotalTurnover(int hid);

    /**
     * 获得酒店各年总营业额
     * @param hid
     * @return 年，营业额
     */
    public HashMap<String,Long> getHotelPeriodTurnOver(int hid);

    /**
     * 获得酒店各月总营业额
     * @param year 指定年份
     * @param hid
     * @return 月份，营业额
     */
    public HashMap<String, Long> getHotelPeriodTurnOver(int year, int hid);

    /**
     * 获得酒店各年总营业额
     * @param year
     * @param month
     * @param hid
     * @return 日，营业额
     */
    public HashMap<String, Long> getHotelPeriodTurnOver(int year, int month, int hid);

    /**
     * 获得酒店总订单数
     * @param hid
     * @return
     */
    public long getHotelOrder(int hid);

    /**
     * 获得酒店各年总订单数
     * @param hid
     * @return 年，订单数
     */
    public HashMap<String,Long> getHotelPeriodOrder(int hid);

    /**
     * 获得酒店各月总订单数
     * @param year 指定年份
     * @param hid
     * @return 月份，订单数
     */
    public HashMap<String, Long> getHotelPeriodOrder(int year, int hid);

    /**
     * 获得酒店各年总订单数
     * @param year
     * @param month
     * @param hid
     * @return 日，订单数
     */
    public HashMap<String, Long> getHotelPeriodOrder(int year, int month, int hid);

    /**
     * 获得酒店指定日期销售额
     * @param year
     * @param month
     * @param day
     * @param hid
     * @return
     */
    public long getHotelTurnover(int year, int month, int day, int hid);

    /**
     * 获得酒店指定日期订单数
     * @param year
     * @param month
     * @param day
     * @param hid
     * @return
     */
    public long getHotelOrder(int year, int month, int day, int hid);

    /**
     * 获得酒店指定月份销售额
     * @param year
     * @param month
     * @param hid
     * @return
     */
    public long getHotelTurnover(int year, int month, int hid);

    /**
     * 获得酒店指定月份订单数
     * @param year
     * @param month
     * @param hid
     * @return
     */
    public long getHotelOrder(int year, int month, int hid);

    /**
     * 获取各类型订单数
     * @return 0，1，2，3 未完成 已完成 已取消 已评价 订单数
     */
    public long[] getTypeOrder(int hid);

    /**
     * 获取酒店各月评价
     * @param hid
     * @return 月份，评价平均值
     */
    public HashMap<String, Double> getComment(int hid);

}

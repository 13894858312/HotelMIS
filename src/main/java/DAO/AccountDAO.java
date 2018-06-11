package DAO;

import bean.Account;

import java.util.HashMap;

/**
 * Created by wangxue on 2018/6/9.
 */
public interface AccountDAO {

    /**
     * 添加账户
     * @param account 全部信息
     * @return 是否成功
     */
    public boolean addAccount(Account account);

    /**
     * 修改账户
     * @param account 全部信息
     * @return 是否成功
     */
    public boolean modifyAccount(Account account);

    /**
     * 检查账户信息正确性
     * @param account uid，密码，账户类型
     * @return 是否正确
     */
    public boolean checkAccount(Account account);

    /**
     * 根据uid获取账户信息
     * @param uid 用户账户id
     * @return 账户信息
     */
    public Account getInfo(int uid);

    /**
     * 获取平台每月用户数统计
     * @return 月/新注册人数
     */
    public HashMap<String, Long> getMonthlyAnalysis();

    /**
     * 获取全部用户数量
     * @return 用户数
     */
    public long getUserNum();

    /**
     * 获取月活跃（有订单产生）用户数
     * @param year 年 month 月
     * @return 该月活跃用户数
     */
    public long getActiveUserNum(int year, int month);

    /**
     * 获取用户各地区订单数
     * @param uid
     * @return
     */
    public HashMap<String, Long> getUserAreaOrder(int uid);

    /**
     * 获取用户各地区销售额
     * @param uid
     * @return
     */
    public HashMap<String, Long> getUserAreaTurnover(int uid);

    /**
     * 获取用户分年销售额
     * @param uid
     * @return
     */
    public HashMap<String, Long> getUserPeriodTurnover(int uid);

    /**
     * 获取用户分月销售额
     * @param year
     * @param uid
     * @return
     */
    public HashMap<String, Long> getUserPeriodTurnover(int year, int uid);

    /**
     * 获取用户分年订单数
     * @param uid
     * @return
     */
    public HashMap<String, Long> getUserPeriodOrder(int uid);

    /**
     * 获取用户分月订单数
     * @param year
     * @param uid
     * @return
     */
    public HashMap<String, Long> getUserPeriodOrder(int year, int uid);

    /**
     * 获取用户指定月份销售额信息
     * @param year
     * @param month
     * @param uid
     * @return
     */
    public long getUserTurnover(int year,int month, int uid);

    /**
     * 获取用户全部订单数
     * @param uid
     * @return
     */
    public long getUserTotalOrder(int uid);

    /**
     * 获取用户全部消费额
     * @param uid
     * @return
     */
    public long getUserTotalTurnover(int uid);

    /**
     * 获取用户各类型订单数
     * @return 0，1，2,3 已完成 未完成 已取消 订单数 已评价
     */
    public long[] getUserTypeOrder(int uid);

}

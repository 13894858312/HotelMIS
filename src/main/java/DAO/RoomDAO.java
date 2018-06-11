package DAO;

import bean.Room;

import java.util.HashMap;

/**
 * Created by wangxue on 2018/6/9.
 */
public interface RoomDAO {

    /**
     * 添加房间
     * @param room
     * @return
     */
    public boolean addRoom(Room room);

    /**
     * 修改房间
     * @param room
     * @return
     */
    public boolean modifyRoom(Room room);

    /**
     * 获取酒店房间信息
     * @param hid
     * @param rid
     * @return
     */
    public Room getRoomInfo(int hid, int rid);

    /**
     * 获取酒店各类客房数量
     * @param hid
     * @return 客房类型，数量
     */
    public HashMap<String, Long> getHotelRoomInfo(int hid);

    /**
     * 获取酒店各类客房销售额
     * @param hid
     * @return 客房类型，销售额
     */
    public HashMap<String, Long> getRoomTurnover(int hid);

    /**
     * 获取酒店各类客房订单数
     * @param hid
     * @return 客房类型，订单数
     */
    public HashMap<String, Long> getRoomOrder(int hid);

    /**
     * 获取酒店指定日期已预订房间数量
     * @param year stime
     * @param month
     * @param day
     * @param hid
     * @return
     */
    public HashMap<String, Long> getReservedRoomInfo(int year, int month, int day, int hid);
}

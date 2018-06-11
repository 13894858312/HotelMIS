import DAO.AccountDAO;
import DAO.HotelDAO;
import DAO.OrderDAO;
import DAO.RoomDAO;
import data.AccountData;
import data.HotelData;
import data.OrderData;
import data.RoomData;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by wangxue on 2018/6/9.
 */
public class DataTest {
    AccountDAO accountDAO = new AccountData();
    HotelDAO hotelDAO = new HotelData();
    OrderDAO orderDAO = new OrderData();
    RoomDAO roomDAO = new RoomData();

    @Test
    public void accountTest(){
        Assert.assertEquals("1", accountDAO.getInfo(1).getPwd());
        HashMap<String,Long> result = accountDAO.getMonthlyAnalysis();
        Iterator<String> iterator = result.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            System.out.println(key+" "+result.get(key));
        }
        Assert.assertEquals(5,accountDAO.getUserNum());
        Assert.assertEquals(1,accountDAO.getActiveUserNum(2018,06));
    }
}

package zhou.com.theold.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhou on 2017/8/23.
 *
 * 获取系统当前时间
 */

public class CurrentTimeUtil {
    public static String nowTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return format.format(date);
    }
}

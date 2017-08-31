package zhou.com.theold.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zhou on 2017/8/23.
 */

public class ToastUtil {
    public static void show(Context context,String value){
        Toast.makeText(context,value,Toast.LENGTH_SHORT).show();
    }
}

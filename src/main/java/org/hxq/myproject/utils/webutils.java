package org.hxq.myproject.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class webutils {
    public static void copypartobean(Map map, Object bean){
        try {
            BeanUtils.populate(bean,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
    /**
     * 将字符串转换成为int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue) {
        try {if(strInt!=null&&strInt!="")
            return Integer.parseInt(strInt);
            else return defaultValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
    public static int parsebig(String strInt,int defaultValue) {
        try {if(strInt!=null&&strInt!="")
            return Integer.parseInt(strInt);

        else return defaultValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}

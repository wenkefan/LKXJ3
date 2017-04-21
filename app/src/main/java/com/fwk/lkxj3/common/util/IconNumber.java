package com.fwk.lkxj3.common.util;

/**
 * Created by fanwenke on 2017/4/21.
 */

public class IconNumber {

    public static int Sujishu = 0;

    public static int getSujishu(int size){
        if (Sujishu < size){
            return Sujishu;
        } else {
            return Sujishu % size;
        }
//        return Sujishu < size ? Sujishu : Sujishu % size;
    }
}

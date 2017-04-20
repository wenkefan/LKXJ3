package com.fwk.lkxj3.common.util;

/**
 * Created by fanwenke on 16/10/18.
 */

public class NumberUtils {
    private int TotalNumber;

    /**
     * 设置一个条目的总分
     *
     * @param initNumber 总分的初始值
     */
    public void setInitTotalPoints(int initNumber) {
        this.TotalNumber = initNumber;
    }

    /**
     * 求总分数
     *
     * @param eachMaxNumber 每个条目的最大值
     * @return
     */
    public int getTotalPoints(int eachMaxNumber) {
        TotalNumber = TotalNumber + eachMaxNumber;
        return TotalNumber;
    }

    /**
     * 获取平均分
     *
     * @param totalNumber 总分
     * @param totalItme   总条目
     * @return 平均分
     */
    public int getAverage(int totalNumber, int totalItme) {
        int average = totalNumber / totalItme * 100;
        return average;
    }

    /**
     * 加1
     *
     * @param average      平均分
     * @param actualNumber
     * @return
     */
    public int jia1(int average, int actualNumber) {
        int actualNumbers = actualNumber + average;
        return actualNumbers;
    }

    /**
     * @param average      平均分
     * @param actualNumber
     * @return
     */
    public int jian1(int average, int actualNumber) {
        int actualNumbers = actualNumber - average;
        return actualNumbers;
    }

    public static int Type12(int a, String aN, int totlaNumber) {
        int aa = Integer.parseInt(aN);
        return a * aa / totlaNumber;
    }

    public static int Type3(int a, String aN, int b, String bN, int totlaNumber) {
        int aa = Integer.parseInt(aN);
        int bb = Integer.parseInt(bN);
        LogUtils.d("a:" + a + "--an:" + aN + "--b:" + b + "--bn:" + bN + "--totla:" + totlaNumber);
        return (a * aa + b * bb) / totlaNumber;
    }

    public static int Type4(int a, String aN, int b, String bN, int c, String cN, int totalNumber) {
        int aa = Integer.parseInt(aN);
        int bb = Integer.parseInt(bN);
        int cc = Integer.parseInt(cN);
        return (a * aa + b * bb + c * cc) / totalNumber;
    }

    public static int Type56(int a, String aN, int b, String bN, int c, String cN,int d, String dN, int totalNumber) {
        LogUtils.d("an:" + aN + "bn:"+bN+"cn:"+cN+"dn:"+dN);
        int aa = Integer.valueOf(aN).intValue();
        int bb = Integer.valueOf(bN);
        int cc = Integer.valueOf(cN);
        int dd = Integer.valueOf(dN);
        return (a * aa + b * bb + c * cc + d * dd) / totalNumber;
    }
}

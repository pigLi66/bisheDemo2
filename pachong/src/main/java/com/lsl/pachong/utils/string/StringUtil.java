package com.lsl.pachong.utils.string;

/**
 * @author lisiliang
 * @since 2020/3/2
 */
public class StringUtil {

    /**
     * 用于去除中文换行
     * @param str
     * @return
     */
    public static String trimCN(String str) {
        if (str == null) return null;
        char[] value = str.toCharArray();
        int var1 = value.length;
        int var2 = 0;

        char[] var3;
        for(var3 = value; var2 < var1 && (var3[var2] <= ' ' || var3[var2] == '　'); ++var2) {
        }

        while(var2 < var1 && var3[var1 - 1] <= '　') {
            --var1;
        }
        return var2 <= 0 && var1 >= value.length ? str : str.substring(var2, var1);
    }

}

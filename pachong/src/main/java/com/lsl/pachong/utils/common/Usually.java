package com.lsl.pachong.utils.common;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author lisiliang
 * @since 2020/2/25
 */
public class Usually {

    public static void print(Collection<?> collection) {
        if (!collection.isEmpty()) {
            collection.forEach(System.out::println);
        }
        printCutLine();
    }

    public static void print(Object[] os) {
        print(Arrays.asList(os));
    }

    public static void print(Object o) {
        System.out.println(o.getClass());
        System.out.println(o);
        printCutLine();
    }

    public static void printCutLine() {
        for (int i=0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

}

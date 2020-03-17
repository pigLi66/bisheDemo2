package com.lsl.demo.utils.db;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.*;

/**
 * @author lisiliang
 * @since 2020/3/7
 */
public class PageBuilder {

    private PageBuilder(){}

    public static<T> Page<T> build(int startPage, int pageSize, List<T> elem) {
        Page<T> rs = new Page<>(startPage, pageSize);
        rs.setTotal(elem.size());
        rs.setCurrent(startPage);
        List<T> records = new ArrayList<>(pageSize);
        if (elem.size() > (startPage - 1) * pageSize) {
            int len = Math.min(startPage * pageSize, elem.size());
            for (int i = (startPage - 1) * pageSize; i < len; i++) {
                records.add(elem.get(i));
            }
        }
        rs.setRecords(records);
        rs.setSize(records.size());
        return rs;
    }

}

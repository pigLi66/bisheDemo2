package com.lsl.demo.utils;

import cn.hutool.core.collection.CollectionUtil;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lisiliang
 * @since 2020/1/11
 */
public class ConvertUtil {

    public static<T> List<T> sourceToTarget(Collection<?> sourceList, Class<T> targetClass) {
        List<T> targetList;

        if (CollectionUtil.isEmpty(sourceList)) {
            targetList = new ArrayList<>();
        } else {
            targetList = sourceList.stream().map(item->sourceToTarget(item, targetClass)).collect(Collectors.toList());
        }

        return targetList;
    }

    public static<T> T sourceToTarget(Object source, Class<T> target) {
        T targetObj = null;

        if (Objects.nonNull(source)) {
            try {
                targetObj = target.newInstance();
                BeanUtils.copyProperties(source, targetObj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return targetObj;
    }

}

package com.dimple.project.home.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * description
 * </p>
 *
 * @author Dimple
 * @date 06/08/20 11:00
 */
@Data
public class AccessDataResult {
    List<AccessData> out = new ArrayList<>();
    List<AccessData> inner = new ArrayList<>();
    List<String> legendData = new ArrayList<>();
    List<String> innerLegendData = new ArrayList<>();


    public AccessDataResult complete() {
        List<AccessData> dataList = new ArrayList<>();
        dataList.addAll(out);
        dataList.addAll(inner);
        legendData = dataList.stream().sorted(Comparator.comparing(AccessData::getValue).reversed()).map(AccessData::getName).collect(Collectors.toList());
        innerLegendData = inner.stream().sorted(Comparator.comparing(AccessData::getValue).reversed()).map(AccessData::getName).collect(Collectors.toList());
        return this;
    }

}

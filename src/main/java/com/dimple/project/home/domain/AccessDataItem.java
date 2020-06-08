package com.dimple.project.home.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * description
 * </p>
 *
 * @author Dimple
 * @date 06/08/20 11:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessDataItem {
    private String name;
    private long value = 0;
    private String filter;
}

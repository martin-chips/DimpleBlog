package com.dimple.project.home.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 * description
 * </p>
 *
 * @author Dimple
 * @date 06/05/20 10:15
 */
@Data
@AllArgsConstructor
public class KeyValue {
    private String key;
    private Long value;
}

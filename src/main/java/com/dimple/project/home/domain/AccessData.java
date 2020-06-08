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
 * @date 06/08/20 11:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessData {
    private String name;
    private long value = 0;
}

package com.dimple.common.core.utils.beans;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Cat
 *
 * @author Dimple
 * @date 12/26/2022 8:57 AM
 */
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Car {
    private String brand;

    private String type;
}

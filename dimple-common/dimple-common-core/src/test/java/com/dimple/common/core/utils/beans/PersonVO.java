package com.dimple.common.core.utils.beans;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * PersonVO
 *
 * @author Dimple
 * @date 12/26/2022 8:57 AM
 */
@Data
public class PersonVO {
    private List<Car> carList;
    private LocalDateTime birthday;
}

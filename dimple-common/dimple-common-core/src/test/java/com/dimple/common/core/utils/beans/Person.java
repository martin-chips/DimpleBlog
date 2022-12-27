package com.dimple.common.core.utils.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Person
 *
 * @author Dimple
 * @date 12/26/2022 8:56 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Person {
    private String name;

    private Integer age;

    private List<Car> carList;

    private LocalDateTime birthday;
}

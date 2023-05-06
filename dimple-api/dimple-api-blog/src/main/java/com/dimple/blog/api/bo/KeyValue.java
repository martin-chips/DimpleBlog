package com.dimple.blog.api.bo;

import lombok.Data;

/**
 * KeyValue
 *
 * @author Dimple
 * @date 3/2/2023 5:19 PM
 */
@Data
public class KeyValue<K, V> {
    private K key;
    private V value;
}

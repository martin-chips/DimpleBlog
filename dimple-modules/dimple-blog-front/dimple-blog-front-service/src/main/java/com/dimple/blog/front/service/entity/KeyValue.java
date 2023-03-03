package com.dimple.blog.front.service.entity;

import lombok.Data;

/**
 * KeyValue
 *
 * @author BianXiaofeng
 * @date 3/2/2023 5:19 PM
 */
@Data
public class KeyValue<K, V> {
    private K key;
    private V value;
}

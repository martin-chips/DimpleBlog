package com.dimple.common.core.domain;

import com.dimple.common.core.constant.HttpStatus;
import lombok.Data;

/**
 * ResponseEntity
 *
 * @author Dimple
 * @date 12/23/2022 2:34 PM
 */
@Data
public class ResponseEntity<T> {
    private int code;

    private String msg;

    private T data;

    private T rows;

    private Long total;

    public boolean isOk() {
        return this.code == HttpStatus.SUCCESS;
    }
}

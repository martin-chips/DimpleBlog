package com.dimple.common.log.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * RequestType
 *
 * @author Dimple
 */
@AllArgsConstructor
@Getter
public enum RequestType {
    PAGE_REQUEST(1),
    API_REQUEST(0);
    private Integer code;
}

package com.dimple.normal;

import org.junit.Test;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : NormalTest
 * @description :
 * @date : 01/02/19 15:28
 */

public class NormalTest {


    @Test
    public void testString() {
        String url = "http://localhost:8080/1/d0096ec6c83575373e3a21d129ff8fef.jpg";
        System.out.println(url.lastIndexOf("/images/"));
        System.out.println(url.substring(url.lastIndexOf("/images/")));
    }

}

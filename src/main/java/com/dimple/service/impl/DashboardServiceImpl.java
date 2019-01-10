package com.dimple.service.impl;

import com.dimple.service.BlogService;
import com.dimple.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : IndexServiceImpl
 * @description :
 * @date : 01/10/19 14:06
 */
@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    BlogService blogService;


}

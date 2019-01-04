package com.dimple.service;

import com.dimple.bean.Rotation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : RotationService
 * @description :
 * @date : 01/03/19 20:41
 */
public interface RotationService {

    Page<Rotation> getRotations(String title, Boolean display, Date startTime, Date endTime, Pageable pageable);

    Rotation getRotationById(Integer id);

    int deleteRotationById(Integer[] id);

    void insertRotation(Rotation rotation);

    void updateRotation(Rotation rotation);

    void changeDisplay(Integer id, Boolean status);
}

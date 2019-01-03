package com.dimple.service;

import com.dimple.bean.Setting;
import com.dimple.bean.Signature;

/**
 * @ClassName: SettingService
 * @Description:
 * @Auther: Owenb
 * @Date: 01/03/19 12:12
 * @Version: 1.0
 */
public interface SettingService {

    void createSetting(Setting setting);

    Setting getSettingById(Integer id);

    Setting getSettingBySettingKey(String settingKey);

    void deleteSettingById(Integer id);

    void updateSetting(Setting setting);

    Signature getSignatureByKey(String key);


    void insertOrUpdateSignature(Signature signature);
}

package com.dimple.modules.common.service;

import com.dimple.modules.endModule.frontSetting.bean.Setting;
import com.dimple.modules.endModule.frontSetting.bean.Signature;

import java.util.List;

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

    void updateSetting(List<Setting> setting);

    Signature getSignatureByKey(String key);


    void insertOrUpdateSignature(Signature signature);

    List<Setting> getAllSetting();

    String getSettingValueBySettingKey(String settingKey);
}

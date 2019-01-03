package com.dimple.repository;

import com.dimple.bean.Setting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : SettingRepository
 * @description :
 * @date : 01/02/19 20:50
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SettingRepositoryTest {

    @Autowired
    SettingRepository settingRepository;

    @Test
    public void testInsertData() {
        Setting setting = new Setting();
        setting.setSettingKey("key");
        setting.setSettingValue("value");
        settingRepository.save(setting);
    }

    @Test
    public void testInsertDataUsingNative() {
        Setting setting = new Setting();
        setting.setSettingKey("key");
        setting.setSettingValue("value");
        settingRepository.createSetting(setting.getSettingKey(), setting.getSettingValue());
    }

}

package com.dimple.service;

import com.dimple.bean.Setting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : SettingServiceTest
 * @description :
 * @date : 01/03/19 12:36
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SettingServiceTest {

    @Autowired
    SettingService settingService;

    @Test
    public void testInsertData() {
        Setting setting = new Setting();
        setting.setSettingValue("value");
        setting.setSettingKey("key");
        settingService.createSetting(setting);
    }

    /**
     * 可以不用参数校验
     */
    @Test
    public void testGetSettingById() {
        System.out.println(settingService.getSettingById(1));//Setting(id=1, settingKey=key1, settingValue=value, note=null)
        System.out.println(settingService.getSettingById(null));//null
    }

    @Test
    public void testGetSettingBySettingKey() {
        System.out.println(settingService.getSettingBySettingKey("key"));//null
        System.out.println(settingService.getSettingBySettingKey("key1"));//Setting(id=1, settingKey=key1, settingValue=value, note=null)
        System.out.println(settingService.getSettingBySettingKey(null));//null

    }

    @Test
    public void testUpdateSetting() {
        Setting setting = new Setting();
        setting.setSettingKey("1");
        setting.setSettingValue("2");
        // settingService.updateSetting(setting);//Exception
        Setting setting1 = settingService.getSettingBySettingKey("key1");
        setting1.setSettingValue("value11");
        settingService.updateSetting(setting1);
    }


    @Test
    public void testDeleteSetting() {
        settingService.deleteSettingById(1);

    }
}
